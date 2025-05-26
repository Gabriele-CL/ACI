package com.aci.test.ACI;

import com.aci.test.POM.Login_Sinta;
import com.aci.test.POM.Sinta_CalcoloTariffePage;
import com.aci.POM.Sinta_PosizioneTributaria;
import com.aci.utils.ExcelUtils_PosizioneTributaria;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Aci_PosizioneTributaria_Test {

    public WebDriver driver;

    public WebElement element;
    public int counter;
    public int counterko;
    public String[][] excelgenerator;
    public String[][] kogenerator;
    Login_Sinta objLoginSinta;
    Sinta_CalcoloTariffePage objSintaPage;
    Sinta_PosizioneTributaria objPosizioneTributaria;

    //Dichiarazione Stringhe
    public List<String> targaVeicolo = new ArrayList<>();
    public List<String> regioneVeicolo = new ArrayList<>();
    public List<String> risultato = new ArrayList<>();


    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://10.64.3.139/tasse-portal/doLogin.do");

        String targa = "Targa";
        String regione = "Regione";
        String esitoTest = "Esito Test";


        targaVeicolo.add(targa);
        regioneVeicolo.add(regione);
        risultato.add(esitoTest);


    }

    @Test(dataProviderClass = ExcelUtils_PosizioneTributaria.class, dataProvider = "ACIWorksheet")

    //dati datapool da inserire nell'applicativo
    public void Test1(String tipoVeicolo, String Targa, String Regione, String Periodo) throws InterruptedException {


        targaVeicolo.add(Targa);
        regioneVeicolo.add(Regione);


        //Login
        objLoginSinta = new Login_Sinta(driver);
        objPosizioneTributaria = new Sinta_PosizioneTributaria(driver);
        objSintaPage = new Sinta_CalcoloTariffePage(driver);
        Thread.sleep(3000);
        objLoginSinta.enterCrendentials();
        Thread.sleep(1000);

        objSintaPage.setArchivioTribu();
        objPosizioneTributaria.setPosizioneTributaria();


        objPosizioneTributaria.setTarga(Targa);
        objPosizioneTributaria.setTipoVeicolo(tipoVeicolo);
        objPosizioneTributaria.setAnnoPartenza(Periodo);
        objPosizioneTributaria.setButtonTarga();

        List<WebElement> errorMessages = driver.findElements(By.xpath("//li[normalize-space()='Veicolo non trovato']"));

        if (!errorMessages.isEmpty()) {
            System.out.println("Il messaggio di errore è presente");
            driver.get("http://10.64.3.139/tasse-portal/doLogin.do");
            String str = "Fail";
            risultato.add(str);
            targaVeicolo.add(Targa);
            regioneVeicolo.add(Regione);
            counter++;
            return;

        } else {
            System.out.println("La tabella è presente, vai avanti");

        }
                // Recupera la tabella e memorizza le informazioni in una variabile
        WebElement table1 = driver.findElement(By.xpath("//table[@id='tabella-dati']"));

        List<WebElement> rows1 = table1.findElements(By.tagName("tr"));
        int numberOfRows = rows1.size();
        System.out.println("Number of rows: " + numberOfRows);

        String celleFinali1 = null;
        for (int i = 1; i < rows1.size(); i++) {
            WebElement row = rows1.get(i);
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                //System.out.println(cell.getText());
                celleFinali1 = cell.getText();
                System.out.println(celleFinali1);


            }
        }

        Thread.sleep(5000);

        //CONFRONTO CON SECONDO ACCOUNT

        driver.get("http://10.64.3.139/tasse-portal/doLogin.do");

        objLoginSinta.setUsername();
        objLoginSinta.setPassword();
        objLoginSinta.setLoginButton();
        objLoginSinta.setInvia();

        objSintaPage.setArchivioTribu();
        objPosizioneTributaria.setPosizioneTributaria();


        objPosizioneTributaria.setTarga(Targa);
        objPosizioneTributaria.setTipoVeicolo(tipoVeicolo);
        objPosizioneTributaria.setAnnoPartenza(Periodo);
        objPosizioneTributaria.setButtonTarga();
        objPosizioneTributaria.setElencoPeriodo();

        WebElement table2 = driver.findElement(By.xpath("//table[@id='tabella-dati']"));
        List<WebElement> rows2 = table2.findElements(By.tagName("tr"));
        int numberOfRows2 = rows2.size();

        System.out.println("Number of rows: " + numberOfRows2);

        String celleFinali2 = null;
        for (int i = 1; i < rows2.size(); i++) {
            WebElement row = rows2.get(i);
            List<WebElement> cells2 = row.findElements(By.tagName("td"));
            for (WebElement cell2 : cells2) {
                System.out.println(cell2.getText());

                celleFinali2 = cell2.getText();
            }
        }


        Thread.sleep(5000);

        //CONFRONTO CON TERZO ACCOUNT

        driver.get("http://10.64.3.139/tasse-portal/doLogin.do");

        objLoginSinta.setUsername2();
        objLoginSinta.setPassword2();
        objLoginSinta.setLoginButton();
        objLoginSinta.setInvia();

        objSintaPage.setArchivioTribu();
        objPosizioneTributaria.setPosizioneTributaria();


        objPosizioneTributaria.setTarga(Targa);
        objPosizioneTributaria.setTipoVeicolo(tipoVeicolo);
        objPosizioneTributaria.setAnnoPartenza(Periodo);
        objPosizioneTributaria.setButtonTarga();
        objPosizioneTributaria.setElencoPeriodo();

        WebElement table3 = driver.findElement(By.xpath("//table[@id='tabella-dati']"));
        List<WebElement> rows3 = table3.findElements(By.tagName("tr"));
        int numberOfRows3 = rows3.size();

        System.out.println("Number of rows: " + numberOfRows3);

        String celleFinali3 = null;
        for (int i = 1; i < rows3.size(); i++) {
            WebElement row = rows3.get(i);
            List<WebElement> cells3 = row.findElements(By.tagName("td"));
            for (WebElement cell3 : cells3) {
                System.out.println(cell3.getText());

                celleFinali3 = cell3.getText();
            }
        }

        if (rows2.size() != rows3.size() || rows1.size() != rows2.size()
                || !celleFinali2.equals(celleFinali3) || !celleFinali1.equals(celleFinali2)) {

            System.out.println("Le tabelle hanno un numero diverso di righe o il contenuto delle celle è diverso.");

            String str = "Fail";
            counterko++;
            risultato.add(str);
            targaVeicolo.add(Targa);
            regioneVeicolo.add(Regione);

        } else {
            System.out.println("Le tabelle hanno un numero uguale di righe e il contenuto delle celle è identico.");

            String str = "Pass";
            counterko++;
            risultato.add(str);
            targaVeicolo.add(Targa);
            regioneVeicolo.add(Regione);
        }
        counter++;

        Thread.sleep(5000);

        driver.get("http://10.64.3.139/tasse-portal/doLogin.do");

    }

    @AfterTest
    public void array () {

        /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/


        System.out.println("Il conteggio è" + "" + counter);
        this.kogenerator = new String[counter + 1][20];

        /*Ko results*/

        for (int i = 0; i <= counterko; i++) {
            String ksd = targaVeicolo.get(i);
            kogenerator[i][0] = ksd;

        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = regioneVeicolo.get(i);
            kogenerator[i][1] = ksd;

        }

        for(int i = 0; i <= counterko; i++){
            String ksd = risultato.get(i);
            kogenerator[i][2] = ksd;
        }
    }


    @AfterSuite

    public void printexcel () throws IOException {

        /*Creazione excel test svolti*/

        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Risultati_Test");

        //using for loop
        int rows = kogenerator.length;
        int cols = kogenerator[0].length;

        for (int r = 0; r < rows; r++) //0 row is created
        {
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) // 0 column is created
            {
                XSSFCell cell = row.createCell(c);
                String value = kogenerator[r][c];

                if (value != null) {
                    cell.setCellValue(value);
                }
            }
        }

        // Formattazione per aggiungere Data e Ora al nome dei due file Excel

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        String formatedDateTime = current.format(format);

        String filePath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/ACI_PosizioneTributaria_" + formatedDateTime + ".xlsx";
        FileOutputStream outstream = new FileOutputStream(filePath);
        workbook.write(outstream);


        outstream.close();

        System.out.println("ACI_Results.xlsx file written succesfully ...");

        driver.close();

    }
}


