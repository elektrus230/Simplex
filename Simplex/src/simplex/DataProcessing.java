/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

/**
 *
 * @author Dinis
 */
public class DataProcessing {
    
    public final static String NUMS = "0123456789";
    
    //<editor-fold defaultstate="collapsed" desc="OPERADORES">
    
    public final static String[] MAIOR_OU_IGUAL = {">=","=>","\u2265","\u2267"};
    public final static String[] MENOR_OU_IGUAL = {"<=","=<","\u2264","\u2266"};
    public final static char IGUAL = '=';
    public final static char MENOS = '-';
    public final static char MAIS = '+';
    
    //</editor-fold>
    
    /**
     * O outpur vai ser um array que contem 3 valores por variavel encontrada
     * [nome da variavel] [qunatidade] [simbolo de positivo ou negativo]
     * @param linha
     * @return 
     */
    public static String[][] getVariaveisDaPrimeiraLinha(String linha){
        
        String[][] output = null;
        
        if(linha != null){
            
            if(linha.contains("=")){

                int charIndex = 0;

                while(charIndex < linha.length()){

                    char caracter = linha.charAt(charIndex);
                                        
                    if(eUmaLetra(caracter) && depoisDeUmIgual(linha,charIndex)){
                      
                        charIndex = extrairVariavel(charIndex, linha, output);
                        
                    }   
                    charIndex++;
                }
            
// "Z = 3X1 + 5X2"
            }
            
        }
        return output;
    }

    public static boolean eUmaLetra(char caracter) {
        
        boolean output = false;

        if(String.valueOf(caracter).matches("[A-z]")){

            output = true;
        }
        
        return output;
    }

    public static int extrairVariavel(int charIndex, String linha, String[][] variaveisEncontradas) {
    
        //TODO: extrair método
        //<editor-fold defaultstate="collapsed" desc="Adiciona mais uma posição ao array">
        
        if(variaveisEncontradas == null){
        
            variaveisEncontradas = new String[1][3];
                    
        }else{
        
            int numDeVariaveisJaEncontradas = variaveisEncontradas.length;
            
            String[][] tempArray = new String[numDeVariaveisJaEncontradas + 1][3];
            
            for(int i = 0; i < numDeVariaveisJaEncontradas; i++){
                
                tempArray[i] = variaveisEncontradas[i];         
            } 
            variaveisEncontradas = tempArray;
        }
        
        //</editor-fold>
        
        int idx = charIndex-1;
        String quantidade = "";
        char espaco = ' ';
        while(linha.charAt(idx) != espaco){
            
            char carater = linha.charAt(idx);
            if(String.valueOf(carater).matches("[0-9]")){
                
                quantidade = carater + quantidade;
                
            }else if(carater == MENOS){
                //TODO: é negativo
                break;
            }else{
                //TODO é positvo
            }
            idx--;
        }    
        return charIndex;
    }

    public static boolean depoisDeUmIgual(String linha, int charIndex) {
        
        int idx = linha.indexOf(String.valueOf(IGUAL));
        System.out.println(idx);
        boolean output = idx > -1 && idx < charIndex;
        
        return output;
    }
}
