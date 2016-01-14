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

        testTransporMatriz();
        testeCalcularQuocienteColunas();
        testeAcrescentarColunasDeSlacks();
        testeAumentarArray();
        testeAumentarMatriz();
    }

    /**
     * Imprime um resultado de um teste
     * @param testName
     * @param result 
     */
    public static void printTest(String testName, boolean result){
        String resultTxt = "Passed";
        if(!result){
            resultTxt = "Failed";
        } 
        System.out.printf("Test %s Result : %s.\n",testName,resultTxt);
    }
    
    public static void testTransporMatriz(){
    

        double[][] input = new double[][]
        {
            {1,4},
            {2,5},
            {3,6}
        };  
        double[][] output = Utils.transporMatriz(input);
        double[][] expResult = new double[][]
        {
            {1,2,3},
            {4,5,6}
        };
        boolean result = true;
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                if(output[i][j] != expResult[i][j]){
                    result = false;
                    break;
                }
            }
            if(!result){
                break;
            }
            
        }
        printTest("Transpor matriz.", result);
    }
    
    public static void testeAcrescentarColunasDeSlacks(){
    
        int colunasAAcrescentar = 4;
        double[][] matrizInput = new double[][] 
        {
            {1,2,3},
            {4,5,6}
        };
        
        double[][] expResult = new double[][]
        {
            {1,2,0,0,0,0,3},
            {4,5,0,0,0,0,6}
        };
        
        int l = expResult.length;
        int c = expResult[0].length;
        
        
        double[][] matrizOutput = InputDataProcessing
                .acrescentarColunasDeSlacks(matrizInput, colunasAAcrescentar);
        
        boolean result = true;
        
        if(matrizOutput.length != l || matrizOutput[0].length != c){
            result = false;
        }

        if(result){
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    if(matrizOutput[i][j] != expResult[i][j]){
                        result = false;
                        break;
                    }
                }
            }
        }
        
        printTest("Acrescentar coluna : ", result);
    }
    
    public static void testeCalcularQuocienteColunas(){
        
        double[] array1 = new double[]
        {
            5,
            10,
            3
        };
        
        double[] array2 = new double[]
        {
            5,
            20,
            15
        };
        double[] expResult = new double[]
        {
            1.0,
            2.0,
            5
        };
        double[] output = Utils.calculaQuocienteColunas(array1, array2);
        boolean result = true;
        System.out.println(Arrays.toString(output));
        for (int i = 0; i < output.length; i++) {
            if(output[i] != expResult[i]){
                result = false;
                break;
            }
        }
        printTest("testeCalcularQuocienteColunas : ", result);
    }

    public static void testeAumentarArray(){
        int cont = 4;
        String[] input = new String[3];
        int expResult = 4;      
        String[] output = Utils.aumentoDeArray(cont, input);
        boolean result = output.length == expResult;
        printTest("testAumentarArray", result);
    }
    
    public static void testeAumentarMatriz(){
        int cont = 4;
        String[][] input = new String[3][3];
        int expResult = 4;      
        String[][] output = Utils.aumentaColunasMatriz(cont,input);
        boolean result = output[0].length == expResult;
        printTest("testAumentarArray", result);
    }
}
