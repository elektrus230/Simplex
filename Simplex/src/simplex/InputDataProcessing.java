/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import static simplex.Reader.lerFicheiro;
import static simplex.Utils.stringContemElelmentoDeArray;
import static simplex.Utils.transporMatriz;

/**
 *
 * @author Grupo 9
 */
public class InputDataProcessing {
    
    public static boolean Maximizacao = true;
    
    //<editor-fold defaultstate="collapsed" desc="OPERADORES">
    public final static String[] MENOR_OU_IGUAL = {">=", "=>", "\u2265", "\u2267"};
    public final static String[] MAIOR_OU_IGUAL = {"<=", "=<", "\u2264", "\u2266"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';
    public final static char BARRA = '/';
    public final static char ESPACO = ' ';

    public static String[] colunaRefResultados;
    public static String[][] variaveis;
    //</editor-fold>

    /**
     * Le id dados do ficheiro e input e devolve a matriz inicial
     * TODO: Fraccoes
     * 
     * @param inputFile
     * @param outputFile
     * @return
     */
    public static double[][] lerDadosEConstruirMatriz(String inputFile, String outputFile) {

        double[][] matrizOutput = null;

        String[] linhas = lerFicheiro(inputFile);

        if (linhas != null) {

            if (linhas.length > 0) {

                Writer.ImprimirDadosIniciais(linhas, outputFile);
                
                variaveis = getVariaveisDaPrimeiraLinha(linhas[0]);
                Maximizacao = eProblemaDeMaximizacao(linhas);
                
                String tipo;
                
                int nLinhas;
                int nColunas;
                int colunaInicioSlack;
                int nSlacks;
                int nVariaveisBasicas;
                
                int nVariaveis = variaveis.length;
                
                if(Maximizacao){

                    tipo = StringsLib.Maximizacao;
                    nLinhas = linhas.length;
                    nColunas = nVariaveis + linhas.length + 1;
                    nSlacks = linhas.length - 1;
                    nVariaveisBasicas = nSlacks;
                    colunaInicioSlack = nVariaveis;
                    
                    
                }else{
                    
                    tipo = StringsLib.Minimizacao;
                    nLinhas = linhas.length;
                    nColunas = nVariaveis + 1;
                    nSlacks = nVariaveis;
                    nVariaveisBasicas = nVariaveis;
                    colunaInicioSlack = nLinhas - 1;
                }
                
                String problema = String.format("O problema actual é um problema de %s.\n",tipo);
                Writer.escreverGenerico(problema,Writer.Escritor);
                
                Simplex.resultados = criarColunaRefResultados(nVariaveisBasicas);
                Simplex.listaDeVariaveis = getListaDeVariaveis();

                matrizOutput = new double[nLinhas][nColunas];
                
                matrizOutput[nLinhas - 1] = setPrimeiraLinha(nColunas, variaveis);
                
                getValoresLinhasDasRestricoes(nLinhas, linhas, nColunas, nVariaveis, variaveis, matrizOutput);
                
                if(!Maximizacao){
                    matrizOutput = transporMatriz(matrizOutput);
                    int tamMat = matrizOutput.length - 1;
                    matrizOutput[tamMat] = Utils.negarArray(matrizOutput[tamMat]);
                    matrizOutput = acrescentarColunasDeSlacks(matrizOutput, nSlacks);
                    nColunas = nVariaveis + nLinhas;
                }
                
                preencherSlacks(matrizOutput, colunaInicioSlack, nColunas);
                
            } else {
                
                Writer.escreverGenerico(StringsLib.Erro_FicheiroVazio,null);
            }
        } else {
            Writer.escreverGenerico(StringsLib.Erro_FicheiroNaoEncontrado,null);
        }
        return matrizOutput;
    }

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

            System.out.println(nfe.getMessage());
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
        
        if(Maximizacao){
        
            output = Utils.negarArray(output);
        }
        
        return output;
    }


