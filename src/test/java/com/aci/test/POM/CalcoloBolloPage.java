package com.aci.test.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalcoloBolloPage {

    WebDriver driver;

    @FindBy(xpath = "(//a[@class='dropdown-item'][normalize-space()='Calcolo Bollo'])[1]")
    WebElement calcoloBolloOption;


    @FindBy(xpath = "(//a[contains(text(),' Targa e dati dichiarati ')])[1]")
    WebElement targaDatiDichiarati;

    @FindBy(xpath = "(//a[normalize-space()='Targa e periodo tributario'])[1]")
    WebElement targaPeriodoTributario;

    @FindBy(xpath = "//a[@title='Targa']")
    WebElement targa1;

    @FindBy(xpath = "//*[@id=\"targa\"]")
    WebElement numeroTarga;

    @FindBy(xpath = "//input[@id='data']")
    WebElement dataCalcolo;

    @FindBy(xpath = "//input[@id='targaTelaio']" )
    WebElement targa;

    @FindBy(xpath = "//select[@id='tipoVeicolo']")
    WebElement veicolo;

    @FindBy(xpath = "//select[@id=\"categoria\"]")
    WebElement categoria;

    @FindBy(xpath = "//select[@id='tipoPagamento']")
    WebElement pagamento;

    @FindBy(xpath = "//input[@id='scadenzaBolloPrec']")
    WebElement scadenzaBolloPre;

    @FindBy(xpath = "//input[@id='validita']")
    WebElement validita;

    @FindBy(xpath = "//input[@aria-describedby='addon-validita']")
    WebElement validitàPeriodoTri;

    @FindBy(xpath = "//input[@id=\"dataImmatricolazione\"]")
    WebElement immatricolazione;

    @FindBy(xpath = "(//input[@id='dataRicusazione'])[1]")
    WebElement dataRicusazione;

    @FindBy(xpath = "//select[@id='agevolazione']")
    WebElement agevolazione;

    @FindBy(xpath = "//input[@id='dataRientro']")
    WebElement dataRientro;

    @FindBy(xpath = "//input[@id='durataPeriodo']")
    WebElement durataPeriodo;

    @FindBy(xpath = "(//input[@id='dataRicusazione'])[2]")
    WebElement data;

    @FindBy(xpath = "//input[@id='importo']")
    WebElement importo;

    @FindBy(xpath = "//button[normalize-space()='Calcola Importo']")
    WebElement calcoloImporto;

    @FindBy(xpath = "//span[@class='pr-2']")
    WebElement errorMessage;

    @FindBy(xpath = "//button[@class=\"btn-close\"]")
    WebElement closeError;

    @FindBy(xpath = "//button[@class=\"btn btn-link\"]")
    WebElement cancfiltri;

    @FindBy(xpath = "")
    WebElement getBollo;

    @FindBy(xpath = "//input[@id='pagamentoAnticipato']")
    WebElement pagAnticipato;

    @FindBy(xpath = "//tbody/tr/td[10]")
    WebElement totale;

    @FindBy(xpath = "//tbody/tr/td[4]")
    WebElement dataPag;

    @FindBy(xpath = "//tbody/tr/td[5]")
    WebElement dataDeco;

    @FindBy(xpath = "//tbody/tr/td[6]")
    WebElement dataScad;

    @FindBy(xpath = "//tbody/tr/td[7]")
    WebElement tassa;

    @FindBy(xpath = "//tbody/tr/td[8]")
    WebElement sanzione;

    @FindBy(xpath = "//tbody/tr/td[9]")
    WebElement interessi;

    public CalcoloBolloPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnOption() throws InterruptedException{
        Thread.sleep(2000);
        this.calcoloBolloOption.click();
    }

    public void setTargaDatiDichiarati(){
        this.targaDatiDichiarati.click();
    }

    public void setTargaPeriodoTributario(){
        this.targaPeriodoTributario.click();

    }
    public void setTargaOption(){
        this.targa1.click();
    }

    public void setNumeroTarga(String targa) throws InterruptedException{
        this.numeroTarga.sendKeys(targa);
    }

    public void setDataCalcolo(String dataCalcolo){
        this.dataCalcolo.sendKeys(dataCalcolo);
    }

    public void setValiditàPeriodoTri(String validità){
        this.validitàPeriodoTri.sendKeys(validità);
    }

    public void setPagAnticipato(){
        this.pagAnticipato.click();
    }

    public void setTarga(String targa) throws InterruptedException{
        Thread.sleep(2000);
        this.targa.sendKeys(targa);
    }

    public void setTipoVeicolo(String tipoVeicolo) throws InterruptedException{
        Select objSelect = new Select(veicolo);
        objSelect.selectByValue(tipoVeicolo);
        Thread.sleep(500);
    }

    public void setCategoria(String classe) throws InterruptedException{
        Select objSelect = new Select(categoria);
        objSelect.selectByValue(classe);
        Thread.sleep(500);
    }

    public void setPagamento(String tipoPagamento) throws InterruptedException{
        Select objSelect = new Select(pagamento);
        objSelect.selectByValue(tipoPagamento);
        Thread.sleep(500);
    }

    public void setScadenzaBolloPre(String dataScadenzaBollo){
        this.scadenzaBolloPre.sendKeys(dataScadenzaBollo);
    }

    public void setValidita(String validità){
        this.validita.sendKeys(validità);
    }

    public void setImmatricolazione(String dataImmatricolazione){
        this.immatricolazione.sendKeys(dataImmatricolazione);
    }

    public void setDataRicusazione(){
        this.dataRicusazione.sendKeys();
    }

    public void setAgevolazione(){
        this.agevolazione.sendKeys();
    }

    public void setDataRientro(){
        this.dataRientro.sendKeys();
    }

    public void setDurataPeriodo(){
        this.durataPeriodo.sendKeys();
    }

    public void setData(){
        this.data.sendKeys();
    }

    public void setImporto(){
        this.importo.sendKeys();
    }

    public void setCalcoloImporto() throws InterruptedException{
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calcoloImporto);
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(calcoloImporto)).click();
        Thread.sleep(500);
    }

    public void clickCloseError(){
        this.closeError.click();
    }

    public boolean setErrorMessage(){
        this.errorMessage.isDisplayed();
        return true;
    }

    public void setCancfiltri() throws InterruptedException{
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancfiltri);
        Thread.sleep(1000);
        cancfiltri.click();
    }

    public String setBollo(){
        String bollo = this.getBollo.getText();
        return bollo;
    }

    public String setTotale(){
        String totaleEUro = this.totale.getText();
        return totaleEUro;
    }

    public String setTerminePagamento(){
        String dataTermine = this.dataPag.getText();
        return dataTermine;
    }

    public String setDataDecorrenza(){
        String dataDecorrenza = this.dataDeco.getText();
        return dataDecorrenza;
    }

    public String setDataScadenza(){
        String dataScadenza = this.dataScad.getText();
        return dataScadenza;
    }

    public String setTassa(){
        String importoTassa = this.tassa.getText();
        return importoTassa;
    }

    public String setSanzione(){
        String importoSanzione = this.sanzione.getText();
        return importoSanzione;
    }

    public String setInteressi(){
        String importoInteressi = this.interessi.getText();
        return importoInteressi;
    }






}




