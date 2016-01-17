/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Formatter;

/**
 *
 * @author Grupo 9
 */
public class Writer {

    public static Formatter Escritor;
    
    
    /**
     * Este método serve para escrever para o ecrâ e para o ficheiro de output
     * os dados lidos do ficheiro de input.
     *
     * @param linhas
     * @param inputPath
     */
    public static void ImprimirDadosIniciais(String[] linhas, String inputPath) {

        try {
            File resultados = new File((inputPath));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (resultados.exists() == false) {

                resultados.createNewFile();
                System.out.printf("O ficheiro de Output foi criado.");
            }

            System.out.printf("Este é o problema de programação linear:\n\n");
            printWriter.printf("Este é o problema de programação linear:\n\n");

            for (String linha : linhas) {

                System.out.printf("%s%n", linha);
                printWriter.printf("%s%n", linha);
            }

            System.out.printf("\n");
            printWriter.printf("\n");

            printWriter.close();

        } catch (FileNotFoundException fnfe) {

            System.out.println("Ficheiro não encontrado " + fnfe.getMessage());

        } catch (IOException ioe) {

            System.out.println("Ocorreu um problema ao escrever no ficheiro" + ioe.getMessage());
        }
    }

    /**
     * imprimir as matrizes, desde a inicial até à ultima iteração
     *
     * @param vars
     * @param matrizSimplex
     * @param cont
     * @param inputPath
     */
    public static void imprimirIteração(String[] vars,
            double[][] matrizSimplex, int cont, String inputPath) {

        try {
            String[][] matrizImprimir = new String[matrizSimplex.length + 1][matrizSimplex[0].length + 1];
            for (int i = 0; i < matrizSimplex.length; i++) {
                for (int j = 0; j < matrizSimplex[i].length; j++) {
                    matrizImprimir[i + 1][j + 1] = String.format("%6.2f",(matrizSimplex[i][j]));
                }
            }
            for (int i = 0; i < vars.length; i++) {
                matrizImprimir[i+1][0]=vars[i];
            }
            
            for (int i = 0; i < matrizImprimir.length; i++) {
                System.out.println(Arrays.toString(matrizImprimir[i]));
            }
            System.out.println(Arrays.toString(vars));
            

            File resultados = new File((inputPath));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (cont == 0) {

                printWriter.printf("\n\t\t-= Matriz inicial =-\n");
                System.out.printf("\n\t\t-= Matriz inicial =-\n");

            } else {

                printWriter.printf("%nIteração nº %d: \n", cont);
                System.out.printf("\n\nIteração nº %d: \n", cont);
            }

            int tamLin;
            int nLinhas = matrizSimplex.length;
            int nColunas = matrizSimplex[0].length;

            for (int linha = 0; linha < nLinhas; linha++) {

                String tempp = "|" + vars[linha] + " | ";

                for (int coluna = 0; coluna < nColunas; coluna++) {
                    tempp += String.format("%8.2f", matrizSimplex[linha][coluna]);
                }

                tamLin = tempp.length();

                if (linha == 0) {

                    printHeader(Simplex.getVariaveis(), printWriter, nColunas);

                    for (int k = 0; k < tamLin + 1; k++) {

                        System.out.printf("-");
                        printWriter.printf("-");
                    }

                    System.out.printf("\n");
                    printWriter.printf("\n");
                }

                System.out.printf(tempp + " | ");
                printWriter.printf(tempp + " | ");

                System.out.print("\n");
                printWriter.printf("\n");

                tempp = "|";

                if (linha == nLinhas - 1) {
                    for (int k = 0; k < tamLin + 1; k++) {
                        System.out.printf("-");
                        printWriter.printf("-");
                    }
                }
            }

            System.out.print("\n");
            printWriter.printf("\n");
            printWriter.close();

        } catch (IOException ioe) {

            System.out.println("Ocorreu um problema ao escrever no ficheiro" + ioe.getMessage());
        }
    }

    /**
     * Este método serve para apresentar os resultados finais
     *
     * @param heads
     * @param matrizSimplex
     * @param caminhoDoFicheiroDeOutput
     */
    public static void escreverResultados(String[] heads, double[][] matrizSimplex, String caminhoDoFicheiroDeOutput) {

        try {

            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            System.out.printf("\n" + StringsLib.Msg_ResultadosEsperados);
            printWriter.printf(StringsLib.Msg_ResultadosEsperados);

            int nLinhas = matrizSimplex.length;
            int nColunas = matrizSimplex[0].length;

            for (int linha = 0; linha < nLinhas; linha++) {

                String nomeVar = linha == nLinhas - 1 ? InputDataProcessing.NomeFuncObjectivo : heads[linha];

                String resultado;
                if (InputDataProcessing.MAXIMIZACAO) {
                    resultado = String.format("%8.2f", matrizSimplex[linha][nColunas - 1]);
                } else {
                    int posicao = linha == nLinhas ? nColunas - heads.length + linha : nColunas - heads.length + linha - 1;
                    resultado = String.format("%8.2f", matrizSimplex[nLinhas - 1][posicao]);
                }

                System.out.printf(StringsLib.Msg_ApresentacaoResultado, nomeVar, resultado);
                printWriter.printf(StringsLib.Msg_ApresentacaoResultado, nomeVar, resultado);
            }

            System.out.printf(StringsLib.Msg_DashesParagraph + StringsLib.Msg_ProgramaTerminadoSucesso);
            printWriter.printf(StringsLib.Msg_DashesParagraph + StringsLib.Msg_ProgramaTerminadoSucesso);
            printWriter.close();

        } catch (IOException ioe) {

            System.out.println(StringsLib.Erro_Escrever + ioe.getMessage());
        }
    }

