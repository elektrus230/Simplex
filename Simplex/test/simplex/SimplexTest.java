/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author André
 */
public class SimplexTest {

    /**
     * Test of encontraColunaPivot method, of class Simplex.
     */
    //@Test
    @Test
    public void testEncontraColunaPivot() {
        System.out.println("encontraColunaPivot");
        double[] Linha = {-3, -5, 5, -10, 0, 0};
        int expResult = 3;
        int result = Simplex.encontraColunaPivot(Linha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of encontraLinhaPivot method, of class Simplex.
     */
    @Test
    public void testEncontraLinhaPivot() {
        System.out.println("encontraLinhaPivot");
        double[] colunaPivotRestricoes = {10,4,6,8,12};
        double[] ultimaColunaRestricoes = {2,2,2,2,2};
        int expResult = 5;
        int result = Simplex.encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        assertEquals(expResult, result);

    }

}