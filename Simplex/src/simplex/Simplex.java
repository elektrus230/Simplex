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
    public static double[][] matrizSimplex = criarMatrizInicial();

    //</editor-fold>
    /**
     * @param args, Recebe dois caminhos de ficheiros, um de input, outro de
     * output
     */
    public static void main(String[] args) {

        //String caminhoDoFicheiroDeInput = args[0];
        //String caminhoDoFicheiroDeOutput = args[1];
        // if (caminhoDoFicheiroDeInput != null
        // && caminhoDoFicheiroDeOutput != null) {
        //do code
        // mainProcessor(caminhoDoFicheiroDeIpunt)
        // } else {
        // System.out.println("Por favor indique os nomes dos ficheiros de "
        //       + "input e output ao chamar este programa.");
        //}
        executarSimplex();

        /**
         * PASSOS: 1 - Ler ficheiro 2 - Processar dados lidos 3 - Aplicar método
         * simplex 4 - Exportar resultados para ficheiro
         */
    }

    public static void executarSimplex() {
        int itCount = 0;
        
        while (existemNumerosNegativos(matrizSimplex[0])) {
            itCount++;
            
            int[] indicesDoPivot = encontraNumPivot(matrizSimplex[0]);

            passarLinhaPivotParaUm(indicesDoPivot);

            for (int linha = 0; linha < matrizSimplex.length; linha++) {

                if ((linha != indicesDoPivot[0]) & ((matrizSimplex[linha][indicesDoPivot[1]]) != 0)) {

                    zerarElementosDaColunaPivot(indicesDoPivot, linha);

                }
            }
            imprimirMatrizSimplexNova(itCount);
        }
    }

    //NOTA: OS métodos utils não recebem matrizes!!!!!!Q!!!!!QW#"!QWE$#"
    //RECEBEM O MINIMO POSSIVEL DE DADOS (OU ESTRUTURAS DED DADOS),
    //PARA PODEREM SER EXECUTADOS
    public static boolean existemNumerosNegativos(double[] primeiraLinha) {

        int coluna = 0;
        boolean existemNumNegat;
        while ((primeiraLinha[coluna] >= 0) && (coluna < primeiraLinha.length - 1)) {
            coluna++;
        }
        if (coluna == primeiraLinha.length - 1) {
            existemNumNegat = false;
        } else {
            existemNumNegat = true;
        }
        return existemNumNegat;
    }

    public static int[] encontraNumPivot(double [] primeiraLinha) {

        int[] indicesDoPivot = new int[2];

        int indiceColunaPivot = encontraColunaPivot(primeiraLinha);
        indicesDoPivot[1] = indiceColunaPivot;

        double[] colunaPivotRestricoes = criaColunaRestricoes(indiceColunaPivot);
        for (int i = 0; i < colunaPivotRestricoes.length; i++) {
        }
        double[] ultimaColunaRestricoes = criaColunaRestricoes(primeiraLinha.length - 1);
        for (int i = 0; i < ultimaColunaRestricoes.length; i++) {
        }
        int indiceLinhaPivot = encontraLinhaPivot(colunaPivotRestricoes, ultimaColunaRestricoes);
        indicesDoPivot[0] = indiceLinhaPivot;

        return indicesDoPivot;
    }

    public static int encontraColunaPivot(double[] primeiraLinha) {

        double menor = primeiraLinha[0];
        int indiceColunaPivot = 0;
        for (int coluna = 1; coluna < primeiraLinha.length; coluna++) {
            if (primeiraLinha [coluna] < menor) {
                menor = primeiraLinha[coluna];
                indiceColunaPivot = coluna;
            }
        }
        return indiceColunaPivot;
    }

    public static double[] criaColunaRestricoes(int indiceColuna) {
        double[] colunaRestricoes = new double[matrizSimplex.length - 1];

        for (int linha = 1; linha < matrizSimplex.length; linha++) {
            colunaRestricoes[linha - 1] = matrizSimplex[linha][indiceColuna];
        }
        return colunaRestricoes;
    }

    public static int encontraLinhaPivot(double[] colunaPivotRestricoes, double[] ultimaColunaRestricoes) {
        double[] quocienteColunas
                = calculaQuocienteColunas(colunaPivotRestricoes, ultimaColunaRestricoes);
        int indiceLinhaPivot = getIndiceLinhaPivot(quocienteColunas);
        return indiceLinhaPivot;
    }

    public static double[] calculaQuocienteColunas(double[] colunaPivotRestricoes, double[] ultimaColunaRestricoes) {
        double[] quocienteColunas = new double[colunaPivotRestricoes.length];
        for (int linha = 0; linha < colunaPivotRestricoes.length; linha++) {
            if (colunaPivotRestricoes[linha] == 0){
                colunaPivotRestricoes[linha] = -1;
            }
            quocienteColunas[linha] = ultimaColunaRestricoes[linha] / colunaPivotRestricoes[linha];
        }
        return quocienteColunas;
    }

    public static int getIndiceLinhaPivot(double[] quocienteColunas) {

        int linha = 0;
        int indiceLinhaPivot = -1;

        while (quocienteColunas[linha] < 0 & linha < quocienteColunas.length) {
            linha++;
        }

        if (linha == (quocienteColunas.length)) {
            System.out.println("Problema não tem solução. Todos os quocientes são negativos");
            indiceLinhaPivot = -1;

        } else if (quocienteColunas[linha] == 0) {
            indiceLinhaPivot = linha + 1;
        } else {
            double menor = quocienteColunas[linha];
            indiceLinhaPivot = linha + 1;

            for (int j = linha + 1; j < quocienteColunas.length; j++) {

                if (quocienteColunas[j] > 0 & quocienteColunas[j] < menor) {
                    menor = quocienteColunas[j];
                    indiceLinhaPivot = j + 1;
                }
            }
        }
        return indiceLinhaPivot;
    }

    public static void passarLinhaPivotParaUm(int[] indicesDoPivot) {

        double valorDoPivot = matrizSimplex[indicesDoPivot[0]][indicesDoPivot[1]];

        for (int coluna = 0; coluna < matrizSimplex[indicesDoPivot[0]].length; coluna++) {
            matrizSimplex[indicesDoPivot[0]][coluna] = matrizSimplex[indicesDoPivot[0]][coluna] / valorDoPivot;
        }
    }

    public static void zerarElementosDaColunaPivot(int[] indicesDoPivot, int linha) {

        double multiplicador = (-1) * matrizSimplex[linha][indicesDoPivot[1]];
        for (int coluna = 0; coluna < matrizSimplex[0].length; coluna++) {

            matrizSimplex[linha][coluna] = multiplicador * matrizSimplex[indicesDoPivot[0]][coluna] + matrizSimplex[linha][coluna];
        }
    }

    // Matriz inicial para testes
    public static double[][] criarMatrizInicial() {

        double matrizInicial[][] = {{-3, -5, 0, 0, 0, 0}, {2, 4, 1, 0, 0, 10}, {6, 1, 0, 1, 0, 20}, {1, -1, 0, 0, 1, 30}};
        System.out.println("Matriz Inicial");
        for (int lin = 0; lin < 4; lin++) {
            for (int col = 0; col < 6; col++) {
                System.out.print("\t " + matrizInicial[lin][col] + " ");
            }
            System.out.println();
        }
        return matrizInicial;

    }

    //Imprimir matrizSimplexnova, após iteração - para testes
    public static void imprimirMatrizSimplexNova(int itCount) {
        System.out.println("\nMatriz Simplex após " + itCount + "º iteração: \n");
        for (int linha = 0; linha < matrizSimplex.length; linha++) {
            for (int coluna = 0; coluna < matrizSimplex[0].length; coluna++) {
                System.out.printf("\t" + String.format("%.2f", matrizSimplex[linha][coluna]) + " ");
            }
            System.out.println();
        }
    }

    static int encontraColunaPivot(int i, int i0, int i1, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
