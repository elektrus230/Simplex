/*
 * Classe com métodos para leitura e extração de dados de ficheiros
 */
package simplex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Grupo 9
 */
public class Reader {
    
    public static final String SYMBOLO_SPLIT = "#";
    public static final String EMPTY = "";
    public static Scanner Leitor = new Scanner(System.in);

    /**
     * Este método lê o ficheiro .txt e salva as linha lidas para uma variável
     *
     * @param input
     * @return
     */
    public static String[] lerFicheiro(String input) {
        
        String[] linhas = null;
        
        File fileInput = new File(input);
        
        if (fileInput.exists()) {
            
            try {
                
                Scanner scanner = new Scanner(fileInput);
                String textoL = SYMBOLO_SPLIT;
                String textoS = EMPTY;
                while (scanner.hasNextLine()) {
                    textoL = scanner.nextLine().trim();
                    
                    if (!textoL.isEmpty()) {
                        textoS += textoL + SYMBOLO_SPLIT;
                    }
                }
                scanner.close();
                if (!textoS.equals(EMPTY)) {
                    
                    linhas = textoS.split(SYMBOLO_SPLIT);
                    
                }

                verificacaoEspacos(linhas);
                
                for (int i = 0; i < linhas.length; i++) {
                    linhas[i] = linhas[i].toUpperCase();
                }
                
            } catch (FileNotFoundException fnfe) {

                //TODO usar exepcao para log
                System.out.println(StringsLib.Erro_LerFicheiro);
            }
        }
        
        return linhas;
    }
    
    /**
     * TODO COmment LOG 
     * @param linhas
     * @return 
     */
    public static boolean verificacaoEspacos(String[] linhas) {
        boolean output = true;
        
        if(linhas != null){
            for (int i = 0; i < linhas.length; i++) {
                Pattern pattern = Pattern.compile(".*\\s{3,}.*");
                Matcher matcher = pattern.matcher(linhas[i]);

                while (matcher.find()) {

                    output = false;
                    if(!Main.TEST_MODE){
                        Writer.escreverLog(StringsLib.Erro_EspaçoesEntreCarateresInvalido, StringsLib.Log_Erro);
                        Writer.forcarSaida(StringsLib.Erro_EspaçoesEntreCarateresInvalido, Writer.Escritor);
                    }
                }      
            }
        }else{
            output = false;
            if(!Main.TEST_MODE){
                Writer.escreverLog(StringsLib.Erro_EspaçoesEntreCarateresInvalido, StringsLib.Log_Erro);
                Writer.forcarSaida(StringsLib.Erro_EspaçoesEntreCarateresInvalido, Writer.Escritor);
            }
           
        }
        return output;
    }
    
}
