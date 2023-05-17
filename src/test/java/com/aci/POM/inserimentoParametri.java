package com.aci.POM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class inserimentoParametri {

    WebDriver driver;

    @FindBy(xpath = "//select[@id=\"categoria\"]")
    WebElement categoria;

    @FindBy(xpath = "//input[@id=\"dataValidita\"]")
    WebElement dataValidita;

    @FindBy(xpath = "//input[@id=\"dataImmatricolazione\"]")
    WebElement immatricolazione;

    @FindBy(xpath = "//input[@id=\"numeroMesi\"]")
    WebElement numeroMesi;

    @FindBy(xpath = "//select[@id=\"uso\"]")
    WebElement us0;

    @FindBy(xpath = "//select[@id=\"specialita\"]")
    WebElement specialita;

    @FindBy(xpath = "//select[@id=\"alimentazione\"]")
    WebElement alimentazione;

    @FindBy(xpath = "//input[@id=\"kw\"]")
    WebElement potenza;

    @FindBy(xpath = "//select[@id=\"euro\"]")
    WebElement eur0;

    @FindBy(xpath = "//input[@id=\"portata\"]")
    WebElement portata;

    @FindBy(xpath = "//input[@id=\"pesoComplessivo\"]")
    WebElement peso;

    @FindBy(xpath = "//input[@id=\"assiMotore\"]")
    WebElement assiMotrice;

    @FindBy(xpath = "//input[@id=\"pesoRimorchio\"]")
    WebElement pesoRimorchio;

    @FindBy(xpath = "//input[@id=\"sospensionePneumatica\"]")
    WebElement sospensionePneumatica;

    @FindBy(xpath = "//input[@id=\"gancioTraino\"]")
    WebElement gancioTraino;

    @FindBy(xpath = "//input[@id=\"cilindrata\"]")
    WebElement cilindrata;



    public inserimentoParametri(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void selectCategoria(String classe) throws InterruptedException{

        Select objSelect = new Select(categoria);
        objSelect.selectByValue(classe);
        Thread.sleep(500);
    }

    public void selectDataValidita(String dataValidità) throws InterruptedException {
        Thread.sleep(1000);
        this.dataValidita.sendKeys(dataValidità);

    }

    public void selectImmatricolazione(String dataCostruzione) throws InterruptedException {
        Thread.sleep(1000);
        this.immatricolazione.sendKeys(dataCostruzione);
    }

    public void selectMesi(){
        numeroMesi.click();
        this.numeroMesi.sendKeys("12");
    }

    public void selectUso(String uso) throws InterruptedException {
        Select objSelect = new Select(us0);
        objSelect.selectByValue(uso);
        Thread.sleep(1000);

    }

    public void selectSpecialità(String specialità) throws InterruptedException {
        Thread.sleep(1000);
        this.specialita.sendKeys(specialità);
    }

    public void selectAlimentazione(String alim) throws InterruptedException {
        Select objSelect = new Select(alimentazione);
        objSelect.selectByValue(alim);

        Thread.sleep(500);
    }

    public void selectPotenza(String potenza) throws InterruptedException{
        this.potenza.click();
        this.potenza.sendKeys(potenza);

        Thread.sleep(500);
    }

    public void selectEuro(String euro) throws InterruptedException{

        Thread.sleep(500);
        Select objSelect = new Select(eur0);
        objSelect.selectByValue(euro);



    }

    public void selectPortata(String portata) throws InterruptedException {
        Thread.sleep(1000);
        this.portata.sendKeys(portata);

    }

    public void selectPeso(String peso) throws InterruptedException {
        Thread.sleep(1000);
        this.peso.sendKeys(peso);

    }

    public void selectAssiMotrici(String assiMotrici) throws InterruptedException{
        Thread.sleep(1000);
        this.assiMotrice.sendKeys(assiMotrici);
    }

    public void selectPesoRimorchio(String pesoRimorchio) throws InterruptedException{
        Thread.sleep(1000);
        this.pesoRimorchio.sendKeys(pesoRimorchio);
    }

    public void selectSospensioniPneumatiche() throws InterruptedException{
        Thread.sleep(1000);
        this.sospensionePneumatica.click();
    }

    public void selectGancioTraino() throws InterruptedException{
        Thread.sleep(1000);
        this.gancioTraino.click();
    }

    public void selectCilindrata(String cilindrata) throws InterruptedException{
        Thread.sleep(1000);
        this.cilindrata.sendKeys(cilindrata);
    }
}



