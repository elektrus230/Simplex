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
        
        double [] colunaPivotRestricoes = criaColunaRestricoes (indiceColunaPivot);
        double [] ultimaColunaRestricoes = criaColunaRestricoes (matrizSimplex[0].length);
        
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
        
        for (int j = linha+1; j < quocienteColunas.length; j++){
            
            if (quocienteColunas[j] > 0 & quocienteColunas[j] < menor){
                menor = quocienteColunas[j];
                indiceLinhaPivot = j+1;
            }
        }
        }
        return indiceLinhaPivot;
    }
    
    public static void passarLinhaPivotParaUm(int indiceDaColunaPivot) {
        
    }

    public static void zerarElementoDaColunaPivot(int indicesDoPivot) {
        
    }

    public static boolean existemNumerosNegativos(double[] matrizSimplex) {
     return true;   
    }
    
}
