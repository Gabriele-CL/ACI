package com.aci.test.ACI;

import com.aci.test.POM.Gestione_Anta;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class ACI_Anta_Esenzioni {

    @Test
    public void ACI_Anta_Esenzioni() throws Exception {
        Gestione_Anta anta = new Gestione_Anta();
        LeggiFlussoExcel excelFlusso = new LeggiFlussoExcel();
        System.out.println("Generiamo un nuovo flusso");
        anta.generaFileConProgressivoContinuo();

        //System.out.println("Verichiamo che il flusso sia elaborato");
        //anta.ControlloEsiti();

        System.out.println("Verichiamo Data Fine esenzione da DB");
        anta.ControlloDataFineEsenzioneDaFile("C:/Users/xclt03/Desktop/Test_Anta/ATPABAZ.D0013022.T000007");

        System.out.println("Verichiamo Data Fine esenzione da Excel");
        excelFlusso.LeggiFlussoExcel();
    }
}

