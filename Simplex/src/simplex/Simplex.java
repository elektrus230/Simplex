/*
 * Classe main do projecto
 */
package simplex;

import java.util.Formatter;
import static simplex.InputDataProcessing.lerDadosEConstruirMatriz;
import static simplex.Simplex.resultados;
import static simplex.Writer.escreverResultados;
import static simplex.Writer.imprimirIteração;

/**
 *
 * @author Grupo 9
 */
public class Simplex {

    //<editor-fold defaultstate="collapsed" desc="Variaveis Globais">
    
    //public static double[][] matrizSimplex;
    public static double [][] matrizSimplex;
    public static String[] resultados;
    public static String[] listaDeVariaveis;
    public static String caminhoDoFicheiroInput;
    public static String caminhoDoFicheiroOutput;
    
    public static Formatter escrever = new Formatter(System.out);
    //</editor-fold>
    
    /**
     * @param args, Recebe dois caminhos de ficheiros, 
     * um de input, outro de output 
     */
    public static void main(String[] args) throws Exception {
        
//        String inputFile = args[0];
//        String outputFile = args[1];
        
////        System.out.println("\nin = " + inputFile);
////        System.out.println("out = " + outputFile+"\n");      
//        String inputFile = "testfiles\\input.txt";
//        String outputFile = "testfiles\\Output.txt";
        
//        if(inputFile != null && outputFile != null){

            simplex.Reader.validacaoParametrosEntrada(args); // serve para validar os argumentos e ficheiros
            
            Writer.escreverHeader(caminhoDoFicheiroOutput);
            
            matrizSimplex = lerDadosEConstruirMatriz(caminhoDoFicheiroInput, caminhoDoFicheiroOutput);

            if(matrizSimplex != null){
            
                executarSimplex (caminhoDoFicheiroOutput);
            
            }else{
                System.out.println("Surgiu um problema ao ler o ficheiro. Por favor verifique se o caminho dos ficheiros foi correctamente inserido.");
            }
            
//        }else{
            
//            System.out.println("Por favor indique os nomes dos ficheiros de "
//                    + "input e output ao chamar este programa.");
//        }
    }
    
    public static void executarSimplex(String outputFile) throws Exception{
        
        int iteracao = 0;
        
        while (existemNumerosNegativos(matrizSimplex[0])) {

            imprimirIteração(resultados, matrizSimplex,iteracao,outputFile);
            
            int[] indicesDoPivot = encontrarNumPivot();

            //<editor-fold defaultstate="collapsed" desc="Mover variaveis na lista de resultados">
            int linhaPivot = indicesDoPivot[0];
            int colunaPivot = indicesDoPivot[1];
            
            if(InputDataProcessing.Maximizacao){
               // resultados[colunaPivot - nSlacks] = listaDeVariaveis[linhaPivot];
            }else{
                resultados[linhaPivot] = listaDeVariaveis[colunaPivot];
                //No writer, tem de se mudar a atribuicao de valores de resultados.
                //De momento está-se a ir buscar á ultima coluna.
                //Tem de se ir buscar á ultima linha
            }
            
            if(colunaPivot < listaDeVariaveis.length){
                resultados[linhaPivot] = listaDeVariaveis[colunaPivot];
            }
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
        System.out.println("\n\n");
        escreverResultados(resultados, matrizSimplex, outputFile);
    }
  
    public static boolean existemNumerosNegativos(double [] primeiraLinha){
       
        int coluna = 0;
        boolean existemNumNegat;
        
        while ((primeiraLinha[coluna] >= 0) && (coluna < primeiraLinha.length-1)) {
            coluna++;
        }
        
        existemNumNegat = coluna != primeiraLinha.length-1;
        return existemNumNegat;        
    }
     
    public static int[] encontrarNumPivot() throws Exception {
        
        int[] indicesDoPivot = new int [2];
        
        int indiceColunaPivot = encontraColunaPivot(matrizSimplex[0]);
        
        indicesDoPivot[1] = indiceColunaPivot;
        
        double [] colunaPivotRestricoes = criaColunaRestricoes (indiceColunaPivot);
        
        double [] ultimaColunaRestricoes = criaColunaRestricoes (matrizSimplex[0].length-1);
       
        int indiceLinhaPivot = encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        
        indicesDoPivot[0] = indiceLinhaPivot;
        
        return indicesDoPivot;
    }

    public static int encontraColunaPivot (double [] primeiraLinha){
        
        double menor = primeiraLinha[0];
        int indiceColunaPivot = 0;
        
        for (int coluna = 1; coluna < primeiraLinha.length; coluna ++){
            if (primeiraLinha[coluna] < menor){
                menor = primeiraLinha[coluna];
                indiceColunaPivot = coluna;
            }
        }
        return indiceColunaPivot;
    }

    public static double [] criaColunaRestricoes (int indiceColuna){
        
        double [] colunaRestricoes = new double [matrizSimplex.length-1];
        
        for (int linha = 1; linha < matrizSimplex.length; linha ++){
            colunaRestricoes [linha-1] = matrizSimplex [linha][indiceColuna];                 
        }
        
        return colunaRestricoes;
    }
    
    /**
     * Devolve o index da linha pivot
     * @param colPivot
     * @param ultimaColuna
     * @return 
     */
    public static int encontraLinhaPivot (double [] colPivot, double []ultimaColuna) throws Exception{
        
        double [] quocienteColunas = Utils.calculaQuocienteColunas(colPivot,ultimaColuna);
        
        int indiceLinhaPivot = getIndiceLinhaPivot (quocienteColunas);
        
        return indiceLinhaPivot;
    } 
    
    public static int getIndiceLinhaPivot (double [] quocienteColunas) throws Exception{
    
        int indiceLinhaPivot = -1;
        int linha = 0;
        
        while(linha < quocienteColunas.length && !(quocienteColunas[linha] > 0)){
            linha++;
        }
        
        if(linha == quocienteColunas.length){
            indiceLinhaPivot = linha + 1;
            
        }else{
        
            double menor = quocienteColunas[linha];
            indiceLinhaPivot = linha + 1;
                
            for (int j = linha; j < quocienteColunas.length; j++){

                if (quocienteColunas[j] > 0 && quocienteColunas[j] < menor){
                    menor = quocienteColunas[j];
                    indiceLinhaPivot = j+1;
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
