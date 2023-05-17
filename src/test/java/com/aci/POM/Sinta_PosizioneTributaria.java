package com.aci.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Sinta_PosizioneTributaria {

    WebDriver driver;
    @FindBy(xpath = "//a[normalize-space()='Posizione tributaria']")
    WebElement posizioneTributaria;

    @FindBy(xpath = "//input[@name='targa']")
    WebElement targa;

    @FindBy(xpath = "//select[@name='tipoVeicolo']")
    WebElement tipoVeicolo;

    @FindBy(xpath = "//input[@name='annoPartenza']")
    WebElement annoPartenza;

    @FindBy(xpath = "//input[@name='buttonTarga']")
    WebElement buttonTarga;


    public Sinta_PosizioneTributaria(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setElencoPeriodo() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//legend[normalize-space()='ELENCO PERIODI TRIBUTARI']")));
    }

    public void setPosizioneTributaria() {
        this.posizioneTributaria.click();
    }

    public void setTarga(String targa) {
        this.targa.sendKeys(targa);
    }

    public void setTipoVeicolo(String tipoVeicolo) {
        Select objSelect = new Select(this.tipoVeicolo);
        objSelect.selectByValue(tipoVeicolo);
    }

    public void setAnnoPartenza(String periodo) {
        this.annoPartenza.clear();
        this.annoPartenza.sendKeys(periodo);
    }

    public void setButtonTarga() {
        this.buttonTarga.click();
    }

    public void tabella() {
        WebElement table = driver.findElement(By.xpath("//table[@id='tabella-dati']"));

        //Find all the rows of the table
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        //Iterate through each row of the table starting from the second row
        for (int i = 1; i < rows.size(); i++) {
            WebElement row = rows.get(i);

            //Find all the cells in the row
            List<WebElement> cells = row.findElements(By.tagName("td"));

            //Iterate through each cell and get the text
            for (WebElement cell : cells) {
                String cellText = cell.getText();
                System.out.println(cellText);
            }
        }
    }

}

