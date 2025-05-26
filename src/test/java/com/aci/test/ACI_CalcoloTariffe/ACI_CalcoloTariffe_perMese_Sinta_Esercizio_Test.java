package com.aci.test.ACI_CalcoloTariffe;


import com.aci.test.POM.Login_Sinta;
import com.aci.test.POM.Sinta_CalcoloTariffePage;
import com.aci.utils.ExcelUtils_CalcoloTariffa_Sinta;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ACI_CalcoloTariffe_perMese_Sinta_Esercizio_Test {

    public ChromeDriver driver;

    public WebElement element;
    public int counter;
    public int counterko;

    public String[][] excelgenerator;

    public String[][] kogenerator;
    Login_Sinta objLoginSinta;
    Sinta_CalcoloTariffePage objSintaPage;

    /**
     * DICHIARAZIONE STRINGHE
     */
    public List<String> resultList = new ArrayList<>();
    public List<String> codClasse = new ArrayList<>();
    public List<String> codtest = new ArrayList<>();
    public List<String> codtestko = new ArrayList<>();
    public List<String> CodClasseko = new ArrayList<>();
    public List<String> datavaliditako = new ArrayList<>();
    public List<String> classeko = new ArrayList<>();
    public List<String> usoko = new ArrayList<>();
    public List<String> specialitàko = new ArrayList<>();
    public List<String> euroko = new ArrayList<>();
    public List<String> datacostruzioneko = new ArrayList<>();
    public List<String> alimko = new ArrayList<>();
    public List<String> potenzako = new ArrayList<>();
    public List<String> cilindratako = new ArrayList<>();
    public List<String> portatako = new ArrayList<>();
    public List<String> pesoko = new ArrayList<>();
    public List<String> assimotriciko = new ArrayList<>();
    public List<String> sospensioneko = new ArrayList<>();
    public List<String> ganciotrainoko = new ArrayList<>();
    public List<String> pesorimorchioko = new ArrayList<>();
    public List<String> risultatoatteso12 = new ArrayList<>();
    public List<String> risultatoatteso1 = new ArrayList<>();
    public List<String> risultatoatteso2 = new ArrayList<>();
    public List<String> risultatoatteso3 = new ArrayList<>();
    public List<String> risultatoatteso4 = new ArrayList<>();
    public List<String> risultatoatteso5 = new ArrayList<>();
    public List<String> risultatoatteso6 = new ArrayList<>();
    public List<String> risultatoatteso7 = new ArrayList<>();
    public List<String> risultatoatteso8 = new ArrayList<>();
    public List<String> risultatoatteso9 = new ArrayList<>();
    public List<String> risultatoatteso10 = new ArrayList<>();
    public List<String> risultatoatteso11 = new ArrayList<>();

    @BeforeTest
    public void beforetest() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--window-size=1920,1200");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://portale3.serviziaci.it/titano-sso/");


        //Login
        objLoginSinta = new Login_Sinta(driver);
        objSintaPage = new Sinta_CalcoloTariffePage(driver);
        Thread.sleep(10000);
        objLoginSinta.enterCrendentials2();
        Thread.sleep(1000);

        objSintaPage.setArchivioTribu();
        objSintaPage.setCalcolTariffa();

