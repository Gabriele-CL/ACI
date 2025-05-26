package com.aci.test.ACI;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ACI_Anta_PRA {
    private static int counter = 0;
    public static void main(String[] args) {


        String inputFile = "C:/Users/ugolini/Desktop/ATLAZQE.D2024365.T074133";
        String outputFile = "C:/Users/ugolini/Desktop/Test_Anta/ATLAZQE.D2024365.T074133";
        StringBuilder content = new StringBuilder();

        try (BufferedReader read = new BufferedReader(new FileReader(inputFile))) {


            String line;
            while ((line = read.readLine()) != null) {
                if (line.startsWith("1")) {
                    line = modifyLine(line);
                }
                content.append(line).append("\n");

            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(content.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        private static String modifyLine (String line){
            if (line.length() < 42) {
                return line;
            } else {

                String inizio = line.substring(0, 35);
                String nuovo = "SA" + String.format("%03d", counter++) + "A" + " ";
                String fine = line.substring(42);

                return inizio + nuovo + fine;
            }
        }

    }

