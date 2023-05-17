package com.aci.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class InstanzaRimborso {

    WebDriver driver;
    @FindBy(xpath = "//div[@class='header-bottom px-3']//a[@class='dropdown-toggle nav-link dropbtn'][normalize-space()='Istanze']")
    WebElement instanzaOption;

    @FindBy(xpath = "(//button[@type=\"button\"])[4]")
    WebElement focus;

    @FindBy(xpath = "(//a[normalize-space()='Acquisizione'])[1]")
    WebElement acquisizioneRimborso;

    @FindBy(xpath = "//button[normalize-space()='NUOVA ISTANZA']")
    WebElement nuovaPratica;

    @FindBy(xpath = "//button[normalize-space()='Cerca']")
    WebElement cerca;

    @FindBy(xpath = "(//input[@id='currentPagamento'])")
    WebElement pagamentoRichiesto;

    @FindBy(xpath = "//button[normalize-space()='Avanti']")
    WebElement avanti;

    @FindBy(xpath = "(//input[@id='codiceFiscale'])[1]")
    WebElement setCodiceFiscale;

    @FindBy(xpath = "//td[4]")
    WebElement getCodiceFiscale;

    @FindBy(xpath = "//span[@class='material-icons fs-4 m-0']")
    WebElement compAutomatico;

    @FindBy(xpath = "//input[@name='recapitoTelefonico']")
    WebElement recapitoTelefonico;

    @FindBy(xpath = "//input[@name='email']")
    WebElement email;

    @FindBy(xpath = "(//select[@id='modalitaRimborso'])[1]")
    WebElement modalitaRimborso;

    @FindBy(xpath = "//input[@id='iban']")
    WebElement iban;

    @FindBy(xpath = "//select[@id='motivazioneRimborso']")
    WebElement motivazioneRimborso;

    @FindBy(xpath = "//input[@id='importoRimborso']")
    WebElement importoRimborso;

    @FindBy(xpath = "//input[@id='protocolloRegionale']")
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



    @FindBy(xpath = "//button[normalize-space()='Salva ed Esci']")
    WebElement salvaEsci;

    @FindBy(xpath = "//button[@id='sottoscrizioneBtn']")
    WebElement confermaSottoscrivi;

    @FindBy(xpath = "//button[normalize-space()='Digitalmente']")
    WebElement digitalmente;

    @FindBy(xpath = "//button[normalize-space()='Firmando il riepilogo cartaceo']")
    WebElement cartaceo;

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

    public void setAnnulla(){
        Actions actions = new Actions(driver);
        actions.moveToElement(annulla).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(annulla));
        this.annulla.click();

    }
    public void setIndietro(){
        Actions actions = new Actions(driver);
        actions.moveToElement(indietro).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(indietro));
        this.indietro.click();
    }
    public void setRiepilogo(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        riepilogo.sendKeys(fileToUpload.getAbsolutePath());
    }
    public void setInstanzaOption(){
        this.instanzaOption.click();
}

    public void setSottoscrizione(){
        this.sottoscrizione.click();
    }

    public void setTornaLista(){
        this.tornaLista.click();
    }
    public void setAcquisizioneRimborso(){
        this.acquisizioneRimborso.click();
    }

    public void setNuovaPratica(){
        Actions actions = new Actions(driver);
        actions.moveToElement(nuovaPratica).perform();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(nuovaPratica));
        this.nuovaPratica.click();
    }


    public void setCerca() throws InterruptedException{
        this.cerca.click();

    }

    public void setPagamentRichiesto(String numeroRicevuta) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(pagamentoRichiesto));


            String number = numeroRicevuta;
            String specifiNumber = number.replace("'", "").trim();

            //Click su Radio Button
            WebElement radioButton = driver.findElement(By.xpath("//td[contains(text(),'" + specifiNumber + "')]/preceding::input[@name=\"currentPagamento\"][1]"));
            radioButton.click();
            System.out.println("Clicked radiobutton");
        }

        public String setImportoEuro(){
        String total = this.importoEuro.getText();
            return total;
        }



    public boolean setAvanti() throws InterruptedException{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", avanti);
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(avanti)).click();
        Thread.sleep(1000);
        return false;
    }

    public void SetCodFiscale(String codFiscale) throws InterruptedException{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", setCodiceFiscale);
        Thread.sleep(15000);
        this.setCodiceFiscale.sendKeys(codFiscale);
    }

    public void setCompAutomatico() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datiRichiedente);
        Thread.sleep(500);
        this.compAutomatico.click();


    }

    public void setCodiceVeicolo(String tipoVeicolo) throws InterruptedException{

            Select objSelect = new Select(this.codiceVeicolo);
            objSelect.selectByValue(tipoVeicolo);
            Thread.sleep(500);
        }

    public void setRecapitoTelefonico(String recapTelefonico){
        this.recapitoTelefonico.sendKeys(recapTelefonico);
    }

    public void setEmail(String email){
        this.email.sendKeys(email);
    }

    public void setModalitaRimborso(String modalitaRimborso) throws InterruptedException{

        Select objSelect = new Select(this.modalitaRimborso);
        objSelect.selectByValue(modalitaRimborso);
        Thread.sleep(500);
    }

    public void setIban(String iban){
        this.iban.sendKeys(iban);
    }

    public void setMotivazioneRimborso(String motivazioneRimborso)throws InterruptedException{

        Select objSelect = new Select(this.motivazioneRimborso);
        objSelect.selectByValue(motivazioneRimborso);
        Thread.sleep(500);
    }

    public void setImportoRimborso(String importoRimborsabile) {
        this.importoRimborso.sendKeys(importoRimborsabile);
    }

    public void setProtocolloRegionale(String protocolloRegionale){
        this.protocolloRegionale.sendKeys(protocolloRegionale);
    }

    public void setAllegato1(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato1.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato2(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato2.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato3(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato3.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato4(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato4.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato5(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato5.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato6(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato6.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato7(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato7.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato8(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato8.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato9(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato9.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato10(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato10.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato11(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato11.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setAllegato12(){
        File fileToUpload = new File(System.getProperty("user.dir")+"//src/test/resources/Instanza/Allegato_Prova.pdf");
        allegato12.sendKeys(fileToUpload.getAbsolutePath());

    }

    public void setSalvaEsci(){
        this.salvaEsci.click();
    }

    public void setConfermaSottoscrivi() throws InterruptedException{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", confermaSottoscrivi);
        Thread.sleep(1000);
        this.confermaSottoscrivi.click();
    }

    public void setDigitalmente(){
        this.digitalmente.click();
    }

    public void setCartaceo(){
        this.cartaceo.click();
    }

    public void setAnnoValidità(String annoValidita) throws InterruptedException{

        Select objSelect = new Select(Validità);
        objSelect.selectByValue(annoValidita);
        Thread.sleep(500);
    }

}
