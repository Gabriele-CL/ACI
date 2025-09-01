package com.aci.test.ACI;

import com.aci.test.POM.Gestione_Anta;
import com.aci.test.POM.Gestione_Anta.EsenzioneResult;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;

public class EsecuzioneTestAntaTest {

    private static final String LOG_FILE = "C:/Users/ugolini/Desktop/Test_Anta/test_anta.log";
    private static final String REPORT_FILE = "C:/Users/ugolini/Desktop/Test_Anta/report_anta.txt";
    private static final String EXCEL_INPUT_FILE = "C:/Users/ugolini/Desktop/Test_Anta/Test acquisizione tracciati ANTA.xlsx";
    private final Gestione_Anta anta = new Gestione_Anta();
    private final String outputDir = "C:/Users/ugolini/Desktop/Test_Anta/";

    @Test
    public void eseguiTestCompletoAnta() {
        log("🚀 Inizio esecuzione test completo Anta");

        String nomeUltimoFile = null;
        String report = "";

        try {
            anta.generaFileConProgressivoContinuo();
            nomeUltimoFile = ottieniNomeUltimoFileGenerato();
            log("✅ File generato e caricato via SFTP: " + nomeUltimoFile);
            report += "1. Generazione file: OK\n";
        } catch (Exception e) {
            log("❌ Errore durante generazione file: " + e.getMessage());
            report += "1. Generazione file: FALLITO\n";
        }

        try {
            anta.ControlloEsiti();
            log("✅ Verifica esito elaborazione completata.");
            report += "2. Verifica esito elaborazione: OK\n";
        } catch (Exception e) {
            log("❌ Errore nella verifica esito: " + e.getMessage());
            report += "2. Verifica esito elaborazione: FALLITO\n";
        }

        try {
            if (nomeUltimoFile != null) {
                String percorsoCompleto = outputDir + nomeUltimoFile;
                List<EsenzioneResult> risultati = anta.ControlloDataFineEsenzioneDaFile(percorsoCompleto);

                // ✅ Aggiunta dei dati da Excel
                aggiungiTermineEsenzioneDaExcel(risultati, nomeUltimoFile);

                String pathExcel = outputDir + "report_anta_esenzione.xlsx";
                scriviReportExcel(risultati, pathExcel);

                log("✅ Verifica Data Fine Esenzione completata.");
                report += "3. Verifica Data Fine Esenzione: OK (vedi Excel)\n";
            } else {
                log("⚠️ Nome file non disponibile per controllo date.");
                report += "3. Verifica Data Fine Esenzione: SKIPPATO\n";
            }
        } catch (Exception e) {
            log("❌ Errore nella verifica Data Fine Esenzione: " + e.getMessage());
            report += "3. Verifica Data Fine Esenzione: FALLITO\n";
        }

        scriviReportTestuale(report);
        log("📄 Report testuale salvato in: " + REPORT_FILE);

        log("✅ Fine esecuzione test.");
    }

    private void aggiungiTermineEsenzioneDaExcel(List<EsenzioneResult> risultati, String nomeFile) {
        try (FileInputStream fis = new FileInputStream(EXCEL_INPUT_FILE);
             Workbook workbookOrigine = new XSSFWorkbook(fis)) {

            Sheet sheetOrigine = workbookOrigine.getSheet("Schema Bolzano");
            if (sheetOrigine == null) {
                log("❌ Il foglio 'Schema Bolzano' non è stato trovato nel file sorgente.");
                return;
            }

            int colFileName = -1;
            int colTermineEsenzione = -1;

            Row header = sheetOrigine.getRow(0);
            for (Cell cell : header) {
                if (cell.getStringCellValue().equalsIgnoreCase("NomeFile")) {
                    colFileName = cell.getColumnIndex();
                } else if (cell.getStringCellValue().equalsIgnoreCase("TermineEsenzione")) {
                    colTermineEsenzione = cell.getColumnIndex();
                }
            }

            if (colFileName == -1 || colTermineEsenzione == -1) {
                log("❌ Colonne richieste non trovate nel foglio Excel.");
                return;
            }

            List<String> termini = new ArrayList<>();

            for (int i = 1; i <= sheetOrigine.getLastRowNum(); i++) {
                Row row = sheetOrigine.getRow(i);
                if (row == null) continue;

                Cell fileNameCell = row.getCell(colFileName);
                if (fileNameCell != null && nomeFile.equals(fileNameCell.getStringCellValue())) {
                    Cell termineCell = row.getCell(colTermineEsenzione);
                    if (termineCell != null) {
                        termini.add(termineCell.toString());
                    }
                }
            }

            // Appendi i valori trovati ai risultati esistenti
            for (int i = 0; i < risultati.size() && i < termini.size(); i++) {
                risultati.get(i).fileName = nomeFile;
                risultati.get(i).termineEsenzione = termini.get(i);
            }

        } catch (Exception e) {
            log("❌ Errore leggendo da Excel origine: " + e.getMessage());
        }
    }

    private String ottieniNomeUltimoFileGenerato() {
        File dir = new File(outputDir);
        File[] files = dir.listFiles((f, name) -> name.startsWith("ATPABAZ.D0013022.T"));
        int max = 0;

        if (files != null) {
            for (File file : files) {
                Matcher matcher = Pattern.compile("T(\\d{6})").matcher(file.getName());
                if (matcher.find()) {
                    int index = Integer.parseInt(matcher.group(1));
                    if (index > max) max = index;
                }
            }
        }

        return String.format("ATPABAZ.D0013022.T%06d", max);
    }

    private void log(String messaggio) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logLine = timestamp + " - " + messaggio;
            out.println(logLine);
            System.out.println(logLine);

        } catch (IOException e) {
            System.err.println("❌ Errore scrivendo nel log: " + e.getMessage());
        }
    }

    private void scriviReportTestuale(String contenuto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE))) {
            writer.write("=== Report Test Anta ===\n");
            writer.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            writer.write("\n\n");
            writer.write(contenuto);
        } catch (IOException e) {
            log("❌ Errore durante scrittura report: " + e.getMessage());
        }
    }

    private void scriviReportExcel(List<EsenzioneResult> risultati, String percorso) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Report Esenzioni");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Targa");
            header.createCell(1).setCellValue("Risultato");
            header.createCell(2).setCellValue("Nome File");
            header.createCell(3).setCellValue("Termine Esenzione");

            int rowNum = 1;
            for (EsenzioneResult result : risultati) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(result.targa);
                row.createCell(1).setCellValue(result.risultato);
                row.createCell(2).setCellValue(result.fileName);
                row.createCell(3).setCellValue(result.termineEsenzione);
            }

            for (int i = 0; i < 4; i++) sheet.autoSizeColumn(i);

            try (FileOutputStream fos = new FileOutputStream(percorso)) {
                workbook.write(fos);
            }

            log("📊 Report Excel salvato in: " + percorso);

        } catch (IOException e) {
            log("❌ Errore scrittura Excel: " + e.getMessage());
        }
    }
}
