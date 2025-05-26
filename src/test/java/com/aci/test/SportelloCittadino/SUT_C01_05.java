package com.aci.test.SportelloCittadino;

import com.aci.test.POM.Login_Spid;
import com.aci.test.POM.SportelloCittadino;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class SUT_C01_05 {
    public ChromeDriver driver;
    public WebElement element;
    Login_Spid Spid;
    SportelloCittadino Sportello;

    @BeforeTest
    public void beforetest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("http://aci40fe-mdx-aci40-tst.apps.osv3.aci.it/");

        Spid = new Login_Spid(driver);
        Sportello = new SportelloCittadino(driver);

        Thread.sleep(3000);
        Sportello.clickContinuaSulSito();
        Spid.setAccedi();

        Thread.sleep(4000);

        List<WebElement> avviso = driver.findElements(By.xpath("//h5[@id=\"terminiModalLabel\"]"));

        if (!avviso.isEmpty()) {
            System.out.println("Il popup è uscito fuori va chiuso");

            WebElement avanti = driver.findElement(By.xpath("//button[contains(text(), 'Avanti')]"));
            avanti.click();
        }

        Spid.setEntraConSpid();
        Thread.sleep(3000);
        Spid.setScegliSpid();
        Thread.sleep(3000);
        Spid.setDemoSpid();
        Thread.sleep(8000);
        Spid.setUsername();
        Spid.setPassword();
        Spid.setEntraConSpid();
        Spid.setConferma();
    }

    @Test
    public void ACI_Sportello_UT() throws InterruptedException {

        // Compilazione Richiesta
        Thread.sleep(5000);
        Sportello.clickOpzionePRA();
        Thread.sleep(2000);
        Sportello.clickOpzioneAnnotazioni();
        Thread.sleep(2000);
        Sportello.clickPerditaRientro();
        Thread.sleep(2000);
        Sportello.clickPerditaPossesso();
        Thread.sleep(2000);
        Sportello.clickFurto();
        Thread.sleep(6000);

        // Compilazione dati
        Sportello.tipologiaVeicolo();
        Thread.sleep(2000);
        Sportello.setTarga();
        Thread.sleep(2000);
        Sportello.setCodiceFiscale();
        Sportello.setNumero();
        Sportello.setVerificaCell();
        Sportello.setEmail();
        Sportello.setVerificaEmail();
        Sportello.clickSuccessivo();

        // Compilazione Documenti
        Thread.sleep(4000);
        Sportello.clickCaricaDocumenti();
        Thread.sleep(2000);
        Sportello.inserisciDocumenti();
        Thread.sleep(5000);
        Sportello.clickClose();
        Thread.sleep(5000);
        Sportello.clickSuccessivo();
        Thread.sleep(2000);
        Sportello.clickPV();
        Thread.sleep(2000);
        Sportello.setMacroServizio();
        Thread.sleep(3000);

        //Clicchiamo due volte per ordinare la lista
        Sportello.clickCreata();
        Thread.sleep(3000);
        Sportello.clickCreata();

        //Verificare che sia Online e Salvata
        List<WebElement> righe = driver.findElements(By.xpath("//div[@role=\"gridcell\"][7]"));

        for (WebElement riga : righe) {
            try {
                String modalità = "Online";
                String stato = "Bozza";
                WebElement tipoPratica = riga.findElement(By.xpath("//div[@role=\"gridcell\"][5]//span[contains(text(), '" + modalità + "')]"));
                WebElement statoPratica = riga.findElement(By.xpath("//div[@role=\"gridcell\"][6]//span[contains(text(), '" + stato + "')]"));

                String pratica = tipoPratica.getText();
                String statoFinale = statoPratica.getText();
                if (pratica.equals(modalità) && statoFinale.equals(stato)) {

                    System.out.println("Appuntamento online e in bozza");
                    break;
                }

            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }

    @AfterMethod
    public void aggiornaEsitoTest(ITestResult result) throws IOException {

        String nomeClasse = "SUT_C01_05";
        String esito = result.isSuccess() ? "OK" : "KO";
        String codAnomalia = result.isSuccess() ? "Nessuna Anomalia" : "Anomalia Riscontrata"; // Puoi impostare un codice specifico in base al tipo di errore
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        String path = "src/test/resources/SportelloCittadino/Sportello UT - Test Automatici.xlsx"; // path file esistente
        FileInputStream fileInput = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInput);
        XSSFSheet sheet = workbook.getSheetAt(0);

        boolean classeTrovata = false;

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // salta intestazioni (i = 1)
            XSSFRow row = sheet.getRow(i);
            if (row == null) continue;

            XSSFCell cell = row.getCell(0); // colonna 0 = nome classe
            if (cell != null && nomeClasse.equals(cell.getStringCellValue().trim())) {
                // Supponiamo: colonna 1 = esito, 2 = cod_anomalia, 3 = data
                row.createCell(5).setCellValue(esito);         // esito
                row.createCell(6).setCellValue(codAnomalia);   // cod_anomalia
                row.createCell(7).setCellValue(data);          // data
                classeTrovata = true;
                break;
            }
        }

        fileInput.close();

        if (classeTrovata) {
            FileOutputStream fileOutput = new FileOutputStream(path);
            workbook.write(fileOutput);
            fileOutput.close();
            System.out.println("✅ File aggiornato con successo per la classe: " + nomeClasse);
        } else {
            System.out.println("⚠️ Classe non trovata nel file: " + nomeClasse);
        }

        workbook.close();

        driver.close();
    }

}

