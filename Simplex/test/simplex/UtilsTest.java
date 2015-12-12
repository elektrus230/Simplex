/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class UtilsTest {
    
    public UtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of encontarMenorNumEmArray method, of class Utils.
     */
    @Test
    public void testEncontarMenorNumEmArray() {
        System.out.println("encontarMenorNumEmArray");
        double[] array = null;
        int expResult = 0;
        int result = Utils.encontarMenorNumEmArray(array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontrarMenorNumPositivoEmArray method, of class Utils.
     */
    @Test
    public void testEncontrarMenorNumPositivoEmArray() {
        System.out.println("encontrarMenorNumPositivoEmArray");
        double[] array = null;
        int expResult = 0;
        int result = Utils.encontrarMenorNumPositivoEmArray(array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of neutralizarLinha method, of class Utils.
     */
    @Test
    public void testNeutralizarLinha() {
        System.out.println("neutralizarLinha");
        double[] linhaPivot = null;
        double[] linha = null;
        int indexDaColunaPivot = 0;
        double[] expResult = null;
        double[] result = Utils.neutralizarLinha(linhaPivot, linha, indexDaColunaPivot);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of somaDeValores method, of class Utils.
     */
    @Test
    public void testSomaDeValores() {
        System.out.println("somaDeValores");
        int a = 0;
        int b = 0;
        int expResult = 0;
        int result = Utils.somaDeValores(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    /**
     * Test of somaDeValores method, of class Utils.
     */
//    @Test
//    public void testSomaDeValores() {
//        Syste m.out.println("somaDeValores");
//        int a = 1;
//        int b = 1;
//        int expResult = 2;
//        int result = Utils.somaDeValores(a, b);
//        assertEquals(expResult, result);
//    }
//    
}
