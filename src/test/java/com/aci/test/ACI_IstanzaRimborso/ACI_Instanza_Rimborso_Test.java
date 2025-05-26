package com.aci.test.ACI_IstanzaRimborso;

import com.aci.test.POM.CalcoloBolloPage;
import com.aci.test.POM.InstanzaRimborso;
import com.aci.test.POM.Login;
import com.aci.utils.ExcelUtils_InstanzaRimborso;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.aci.utils.PropertiesFile.getUrl;


public class ACI_Instanza_Rimborso_Test {
    public WebDriver driver;
    public int counterko;
    public int counter;
    public String[][] kogenerator;
    Login objLogin;
    InstanzaRimborso objRimborso;
    CalcoloBolloPage objCalBollo;

    public List<String> targaFinal = new ArrayList<>();
    public List<String> esit0 = new ArrayList<>();
    public List<String> codRicevuta = new ArrayList<>();
    //public List<String> codiceCasistica = new ArrayList<>();
    private boolean anagraficoMancante = false;

    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getUrl());

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'ACCEDI')]")));
        wait.withTimeout(Duration.ofSeconds(8));

        //Login
        objLogin = new Login(driver);
        objRimborso = new InstanzaRimborso(driver);
        objLogin.enterCrendentials();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header-bottom px-3']//a[@class='dropdown-toggle nav-link dropbtn'][normalize-space()='Istanze']")));

        objRimborso.setInstanzaOption();
        objRimborso.setAcquisizioneRimborso();

        String targaFinale = "Targa";
        String esito = "Esito";
        String codiceRicevuta = "Codice Ricevuta";

        targaFinal.add(targaFinale);
        esit0.add(esito);
        codRicevuta.add(codiceRicevuta);


    }

    WebDriverWait wait;

    @Test(dataProviderClass = ExcelUtils_InstanzaRimborso.class, dataProvider = "ACIWorksheet")
    public void Test1(String Targa, String TipoVeicolo, String AnnoValidita, String RichiedentePassivo,
                      String CodiceFiscale, String NumeroRicevuta,
                      String RecapTelefonico, String Email, String ModalitaRimborso, String SoggettoDelegato, String Iban,
                      String MotivazioneRimborso, String ImportoRimborsabile, String ProtocolloRegionale, String Completamento) throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        objRimborso = new InstanzaRimborso(driver);
        objCalBollo = new CalcoloBolloPage(driver);

        objRimborso.setNuovaPratica();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"targa\"]"))); // Wait for the element to be visible

        objCalBollo.setNumeroTarga(Targa);
        objRimborso.setCodiceVeicolo(TipoVeicolo);
        objRimborso.setAnnoValidità(AnnoValidita);
        objRimborso.setCerca();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        By nuovaIstanzaButton = By.xpath("//button[normalize-space()='NUOVA ISTANZA']");
        By noElementFoundMessage = By.xpath("//p[contains(text(), 'Nessun elemento trovato')]");
        By infoIcon = By.xpath("//span[normalize-space()='info_outline']");
        By currentPagamentoInput = By.xpath("(//input[@id='currentPagamento'])");

        List<WebElement> noInstanza = driver.findElements(noElementFoundMessage);

        if (noInstanza.size() > 0) {
            String str = "Non esistono instanze disponibili per la targa: " + Targa;
            counterko++;
            esit0.add(str);
            targaFinal.add(Targa);
            codRicevuta.add(NumeroRicevuta);

            System.out.println("Non esistono instanze disponibili per la targa: " + Targa);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
            wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(currentPagamentoInput));
            WebElement pagamento = driver.findElement(currentPagamentoInput);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", pagamento);

            List<WebElement> infoInstanzaFatta = driver.findElements(infoIcon);
            if (infoInstanzaFatta.size() > 0) {
                String str = "L'instanza è stata già fatta";
                counterko++;
                esit0.add(str);
                targaFinal.add(Targa);
                codRicevuta.add(NumeroRicevuta);
                System.out.println("L'instanza è stata già fatta");
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
                driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                wait.until(ExpectedConditions.visibilityOfElementLocated(nuovaIstanzaButton));
            } else {
                objRimborso.setPagamentRichiesto(NumeroRicevuta);
                objRimborso.setAvanti();

            }
            counter++;
        }

        if (RichiedentePassivo.contains("SI")) {
            objRimborso.clickSoggettoP();

            List<WebElement> errore = driver.findElements(By.xpath("//h5[normalize-space()='Mancanza dati anagrafici']"));

            if (errore.size() > 0) {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                System.out.println("Il datapool non contiene tutti i dati che servono");
                driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                wait.withTimeout(Duration.ofSeconds(15));
            } else {
                wait.withTimeout(Duration.ofSeconds(5));

                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                objRimborso.setEmail(Email);

                WebElement verifyClick = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick);


                if (!verifyClick.isEnabled()) {
                    System.out.println("Il tasto non è cliccabile");
                    driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                    wait.withTimeout(Duration.ofSeconds(20));

                } else {
                    if (RichiedentePassivo.contains("No")) {
                        objRimborso.SetCodFiscale(CodiceFiscale);
                        objRimborso.setCompAutomatico();

                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                        List<WebElement> errore2 = driver.findElements(By.xpath("//h5[normalize-space()='Mancanza dati anagrafici']"));

                        if (errore2.size() > 0) {
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
                            System.out.println("Il datapool non contiene tutti i dati che servono");
                            driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

                        } else {


                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                            objRimborso.setRecapitoTelefonico(RecapTelefonico);
                            objRimborso.setEmail(Email);

                            WebElement verifyClick2 = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick);


                            if (!verifyClick2.isEnabled()) {
                                System.out.println("Il tasto non è cliccabile");
                                driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                            } else if (RichiedentePassivo.contains("Manuale")) {
                                objRimborso.SetCodFiscale2();
                                objRimborso.fillCognome();
                                objRimborso.fillNome();
                                objRimborso.setSesso();
                                objRimborso.setDataNascita();
                                objRimborso.fillProvinciaIndirizzo();
                                objRimborso.fillComuneIndirizzo();
                                objRimborso.fillCapIndirizzo();
                                objRimborso.setRecapitoTelefonico(RecapTelefonico);
                                objRimborso.setEmail(Email);
                                List<WebElement> errore3 = driver.findElements(By.xpath("//h5[normalize-space()='Mancanza dati anagrafici']"));

                                if (errore3.size() > 0) {
                                    System.out.println("Il datapool non contiene tutti i dati che servono");
                                    driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='NUOVA ISTANZA']")));
                                } else {
                                    objRimborso.setRecapitoTelefonico(RecapTelefonico);
                                    objRimborso.setEmail(Email);

                                    WebElement verifyClick3 = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyClick);

                                    if (!verifyClick3.isEnabled()) {
                                        System.out.println("Il tasto non è cliccabile");
                                        driver.get("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='NUOVA ISTANZA']")));
                                    }
                                    System.out.println("Non fare nulla");
                                    objRimborso.setAvanti();
                                    wait.withTimeout(Duration.ofSeconds(5));

                                }
                            }
                        }


                        objRimborso.setModalitaRimborso(ModalitaRimborso);

                        if (ModalitaRimborso.contains("AR")) {
                            objRimborso.setIban(Iban);
                        } else if (ModalitaRimborso.contains("AD")) {
                            objRimborso.setSoggettodelegato(SoggettoDelegato);
                            if (SoggettoDelegato.contains(" PERSONA FISICA ")) {
                                objRimborso.fillCodiceFiscale3(CodiceFiscale);
                                objRimborso.fillCognome2();
                                objRimborso.fillNome2();
                                objRimborso.setIban(Iban);
                            } else if (SoggettoDelegato.contains(" PERSONA GIURIDICA ")) {
                                objRimborso.fillCodiceFiscale3(CodiceFiscale);
                                objRimborso.fillDelegato();
                                objRimborso.setIban(Iban);
                            }
                        } else if (ModalitaRimborso.contains("CU")) {
                            System.out.println("Niente da aggiungere");
                        }
                    }
                    objRimborso.setMotivazioneRimborso(MotivazioneRimborso);
                    objRimborso.setImportoRimborso(ImportoRimborsabile);
                    objRimborso.setProtocolloRegionale(ProtocolloRegionale);

                    WebElement avanti = driver.findElement(By.xpath("//button[normalize-space()='Avanti']"));
                    if (!avanti.isEnabled()) {
                        System.out.println("Dati richiesta rimborso non completa");
                        driver.navigate().to("http://nstar-web-lazio-tsi.apps.osv1.aci.it/home/rimborsi/acquisizione-pratica");
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='NUOVA ISTANZA']")));

                    } else {
                        objRimborso.setAvanti();
                        wait.withTimeout(Duration.ofSeconds(20));

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
                    }
                }
            }
        }

    }
}



        /*objRimborso.setAvanti();
        objRimborso.setConfermaSottoscrivi();

        if(Completamento.contains("1")){
        objRimborso.setDigitalmente();
        }else{
        objRimborso.setCartaceo();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        objRimborso.setRiepilogo();
        objRimborso.setSottoscrizione();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        objRimborso.setTornaLista();

        }
        }
        }
        }
        }
        }
        }*/



