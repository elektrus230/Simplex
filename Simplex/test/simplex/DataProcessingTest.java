/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.Arrays;
import org.junit.Assert;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static simplex.DataProcessing.getValoresLinhasDasRestricoes;
import static simplex.DataProcessing.getVariaveisDaPrimeiraLinha;
import static simplex.DataProcessing.setPrimeiraLinha;

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
        String linha = "Z = 3X1 + 5X2";
        String[][] expResult = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
        String[][] result = DataProcessing.getVariaveisDaPrimeiraLinha(linha);
        assertArrayEquals(expResult, result);

    }

    @Test
    public void testValorPrimeiraLinha() {
        System.out.println("ValorPrimeiraLinha");
        int nColunas = 2;
        String[][] vars = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
    
        double[] expResult = {-3.00,-5.00};
        double[] result = DataProcessing.setPrimeiraLinha(nColunas, vars);
        Assert.assertArrayEquals(expResult, result, 0);
        
    }

    /**
     * Test of getValoresLinhasDasRestricoes method, of class DataProcessing.
     */
    @Test
    public void testGetValoresLinhasDasRestricoes() {
        System.out.println("getValoresLinhasDasRestricoes");
        int nLinhas = 4;
        String[] linhas = 
        {        
            "Z = 3X1 + 5X2",
            "X1 + 0X2 ≤ 4",
            "0X1 + 2X2 ≤ 12",
            "3X1 + 2X2 ≤ 18"
        };
        int nVariaveis = 2;
        int nColunas = nLinhas + nVariaveis + 1;
        String[][] variaveis = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
        double[][] matrizOutput = new double[nLinhas][nColunas];
        DataProcessing.getValoresLinhasDasRestricoes(nLinhas, linhas, nColunas, nVariaveis, variaveis, matrizOutput);
    }

    /**
     * Test of valoresDasVariaveisNasRestricoes method, of class DataProcessing.
     */
    @Test
    public void testValoresDasVariaveisNasRestricoes() {
        System.out.println("valoresDasVariaveisNasRestricoes");
        String[][] variaveis = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
        String linha = "X1 + 0X2 ≤ 4";
        double[] expResult = {1,0};
        double[] result = DataProcessing.getValoresDasVariaveisEmLinhaDeRestricoes(variaveis, linha);
        assertArrayEquals(expResult, result,1);
    }

    /**
     * Test of setPrimeiraLinha method, of class DataProcessing.
     */
    @Test
    public void testSetPrimeiraLinha() {
        System.out.println("setPrimeiraLinha");
        int nColunas = 6;
         String[][] variaveis = 
        { 
            {"X1","3","+"},
            {"X2","5","+"}    
        };
        double[] expResult = {-3,-5,0,0,0,0};
        double[] result = DataProcessing.setPrimeiraLinha(nColunas, variaveis);
        assertArrayEquals(expResult, result,1);
    }  
    
    /**
     * Test of setPrimeiraLinha method, of class DataProcessing.
     */
    @Test
    public void testGetTabelaCompleta() {
        
        System.out.println("BigTest");
        
        String[] linhas = 
        {        
            "Z = 20X1 + 0X2",
            "X1 + 0X2 ≤ 4",
            "0X1 + 55X2 ≤ 12",
            "3X1 + 3X2 ≤ 18"
        };
        
        String[][] variaveis = getVariaveisDaPrimeiraLinha(linhas[0]);
        
        int nLinhas = linhas.length;
        int nColunas = variaveis.length + nLinhas;
        int nVariaveis = variaveis.length;

        double[][] matrizOutput = new double[nLinhas][nColunas];

        matrizOutput[0] = setPrimeiraLinha(nColunas, variaveis);
        
        getValoresLinhasDasRestricoes(nLinhas, linhas, nColunas, nVariaveis, variaveis, matrizOutput);
        
        double[][] result = matrizOutput;
        
        double[][] expResult = 
        {
            {-20,0,0,0,0,0},
            {1,0,1,0,0,4},
            {0,55,0,1,0,12},
            {3,3,0,0,1,18},
        };

        for (double[] result1 : result) {
            System.out.println(Arrays.toString(result1));
        }
        
        
        assertArrayEquals(expResult, result);
    }  
    
   
}
