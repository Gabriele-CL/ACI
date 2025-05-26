package com.aci.test.ACI;

import com.aci.test.POM.Login;
import com.aci.test.POM.Login_Sinta;
import com.aci.POM.Sinta_Plus;
import com.aci.utils.ExcelUtils_DatiDichiarati;
import com.aci.utils.ExcelUtils_DatiDichiarati_SintaPlus;
import io.github.bonigarcia.wdm.WebDriverManager;
//import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ACI_SintaPlus_Test {

    public WebDriver driver;
    public WebElement element;
    Login_Sinta objLogin;
    Sinta_Plus objPlus;
    @BeforeTest

    public void beforetest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://10.64.3.139/tasse-portal/doLogin.do");

        //Accesso a Sinta Plus
        objLogin = new Login_Sinta(driver);
        objPlus = new Sinta_Plus(driver);

        objLogin.setUsername3();
        objLogin.setPassword3();
        objLogin.setLoginButton();
        objLogin.clear();
        objLogin.setSintaPlus();
        objLogin.setInvia();

        List<WebElement> aperturaCassa = driver.findElements(By.xpath("//h1[normalize-space()='Apertura Cassa']"));

        if (aperturaCassa.size() > 0){
            System.out.println("Va fatta la prima apertura cassa mattutina");
            objPlus.setInviaCassa();
            objPlus.setTarga();
            objPlus.setTerzaOpzione();
            objPlus.setSalva();

        }else{
            System.out.println("Il messaggio non è apparso, vai avanti");
            objPlus.setTarga();
            objPlus.setTerzaOpzione();
            objPlus.setSalva();
        }

    }


    @Test(dataProviderClass = ExcelUtils_DatiDichiarati_SintaPlus.class, dataProvider = "ACIWorksheet")
    public void Test1(String Cod_Classe,	String Cod_Test, String Targa, String CodiceFiscale, String TipoVeicolo, String TipoPagamento, String DataScadenzaBollo, String Validità,
                      String Classe, String Uso, String Specialità, String Euro, String DataImmatricolazione, String Alim, String Potenza, String Cilindrata, String Ecologico, String InstazioneImpianto, String DataInstazioneImpianto, String Portata, String Peso, String AssiMotrice, String AssiRimorchio, String SospensioniPneumatiche,
                      String Rimorchiabilità, String PesoRimorchio, String Tassa, String DatiRicusazione, String Agevolazione, String DataRientro, String DurataPeriodo, String Data, String Importo) throws InterruptedException {

        objPlus.setTarga();
        objPlus.setRicercaTarga(Targa);
        Thread.sleep(4000);
        objPlus.setCerca();
        Thread.sleep(20000);


        List<WebElement> errorMessage = driver.findElements(By.xpath("//span[@class='ui-messages-error-icon']"));

        if (errorMessage.size() > 0){
            System.out.println("Il messaggio di errore è presente, si passa per i dati dichiarati");
            objPlus.setDatiDichiarati();
            Thread.sleep(8000);
            objPlus.setCodiceFiscale(CodiceFiscale);
            objPlus.setCategoria(Classe);


        }




    }

}
