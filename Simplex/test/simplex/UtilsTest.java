/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
     * Test of somaDeValores method, of class Utils.
     */
    @Test
    public void testSomaDeValores() {
        System.out.println("somaDeValores");
        int a = 1;
        int b = 1;
        int expResult = 2;
        int result = Utils.somaDeValores(a, b);
        assertEquals(expResult, result);
    }
    
}
