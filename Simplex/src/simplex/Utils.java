/*
 * Classe com métodos utilitários
 */
package simplex;

/**
 *
 * @author Grupo 9
 */
public class Utils {


    /**
     * Recebe um array e expande-o, preservando o seu conteudo
     * @param output
     * @return 
     */
    public static String[][] expandirArray(String[][] output) {
        if (output == null) {
            output = new String[1][3];
        } else {
            int tamanho = output.length;
            String[][] tempArray = new String[tamanho + 1][3];
            System.arraycopy(output, 0, tempArray, 0, tamanho);
            output = tempArray;
        }
        return output;
    }

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
     * TODO: Teste unitário
     * @param matriz
     * @return 
     */
    public static double[][] colocarUltimaLinhaEmPrimeiro(double[][] matriz){
    
        double[][] output = new double[matriz.length][matriz[0].length];
        output[0] = matriz[matriz.length-1];
        for (int i = 1; i < matriz.length-1; i++) {
            output[i] = matriz[i-1];
        }
        return output;
    }
    
    /**
     * TODO: Teste unitario
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
}
