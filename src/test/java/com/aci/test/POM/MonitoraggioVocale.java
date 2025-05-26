package com.aci.test.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MonitoraggioVocale {

    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"text_input_1\"]")
    WebElement username;

    @FindBy(xpath = "//*[@id=\"text_input_2\"]")
    WebElement password;

    @FindBy(xpath = "//button[@kind=\"secondaryFormSubmit\"]")
    WebElement login;

    @FindBy( css = "#root > span > svg")
    WebElement pulsanteVocale;


    public MonitoraggioVocale(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void inserisciUsername(){
        this.username.sendKeys("daniela");
    }

    public void inserisciPassword(){
        this.password.sendKeys("daniela");
    }

    public void clickLogin(){
        this.login.click();
    }

    public void clickRegistrazione(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(pulsanteVocale));
        this.pulsanteVocale.click();
    }




}