    /**
     * Este método serve para imprimir o cabeçalho do problema, ao criarmos o
     * FileWriter temos que por false no segundo argumento, pois assim apaga o
     * que tiver dentro do ficheiro e escreve o pretendido é preciso lembrar que
     * deve-se sempre acabar com o fecho do PrintWiter ao sair
     *
     * @param inputPath
     */
    public static void escreverHeader(String inputPath) {

        try {
            File resultados = new File((inputPath));
            FileWriter fileWriter = new FileWriter(resultados, false);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            System.out.printf(StringsLib.Header_Console);
            printWriter.printf(StringsLib.Header_File);
            printWriter.close();

        } catch (IOException ioe) {
            //TODO usar exepcao
            System.out.println(StringsLib.Erro_EscreverHeader);
        }
    }
    /**
     * este método serve para criar o cabeçalho da matriz criada com o nome das
     * variáveis as folgas e de b
     *
     * @param vars
     * @param printWriter
     * @param nColunas
     */
    private static void printHeader(String[] vars, PrintWriter printWriter, int nColunas) {

        String header = String.format("\n%7s", "");

        if (InputDataProcessing.MAXIMIZACAO) {

            for (int i = 0; i < vars.length; i++) {
                header += String.format("%7s", vars[i]);
            }

            for (int i = vars.length; i < nColunas - 1; i++) {
                header += String.format("%8s", "F" + (i - vars.length + 1));
            }

        } else {

            for (int i = 0; i < nColunas - vars.length - 1; i++) {
                header += String.format("%8s", "Y" + (i + 1));
            }

            for (int i = 0; i < vars.length; i++) {
                header += String.format("%7s", vars[i]);
            }
        }

        header += String.format("%7s", "B");

        printWriter.println(header);
        System.out.println(header);
    }

    /**
     * Escreve uma mensagem e termina o programa.
     *
     * @param mensagem
     * @param formatter
     */
    public static void forcarSaida(String mensagem, Formatter formatter) {
        escreverGenerico(mensagem, formatter);
        escreverGenerico(StringsLib.Msg_Saida, formatter);
        if (formatter != null) {
            formatter.close();
        }
        if (Main.TEST_MODE) {
            return;
        } else {
            System.exit(0);
        }
    }

//    /**
//     * Escreve uma mensagem e termina o programa.
//     * @param mensagem 
//     */
//    public static void forcarSaida(String mensagem, int linha, Class cls, Formatter method){
//        escreverGenerico(mensagem,fileprinter);
//        escreverGenerico(StringsLib.Msg_Saida,fileprinter);
//        if(fileprinter != null){
//            fileprinter.close();
//        }
//        
//        
//        if(Main.TEST_MODE){
//            return;
//        }else{
//            System.exit(0);
//            
//        }
//    }

    /**
     * Escreve uma mensagem para a consola. Se o formatter recebido existir,
     * escreve tambem para um ficheiro
     *
     * @param mensagem
     * @param formatter
     */
    public static void escreverGenerico(String mensagem, Formatter formatter) {
        System.out.println(mensagem);
        if (formatter != null) {
            formatter.format(mensagem);
        }
    }

    /**
     * Escreve uma mensagem com uma cor
     *
     * @param mensagem
     * @param formatter
     * @param cor
     */
    public static void escreverGenerico(String mensagem, Formatter formatter, String cor) {
        String mensagemColorida = StringsLib.Escape_Char + cor + mensagem;
        escreverGenerico(mensagemColorida, formatter);
    }
    
    /**
         * loga para 
         * validação de argumentos
         * validação de ficheiros
         * validação de espaços
         * validação de nullpointexception
         * validação da primeira linha
         * validação das linhas de restrições
         * validação de incógnitas nas linhs das restrições
         * validação de zeros nos divisores
         * 
         * 
     * @param mensagem
     * @param tipoErro
     * @param local
         */
    public static void escreverLog(String mensagem , String tipoErro) {
        try {
            
            String path = Main.getCaminhoFicheiroOutput(Main.logPath);
            
            if(Main.TEST_MODE){
                path = "LOG.txt";
            }
            
            File log = new File((path));
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            String data = Utils.getDataActual(StringsLib.FormatoData);
            
            String a = Arrays.toString(Thread.currentThread().getStackTrace());

            String entry = String.format("# %s ERRO: %s : %s -> %s", data,tipoErro,mensagem,a);
            printWriter.println(entry);
            printWriter.close();
        } catch (IOException ioe) {
            Writer.escreverGenerico(StringsLib.Erro_EscreverLog, Writer.Escritor);
        }
    }
}
