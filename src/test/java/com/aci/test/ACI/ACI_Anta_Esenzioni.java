package com.aci.test.ACI;

import com.aci.test.POM.Gestione_Anta;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ACI_Anta_Esenzioni {

    @Test
    public void ACI_Anta_Esenzioni() throws Exception {
        Gestione_Anta anta = new Gestione_Anta();
        LeggiFlussoExcel excelFlusso = new LeggiFlussoExcel();
        //anta.generaFileConProgressivoContinuo();
        //anta.ControlloEsiti();
        //anta.ControlloDataFineEsenzioneDaFile("C:/Users/ugolini/Desktop/Test_Anta/ATPABAZ.D0013022.T000007");
        excelFlusso.LeggiFlussoExcel();
    }
}

