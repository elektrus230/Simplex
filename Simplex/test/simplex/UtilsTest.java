/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    /**
     * Test of calculaQuocienteColunas method, of class Simplex.
     */
    @Test
    public void testCalculaQuocienteColunas() {
        System.out.println("calculaQuocienteColunas");
        double[] colunaPivot = {1, 2, 3, 4, 5, 0} ;
        double[] ultimaColuna = {3, 4, 6, 24, 0, 10};
        double[] expResult = {3, 2, 2, 6, 0, 0};
        double[] result = Utils.calculaQuocienteColunas(colunaPivot, ultimaColuna);
        assertArrayEquals(expResult, result,1);    
    }

    /**
     * Test of arrayContemValor method, of class Utils.
     */
    @Test
    public void testArrayContemValor() {
        System.out.println("arrayContemValor");
        String val = "X";
        String[] array = {"S","X","L"};
        boolean expResult = true;
        boolean result = Utils.arrayContemValor(val, array);
        assertEquals(expResult, result);
    }

    /**
     * Test of expandirArray method, of class Utils.
     */
    @Test
    public void testExpandirArray() {
        System.out.println("expandirArray");
        String[][] output = new String[2][3];
        String[][] expResult = new String[3][3];
        String[][] result = Utils.expandirArray(output);
        assertArrayEquals(expResult, result);
    }

}
