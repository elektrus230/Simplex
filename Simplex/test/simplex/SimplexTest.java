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
 * @author Geral
 */
public class SimplexTest {
    
    public SimplexTest() {
    }

    /**
     * Test of main method, of class Simplex.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Simplex.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executarSimplex method, of class Simplex.
     */
    @Test
    public void testExecutarSimplex() {
        System.out.println("executarSimplex");
        Simplex.executarSimplex();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontraNumPivot method, of class Simplex.
     */
    @Test
    public void testEncontraNumPivot() {
        System.out.println("encontraNumPivot");
        int[] expResult = null;
        int[] result = Simplex.encontraNumPivot();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontraColunaPivot method, of class Simplex.
     */
    @Test
    public void testEncontraColunaPivot() {
        System.out.println("encontraColunaPivot");
        double[] primeiraLinha = null;
        int expResult = 0;
        int result = Simplex.encontraColunaPivot(primeiraLinha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criaColunaRestricoes method, of class Simplex.
     */
    @Test
    public void testCriaColunaRestricoes() {
        System.out.println("criaColunaRestricoes");
        int indiceColuna = 0;
        double[] expResult = null;
        double[] result = Simplex.criaColunaRestricoes(indiceColuna);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encontraLinhaPivot method, of class Simplex.
     */
    @Test
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
        double[] colunaPivotRestricoes = null;
        double[] ultimaColunaRestricoes = null;
        double[] expResult = null;
        double[] result = Simplex.calculaQuocienteColunas(colunaPivotRestricoes, ultimaColunaRestricoes);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndiceLinhaPivot method, of class Simplex.
     */
    @Test
    public void testGetIndiceLinhaPivot() {
        System.out.println("getIndiceLinhaPivot");
        double[] quocienteColunas = null;
        int expResult = 0;
        int result = Simplex.getIndiceLinhaPivot(quocienteColunas);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of passarLinhaPivotParaUm method, of class Simplex.
     */
    @Test
    public void testPassarLinhaPivotParaUm() {
        System.out.println("passarLinhaPivotParaUm");
        int[] indicesDoPivot = null;
        Simplex.passarLinhaPivotParaUm(indicesDoPivot);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of zerarElementosDaColunaPivot method, of class Simplex.
     */
    @Test
    public void testZerarElementosDaColunaPivot() {
        System.out.println("zerarElementosDaColunaPivot");
        int[] indicesDoPivot = null;
        int linha = 0;
        Simplex.zerarElementosDaColunaPivot(indicesDoPivot, linha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarMatrizInicial method, of class Simplex.
     */
    @Test
    public void testCriarMatrizInicial() {
        System.out.println("criarMatrizInicial");
        double[][] expResult = null;
        double[][] result = Simplex.criarMatrizInicial();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of imprimirMatrizSimplexNova method, of class Simplex.
     */
    @Test
    public void testImprimirMatrizSimplexNova() {
        System.out.println("imprimirMatrizSimplexNova");
        Simplex.imprimirMatrizSimplexNova();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
