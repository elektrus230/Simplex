/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author André
 */
public class SimplexTest {

    /**
     * Test of encontraNumPivot method, of class Simplex.
     */
    @Test
   /** public void testEncontraNumPivot() {
        System.out.println("encontraNumPivot");
        double[] primeiraLinha = {-3, -5, 5, -2, 0, 0};
        i expResult = 1;
        int result = Simplex.encontraNumPivot(primeiraLinha);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontraColunaPivot method, of class Simplex.
     */
    //@Test
    public void testEncontraColunaPivot() {
        System.out.println("encontraColunaPivot");
        double[] Linha = {-3, -5, 5, -10, 0, 0};
        int expResult = 3;
        int result = Simplex.encontraColunaPivot(Linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of criaColunaRestricoes method, of class Simplex.
     */
    @Test


    /**
     * Test of encontraLinhaPivot method, of class Simplex.
     */
   
    
    public void testEncontraLinhaPivot() {
        System.out.println("encontraLinhaPivot");
        double[] colunaPivotRestricoes = null;
        double[] ultimaColunaRestricoes = null;
        int expResult = 0;
        int result = Simplex.encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculaQuocienteColunas method, of class Simplex.
     */
    @Test
    public void testCalculaQuocienteColunas() {
        System.out.println("calculaQuocienteColunas");
        double[] colunaPivotRestricoes = {1, 2, 3, 4, 5, 0} ;
        double[] ultimaColunaRestricoes = {3, 4, 6, 24, 0, 10};
        double[] expResult = {3, 2, 2, 6, 0, -10};
        double[] result = Simplex.calculaQuocienteColunas(colunaPivotRestricoes, ultimaColunaRestricoes);
        assertArrayEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

}