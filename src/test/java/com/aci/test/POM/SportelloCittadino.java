package com.aci.test.POM;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class SportelloCittadino {
    WebDriver driver;
    WebElement element;

    @FindBy(xpath = "(//div[@role=\"listitem\"])[1]")
    WebElement opzionePRA;

    @FindBy(xpath = "//span[contains(text(),'Creata il')]")
    WebElement creata;

    @FindBy(xpath = "//*[@class=\"form-control mx-textarea-input\"]")
    WebElement datiRichiesta;

     @FindBy(xpath = "//label[contains(text(),'Ufficio')]")
     WebElement ufficio;

    @FindBy(xpath = "//label[contains(text(),'Online')]")
    WebElement online;

    @FindBy(xpath = "//span[contains(text(),'RICHIESTA INFORMAZIONI SUI SERVIZI')]")
    WebElement richiestaInfo;

    @FindBy(xpath = "//span[contains(text(),'RILASCIO INFORMAZIONI GENERALI SUI SERVIZI OFFERTI')]")
    WebElement infoGenerali;

    @FindBy(xpath = "(//div[@role=\"listitem\"])[3]")
    WebElement opzioneURP;

    @FindBy(xpath = "//span[contains(text(), 'ANNOTAZIONE/TRASCRIZIONI')]")
    WebElement opzioneAnnotazioni;

    @FindBy(xpath = "//span[contains(text(), 'EFFETTUARE UN TRASFERIMENTO PROPRIETA')]")
    WebElement opzioneTrasferimento;

    @FindBy(xpath = "//span[contains(text(), 'ANNOTARE UNA PERDITA O UN RIENTRO IN POSSESSO')]")
    WebElement opzionePerditaRientro;

    @FindBy(xpath = "//span[contains(text(), 'PERDITA POSSESSO')]")
    WebElement perditaPossesso;

    @FindBy(xpath = "//span[contains(text(), 'PER FURTO')]")
    WebElement perFurto;

    @FindBy(xpath = "//span[contains(text(), 'PER USO PROPRIO O PRIVATO')]")
    WebElement opzioneProPrivato;

    @FindBy(xpath = "//span[contains(text(), 'USO DEL VEICOLO PROPRIO O PRIVATO')]")
    WebElement opzioneUsoProPrivato;

    @FindBy(xpath = "//input[@class=\"widget-combobox-input\"]")
    WebElement selezioneUfficio;

    @FindBy(xpath = "//input[@class=\"widget-combobox-input\"]")
    WebElement selezioneRegione;

    @FindBy(xpath = "//button[@id=\"proceed-button\"]")
    WebElement continuaSulSito;

    @FindBy(xpath = "//span[@class=\"widget-combobox-caption-text\"]")
    WebElement primaOpzione;

    @FindBy(xpath = "(//div[@data-position=\"0,0\"]//span[contains(text(), '08:00')])[1]")
    WebElement orario;

    @FindBy(xpath = "//input[@class=\"widget-combobox-input\"]")
    WebElement tipologiaVeicolo;

    @FindBy(xpath = "(//input[@type=\"text\"])[6]")
    WebElement targa;

    @FindBy(xpath = "(//input[@type=\"text\"])[7]")
    WebElement codiceFiscale;

    @FindBy(xpath = "(//input[@type=\"text\"])[8]")
    WebElement cellulare;

    @FindBy(xpath = "(//input[@type=\"text\"])[9]")
    WebElement verificaCell;

    @FindBy(xpath = "(//input[@type=\"text\"])[10]")
    WebElement email;

    @FindBy(xpath = "(//input[@type=\"text\"])[11]")
    WebElement verificaEmail;

    @FindBy(xpath = "//button[@type=\"button\"]/span")
    WebElement successivo;

    @FindBy(xpath = "(//button[@type=\"button\"])[2]")
    WebElement caricaDocumenti;

    @FindBy(xpath = "//input[@type='file']")
    WebElement documenti;

    @FindBy(xpath = "//button[@class= 'close']")
    WebElement close;

    @FindBy(xpath = "//div[@class=\"widget-switch-btn left\"]")
    WebElement presaVisione;

    @FindBy(xpath = "//button[@class=\"btn btn-primary\"]")
    WebElement confermaEliminazione;

    @FindBy(xpath = "//span[@class=\"mx-text mx-name-text1 au-Slot-Tile au-Slot_Tile\"]")
    WebElement selezioneOrario;

    @FindBy(xpath = "(//a[@role=\"button\"])[2]")
    WebElement macroServizio;

    @FindBy(xpath = "(//div[@role=\"listitem\"])[2]")
    WebElement opzioneTasse;

    @FindBy(xpath = "//span[contains(text(), 'ESENZIONI')]")
    WebElement esenzione;

    @FindBy(xpath = "//span[contains(text(), 'Richiedere una esenzione per persone con disabilità')]")
    WebElement esenzioneDisab;

    @FindBy(xpath = "//span[contains(text(), 'Richiedere una esenzione/riduzione per veicolo storico')]")
    WebElement esenzione2;

    @FindBy(xpath = "//button[contains(text(), 'OK')]")
    WebElement ok;


    public SportelloCittadino(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOpzionePRA() {
        this.opzionePRA.click();
    }

    public void clickOpzioneAnnotazioni() {
        this.opzioneAnnotazioni.click();
    }

    public void clickTrasferimento() {
        this.opzioneTrasferimento.click();

    }

    public void clickPerditaRientro() {
        this.opzionePerditaRientro.click();

    }

    public void clickInfoGenerali() {
        this.infoGenerali.click();

    }

    public void clickPerditaPossesso() {
        this.perditaPossesso.click();
    }

    public void clickFurto() {
        this.perFurto.click();
    }

    public void clickProprioPrivato() {
        this.opzioneProPrivato.click();
    }

    public void clickUsoProprioPrivato() {
        this.opzioneUsoProPrivato.click();
    }

    public void setSelezioneUfficio() {
        this.selezioneUfficio.sendKeys("ROMA");
        this.selezioneUfficio.sendKeys(Keys.ENTER);
    }

    public void setSelezioneRegione() {
        this.selezioneUfficio.sendKeys("LAZIO");
        this.selezioneUfficio.sendKeys(Keys.ENTER);
    }

    public void clickPrimaOpzione() {
        this.primaOpzione.click();
    }

    public void clickCreata(){
        this.creata.click();
    }

    public void selectOrario() {
        this.orario.click();
    }

    public void tipologiaVeicolo() {
        this.tipologiaVeicolo.sendKeys("Autoveicolo");
        this.tipologiaVeicolo.sendKeys(Keys.ENTER);
    }


    public void setTarga() {
        this.targa.sendKeys("VA713AA");
    }

    public void setCodiceFiscale() {
        this.codiceFiscale.sendKeys("VTIMRC64H23H501F");
    }

    public void setNumero() {
        this.cellulare.sendKeys("3357350760");
    }

    public void setVerificaCell() {
        this.verificaCell.sendKeys("3357350760");
    }

    public void setEmail() {
        this.email.sendKeys("sportello_aci@putsbox.com");
    }

    public void setVerificaEmail() {
        this.verificaEmail.sendKeys("sportello_aci@putsbox.com");
    }

    public void clickSuccessivo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(successivo));
        this.successivo.click();
    }

    public void clickOk(){
        this.ok.click();

    }

    public void clickCaricaDocumenti() {
        this.caricaDocumenti.click();
    }

    public void inserisciDocumenti() {
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/pdf_A_b1.pdf");
        documenti.sendKeys(fileToUpload.getAbsolutePath());
    }

    public void clickClose() {
        this.close.click();
    }

    public void clickPV() {
        this.presaVisione.click();
    }

    public void confEliminazione() {
        this.confermaEliminazione.click();
    }



    public String selezionaOrario() {
        List<WebElement> orari = driver.findElements(By.xpath("//div[@class=\"mx-name-container2 au-Slot_Tile au-Slot_Tile\"]"));

        for (WebElement orario : orari) {
            if (orario.isEnabled() && orario.isDisplayed()) {
                String slot = orario.getText();
                orario.click();
                return slot;
            }
        }
        return null;
    }

    public String selezionaOrarioSlot(String orarioDesiderato) {
        List<WebElement> orari = driver.findElements(By.xpath("//div[@class=\"mx-name-container2 au-Slot_Tile au-Slot_Tile\"]"));

        for (WebElement orario : orari) {
            if (orario.isEnabled() && orario.isDisplayed()) {
                String slot = orario.getText();
                if (slot.contains(orarioDesiderato)) {
                    orario.click();
                    return slot;
                }
            }
        }
            return null;
    }



    public void setMacroServizio() {
        this.macroServizio.click();
    }

    public void setOpzioneTasse() {
        this.opzioneTasse.click();
    }

    public void clickEsenzione() {
        this.esenzione.click();
    }

    public void clickEsenzioneDisab() {
        this.esenzioneDisab.click();
    }

    public void clickEsenzioneStorico() {
        this.esenzione2.click();
    }

    public void clickOpzioneURP() {
        this.opzioneURP.click();
    }

    public void clickRichiestaInfo() {
        this.richiestaInfo.click();
    }

    public void clickUfficio() {
        this.ufficio.click();
    }

    public void clickOnline() {
        this.online.click();
    }

    public void clickContinuaSulSito(){
        this.continuaSulSito.click();
    }

    public void compilaRichiesta() {
        this.datiRichiesta.sendKeys("Test per dati richiesta");
    }

}