    //<editor-fold defaultstate="collapsed" desc="LER 1ª LINHA">
    /**
     * O output vai ser um array que contem 3 valores por variavel encontrada
     * [nome da variavel] [quantidade] [simbolo de positivo ou negativo]
     *
     * @param linha
     * @return
     */
    public static String[][] getVariaveisDaPrimeiraLinha(String linha) {
        String[][] output = null;
        if (linha != null) {       
            int charIndex = 0;
            while (charIndex < linha.length()) {
                char caracter = linha.charAt(charIndex);
                if (eUmaLetra(caracter) && depoisDeUmIgual(linha, charIndex)) {
                    output = Utils.expandirArray(output);
                    extrairValorDaVariavel(charIndex, linha, output);
                    charIndex = extrairNomeDaVariavel(charIndex, linha, output);
                }
                charIndex++;
            }
        }
        return output;
    }

    /**
     * Verifica se o caracter é uma letra
     * @param caracter
     * @return
     */
    public static boolean eUmaLetra(char caracter) {
        boolean output = false;
        if (String.valueOf(caracter).matches("[A-z]")) {
            output = true;
        }
        return output;
    }

    /**
     * Extrai o nome de uma variável
     *
     * @param charIndex
     * @param linha
     * @param varsEncontradas
     * @return
     */
    public static int extrairNomeDaVariavel(int charIndex, String linha, String[][] varsEncontradas) {
        int idx = charIndex;
        String nome = "";
        char carater = linha.charAt(idx);
        while (!nomeDaVariavelTerminou(carater) && idx < linha.length()) {
            carater = linha.charAt(idx);
            if (String.valueOf(carater).matches("[0-z]")) {
                nome += carater;
            }
            idx++;
        }
        int indexInserirVar = varsEncontradas.length - 1;
        varsEncontradas[indexInserirVar][0] = nome;
        return charIndex + nome.length() - 1;
    }

    /**
     * Verifica se um index é após um simbolo =
     *
     * @param linha
     * @param charIndex
     * @return
     */
    public static boolean depoisDeUmIgual(String linha, int charIndex) {
        int idx = linha.indexOf(String.valueOf(IGUAL));
        boolean output = idx > -1 && idx < charIndex;
        return output;
    }

    /**
     * Verifica se o nome de uma variavel terminou
     *
     * @param caracter
     * @return
     */
    public static boolean nomeDaVariavelTerminou(char caracter) {
        boolean output = true;
        String car = String.valueOf(caracter);
        if (!car.matches("\\s+")) {
            boolean eOperador = false;
            for (String MAIOR_OU_IGUAL1 : MAIOR_OU_IGUAL) {
                if (car.equals(MAIOR_OU_IGUAL1)) {
                    eOperador = true;
                    break;
                }
            }
            if (!eOperador) {
                for (String MENOR_OU_IGUAL1 : MENOR_OU_IGUAL) {
                    if (car.equals(MENOR_OU_IGUAL1)) {
                        eOperador = true;
                        break;
                    }
                }
                if (!eOperador) {
                    if (caracter != MAIS && caracter != IGUAL && caracter != MENOS) {
                        output = false;
                    }
                }
            }
        }
        return output;
    }

    /**
     * Eliminar
     * @param charIndex
     * @param linha
     * @param varsEncontradas 
     */
    public static void extrairValorDaVariavel(int charIndex, String linha, String[][] varsEncontradas) {

        int ultimaVarEncontrada = varsEncontradas.length - 1;
        //String[] quantOperador = extrairValorDaVariavel(charIndex, linha);
        String[] quantOperador = extrairValorEOperadorDeVariavel(linha, charIndex);
        varsEncontradas[ultimaVarEncontrada][1] = quantOperador[0];
        varsEncontradas[ultimaVarEncontrada][2] = quantOperador[1];

    }
    //</editor-fold> 

    /**
     * Rotarna um array com os nomes de todas as variaveis do programa
     * @return 
     */
    public static String[] getListaDeVariaveis() {
        String[] output = new String[variaveis.length];
        for (int i = 0; i < variaveis.length; i++) {
            output[i] = variaveis[i][0];
        }
        return output;
    }

