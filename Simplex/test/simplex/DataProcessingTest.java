/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class DataProcessingTest {
    
    public DataProcessingTest() {
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
     * Test of getVariaveisDaPrimeiraLinha method, of class DataProcessing.
     */
    @Test
    public void testGetVariaveisDaPrimeiraLinha() {
        
        System.out.println("getVariaveisDaPrimeiraLinha");
        String linha = "Z = 3X1 + 5X2";
        String[] expResult = {"X1","X2"};
        String[] result = DataProcessing.getVariaveisDaPrimeiraLinha(linha);
        assertArrayEquals(expResult, result);
        
    }
    
}
