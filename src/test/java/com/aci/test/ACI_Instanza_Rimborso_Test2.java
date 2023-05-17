package com.aci.test;

import com.aci.POM.CalcoloBolloPage;
import com.aci.POM.InstanzaRimborso;
import com.aci.POM.Login;
import com.aci.utils.ExcelUtils_InstanzaRimborso;
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
import java.time.Duration;
import java.util.List;

import static com.aci.utils.PropertiesFile.getUrl;


public class ACI_Instanza_Rimborso_Test2 {

    public WebDriver driver;
    Login objLogin;
    InstanzaRimborso objRimborso;
    CalcoloBolloPage objCalBollo;

    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getUrl());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //Login
        objLogin = new Login(driver);
        objRimborso = new InstanzaRimborso(driver);
        objLogin.enterCrendentials();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        objRimborso.setInstanzaOption();
        objRimborso.setAcquisizioneRimborso();
    }

    @Test(dataProviderClass = ExcelUtils_InstanzaRimborso.class, dataProvider = "ACIWorksheet")

    //dati datapool da inserire nell'applicativo
    public void Test1(String Targa, String TipoVeicolo, String AnnoValidita,
                      String CodiceFiscale, String NumeroRicevuta,
                      String RecapTelefonico, String Email, String ModalitaRimborso, String Iban,
                      String MotivazioneRimborso, String ImportoRimborsabile, String ProtocolloRegionale, String Completamento) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        objRimborso = new InstanzaRimborso(driver);
        objCalBollo = new CalcoloBolloPage(driver);
        objRimborso.setNuovaPratica();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        objCalBollo.setNumeroTarga(Targa);
        objRimborso.setCodiceVeicolo(TipoVeicolo);
        objRimborso.setAnnoValidità(AnnoValidita);
        objRimborso.setCerca();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<WebElement> noElements = driver.findElements(By.xpath("//p[normalize-space()='Nessun elemento trovato']"));

        if (noElements.size() > 0) {
            System.out.println("Non esiste nessuna possibile istanza per la targa" + " " + Targa);

        } else {


            WebElement pagamento = driver.findElement(By.xpath("(//input[@id='currentPagamento'])"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pagamento);

            List<WebElement> elements = driver.findElements(By.xpath("//span[normalize-space()='info_outline']"));

            if (elements.size() > 0) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                System.out.println("Istanza già fatta");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                objRimborso.setAnnulla();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                js.executeScript("window.scrollTo(0, 0)");
            } else {


                objRimborso.setPagamentRichiesto(NumeroRicevuta);
                objRimborso.setAvanti();

                objRimborso.SetCodFiscale(CodiceFiscale);
                objRimborso.setCompAutomatico();

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                List<WebElement> errore = driver.findElements(By.xpath("//h5[normalize-space()='Mancanza dati anagrafici']"));

                if (errore.size() > 0) {
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    System.out.println("Il datapool non contiene tutti i dati che servono");
                    objRimborso.setIndietro();
                    objRimborso.setAnnulla();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

                } else {


                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

                    objRimborso.setRecapitoTelefonico(RecapTelefonico);
                    objRimborso.setEmail(Email);

                    WebElement verifyClick = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick);


                    if (!verifyClick.isEnabled()) {
                        System.out.println("Il tasto non è cliccabile");
                        driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

                    } else {
                        System.out.println("Do nothing");
                        objRimborso.setAvanti();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

                        objRimborso.setModalitaRimborso(ModalitaRimborso);
                        objRimborso.setIban(Iban);
                        objRimborso.setMotivazioneRimborso(MotivazioneRimborso);
                        objRimborso.setImportoRimborso(ImportoRimborsabile);

                        objRimborso.setProtocolloRegionale(ProtocolloRegionale);

                        WebElement avanti = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                        if (!avanti.isEnabled()) {
                            System.out.println("Il tasto non è cliccabile");
                            driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

                        } else {
                            objRimborso.setAvanti();

                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

                            if (MotivazioneRimborso.contains("1")) {

                                objRimborso.setAllegato1();
                                objRimborso.setAllegato2();
                                objRimborso.setAllegato3();
                                objRimborso.setAllegato4();
                            } else if (MotivazioneRimborso.contains("2")) {
                                objRimborso.setAllegato5();
                                objRimborso.setAllegato6();
                                objRimborso.setAllegato7();
                            } else if (MotivazioneRimborso.contains("3")) {
                                objRimborso.setAllegato8();
                                objRimborso.setAllegato9();
                            } else if (MotivazioneRimborso.contains("4")) {
                                objRimborso.setAllegato10();
                                objRimborso.setAllegato11();
                                objRimborso.setAllegato12();
                            }

                            objRimborso.setAvanti();
                            objRimborso.setConfermaSottoscrivi();

                            if (Completamento.contains("1")) {
                                objRimborso.setDigitalmente();
                            } else {
                                objRimborso.setCartaceo();
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                                objRimborso.setRiepilogo();
                                objRimborso.setSottoscrizione();
                            }

                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                            objRimborso.setTornaLista();

                        }

                    }
                }
            }
        }
    }
}



