/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class DataProcessingTest {
    
    public DataProcessingTest() {
    }

    /**
     * Test of getVariaveisDaPrimeiraLinha method, of class DataProcessing.
     */
    @Test
    public void testGetVariaveisDaPrimeiraLinha() {
        System.out.println("getVariaveisDaPrimeiraLinha");
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
        String linha = "";
        String[][] expResult = null;
        String[][] result = DataProcessing.getVariaveisDaPrimeiraLinha(linha);
        assertArrayEquals(expResult, result);
        
    }

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
    
    
    /**
     * Test of extrairVariavel method, of class DataProcessing.
     */
    @Test
    public void testExtrairVariavel() {
        System.out.println("extrairVariavel");
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
        int charIndex = 0;
        String linha = "";
        String[][] variaveisEncontradas = null;
        int expResult = 0;
        int result = DataProcessing.extrairVariavel(charIndex, linha, variaveisEncontradas);
        assertEquals(expResult, result);
    }

    //<editor-fold defaultstate="" desc="">
    
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
    

   
    
}
