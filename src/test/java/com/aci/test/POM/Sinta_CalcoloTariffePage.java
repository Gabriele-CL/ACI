package com.aci.test.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Sinta_CalcoloTariffePage {
    WebDriver driver;

    @FindBy(xpath = "//span[normalize-space()='ARCHIVIOTRIBUTARIO']")
    WebElement archivioTribu;

    @FindBy(xpath = "//a[normalize-space()='Calcolo tariffa']")
    WebElement calcolTariffa;

    @FindBy(xpath = "//select[@name='regione']")
    WebElement regione;

    @FindBy(xpath = "//input[@name='dataCalcoloG']")
    WebElement giorno;

    @FindBy(xpath = "//input[@name='dataCalcoloM']")
    WebElement mese;

    @FindBy(xpath = "//input[@name='dataCalcoloA']")
    WebElement anno;

    @FindBy(xpath = "//select[@name='categoria']")
    WebElement categoria;

    @FindBy(xpath = "//select[@name='direttivaCeeEuro']")
    WebElement eur0;

    @FindBy(xpath = "//select[@name='uso']")
    WebElement us0;

    @FindBy(xpath = "//input[@name='dataCostruzioneG']")
    WebElement giorno2;

    @FindBy(xpath = "//input[@name='dataCostruzioneM']")
    WebElement mese2;

    @FindBy(xpath = "//input[@name='dataCostruzioneA']")
    WebElement anno2;

    @FindBy(xpath = "//input[@name='mesi']")
    WebElement mesi;

    @FindBy(xpath = "//select[@name='specialitaPRA']")
    WebElement specialitaDropdown;



    @FindBy(xpath = "//select[@name='alimentazione']")
    WebElement alimentazione;

    @FindBy(xpath = "//input[@name='portata']")
    WebElement portata;

    @FindBy(xpath = "//input[@name='pesoComplessivo']")
    WebElement peso;

    @FindBy(xpath = "//input[@name='assiMotrici']")
    WebElement assiMotrice;

    @FindBy(xpath = "//input[@name='pesoRimorchio']")
    WebElement pesoRimorchio;

    @FindBy(xpath = "//input[@name='sospensionePneumatica']")
    WebElement sospensionePneumatica;

    @FindBy(xpath = "//input[@name='gancioTraino']")
    WebElement gancioTraino;

    @FindBy(xpath = "//input[@name='cilindrata']")
    WebElement cilindrata;

    @FindBy(xpath = "//input[@name='potenza']")
    WebElement potenza;

    @FindBy(xpath = "//input[@value='INVIO']")
    WebElement invio;

    @FindBy(xpath = "(//tr/td[2])[2]")
    WebElement totaleMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[2]")
    WebElement unMese;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[3]")
    WebElement dueMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[4]")
    WebElement treMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[5]")
    WebElement quattroMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[6]")
    WebElement cinqueMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[7]")
    WebElement seiMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[8]")
    WebElement setteMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[9]")
    WebElement ottoMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[10]")
    WebElement noveMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[11]")
    WebElement dieciMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[12]")
    WebElement undiciMesi;

    @FindBy(xpath = "//*[@id=\"importi\"]/table/tbody/tr[4]/td[13]")
    WebElement dodiciMesi;

    public Sinta_CalcoloTariffePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectPotenza(String potenza) throws InterruptedException{
        this.potenza.click();
        this.potenza.sendKeys(potenza);

        Thread.sleep(500);
    }

    public void setInvio() throws InterruptedException{
        this.invio.click();


        Thread.sleep(500);
    }

    public void setRegione(String regione){
        Select objSelect = new Select(this.regione);
        objSelect.selectByValue(regione);

    }

    public String setTotaleMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totaleMesi);
        String totaleMesi = this.totaleMesi.getText();
        return totaleMesi;
    }

    public String setUnMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", unMese);
        String setUnMesi = this.unMese.getText();
        return setUnMesi;
    }

    public String setDueMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dueMesi);
        String setDueMesi = this.dueMesi.getText();
        return setDueMesi;
    }

    public String setTreMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", treMesi);
        String setTreMesi = this.treMesi.getText();
        return setTreMesi;
    }

    public String setQuattroMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quattroMesi);
        String setQuattroMesi = this.quattroMesi.getText();
        return setQuattroMesi;
    }

    public String setCinqueMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cinqueMesi);
        String setCinqueMesi = this.cinqueMesi.getText();
        return setCinqueMesi;
    }

    public String setSeiMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", seiMesi);
        String setSeiMesi = this.seiMesi.getText();
        return setSeiMesi;
    }

    public String setSetteMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", setteMesi);
        String setSetteMesi = this.setteMesi.getText();
        return setSetteMesi;
    }

    public String setOttoMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ottoMesi);
        String setOttoMesi = this.ottoMesi.getText();
        return setOttoMesi;
    }

    public String setNoveMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", noveMesi);
        String setNoveMesi = this.noveMesi.getText();
        return setNoveMesi;
    }

    public String setDieciMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dieciMesi);
        String setDieciMesi = this.dieciMesi.getText();
        return setDieciMesi;
    }

    public String setUndiciMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totaleMesi);
        String setUndiciMesi = this.undiciMesi.getText();
        return setUndiciMesi;
    }

    public String setDodiciMesi(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dodiciMesi);
        String setDodiciMesi = this.dodiciMesi.getText();
        return setDodiciMesi;
    }

    public void selectCilindrata(String cilindrata) throws InterruptedException{
        Thread.sleep(1000);
        this.cilindrata.sendKeys(cilindrata);
    }

    public void selectGancioTraino() throws InterruptedException{
        Thread.sleep(1000);
        this.gancioTraino.click();
    }

    public void selectAssiMotrici(String assiMotrici) throws InterruptedException{
        Thread.sleep(1000);
        this.assiMotrice.sendKeys(assiMotrici);
    }

    public void selectPeso(String peso) throws InterruptedException {
        Thread.sleep(1000);
        this.peso.sendKeys(peso);

    }

    public void selectPesoRimorchio(String pesoRimorchio) throws InterruptedException{
        Thread.sleep(1000);
        this.pesoRimorchio.sendKeys(pesoRimorchio);
    }

    public void selectSospensioniPneumatiche() throws InterruptedException{
        Thread.sleep(1000);
        this.sospensionePneumatica.click();
    }

    public void selectPortata(String portata) throws InterruptedException {
        Thread.sleep(1000);
        this.portata.sendKeys(portata);

    }

    public void selectSpecialità(String specialità) throws InterruptedException {
        Thread.sleep(1000);
        this.specialitaDropdown.click();

        WebElement specialitaOption = driver.findElement(By.xpath("//select[@name='specialitaPRA']/option[@value='" + specialità + "']"));
        specialitaOption.click();
    }

    public void selectAlimentazione(String alim) throws InterruptedException {
        Select objSelect = new Select(alimentazione);
        objSelect.selectByValue(alim);

        Thread.sleep(500);
    }

    public void setCategoria(String classe){
        Select objSelect = new Select(categoria);
        objSelect.selectByValue(classe);

    }

    public void setEuro(String euro){
        Select objSelect = new Select(eur0);
        objSelect.selectByValue(euro);

    }

    public void setUso(String uso){
        Select objSelect = new Select(us0);
        objSelect.selectByValue(uso);

    }

    public void setGiorno(String giorno){
        this.giorno.clear();
        this.giorno.sendKeys(giorno);
    }

    public void setMese(String mese){
        this.mese.clear();
        this.mese.sendKeys(mese);
    }

    public void setAnno(String anno){
        this.anno.clear();
        this.anno.sendKeys(anno);
    }


    public void setGiorno2(String giorno2){
        this.giorno2.sendKeys(giorno2);
    }

    public void setMese2(String mese2){
        this.mese2.sendKeys(mese2);
    }

    public void setAnno2(String anno2){
        this.anno2.sendKeys(anno2);
    }

    public void setMesi(){
        this.mesi.sendKeys("3");
    }

    public void setArchivioTribu() {
        this.archivioTribu.click();
    }

    public void setCalcolTariffa(){
        this.calcolTariffa.click();
    }
}


