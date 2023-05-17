package com.aci.utils;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ExcelUtils_CalcoloTariffa {
    @DataProvider(name = "ACIWorksheet")
    public static Object[][] getData(Method m) throws EncryptedDocumentException, IOException {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream("path/to/config.properties");
        prop.load(input);

        String excelSheetName = m.getName();
        String excelFilePath = prop.getProperty("excelFilePath");
        File f = new File(excelFilePath);
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);

        Sheet sheetName = wb.getSheet(excelSheetName);

        int totalRows = sheetName.getLastRowNum();
//		System.out.println(totalRows);
        Row rowCells = sheetName.getRow(0);
        int totalCols = rowCells.getLastCellNum();
//		System.out.println(totalCols);

        DataFormatter format = new DataFormatter();

        //si mette i=1 perchè saltiamo l'header, se l'header è la prima colonna allora bisogna mettere j=1
        String[][] testData = new String[totalRows][totalCols];
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
                //  System.out.println(testData[i-1][j]);
            }
        }

        return testData;
    }
}


