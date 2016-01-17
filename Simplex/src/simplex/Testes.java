/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.Arrays;

/**
 *
 * @author Grupo 9
 */
public class Testes {
    
    public static void main(String[] args) {
        
        Main.TEST_MODE = true;
        
        testeTransporMatriz();
        testeCalcularQuocienteColunas();
        testeAcrescentarColunasDeSlacks();
        testeAumentarArray();
        testeAumentarMatriz();
        testeParseDoubleSeguro();
        testeCriarMatrizComFolgas();
        testeVerificacaoEspacos();
        testeAdicionarSlacks();
        testeEProblemaDeMaximixacao();
        testeValidacaoPrimeiraLinha();
        testeValidacaoLinhaRestricoes();
        testeValidarVariaveisRestricoes();
        testeTransporParaDouble();
    }

    /**
     * Imprime um resultado de um teste
     *
     * @param testName
     * @param result
     */
    public static void printTest(String testName, boolean result) {
        String resultTxt = "Passed";
        if (!result) {
            resultTxt = "Failed";
        }
        System.out.printf("Test %s Result : %s.\n", testName, resultTxt);
    }
    
    public static void testeTransporMatriz() {
        
        double[][] input = new double[][]{
            {1, 4},
            {2, 5},
            {3, 6}
        };
        double[][] output = Utils.transporMatriz(input);
        double[][] expResult = new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        };
        boolean result = true;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if (output[i][j] != expResult[i][j]) {
                    result = false;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        printTest("Transpor matriz.", result);
    }
    
