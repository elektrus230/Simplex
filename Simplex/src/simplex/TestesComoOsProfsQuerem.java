/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

/**
 *
 * @author Dinis
 */
public class TestesComoOsProfsQuerem {
    
    public static void main(String[] args) {
        testGetValor();
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
}
