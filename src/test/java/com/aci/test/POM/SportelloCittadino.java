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
        System.out.println("🖱️ Clicco su: Opzione PRA");
        this.opzionePRA.click();
    }

    public void clickOpzioneAnnotazioni() {
        System.out.println("📝 Clicco su: Opzione Annotazioni");
        this.opzioneAnnotazioni.click();
    }

    public void clickTrasferimento() {
        System.out.println("🔄 Clicco su: Trasferimento Proprietà");
        this.opzioneTrasferimento.click();
    }

    public void clickPerditaRientro() {
        System.out.println("🚗 Clicco su: Annotare una perdita/rientro in possesso");
        this.opzionePerditaRientro.click();
    }

    public void clickInfoGenerali() {
        System.out.println("ℹ️ Clicco su: Informazioni Generali");
        this.infoGenerali.click();
    }

    public void clickPerditaPossesso() {
        System.out.println("⚠️ Clicco su: Perdita Possesso");
        this.perditaPossesso.click();
    }

    public void clickFurto() {
        System.out.println("🚨 Clicco su: Per Furto");
        this.perFurto.click();
    }

    public void clickProprioPrivato() {
        System.out.println("🏠 Clicco su: Uso proprio o privato");
        this.opzioneProPrivato.click();
    }

    public void clickUsoProprioPrivato() {
        System.out.println("🚗 Clicco su: Uso veicolo proprio o privato");
        this.opzioneUsoProPrivato.click();
    }

    public void setSelezioneUfficio() {
        System.out.println("🏢 Seleziono: Ufficio (ROMA)");
        this.selezioneUfficio.sendKeys("ROMA");
        this.selezioneUfficio.sendKeys(Keys.ENTER);
    }

    public void setSelezioneRegione() {
        System.out.println("🌍 Seleziono: Regione (LAZIO)");
        this.selezioneUfficio.sendKeys("LAZIO");
        this.selezioneUfficio.sendKeys(Keys.ENTER);
    }

    public void clickPrimaOpzione() {
        System.out.println("👉 Clicco su: Prima opzione");
        this.primaOpzione.click();
    }

    public void clickCreata(){
        System.out.println("🆕 Clicco su: Creata il ...");
        this.creata.click();
    }

    public void selectOrario() {
        System.out.println("⏰ Seleziono un orario");
        this.orario.click();
    }

    public void tipologiaVeicolo() {
        System.out.println("🚗 Inserisco tipologia veicolo: Autoveicolo");
        this.tipologiaVeicolo.sendKeys("Autoveicolo");
        this.tipologiaVeicolo.sendKeys(Keys.ENTER);
    }

    public void setTarga() {
        System.out.println("🔢 Inserisco targa: VA713AA");
        this.targa.sendKeys("VA713AA");
    }

    public void setCodiceFiscale() {
        System.out.println("📄 Inserisco codice fiscale: VTIMRC64H23H501F");
        this.codiceFiscale.sendKeys("VTIMRC64H23H501F");
    }

    public void setNumero() {
        System.out.println("📱 Inserisco numero cellulare: 3357350760");
        this.cellulare.sendKeys("3357350760");
    }

    public void setVerificaCell() {
        System.out.println("🔍 Verifico numero cellulare: 3357350760");
        this.verificaCell.sendKeys("3357350760");
    }

    public void setEmail() {
        System.out.println("📧 Inserisco email: sportello_aci@putsbox.com");
        this.email.sendKeys("sportello_aci@putsbox.com");
    }

    public void setVerificaEmail() {
        System.out.println("🔍 Verifico email: sportello_aci@putsbox.com");
        this.verificaEmail.sendKeys("sportello_aci@putsbox.com");
    }

    public void clickSuccessivo() {
        System.out.println("➡️ Clicco su: Successivo");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(successivo));
        this.successivo.click();
    }

    public void clickOk(){
        System.out.println("🆗 Clicco su: OK");
        this.ok.click();
    }

    public void clickCaricaDocumenti() {
        System.out.println("📤 Clicco su: Carica Documenti");
        this.caricaDocumenti.click();
    }

    public void inserisciDocumenti() {
        System.out.println("📎 Inserisco documenti");
        File fileToUpload = new File(System.getProperty("user.dir") + "//src/test/resources/Instanza/pdf_A_b1.pdf");
        documenti.sendKeys(fileToUpload.getAbsolutePath());
    }

    public void clickClose() {
        System.out.println("❌ Clicco su: Chiudi");
        this.close.click();
    }

    public void clickPV() {
        System.out.println("👁️ Clicco su: Presa Visione");
        this.presaVisione.click();
    }

    public void confEliminazione() {
        System.out.println("🗑️ Confermo eliminazione");
        this.confermaEliminazione.click();
    }

    public String selezionaOrario() {
        System.out.println("🗓️ Seleziono orario disponibile");
        List<WebElement> orari = driver.findElements(By.xpath("//div[@class=\"mx-name-container2 au-Slot_Tile au-Slot_Tile\"]"));
        for (WebElement orario : orari) {
            if (orario.isEnabled() && orario.isDisplayed()) {
                String slot = orario.getText();
                System.out.println("✅ Orario selezionato: " + slot);
                orario.click();
                return slot;
            }
        }
        return null;
    }

    public String selezionaOrarioSlot(String orarioDesiderato) {
        System.out.println("📅 Cerco orario: " + orarioDesiderato);
        List<WebElement> orari = driver.findElements(By.xpath("//div[@class=\"mx-name-container2 au-Slot_Tile au-Slot_Tile\"]"));
        for (WebElement orario : orari) {
            if (orario.isEnabled() && orario.isDisplayed()) {
                String slot = orario.getText();
                if (slot.contains(orarioDesiderato)) {
                    System.out.println("✅ Orario trovato e selezionato: " + slot);
                    orario.click();
                    return slot;
                }
            }
        }
        System.out.println("❌ Orario non trovato");
        return null;
    }

    public void setMacroServizio() {
        System.out.println("🖱️ Clicco su: Macro Servizio");
        this.macroServizio.click();
    }

    public void setOpzioneTasse() {
        System.out.println("💰 Clicco su: Opzione Tasse");
        this.opzioneTasse.click();
    }

    public void clickEsenzione() {
        System.out.println("✅ Clicco su: Esenzione");
        this.esenzione.click();
    }

    public void clickEsenzioneDisab() {
        System.out.println("♿ Clicco su: Esenzione per persone con disabilità");
        this.esenzioneDisab.click();
    }

    public void clickEsenzioneStorico() {
        System.out.println("📜 Clicco su: Esenzione veicolo storico");
        this.esenzione2.click();
    }

    public void clickOpzioneURP() {
        System.out.println("📞 Clicco su: Opzione URP");
        this.opzioneURP.click();
    }

    public void clickRichiestaInfo() {
        System.out.println("💬 Clicco su: Richiesta Informazioni");
        this.richiestaInfo.click();
    }

    public void clickUfficio() {
        System.out.println("🏢 Clicco su: Ufficio");
        this.ufficio.click();
    }

    public void clickOnline() {
        System.out.println("🌐 Clicco su: Online");
        this.online.click();
    }

    public void clickContinuaSulSito(){
        System.out.println("🌍 Clicco su: Continua sul Sito");
        this.continuaSulSito.click();
    }

    public void compilaRichiesta() {
        System.out.println("📝 Compilo la richiesta con testo di esempio");
        this.datiRichiesta.sendKeys("Test per dati richiesta");
    }


}


