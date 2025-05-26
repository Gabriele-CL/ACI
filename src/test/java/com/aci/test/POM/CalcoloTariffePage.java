package com.aci.test.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalcoloTariffePage {
    WebDriver driver;

    @FindBy(xpath = "(//a[@class='dropdown-toggle nav-link'])[1]")
    WebElement dropDownMenu;

    @FindBy(xpath = "(//a[contains(text(),' Calcolo Tariffa ')])[1]")
    WebElement calcoloTariffaOption;

    @FindBy(xpath = "//button[contains(text(),'Cerca')]")
    WebElement cerca;
    @FindBy(xpath = "//button[@class=\"btn btn-link\"]")
    WebElement cancfiltri;

    @FindBy(xpath = "//tbody/tr/td[13]")
    WebElement mensilità;

    public CalcoloTariffePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnDropMenu() {
        dropDownMenu.click();
    }

    public void clickOnOption(){
        calcoloTariffaOption.click();

    }

    public void clickOnCerca() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cerca);
        Thread.sleep(500);
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(cerca)).click();
        Thread.sleep(500);
    }
    public void cancellafiltri() throws InterruptedException{
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cancfiltri);
        Thread.sleep(1000);
        cancfiltri.click();
    }

    public String getMensilita(){
        String mensilità = this.mensilità.getText();
        return mensilità;

    }
}


