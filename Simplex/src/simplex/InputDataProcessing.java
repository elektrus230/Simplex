/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import static simplex.Reader.lerFicheiro;

/**
 * TODO: Intrepertar operadores
 *
 * @author Dinis
 */
public class InputDataProcessing {

    //<editor-fold defaultstate="collapsed" desc="OPERADORES">
    public final static String[] MAIOR_OU_IGUAL = {">=", "=>", "\u2265", "\u2267"};
    public final static String[] MENOR_OU_IGUAL = {"<=", "=<", "\u2264", "\u2266"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';

    public static String[] colunaRefResoltados;
    public static String[][] variaveis;
    //</editor-fold>
    
    /**
     * Le id dados do ficheiro e input e devolve a matriz inicial
     * @param inputFile
     * @param outputFile
     * @return 
     */
    public static double[][] lerDadosEConstruirMatriz(String inputFile, String outputFile) {
        
        double[][] matrizOutput = null;
        
        String[] linhas = lerFicheiro(inputFile);
        
        if(linhas != null){
            
            if(linhas.length > 0){
                
                Writer.ImprimirDadosIniciais(linhas, outputFile);

                variaveis = getVariaveisDaPrimeiraLinha(linhas[0]);

                int nLinhas = linhas.length;
                int nColunas = variaveis.length + nLinhas;
                int nVariaveis = variaveis.length;
                int nSlacks = linhas.length - 1;

                Simplex.resultados = criarColunaRefResultados(nSlacks);
                Simplex.listaDeVariaveis = getListaDeVariaveis();

                matrizOutput = new double[nLinhas][nColunas];

                matrizOutput[0] = setPrimeiraLinha(nColunas, variaveis);

                getValoresLinhasDasRestricoes(nLinhas, linhas, nColunas, nVariaveis, variaveis, matrizOutput);

            }else{
                System.out.println("Erro : o ficheiro estava vazio.");
            }
        }else{
                System.out.println("Erro : o ficheiro não foi encontrado.");
        }
        return matrizOutput;
    }
    
    /**
     * Extrai os valores das variaveis nas linhas das restricoes
     * @param nLinhas
     * @param linhas
     * @param nColunas
     * @param nVariaveis
     * @param variaveis
     * @param matrizOutput 
     */
    public static void getValoresLinhasDasRestricoes(int nLinhas, String[] linhas, int nColunas, int nVariaveis, String[][] variaveis, double[][] matrizOutput) {
        
        try {
            
            for (int i = 1; i < nLinhas; i++) {
                
                String linha = linhas[i];
                double[] linhaParaMatriz = new double[nColunas];

                //<editor-fold defaultstate="collapsed" desc="SLACK">
                int indexSlack = i + nVariaveis;
                linhaParaMatriz[indexSlack - 1] = 1;
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Variaveis">
                double[] valores =  getValoresDasVariaveisEmLinhaDeRestricoes(variaveis, linha);
                for (int j = 0; j < nVariaveis; j++) {
                    linhaParaMatriz[j] = valores[j];

                }
                //</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="Ultima Coluna">
                linhaParaMatriz[nColunas - 1] = getUltimaColuna(linha);
                //</editor-fold>

                matrizOutput[i] = linhaParaMatriz;
            }
        } catch (NumberFormatException nfe) {
            
            System.out.println(nfe.getMessage());
        }
    }

    /**
     * Extrai o valor referente a ultima comluna da matriz final
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
     * Extrai o valr de todas as variaveis que sao encontradas
     * @param variaveis
     * @param linha
     * @return 
     */
    public static double[] getValoresDasVariaveisEmLinhaDeRestricoes(String[][] variaveis, String linha) {
        
        double[] output = new double[variaveis.length];
        for (int i = 0; i < variaveis.length; i++) {
            
            int index = -1;
            
            String variavel = variaveis[i][0];
            
            if (linha.contains(variavel)) {

                index = linha.indexOf(variavel);
                
                String valor =  extrairValorDaVariavel(index,linha)[0];
                output[i] = Double.parseDouble(valor);
            }
        }  
        return output;
    }

    /**
     * Coloca a primeira linha na matriz
     * @param nColunas
     * @param variaveis
     * @return 
     */
    public static double[] setPrimeiraLinha(int nColunas, String[][] variaveis) {
        
        double[] output = new double[nColunas];
        for (int i = 0; i < variaveis.length; i++) {
            try {
                double value = Double.parseDouble(variaveis[i][1]);
                output[i] = value == 0 ? 0 : value * -1;
                
            } catch (NumberFormatException nfe) {

                System.out.println(nfe.getMessage());
            }
        }
        return output;
    }
   
    /**
     * Extrai o valor de uma variavel
     * @param charIndex
     * @param linha
     * @return 
     */
    public static String[] extrairValorDaVariavel(int charIndex, String linha) {

        String[] output = new String[2];

        int idx = charIndex - 1;
        String numero = "";
        char carater = idx > -1 ? linha.charAt(idx) : ' ';
        
        while (!nomeDaVariavelTerminou(carater)) {

            if (String.valueOf(carater).matches("[0-9]")) {

                numero = carater + numero;
            }

            idx--;
            carater = idx > -1 ? linha.charAt(idx) : ' ';
        }
        
        if (numero.equals("")) {
            numero = "1";
        }

        String operador = "+";

        if (carater == MENOS) {
            operador = "-";
        }

        output[0] = numero;
        output[1] = operador;
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

            if (linha.contains("=")) {
                
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
        }
        return output;
    }

    /**
     * Verifica se o character é uma letra
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
     * Extrai o nome de uma variavel
     * @param charIndex
     * @param linha
     * @param variaveisEncontradas
     * @return 
     */
    public static int extrairNomeDaVariavel(int charIndex, String linha, String[][] variaveisEncontradas) {
 
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

        int indexsOndeInserirVariavel = variaveisEncontradas.length - 1;
        variaveisEncontradas[indexsOndeInserirVariavel][0] = nome;

        return charIndex + nome.length() - 1;
    }

    /**
     * Verifica se um index é após um simbolo =
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
    
    public static void extrairValorDaVariavel(int charIndex, String linha, String[][] variaveisEncontradas) {

        int ultimaVariavelEncontrada = variaveisEncontradas.length - 1;
        String[] quantOperador = extrairValorDaVariavel(charIndex, linha);
        variaveisEncontradas[ultimaVariavelEncontrada][1] = quantOperador[0];
        variaveisEncontradas[ultimaVariavelEncontrada][2] = quantOperador[1];

    }
    //</editor-fold>

    
    public static String[] getListaDeVariaveis(){
        String[] output = new String[variaveis.length];   
        for(int i = 0; i < variaveis.length; i++)
        {
            output[i] = variaveis[i][0];
        }
        return output;
    }
    
    private static String[] criarColunaRefResultados(int nSlacks) { 
        String[] output = new String[nSlacks + 1];   
        
        //Todo mudar o Z para var dinamica
        output[0] = "Z "; 
        
        
        for(int i = 1; i < nSlacks + 1; i++)
        {
            output[i] = "F" + (i);
        }
        return output;
    }
}
