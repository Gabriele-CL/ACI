package com.aci.test.ACI;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeggiFlussoExcel {

    public void LeggiFlussoExcel() {
        String filePath = "C:/Users/ugolini/Desktop/Test_Anta/Test acquisizione tracciati ANTA.xlsx";
        String sheetName = "Schema Bolzano";
        String flussoTarget = "ATPABAZ.D0023022.T011070";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                System.out.println("Foglio non trovato: " + sheetName);
                return;
            }

            // Riga 17 (indice 16) contiene i titoli
            Row headerRow = sheet.getRow(16);
            if (headerRow == null) {
                System.out.println("Riga header (16) non trovata.");
                return;
            }

            DataFormatter formatter = new DataFormatter(); // restituisce il testo come lo vedi in Excel
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Trovo le colonne "Flusso" e "TermineEsenzione"
            int colFlusso = -1;
            int colTermineEsenzione = -1;
            for (Cell cell : headerRow) {
                String value = formatter.formatCellValue(cell, evaluator).trim();
                if (value.equalsIgnoreCase("Flusso")) {
                    colFlusso = cell.getColumnIndex();
                } else if (value.equalsIgnoreCase("TermineEsenzione")) {
                    colTermineEsenzione = cell.getColumnIndex();
                }
            }

            if (colFlusso == -1 || colTermineEsenzione == -1) {
                System.out.println("Colonne 'Flusso' o 'TermineEsenzione' non trovate!");
                return;
            }

            List<String> risultati = new ArrayList<>();

            // Itero le righe successive all'intestazione
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int r = row.getRowNum();
                if (r <= 16) continue;        // salto intestazione
                if (row.getZeroHeight()) continue; // salta righe nascoste/filtrate

                // Leggo "Flusso" come appare in Excel
                String flusso = formatConsideringMerged(sheet, r, colFlusso, formatter, evaluator).trim();

                // Confronto flusso ignorando maiuscole/minuscole e spazi
                if (flusso.equalsIgnoreCase(flussoTarget)) {

                    // Leggo "TermineEsenzione" considerando celle unite
                    MergedRead res = readWithMergedInfo(sheet, r, colTermineEsenzione, formatter, evaluator);
                    String valore = res.value.trim();

                    if (!valore.isEmpty()) {
                        risultati.add(valore);
                    } else {
                        risultati.add("⚠️ Nessuna esenzione trovata.");
                    }
                }
            }

            // Stampo i risultati finali
            System.out.println("Totale corrispondenze trovate: " + risultati.size());
            System.out.println("Valori di TermineEsenzione per flusso " + flussoTarget + ":");
            for (String r : risultati) {
                System.out.println(r);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Utils ---------------------------------------------------------------

    // Ritorna il testo visualizzato in Excel per (rowIndex, colIndex), seguendo le merged cells
    private static String formatConsideringMerged(Sheet sheet, int rowIndex, int colIndex,
                                                  DataFormatter formatter, FormulaEvaluator evaluator) {
        MergedRead res = readWithMergedInfo(sheet, rowIndex, colIndex, formatter, evaluator);
        return res.value;
    }

    // Struttura per tornare anche l'informazione se il valore è stato preso dalla top-left di una merged region
    private static class MergedRead {
        final String value;
        final boolean fromMerged;
        MergedRead(String value, boolean fromMerged) {
            this.value = value;
            this.fromMerged = fromMerged;
        }
    }

    private static MergedRead readWithMergedInfo(Sheet sheet, int rowIndex, int colIndex,
                                                 DataFormatter formatter, FormulaEvaluator evaluator) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = (row == null) ? null : row.getCell(colIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        String formatted = (cell == null) ? "" : formatter.formatCellValue(cell, evaluator);

        if (formatted != null && !formatted.trim().isEmpty()) {
            return new MergedRead(formatted, false);
        }

        // Se vuoto, controlla se la cella fa parte di una merged region e prendi la top-left
        Cell topLeft = getTopLeftIfInMerged(sheet, rowIndex, colIndex);
        if (topLeft != null) {
            String mergedVal = formatter.formatCellValue(topLeft, evaluator);
            if (mergedVal != null && !mergedVal.trim().isEmpty()) {
                return new MergedRead(mergedVal, true);
            }
        }
        return new MergedRead("", false);
    }

    private static Cell getTopLeftIfInMerged(Sheet sheet, int rowIndex, int colIndex) {
        int numMerged = sheet.getNumMergedRegions();
        for (int i = 0; i < numMerged; i++) {
            CellRangeAddress region = sheet.getMergedRegion(i);
            if (region.isInRange(rowIndex, colIndex)) {
                Row firstRow = sheet.getRow(region.getFirstRow());
                if (firstRow != null) {
                    return firstRow.getCell(region.getFirstColumn(), Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                }
            }
        }
        return null;
    }
}