    /**
     * Cria um array que vai servir para ir guardando as variaveis de resultado
     * @param nResultadosEserados
     * @return lista ordenada de nomes de variaveis/slacks/Z
     */
    private static String[] criarColunaRefResultados(int nResultadosEserados) {
        String[] output = new String[nResultadosEserados + 1];

        for (int i = 0; i < nResultadosEserados; i++) {
            output[i] = "F" + (i + 1);
        }
        output[nResultadosEserados] = "Z ";
        
        return output;
    }

    /**
     * Determina se o problema em questão é de maximização ou de minimização
     * @param linhas
     * @return 
     */
    private static boolean eProblemaDeMaximizacao(String[] linhas) {
    
        boolean output = true;
        for (String linha : linhas) {
            if(stringContemElelmentoDeArray(linha, MENOR_OU_IGUAL)){
                output = false;
                break;
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
    private static void preencherSlacks(double[][] matrizOutput,
            int colunaInicio, int nColunas) {
        int linha = 0;
        for (int coluna = colunaInicio; coluna < nColunas - 1; coluna++){
            matrizOutput[linha][coluna] = 1;
            linha++;
        }
    }
    
    /**
     * Valida e extrai 
     * @param string
     * @param inicioDaVariavel
     * @return 
     */
    public static String[] extrairValorEOperadorDeVariavel(String string, int inicioDaVariavel){

        String[] output = new String[2];
        int idx = inicioDaVariavel;
        String valor = "1";
        String operador = "+";
        char carater = idx > 0 ? string.charAt(idx-1) : ESPACO;
        
        while(carater != ' '){
        
            idx--;
            carater = idx > 0 ? string.charAt(idx-1) : ESPACO;
        }

        if(idx != inicioDaVariavel){
        
            String content = string.substring(idx,inicioDaVariavel);
            
            if(content.contains("/")){
            
                output = getValorFraccao(content);
                
            }else{
                
                carater = string.charAt(idx);
                if(carater == MAIS || carater == MENOS){
                    idx++;
                    if(carater == MENOS){
                        output[1] = String.valueOf(MENOS);
                    }
                }

                if(idx < inicioDaVariavel){
                    String ss = string.substring(idx, inicioDaVariavel);
                    
                    
                    if(ss.matches("^\\d+(\\.\\d+)*$")){
                        output[0] = ss;
                    }else{
                         Writer.forcarSaida(StringsLib.Erro_LerVariaveis, null);
                    }
                }
            }
        }
        if(output[0] == null){
            output[0] = valor;
        }
        if(output[1] == null){
            output[1] = operador;
        }
        return output;
   }
    
    /**
     * Valida e extrai um valor fraccionario
     * Verifica se é positivo ou negativo.
     * 
     * @param content
     * @return 
     */
    private static String[] getValorFraccao(String content) {
        
        String[] output = new String[2];
        int indexBarra = content.indexOf(String.valueOf(BARRA));
        
        if(content.length() < 3){
            Writer.forcarSaida(StringsLib.Erro_LerValorFraccao, null);
            System.exit(0);
            
        }
        
        if(!content.matches("[0-9+-/]*")){
            
            Writer.forcarSaida(StringsLib.Erro_LerValorFraccao, null);
            System.exit(0);
        }
        
        String preBarra = content.substring(0,indexBarra );
        String posBarra = content.substring(indexBarra + 1, content.length());
        try{
        
            double preBarraDouble = Double.parseDouble(preBarra);
            double posBarraDouble = Double.parseDouble(posBarra);
            double valorAAdicionar = preBarraDouble / posBarraDouble;
            output[0] = String.valueOf(valorAAdicionar);
            
        }catch (Exception ex){
            
            Writer.forcarSaida(StringsLib.Erro_LerValorFraccao, null);
            System.exit(0);
        }
        
        return output;
    }

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