//Compilazione dei file Excel per AciResults
        String codicetest = "Cod_Test";
        String codiceclasse = "Cod_Classe";
        String esitotest = "Esito Test";

        resultList.add(esitotest);
        codtest.add(codicetest);
        codClasse.add(codiceclasse);

        //Compilazione dei file Excel per KO_Results
        String codicetestko = "Cod_Test";
        String codiceclasseko = "Cod_Classe";
        String datavalidità = "Data Validita";
        String classe = "Classe";
        String uso = "Uso";
        String Specialità = "Specialita";
        String Euro = "Euro";
        String DataCostruzione = "DataCostruzione";
        String Alimentazione = "Alim";
        String Potenza = "Potenza";
        String Cilindrata = "Cilindrata";
        String Portata = "Portata";
        String Peso = "Peso";
        String AssiMotrici = "AssiMotrice";
        String Sospensione = "SospensioniPneumatiche";
        String GancioTraino = "GncioTraino";
        String PesoRimorchio = "PesoRimorchio";
        String RisultatoAtteso1 = "1";
        String RisultatoAtteso2 = "2";
        String RisultatoAtteso3 = "3";
        String RisultatoAtteso4 = "4";
        String RisultatoAtteso5 = "5";
        String RisultatoAtteso6 = "6";
        String RisultatoAtteso7 = "7";
        String RisultatoAtteso8 = "8";
        String RisultatoAtteso9 = "9";
        String RisultatoAtteso10 = "10";
        String RisultatoAtteso11 = "11";
        String RisultatoAtteso12 = "12";

        codtestko.add(codicetestko);
        CodClasseko.add(codiceclasseko);
        datavaliditako.add(datavalidità);
        classeko.add(classe);
        usoko.add(uso);
        specialitàko.add(Specialità);
        euroko.add(Euro);
        datacostruzioneko.add(DataCostruzione);
        alimko.add(Alimentazione);
        potenzako.add(Potenza);
        cilindratako.add(Cilindrata);
        portatako.add(Portata);
        pesoko.add(Peso);
        assimotriciko.add(AssiMotrici);
        sospensioneko.add(Sospensione);
        ganciotrainoko.add(GancioTraino);
        pesorimorchioko.add(PesoRimorchio);
        risultatoatteso1.add(RisultatoAtteso1);
        risultatoatteso2.add(RisultatoAtteso2);
        risultatoatteso3.add(RisultatoAtteso3);
        risultatoatteso4.add(RisultatoAtteso4);
        risultatoatteso5.add(RisultatoAtteso5);
        risultatoatteso6.add(RisultatoAtteso6);
        risultatoatteso7.add(RisultatoAtteso7);
        risultatoatteso8.add(RisultatoAtteso8);
        risultatoatteso9.add(RisultatoAtteso9);
        risultatoatteso10.add(RisultatoAtteso10);
        risultatoatteso11.add(RisultatoAtteso11);
        risultatoatteso12.add(RisultatoAtteso12);

    }

    @Test(dataProviderClass = ExcelUtils_CalcoloTariffa_Sinta.class, dataProvider = "ACIWorksheet")

    //dati datapool da inserire nell'applicativo
    public void Foglio1(String Cod_Classe, String Cod_Test, String Regione, String DataValidità, String Classe,
                        String Uso, String Specialità, String CtarGProva, String Euro, String DataCostruzione,
                        String CIntegrazioneComplessi, String Alim, String KW, String HP, String CV, String Potenza,
                        String Cilindrata, String Ecologico, String IstallazioneImpianto, String DataIstallazioneImpianto,
                        String Portata, String Peso, String AssiMotrici, String AssiRimorchio, String SospensioniPneumatiche,
                        String GancioTraino, String PesoRimorchio, String dodici) throws InterruptedException {
        /* crezione liste nell'excel*/
        codClasse.add(Cod_Classe);
        codtest.add(Cod_Test);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        objSintaPage.setRegione(Regione);
        objSintaPage.setCategoria(Classe);

        if ((Strings.isNullOrEmpty(Uso))) {
            System.out.println("Nothing to add");
        } else if ((!Strings.isNullOrEmpty(Uso))) {
            objSintaPage.setUso(Uso);
            Thread.sleep(500);
        }

        String data = DataValidità;
        System.out.println(data);
        String[] partiData = data.split("/");

        String giorno = partiData[0];
        System.out.println(giorno);
        String mese = partiData[1];
        System.out.println(mese);
        String anno = partiData[2];
        System.out.println(anno);

        objSintaPage.setGiorno(giorno);
        objSintaPage.setMese(mese);
        objSintaPage.setAnno(anno);
        objSintaPage.setEuro(Euro);

        String data2 = DataCostruzione;
        System.out.println(data2);
        String[] partiData2 = data.split("/");

        String giorno2 = partiData2[0];
        System.out.println(giorno2);
        String mese2 = partiData2[1];
        System.out.println(mese2);
        String anno2 = partiData2[2];
        System.out.println(anno2);

        objSintaPage.setGiorno2(giorno2);
        objSintaPage.setMese2(mese2);
        objSintaPage.setAnno2(anno2);
        //objSintaPage.setMesi();

        /* Inserimento Specialità */
        if ((Strings.isNullOrEmpty(Specialità))) {
            System.out.println("Nothing to add");

        } else if ((!Strings.isNullOrEmpty(Specialità))) {
            Thread.sleep(4000);
            objSintaPage.selectSpecialità(Specialità);
        }


        if ((Strings.isNullOrEmpty(Alim))) {
            System.out.println("Nothing to add");
        } else if ((!Strings.isNullOrEmpty(Alim))) {
            objSintaPage.selectAlimentazione(Alim);
            Thread.sleep(500);
        }


        /* Inserimento Portata*/
        if ((Strings.isNullOrEmpty(Portata))) {
            System.out.println("Nothing to add");

        } else if ((!Strings.isNullOrEmpty(Portata))) {
            Thread.sleep(1000);
            objSintaPage.selectPortata(Portata);

        }

        if ((Strings.isNullOrEmpty(Peso))) {
            System.out.println("Nothing to add");

        } else if ((!Strings.isNullOrEmpty(Peso))) {
            Thread.sleep(1000);
            objSintaPage.selectPeso(Peso);
        }

        /* Inserimento AssiMotrici*/

        if ((Strings.isNullOrEmpty(AssiMotrici))) {
            System.out.println("Nothing to add");

        } else if ((!Strings.isNullOrEmpty(AssiMotrici))) {
            Thread.sleep(1000);
            objSintaPage.selectAssiMotrici(AssiMotrici);
        }

        /* Inserimento Peso Rimorchio*/

        if ((Strings.isNullOrEmpty(PesoRimorchio))) {
            System.out.println("Nothing to add");

        } else if ((!Strings.isNullOrEmpty(PesoRimorchio))) {
            Thread.sleep(1000);
            objSintaPage.selectPesoRimorchio(PesoRimorchio);

        }

        /* Sospensioni Pneumatiche*/

        if (SospensioniPneumatiche.contains("NO")) {
            System.out.println("it did not click on check box ");

        } else if (SospensioniPneumatiche.contains("SI")) {
            Thread.sleep(1000);
            objSintaPage.selectSospensioniPneumatiche();
        } else if ((Strings.isNullOrEmpty(SospensioniPneumatiche))) {
            System.out.println("it did not click on check box ");
        }

        /* Gancio Traino*/

        if ((Strings.isNullOrEmpty(GancioTraino))) {
            System.out.println("it did not click on check box ");

        } else if ((!Strings.isNullOrEmpty(GancioTraino))) {
            Thread.sleep(1000);
            objSintaPage.selectGancioTraino();
        }

        /* Potenza */
        if ((Strings.isNullOrEmpty(Potenza))) {
            objSintaPage.selectCilindrata(Cilindrata);
        } else if ((!Strings.isNullOrEmpty(Potenza)))
            objSintaPage.selectPotenza(Potenza);

        objSintaPage.setInvio();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        String unMese = objSintaPage.setUnMesi().trim();
        String dueMesi = objSintaPage.setDueMesi().trim();
        String treMesi = objSintaPage.setTreMesi().trim();
        String quattroMesi = objSintaPage.setQuattroMesi().trim();
        String cinqueMesi = objSintaPage.setCinqueMesi().trim();
        String seiMesi = objSintaPage.setSeiMesi().trim();
        String setteMesi = objSintaPage.setSetteMesi().trim();
        String ottoMesi = objSintaPage.setOttoMesi().trim();
        String noveMesi = objSintaPage.setNoveMesi().trim();
        String dieciMesi = objSintaPage.setDieciMesi().trim();
        String undiciMesi = objSintaPage.setUndiciMesi().trim();
        String dodiciMesi = objSintaPage.setDodiciMesi().trim();

        objSintaPage.setCalcolTariffa();

        /* Inserimento risultati dei test nell'excel*/

        if (dodiciMesi.equals(dodici)) {

            String str = "Pass";
            counterko++;
            resultList.add(str);
            codtestko.add(Cod_Test);
            CodClasseko.add(Cod_Classe);
            datavaliditako.add(DataValidità);
            classeko.add(Classe);
            usoko.add(Uso);
            specialitàko.add(Specialità);
            euroko.add(Euro);
            datacostruzioneko.add(DataCostruzione);
            alimko.add(Alim);
            potenzako.add(Potenza);
            cilindratako.add(Cilindrata);
            portatako.add(Portata);
            pesoko.add(Peso);
            assimotriciko.add(AssiMotrici);
            sospensioneko.add(SospensioniPneumatiche);
            ganciotrainoko.add(GancioTraino);
            pesorimorchioko.add(PesoRimorchio);
            risultatoatteso1.add(unMese);
            risultatoatteso2.add(dueMesi);
            risultatoatteso3.add(treMesi);
            risultatoatteso4.add(quattroMesi);
            risultatoatteso5.add(cinqueMesi);
            risultatoatteso6.add(seiMesi);
            risultatoatteso7.add(setteMesi);
            risultatoatteso8.add(ottoMesi);
            risultatoatteso9.add(noveMesi);
            risultatoatteso10.add(dieciMesi);
            risultatoatteso11.add(undiciMesi);
            risultatoatteso12.add(dodiciMesi);



        } else {

            String str = "Fail";
            counterko++;
            resultList.add(str);
            codtestko.add(Cod_Test);
            CodClasseko.add(Cod_Classe);
            datavaliditako.add(DataValidità);
            classeko.add(Classe);
            usoko.add(Uso);
            specialitàko.add(Specialità);
            euroko.add(Euro);
            datacostruzioneko.add(DataCostruzione);
            alimko.add(Alim);
            potenzako.add(Potenza);
            cilindratako.add(Cilindrata);
            portatako.add(Portata);
            pesoko.add(Peso);
            assimotriciko.add(AssiMotrici);
            sospensioneko.add(SospensioniPneumatiche);
            ganciotrainoko.add(GancioTraino);
            pesorimorchioko.add(PesoRimorchio);
            risultatoatteso1.add(unMese);
            risultatoatteso2.add(dueMesi);
            risultatoatteso3.add(treMesi);
            risultatoatteso4.add(quattroMesi);
            risultatoatteso5.add(cinqueMesi);
            risultatoatteso6.add(seiMesi);
            risultatoatteso7.add(setteMesi);
            risultatoatteso8.add(ottoMesi);
            risultatoatteso9.add(noveMesi);
            risultatoatteso10.add(dieciMesi);
            risultatoatteso11.add(undiciMesi);
            risultatoatteso12.add(dodiciMesi);
        }


        counter++;

    }
    public String[][] contenitore () {
        return excelgenerator;
    }

    @AfterTest
    public void array () {

        /* inserimento e dichiarazione della posizione nelle celle dei dati di test*/


        System.out.println("Il conteggio è" + "" + counter);
        this.kogenerator = new String[counterko + 1][40];

        /*Ko results*/

        for (int i = 0; i <= counterko; i++) {
            String ksd = codtestko.get(i);
            kogenerator[i][0] = ksd;

        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = CodClasseko.get(i);
            kogenerator[i][1] = ksd;

        }

        for(int i = 0; i <= counterko; i++){
            String ksd = resultList.get(i);
            kogenerator[i][2] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = datavaliditako.get(i);
            kogenerator[i][3] = ksd;
            //  System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = classeko.get(i);
            kogenerator[i][4] = ksd;
            //   System.out.println(asd);
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = usoko.get(i);
            kogenerator[i][5] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = specialitàko.get(i);
            kogenerator[i][6] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = euroko.get(i);
            kogenerator[i][7] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = datacostruzioneko.get(i);
            kogenerator[i][8] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = alimko.get(i);
            kogenerator[i][9] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = potenzako.get(i);
            kogenerator[i][10] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = cilindratako.get(i);
            kogenerator[i][11] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = portatako.get(i);
            kogenerator[i][12] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = pesoko.get(i);
            kogenerator[i][13] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = assimotriciko.get(i);
            kogenerator[i][14] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = sospensioneko.get(i);
            kogenerator[i][15] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = ganciotrainoko.get(i);
            kogenerator[i][16] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = pesorimorchioko.get(i);
            kogenerator[i][17] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso1.get(i);
            kogenerator[i][18] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso2.get(i);
            kogenerator[i][19] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso3.get(i);
            kogenerator[i][20] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso4.get(i);
            kogenerator[i][21] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso5.get(i);
            kogenerator[i][22] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso6.get(i);
            kogenerator[i][23] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso7.get(i);
            kogenerator[i][24] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso8.get(i);
            kogenerator[i][25] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso9.get(i);
            kogenerator[i][26] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso10.get(i);
            kogenerator[i][27] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso11.get(i);
            kogenerator[i][28] = ksd;
        }

        for (int i = 0; i <= counterko; i++) {
            String ksd = risultatoatteso12.get(i);
            kogenerator[i][29] = ksd;
        }


    }


    @AfterSuite

    public void printexcel () throws IOException {

        // Formattazione per aggiungere Data e Ora al nome dei due file Excel

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm");
        String formatedDateTime = current.format(format);

        /* Creazione Ko results*/


        @SuppressWarnings("resource")
        XSSFWorkbook workbookKO = new XSSFWorkbook();
        XSSFSheet sheetKO = workbookKO.createSheet("KO_risultati");

        //using for loop
        int rowsko = kogenerator.length;
        System.out.println("le righe sono" + "" + rowsko);
        int colsko = kogenerator[0].length;
        System.out.println("le colonne sono" + ""+ colsko);

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
        String koreultsPath = System.getProperty("user.dir") + "//src/test/java/com/aci/fileOutput/Test_CalcolTariffe_Titano_Bolzano_Nuovo_" + formatedDateTime + "." + "xlsx";
        FileOutputStream outkoresults = new FileOutputStream(koreultsPath);
        workbookKO.write(outkoresults);
        workbookKO.write(outkoresults);

        outkoresults.close();

        System.out.println("KO_Test file written succesfully ...");

        driver.close();
    }
}

