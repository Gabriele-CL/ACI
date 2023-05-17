package com.aci.test;

import com.aci.POM.CalcoloTariffePage;
import com.aci.POM.Login;
import com.aci.POM.inserimentoParametri;
import com.aci.utils.ExcelUtils_CalcoloTariffa;
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
import org.testng.util.Strings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.aci.utils.PropertiesFile.getUrl;

public class ACI_Calcolo_Tariffe_nstar_Test {

    public WebDriver driver;

    public int counter;
    public int counterko;

    public String[][] excelgenerator;

    public String[][] kogenerator;

    Login objLogin;
    CalcoloTariffePage objCalTariffe;

    inserimentoParametri objInsParametri;


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
        public void beforetest () throws InterruptedException, IOException {
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

            //Click sulla Pagina Calcolo Tariffe
            objCalTariffe = new CalcoloTariffePage(driver);
            objCalTariffe.clickOnDropMenu();
            objCalTariffe.clickOnOption();
            String strUrl = driver.getCurrentUrl();


            //Compilazione dei file Excel per AciResults
            String codicetest = "CODICE TEST";
            String codiceclasse = "CODICE CLASSE";
            String esitotest = "ESITO TEST";

            resultList.add(esitotest);
            codtest.add(codicetest);
            codClasse.add(codiceclasse);

            //Compilazione dei file Excel per KO_Results
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


        @Test(dataProviderClass = ExcelUtils_CalcoloTariffa.class, dataProvider = "ACIWorksheet")

        //dati datapool da inserire nell'applicativo
        public void Tariffario_NSTAR_2022_Umbria (String Cod_Classe, String Cod_Test, String Regione, String DataValidità, String Classe,
                String Uso, String Specialità, String CtarGProva, String Euro, String DataCostruzione,
                String CIntegrazioneComplessi, String Alim, String KW, String HP, String CV, String Potenza,
                String Cilindrata, String Ecologico, String IstallazioneImpianto, String DataIstallazioneImpianto,
                String Portata, String Peso, String AssiMotrici, String AssiRimorchio, String SospensioniPneumatiche,
                String GancioTraino, String PesoRimorchio, String dodici) throws InterruptedException {

            /* crezione liste nell'excel*/
            codClasse.add(Cod_Classe);
            codtest.add(Cod_Test);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

            /* Inserimento Parametri */
            objInsParametri = new inserimentoParametri(driver);


            /*Inserimento Categoria */
            Thread.sleep(500);

            objInsParametri.selectCategoria(Classe);
            Thread.sleep(500);

            /* Data Valadità */
            Thread.sleep(1000);
            objInsParametri.selectDataValidita(DataValidità);

            /* Data Immatricolazione */
            Thread.sleep(1000);
            objInsParametri.selectImmatricolazione(DataCostruzione);

            /* Mesi */
            objInsParametri.selectMesi();

            /*Uso */
            objInsParametri.selectUso(Uso);
            Thread.sleep(500);

            /* Inserimento Specialità */
            if ((Strings.isNullOrEmpty(Specialità))) {
                System.out.println("Nothing to add");

            } else if ((!Strings.isNullOrEmpty(Specialità))) {
                Thread.sleep(1000);
                objInsParametri.selectSpecialità(Specialità);
            }

            /* Alimentazione */
            objInsParametri.selectAlimentazione(Alim);
            Thread.sleep(500);

            /* Euro */
            objInsParametri.selectEuro(Euro);

            /* Inserimento Portata*/
            if ((Strings.isNullOrEmpty(Portata))) {
                System.out.println("Nothing to add");

            } else if ((!Strings.isNullOrEmpty(Portata))) {
                Thread.sleep(1000);
                objInsParametri.selectPortata(Portata);

            }

            /* Iserimento Peso*/

            if ((Strings.isNullOrEmpty(Peso))) {
                System.out.println("Nothing to add");

            } else if ((!Strings.isNullOrEmpty(Peso))) {
                Thread.sleep(1000);
                objInsParametri.selectPeso(Peso);
            }

            /* Inserimento AssiMotrici*/

            if ((Strings.isNullOrEmpty(AssiMotrici))) {
                System.out.println("Nothing to add");

            } else if ((!Strings.isNullOrEmpty(AssiMotrici))) {
                Thread.sleep(1000);
                objInsParametri.selectAssiMotrici(AssiMotrici);
            }

            /* Inserimento Peso Rimorchio*/

            if ((Strings.isNullOrEmpty(PesoRimorchio))) {
                System.out.println("Nothing to add");

            } else if ((!Strings.isNullOrEmpty(PesoRimorchio))) {
                Thread.sleep(1000);
                objInsParametri.selectPesoRimorchio(PesoRimorchio);

            }

            /* Sospensioni Pneumatiche*/

            if (SospensioniPneumatiche.contains("NO")) {
                System.out.println("it did not click on check box ");

            } else if (SospensioniPneumatiche.contains("SI")) {
                Thread.sleep(1000);
                objInsParametri.selectSospensioniPneumatiche();
            } else if ((Strings.isNullOrEmpty(SospensioniPneumatiche))) {
                System.out.println("it did not click on check box ");
            }

            /* Gancio Traino*/

            if ((Strings.isNullOrEmpty(GancioTraino))) {
                System.out.println("it did not click on check box ");

            } else if ((!Strings.isNullOrEmpty(GancioTraino))) {
                Thread.sleep(1000);
                objInsParametri.selectGancioTraino();
            }

            /* Potenza */
            if ((Strings.isNullOrEmpty(Potenza))) {
                objInsParametri.selectCilindrata(Cilindrata);
            } else if ((!Strings.isNullOrEmpty(Potenza)))
                objInsParametri.selectPotenza(Potenza);

            /*Cerca*/
            objCalTariffe.clickOnCerca();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            /* Cancella Filtri*/
            Thread.sleep(100);
            objCalTariffe.cancellafiltri();
            String enteredText = objCalTariffe.getMensilita();

            System.out.println(enteredText);
            String finalEnteredText = enteredText.replace("€", "").replace(".", ",").trim();



            /* Inserimento risultati dei test nell'excel*/

            if (finalEnteredText.equals(dodici)) {

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
                datacostruzione.add(DataCostruzione);
                alim.add(Alim);
                potenza0.add(Potenza);
                cilindrata.add(Cilindrata);
                portata.add(Portata);
                peso.add(Peso);
                assimotrici.add(AssiMotrici);
                sospensione.add(SospensioniPneumatiche);
                ganciotraino.add(GancioTraino);
                pesorimorchio.add(PesoRimorchio);
                risulatoattuale.add(finalEnteredText); //output effettivo
                risultatoattesoko.add(dodici);
            }


            counter++;

        }
        public String[][] contenitore () {
            return excelgenerator;
        }


        @AfterTest
        public void array () {

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
                String asd = resultList.get(i);
                excelgenerator[i][2] = asd;
                //   System.out.println(asd);
            }



            /*Ko results*/


            for (int i = 0; i <= counterko; i++) {
                String ksd = codtestko.get(i);
                kogenerator[i][0] = ksd;

            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = CodClasseko.get(i);
                kogenerator[i][1] = ksd;

            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = datavalidita.get(i);
                kogenerator[i][2] = ksd;
                //  System.out.println(asd);
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = uso0.get(i);
                kogenerator[i][3] = ksd;
                //   System.out.println(asd);
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = specialità.get(i);
                kogenerator[i][4] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = euro.get(i);
                kogenerator[i][5] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = datacostruzione.get(i);
                kogenerator[i][6] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = alim.get(i);
                kogenerator[i][7] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = potenza0.get(i);
                kogenerator[i][8] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = cilindrata.get(i);
                kogenerator[i][9] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = portata.get(i);
                kogenerator[i][10] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = peso.get(i);
                kogenerator[i][11] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = assimotrici.get(i);
                kogenerator[i][12] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = sospensione.get(i);
                kogenerator[i][13] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = ganciotraino.get(i);
                kogenerator[i][14] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = pesorimorchio.get(i);
                kogenerator[i][15] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = risulatoattuale.get(i);
                kogenerator[i][16] = ksd;
            }

            for (int i = 0; i <= counterko; i++) {
                String ksd = risultatoattesoko.get(i);
                kogenerator[i][17] = ksd;
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

            String filePath = System.getProperty("user.dir") + "//src/test/java/FileOutput/ACIResults_CalcoloTariffe_" + formatedDateTime + ".xlsx";
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
            String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/KO_Test_CalcolTariffe_" + formatedDateTime + "." + "xlsx";
            FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
            workbookKO.write(outkoresults);

            outkoresults.close();

            System.out.println("KO_Test file written succesfully ...");

            driver.close();

        }
    }


