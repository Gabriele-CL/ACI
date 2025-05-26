package com.aci.test.ACI;


import javax.mail.Session;
import java.io.*;
import java.util.Properties;

public class ACI_Anta {
    public static void main(String[] args) {
        String server = "172.24.225.100";
        int port = 22;
        String user = "prolazio";
        String pass = "KB33mBGF";

        String inputFile = "C:/Users/ugolini/Desktop/ATLAZAE.D0022056.T051005";
        String outputDir = "C:/Users/ugolini/Desktop/Test_Anta/";

        String filePrefix = "ATLAZAE.D";
        String fileSuffix = ".T051005";

        int fileCount = 1;
        int counter = 64;

        String prefix = "108M01";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String firstLine = reader.readLine();
            String lastLine = null;

            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
                lastLine = line;
            }

            String[] lines = content.toString().split("\n");

            for (int fileIndex = 1; fileIndex <= fileCount; fileIndex++) {
                String outputFile = outputDir + String.format("%s%04d%s", filePrefix, fileIndex, fileSuffix);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                    writer.write(firstLine);
                    writer.newLine();

                    for (int i = 1; i < lines.length - 1; i++) {
                        String modifiedLine = (lines[i].length() < 13)
                                ? lines[i]
                                : prefix + String.format("SA%03dAA", counter++) + lines[i].substring(13);
                        writer.write(modifiedLine);
                        writer.newLine();
                    }

                    // Calcoliamo il numero di righe (escludendo la prima e l'ultima riga)
                    int numberOfLines = lines.length;

                    // Rimuoviamo la parte numerica finale da lastLine
                    if (lastLine != null) {
                        // Prepara la nuova parte numerica in base al numero di righe
                        String newLastNumber = String.format("%09d", numberOfLines); // Formatta il numero con 9 cifre

                        // Trova la posizione dove sostituire il numero
                        lastLine = lastLine.substring(0, 31) + "    " + newLastNumber;

                        // Scrive la riga modificata
                        writer.write(lastLine);
                        writer.newLine();
                    }

                }

                //System.out.println("File generato: " + outputFile);
               // uploadFile(outputFile, server, port, user, pass);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void uploadFile(String localFilePath, String server, int port, String user, String pass) {
        Session session = null;
        ChannelSftp channelSftp = null;

        try {
            JSch jsch = new JSch();
            session = jsch.getSession(user, server, port);
            session.setPassword(pass);

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            File localFile = new File(localFilePath);
            if (!localFile.exists()) {
                System.err.println("Errore: Il file non esiste - " + localFilePath);
                return;
            }

            String remoteFile = "/in/" + localFile.getName();
            try (FileInputStream inputStream = new FileInputStream(localFile)) {
                channelSftp.put(inputStream, remoteFile);
                System.out.println("File caricato con successo: " + remoteFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }*/
}
