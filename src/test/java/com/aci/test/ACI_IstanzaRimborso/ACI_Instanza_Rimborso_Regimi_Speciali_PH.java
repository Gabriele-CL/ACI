package com.aci.test.ACI_IstanzaRimborso;

import com.aci.test.POM.CalcoloBolloPage;
import com.aci.test.POM.InstanzaRimborso;
import com.aci.test.POM.Login;
import com.aci.utils.ExcelUtils_InstanzaRimborsoPH;
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
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_Instanza_Rimborso_Regimi_Speciali_PH {
    public WebDriver driver;
    public int counterko;
    public int counter;
    public String[][] kogenerator;
    Login objLogin;
    InstanzaRimborso objRimborso;
    CalcoloBolloPage objCalBollo;

    public List<String> targaFinal = new ArrayList<>();
    public List<String> esit0 = new ArrayList<>();
    public List<String> tipologiaH = new ArrayList<>();


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
        String tipologiaHandicap = "Tipologia Handicap";

        targaFinal.add(targaFinale);
        esit0.add(esito);
        tipologiaH.add(tipologiaHandicap);


    }

    WebDriverWait wait;

    @Test(dataProviderClass = ExcelUtils_InstanzaRimborsoPH.class, dataProvider = "ACIWorksheet")
    public void Test1(String Targa, String RichiedentePassivo,
                      String CodiceFiscalePh, String CodiceFiscaleTutore,
                      String RecapTelefonico, String Email, String TipologiaHandicap, String Rivedibilità, String Sottoscrizione
    ) throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        objRimborso = new InstanzaRimborso(driver);
        objCalBollo = new CalcoloBolloPage(driver);

        /*// Lanciamo la query per pulire le istanze già create
        String url = "";
        String username = "nartpt01c_prj";
        String password = "dND85HLc";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Utilizzare un PreparedStatement per prevenire l'iniezione SQL e rendere la query parametrica
            String query1 = "UPDATE anagrafe.e_pagamento " +
                    "SET identificativo_pratica = NULL " +
                    "WHERE id_veicolo IN (SELECT et.id_veicolo FROM anagrafe.e_targa_veicolo et WHERE et.targa IN (?))";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query1)) {
                // Imposta il valore della variabile Targa come parametro nella query
                preparedStatement.setString(1, Targa);
                jdbc:postgresql://colpsge-vip:5444/nartpt01c
                // Esegui l'aggiornamento
                preparedStatement.executeUpdate();
                System.out.println("Targa" + " " + Targa + " " + "cancellata dal Database");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //*** ASSOCIAZIONE
        objRimborso.setNuovaPratica();
        objRimborso.setPH();
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

        } else if (!objRimborso.isButtonAvantiClickable()) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.get("https://nstar-web-lazio-tsi.cnstar.serviziaci.it/home/regimi-speciali/acquisizione-rs");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));
        } else {
            objRimborso.setAvanti();
            Thread.sleep(8000);
            counter++;

            // DATI RICHIEDENTE
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement richiedenteP;
            WebElement richiedentePH;
            WebElement soggettoTutore;

            wait.withTimeout(Duration.ofSeconds(5));

            if (RichiedentePassivo.contains("Si")) {
                js.executeScript("window.scrollTo(0, 0)");
                richiedenteP = driver.findElement(By.xpath("//*[@id=\"richiedenteSoggPassivo\"]"));
                richiedenteP.click();
                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                objRimborso.setEmail(Email);
            } else if (RichiedentePassivo.contains("PH")) {
                js.executeScript("window.scrollTo(0, 0)");
                richiedentePH = driver.findElement(By.xpath("//*[@id=\"richiedentePh\"]"));
                richiedentePH.click();
                objRimborso.SetCodFiscale(CodiceFiscalePh);
                objRimborso.setCompAutomatico();
                Thread.sleep(3000);
                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                objRimborso.setEmail(Email);
            } else if (RichiedentePassivo.contains("Tutore")) {
                js.executeScript("window.scrollTo(0, 0)");
                soggettoTutore = driver.findElement(By.xpath("//*[@id=\"richiedenteDt\"]"));
                soggettoTutore.click();
                objRimborso.SetCodFiscale(CodiceFiscaleTutore);
                objRimborso.setCompAutomatico();
                Thread.sleep(3000);
                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                objRimborso.setEmail(Email);
            }

            if (isElementDisplayed(By.xpath("//div[@class='alert alert-warning rounded-lg shadow']")) || objRimborso.toponimoVuoto()) {
                String str = "Il Datapool non contiene tutti i dati richiesti";
                counterko++;
                esit0.add(str);
                targaFinal.add(Targa);
                handleDataErrorCase(driver);
            } else {

                //objRimborso.setRegimiSpeciali();
                objRimborso.setAvanti();
                String TipoH = objRimborso.setTipologiaServizioPH(TipologiaHandicap);
                objRimborso.setDataRicoInvalidità();

                if (Rivedibilità.contains("Si")) {
                    System.out.println("Inseriamo la Rivedibilità");
                    objRimborso.setRivedibilità();
                } else {
                    System.out.println("Rivedibilità non inserita");
                }

                if (RichiedentePassivo.contains("Si")) {
                    objRimborso.SetCodFiscalePH(CodiceFiscalePh);
                    objRimborso.setCompAutomatico();
                    Thread.sleep(3000);
                    objRimborso.setRecapitoTelefonico(RecapTelefonico);
                    objRimborso.setEmail(Email);

                    if (objRimborso.toponimoVuoto()) {
                        System.out.println("Manca il toponimo");
                        String str = "Manca il toponimo";
                        counterko++;
                        esit0.add(str);
                        targaFinal.add(Targa);
                        handleDataErrorCase(driver);
                        return;
                    }

                } else if (RichiedentePassivo.contains("Tutore")) {

                    objRimborso.SetCodFiscalePH(CodiceFiscaleTutore);
                    objRimborso.setCompAutomatico();
                    Thread.sleep(3000);
                    objRimborso.setRecapitoTelefonico(RecapTelefonico);
                    objRimborso.setEmail(Email);

                    if (objRimborso.toponimoVuoto()) {
                        System.out.println("Manca il toponimo");
                        String str = "Manca il toponimo";
                        counterko++;
                        esit0.add(str);
                        targaFinal.add(Targa);
                        handleDataErrorCase(driver);
                        return;
                    }
                } else if (RichiedentePassivo.contains("PH")) {
                    System.out.println("Non va aggiunto nulla");
                }
                objRimborso.setAvanti();
                Thread.sleep(3000);
                List<WebElement> uploadButtons = driver.findElements(By.xpath("//input[@type='file']"));

// Array di percorsi dei file disponibili
                String[] filePaths = {
                        System.getProperty("user.dir") + "//src/test/resources/Instanza/1_1.pdf",
                        System.getProperty("user.dir") + "//src/test/resources/Instanza/2_2.pdf",
                        System.getProperty("user.dir") + "//src/test/resources/Instanza/3_3.pdf"
                };

// Istanza della classe Random
                Random random = new Random();

                for (int i = 0; i < uploadButtons.size(); i++) {
                    WebElement button = uploadButtons.get(i);

                    // Seleziona un file casualmente
                    int randomIndex = random.nextInt(filePaths.length);
                    File fileToUpload = new File(filePaths[randomIndex]);

                    // Carica il file selezionato
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
                String numeroPratica = objRimborso.numeroPraticaPH();
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
                tipologiaH.add(TipoH);

            }
        }
    }


    @AfterTest
    public void array() {
        System.out.println("Il conteggio è " + counterko);

            /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/

            this.kogenerator = new String[counterko + 1][40];

            /*Ko results*/

            for (int i = 0; i <= counterko; i++) {
                String ksd = esit0.get(i);
                kogenerator[i][0] = ksd;

            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = targaFinal.get(i);
                kogenerator[i][1] = ksd;

            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = tipologiaH.get(i);
                kogenerator[i][2] = ksd;
            }
        }

    @AfterSuite

    public void printexcel() throws IOException {

        // Formattazione per aggiungere Data e Ora al nome dei due file Excel

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        String formatedDateTime = current.format(format);

        @SuppressWarnings("resource")
        XSSFWorkbook workbookKO = new XSSFWorkbook();
        XSSFSheet sheetKO = workbookKO.createSheet("Istanza_Rimborso_PH");

        //using for loop
        int rowsko = kogenerator.length;
        System.out.println("le righe sono " + rowsko);
        int colsko = kogenerator[0].length;
        System.out.println("le colonne sono " + colsko);

        for (int r = 0; r < rowsko; r++) {
            XSSFRow row = sheetKO.createRow(r);
            for (int c = 0; c < colsko; c++) // 0 column is created
            {
                XSSFCell cellko = row.createCell(c);
                String valueko = kogenerator[r][c];

                if (valueko != null)
                    cellko.setCellValue(valueko);
            }
        }

        String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/Test_Istanza_Rimborso_RegimiS_PH_" + formatedDateTime + "." + "xlsx";
        FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
        workbookKO.write(outkoresults);
        outkoresults.close();
        System.out.println("KO_Test file written succesfully ...");
        driver.close();
    }

    private void handleDataErrorCase(WebDriver driver) {
        System.out.println("Il datapool non contiene tutti i dati che servono");
        driver.get("https://nstar-web-lazio-tsi.cnstar.serviziaci.it/home/regimi-speciali/acquisizione-rs");
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


