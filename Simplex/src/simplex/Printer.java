/*
 * Classe com métodos de escrita de dados em ficheiros
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
 * @author Dinis
 */
public class Printer {

    public final static String caminhoDoFicheiroDeOutput = "teste\\output.txt";

    public static void ImprimirDadosIniciais(String[] linhas) {

        try {

            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, false);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            
            if (resultados.exists() == false) {
                resultados.createNewFile();
                System.out.printf("O ficheiro de Output foi criado");
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
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public static void ImprimirMatrizes(double[][] matrizSimplex, int cont) {
        try {

            File resultados = new File((caminhoDoFicheiroDeOutput));
            FileWriter fileWriter = new FileWriter(resultados, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            if (cont == 0) {
               
                printWriter.printf("matriz inicial \n\n");
                System.out.printf("matriz inicial \n\n");
            } else {
                
                printWriter.printf("%nIteração nº %d: %n%n", cont);
                System.out.printf("\n\nIteração nº %d: \n\n", cont);
            }

            for (int linha = 0; linha < matrizSimplex.length; linha++) {
                for (int coluna = 0; coluna < matrizSimplex[linha].length; coluna++) {

                    System.out.printf("\t %6.2f ", matrizSimplex[linha][coluna]);
                    printWriter.printf("|%6.2f|", matrizSimplex[linha][coluna]);
                }

                System.out.print("\n");
                printWriter.printf("%n");
            }
            
            printWriter.close();
            
        } catch (IOException ioe) {

            System.out.println(ioe.getMessage());
        }
    }
}
