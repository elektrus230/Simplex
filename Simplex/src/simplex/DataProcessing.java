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
public class DataProcessing {

    public final static String NUMS = "0123456789";

    //<editor-fold defaultstate="collapsed" desc="OPERADORES">
    public final static String[] MAIOR_OU_IGUAL = {">=", "=>", "\u2265", "\u2267"};
    public final static String[] MENOR_OU_IGUAL = {"<=", "=<", "\u2264", "\u2266"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';

    //</editor-fold>
    public static void mainProcessor(String caminhoDoFicheiroDeInput) {
        String[] array = lerFicheiro(caminhoDoFicheiroDeInput);

        String[][] variaveisDaPrimeiraLinha = getVariaveisDaPrimeiraLinha(array[0]);//vai apanhar as variaveis, valores e operadores na primeira linha

        int numeroDeLinhas = array.length;
        int numeroDeColunas = variaveisDaPrimeiraLinha.length + numeroDeLinhas + 1;
        int numeroDeVariaveisBase = variaveisDaPrimeiraLinha.length;
        int numeroDeSlacks = numeroDeLinhas - 1;
        double[][] matrizInicial = new double[numeroDeLinhas][numeroDeColunas];

        //Preenchimento das slacks
        preencherSlacks(variaveisDaPrimeiraLinha, matrizInicial);

        //preenchimento da linha de Z
        double[] ValorPrimeiraLinha = ValorPrimeiraLinha(numeroDeColunas, variaveisDaPrimeiraLinha);
        for (int i = 0; i < numeroDeVariaveisBase; i++) {
            matrizInicial[i][0] = ValorPrimeiraLinha[i]*-1;
        }

        // Preencher as linhas das restrições na posição das Variaveis base
        for (int i = 1; i < numeroDeLinhas; i++) { //começa a ler na linha 1, sendo a 0 a linha de Z
            String[][] variaveisDasVariaveisBase = getVariaveisDasRestricoes(array[i]);
            double[] valorPorLinha = new double[variaveisDaPrimeiraLinha.length];
            for (int j = 0; j < variaveisDaPrimeiraLinha.length; j++) {
                try {
                    valorPorLinha[j] = Double.parseDouble(variaveisDasVariaveisBase[j][1]);
                } catch (NumberFormatException nfe) {

                }
            }

        }

    }

        // Prencher a coluna do b
//        for (int i=1; i<numeroDeLinhas;i++){
//            double[] b= new double [numeroDeLinhas-1];
//           b[i] = Double.parseDouble(array.substring(array.('MENOR_OU_IGUAL') + 1).trim());
//            matrizInicial[i][numeroDeColunas]= b[i];
//       }
//}
    public static double[] ValorPrimeiraLinha(int numeroDeColunas, String[][] variaveisDaPrimeiraLinha) {
        double[] valorPrimeiraLinha = new double[variaveisDaPrimeiraLinha.length];
        for (int i = 0; i < variaveisDaPrimeiraLinha.length; i++) { //Preencher a linha de Z
            try {
                valorPrimeiraLinha[i] = Double.parseDouble(variaveisDaPrimeiraLinha[i][1]);
            } catch (NumberFormatException nfe) {

            }
        }
        return valorPrimeiraLinha;
    }

    public static void preencherSlacks(String[][] variaveisDaPrimeiraLinha, double[][] matrizInicial) {
        for (int i = variaveisDaPrimeiraLinha.length + 1; i < (matrizInicial.length - 1); i++) { //preencher os slacks
            matrizInicial[i][i] = 1;
        }
    }

    //<editor-fold defaultstate="" desc="LER 1ª LINHA">
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
                        //Encontrei uma variavel
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
     * O output vai ser um array que contem 1 valor quantidade
     *
     * @param linha
     * @return
     */
    public static String[][] getVariaveisDasRestricoes(String linha) {

        String[][] output = null;

        if (linha != null) {
            int charIndex = 0;

            while (charIndex < linha.length()) {

                char caracter = linha.charAt(charIndex);

                if (eUmaLetra(caracter) && depoisDeUmIgual(linha, charIndex)) {
                    //Encontrei uma variavel
                    output = Utils.expandirArray(output);
                    extrairValorDaVariavel(charIndex, linha, output);
                    charIndex = extrairNomeDaVariavel(charIndex, linha, output);
                }
                charIndex++;
            }

        }
        return output;
    }

    public static boolean eUmaLetra(char caracter) {

        boolean output = false;

        if (String.valueOf(caracter).matches("[A-z]")) {

            output = true;
        }
        return output;
    }

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

    public static boolean depoisDeUmIgual(String linha, int charIndex) {

        int idx = linha.indexOf(String.valueOf(IGUAL));
        boolean output = idx > -1 && idx < charIndex;

        return output;
    }

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

        int idx = charIndex - 1;
        String numero = "";

        char carater = linha.charAt(idx);

        while (!nomeDaVariavelTerminou(carater)) {

            carater = linha.charAt(idx);

            if (String.valueOf(carater).matches("[0-9]")) {
                numero += carater;
            }
            idx--;
        }

        if (numero.equals("")) {
            numero = "1";
        }

        String operador = "+";

        if (carater == MENOS) {
            operador = "-";
        }

        variaveisEncontradas[variaveisEncontradas.length - 1][1] = numero;
        variaveisEncontradas[variaveisEncontradas.length - 1][2] = operador;

    }

    //</editor-fold>
}
