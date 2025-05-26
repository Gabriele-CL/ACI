package com.aci.test.ACI;

import com.aci.test.POM.MonitoraggioVocale;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class ACI_Vocale {

    public WebDriver driver;

    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://c-monitoraggio-telefonico.serviziaci.it/");
    }

    @Test
    public void Login() throws InterruptedException {
        MonitoraggioVocale monitoraggioVocale;

        monitoraggioVocale = new MonitoraggioVocale(driver);
        Thread.sleep(6000);
        monitoraggioVocale.inserisciUsername();
        monitoraggioVocale.inserisciPassword();
        monitoraggioVocale.clickLogin();
        Thread.sleep(10000);

        WebElement microfono = driver.findElement(By.xpath("//svg[@data-icon='microphone-lines']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", microfono);

        Thread.sleep(500);

        microfono.click();

    }
}
