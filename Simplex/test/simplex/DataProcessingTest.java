/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class DataProcessingTest {
    
    public DataProcessingTest() {
    }

    //<editor-fold defaultstate="collapsed" desc="E Uma Letra">
    
    /**
     * Test of eInicioDeUmaVariavel method, of class DataProcessing.
     */
    public void testEUmaLetra() {
        System.out.println("eInicioDeUmaVariavel");
        boolean expResult = true;
        char caracter = 'X';
        boolean result = DataProcessing.eUmaLetra(caracter);
        assertEquals(expResult,result);
    }
    
    /**
     * Test of eInicioDeUmaVariavel method, of class DataProcessing.
     */
    public void testEUmaLetraF() {
        System.out.println("eInicioDeUmaVariavel");
        boolean expResult = false;
        char caracter = ' ';
        boolean result = DataProcessing.eUmaLetra(caracter);
        assertEquals(expResult,result);
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Depois De Um Igual">
    
    /**
     * Test of depoisDeUmIgual method, of class DataProcessing.
     */
    @Test
    public void testDepoisDeUmIgual() {
        System.out.println("depoisDeUmIgual");
        String linha = "Z = 3X1 + 5X2";
        int charIndex = 3;
        boolean expResult = true;
        boolean result = DataProcessing.depoisDeUmIgual(linha, charIndex);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDepoisDeUmIgualF() {
        System.out.println("depoisDeUmIgual");
        String linha = "Z = 3X1 + 5X2";
        int charIndex = 1;
        boolean expResult = false;
        boolean result = DataProcessing.depoisDeUmIgual(linha, charIndex);
        assertEquals(expResult, result);
       
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Nome da Variavel Terminou">
    
    /**
     * Test of nomeDaVariavelTerminou method, of class DataProcessing.
     */
    @Test
    public void testNomeDaVariavelTerminou() {
        System.out.println("nomeDaVariavelTerminou");
        char caracter = ' ';
        boolean expResult = true;
        boolean result = DataProcessing.nomeDaVariavelTerminou(caracter);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNomeDaVariavelTerminouOperador() {
        System.out.println("nomeDaVariavelTerminou");
        char caracter = '=';
        boolean expResult = true;
        boolean result = DataProcessing.nomeDaVariavelTerminou(caracter);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testNomeDaVariavelTerminouNumero() {
        System.out.println("nomeDaVariavelTerminou");
        char caracter = '5';
        boolean expResult = false;
        boolean result = DataProcessing.nomeDaVariavelTerminou(caracter);
        assertEquals(expResult, result);
    }
    
    //</editor-fold>

    /**
     * Test of extrairValorDaVariavel method, of class DataProcessing.
     */
    @Test
    public void testExtrairValorDaVariavel() {
        System.out.println("extrairValorDaVariavel");
        int charIndex = 5;
        String linha = "Z = 3X1 + 5X2";
        String[] expResult = {"X2","3","+"};
        String[][] variaveisEncontradas = 
        { 
            {"X1","",""},
            {"X2","",""}    
        };
        
        DataProcessing.extrairValorDaVariavel(charIndex, linha, variaveisEncontradas);
        assertArrayEquals(expResult, variaveisEncontradas[1]);
    }

    /**
     * Test of extrairNomeDaVariavel method, of class DataProcessing.
     */
    @Test
    public void testExtrairNomeDaVariavel() {
        System.out.println("extrairNomeDaVariavel");
        int charIndex = 5;
        String linha = "Z = 3X1 + 5X2";
        String[][] variaveisEncontradas = new String[1][3];
        int expResult = 6;
        int result = DataProcessing.extrairNomeDaVariavel(charIndex, linha, variaveisEncontradas);
        assertEquals(expResult, result);
    }

    /**
     * Test of getVariaveisDaPrimeiraLinha method, of class DataProcessing.
     */
    @Test
    public void testGetVariaveisDaPrimeiraLinha() {
        System.out.println("getVariaveisDaPrimeiraLinha");
        String linha = "1X1 + 5X2 <= 5";
        String[][] expResult = 
        { 
            {"X1","1","+"},
            {"X2","5","+"}    
        };
        String[][] result = DataProcessing.getVariaveisDaPrimeiraLinha(linha);
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testValorPrimeiraLinha() {
        System.out.println("ValorPrimeiraLinha");
        int numeroDeColunas = 2;
        String[][] variaveisDaPrimeiraLinha = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
    
        double[] expResult = {-3.00,-5.00};
        double[] result = DataProcessing.ValorPrimeiraLinha(numeroDeColunas, variaveisDaPrimeiraLinha);
        Assert.assertArrayEquals(expResult, result, 0);
        
    }

   
//    /**
//     * Test of preencherSlacks method, of class DataProcessing.
//     */
//    @Test
//    public void testPreencherSlacks() {
//        System.out.println("preencherSlacks");
//        String[][] variaveisDaPrimeiraLinha = null;
//        double[][] matrizInicial = null;
//        for (int i=0;i<matrizInicial.length;i++){
//            for (int j=0; j< matrizInicial[i].length; j++){
//                System.out.println(matrizInicial[i][j]);
//            }
//        }
//        DataProcessing.preencherSlacks(variaveisDaPrimeiraLinha, matrizInicial);
//      
//    }

   
    
}
