/*
 * Classe main do projecto
 */
package simplex;

import static simplex.Simplex.resultados;
import static simplex.Writer.escreverResultados;
import static simplex.Writer.imprimirIteração;

/**
 *
 * @author Grupo 9
 */
public class Simplex {

    public static double [][] matrizSimplex;
    public static String[] resultados;
    public static String[] variaveis;
    private static int nVariaveis;


    /**
     * TODO UNIT TEST COMMENT
     * @param outputFile 
     */
    public static void executarSimplex(String outputFile){
        
        int nLinhas = matrizSimplex.length;
        int nColunas = matrizSimplex[0].length;
        double[] valZ = new double[1];
        
        if(Grafico.GRAPH_MODE){
            Grafico.setFormatoDoGrafico();
            Grafico.prepararGrafico(nLinhas, nColunas);
            valZ[0] = matrizSimplex[nLinhas-1][nColunas-1];
        }
                
        int iteracao = 0;
        
        while (existemNumerosNegativos(matrizSimplex[nLinhas - 1])) {

            Writer.imprimirIteração(resultados, matrizSimplex,iteracao,outputFile);
            
            int[] indxsPivot = encontrarNumPivot();
            
            alocarVariaveisResultado(indxsPivot);
            
            passarLinhaPivotParaUm(indxsPivot);

            for (int linha = 0; linha < matrizSimplex.length; linha++) {
                if ((linha != indxsPivot[0]) & (matrizSimplex[linha][indxsPivot[1]]) != 0) {
                    zerarElementosDaColunaPivot(indxsPivot, linha);
                }
            }
            
            if(Grafico.GRAPH_MODE){
               valZ = simplex.Utils.aumentarArray(iteracao, valZ);
               valZ[iteracao] = matrizSimplex[nLinhas - 1][nColunas - 1];
            }

            iteracao++;
        }
        
        if(Grafico.GRAPH_MODE){
            Grafico.gerarScript(iteracao, valZ);
        }
        
        imprimirIteração(resultados, matrizSimplex,iteracao,outputFile);
        String[] res =  resultados;
//        String[] res = InputDataProcessing.MAXIMIZACAO ? resultados : getVariaveis();
        escreverResultados(res, matrizSimplex, outputFile);
    }

    /**
     * TODO COMMENT
     * TODO UNIT TEST
     * @param indicesDoPivot 
     */
    private static void alocarVariaveisResultado(int[] indicesDoPivot) {
     
        int linhaPivot = indicesDoPivot[0];
        int colunaPivot = indicesDoPivot[1];
        String variavelPivot = InputDataProcessing.MAXIMIZACAO ? variaveis[colunaPivot] : "Y" + (colunaPivot + 1);
        resultados[linhaPivot] = variavelPivot;
    }
  
    /**
     * TODO COMMENT
     * TODO UNIT TEST
     * @param ultimaLinha
     * @return 
     */
    public static boolean existemNumerosNegativos(double [] ultimaLinha){
       
        int coluna = 0;
        boolean existemNumNegat;
        
        while ((ultimaLinha[coluna] >= 0) && (coluna < ultimaLinha.length-1)) {
            coluna++;
        }
        
        existemNumNegat = coluna != ultimaLinha.length-1;
        return existemNumNegat;        
    }
     
    /**+
     * TODO COMMENT
     * TODO UNIT TEST
     * @return 
     */
    public static int[] encontrarNumPivot(){
        
        int[] indicesDoPivot = new int [2];
        
        int indiceColunaPivot = encontraColunaPivot(matrizSimplex[matrizSimplex.length -1]);
        
        indicesDoPivot[1] = indiceColunaPivot;
        
        double [] colunaPivotRestricoes = criaColunaRestricoes (indiceColunaPivot);
        
        double [] ultimaColunaRestricoes = criaColunaRestricoes (matrizSimplex[0].length-1);
       
        int indiceLinhaPivot = encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        
        indicesDoPivot[0] = indiceLinhaPivot;
        
        return indicesDoPivot;
    }

