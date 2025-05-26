package com.aci.test.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InstanzaRimborso {

    WebDriver driver;

    @FindBy(xpath = "//select[@id='tipoSoggettoDelegato']")
    WebElement soggettoDelegato;

    @FindBy(xpath = "//*[@id=\"rivedibilita\"]")
    WebElement rivedibilità;

    @FindBy(xpath = "//*[@id=\"dataRiconoscimentoInvalidita\"]")
    WebElement dataRicoInvalidità;

    @FindBy(xpath = "//button[contains(text(), 'Controlla Esito')]")
    WebElement controllaEsito;

    @FindBy(xpath = "//button[@role=\"option\"]")
    WebElement opzioneStato;

    @FindBy(xpath = "//*[@id=\"cognome\"]")
    WebElement cognome;

    @FindBy(xpath = "//*[@id=\"nome\"]")
    WebElement nome;

    @FindBy(xpath = "//*[@id=\"dataProtocollo\"]")
    WebElement dataProtocollo;

    @FindBy(xpath = "//*[@id=\"sesso\"]")
    WebElement sesso;

    @FindBy(xpath = "//*[@id=\"dataDiNascita\"]")
    WebElement dataNascita;

    @FindBy(xpath = "//*[@id=\"dataInizioValiditaRegimeSpeciale\"]")
    WebElement dataDecorrenzaEsenzione;
    @FindBy(xpath = "//input[@id='codiceFiscaleDelegato']")
    WebElement codiceFiscaleDelegato;

    @FindBy(xpath = "//*[@id=\"partitaIvaDelegato\"]")
    WebElement codiceFiscaleGiuridico;

    @FindBy(xpath = "//input[@id='cognomeDelegato']")
    WebElement cognomeDelegato;

    @FindBy(xpath = "//input[@id='nomeDelegato']")
    WebElement nomeDelegato;

    @FindBy(xpath = "//*[@id=\"nazioneDiNascita\"]")
    WebElement statoNascita;

    @FindBy(xpath = "//*[@id=\"provinciaDiNascita\"]")
    WebElement provinciaNascita;

    @FindBy(xpath = "//*[@id=\"comuneDiNascita\"]")
    WebElement comuneNascita;

    @FindBy(xpath = "//*[@id=\"provinciaIndirizzo\"]")
    WebElement provinciaIndirizzo;

    @FindBy(xpath = "//*[@id=\"comuneIndirizzo\"]")
    WebElement comuneIndirizzo;

    @FindBy(xpath = "//*[@id=\"capIndirizzo\"]")
    WebElement capIndirizzo;

    @FindBy(xpath = "//*[@id=\"toponimo\"]")
    WebElement toponimoIndirizzo;

    @FindBy(xpath = "(//input[@type=\"text\"])[12]")
    WebElement indirizzo;

    @FindBy(xpath = "//*[@id=\"richiedenteSoggettoPassivo\"]")
    WebElement richiedenteSP;
    @FindBy(xpath = "//div[@class='header-bottom px-3']//a[@class='dropdown-toggle nav-link dropbtn'][normalize-space()='Istanze']")
    WebElement instanzaOption;

    @FindBy(xpath = "(//button[@type=\"button\"])[4]")
    WebElement focus;

    @FindBy(xpath = "(//a[normalize-space()='Acquisizione'])[1]")
    WebElement acquisizioneRimborso;

    @FindBy(xpath = "(//a[normalize-space()='Acquisizione'])[2]")
    WebElement acquisizioneRimborsoRegimi;

    @FindBy(xpath = "//button[normalize-space()='NUOVA ISTANZA']")
    WebElement nuovaPratica;

    @FindBy(xpath = "//a[normalize-space()='Regimi Speciali - Acquisizione Regime Speciale']")
    WebElement regimiSpeciali;

    @FindBy(xpath = "//button[normalize-space()='SERVIZIO ESENTE']")
    WebElement servizioEsente;

    @FindBy(xpath = "//button[normalize-space()='PH']")
    WebElement ph;

    @FindBy(xpath = "//button[normalize-space()='Cerca']")
    WebElement cerca;

    @FindBy(xpath = "(//input[@id='currentPagamento'])")
    WebElement pagamentoRichiesto;

    @FindBy(xpath = "//button[normalize-space()='Avanti']")
    WebElement avanti;

    @FindBy(xpath = "(//input[@id='codiceFiscale'])[1]")
    WebElement setCodiceFiscale;

    @FindBy(xpath = "//*[@id=\"codiceFiscale-ph\"]")
    WebElement setCodiceFiscalePH;

    @FindBy(xpath = "//td[4]")
    WebElement getCodiceFiscale;

    @FindBy(xpath = "//span[@class='material-icons fs-4 m-0']")
    WebElement compAutomatico;

    @FindBy(xpath = "//*[@id=\"telefono\"]")
    WebElement recapitoTelefonico;

    @FindBy(xpath = "//*[@id=\"email\"]")
    WebElement email;

    @FindBy(xpath = "(//select[@id='modalitaRimborso'])[1]")
    WebElement modalitaRimborso;
    @FindBy(xpath = "//input[@id='iban']")
    WebElement iban;

    @FindBy(xpath = "//*[@id=\"intestazioneDelegato\"]")
    WebElement intestazioneDelegato;

    @FindBy(xpath = "//select[@id='motivazioneRimborso']")
    WebElement motivazioneRimborso;

    @FindBy(xpath = "//input[@id='importoRimborso']")
    WebElement importoRimborso;

    @FindBy(xpath = "//*[@id=\"numeroProtocollo\"]")
    WebElement protocolloRegionale;

    @FindBy(xpath = "//input[@id='tipoAllegato-26']")
    WebElement allegato1;

    @FindBy(xpath = "//input[@id='tipoAllegato-10']")
    WebElement allegato2;


    @FindBy(xpath = "//input[@id='tipoAllegato-2']")
    WebElement allegato3;

    @FindBy(xpath = "//input[@id='tipoAllegato-1']")
    WebElement allegato4;

    @FindBy(xpath = "//input[@id='tipoAllegato-27']")
    WebElement allegato5;

    @FindBy(xpath = "//input[@id='tipoAllegato-23']")
    WebElement allegato6;

    @FindBy(xpath = "//input[@id='tipoAllegato-22']")
    WebElement allegato7;

    @FindBy(xpath = "//input[@id='tipoAllegato-5']")
    WebElement allegato8;

    @FindBy(xpath = "//input[@id='tipoAllegato-9']")
    WebElement allegato9;

    @FindBy(xpath = "//input[@id='tipoAllegato-28']")
    WebElement allegato10;

    @FindBy(xpath = "//input[@id='tipoAllegato-24']")
    WebElement allegato11;

    @FindBy(xpath = "//input[@id='tipoAllegato-6']")
    WebElement allegato12;

    @FindBy(xpath = "//input[@id='tipoAllegato-29']")
    WebElement allegato13;


    @FindBy(xpath = "//button[normalize-space()='Salva ed Esci']")
    WebElement salvaEsci;

    @FindBy(xpath = "//button[@id='sottoscrizioneBtn']")
    WebElement confermaSottoscrivi;

    @FindBy(xpath = "//button[normalize-space()='Digitalmente']")
    WebElement digitalmente;

    @FindBy(xpath = "//button[normalize-space()='Firmando il riepilogo cartaceo']")
    WebElement cartaceo;

    @FindBy(xpath = "//button[contains(text(), 'Sottoscrizione completata')]")
    WebElement sottoscrizioneComp;

    @FindBy(xpath = "//select[@id='annoValidita']")
    WebElement Validità;

    @FindBy(xpath = "//select[@id='codiceTipoVeicolo']")
    WebElement codiceVeicolo;

    @FindBy(xpath = "//span[normalize-space()='Dati Richiedente']")
    WebElement datiRichiedente;

    @FindBy(xpath = "(//td)[8]")
    WebElement importoEuro;

    @FindBy(xpath = "//input[@id='toponimoIndirizzo']")
    WebElement toponimo;

    @FindBy(xpath = "//input[@id='riepilogo']")
    WebElement riepilogo;

    @FindBy(xpath = "//button[normalize-space()='Sottoscrizione completata']")
    WebElement sottoscrizione;

    @FindBy(xpath = "//a[normalize-space()='Torna alla lista delle pratiche']")
    WebElement tornaLista;

    @FindBy(xpath = "//span[normalize-space()='info_outline']")
    WebElement infoOutline;

    @FindBy(xpath = "//span[@class='sr-only']")
    WebElement loadinSpin;

    @FindBy(xpath = "(//button[@type=\"button\"])[4]")
    WebElement indietro;

    @FindBy(xpath = "//button[normalize-space()='Annulla']")
    WebElement annulla;

    public InstanzaRimborso(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSoggettoP() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(500);
        this.richiedenteSP.click();
    }

    public void fillNome() {
        this.nome.sendKeys("Test");
    }

    public void fillCognome() {
        this.cognome.sendKeys("Test");
    }

    public void setSesso() throws InterruptedException {
        Select objSelect = new Select(this.sesso);
        objSelect.selectByValue("M");
        Thread.sleep(4000);
    }

    public String setTipologiaServizio(String TipologiaHandicap) throws InterruptedException {
        // Trova e clicca sull'opzione specifica direttamente tramite XPath
        WebElement opzione = driver.findElement(By.xpath("//select[@id='tipoRs']/option[" + TipologiaHandicap + "]"));
        // Clicca sull'opzione
        opzione.click();
        String TH = opzione.getText();
        Thread.sleep(1000);
        return TH;
    }

    public String setTipologiaServizioPH(String TipologiaHandicap) throws InterruptedException {
        // Trova e clicca sull'opzione specifica direttamente tramite XPath
        WebElement opzione = driver.findElement(By.xpath("//select[@id=\"tipologiaHandicap\"]/option[" + TipologiaHandicap + "]"));
        // Clicca sull'opzione
        opzione.click();
        String PH = opzione.getText();
        System.out.println(PH);
        Thread.sleep(1000);
        return PH;
    }

    public void setDataRicoInvalidità() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dataRiconoInva = wait.until(ExpectedConditions.elementToBeClickable(dataRicoInvalidità));

        // Ottieni la data corrente e formatta in dd/MM/yyyy
        String dataCorrente = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Enter the complete date
        dataRiconoInva.sendKeys(dataCorrente);


        Thread.sleep(5000);
    }
    ;
    public void setDataNascita() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dataNascitaElement = wait.until(ExpectedConditions.elementToBeClickable(dataNascita));

        // Enter the complete date
        dataNascitaElement.sendKeys("01/01/1990");

        Thread.sleep(5000);

        // Move to the next field if needed
        dataNascitaElement.sendKeys(Keys.TAB);
    }

    public void setDataDecoEsenzione() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dataNascitaElement = wait.until(ExpectedConditions.elementToBeClickable(dataDecorrenzaEsenzione));

        // Enter the complete date
        dataNascitaElement.sendKeys("01/02/2025");

        Thread.sleep(2000);
    }



    public void setStatoNascita() throws InterruptedException {
        String testoStato = "ITALIA";

        for (int i = 0; i < testoStato.length(); i++) {
            statoNascita.sendKeys(String.valueOf(testoStato.charAt(i)));
            // Aggiungi una piccola pausa tra l'inserimento di ogni carattere
            Thread.sleep(100);
        }

        Thread.sleep(3000);

        // Dopo aver inserito tutti i caratteri, puoi fare clic sull'opzione dello stato
        opzioneStato.click();
    }


    public void setProvinciaComune() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement comuneNascita = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='luogo']")));
        comuneNascita.sendKeys("Roma");
        Thread.sleep(2000);
        //WebElement primaScelta = driver.findElement(By.xpath("//*[@id=\"ngb-typeahead-5-0\"]"));
        //primaScelta.click();
        comuneNascita.sendKeys(Keys.ENTER);
    }


    public void fillDelegato() {
        this.intestazioneDelegato.sendKeys("Test");

    }

    public void fillProvinciaIndirizzo() throws InterruptedException {
        WebElement provinciaIndirizzo1 = driver.findElement(By.xpath("//input[@id=\"provinciaDiNascita\"]"));
        provinciaIndirizzo1.click();
        provinciaIndirizzo1.sendKeys("Roma");
        WebElement accettaDato = driver.findElement(By.xpath("//span[@class='ngb-highlight']"));
        accettaDato.click();
    }

    public void fillComuneIndirizzo() throws InterruptedException {
        WebElement comuneIndirizzo1 = driver.findElement(By.xpath("//input[@id='luogoDiNascita']"));
        comuneIndirizzo1.click();
        comuneIndirizzo1.sendKeys("Roma");
        WebElement accettaDato = driver.findElement(By.xpath("//span[@class='ngb-highlight']"));
        accettaDato.click();
    }

    public void fillCapIndirizzo() throws InterruptedException {
        WebElement capIndirizzo1 = driver.findElement(By.xpath("//input[@id='cap']"));
        capIndirizzo1.click();
        capIndirizzo1.sendKeys("00171");
        WebElement accettaDato = driver.findElement(By.xpath("//span[@class='ngb-highlight']"));
        accettaDato.click();
    }


    public void setSottoscrizioneComp() throws InterruptedException {
        sottoscrizioneComp.click();
        Thread.sleep(3000);
    }

    public String numeroPratica(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement numeroP = driver.findElement(By.xpath("//body//app-root//app-sottoscrizione-rs-esito//h3[1]"));
        WebElement pratica = wait.until(ExpectedConditions.visibilityOf(numeroP));
        String numeroPratica = pratica.getText();

        System.out.println(numeroPratica);

        String numeroP1 = numeroPratica.replace("Pratica", "").replace(" - SERVIZIO ESENTE", "");
        System.out.println(numeroP1);
        return numeroP1;
    }

    public String numeroPraticaPH(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement numeroP = driver.findElement(By.xpath("//body//app-root//app-sottoscrizione-rs-esito//h3[1]"));
        WebElement pratica = wait.until(ExpectedConditions.visibilityOf(numeroP));
        String numeroPratica = pratica.getText();

        System.out.println(numeroPratica);

        String numeroP1 = numeroPratica.replace("Pratica", "").replace(" - PH", "");
        System.out.println(numeroP1);
        return numeroP1;
    }
    public void fillToponimoIndirizzo() throws InterruptedException {
        WebElement toponimoIndirizzo1 = driver.findElement(By.xpath("//input[@id='toponimo']"));
        toponimoIndirizzo1.click();
        toponimoIndirizzo1.sendKeys("Via");
        WebElement accettaDato = driver.findElement(By.xpath("//span[@class='ngb-highlight']"));
        accettaDato.click();
        toponimoIndirizzo1.sendKeys(Keys.TAB);
    }

    public void fillIndirizzo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement indirizzoElement = wait.until(ExpectedConditions.elementToBeClickable(indirizzo));

        //indirizzoElement.click();
        indirizzoElement.sendKeys("test test");
    }


    public void setAnnulla() {
        Actions actions = new Actions(driver);
        actions.moveToElement(annulla).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(annulla));
        this.annulla.click();

    }

    public void fillCodiceFiscale3(String codiceFiscale) {
        this.codiceFiscaleDelegato.sendKeys(codiceFiscale);
    }

    public void fillCodiceFiscale4(String codiceFiscale) {
        this.codiceFiscaleGiuridico.sendKeys(codiceFiscale);
    }

    public void fillCognome2() {
        this.cognomeDelegato.sendKeys("Test");
    }

    public void fillNome2() {
        this.nomeDelegato.sendKeys("Test");
    }

    public void setIndietro() {
        Actions actions = new Actions(driver);
        actions.moveToElement(indietro).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(indietro));
        this.indietro.click();
    }

    public void setRivedibilità(){
        this.rivedibilità.click();
    }

    public void setRiepilogo() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        riepilogo.sendKeys(fileToUpload.getAbsolutePath());
    }

    public void setInstanzaOption() {
        this.instanzaOption.click();
    }

    public boolean setRegimiSpeciali() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(regimiSpeciali)).click();
        Thread.sleep(1000);
        return false;

    }

    public void setSottoscrizione() {
        this.sottoscrizioneComp.click();
    }

    public void setControllaEsito(){
        this.controllaEsito.click();

    }

    public void setTornaLista() throws InterruptedException {
        this.tornaLista.click();
        Thread.sleep(4000);
    }

    public void setAcquisizioneRimborso() {
        this.acquisizioneRimborso.click();
    }

    public void setAcquisizioneRimborsoRegimi() {
        this.acquisizioneRimborsoRegimi.click();
    }

    public void setNuovaPratica() {
        Actions actions = new Actions(driver);
        actions.moveToElement(nuovaPratica).perform();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-wrapper")));
        this.nuovaPratica.click();

    }


    public void setCerca() throws InterruptedException {
        this.cerca.click();

    }

    public void setServizioEsente() throws InterruptedException {
        this.servizioEsente.click();

    }

    public void setPH() throws InterruptedException {
        this.ph.click();

    }

    public void setPagamentRichiesto(String numeroRicevuta) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(pagamentoRichiesto));


        String number = numeroRicevuta;
        String specifiNumber = number.replace("'", "").trim();

        //Click su Radio Button
        WebElement radioButton = driver.findElement(By.xpath("//td[contains(text(),'" + specifiNumber + "')]/preceding::input[@name=\"currentPagamento\"][1]"));
        radioButton.click();
        System.out.println("Click sull'instanza da poter iniziare");
    }

    public String setImportoEuro() {
        String total = this.importoEuro.getText();
        return total;
    }


    public boolean setAvanti() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", avanti);
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(avanti)).click();
        Thread.sleep(1000);
        return false;
    }

    public boolean isButtonAvantiClickable() {
        try {
            WebElement avantiButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Avanti']")));
            return avantiButton.isDisplayed() && avantiButton.isEnabled();
        } catch (Exception e) {
            System.out.println("Codice Fiscale già inserito" + e.getMessage());
            return false;
        }
    }

    public void SetCodFiscale(String codFiscale) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", setCodiceFiscale);
        Thread.sleep(15000);
        this.setCodiceFiscale.sendKeys(codFiscale);
    }

    public void SetCodFiscalePH(String codFiscale) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", setCodiceFiscalePH);
        Thread.sleep(15000);
        this.setCodiceFiscalePH.sendKeys(codFiscale);
    }
    public void SetCodFiscale2() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", setCodiceFiscale);
        Thread.sleep(15000);
        this.setCodiceFiscale.sendKeys("TSTTST90A01H501I");
    }

    public void setCompAutomatico() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datiRichiedente);
        Thread.sleep(500);
        this.compAutomatico.click();


    }

    public void setCodiceVeicolo(String tipoVeicolo) throws InterruptedException {

        Select objSelect = new Select(this.codiceVeicolo);
        objSelect.selectByValue(tipoVeicolo);
        Thread.sleep(500);
    }

    public void setSoggettodelegato(String soggettodelegato) throws InterruptedException {
        Select objSelect = new Select(this.soggettoDelegato);
        objSelect.selectByValue(soggettodelegato);
        Thread.sleep(500);
    }

    public void setRecapitoTelefonico(String recapTelefonico) {
        this.recapitoTelefonico.sendKeys(recapTelefonico);
    }

    public void setEmail(String email) {
        this.email.clear();
        this.email.sendKeys(email);
    }

    public boolean toponimoVuoto(){
        String toponimo = toponimoIndirizzo.getAttribute("value");
        return toponimo == null || toponimo.isEmpty();
    }

    public void setModalitaRimborso(String modalitaRimborso) throws InterruptedException {

        Select objSelect = new Select(this.modalitaRimborso);
        objSelect.selectByValue(modalitaRimborso);
        Thread.sleep(500);
    }

    public void setIban(String iban) {
        this.iban.sendKeys(iban);
    }

    public void setMotivazioneRimborso(String motivazioneRimborso) throws InterruptedException {

        Select objSelect = new Select(this.motivazioneRimborso);
        objSelect.selectByValue(motivazioneRimborso);
        Thread.sleep(500);
    }

    public void setImportoRimborso(String importoRimborsabile) {
        this.importoRimborso.sendKeys(importoRimborsabile);
    }

    public void setProtocolloRegionale(String protocolloRegionale) {
        this.protocolloRegionale.sendKeys(protocolloRegionale);
        this.dataProtocollo.sendKeys("01/01/2022");

    }

    public void setAllegato1() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato1.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato2() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato2.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato3() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato3.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato4() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato4.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato5() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato5.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato6() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato6.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato7() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato7.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato8() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato8.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato9() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato9.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato10() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato10.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato11() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato11.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato12() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato12.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato13() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato13.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setSalvaEsci() {
        this.salvaEsci.click();
    }

    public void setConfermaSottoscrivi() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confermaSottoscrivi);
        Thread.sleep(1000);
        this.confermaSottoscrivi.click();
    }

    public void setDigitalmente() {
        this.digitalmente.click();
    }

    public void setCartaceo() {
        this.cartaceo.click();
    }

    public void setAnnoValidità(String annoValidita) throws InterruptedException {

        Select objSelect = new Select(Validità);
        objSelect.selectByValue(annoValidita);
        Thread.sleep(500);
    }

}
