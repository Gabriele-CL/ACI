package com.aci.test;

import com.aci.POM.CalcoloBolloPage;
import com.aci.POM.CalcoloTariffePage;
import com.aci.POM.Login;
import com.aci.utils.ExcelUtils_DatiDichiarati;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_CalcoloBollo_perDatiDichiarati_Test {

    public WebDriver driver;
    public int counter;
    public int counterko;
    public String[][] excelgenerator;
    public String[][] kogenerator;

    Login objLogin;
    CalcoloTariffePage objCalTariffe;
    CalcoloBolloPage objCalBollo;

    /**
     * DICHIARAZIONE STRINGHE
     */
    public List<String> resultList = new ArrayList<>();
    public List<String> codClasse = new ArrayList<>();
    public List<String> codtest = new ArrayList<>();
    public List<String> codtestko = new ArrayList<>();
    public List<String> CodClasseko = new ArrayList<>();
    public List<String> datavalidita = new ArrayList<>();
    public List<String> classe0 = new ArrayList<>();
    public List<String> uso0 = new ArrayList<>();
    public List<String> specialità = new ArrayList<>();
    public List<String> euro = new ArrayList<>();
    public List<String> datacostruzione = new ArrayList<>();
    public List<String> alim = new ArrayList<>();
    public List<String> potenza0 = new ArrayList<>();
    public List<String> cilindrata = new ArrayList<>();
    public List<String> portata = new ArrayList<>();
    public List<String> peso = new ArrayList<>();
    public List<String> assimotrici = new ArrayList<>();
    public List<String> sospensione = new ArrayList<>();
    public List<String> ganciotraino = new ArrayList<>();
    public List<String> pesorimorchio = new ArrayList<>();
    public List<String> risulatoattuale = new ArrayList<>();
    public List<String> risultatoattesoko = new ArrayList<>();


    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(getUrl());

        //Login
        objLogin = new Login(driver);
        objLogin.enterCrendentials();

        Thread.sleep(3000);

        objCalTariffe = new CalcoloTariffePage(driver);
        objCalBollo = new CalcoloBolloPage(driver);
        objCalTariffe.clickOnDropMenu();
        objCalBollo.clickOnOption();
        objCalBollo.setTargaDatiDichiarati();

        //Compilazione dei file Excel per AciResults
        String codicetest = "CODICE TEST";
        String codiceclasse = "CODICE CLASSE";
        String esitotest = "ESITO TEST";

        resultList.add(esitotest);
        codtest.add(codicetest);
        codClasse.add(codiceclasse);

        //Compilazione dei file Excel per AciResults
        String codicetestko = "CODICE TEST";
        String codiceclasseko = "CODICE CLASSE";
        String datavalidità = "DATA VALIDITA";
        String classe = "CLASSE";
        String uso = "USO";
        String Specialità = "SPECIALITA";
        String Euro = "EURO";
        String DataCostruzione = "DATACOSTRUZIONE";
        String Alimentazione = "ALIMENTAZIONE";
        String Potenza = "POTENZA";
        String Cilindrata = "CILINDRATA";
        String Portata = "PORTATA";
        String Peso = "PESO";
        String AssiMotrici = "ASSIMOTRICI";
        String Sospensione = "SOSPENSIONE";
        String GancioTraino = "GANCIO TRAINO";
        String PesoRimorchio = "PESO RIMORCHIO";
        String RisultatoAttuale = "RISULTATO ATTUALE";
        String RisultatoAttesoKO = "RISULTATO ATTESO";

        codtestko.add(codicetestko);
        CodClasseko.add(codiceclasseko);
        datavalidita.add(datavalidità);
        classe0.add(classe);
        uso0.add(uso);
        specialità.add(Specialità);
        euro.add(Euro);
        datacostruzione.add(DataCostruzione);
        alim.add(Alimentazione);
        potenza0.add(Potenza);
        cilindrata.add(Cilindrata);
        portata.add(Portata);
        peso.add(Peso);
        assimotrici.add(AssiMotrici);
        sospensione.add(Sospensione);
        ganciotraino.add(GancioTraino);
        pesorimorchio.add(PesoRimorchio);
        risulatoattuale.add(RisultatoAttuale);
        risultatoattesoko.add(RisultatoAttesoKO);
    }

    @Test(dataProviderClass = ExcelUtils_DatiDichiarati.class, dataProvider = "ACIWorksheet")

    //dati datapool da inserire nell'applicativo
    public void Test1 (String Cod_Classe,	String Cod_Test, String Targa, String TipoVeicolo, String TipoPagamento, String DataScadenzaBollo, String Validità,
                       String Classe, String Uso, String Specialità, String Euro, String DataImmatricolazione, String Alim, String Potenza, String Cilindrata, String Ecologico, String InstazioneImpianto, String DataInstazioneImpianto, String Portata, String Peso, String AssiMotrice, String AssiRimorchio, String SospensioniPneumatiche,
                       String Rimorchiabilità, String PesoRimorchio, String Tassa, String DatiRicusazione, String Agevolazione, String DataRientro, String DurataPeriodo, String Data, String Importo) throws InterruptedException {


        /* crezione liste nell'excel*/
        codClasse.add(Cod_Classe);
        codtest.add(Cod_Test);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        objCalBollo = new CalcoloBolloPage(driver);

        // Selezione Targa
        objCalBollo.setTarga(Targa);

        // Selezione Tipo di veicolo
        objCalBollo.setTipoVeicolo(TipoVeicolo);

        // Verifica presenza errore
        if (objCalBollo.setErrorMessage()) {
            objCalBollo.clickCloseError();

        } else {
            System.out.println("Do nothing");
        }

        // Selezione Classe
        objCalBollo.setCategoria(Classe);

        // Selezione tipo di Pagamento
        objCalBollo.setPagamento(TipoPagamento);

        // Selezione scadenza bollo
        objCalBollo.setScadenzaBolloPre(DataScadenzaBollo);

        // Selezione mesi validità
        objCalBollo.setValidita(Validità);

        // Selezione data Immatricolazione
        objCalBollo.setImmatricolazione(DataImmatricolazione);

        // Clicca su Cerca
        objCalBollo.setCalcoloImporto();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Cancella Filtri
        Thread.sleep(100);
        objCalBollo.setCancfiltri();

        // Estrazione del risultato
        String risultatoBollo = objCalBollo.setBollo();

        System.out.println(risultatoBollo);

        // Elaborazione risultato per verifica
        String prezzoBollo = risultatoBollo.replace("€", "").replace(".", ",").trim();

        // Verifica Calcolo del bollo
        /*if(prezzoBollo.equals(Tassa)){
                String str = "Pass";
                resultList.add(str);
            } else {

            String str = "Fail";
            counterko++;
            resultList.add(str);
            codtestko.add(Cod_Test);
            CodClasseko.add(Cod_Classe);
            datavalidita.add(DataValidità);
            classe0.add(Classe);
            uso0.add(Uso);
            specialità.add(Specialità);
            euro.add(Euro);
            datacostruzione.add(DataImmatricolazione);
            alim.add(Alim);
            potenza0.add(Potenza);
            cilindrata.add(Cilindrata);
            portata.add(Portata);
            peso.add(Peso);
            assimotrici.add(AssiMotrici);
            sospensione.add(SospensioniPneumatiche);
            ganciotraino.add(GancioTraino);
            pesorimorchio.add(PesoRimorchio);
            risulatoattuale.add(prezzoBollo); //output effettivo
            risultatoattesoko.add(Tassa);
    }


    counter++;

}
    public String[][] contenitore () {
        return excelgenerator;
    }

         */
    }


















    }
