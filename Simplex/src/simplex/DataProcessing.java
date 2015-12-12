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
    
    //</editor-fold>
    
    
    public static String[] getVariaveisDaPrimeiraLinha(String linha){
        
        String[] output = null;
        
        if(linha != null){
            
            if(linha.contains("=")){
        
                int numDeVariaveis = 0;

                int charIndex = 0;

                while(charIndex < linha.length()){

                    char carater = linha.charAt(charIndex);
                    
                    //if(NUMS.contains(carater)){}

                    charIndex++;
                }
            
// "Z = 3X1 + 5X2"
            }
        }
        return output;
    }
}
