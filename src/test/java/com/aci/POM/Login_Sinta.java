package com.aci.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Sinta {
    WebDriver driver;

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    WebElement password;

    @FindBy(xpath = "//input[@value='Invia']")
    WebElement loginButton;

    @FindBy(xpath = "//input[@name='urlTasseWeb']")
    WebElement urlTasse;

    @FindBy(xpath = "//input[@value='Invia']")
    WebElement invia;

    @FindBy(xpath = "//input[@value='77000']")
    WebElement sintaPlus;

    public Login_Sinta(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCrendentials() throws InterruptedException {
        this.username.sendKeys("g.gregori");
        this.password.sendKeys("manutenzione");
        Thread.sleep(1000);
        this.loginButton.click();
        Thread.sleep(3000);
        this.urlTasse.clear();
        this.invia.click();
    }

    public void setUsername(){
        this.username.sendKeys("g.miranda");
    }

    public void setPassword(){
        this.password.sendKeys("manutenzione");
    }

    public void setUsername2(){
        this.username.sendKeys("ele.amato");
    }

    public void setPassword2(){
        this.password.sendKeys("manutenzione");
    }

    public void setUsername3(){
        this.username.sendKeys("c.pelos");
    }

    public void setPassword3(){
        this.password.sendKeys("manutenzione");
    }

    public void setLoginButton(){
        this.loginButton.click();
    }

    public void setInvia(){
        this.invia.click();
    }

    public void setInviaMulti(){
        this.invia.click();
        this.urlTasse.clear();
        this.invia.click();
    }

    public void setUsernameMulti(String credential){
        this.username.sendKeys(credential);
    }

    public void setPasswordMulti(String credential) {
        this.password.sendKeys(credential);
    }

    public void setSintaPlus(){
        this.sintaPlus.click();
    }

    public void clear(){
        this.urlTasse.clear();
    }


}

