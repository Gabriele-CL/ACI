package com.aci.test;

import com.aci.POM.CalcoloBolloPage;
import com.aci.POM.CalcoloTariffePage;
import com.aci.POM.Login;
import com.aci.utils.ExcelUtils_PerTarga;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_CalcoloBollo_perTargaPeriodoTributario_Test {

    public WebDriver driver;
    public int counter;
    public int counterko;
    public String[][] excelgenerator;
    public String[][] kogenerator;
    Login objLogin;
    CalcoloTariffePage objCalcoloTariffe;
    CalcoloBolloPage objCalcoloBollo;

    public List<String> risultatoTest = new ArrayList<String>();
    public List<String> codClasse = new ArrayList<String>();
    public List<String> codtest = new ArrayList<String>();

    public List<String> targa = new ArrayList<String>();
    public List<String> codtestko = new ArrayList<String>();

    public List<String> CodClasseko = new ArrayList<String>();
    public List<String> risultatoattuale = new ArrayList<String>();
    public List<String> risultatoatteso = new ArrayList<String>();

    public List<String> termPagamentoAtteso = new ArrayList<String>();
    public List<String> termPagamentoAttuale = new ArrayList<String>();
    public List<String> dataDecoAtteso = new ArrayList<String>();
    public List<String> dataDecoAttuale = new ArrayList<String>();
    public List<String> dataScaAtteso = new ArrayList<String>();
    public List<String> dataScaAttuale = new ArrayList<String>();
    public List<String> tassaAtteso = new ArrayList<String>();
    public List<String> tassaAttuale = new ArrayList<String>();
    public List<String> sanzioneAtteso = new ArrayList<String>();
    public List<String> sanzioneAttuale = new ArrayList<String>();
    public List<String> interessiAtteso = new ArrayList<String>();
    public List<String> interessiAttuale = new ArrayList<String>();

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

        objCalcoloTariffe = new CalcoloTariffePage(driver);
        objCalcoloBollo = new CalcoloBolloPage(driver);
        objCalcoloTariffe.clickOnDropMenu();
        objCalcoloBollo.clickOnOption();
        objCalcoloBollo.setTargaPeriodoTributario();

        //Composizione delle colonne e righe del File Excel ACI_Bollo_Results

        String codicetest = "Codice Test";
        String codiceclasse = "Codice Classe";
        String esitotest = "Esito Test";

        risultatoTest.add(esitotest);
        codtest.add(codicetest);
        codClasse.add(codiceclasse);

        //Composizione delle colonne e righe del File Excel KO_Bollo_Results

        String codicetestKo = "Codice Test";
        String codiceclasseKo = "Codice Classe";
        String terminedipaga = "Termine Pagamento Attuale";
        String terminedipaga2 = "Termine Pagamento Atteso";
        String datadecorrenza = "Data Decorrenza Attuale";
        String datadecorrenza2 = "Data Decorrenza Attesa";
        String datascadenza = "Data Scadenza Attuale";
        String datascadenza2 = "Data Scadenza Attesa";
        String tassa = "Tassa Attuale";
        String tassa2 = "Tassa Attesa";
        String sanzione = "Sanzione Attuale";
        String sanzione2 = "Sanzione Attesa";
        String interessi = "Interessi Attuali";
        String interessi2 = "Interessi Attesi";
        String risultatoAttuale = "Risultato Attuale";
        String risultatoAtteso = "Risultato Atteso";


        codtestko.add(codicetestKo);
        CodClasseko.add(codiceclasseKo);
        termPagamentoAttuale.add(terminedipaga);
        termPagamentoAtteso.add(terminedipaga2);
        dataDecoAttuale.add(datadecorrenza);
        dataDecoAtteso.add(datadecorrenza2);
        dataScaAttuale.add(datascadenza);
        dataScaAtteso.add(datascadenza2);
        tassaAttuale.add(tassa);
        tassaAtteso.add(tassa2);
        sanzioneAttuale.add(sanzione);
        sanzioneAtteso.add(sanzione2);
        interessiAttuale.add(interessi);
        interessiAtteso.add(interessi2);
        risultatoattuale.add(risultatoAttuale);
        risultatoatteso.add(risultatoAtteso);
    }


