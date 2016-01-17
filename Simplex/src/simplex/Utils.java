/*
 * Classe com métodos utilitários
 */
package simplex;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Grupo 9
 */
public class Utils {
    
    /**
     * Recebe um array e expande-o, preservando o seu conteudo
     * TESTE UNITARIO
     * @param array
     * @return 
     */
    public static String[][] expandirArray(String[][] array) {
        if (array == null) {
            array = new String[1][3];
        } else {
            int tamanho = array.length;
            String[][] tempArray = new String[tamanho + 1][3];
            System.arraycopy(array, 0, tempArray, 0, tamanho);
            array = tempArray;
        }
        return array;
    }
    
    /**
     * Retorna um array contendo o resultado da divisao dos
     * elementos de um array pelos elementos de outro dado array
     * TESTE UNITARIO
     * @param colPivot
     * @param ultimaColuna
     * @return 
     */
    public static double[] calculaQuocienteColunas(double[] colPivot, double[] ultimaColuna) {
        double[] quocienteColunas = new double[colPivot.length];
        for (int linha = 0; linha < colPivot.length; linha++) {
            if (ultimaColuna[linha] != 0 && colPivot[linha] != 0) {
                quocienteColunas[linha] = ultimaColuna[linha] / colPivot[linha];
            } else {
                quocienteColunas[linha] = 0;
            }
        }
        return quocienteColunas;
    }
    
    /**
     * Verifica se uma string contem um elemento de um dado array 
     * TESTE UNITARIO
     * @param str
     * @param elementos
     * @return 
     */
    public static boolean stringContemElelmentoDeArray(String str, String[] elementos){
        boolean output = false;
        for (int i = 0; i < elementos.length; i++ ) {
            if (str.contains(elementos[i])) {
                output = true;
                break;
            }
        }
        return output;
    }
    
    /**
     * Transpoe uma matriz recebida
     * Teste unitario
     * @param matriz
     * @return 
     */
    public static double[][] transporMatriz(double[][] matriz){
        double[][] output = new double[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz[0].length; j++){
                output[j][i] = matriz[i][j];   
            }   
        }
        return output;
    }
    
    /**
     * Multilica os valores de um array por menos 1
     * Se um dos valores for 0, não multiplica, uma 
     * vez que doubles aceitam -0, o que tende a 
     * gerar problemas
     * Teste Unitário
     * @param array
     * @return 
     */
    public static double[] negarArray(double[] array){
        double[] output = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            double value = array[i];
            output[i] = value == 0 ? 0 : value * -1;
        }
        return output;
    }
    
    /**
     * Aumenta o numero de colunas de uma dada matriz 
     * UNIT TEST
     * @param cont
     * @param matriz
     * @return 
     */
    public static String[][] aumentaColunasMatriz(int cont, String[][] matriz) {
        String[][] output = matriz;
        int cols = matriz[0].length;
        int lins = matriz.length;
        if (cols - 1 <= cont) {
            output = new String[lins][cols + 1];
            for (int i = 0; i < lins; i++) {
                System.arraycopy(matriz[i], 0, output[i], 0, cols);
            }
        }
        return output;
    }

    /**
     * Aumenta o numero de linhas de um array
     * UNIT TEST
     * @param cont
     * @param array
     * @return 
     */
    public static String[] aumentarArray(int cont, String[] array) {
        String[] output = array;
        if (array.length <= cont) {
            int tamanho = array.length;
            output = new String[tamanho + 1];
            System.arraycopy(array, 0, output, 0, tamanho);
        }
        return output;
    }
    
    /**
     * Aumenta o tamanho de um array
     * UNIT TEST
     * @param cont
     * @param array
     * @return 
     */
    public static double[] aumentarArray(int cont, double[] array) {
        double[] output = array;
        if (array.length <= cont) {
            int tamanho = array.length;
            output = new double[tamanho + 1];
            System.arraycopy(array, 0, output, 0, tamanho);
        }
        return output;
    }
    
    /**
     * Faz parse de um double e evita crashar
     * devolve 0 se falhar
     * Defaults to 0 if fails
     * UNIT TEST
     * @param string
     * @return 
     */
    public static double parseDoubleSeguro(String string){
    
        double output = 0.0;
        try{
            output = Double.parseDouble(string);
        }catch (NumberFormatException nfe){
            //TODO Log problem
        }       
        return output;
    }
    
    /**
     * TODO COMMENT UNIT TEST
     * @param vars
     * @param nLinhas
     * @return 
     */
    public static String[] getVariaveisCalculo(String[] vars, int nLinhas) {

        String[] array = new String[nLinhas -1];
        for (int i = 0; i < (nLinhas-1); i++) {

            if (vars[0].contains("X")) {
                array[i] = "Y" + (i + 1);
            } else {
                array[i] = "X" + (i + 1);
            }
        }
        return array;
    }

    /**
     * TODO Unit TESt COMMENT
     * @param matriz
     * @return
     */
    public static double[][] converterMatrizParaDouble(String[][] matriz) {
        int nLinhas = matriz.length;
        int nColunas = matriz[0].length;
        double[][] output = new double[nLinhas][nColunas];
        for (int i = 0; i < nLinhas; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = InputDataProcessing.transporParaDouble(matriz[i][j]);
            }
        }
        return output;
    }
    
    /**
     * Devolve a data actual num dado formato
     * @param formato
     * @return 
     */
    public static String getDataActual(String formato) {
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        return sdf.format(date);
    }
}
