/*
 * Classe main do projecto
 */
package simplex;

import java.util.Formatter;
import static simplex.Simplex.resultados;
import static simplex.Writer.escreverResultados;
import static simplex.Writer.imprimirIteração;

/**
 *
 * @author Grupo 9
 */
public class Simplex {

    //<editor-fold defaultstate="collapsed" desc="Variaveis Globais">
    

    public static double [][] matrizSimplex;
    public static String[] resultados;
    public static String[] listaDeVariaveis;
    public static String caminhoDoFicheiroInput;
    public static String caminhoDoFicheiroOutput;
    
    public static Formatter escrever = new Formatter(System.out);
    //</editor-fold>

    public static void executarSimplex(String outputFile){
        
        int iteracao = 0;
        
        while (existemNumerosNegativos(matrizSimplex[matrizSimplex.length - 1])) {

            imprimirIteração(resultados, matrizSimplex,iteracao,outputFile);
            
            int[] indicesDoPivot = encontrarNumPivot();
            
            //<editor-fold defaultstate="collapsed" desc="Mover variaveis na lista de resultados">
            int linhaPivot = indicesDoPivot[0];
            int colunaPivot = indicesDoPivot[1];
            String variavelPivot = listaDeVariaveis[colunaPivot];  
            resultados[linhaPivot] = variavelPivot;
            //</editor-fold>
            
            passarLinhaPivotParaUm(indicesDoPivot);

            for (int linha = 0; linha < matrizSimplex.length; linha++) {

                if ((linha != indicesDoPivot[0]) & ((matrizSimplex[linha][indicesDoPivot[1]]) != 0)) {

                    zerarElementosDaColunaPivot(indicesDoPivot, linha);
                }
            }
            iteracao++;
        }
        
        imprimirIteração(resultados, matrizSimplex,iteracao,outputFile);
        escreverResultados(resultados, matrizSimplex, outputFile);
    }
  
    public static boolean existemNumerosNegativos(double [] ultimaLinha){
       
        int coluna = 0;
        boolean existemNumNegat;
        
        while ((ultimaLinha[coluna] >= 0) && (coluna < ultimaLinha.length-1)) {
            coluna++;
        }
        
        existemNumNegat = coluna != ultimaLinha.length-1;
        return existemNumNegat;        
    }
     
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

    public static double [] criaColunaRestricoes (int indiceColuna){
        
        double [] colunaRestricoes = new double [matrizSimplex.length-1];
        
        for (int linha = 0; linha < matrizSimplex.length - 1; linha ++){
            colunaRestricoes [linha] = matrizSimplex [linha][indiceColuna];                 
        }
        
        return colunaRestricoes;
    }
    
    /**
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
     * Divide um array pelo valor do elemento pivot
     * @param indicesDoPivot 
     */
    public static void passarLinhaPivotParaUm(int [] indicesDoPivot) {

        double valorDoPivot = matrizSimplex[indicesDoPivot[0]][indicesDoPivot[1]];
        
        for (int coluna = 0; coluna < matrizSimplex[indicesDoPivot[0]].length; coluna++) {

            matrizSimplex[indicesDoPivot[0]][coluna] = matrizSimplex[indicesDoPivot[0]][coluna] / valorDoPivot;
        }
    }

    /**
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
    
    
    //TODO: Remover
    //<editor-fold defaultstate="collapsed" desc="Teste - dummy matrix">
    public static double [][] criarMatrizInicial (){
       double matrizInicial [][] = {{-3,-5,0,0,0,0}, {2,4,1,0,0,10}, {6,1,0,1,0,20}, {1,-1,0,0,1,30}};
         System.out.println ("Matriz Inicial");       
        for (int lin = 0; lin < 4; lin++){
            for (int col = 0; col < 6; col++){
            System.out.print("\t " + matrizInicial [lin][col] + " ");                 
            }
            System.out.println();
        }               
        return matrizInicial;               
    }
    //</editor-fold>
    //Imprimir matrizSimplexnova, após iteração - para testes
    public static void imprimirMatrizSimplexNova (){
        System.out.println ("Matriz Simplex após primeira iteração");
        for (int linha = 0; linha < matrizSimplex.length; linha++ ){
            for (int coluna = 0; coluna < matrizSimplex[0].length; coluna ++){
              System.out.print("\t " + matrizSimplex [linha][coluna] + " ");                 
            }
            System.out.println();  
            }
        }
      
    }
