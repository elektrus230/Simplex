/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static simplex.Main.matrizSimplexInicial;
import static simplex.StringsLib.EPS;
import static simplex.StringsLib.PNG;
import static simplex.StringsLib.TXT;

/**
 *
 * @author Grupo 9
 */
public class Grafico {

    public static File script = new File((StringsLib.Path_ScriptFile));
    public static String formatoDoGrafico;
    public static String Extensao;
    public static boolean GRAPH_MODE = false;

    /**
     * Pede ao utilizador para definir o tipo de ficheiro do grafico.
     * @return
     */
    public static void setFormatoDoGrafico() {

        String formato = StringsLib.PNG;
        
        Writer.escreverGenerico(StringsLib.Menu_TipoGráfico, null);

        String opcao = Reader.Leitor.next();

        switch (opcao) {
            case "1": formato = PNG; break;
            case "2": formato = TXT; break;
            case "3": formato = EPS; break;
            default:
                Writer.escreverGenerico(StringsLib.Msg_DefaultGrafico, null);
                break;
        }

        formatoDoGrafico = formato;
    }

    public static void gerarScript(int iteracao, double[] valoresZ) {

      
        try {

            FileWriter fileWriter = new FileWriter(script, false);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);

            String a, b;
            int indiceUltColuna = matrizSimplexInicial[0].length - 1;
            double xmax = 0, ymax = 0;

            /**
             * parte de envio das ordens para o Script em termos de tipo de
             * ficheiro a ser plotado
             */
            if (formatoDoGrafico.equalsIgnoreCase(PNG)) {
                printWriter.println("set term pngcairo enhanced size 1366,768 font \"arial,10\" ");
                printWriter.println("set output \"Solucao Grafica.png\"");
            }
            if (formatoDoGrafico.equalsIgnoreCase(EPS)) {
                printWriter.println("set term postscript enhanced landscape color \"arial\" 14 ");
                printWriter.println("set output \"Solucao Grafica.eps\"");
            }

            if (formatoDoGrafico.equalsIgnoreCase(TXT)) {
                printWriter.println("set term dumb 100 35");
                printWriter.println("set output \"Solucao Grafica.txt\"");
            }

            printWriter.println("set parametric");
            printWriter.println("set style fill empty");
            printWriter.println("set title \"Solução Gráfica\"");
            printWriter.println("set xl \"X1\"");
            printWriter.println("set yl \"X2\"");
            printWriter.println("set key outside right ");
            printWriter.println("set grid front");

            for (int i = 0; i < matrizSimplexInicial.length - 1; i++) {
                
                /**
                 * esta parte serve para arranjar o gráfico em termos de eixos
                 */
                if (matrizSimplexInicial[i][0] != 0) {
                    if (xmax < matrizSimplexInicial[i][indiceUltColuna] / matrizSimplexInicial[i][0]) {
                        xmax = matrizSimplexInicial[i][indiceUltColuna] / matrizSimplexInicial[i][0];
                    }
                }
                if (matrizSimplexInicial[i][1] != 0) {
                    if (ymax < matrizSimplexInicial[i][indiceUltColuna] / matrizSimplexInicial[i][1]) {
                        ymax = matrizSimplexInicial[i][indiceUltColuna] / matrizSimplexInicial[i][1];
                    }
                }
                /**
                 * aqui fazemos as contas para as equações parametricas estão
                 * certas neste momento
                 */
                if (matrizSimplexInicial[i][0] == 0) {
                    a = "t";
                    b = String.valueOf(matrizSimplexInicial[i][indiceUltColuna]) + "/" + String.valueOf(Main.matrizSimplexInicial[i][1]);

                } else {
                    if (matrizSimplexInicial[i][1] == 0) {
                        a = String.valueOf(matrizSimplexInicial[i][indiceUltColuna]) + "/" + String.valueOf(matrizSimplexInicial[i][0]);
                        b = "t";

                    } else {
                        a = "t";
                        b = "(" + String.valueOf(matrizSimplexInicial[i][indiceUltColuna]) + "-" + String.valueOf(Main.matrizSimplexInicial[i][0]) + "*t )/" + String.valueOf(matrizSimplexInicial[i][1]);

                    }
                }
                printWriter.printf("f%d1(t)= %s\nf%d2(t)= %s\n", i, a, i, b);
            }

            for (int k = 0; k < valoresZ.length; k++) {
                a = "t";
                b = "(" + String.valueOf(valoresZ[k]) + "-" + String.valueOf(Main.matrizSimplexInicial[matrizSimplexInicial.length - 1][0]) + "*t )/" + String.valueOf(matrizSimplexInicial[matrizSimplexInicial.length - 1][1]);
                printWriter.printf("g%d1(t)= %s\ng%d2(t)= %s\n", k, a, k, b);
            }

            xmax = 1.10 * xmax;
            ymax = 1.10 * ymax;
            double tmax = xmax;

            if (tmax < ymax) {
                tmax = ymax;
            }
            tmax = 20 * tmax;
            printWriter.println("set xrange [0<*:" + xmax + "]");
            printWriter.println("set yrange [0<*:" + ymax + "]");
            printWriter.println("set trange [0<*:" + tmax + "]");
            if (!formatoDoGrafico.equalsIgnoreCase(".TXT")) {
                printWriter.println("set object 1 rect from 0.000, 0.000 to " + xmax + "," + ymax);
                printWriter.println("set object 1 back clip lw 1.0 dashtype solid fc \"black\" fillstyle transparent solid 0.8 border lt -1");
                printWriter.println("set grid ytics mytics lt 1 lc rgb \"black\" lw 0.4");
                printWriter.println("set grid xtics mxtics lt 1 lc rgb \"black\" lw 0.4");
                printWriter.println("set mxtics 2");
                printWriter.println("set mytics 2");
            }
            printWriter.println("plot \\");
            if (!formatoDoGrafico.equalsIgnoreCase(TXT)) {
                //preenche a branco a zona não admissivel das restriçoes dependendo se é maximização ou minimização.
                for (int i = 0; i < matrizSimplexInicial.length - 1; i++) {
                    if (InputDataProcessing.MAXIMIZACAO) {
                        printWriter.printf("f%d1(t),f%d2(t) ti \"\" w filledcurves below x2 lt 1 lc \"white\",\\%n", i, i);
                    } else {
                        printWriter.printf("f%d1(t),f%d2(t) ti \"\" w filledcurves above x1 lt 1 lc \"white\",\\%n", i, i);
                    }
                }
            }
            if (!formatoDoGrafico.equalsIgnoreCase(TXT)) {
                //reemprime as retas das restriçoes com outra cor
                for (int j = 0; j < matrizSimplexInicial.length - 1; j++) {
                    int n = j + 1;
                    if (iteracao != 0 || j != matrizSimplexInicial.length - 1) {
                        printWriter.printf("f%d1(t),f%d2(t) ti \"restrição %d\" lt 1 lw 3.5,\\%n", j, j, n);
                    } else {
                        printWriter.printf("f%d1(t),f%d2(t) ti \"restrição %d\" lt 1 lw 3.5 %n", j, j, n);
                    }
                }

                //cria as retas provenientes das iterações, cujos valores sao passados para um array 
                for (int k = 0; k < valoresZ.length; k++) {
                    int i = k + 1;
                    if (k != valoresZ.length - 1) {
                        printWriter.printf("g%d1(t),g%d2(t) ti \"iteração %d\" with lines linewidth 4,\\%n", k, k, i);
                    } else {
                        printWriter.printf("g%d1(t),g%d2(t) ti \"iteração %d\"with lines linewidth 4\n", k, k, i);
                    }
                }
            }else {
               //reemprime as retas das restriçoes com outra cor
                for (int j = 0; j < matrizSimplexInicial.length - 1; j++) {
                    int n = j + 1;
                    if (j != matrizSimplexInicial.length - 1) {
                         printWriter.printf("f%d1(t),f%d2(t) ti \"restrição %d\"lc rgb \"#0025ad\" lt 2 lw 4,\\%n", j, j, n);
                    } else {
                        printWriter.printf("f%d1(t),f%d2(t) ti \"restrição %d\"lc rgb \"#0025ad\" lt 2 lw 4 %n", j, j, n);
                    }
                }

                //cria as retas provenientes das iterações, cujos valores sao passados para um array 
                for (int k = 0; k < valoresZ.length; k++) {
                    int i = k + 1;
                    if (k != valoresZ.length - 1) {
                        printWriter.printf("g%d1(t),g%d2(t) ti \"iteração %d\" with lines linewidth 4,\\%n", k, k, i);
                    } else {
                        printWriter.printf("g%d1(t),g%d2(t) ti \"iteração %d\"with lines linewidth 4\n", k, k, i);
                    }
                } 
            }
            printWriter.println("set terminal wxt");
            printWriter.println("set output");
            printWriter.println("replot");
            printWriter.close();
            Runtime r = Runtime.getRuntime();

            Process p = r.exec("gnuplot script.gp\"");
            Thread.sleep(1000);

        } catch (IOException ioe) {

            //TODO c
        } catch (InterruptedException ie) {
        
            //TODO
        }
        
    }
    
    public static void prepararGrafico(int nLinhas,int nColunas) {
        String[] calcVars = Utils.getVariaveisCalculo(Simplex.getVariaveis(), nLinhas);
        int nVars = calcVars.length;
        calcVars = Utils.aumentarArray(nLinhas, calcVars);
        for (int i = nVars; i < calcVars.length; i++) {
            calcVars[i] = Simplex.resultados[i - nVars];
        }   
    }

    /**
     * Define se o programa vai ou não permitir ao utilizador gerar um gráfico
     * @param cont 
     */
    public static void setGraphicMode(int cont) {
        GRAPH_MODE = cont == 2;
    }

}
