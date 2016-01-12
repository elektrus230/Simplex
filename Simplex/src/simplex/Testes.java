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
        //testGetValor();
        //testTransporMatriz();
        testeCalcularQuocienteColunas();
        //testeAcrescentarColunasDeSlacks();
    }
    
    /**
     * Test metodo get valor
     */
    public static void testGetValor(){
    
        boolean testResult;
        
        String teste = "z = x1 + 3x2 + -6x3";
        int indexVar = 4;       
        String[] result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        String[] expResult = new String[] {"1","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor nulo.", testResult);
        
        teste = "z = -x1 + 3x2 + -6x3";
        indexVar = 5;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"1","-"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor nulo negativo.", testResult);
        
        teste = "z = 123x1 + 3x2 + -6x3";
        indexVar = 7;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"123","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor 123.", testResult);
        
        teste = "z = -123x1 + 3x2 + -6x3";
        indexVar = 8;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"123","-"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor -123.", testResult);
        
        teste = "z = 0x1 + 3x2 + -6x3";
        indexVar = 5;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"0","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor 0.", testResult);
        
        teste = "z = 1/2x1 + 3x2 + -6x3";
        indexVar = 7;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"0.5","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor 1/2.", testResult);
        
        teste = "z = -1/2x1 + 3x2 + -6x3";
        indexVar = 8;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"-0.5","-"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor -1/2.", testResult);
        
        teste = "z = 1/-2x1 + 3x2 + -6x3";
        indexVar = 8;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"-0.5","-"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor 1/-2.", testResult);
        
        teste = "z = -1/-2x1 + 3x2 + -6x3";
        indexVar = 9;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"0.5","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor -1/-2.", testResult);
        
        teste = "z = 0.52x1 + 3x2 + -6x3";
        indexVar = 7;       
        result = InputDataProcessing.extrairValorEOperadorDeVariavel(teste, indexVar);
        expResult = new String[] {"0.5","+"};
        testResult = expResult[0].equals(result[0]);
        printTest("Ler valor de variavel : valor 0.5.", testResult);
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
}
