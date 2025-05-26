package com.aci.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils_InstanzaRimborsoRegimiSpeciali {

    @DataProvider(name = "ACIWorksheet")
    public static Object[][] getData(Method m) throws EncryptedDocumentException, IOException {
        // Nome del foglio Excel in base al nome del metodo
        String excelSheetName = m.getName();
        File f = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/DataPool_Rimborsi.Regimi_Speciali_servizi_Esenti.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);

        // Ottieni il foglio specifico
        Sheet sheetName = wb.getSheet(excelSheetName);

        // Calcola il numero totale di righe e colonne
        int totalRows = sheetName.getLastRowNum();
        Row rowCells = sheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();

        // Formatter per leggere i valori delle celle
        DataFormatter format = new DataFormatter();

        // Lista per salvare i dati delle righe non vuote
        List<String[]> filteredData = new ArrayList<>();

        // Scorri le righe a partire dalla seconda (i = 1 per saltare l'header)
        for (int i = 1; i <= totalRows; i++) {
            Row currentRow = sheetName.getRow(i);
            if (currentRow == null) continue; // Salta le righe completamente vuote

            boolean isRowEmpty = true;
            String[] rowData = new String[totalCols];

            for (int j = 0; j < totalCols; j++) {
                String cellValue = format.formatCellValue(currentRow.getCell(j));
                rowData[j] = cellValue;

                // Controlla se la riga contiene almeno un valore non vuoto
                if (cellValue != null && !cellValue.trim().isEmpty()) {
                    isRowEmpty = false;
                }
            }

            // Aggiungi solo le righe che non sono vuote
            if (!isRowEmpty) {
                filteredData.add(rowData);
            }
        }

        // Converte la lista in un array bidimensionale
        String[][] testData = new String[filteredData.size()][totalCols];
        return filteredData.toArray(testData);
    }
}
