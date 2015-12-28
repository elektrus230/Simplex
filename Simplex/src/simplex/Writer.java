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

/**
 *
 * @author bruno
 */
public class Writer {
    
    public static void ImprimirDadosIniciais(String[] linhas, String caminhoDoFicheiroDeOutput) {

        try {
            
            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, false);
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

            System.out.println("Ocorreu um problema ao escrever no ficheiro"+ ioe.getMessage());
        }
    }

    /**
     * 
     * @param vars
     * @param matrizSimplex
     * @param cont
     * @param caminhoDoFicheiroDeOutput 
     */
    
    
    public static void imprimirIteração(String[]vars, double[][] matrizSimplex, int cont, String caminhoDoFicheiroDeOutput) {
        
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
                    
                    printHeader(Simplex.listaDeVariaveis,printWriter,matrizSimplex[0].length);
                    
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

            System.out.println("Ocorreu um problema ao escrever no ficheiro"+ ioe.getMessage());
        }
    }

    /**
     * 
     * @param matrizSimplex
     * @param cont
     * @param caminhoDoFicheiroDeOutput 
     */
    public static void ImprimirMatrizesS(String[][] matrizSimplex, int cont, String caminhoDoFicheiroDeOutput) {
        try {

            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            
            if (cont == 0) {
                
                printWriter.printf("matriz inicial \n");
                System.out.printf("matriz inicial \n");
            } else {
                
                printWriter.printf("%nIteração nº %d: \n", cont);
                System.out.printf("%nIteração nº %d: \n", cont);
            }

            int tamLin;
            
            for (int linha = 0; linha < matrizSimplex.length; linha++) {
                
                String tempp = "|" + String.format("%4s|", matrizSimplex[linha][0]);
                
                for (int coluna = 1; coluna < matrizSimplex[linha].length; coluna++) {
                    
                    tempp += String.format("%9s", matrizSimplex[linha][coluna]);

                }
                
                tamLin = tempp.length();
                
                if (linha == 0 || linha == 1 || linha == 2) {
                    
                    for (int k = 0; k < tamLin + 1; k++) {
                        System.out.printf("-");
                        printWriter.printf("-");
                    }
                    
                    System.out.printf("\n");
                    printWriter.printf("\n");
                }
                
                System.out.printf(tempp + "|");
                printWriter.printf(tempp + "|");
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

            System.out.println("Ocorreu um problema ao escrever no ficheiro"+ ioe.getMessage());
        }

    }

    public static void escreverResultados(String[][] matrizSimplexS, String caminhoDoFicheiroDeOutput) {
        try {
            
            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            
            System.out.printf("Esta foi a ultima Iteração \n");
            printWriter.printf("Esta foi a ultima Iteração \n");
            int nLinhas = matrizSimplexS.length;
            int nColunas= matrizSimplexS[0].length;
            
           
            
            for (int linha = 1; linha < nLinhas; linha++) {
                System.out.printf("O resultado final de %3s = %s %n", matrizSimplexS[linha][0], matrizSimplexS[linha][nColunas-1]);
                printWriter.printf("O resultado final de %3s = %s %n", matrizSimplexS[linha][0], matrizSimplexS[linha][nColunas-1]);
            }
            printWriter.close();
            
        } catch (IOException ioe) {
            
            System.out.println("Ocorreu um problema ao escrever no ficheiro"+ ioe.getMessage());
        }
    }
    
    public static void escreverResultados(String[] heads, double[][] matrizSimplex, String caminhoDoFicheiroDeOutput) {
        
        try {
            
            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            
            System.out.printf("Resultados finais:\n------------------------\n");
            printWriter.printf("Resultados finais:\n------------------------\n");
            
            int nLinhas = matrizSimplex.length;
            int nColunas= matrizSimplex[0].length;
            
            for (int linha = 0; linha < nLinhas; linha++) {
                
                String nomeVar = linha == 0 ? "Z" : heads[linha];
                System.out.printf("O resultado final de %3s = %s %n", nomeVar, matrizSimplex[linha][nColunas-1]);
                printWriter.printf("O resultado final de %3s = %s %n",nomeVar, matrizSimplex[linha][nColunas-1]);
            }
            
            System.out.printf("------------------------\nPrograma terminado.\n");
            printWriter.printf("------------------------\nPrograma terminado.\n");
            printWriter.close();
            
        } catch (IOException ioe) {
            
            System.out.println("Ocorreu um problema ao escrever no ficheiro"+ ioe.getMessage());
        }
    }
    
    public static void escreverHeader(String caminhoDoFicheiroDeOutput){
    
        try{
            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            
            System.out.printf("-----------------------------------------------\n\t\tSIMPLEX\n-----------------------------------------------\n");
            printWriter.printf("\"-----------------------------------------------\\n\\t\\tSIMPLEX\\n-----------------------------------------------\n");
            
        }catch (IOException ioe){
            System.out.println("Não foi possivel imprimir o header.");
        }
    }

    private static void printHeader(String[] vars, PrintWriter printWriter, int length) {
        
        String header = String.format("\n%7s", " ");
        
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
    
  
}
