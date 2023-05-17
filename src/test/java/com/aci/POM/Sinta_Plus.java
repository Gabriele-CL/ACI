package com.aci.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Sinta_Plus {

    WebDriver driver;

    @FindBy(xpath = "//span[@class='ui-button-text ui-c']")
    WebElement inviaCassa;

    @FindBy(xpath = "//span[normalize-space()='Targa/Telaio']")
    WebElement targa;

    @FindBy(xpath = "(//span[@class='ui-radiobutton-icon ui-icon ui-icon-blank ui-c'])[4]")
    WebElement terzaOpzione;

    @FindBy(xpath = "//span[@class='ui-button-text ui-c']")
    WebElement salva;

    @FindBy(xpath = "//input[@id='formRicercaTarga:targaTelaio']")
    WebElement ricercaTarga;

    @FindBy(xpath = "//span[contains(text(),'Cerca')]")
    WebElement cerca;

    @FindBy(xpath = "(//span[normalize-space()='Procedi per Dati Dichiarati'])[1]")
    WebElement datiDichiarati;

    @FindBy(xpath = "//input[@id='formDatiDichiarati:codiceFiscale']")
    WebElement codiceFiscale;

    @FindBy(xpath = "//input[@id='formRicercaTarga:codiceFiscale']")
    WebElement codiceFiscaleIniziale;

    @FindBy(xpath = "//div[@class='ui-selectonemenu-trigger ui-state-default ui-corner-right ui-state-hover']//span[@class='ui-icon ui-icon-triangle-1-s ui-c']")
    WebElement categoria;

    public Sinta_Plus(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setCodiceFiscale(String codiceFiscale){
        this.codiceFiscale.sendKeys(codiceFiscale);
    }

    public void setCategoria(String classe) throws InterruptedException {
        Select objSelect = new Select(categoria);
        objSelect.selectByValue(classe);
        Thread.sleep(500);
    }

    public void setCerca(){
        this.codiceFiscaleIniziale.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cerca);
        this.cerca.click();
    }

    public void setRicercaTarga(String targa){
        this.ricercaTarga.sendKeys(targa);
    }

    public void setTerzaOpzione() {
        this.terzaOpzione.click();
    }

    public void setSalva(){
        this.salva.click();
    }

    public void setTarga() {
        this.targa.click();
    }

    public void setDatiDichiarati(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datiDichiarati);
        this.datiDichiarati.click();
    }

    public void setInviaCassa(){
        this.inviaCassa.click();
    }
}
