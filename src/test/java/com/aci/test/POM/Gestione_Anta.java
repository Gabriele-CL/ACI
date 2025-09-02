package com.aci.test.POM;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.*;
import com.jcraft.jsch.*;
import java.sql.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

public class Gestione_Anta {

    private final String filePath = "C:/Users/xclt03/Desktop/ATPABAZ.D0013022.T000050";
    private final String outputDir = "C:/Users/xclt03/Desktop/Test_Anta/";
    private final String prefissoNomeFile = "ATPABAZ.D0013022.";

    // ✅ Metodo aggiornato
    public void generaFileConProgressivoContinuo() {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));

            if (allLines.size() < 3) {
                System.out.println("⚠️ Il file è troppo corto per contenere righe utili.");
                return;
            }

            String primaRiga = allLines.get(0);
            String ultimaRiga = allLines.get(allLines.size() - 1);
            List<String> righeIntermedie = allLines.subList(1, allLines.size() - 1);

            // Calcola l'ultimo SC usato tra tutti i file esistenti
            // Leggi la targa da ultima_riga.txt, se esiste
            int counter;
            File ultimaRigaFile = new File("C:/Users/xclt03/Desktop/Test_Anta/Test_Anta/ultima_riga.txt");

            if (ultimaRigaFile.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(ultimaRigaFile))) {
                    String linea = br.readLine();
                    if (linea != null && linea.matches("SC\\d{3}AA")) {
                        int ultimoNumero = Integer.parseInt(linea.substring(2, 5)); // estrae nnn da SCnnnAA
                        counter = ultimoNumero + 1;
                    } else {
                        counter = trovaUltimoNumeroSC() + 1;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    counter = trovaUltimoNumeroSC() + 1;
                }
            } else {
                counter = trovaUltimoNumeroSC() + 1;
            }

            List<String> righeModificate = new ArrayList<>();
            righeModificate.add(primaRiga);

            for (String riga : righeIntermedie) {
                if (riga.length() < 13) {
                    righeModificate.add(riga);
                    continue;
                }

                String prefisso = riga.substring(0, 6); // es. "108M01"
                String nuovaParte = String.format("SC%03dAA", counter++);
                String resto = riga.substring(13);
                String nuovaRiga = prefisso + nuovaParte + resto;

                righeModificate.add(nuovaRiga);
            }

            righeModificate.add(ultimaRiga);

            // Nome del file nuovo
            int nextFileIndex = calcolaProssimoIndiceFile();
            String nomeFileOutput = String.format(prefissoNomeFile + "T%06d", nextFileIndex);
            String outputPath = outputDir + nomeFileOutput;

            // Scrivi il file modificato
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
                for (String riga : righeModificate) {
                    writer.write(riga);
                    writer.newLine();
                }
            }

            System.out.println("✅ File generato: " + outputPath);
            System.out.println("📄 Contenuto:");
            righeModificate.forEach(System.out::println);

            // Upload via SFTP
            caricaFileSuSFTP(outputPath, "/in", nomeFileOutput);

            // Ultima targa scritta su file di log
            String ultimaTarga = "";
            Pattern pattern = Pattern.compile("SC\\d{3}AA");
            for (int i = righeModificate.size() - 1; i >= 0; i--) {
                Matcher matcher = pattern.matcher(righeModificate.get(i));
                if (matcher.find()) {
                    ultimaTarga = matcher.group();
                    break;
                }
            }

            if (!ultimaTarga.isEmpty()) {
                System.out.println("📌 Ultima targa generata: " + ultimaTarga);
                File outputTarga = new File("C:/Users/xclt03/Desktop/Test_Anta/Test_Anta/ultima_riga.txt");
                outputTarga.getParentFile().mkdirs();
                try (BufferedWriter targaWriter = new BufferedWriter(new FileWriter(outputTarga))) {
                    targaWriter.write(ultimaTarga);
                    targaWriter.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Trova il numero SC più alto tra tutti i file
    private int trovaUltimoNumeroSC() {
        File dir = new File(outputDir);
        File[] files = dir.listFiles((d, name) -> name.startsWith(prefissoNomeFile + "T"));

        int maxNumeroSC = 0;
        Pattern pattern = Pattern.compile("SC(\\d{3})AA");

        if (files != null) {
            for (File file : files) {
                try {
                    List<String> lines = Files.readAllLines(file.toPath());
                    for (String line : lines) {
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            int numero = Integer.parseInt(matcher.group(1));
                            if (numero > maxNumeroSC) {
                                maxNumeroSC = numero;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return maxNumeroSC;
    }

    // ✅ Calcola il prossimo Tnnnnnn
    private int calcolaProssimoIndiceFile() {
        File dir = new File(outputDir);
        File[] files = dir.listFiles((d, name) -> name.startsWith(prefissoNomeFile + "T"));

        int maxIndex = 0;

        if (files != null) {
            for (File file : files) {
                Matcher matcher = Pattern.compile("T(\\d{6})").matcher(file.getName());
                if (matcher.find()) {
                    int index = Integer.parseInt(matcher.group(1));
                    if (index > maxIndex) {
                        maxIndex = index;
                    }
                }
            }
        }

        return maxIndex + 1;
    }

    // ✅ Upload SFTP
    public void caricaFileSuSFTP(String localFilePath, String remoteDir, String remoteFileName) {
        final String host = "172.24.225.100";
        final int port = 22;
        final String user = "prolazio";
        final String password = "KB33mBGF";

        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            File file = new File(localFilePath);
            if (!file.exists() || !file.isFile()) {
                System.err.println("❌ File locale non trovato: " + localFilePath);
                return;
            }

            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(10000);

            Channel channel = session.openChannel("sftp");
            channel.connect(5000);
            channelSftp = (ChannelSftp) channel;

            channelSftp.cd(remoteDir);
            channelSftp.put(localFilePath, remoteFileName);

            SftpATTRS attrs = channelSftp.lstat(remoteDir + "/" + remoteFileName);
            System.out.println("✅ Upload completato: " + remoteFileName + " (" + attrs.getSize() + " bytes)");

        } catch (SftpException | JSchException | RuntimeException e) {
            System.err.println("❌ Errore durante upload: " + e.getMessage());
        } finally {
            if (channelSftp != null && channelSftp.isConnected()) channelSftp.exit();
            if (session != null && session.isConnected()) session.disconnect();
        }
    }
    public void ControlloEsiti() {
        String url = "jdbc:postgresql://172.24.225.239:5444/pro_nartpt01t";
        String username = "pro_tst_adm";
        String password = "usrtestpro";

        String query = "SELECT id_audit, response FROM anagrafe.e_audit_eventi WHERE event_header = 'ATPABAZ.D0023022.T000003'";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            boolean tuttoOk = true;
            int count = 0;

            while (rs.next()) {
                count++;
                int idAudit = rs.getInt("id_audit");
                String response = rs.getString("response");

                boolean isElaborato = false;

                if (response != null) {
                    try {
                        JSONObject json = new JSONObject(response.trim());
                        String esito = json.optString("esito", "");
                        if ("ELABORATO".equalsIgnoreCase(esito)) {
                            isElaborato = true;
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Errore parsing JSON per id_audit = " + idAudit);
                    }
                }

                if (!isElaborato) {
                    tuttoOk = false;
                    System.out.println("❌ Riga con esito diverso da 'ELABORATO': id_audit = " + idAudit + ", response = " + response);
                }
            }

            if (count == 0) {
                System.out.println("⚠️ Nessuna riga trovata.");
            } else if (tuttoOk) {
                System.out.println("✅ Tutte le " + count + " righe hanno esito 'ELABORATO'.");
            } else {
                System.out.println("⚠️ Alcune righe non sono 'ELABORATO'.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EsenzioneResult> ControlloDataFineEsenzioneDaFile(String percorsoFile) {
        List<EsenzioneResult> risultati = new ArrayList<>();
        String url = "jdbc:postgresql://172.24.225.239:5444/pro_nartpt01t";
        String username = "pro_tst_adm";
        String password = "usrtestpro";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        try {
            List<String> righe = Files.readAllLines(Paths.get(percorsoFile));
            // Salta prima e ultima riga
            List<String> righeDaProcessare = righe.subList(1, righe.size() - 1);

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                int index = 0;

                for (String riga : righeDaProcessare) {
                    index++;

                    if (riga.length() >= 15) {
                        String targa = riga.substring(6, 15).trim();

                        String query = "SELECT id_esenzione, data_fine_validita " +
                                "FROM anagrafe.e_esenzione " +
                                "WHERE targa = '" + targa + "'";

                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(query)) {

                            boolean trovato = false;

                            while (rs.next()) {
                                trovato = true;
                                Date dataFine = rs.getDate("data_fine_validita");

                                String risultato = (dataFine != null)
                                        ? sdf.format(dataFine)
                                        : "❌ Nessuna data fine.";

                                EsenzioneResult res = new EsenzioneResult(targa, risultato);
                                risultati.add(res);

                                // 👉 stampa solo la data / messaggio
                                System.out.println(risultato);
                            }

                            if (!trovato) {
                                EsenzioneResult res = new EsenzioneResult(targa, "⚠️ Nessuna esenzione trovata.");
                                risultati.add(res);

                                System.out.println("⚠️ Nessuna esenzione trovata.");
                            }

                        } catch (SQLException e) {
                            EsenzioneResult res = new EsenzioneResult(targa, "❌ Errore query DB: " + e.getMessage());
                            risultati.add(res);
                            System.out.println("❌ Errore query DB: " + e.getMessage());
                        }

                    } else {
                        EsenzioneResult res = new EsenzioneResult("?", "⚠️ Riga non valida: " + riga);
                        risultati.add(res);
                        System.out.println("⚠️ Riga non valida: " + riga);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            EsenzioneResult res = new EsenzioneResult("GLOBAL", "❌ Errore generale: " + e.getMessage());
            risultati.add(res);
            System.out.println("❌ Errore generale: " + e.getMessage());
        }

        System.out.println("\nTotale corrispondenze trovate: " + risultati.size());
        return risultati;


    }





    // Metodo privato per leggere le targhe dal file txt
    public static List<String> leggiTargheDaFileTxt(String path) throws IOException {
        List<String> targhe = new ArrayList<>();
        List<String> lines = java.nio.file.Files.readAllLines(new File(path).toPath());

        // Salta la prima e ultima riga
        for (int i = 1; i < lines.size() - 1; i++) {
            String line = lines.get(i).trim();
            if (line.length() >= 13) {
                String targaCandidata = line.substring(0, 13); // primi 13 caratteri
                if (targaCandidata.startsWith("103M01")) {      // opzionale: controlla che inizi con prefisso atteso
                    targhe.add(targaCandidata);
                } else {
                    System.out.println("⚠️  Non inizia con '103M01', ignorata.");
                }
            } else {
                System.out.println("⚠️  Riga troppo corta, ignorata.");
            }
        }

        return targhe;
    }



    // Metodo privato per contare righe nel foglio Excel
    public static int contaRigheNelFoglioExcel(String pathExcel, String nomeFile) throws Exception {
        try (FileInputStream fis = new FileInputStream(pathExcel);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Schema Bolzano");
            if (sheet == null) throw new RuntimeException("❌ Foglio 'Schema Bolzano' non trovato.");

            int count = 0;

            // 🆕 Prende solo il nome del file, senza percorso
            String nomeFileSemplice = new File(nomeFile).getName();

            // Inizia dalla riga 18 (indice 17)
            for (int i = 17; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell cell = row.getCell(0); // Prima colonna
                if (cell == null) continue;

                String value = cell.toString().trim();
                if (value.contains(nomeFileSemplice)) {
                    count++;
                }
            }

            return count;
        }
    }

    public static class EsenzioneResult {
        public String targa;
        public String risultato;

        // 🔽 Aggiungi questi due nuovi campi:
        public String fileName;
        public String termineEsenzione;

        // 🔽 Costruttore aggiornato
        public EsenzioneResult(String targa, String risultato) {
            this.targa = targa;
            this.risultato = risultato;
        }

        // (facoltativo: puoi aggiungere un secondo costruttore completo)
        public EsenzioneResult(String targa, String risultato, String fileName, String termineEsenzione) {
            this.targa = targa;
            this.risultato = risultato;
            this.fileName = fileName;
            this.termineEsenzione = termineEsenzione;
        }
    }


}