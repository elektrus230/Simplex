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
import java.util.Formatter;

/**
 *
 * @author Grupo 9
 */
public class Writer {

    //<editor-fold desc="Escrever os dados iniciais retirados do Ficheiro de Input">
    /**
     * este método serve para escrever para o ecrâ e para o ficheiro de output
     * os dados lidos do ficheiro de input.
     * @param linhas
     * @param caminhoDoFicheiroDeOutput
     */
    public static void ImprimirDadosIniciais(String[] linhas, String caminhoDoFicheiroDeOutput) {

        try {
            File resultados = new File((caminhoDoFicheiroDeOutput));
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="imprimir as matrizes, desde a inicial até à ultima iteração">
    /**
     * imprimir as matrizes, desde a inicial até à ultima iteração
     * @param vars
     * @param matrizSimplex
     * @param cont
     * @param caminhoDoFicheiroDeOutput
     */
    public static void imprimirIteração(String[] vars, double[][] matrizSimplex, int cont, String caminhoDoFicheiroDeOutput) {

        try {

            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (cont == 0) {

                printWriter.printf("Matriz inicial \n");
                System.out.printf("Matriz inicial \n");

            } else {

                printWriter.printf("%nIteração nº %d: \n", cont);
                System.out.printf("\n\nIteração nº %d: \n", cont);
            }

            int tamLin;

            for (int linha = 0; linha < matrizSimplex.length; linha++) {

                String tempp = "|" + vars[linha] + " | ";

                for (int coluna = 0; coluna < matrizSimplex[linha].length; coluna++) {
                    tempp += String.format("%8.2f", matrizSimplex[linha][coluna]);
                }

                tamLin = tempp.length();

                if (linha == 0) {

                    printHeader(Simplex.listaDeVariaveis, printWriter, matrizSimplex[0].length);

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

                if (linha == matrizSimplex.length - 1) {
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
    //</editor-fold>


    
    //<editor-fold defaultstate="collapsed" desc="escrita dos resultados finais">
    /**
     * Este método serve para apresentar os resultados finais
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

            System.out.printf("\n\nResultados finais:\n------------------------\n");
            printWriter.printf("\nResultados finais:\n------------------------\n");

            int nLinhas = matrizSimplex.length;
            int nColunas = matrizSimplex[0].length;

            for (int linha = 0; linha < nLinhas; linha++) {
               
                String resultado = String.format("%8.2f",matrizSimplex[linha][nColunas - 1]);
                String nomeVar = linha == 0 ? "Z" : heads[linha];
                System.out.printf("O resultado final de %3s = %s %n", nomeVar, resultado);
                printWriter.printf("O resultado final de %3s = %s %n", nomeVar, resultado);
            }

            System.out.printf("------------------------\nPrograma terminado.\n");
            printWriter.printf("------------------------\nPrograma terminado.\n");
            printWriter.close();

        } catch (IOException ioe) {

            System.out.println("Ocorreu um problema ao escrever no ficheiro" + ioe.getMessage());
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="criar o cabeçalho do resultado">
    /**
     * Este método serve para imprimir o cabeçalho do problema, ao criarmos o
     * FileWriter temos que por false no segundo argumento, pois assim apaga o
     * que tiver dentro do ficheiro e escreve o pretendido é preciso lembrar que
     * deve-se sempre acabar com o fecho do PrintWiter ao sair
     * @param caminhoDoFicheiroDeOutput
     */
    public static void escreverHeader(String caminhoDoFicheiroDeOutput) {

        try {
            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, false);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            System.out.printf("-----------------------------------------------\n\t\t   SIMPLEX\n-----------------------------------------------\n");
            printWriter.printf("-----------------------------------------------\n\t\t\t\t\tSIMPLEX\n-----------------------------------------------\n");
            printWriter.close();

        } catch (IOException ioe) {
            System.out.println("Não foi possivel imprimir o header.");
        }
    }
    //</editor-fold>

    //<editor-fold desc="criação do cabeçalho da matriz">
    /**
     * este método serve para criar o cabeçalho da matriz criada com o nome das
     * variáveis as folgas e de b
     *
     * @param vars
     * @param printWriter
     * @param length
     */
    private static void printHeader(String[] vars, PrintWriter printWriter, int length) {

        String header = String.format("\n%7s", "");

        for (int i = 0; i < vars.length; i++) {
            header += String.format("%7s", vars[i]);
        }

        for (int i = vars.length; i < length - 1; i++) {
            header += String.format("%8s", "F" + (i - vars.length + 1));
        }

        header += String.format("%7s", "B");

        printWriter.println(header);
        System.out.println(header);
    }
    //</editor-fold>
    
    /**
     * Escreve uma mensagem e termina o programa.
     * @param mensagem
     * @param formatter 
     */
    public static void forcarSaida(String mensagem, Formatter formatter){
        escreverGenerico(mensagem,formatter);
        escreverGenerico(StringsLib.Msg_Saida,formatter);
        if(formatter != null){
            formatter.close();
        }
        System.exit(0);
    }
    
    /**
     * Escreve uma mensagem para a consola.
     * Se o formatter recebido existir, 
     * escreve tambem para um ficheiro
     * @param mensagem
     * @param formatter 
     */
    public static void escreverGenerico(String mensagem, Formatter formatter){
        System.out.println(mensagem);
        if(formatter != null){
            formatter.format(mensagem);
        }
    }
}
