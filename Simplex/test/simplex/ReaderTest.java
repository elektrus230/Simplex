/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.Arrays;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 *
 * @author Dinis
 */
public class ReaderTest {
    
    public ReaderTest() {
    }

    /**
     * Test of lerFicheiro method, of class Reader.
     */
    @Test
    public void testLerFicheiro() {
        System.out.println("lerFicheiro");
        String caminhoFicheiroInput = "testfiles\\inputA.txt";
        String[] expResult = 
        {
            "z = 3X1 + 5X2",
            "X1 =< 4",
            "2X2 =< 12",
            "3X1 + 2X2 =< 18",
        };
        String[] result = Reader.lerFicheiro(caminhoFicheiroInput);
        System.out.println(Arrays.toString(result));
        assertArrayEquals(expResult, result);
        
    }
    
}