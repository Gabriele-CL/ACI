package com.aci.test.ACI_IstanzaRimborso;

import com.aci.test.POM.CalcoloBolloPage;
import com.aci.test.POM.InstanzaRimborso;
import com.aci.test.POM.Login;
import com.aci.utils.ExcelUtils_InstanzaRimborso;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.sound.midi.SysexMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_Instanza_Rimborso_Aggiornata_Test {
    public WebDriver driver;
    public int counterko;
    public int counter;
    public String[][] kogenerator;
    Login objLogin;
    InstanzaRimborso objRimborso;
    CalcoloBolloPage objCalBollo;

    public List<String> targaFinal = new ArrayList<>();
    public List<String> esit0 = new ArrayList<>();
    public List<String> codRicevuta = new ArrayList<>();
    //public List<String> codiceCasistica = new ArrayList<>();

    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless", "--window-size=1920,1200");//senza browser
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getUrl());

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'ACCEDI')]")));
        wait.withTimeout(Duration.ofSeconds(8));

        //Login
        objLogin = new Login(driver);
        objRimborso = new InstanzaRimborso(driver);
        objLogin.enterCrendentials();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header-bottom px-3']//a[@class='dropdown-toggle nav-link dropbtn'][normalize-space()='Istanze']")));

        objRimborso.setInstanzaOption();
        objRimborso.setAcquisizioneRimborso();

        String targaFinale = "Targa";
        String esito = "Esito";
        String codiceRicevuta = "Codice Ricevuta";

        targaFinal.add(targaFinale);
        esit0.add(esito);
        codRicevuta.add(codiceRicevuta);


    }

    WebDriverWait wait;

    @Test(dataProviderClass = ExcelUtils_InstanzaRimborso.class, dataProvider = "ACIWorksheet")
    public void Test1(String Targa, String TipoVeicolo, String AnnoValidita, String RichiedentePassivo,
                      String CodiceFiscale, String NumeroRicevuta,
                      String RecapTelefonico, String Email, String ModalitaRimborso, String SoggettoDelegato, String Iban,
                      String MotivazioneRimborso, String ImportoRimborsabile, String ProtocolloRegionale, String Completamento) throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        objRimborso = new InstanzaRimborso(driver);
        objCalBollo = new CalcoloBolloPage(driver);

        // Lanciamo la query per pulire le istanze già create
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
        }


        //*** ASSOCIAZIONE

        objRimborso.setNuovaPratica();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"targa\"]"))); // Wait for the element to be visible

        objCalBollo.setNumeroTarga(Targa);
        objRimborso.setCodiceVeicolo(TipoVeicolo);
        objRimborso.setAnnoValidità(AnnoValidita);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Cerca']"))); // Wait for the element to be visible
        objRimborso.setCerca();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By nuovaIstanzaButton = By.xpath("//button[normalize-space()='NUOVA ISTANZA']");
        By noElementFoundMessage = By.xpath("//p[contains(text(), 'Nessun elemento trovato')]");
        By infoIcon = By.xpath("//span[normalize-space()='info_outline']");
        By currentPagamentoInput = By.xpath("(//input[@id='currentPagamento'])");
        boolean hasClickedAvanti = false;

        List<WebElement> noInstanza = driver.findElements(noElementFoundMessage);

        if (noInstanza.size() > 0) {
            String str = "Non esistono instanze disponibili per la targa: " + Targa;
            counterko++;
            esit0.add(str);
            targaFinal.add(Targa);
            codRicevuta.add(NumeroRicevuta);

            System.out.println("Non esistono instanze disponibili per la targa: " + Targa);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(currentPagamentoInput));
            WebElement pagamento = driver.findElement(currentPagamentoInput);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pagamento);

            List<WebElement> infoInstanzaFatta = driver.findElements(infoIcon);
            if (infoInstanzaFatta.size() > 0) {
                String str = "L'instanza è stata già fatta";
                counterko++;
                esit0.add(str);
                targaFinal.add(Targa);
                codRicevuta.add(NumeroRicevuta);
                System.out.println("L'instanza è stata già fatta");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));
            } else {
                objRimborso.setPagamentRichiesto(NumeroRicevuta);
                objRimborso.setAvanti();
            }
            counter++;
        }

        //*** DATI RICHIEDENTE
        WebElement richiedenteP;
        List<WebElement> errorElements;
        String xpathError = "//h5[normalize-space()='Mancanza dati anagrafici']";

        if (RichiedentePassivo.contains("Si")) {
            richiedenteP = driver.findElement(By.xpath("//*[@id=\"richiedenteSoggettoPassivo\"]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", richiedenteP);
            objRimborso.clickSoggettoP();
        } else if (RichiedentePassivo.contains("No")) {
            objRimborso.SetCodFiscale(CodiceFiscale);
            objRimborso.setCompAutomatico();
        } else if (RichiedentePassivo.contains("Manuale")) {
            objRimborso.SetCodFiscale2();
            objRimborso.fillCognome();
            objRimborso.fillNome();
            objRimborso.setSesso();
            objRimborso.setDataNascita();
            objRimborso.setDataNascita();
            objRimborso.setStatoNascita();
            objRimborso.fillProvinciaIndirizzo();
            objRimborso.fillComuneIndirizzo();
            objRimborso.fillToponimoIndirizzo();
            objRimborso.fillIndirizzo();
            objRimborso.setProvinciaComune();
            objRimborso.fillCapIndirizzo();
        }

        wait.withTimeout(Duration.ofSeconds(5));
        errorElements = driver.findElements(By.xpath(xpathError));

        if (errorElements.size() > 0) {
            String str = "Il Datapool non contiene tutti i dati richiesti";
            counterko++;
            esit0.add(str);
            targaFinal.add(Targa);
            codRicevuta.add(NumeroRicevuta);
            handleDataErrorCase(driver);
        } else {
            if ((RichiedentePassivo.contains("Si") || RichiedentePassivo.contains("No") || RichiedentePassivo.contains("Manuale")) && setAndVerifyData(objRimborso, RecapTelefonico, Email, driver)) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
                clickAvantiButton(driver);
                objRimborso.setModalitaRimborso(ModalitaRimborso);
            } else if (!hasClickedAvanti && RichiedentePassivo.contains("Manuale")) {
                WebElement verifyClick3 = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick3);

                if (!verifyClick3.isEnabled()) {
                    handleUnClickableCase(driver);
                } else {

                    System.out.println("Non fare nulla");
                    clickAvantiButton(driver);
                    wait.withTimeout(Duration.ofSeconds(5));

                    //*** DATI RICHIESTA RIMBORSO
                    objRimborso.setModalitaRimborso(ModalitaRimborso);
                }
            }

            objRimborso.setMotivazioneRimborso(MotivazioneRimborso);
            objRimborso.setImportoRimborso(ImportoRimborsabile);
            objRimborso.setProtocolloRegionale(ProtocolloRegionale);

            if (ModalitaRimborso.contains("AR")) {
                objRimborso.setIban(Iban);

            } else if (ModalitaRimborso.contains("AD")) {
                objRimborso.setSoggettodelegato(SoggettoDelegato);

                if (SoggettoDelegato.contains("F")) {
                    objRimborso.fillCodiceFiscale3(CodiceFiscale);
                    objRimborso.fillCognome2();
                    objRimborso.fillNome2();
                    objRimborso.setIban(Iban);
                } else if (SoggettoDelegato.contains("G")) {
                    objRimborso.fillCodiceFiscale4(CodiceFiscale);
                    objRimborso.fillDelegato();
                    objRimborso.setIban(Iban);
                }

            } else if (ModalitaRimborso.contains("CU")) {
                System.out.println("Niente da aggiungere");
            }

            WebElement avanti = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", avanti);

            if (!avanti.isEnabled()) {
                String str = "Dati richiesta rimborso non completa";
                counterko++;
                esit0.add(str);
                targaFinal.add(Targa);
                codRicevuta.add(NumeroRicevuta);
                System.out.println("Dati richiesta rimborso non completa");
                driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='NUOVA ISTANZA']")));
            } else {

                //*** DOCUMENTI
                objRimborso.setAvanti();
                wait.withTimeout(Duration.ofSeconds(20));

                if (MotivazioneRimborso.contains("1")) {

                    objRimborso.setAllegato1();
                    objRimborso.setAllegato2();
                    objRimborso.setAllegato3();
                    objRimborso.setAllegato4();
                } else if (MotivazioneRimborso.contains("2")) {
                    objRimborso.setAllegato5();
                    objRimborso.setAllegato6();
                    objRimborso.setAllegato7();
                } else if (MotivazioneRimborso.contains("3")) {
                    objRimborso.setAllegato8();
                    objRimborso.setAllegato9();
                } else if (MotivazioneRimborso.contains("4")) {
                    objRimborso.setAllegato10();
                    objRimborso.setAllegato11();
                    objRimborso.setAllegato12();
                } else if (MotivazioneRimborso.contains("5")) {
                    objRimborso.setAllegato13();
                }

                //*** RIEPILOGO
                objRimborso.setAvanti();
                objRimborso.setConfermaSottoscrivi();

                if (Completamento.contains("1")) {
                    String str = "L'istanza di rimborso è stata completata";
                    counterko++;
                    esit0.add(str);
                    targaFinal.add(Targa);
                    codRicevuta.add(NumeroRicevuta);
                    objRimborso.setDigitalmente();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    //*** SOTTOSCRIZIONE
                    objRimborso.setTornaLista();
                }
            }
        }
    }

    @AfterTest
    public void array() {

        /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/


        System.out.println("Il conteggio è " + counter);
        this.kogenerator = new String[counterko + 1][20];

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
            String ksd = codRicevuta.get(i);
            kogenerator[i][2] = ksd;
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
        //String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/Test_CalcolTariffe_Sinta_Bolzano_" + formatedDateTime + "." + "xlsx";
        String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/Test_Istanza_Rimborso_Aggiornata_ProvaNovembre_" + formatedDateTime + "." + "xlsx";
        FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
        workbookKO.write(outkoresults);

        outkoresults.close();

        System.out.println("KO_Test file written succesfully ...");

        driver.close();

    }


    // Helper methods
    private void handleDataErrorCase(WebDriver driver) {
        System.out.println("Il datapool non contiene tutti i dati che servono");
        driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
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
}


