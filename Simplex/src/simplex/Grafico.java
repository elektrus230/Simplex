/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

/**
 *
 * @author Grupo 9
 */
public class Grafico {
    
    public static String formatoDoGrafico;
    
    /**
     * Pede ao utilizador para definir o tipo de ficheiro do grafico.
     * @return 
     */
    private static String getFormatoDoGrafico() {
        
        String output = StringsLib.PNG;
        
        System.out.println("\nPor favor seleccione o formato do ficheiro do gráfico:"
                + "\n\t1 - PNG"
                + "\n\t2 - TXT"
                + "\n\t3 - EPS");
        
        String opcao = Reader.Leitor.next();
        
        switch(opcao){
            case "1":
                output = StringsLib.PNG;
                break;
            case "2":
                output = StringsLib.TXT;
                break;
            case "3":
                output = StringsLib.EPS;
            default:
                System.out.println("Opção selecionada inválida. "
                        + "Formato PNG definido por defeito.");
                break; 
        }
        
        return output;
    }
    
    
    
}
