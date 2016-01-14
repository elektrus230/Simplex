/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

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
    public final static String[] MENOR_OU_IGUAL = {"<=", "=<", "\u2264", "\u2266"} ;
    public final static String[] MAIOR_OU_IGUAL = {">=", "=>", "\u2265", "\u2267"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';
    public final static char BARRA = '/';
    public final static char ESPACO = ' ';

    //</editor-fold>
    
    public static String[] colunaRefResultados;

    public static double[][] extrairValoresDasLinhas(String[] linhas) {

        double[][] matrizOutput = null;

        if (linhas != null) {

            if (linhas.length > 0) {

                MAXIMIZACAO = eProblemaDeMaximizacao(linhas);
                
                String[][] matrizInicialS = new String[linhas.length][3];
                Pattern pattern = Pattern.compile(StringsLib.Regex_PrimeiraLinha);
                Matcher matcher = pattern.matcher(linhas[0]);
                
                
                matrizInicialS = encontrarVariaveis(matcher, matrizInicialS);
                
                int linhaZ = matrizInicialS.length - 1;
                int colunaZ = matrizInicialS[0].length - 1;
                matrizInicialS[linhaZ][colunaZ] = "0";
                preencherMatrizRestricoes(linhas, matrizInicialS);
                matrizOutput = converterMatrizParaDouble(matrizInicialS);
                
                Simplex.resultados = criarColunaResultados(linhas.length);
                //System.out.println(Arrays.toString(Simplex.resultados));
            }
        }
        return matrizOutput;
    }

    private static String[][] encontrarVariaveis(Matcher matcher, String[][] matrizInicialS) {
        int cont = 0;
        while (matcher.find()) {
            
            Simplex.listaVariaveis = Utils.aumentoDeArray(cont,Simplex.listaVariaveis);
            matrizInicialS = Utils.aumentaColunasMatriz(cont,matrizInicialS);
            Simplex.listaVariaveis[cont] = matcher.group(7);
            
            String valor = EMPTY;
            if (matcher.group(0).contains(String.valueOf(MENOS))) {
                valor =String.valueOf(MENOS);
            } else {
                valor = String.valueOf(MAIS);
            }
            
            if (matcher.group(1).isEmpty()) {
                valor += "1";
            } else {
                valor += transporParaDouble(matcher.group(1).trim().replaceAll(SPACE, EMPTY));
            }
            matrizInicialS[matrizInicialS.length - 1][cont] = valor.replaceAll(SPACE, EMPTY);
            cont++;
        }
        return matrizInicialS;
    }
    
    public static double[][] converterMatrizParaDouble(String[][] matriz) {

        double[][] output = new double[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = transporParaDouble(matriz[i][j]);
            }
        }
        return output;
    }
    
    public static void preencherMatrizRestricoes(String[] linhas, String[][] matriz) {   
        
        String[] linhasTemp = new String[linhas.length - 1];
        for (int i = 0; i < linhasTemp.length; i++) {
            linhasTemp[i] = linhas[i + 1];
            
//            System.out.println("linhas ->"+linhas[i] );

        }
        for (int i = 0; i < linhasTemp.length; i++) {
            for (int j = 0; j < matriz[i].length - 1; j++) {
                double valorV = 0;
                String padrao = "(([+-]?\\s{0,2}(\\d*(\\.\\d{1,2})?(/\\d*(\\.\\d{1,2})?)?))(" + Simplex.listaVariaveis[j] + "))";
                Pattern retricao = Pattern.compile(padrao);
                Matcher n = retricao.matcher(linhasTemp[i]);

                while (n.find()) {

                    if (n.group(2).isEmpty() || (n.group(2).contains("+") && n.group(3).isEmpty())) {
                        valorV += 1;
                    } else {

                        if (n.group(2).contains("-") && n.group(3).isEmpty()) {
                            valorV += -1;
                        } else {
                            valorV += transporParaDouble(n.group(2).trim().replaceAll(" ", ""));
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
                matriz[i][matriz[i].length - 1] = linhasTemp[i].substring(index).trim().replaceAll(" ", "");
            } else {
                matriz[i][matriz[i].length - 1] = "0";
            }

        }
    }
     
    private static double transporParaDouble(String conteudo) {
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
            output = Double.parseDouble(conteudo);
        }
        return output;
    }

    //<editor-fold defaultstate="collapsed" desc="FOLGAS">
    
    /**
     * Cria folgas e torna a linha Funcao Obj negativa
     * @param matrizAFolgar
     * @return 
     */
    public static double[][] criarMatrizComFolgas(double[][] matrizAFolgar) {
        
        int linhas = matrizAFolgar.length;
        int colunas = matrizAFolgar[0].length;
        int colunasComSlacks = colunas + linhas - 1;
        
        double[][] output = new double[linhas][colunasComSlacks];
        
        adicionarSlacks(matrizAFolgar, output);
        
        for(int i=0;i< Simplex.listaVariaveis.length - 1;i++){
            output[linhas- 1 ][i] *= -1; //multiplica por -1 a linha da função
        }
        return output;
    }

    /**
     * Copia os dados de uma matriz para uma maior e adiciona slacks
     * @param semSlacks
     * @param comSlacks 
     */
    private static void adicionarSlacks(double[][] semSlacks, double[][] comSlacks) {
        for (int i = 0; i < semSlacks.length; i++) {
            for (int j = 0; j < semSlacks[i].length-1; j++) {
                comSlacks[i][j] = semSlacks[i][j];
            }
        }
        for (int i = 0; i < comSlacks.length; i++) {
            comSlacks[i][comSlacks[i].length-1] = semSlacks[i][(semSlacks[i].length)-1]; //preenche a coluna dos b's
        }
        for (int i = 0; i < comSlacks.length-1; i++) {
            comSlacks[i][semSlacks[0].length-1+i] = 1; //preenche as folgas
        }
    }
    
    //</editor-fold>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Extrai os valores das variaveis nas linhas das restricoes
     *
     * @param nLinhas
     * @param linhas
     * @param nColunas
     * @param nVariaveis
     * @param variaveis
     * @param matrizOutput
     */
    public static void getValoresLinhasDasRestricoes(int nLinhas,
            String[] linhas, int nColunas, int nVariaveis, 
            String[][] variaveis, double[][] matrizOutput) {

        try {

            for (int i = 1; i < nLinhas; i++) {

                String linha = linhas[i];
                
                double[] linhaParaMatriz = new double[nColunas];

                //<editor-fold defaultstate="collapsed" desc="Variaveis">
                double[] valores = getValoresDasVariaveisEmLinhaDeRestricoes(variaveis, linha);
                for (int j = 0; j < nVariaveis; j++) {
                    linhaParaMatriz[j] = valores[j];

                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Ultima Coluna">
                linhaParaMatriz[nColunas - 1] = getUltimaColuna(linha);
                //</editor-fold>

                matrizOutput[i-1] = linhaParaMatriz;
            }
        } catch (NumberFormatException nfe) {

            System.out.println(nfe);
        }
    }

    /**
     * Extrai o valor referente a ultima comluna da matriz final
     *
     * @param linha
     * @return
     */
    private static double getUltimaColuna(String linha) {

        //TODO: ver se é preciso fazer tb por =
        double output;
        int index = -1;

        for (String charMaior : MAIOR_OU_IGUAL) {
            if (linha.indexOf(charMaior) > 0) {
                index = linha.indexOf(charMaior) + charMaior.length();
                break;
            }
        }
        if (index == -1) {
            for (String charMenor : MENOR_OU_IGUAL) {
                if (linha.indexOf(charMenor) > 0) {
                    index = linha.indexOf(charMenor) + charMenor.length();
                    break;
                }
            }
        }
        if (index != -1) {
            output = Double.parseDouble(linha.substring(index).trim());
        } else {
            output = 0;
        }

        return output;
    }

    /**
     * Extrai o valor de todas as variaveis que sao encontradas
     *
     * @param variaveis
     * @param linha
     * @return
     */
    public static double[] getValoresDasVariaveisEmLinhaDeRestricoes(String[][] variaveis, String linha) {
        
        double[] output = new double[variaveis.length];
        int indexInicial = 0;
        for (int i = 0; i < variaveis.length; i++) {
            int index = -1;
            if (linha.contains(variaveis[i][0])) {

                index = linha.indexOf(variaveis[i][0]);

                String temp = linha.substring(indexInicial, index).trim();
                temp = temp.replaceAll(" ", "");

                indexInicial = index + variaveis[i][0].length();

                if (temp.matches(".*\\d+.*")) {
                    output[i] = Double.parseDouble(temp);
                } else {
                    if (temp.contains("-")) {
                        output[i] = -1;
                    } else {
                        output[i] = 1;
                    }
                }
            } else {
                output[i] = 0;
            }
        }

        return output;
    }

    /**
     * Coloca a primeira linha na matriz
     *
     * @param nColunas
     * @param variaveis
     * @return
     */
    public static double[] setPrimeiraLinha(int nColunas, String[][] variaveis) {

        double[] output = new double[nColunas];

        for (int i = 0; i < variaveis.length; i++) {
            try{
            
                double doubleValue = Double.parseDouble(variaveis[i][1]);
                output[i] = doubleValue;
                
            }catch (NumberFormatException nfe){
                
                Writer.forcarSaida(
                        StringsLib.Erro_ParseStringParaDouble 
                                + nfe.getMessage(), Writer.Escritor);
            }
        }
        
        if(MAXIMIZACAO){
        
            output = Utils.negarArray(output);
        }
        
        return output;
    }

    /**
     * Cria um array que vai servir para ir guardando as variaveis de resultado
     * @param nResultadosEserados
     * @return lista ordenada de nomes de variaveis/slacks/Z
     */
    private static String[] criarColunaResultados(int nResultadosEserados) {
        String[] output = new String[nResultadosEserados + 1];

        for (int i = 0; i < nResultadosEserados; i++) {
            if (i == nResultadosEserados - 1) {    
                output[i] = "Z ";
            }else{
                output[i] = "F" + (i + 1);
            }
        }        
        return output;
    }

    /**
     * Determina se o problema em questão é de maximização ou de minimização
     * Se detetar uma inconsitencia de simbolos nas linhas, forca o programa a parar
     * @param linhas
     * @return 
     */
    public static boolean eProblemaDeMaximizacao(String[] linhas) {
    
        boolean output = true;
        int trigger = 0;
        for (int i = 1; i < linhas.length; i++) {
            
            if(trigger == 0){
                if(stringContemElelmentoDeArray(linhas[i], MAIOR_OU_IGUAL)){
                    output = true;
                    trigger = 1;
                }else if(stringContemElelmentoDeArray(linhas[i], MENOR_OU_IGUAL)){
                    output = false;
                    trigger = 2;
                }
            }else{
            
                if(trigger == 1){
                
                    if(stringContemElelmentoDeArray(linhas[i], MENOR_OU_IGUAL)){
                        Writer.forcarSaida(StringsLib.Erro_InconsistenciaOperador, Writer.Escritor);
                    }
                    
                }else{
                
                                    
                    if(stringContemElelmentoDeArray(linhas[i], MAIOR_OU_IGUAL)){
                        Writer.forcarSaida(StringsLib.Erro_InconsistenciaOperador, Writer.Escritor);
                    }
                }
            }   
        }
        return output;
    }

    /**
     * Preenche os slacks na tabela simplex. TODO: alterar conforme 
     * Colaca o valor 1 nos espaços referentes a slacks
     * @param matrizOutput
     * @param colunaInicio
     * @param nColunas 
     */
    public static void preencherSlacks(double[][] matrizOutput,
            int colunaInicio, int nColunas) {
        int linha = 0;
        for (int coluna = colunaInicio; coluna < nColunas - 1; coluna++){
            matrizOutput[linha][coluna] = 1;
            linha++;
        }
    }
  
    /**
     * Processo necessário para a minimização
     * Acrescenta colunas entre antes da ultima coluna
     * @param matrizOutput
     * @param nSlacks
     * @return 
     */
    public static double[][] acrescentarColunasDeSlacks(double[][] matrizOutput, int nSlacks) {
        
        int linhas = matrizOutput.length;
        int colunas = matrizOutput[0].length;
        
        double[][] output = new double[linhas][colunas + nSlacks];
        for (int i = 0; i < linhas;i++) {
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
}
