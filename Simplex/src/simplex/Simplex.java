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
    
    public static double[][] matrizSimplex;
    
    //</editor-fold>
    
    /**
     * @param args, Recebe dois caminhos de ficheiros, 
     * um de input, outro de output 
     */
    public static void main(String[] args) {
        /**
         * PASSOS:
         * 1 - Ler ficheiro
         * 2 - Processar dados lidos
         * 3 - Aplicar método simplex
         * 4 - Exportar resultados para ficheiro
         */
    }
    
    public static void executarSimplex(){
    
        
        while(existemNumerosNegativos(matrizSimplex[0])){
        
            int[] indicesDoPivot = encontraNumPivot();
            
            passarLinhaPivotParaUm(indicesDoPivot[0]);
                    
            for(int linha = 0; linha < matrizSimplex.length; linha++){
            
                if(linha != indicesDoPivot[0]){
                
                    zerarElementoDaColunaPivot(indicesDoPivot[1]);
                }
            }              
        } 
    }

    
    //NOTA: OS métodos utils não recebem matrizes!!!!!!Q!!!!!QW#"!QWE$#"
    //RECEBEM O MINIMO POSSIVEL DE DADOS (OU ESTRUTURAS DED DADOS),
    //PARA PODEREM SER EXECUTADOS
    
    private static int[] encontraNumPivot() {
        
        int colunaPivot = encontraColunaPivot();
        
        int linhaPivot = encontraLinhaPivot();
       
    }

    private static void passarLinhaPivotParaUm(int indiceDaColunaPivot) {
        
    }

    private static void zerarElementoDaColunaPivot(int indicesDoPivot) {
        
    }

    private static boolean existemNumerosNegativos(double[] matrizSimplex) {
        
    }
    
    
}
