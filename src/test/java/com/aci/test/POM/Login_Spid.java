package com.aci.test.POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Spid {

    WebDriver driver;

    @FindBy(xpath = "//button[@type=\"button\"]")
    WebElement accedi;

    @FindBy(xpath = "//span[contains(text(), 'Entra con SPID')]")
    WebElement entraConSpid;

    @FindBy(xpath = "//span[contains(text(), 'Scegli il tuo SPID')]")
    WebElement scegliIltuoSpid;

    @FindBy(xpath = "(//span[contains(text(), 'Demo SPID')])[2]")
    WebElement demoSpid;

    @FindBy(xpath = "//*[@id=\"username\"]")
    WebElement username;

    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement password;

    @FindBy(xpath = "//input[@value=\"Conferma\"]")
    WebElement conferma;

    public Login_Spid(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setAccedi() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(accedi));
        this.accedi.click();
    }

    public void setEntraConSpid() {
        this.entraConSpid.click();
    }

    public void setScegliSpid() {
        this.scegliIltuoSpid.click();
    }

    public void setDemoSpid() {
        Actions actions = new Actions(driver);
        actions.moveToElement(demoSpid).perform();
        this.demoSpid.click();
    }

    public void setUsername() {
        this.username.sendKeys("cesare");
    }

    public void setPassword() {
        this.password.sendKeys("password123");
    }

    public void setConferma() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.END).perform();
        this.conferma.click();
    }

}