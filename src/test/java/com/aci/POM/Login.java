package com.aci.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static com.aci.utils.PropertiesFile.getPassword;
import static com.aci.utils.PropertiesFile.getUsername;

public class Login {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'ACCEDI')]")
    WebElement accedi;

    @FindBy(xpath = "//input[@id=\"username\"]")
    WebElement username;

    @FindBy(xpath = "//input[@id=\"password\"]")
    WebElement password;

    @FindBy(xpath = "//input[@id=\"kc-login\"]")
    WebElement loginButton;

    public Login(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCrendentials() throws IOException {
        this.accedi.click();
        this.username.sendKeys(getUsername());
        this.password.sendKeys(getPassword());
        this.loginButton.click();
    }
}





