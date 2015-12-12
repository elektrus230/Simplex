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
        int indiceColunaPivot = -1;
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
        
        int indiceLinhaPivot = getIndiceLinhaPivot (DivUltimaColunaPorColunaPivot);
       
        return indiceLinhaPivot;
    }
        
    private static double[] calculaDivUltimaColunaPorColunaPivot (int indiceColunaPivot){
        double [] ultimaColunaDivColunaPivot = new double [matrizSimplex.length-1];
        int ultimaColuna = matrizSimplex[0].length;
       
        for (int linha = 1; linha < matrizSimplex.length; linha ++){
        ultimaColunaDivColunaPivot [linha-1]=
                matrizSimplex [linha][ultimaColuna]/matrizSimplex[linha][indiceColunaPivot];
        //o índice zero da divisão corresponde à divisão da linha 1 da matriz simplex. Ainda vou alterar
        // isto para enviar dois arrays com as colunas sem contar com a linha zero e tudo isto já ficará
        // mais claro. Mas isto deverá estar a funcionar. Logo já vou testar.
        }
        return ultimaColunaDivColunaPivot;
    }
        
    private static int getIndiceLinhaPivot (double [] DivUltimaColunaPorColunaPivot){
        
        int linha = 1;
        int indiceLinhaPivot = -1;
        
        while (DivUltimaColunaPorColunaPivot[linha]<0 & linha < DivUltimaColunaPorColunaPivot.length){
         linha++; 
        }
        
        if (linha == (DivUltimaColunaPorColunaPivot.length +1 )){
            System.out.println ("Problema não tem solução. Todos os quocientes são negativos");
            indiceLinhaPivot = -1;
            
        }else if (DivUltimaColunaPorColunaPivot[linha] == 0){
           indiceLinhaPivot = linha+1;   
        }
        else{   
        double menor = DivUltimaColunaPorColunaPivot[linha];
        
        for (int j = linha+1; j < DivUltimaColunaPorColunaPivot.length-1; j++){
            
            if (DivUltimaColunaPorColunaPivot[j] > 0 & DivUltimaColunaPorColunaPivot[j] < menor){
                menor = DivUltimaColunaPorColunaPivot[j];
                indiceLinhaPivot = j+1;
            }
        }
        }
        return indiceLinhaPivot;
    }
    
    private static void passarLinhaPivotParaUm(int indiceDaColunaPivot) {
        
    }

    private static void zerarElementoDaColunaPivot(int indicesDoPivot) {
        
    }

    private static boolean existemNumerosNegativos(double[] matrizSimplex) {
        return false;
        //TODO:
    }
    
}
