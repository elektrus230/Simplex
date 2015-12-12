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
    
    public static int[] encontraNumPivot() {
        
        int[] indicesDoPivot = null;
        
        int indiceColunaPivot = encontraColunaPivot(matrizSimplex[0]);
        indicesDoPivot[1] = indiceColunaPivot;
        
        int indiceLinhaPivot = encontraLinhaPivot(indiceColunaPivot);
        indicesDoPivot[0] = indiceLinhaPivot;
        
        return indicesDoPivot;
    }

    public static int encontraColunaPivot (double [] primeiraLinha){
        double menor = primeiraLinha[0];
        int indiceColunaPivot = 0;
        for (int coluna = 0; coluna < primeiraLinha.length; coluna ++){
            if (primeiraLinha[coluna] < menor){
                menor = primeiraLinha[coluna];
                indiceColunaPivot = coluna;
            }
        }
        return indiceColunaPivot;
    }

    private static int encontraLinhaPivot (int indiceColunaPivot){
        
        double [] DivUltimaColunaPorColunaPivot =
                calculaDivUltimaColunaPorColunaPivot(indiceColunaPivot);
        
        int indiceLinhaPivot = menorDivPositiva (DivUltimaColunaPorColunaPivot);
       
        return indiceLinhaPivot;
    }
        
    private static double[] calculaDivUltimaColunaPorColunaPivot (int indiceColunaPivot){
        double [] ultimaColunaDivColunaPivot = null;
        int ultimaColuna = matrizSimplex[0].length;
       
        for (int linha = 1; linha < matrizSimplex.length; linha ++){
        ultimaColunaDivColunaPivot [linha]=
                matrizSimplex [linha][ultimaColuna]/matrizSimplex[linha][indiceColunaPivot];   
        }
        return ultimaColunaDivColunaPivot;
    }
        
    private static int menorDivPositiva (double [] DivUltimaColunaPorColunaPivot){
        
        int linha = 0;
        int indiceLinhaPivot = 0;
        
        while (DivUltimaColunaPorColunaPivot[linha]<0){
         linha++; 
        }
        double menor = DivUltimaColunaPorColunaPivot[linha];
        for (int j = linha+1; j < matrizSimplex.length-1; j++){
            
            if (DivUltimaColunaPorColunaPivot[j] > 0 & DivUltimaColunaPorColunaPivot[j] < menor){
                menor = DivUltimaColunaPorColunaPivot[j];
                indiceLinhaPivot = j;   
            }
        }
        return indiceLinhaPivot;
    }
    
    private static void passarLinhaPivotParaUm(int indiceDaColunaPivot) {
        
    }

    private static void zerarElementoDaColunaPivot(int indicesDoPivot) {
        
    }

    private static boolean existemNumerosNegativos(double[] matrizSimplex) {
        
    }
    
}