@Test(dataProviderClass = ExcelUtils_PerTarga.class, dataProvider = "ACIWorksheet")

        public void Foglio1(String Cod_Test, String Cod_Classe, String Targa, String TipoVeicolo,
                String ScadenzaPeriodoTrib, String Validità, String DataCalcolo, String PagamentoAnticipato, String TerminePagamento,
                String DataDecorrenza, String DataScadenza, String Tassa, String Sanzione,
                String Interessi, String Prezzo) throws InterruptedException{

    codtest.add(Cod_Test);
    codClasse.add(Cod_Classe);

    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Imposta numero Targa
    objCalcoloBollo.setNumeroTarga(Targa);

        // Imposta tipo Veicolo
    objCalcoloBollo.setTipoVeicolo(TipoVeicolo);

        // Imposta Scadenza
    objCalcoloBollo.setDataCalcolo(ScadenzaPeriodoTrib);

    // Imposta Validità
    objCalcoloBollo.setValiditàPeriodoTri(Validità);

    // Click su Calcola Importo
    objCalcoloBollo.setCalcoloImporto();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // Cancella Filtri
    Thread.sleep(500);
    objCalcoloTariffe.cancellafiltri();

    String conteggio = objCalcoloBollo.setTotale();
    String terminePagamento = objCalcoloBollo.setTerminePagamento();
    String dataDecorrenza1 = objCalcoloBollo.setDataDecorrenza();
    String dataScadenza1 = objCalcoloBollo.setDataScadenza();
    String tassa = objCalcoloBollo.setTassa();
    String sanzione = objCalcoloBollo.setSanzione();
    String interessi = objCalcoloBollo.setInteressi();



    String conteggioFinale = conteggio.replace("€", "").replace(",", ".").trim();
    String tassaFinale = tassa.replace("€", "").replace(",", ".").trim();
    String sanzioneFinale = sanzione.replace("€", "").replace(",", ".").trim();
    String interessiFinale = interessi.replace("€", "").replace(",", ".").trim();

        if (conteggioFinale.equals(Prezzo)) {

        String str = "Pass";
        risultatoTest.add(str);

    } else {

        String str = "Fail";
        counterko++;
        codtestko.add(Cod_Test);
        CodClasseko.add(Cod_Classe);
        risultatoTest.add(str);
        termPagamentoAttuale.add(terminePagamento);
        termPagamentoAtteso.add(TerminePagamento);
        dataDecoAttuale.add(dataDecorrenza1);
        dataDecoAtteso.add(DataDecorrenza);
        dataScaAttuale.add(dataScadenza1);
        dataScaAtteso.add(DataScadenza);
        tassaAttuale.add(tassaFinale);
        tassaAtteso.add(Tassa);
        sanzioneAttuale.add(sanzioneFinale);
        sanzioneAtteso.add(Sanzione);
        interessiAttuale.add(interessiFinale);
        interessiAtteso.add(Interessi);
        risultatoatteso.add(Prezzo);
        risultatoattuale.add(conteggioFinale);


    }
    counter++;
}
    public String[][] contenitore () {
        return excelgenerator;
    }

    @AfterTest
    public void array () throws IOException {

        /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/
        System.out.println(counter);
        this.excelgenerator = new String[counter + 1][3];
        this.kogenerator = new String[counterko + 1][18];




        for (int i = 0; i <= counter; i++) {
            String asd = codClasse.get(i);
            excelgenerator[i][0] = asd;

        }

        for (int i = 0; i <= counter; i++) {
            String asd = codtest.get(i);
            excelgenerator[i][1] = asd;

        }

        for (int i = 0; i <= counter; i++) {
            String asd = risultatoTest.get(i);
            excelgenerator[i][2] = asd;
            //   System.out.println(asd);
        }

        // Posizioni FIle KO

        for (int i = 0; i <= counterko; i++) {
            String ksd = codtestko.get(i);
            kogenerator[i][0] = ksd;

        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = CodClasseko.get(i);
            kogenerator[i][1] = ksd;

        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = termPagamentoAttuale.get(i);
            kogenerator[i][2] = ksd;
            //  System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = termPagamentoAtteso.get(i);
            kogenerator[i][3] = ksd;
            //  System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = dataDecoAttuale.get(i);
            kogenerator[i][4] = ksd;
            //   System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = dataDecoAtteso.get(i);
            kogenerator[i][5] = ksd;
            //   System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = dataScaAttuale.get(i);
            kogenerator[i][6] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = dataScaAtteso.get(i);
            kogenerator[i][7] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = tassaAttuale.get(i);
            kogenerator[i][8] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = tassaAtteso.get(i);
            kogenerator[i][9] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = sanzioneAttuale.get(i);
            kogenerator[i][10] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = sanzioneAtteso.get(i);
            kogenerator[i][11] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = interessiAttuale.get(i);
            kogenerator[i][12] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = interessiAtteso.get(i);
            kogenerator[i][13] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso.get(i);
            kogenerator[i][14] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoattuale.get(i);
            kogenerator[i][15] = ksd;
        }
    }

    @AfterSuite

    public void printexcel () throws IOException {

        /*Creazione excel test svolti*/

        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Risultati_Test");

        //using for loop
        int rows = excelgenerator.length;
        int cols = excelgenerator[0].length;

        for (int r = 0; r < rows; r++) //0 row is created
        {
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) // 0 column is created
            {

                XSSFCell cell = row.createCell(c);
                String value = excelgenerator[r][c];

                if (value != null) {
                    cell.setCellValue(value);
                }
            }
        }

        // Formattazione per aggiungere Data e Ora al nome dei due file Excel

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        String formatedDateTime = current.format(format);

        String filePath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/ACIResults_BolloTargaPeriodoTributario_" + formatedDateTime + ".xlsx";
        FileOutputStream outstream = new FileOutputStream(filePath);
        workbook.write(outstream);


        outstream.close();

        System.out.println("ACI_Results.xlsx file written succesfully ...");

        /* Creazione Ko results*/


        @SuppressWarnings("resource")
        XSSFWorkbook workbookKO = new XSSFWorkbook();
        XSSFSheet sheetKO = workbookKO.createSheet("KO_risultati");

        //using for loop
        int rowsko = kogenerator.length;
        int colsko = kogenerator[0].length;

        for (int r = 0; r < rowsko; r++) //0 row is created
        {
            XSSFRow row = sheetKO.createRow(r);
            for (int c = 0; c < colsko; c++) // 0 column is created
            {
                XSSFCell cellko = row.createCell(c);
                String valueko = kogenerator[r][c];

                if (valueko != null)
                    cellko.setCellValue(valueko);
            }
        }
        String koreultsPath = System.getProperty("user.dir") + "//src/test/java/FileOutput/KO_Test_BolloTargaPeriodoTributario_" + formatedDateTime + "." + "xlsx";
        FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
        workbookKO.write(outkoresults);

        outkoresults.close();

        System.out.println("KO_Test file written succesfully ...");

        driver.close();

    }
}