    public static void testeAcrescentarColunasDeSlacks() {
        
        int colunasAAcrescentar = 4;
        double[][] matrizInput = new double[][]{
            {1, 2, 3},
            {4, 5, 6}
        };
        
        double[][] expResult = new double[][]{
            {1, 2, 0, 0, 0, 0, 3},
            {4, 5, 0, 0, 0, 0, 6}
        };
        
        int l = expResult.length;
        int c = expResult[0].length;
        
        double[][] matrizOutput = InputDataProcessing
                .acrescentarColunasDeSlacks(matrizInput, colunasAAcrescentar);
        
        boolean result = true;
        
        if (matrizOutput.length != l || matrizOutput[0].length != c) {
            result = false;
        }
        
        if (result) {
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    if (matrizOutput[i][j] != expResult[i][j]) {
                        result = false;
                        break;
                    }
                }
            }
        }
        
        printTest("Acrescentar coluna : ", result);
    }
    
    public static void testeCalcularQuocienteColunas() {
        
        double[] array1 = new double[]{
            5,
            10,
            3
        };
        
        double[] array2 = new double[]{
            5,
            20,
            15
        };
        double[] expResult = new double[]{
            1.0,
            2.0,
            5
        };
        double[] output = Utils.calculaQuocienteColunas(array1, array2);
        boolean result = true;
        
        for (int i = 0; i < output.length; i++) {
            if (output[i] != expResult[i]) {
                result = false;
                break;
            }
        }
        printTest("testeCalcularQuocienteColunas : ", result);
    }
    
    public static void testeAumentarArray() {
        int cont = 4;
        String[] input = new String[3];
        int expResult = 4;
        String[] output = Utils.aumentarArray(cont, input);
        boolean result = output.length == expResult;
        printTest("testAumentarArray", result);
    }
    
    public static void testeAumentarMatriz() {
        int cont = 4;
        String[][] input = new String[3][3];
        int expResult = 4;
        String[][] output = Utils.aumentaColunasMatriz(cont, input);
        boolean result = output[0].length == expResult;
        printTest("testAumentarArray", result);
    }
    
    public static void testeParseDoubleSeguro() {
        
        boolean result = false;
        String input = "1";
        double expResult = 1.0;
        double output = Utils.parseDoubleSeguro(input);
        result = expResult == output;
        printTest("parseDoubleSeguro", result);
        
        result = false;
        input = "";
        expResult = 0.0;
        output = Utils.parseDoubleSeguro(input);
        result = expResult == output;
        printTest("parseDoubleSeguro", result);
        
    }
    
    public static void testeCriarMatrizComFolgas() {

        //<editor-fold defaultstate="collapsed" desc="TESTE 1">
        InputDataProcessing.MAXIMIZACAO = true;
        Simplex.setnVariaveis(2);
        double[][] input = new double[][]{
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3},};
        double[][] expResult = new double[][]{
            {1, 2, 1, 0, 0, 3},
            {1, 2, 0, 1, 0, 3},
            {1, 2, 0, 0, 1, 3},
            {-1, -2, 0, 0, 0, 3}
        };
        double[][] output = InputDataProcessing.criarMatrizComFolgas(input);
        
        boolean result = true;
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                if (expResult[i][j] != output[i][j]) {
                    result = false;
                    break;
                }
                if (!result) {
                    break;
                }
            }
        }
        printTest("CriarMatrizComFolgas TESTE 1", result);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="TEST 2">
        InputDataProcessing.MAXIMIZACAO = false;
        Simplex.setnVariaveis(2);
        input = new double[][]{
            {4, 2, 2},
            {1, 6, 3},
            {1, 2, 3},};
        expResult = new double[][]{
            {4, 2, 1, 0, 2},
            {1, 6, 0, 1, 3},
            {-1, -2, 0, 0, 3},};
        
        output = InputDataProcessing.criarMatrizComFolgas(input);
        
        result = true;
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                if (expResult[i][j] != output[i][j]) {
                    result = false;
                    break;
                }
                if (!result) {
                    break;
                }
            }
        }
        printTest("CriarMatrizComFolgas TESTE 2", result);
        //</editor-fold>
    }
    
    public static void testeAdicionarSlacks() {
        
        boolean result = true;
        
        double[][] input = new double[][]{
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3},
            {1, 2, 3}
        };
        int inicio = 2;
        
        double[][] output = new double[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
        
        };
        double[][] expResult = new double[][]{
            {1, 2, 1, 0, 0, 3},
            {1, 2, 0, 1, 0, 3},
            {1, 2, 0, 0, 1, 3},
            {1, 2, 0, 0, 0, 3}
        
        };
        
        InputDataProcessing.adicionarSlacks(input, output, inicio);
        
        for (int i = 0; i < expResult.length; i++) {
            for (int j = 0; j < expResult[0].length; j++) {
                if (expResult[i][j] != output[i][j]) {
                    result = false;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        
        printTest("Adicionar slacks", result);
    }
    
    public static void testeEProblemaDeMaximixacao() {
        
        boolean result;
        String[] linhas
                = {
                    "Z = 3X1 + 5X2",
                    "2X1 + 4X2  => 10",
                    "6X2  + X2 => 20",
                    "X1 - X2 => 30"
                };
        
        result = InputDataProcessing.eProblemaDeMaximizacao(linhas);
        printTest("testeEProblemaDeMaximizacao", !result);
    }
    
    public static void testeVerificacaoEspacos() {
        String[] inputA = new String[]{
            "z  =  2  .  2  /  2  .  2  x  1  +  2x2",
            "x  2  +  4  x  3  <=  4  5"
        };
        String[] inputB = new String[]{
            "z  =   2  .  2  /  2  .  2  x  1  +  2x2",
            "x  2  +  4  x  3  <=  4   5"
        };
        boolean expResult = true;
        boolean output = simplex.Reader.verificacaoEspacos(inputA);
        boolean result = output == expResult;
        printTest("testeVerificacaoEspacos inputA", result);
        
        expResult = false;
        output = simplex.Reader.verificacaoEspacos(inputB);
        result = output == expResult;
        printTest("testeVerificacaoEspacos inputB", result);
    }
    
    public static void testeValidacaoPrimeiraLinha() {
        String inputA = "z=2.2/2.2x1+2x2";
        String inputB = "x=skd+dmx*df";
        String inputC = "w=1x1-2x2+x3+x4+5.25x5";
        
        boolean expResult = true;
        boolean output = simplex.Main.validacaoPrimeiraLinha(inputA);
        boolean result = output == expResult;
        printTest("testeValidacaoPrimeiraLinha InputA", result);
        
        expResult = false;
        output = simplex.Main.validacaoPrimeiraLinha(inputB);
        result = output == expResult;
        printTest("testeValidacaoPrimeiraLinha InputB", result);
        
        expResult = true;
        output = simplex.Main.validacaoPrimeiraLinha(inputC);
        result = output == expResult;
        printTest("testeValidacaoPrimeiraLinha Inputc", result);
        
    }
    
    public static void testeValidacaoLinhaRestricoes() {
        String[] inputA = {"z=2.2/2.2x1+2x2"};
        String[] inputB = {"x1+y2-3x7<=-25"};
        String[] inputC = {"1.2/2.5x1-2x2+x3+x4+5.25x5>=52"};
        
        boolean expResult = false;
        boolean output = simplex.Main.validacaoLinhaRestricoes(inputA);
        boolean result = output == expResult;
        printTest("testeValidacaoLinhaRestricoes InputA", result);
        
        expResult = true;
        output = simplex.Main.validacaoLinhaRestricoes(inputB);
        result = output == expResult;
        printTest("testeValidacaoLinhaRestricoes InputB", result);
        
        expResult = true;
        output = simplex.Main.validacaoLinhaRestricoes(inputC);
        result = output == expResult;
        printTest("testeValidacaoLinhaRestricoes Inputc", result);
        
    }
    
    public static void testeValidarVariaveisRestricoes() {
        String[] variaveisExistentesPrimeiraLinha = {
            "X1", "X2", "X3"};
        String[] linhas1 = {"2Y3+24X2+34X3<=24",
            "2X1+3.5X2+4X3<=34"};
        String[] linhas2 = {"2X1+24X2+34X3<=24",
            "2X1+3.5X2+4X3<=34"};
        
        boolean expResult = false;
        boolean output = simplex.InputDataProcessing.validarVariaveisRestricoes(variaveisExistentesPrimeiraLinha, linhas1);
        boolean result = output == expResult;
        printTest("testeValidarVariaveisRestricoes linhas1", result);
        
        expResult = true;
        output = simplex.InputDataProcessing.validarVariaveisRestricoes(variaveisExistentesPrimeiraLinha, linhas2);
        result = output == expResult;
        printTest("testeValidarVariaveisRestricoes linhas2", result);
        
    }

   
    public static void testeTransporParaDouble(){
        String numeros1 ="205.5/205.5";
        double expResult =1;
        double output=simplex.InputDataProcessing.transporParaDouble(numeros1);
        boolean result = output == expResult;
        printTest("testeTransporParaDouble", result);
    }
}
