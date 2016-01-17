/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static simplex.Utils.stringContemElelmentoDeArray;

/**
 *
 * @author Grupo 9
 */
public class InputDataProcessing {

    public static boolean MAXIMIZACAO = true;
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    //<editor-fold defaultstate="collapsed" desc="OPERADORES">
    public final static String[] MENOR_OU_IGUAL = {"<=", "=<", "\u2264", "\u2266"};
    public final static String[] MAIOR_OU_IGUAL = {">=", "=>", "\u2265", "\u2267"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';
    public final static char BARRA = '/';
    public final static char ESPACO = ' ';
    //</editor-fold>

    public static String NomeFuncObjectivo;
    public static String[] colunaRefResultados;

    /**
     * aqui se vai retirar os valores das equações para desse modo se poder
     * efectuar os calculos
     *
     * @param linhas
     * @return
     */
    public static double[][] extrairValoresDasLinhas(String[] linhas) {

        double[][] matrizOutput = null;

        if (linhas != null) {

            if (linhas.length > 0) {

                setNomeFuncaoObjectivo(linhas[0]);

                Writer.ImprimirDadosIniciais(linhas, Main.outputPath);

                MAXIMIZACAO = eProblemaDeMaximizacao(linhas);

                String[][] matrizInicialS = getMatrixInicial(linhas);

                preencherMatrizRestricoes(linhas, matrizInicialS);

                matrizOutput = Utils.converterMatrizParaDouble(matrizInicialS);

                int nResultados = MAXIMIZACAO ? linhas.length : Simplex.getnVariaveis() + 1;
                String[] resultados = criarColunaResultados(nResultados);
                Simplex.setResultados(resultados);
            }
        }
        return matrizOutput;
    }

    /**
     * TODO: rever - certas coisas so devem acontecer se existirem pelo menos 2
     * vars
     *
     * @param linhas
     * @return
     */
    private static String[][] getMatrixInicial(String[] linhas) {
        String[][] matrizInicialS = new String[linhas.length][3];
        Pattern pattern = Pattern.compile(StringsLib.Regex_PrimeiraLinha);
        Matcher matcher = pattern.matcher(linhas[0]);
        matrizInicialS = encontrarVariaveis(matcher, matrizInicialS);
        int linhaZ = matrizInicialS.length - 1;
        int colunaZ = matrizInicialS[0].length - 1;
        matrizInicialS[linhaZ][colunaZ] = "0";
        return matrizInicialS;
    }

    /**
     * TODO : 
     *este método vai nos encontrar as variáveis existentes na linha da função objectivo
     * e coloca-las num array que vai aumentando de tamanho caso seja necessário e ao mesmo tempo
     * vai construindo a matriz inicial, cujo tamanho também é dinâmico
     * 
     * @param matcher
     * @param matrizInicialS
     * @return
     */
    private static String[][] encontrarVariaveis(Matcher matcher, String[][] matrizInicialS) {

        int cont = 0;

        String[] variveisEncontradas = new String[2];

        while (matcher.find()) {

            variveisEncontradas = Utils.aumentarArray(cont, variveisEncontradas);
            matrizInicialS = Utils.aumentaColunasMatriz(cont, matrizInicialS);
            variveisEncontradas[cont] = matcher.group(8);

            String valor = EMPTY;
            if (matcher.group(0).contains("-") && matcher.group(4).isEmpty()) {
                valor += "-1";
            } else {

                if (matcher.group(1).isEmpty() || (matcher.group(1).contains("+") && matcher.group(4).isEmpty())) {
                    valor += "1";
                } else {
                    valor += transporParaDouble(matcher.group(1).trim().replaceAll(SPACE, EMPTY));
                }
            }
            matrizInicialS[matrizInicialS.length - 1][cont] = valor.replaceAll(SPACE, EMPTY);

            cont++;
        }

        Simplex.setVariaveis(variveisEncontradas);
        Simplex.setnVariaveis(cont);
        Grafico.setGraphicMode(cont);

        return matrizInicialS;
    }

    //<editor-fold defaultstate="collapsed" desc="PREENCHIMENTO DA MATRIZ">
    /**
     * TODO: 
     * este método vai apanhar o array criado com o nome das variáveis
     * e vai apanhando em toda a linha essas variáveis e vai somando ou subtraindo
     * os valores que a variável tem por linha e colocando na matriz no seu local
     * certo.
     * este método tem duas partes;
     * a parte onde há as variáveis, e a parte após os menores ou maiores e iguais 
     *a parte onde temos as variáveis vamos pelo nome
     * a outra parte é o que resta a seguir ao menor ou maior e iguais
     * 
     * @param linhas
     * @param matriz
     */
    public static void preencherMatrizRestricoes(String[] linhas, String[][] matriz) {

        int nLinhas = matriz.length;
        int nColunas = matriz[0].length;

        String[] linhasTemp = new String[nLinhas - 1];
        for (int i = 0; i < linhasTemp.length; i++) {
            linhasTemp[i] = linhas[i + 1];
            validarVariaveisRestricoes(Simplex.getVariaveis(), linhas);
        }

        for (int i = 0; i < linhasTemp.length; i++) {
            for (int j = 0; j < nColunas - 1; j++) {
                double valorV = 0;
                String padrao = "(([+-]?\\s{0,2}(\\d*(\\.\\d{1,2})?(/\\d*(\\.\\d{1,2})?)?))(" + Simplex.variaveis[j] + "))";
                Pattern retricao = Pattern.compile(padrao);
                Matcher matcher = retricao.matcher(linhasTemp[i]);

                while (matcher.find()) {
                    if (matcher.group(2).isEmpty() || (matcher.group(2).contains("+") && matcher.group(3).isEmpty())) {
                        valorV += 1;
                    } else {

                        if (matcher.group(2).contains("-") && matcher.group(3).isEmpty()) {
                            valorV += -1;
                        } else {
                            valorV += transporParaDouble(matcher.group(2).trim().replaceAll(SPACE, EMPTY));
                        }
                    }
                }
                matriz[i][j] = String.valueOf(valorV);
                if (matriz[i][j] == EMPTY) {
                    matriz[i][j] = "0";
                }
            }

            int index = -1;

            for (String charMaior : MAIOR_OU_IGUAL) {
                if (linhasTemp[i].indexOf(charMaior) > 0) {
                    index = linhasTemp[i].indexOf(charMaior) + charMaior.length();
                    break;
                }
            }
            if (index == -1) {
                for (String charMenor : MENOR_OU_IGUAL) {
                    if (linhasTemp[i].indexOf(charMenor) > 0) {
                        index = linhasTemp[i].indexOf(charMenor) + charMenor.length();
                        break;
                    }
                }
            }
            if (index != -1) {
                matriz[i][nColunas - 1] = linhasTemp[i]
                        .substring(index)
                        .trim()
                        .replaceAll(SPACE, EMPTY);
            } else {
                matriz[i][nColunas - 1] = "0";
            }
        }
    }

    /**
     * TODO COMMENT UNIT TEST - está feito
     * aqui vamos validar se existem variaveis nas linhas das restrições
     * que não existam na linha da função objectivo
     * 
     * @param varsExistentes
     * @param linhas
     */
    public static boolean validarVariaveisRestricoes(String[] varsExistentes, String[] linhas) {
        boolean output = true;
        for (String linha : linhas) {
            String padrao = StringsLib.Regex_ValidaMaisDe2Espacos;
            Pattern pattern = Pattern.compile(padrao);
            Matcher matcher = pattern.matcher(linha);
            int cont = 0, controle = 0;
            while (matcher.find()) {
                String encontrou = matcher.group();
                if (Arrays.asList(varsExistentes).contains(encontrou)) {
                    cont++;
                }
                controle++;

                if (controle > cont) {
                    //TODO add to log
                    System.out.println(StringsLib.Erro_VariavelInfiltrada + " ->validarVariaveisRestricoes + InputDataProcessing");
                    output = false;
                }

            }
        }
        return output;
    }

    //</editor-fold>
    /**
     * TODO REVER TITULO COMMETN UNIT TEST CLASS
     *este metodo transpoe uma string que pode ter a barra de divisor
     * separa a string pela barra e depois realiza a operação de divisão
     * aproveita para validar se o numero está a ser dividido por 0 ou não
     * @param conteudo
     * @return
     */
    public static double transporParaDouble(String conteudo) {
        double output = 0;
        if (conteudo.contains("/")) {
            int indexBarra = conteudo.indexOf(String.valueOf(BARRA));
            String preBarraS = conteudo.substring(0, indexBarra);
            String posBarraS = conteudo.substring(indexBarra + 1, conteudo.length());
            double preBarraD = Double.parseDouble(preBarraS);
            double posBarraD = Double.parseDouble(posBarraS);
            if (posBarraD == 0) {
                Writer.forcarSaida(StringsLib.Erro_DivisorIgualZero, Writer.Escritor);
            } else {
                output = preBarraD / posBarraD;
            }

        } else {
            //TODO rever
            output = Utils.parseDoubleSeguro(conteudo);
        }
        return output;
    }

    //<editor-fold defaultstate="collapsed" desc="FOLGAS">
    /**
     * UNIT TEST Cria folgas e torna a linha Funcao Obj negativa
     *
     * @param matrizAFolgar
     * @return
     */
    public static double[][] criarMatrizComFolgas(double[][] matrizAFolgar) {

        int linhas = matrizAFolgar.length;
        int colunas = matrizAFolgar[0].length;
        int colunasComSlacks = colunas + linhas - 1;

        double[][] output = new double[linhas][colunasComSlacks];

        int inicioSlacks = MAXIMIZACAO ? Simplex.getnVariaveis() : linhas - 1;

        adicionarSlacks(matrizAFolgar, output, inicioSlacks);

        for (int i = 0; i < Simplex.getnVariaveis(); i++) {
            output[linhas - 1][i] *= -1;
        }
        return output;
    }

    /**
     * UNIT TEST Copia os dados de uma matriz para uma maior e adiciona slacks
     *
     * @param semSlacks
     * @param comSlacks
     */
    public static void adicionarSlacks(double[][] semSlacks, double[][] comSlacks, int inicio) {

        for (int i = 0; i < semSlacks.length; i++) {
            System.arraycopy(semSlacks[i], 0, comSlacks[i], 0, semSlacks[i].length - 1);
        }

        for (int i = 0; i < comSlacks.length; i++) {
            comSlacks[i][comSlacks[i].length - 1] = semSlacks[i][(semSlacks[i].length) - 1];
        }

        for (int i = 0; i < comSlacks.length - 1; i++) {
            comSlacks[i][semSlacks[0].length - 1 + i] = 1;
        }
    }

    /**
     * TODO UNIT TEST Preenche os slacks na tabela simplex. Colaca o valor 1 nos
     * espaços referentes a slacks
     *
     * @param matrizOutput
     * @param colunaInicio
     * @param colunaFin
     */
    public static void preencherSlacks(double[][] matrizOutput, int colunaInicio, int colunaFin) {
        int linha = 0;
        for (int coluna = colunaInicio; coluna < colunaFin; coluna++) {
            matrizOutput[linha][coluna] = 1;
            linha++;
        }
    }

    //</editor-fold>
    /**
     * TODO UNIT TEST Cria um array que vai servir para ir guardando as
     * variaveis de resultado
     *
     * @param nResultadosEserados
     * @return lista ordenada de nomes de variaveis/slacks/Z
     */
    private static String[] criarColunaResultados(int nResultadosEserados) {

        String[] output = new String[nResultadosEserados];

        String[] vars = Simplex.getVariaveis();

        String var = "S";

        String obj = InputDataProcessing.MAXIMIZACAO ? "Z " : "W ";

        for (int i = 0; i < nResultadosEserados; i++) {
            if (i == nResultadosEserados - 1) {
                output[i] = obj;
            } else {
                if (InputDataProcessing.MAXIMIZACAO) {

                    output[i] = var + (i + 1);

                } else {

                    output[i] = vars[i];
                }
            }
        }
        return output;
    }

    /**
     * TODO UNIT TEST Determina se o problema em questão é de maximização ou de
     * minimização Se detetar uma inconsitencia de simbolos nas linhas, forca o
     * programa a parar
     *
     * @param linhas
     * @return
     */
    public static boolean eProblemaDeMaximizacao(String[] linhas) {
        boolean output = true;
        int trigger = 0;
        for (int i = 1; i < linhas.length; i++) {
            if (trigger == 0) {
                if (stringContemElelmentoDeArray(linhas[i], MENOR_OU_IGUAL)) {
                    output = true;
                    trigger = 1;
                } else if (stringContemElelmentoDeArray(linhas[i], MAIOR_OU_IGUAL)) {
                    output = false;
                    trigger = 2;
                }
            } else {

                if (trigger == 1) {

                    if (stringContemElelmentoDeArray(linhas[i], MAIOR_OU_IGUAL)) {
                        Writer.forcarSaida(StringsLib.Erro_InconsistenciaOperador, Writer.Escritor);
                    }

                } else {

                    if (stringContemElelmentoDeArray(linhas[i], MENOR_OU_IGUAL)) {
                        Writer.forcarSaida(StringsLib.Erro_InconsistenciaOperador, Writer.Escritor);
                    }
                }
            }
        }
        Writer.escreverGenerico(String.format(StringsLib.Msg_TipoProblemaPPL,
                (output ? StringsLib.Maximizacao : StringsLib.Minimizacao)), Writer.Escritor);
        return output;
    }

    /**
     * TODO to remove? Processo necessário para a maximização e minimização Acrescenta colunas
     * entre variaveis de decisão a ultima coluna
     *
     * @param matrizOutput
     * @param nSlacks
     * @return
     */
    public static double[][] acrescentarColunasDeSlacks(double[][] matrizOutput, int nSlacks) {

        int linhas = matrizOutput.length;
        int colunas = matrizOutput[0].length;

        double[][] output = new double[linhas][colunas + nSlacks];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {

                output[i][j] = matrizOutput[i][j];
            }
        }

        for (int i = 0; i < linhas; i++) {
            output[i][colunas + nSlacks - 1] = output[i][colunas - 1];
            output[i][colunas - 1] = 0;

        }
        return output;
    }
/**
 * serve para definirmos qual o nome da função objectivo
 * 
 * @param linha 
 */
    private static void setNomeFuncaoObjectivo(String linha) {
        if (linha.toUpperCase().split("=")[0].contains("W")) {
            NomeFuncObjectivo = "W";
        } else {
            NomeFuncObjectivo = "Z";
        }
    }
}