    /**
     * TODO UNIT TEST
     * TODO COMMENT
     * @param ultimaLinha
     * @return 
     */
    public static int encontraColunaPivot (double [] ultimaLinha){
        
        double menor = ultimaLinha[0];
        int indiceColunaPivot = 0;
        
        for (int coluna = 1; coluna < ultimaLinha.length; coluna ++){
            if (ultimaLinha[coluna] < menor){
                menor = ultimaLinha[coluna];
                indiceColunaPivot = coluna;
            }
        }
        return indiceColunaPivot;
    }

    /**
     * TODO COMMENT
     * TODO UNIT TEST
     * 
     * @param indiceColuna
     * @return 
     */
    public static double [] criaColunaRestricoes (int indiceColuna){
        
        double [] colunaRestricoes = new double [matrizSimplex.length-1];
        
        for (int linha = 0; linha < matrizSimplex.length - 1; linha ++){
            colunaRestricoes [linha] = matrizSimplex [linha][indiceColuna];                 
        }
        
        return colunaRestricoes;
    }
    
    /**
     * TODO 
     * Devolve o index da linha pivot
     * @param colPivot
     * @param ultimaColuna
     * @return 
     */
    public static int encontraLinhaPivot (double [] colPivot, double []ultimaColuna){
        
        double [] quocienteColunas = Utils.calculaQuocienteColunas(colPivot,ultimaColuna);
        
        int indiceLinhaPivot = getIndiceLinhaPivot (quocienteColunas);
        
        return indiceLinhaPivot;
    } 
    
    /**
     * TODO COMMENT
     * TODO UNIT TEST
     * @param quocienteColunas
     * @return 
     */
    public static int getIndiceLinhaPivot (double [] quocienteColunas){
    
        int indiceLinhaPivot = -1;
        int linha = 0;
        
        while(linha < quocienteColunas.length && !(quocienteColunas[linha] > 0)){
            linha++;
        }
        if(linha == quocienteColunas.length){
            indiceLinhaPivot = linha;  
        }else{
        
            double menor = quocienteColunas[linha];
            indiceLinhaPivot = linha;
                
            for (int j = linha; j < quocienteColunas.length; j++){

                if (quocienteColunas[j] > 0 && quocienteColunas[j] < menor){
                    menor = quocienteColunas[j];
                    indiceLinhaPivot = j;
                }
            }
        }
        return indiceLinhaPivot;
    }
    
    /**
     * TODO UNIT TEST
     * Divide um array pelo valor do elemento pivot
     * @param indicePivot 
     */
    public static void passarLinhaPivotParaUm(int [] indicePivot) {
        double valorDoPivot = matrizSimplex[indicePivot[0]][indicePivot[1]];
        for (int coluna = 0; coluna < matrizSimplex[indicePivot[0]].length; coluna++) {
            matrizSimplex[indicePivot[0]][coluna] = matrizSimplex[indicePivot[0]][coluna] / valorDoPivot;
        }
    }

    /**
     * TODO UNIT TEST
     * Torna todos os valores do array a zero
     * @param indicesDoPivot
     * @param linha 
     */
    public static void zerarElementosDaColunaPivot(int [] indicesDoPivot, int linha) {
        double multiplicador = (-1)* matrizSimplex [linha][indicesDoPivot[1]];
        for (int coluna=0; coluna<matrizSimplex[0].length; coluna++){
            
            matrizSimplex[linha][coluna]= 
                    multiplicador*matrizSimplex[indicesDoPivot[0]][coluna] + matrizSimplex[linha][coluna];    
        }  
    }

    //<editor-fold defaultstate="collapsed" desc="Getter & Setters">
    
    public static void setResultados(String[] varsResultados) {
        Simplex.resultados = varsResultados;
    }

    public static int getnVariaveis() {
        return nVariaveis;
    }

    public static void setnVariaveis(int nVariaveis) {
        Simplex.nVariaveis = nVariaveis;
    }
    
    public static String[] getVariaveis() {
        return variaveis;
    }

    public static void setVariaveis(String[] variaveis) {
        Simplex.variaveis = variaveis;
    }
}
    
    //</editor-fold>
    
