/*
 * Classe main do projecto
 */
package simplex;

/**
 *
 * @author Grupo 9
 */
public class Simplex {

    //<editor-fold defaultstate="collapsed" desc="Matriz Simplex">
    
    //public static double[][] matrizSimplex;
 public static double [][] matrizSimplex = criarMatrizInicial();
 
    //</editor-fold>
    
    /**
     * @param args, Recebe dois caminhos de ficheiros, 
     * um de input, outro de output 
     */
    public static void main(String[] args) {
        executarSimplex ();
        /**
         * PASSOS:
         * 1 - Ler ficheiro
         * 2 - Processar dados lidos
         * 3 - Aplicar método simplex
         * 4 - Exportar resultados para ficheiro
         */
    }
    
    public static void executarSimplex(){
        
        //while(existemNumerosNegativos(matrizSimplex[0])){
        
            int[] indicesDoPivot = encontraNumPivot();
            
            passarLinhaPivotParaUm(indicesDoPivot);
                   
            for(int linha = 0; linha < matrizSimplex.length; linha++){
            
                if((linha != indicesDoPivot[0]) & ((matrizSimplex [linha][indicesDoPivot[1]]) != 0)) {
                
                    zerarElementosDaColunaPivot(indicesDoPivot, linha);
                    
                }
            
            } 
            imprimirMatrizSimplexNova ();
        } 
//}

    
    //NOTA: OS métodos utils não recebem matrizes!!!!!!Q!!!!!QW#"!QWE$#"
    //RECEBEM O MINIMO POSSIVEL DE DADOS (OU ESTRUTURAS DED DADOS),
    //PARA PODEREM SER EXECUTADOS
      
    public static int[] encontraNumPivot() {
        
        int[] indicesDoPivot = new int [2];
        
        int indiceColunaPivot = encontraColunaPivot(matrizSimplex[0]);
        indicesDoPivot[1] = indiceColunaPivot;
        
        double [] colunaPivotRestricoes = criaColunaRestricoes (indiceColunaPivot);
       // System.out.println(matrizSimplex[0].length-1);
        for (int i=0; i< colunaPivotRestricoes.length; i++){
//            System.out.println(colunaPivotRestricoes [i]);
        }
        double [] ultimaColunaRestricoes = criaColunaRestricoes (matrizSimplex[0].length-1);
        for (int i=0; i< ultimaColunaRestricoes.length; i++){
//            System.out.println(ultimaColunaRestricoes [i]);
        }
        int indiceLinhaPivot = encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        indicesDoPivot[0] = indiceLinhaPivot;
        
        return indicesDoPivot;
    }

    public static int encontraColunaPivot (double [] primeiraLinha){
        double menor = primeiraLinha[0];
        int indiceColunaPivot = -1;
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
    
    public static int encontraLinhaPivot (double [] colunaPivotRestricoes, double []ultimaColunaRestricoes){
        double [] quocienteColunas =
                calculaQuocienteColunas(colunaPivotRestricoes,ultimaColunaRestricoes);
        int indiceLinhaPivot = getIndiceLinhaPivot (quocienteColunas);
        return indiceLinhaPivot;
    }
    
        
    public static double[] calculaQuocienteColunas (double [] colunaPivotRestricoes, double []ultimaColunaRestricoes){
        double [] quocienteColunas = new double [colunaPivotRestricoes.length];
        for (int linha = 0; linha < colunaPivotRestricoes.length; linha ++){
        quocienteColunas [linha]=
                ultimaColunaRestricoes [linha]/colunaPivotRestricoes [linha];
        
//            System.out.println(quocienteColunas [linha]);
        } 
        return quocienteColunas;
        }
        
    public static int getIndiceLinhaPivot (double [] quocienteColunas){
        
        int linha = 0;
        int indiceLinhaPivot = -1;
        
        while (quocienteColunas[linha]<0 & linha < quocienteColunas.length){
         linha++; 
        }
        
        if (linha == (quocienteColunas.length)){
            System.out.println ("Problema não tem solução. Todos os quocientes são negativos");
            indiceLinhaPivot = -1;
            
        }else if (quocienteColunas[linha] == 0){
           indiceLinhaPivot = linha+1;   
        }
        else{   
        double menor = quocienteColunas[linha];
        indiceLinhaPivot = linha+1;
        
//        System.out.println(menor);
//        System.out.println(indiceLinhaPivot);
        
        for (int j = linha+1; j < quocienteColunas.length; j++){
            
            if (quocienteColunas[j] > 0 & quocienteColunas[j] < menor){
                menor = quocienteColunas[j];
                indiceLinhaPivot = j+1;
            }
        }
        }
        return indiceLinhaPivot;
    }
    
    public static void passarLinhaPivotParaUm(int [] indicesDoPivot) {
    //André   
       
        double [] matriz = {0.5, 1, 0.25, 0, 0, 2.5};  
        matrizSimplex [indicesDoPivot [0]] = matriz;
    }

    public static void zerarElementosDaColunaPivot(int [] indicesDoPivot, int linha) {
        
        double multiplicador = (-1)* matrizSimplex [linha][indicesDoPivot[1]];
        for (int coluna=0; coluna<matrizSimplex[0].length; coluna++){
            
            matrizSimplex[linha][coluna]= 
                    multiplicador*matrizSimplex[indicesDoPivot[0]][coluna] + matrizSimplex[linha][coluna];    
        }  
    } 

//    public static boolean existemNumerosNegativos(double[] matrizSimplex) {
//   //André
//        return true;   
//    }
      
    // Matriz inicial para testes
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
