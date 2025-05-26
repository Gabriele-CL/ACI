package com.aci.test.ACI_IstanzaRimborso;

import com.aci.test.POM.CalcoloBolloPage;
import com.aci.test.POM.InstanzaRimborso;
import com.aci.test.POM.Login;
import com.aci.utils.ExcelUtils_InstanzaRimborso;
import com.aci.utils.ExcelUtils_InstanzaRimborsoRegimiSpeciali;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_Instanza_Rimborso_Regimi_Speciali_Serivizio_Esente {
    public WebDriver driver;
    public int counterko;
    public int counter;
    public String[][] kogenerator;
    Login objLogin;
    InstanzaRimborso objRimborso;
    CalcoloBolloPage objCalBollo;

    public List<String> targaFinal = new ArrayList<>();
    public List<String> esit0 = new ArrayList<>();
    public List<String> servizioEse = new ArrayList<>();


    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless", "--window-size=1920,1200");//senza browser
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(/*options*/);
        driver.manage().window().maximize();
        driver.get(getUrl());

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'ACCEDI')]")));
        wait.withTimeout(Duration.ofSeconds(8));

        //Login
        objLogin = new Login(driver);
        objRimborso = new InstanzaRimborso(driver);
        objLogin.enterRimborsiCredentials2();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header-bottom px-3']//a[@class='dropdown-toggle nav-link dropbtn'][normalize-space()='Istanze']")));

        objRimborso.setInstanzaOption();
        objRimborso.setAcquisizioneRimborsoRegimi();

        String targaFinale = "Targa";
        String esito = "Esito";
        String servizioEsente = "Servizio Esente";

        targaFinal.add(targaFinale);
        esit0.add(esito);
        servizioEse.add(servizioEsente);


    }

    WebDriverWait wait;

    @Test(dataProviderClass = ExcelUtils_InstanzaRimborsoRegimiSpeciali.class, dataProvider = "ACIWorksheet")
    public void Test1(String Targa, String RichiedentePassivo,
                      String CodiceFiscalePh, String CodiceFiscaleTutore,
                      String RecapTelefonico, String Email, String ServizioEsente, String Rivedibilità, String Sottoscrizione
    ) throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        objRimborso = new InstanzaRimborso(driver);
        objCalBollo = new CalcoloBolloPage(driver);

        //*** ASSOCIAZIONE

        objRimborso.setNuovaPratica();
        objRimborso.setServizioEsente();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"targa\"]"))); // Wait for the element to be visible

        objCalBollo.setNumeroTarga(Targa);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Cerca']"))); // Wait for the element to be visible
        objRimborso.setCerca();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By nuovaIstanzaButton = By.xpath("//button[normalize-space()='NUOVA ISTANZA']");
        By noElementFoundMessage = By.xpath("//p[contains(text(), 'Nessun elemento trovato')]");

        List<WebElement> noInstanza = driver.findElements(noElementFoundMessage);

        if (noInstanza.size() > 0) {
            String str = "Non esistono instanze disponibili per la targa: " + Targa;
            counterko++;
            esit0.add(str);
            targaFinal.add(Targa);

            System.out.println("Non esistono instanze disponibili per la targa: " + Targa);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.get("https://nstar-web-lazio-tsi.cnstar.serviziaci.it/home/regimi-speciali/acquisizione-rs");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));

            // Ricomincia il ciclo
            return;

        } else {
            objRimborso.setAvanti();

            counter++;


            WebElement richiedenteP =  driver.findElement(By.xpath("//*[@id=\"richiedenteSoggettoPassivo\"]"));
            if (!richiedenteP.isEnabled()){
                System.out.println("Il soggetto è una persona giuridica");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                driver.get("https://nstar-web-lazio-tsi.cnstar.serviziaci.it/home/regimi-speciali/acquisizione-rs");
                wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));

                // Ricomincia il ciclo
                return;

            } else if (RichiedentePassivo.contains("Si")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", richiedenteP);
                objRimborso.clickSoggettoP();
            } else if (RichiedentePassivo.contains("No")) {
                objRimborso.SetCodFiscale(CodiceFiscalePh);
                objRimborso.setCompAutomatico();
                Thread.sleep(3000);
            } else if (RichiedentePassivo.contains("Manuale")) {
                objRimborso.SetCodFiscale2();
                objRimborso.fillCognome();
                objRimborso.fillNome();
                objRimborso.setSesso();
                objRimborso.setDataNascita();
                objRimborso.setStatoNascita();
                objRimborso.fillProvinciaIndirizzo();
                objRimborso.fillComuneIndirizzo();
                objRimborso.fillToponimoIndirizzo();
                objRimborso.fillIndirizzo();
                objRimborso.setProvinciaComune();
                objRimborso.fillCapIndirizzo();
            }
        }

            if (isElementDisplayed(By.xpath("//div[@class='alert alert-warning rounded-lg shadow']")) || objRimborso.toponimoVuoto()) {
                String str = "Il Datapool non contiene tutti i dati richiesti";
                counterko++;
                esit0.add(str);
                targaFinal.add(Targa);
                handleDataErrorCase(driver);
                return;
            } else {
                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                objRimborso.setEmail(Email);
                objRimborso.setAvanti();
            }

        String TipoH = objRimborso.setTipologiaServizio(ServizioEsente);
        objRimborso.setDataDecoEsenzione();
        objRimborso.setAvanti();

        Thread.sleep(3000);

        List<WebElement> uploadButtons = driver.findElements(By.xpath("//input[@type='file']"));

        for (int i = 0; i < uploadButtons.size(); i++) {
            WebElement button = uploadButtons.get(i);

            File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/2_2.pdf");
            button.sendKeys(fileToUpload.getAbsolutePath());
        }

        Thread.sleep(3000);

        objRimborso.setAvanti();

        Thread.sleep(3000);

        //*** SOTTOSCRIZIONE
        objRimborso.setConfermaSottoscrivi();
        objRimborso.setCartaceo();
        Thread.sleep(3000);

        WebElement updateSottoscrizione = driver.findElement(By.xpath("//input[@type='file']"));
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/2_2.pdf");
        updateSottoscrizione.sendKeys(fileToUpload.getAbsolutePath());

        Thread.sleep(2000);
        objRimborso.setSottoscrizioneComp();

        Thread.sleep(3000);

        String numeroPratica = objRimborso.numeroPratica();

        objRimborso.setTornaLista();

        Thread.sleep(2000);
        //Clicchiamo la pratica che contiene il numero pratica
        WebElement pratica = driver.findElement(By.xpath("//td/a[contains(text(),'" + numeroPratica + "')]"));
        pratica.click();
        Thread.sleep(4000);

        objRimborso.setControllaEsito();
        Thread.sleep(4000);

        String str = "L'istanza di rimborso è stata completata";
        counterko++;
        esit0.add(str);
        targaFinal.add(Targa);
        servizioEse.add(TipoH);


        //objRimborso.setRegimiSpeciali();
    }

    @AfterTest
    public void array() {

        /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/


        System.out.println("Il conteggio è " + counter);
        this.kogenerator = new String[counterko + 1][20];

        /*Ko results*/

        // Assumendo che tutte le liste abbiano la stessa dimensione, usiamo la dimensione di targaFinal come riferimento.
        int totalEntries = targaFinal.size(); // Numero totale di targhe
        kogenerator = new String[totalEntries][3]; // Dimensione aggiornata per includere tutte le targhe

        for (int i = 0; i < totalEntries; i++) {
            // Popolamento del campo 0: esito
            if (i < esit0.size()) {
                kogenerator[i][0] = esit0.get(i);
            } else {
                kogenerator[i][0] = "N/A"; // Esito non disponibile
            }

            // Popolamento del campo 1: targa
            kogenerator[i][1] = targaFinal.get(i);

            // Popolamento del campo 2: tipologia
            if (i < servizioEse.size()) {
                kogenerator[i][2] = servizioEse.get(i);
            } else {
                kogenerator[i][2] = "N/A"; // Tipologia non disponibile
            }
        }
    }


    @AfterSuite

    public void printexcel() throws IOException {

        // Formattazione per aggiungere Data e Ora al nome dei due file Excel

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        String formatedDateTime = current.format(format);

        /* Creazione Ko results*/


        @SuppressWarnings("resource")
        XSSFWorkbook workbookKO = new XSSFWorkbook();
        XSSFSheet sheetKO = workbookKO.createSheet("KO_risultati");

        //using for loop
        int rowsko = kogenerator.length;
        System.out.println("le righe sono " + rowsko);
        int colsko = kogenerator[0].length;
        System.out.println("le colonne sono " + colsko);

        for (int r = 0; r < rowsko; r++) //0 row is created
        {
            XSSFRow row = sheetKO.createRow(r);
            for (int c = 0; c < colsko; c++) // 0 column is created
            {
                XSSFCell cellko = row.createCell(c);
                String valueko = kogenerator[r][c];

                if (valueko != null)
                    cellko.setCellValue(valueko);
            }
        }

        String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/Test_Istanza_Rimborso_RegimiS_ServiziEse_" + formatedDateTime + "." + "xlsx";
        FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
        workbookKO.write(outkoresults);

        outkoresults.close();

        System.out.println("KO_Test file written succesfully ...");

        //driver.close();

    }


    // Helper methods
    private void handleDataErrorCase(WebDriver driver) {
        System.out.println("Il datapool non contiene tutti i dati che servono");
        driver.navigate().to("https://nstar-web-lazio-tsi.cnstar.serviziaci.it/home/regimi-speciali/acquisizione-rs");
        wait.withTimeout(Duration.ofSeconds(20));

    }

    private void clickAvantiButton(WebDriver driver) {
        WebElement avantiButton = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
        avantiButton.click();
    }

    private boolean setAndVerifyData(InstanzaRimborso objRimborso, String RecapTelefonico, String Email, WebDriver
            driver) {
        objRimborso.setRecapitoTelefonico(RecapTelefonico);
        objRimborso.setEmail(Email);

        WebElement verifyClick = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick);

        return verifyClick.isEnabled();
    }

    private void handleUnClickableCase(WebDriver driver) {
        System.out.println("Il tasto non è cliccabile");
        driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
        wait.withTimeout(Duration.ofSeconds(20));
    }

    private void navigateToAcquisizionePratica() {
        driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
        wait.withTimeout(Duration.ofSeconds(20));
    }

    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // L'elemento non è presente
        }
    }
}


