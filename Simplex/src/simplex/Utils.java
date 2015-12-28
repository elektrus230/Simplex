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
}
