/*
 * Classe com métodos para leitura e extração de dados de ficheiros
 */
package simplex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Grupo 9
 */
public class Reader { 
    
    public static String[] lerFicheiro(String caminhoDoFicheiroInput){
    
        String[] linhas = null;
        
        File file = new File(caminhoDoFicheiroInput);
        
        if(file.exists()){
        
            try{
            
                Scanner scanner = new Scanner(file);
                String texto = "";
                
                while(scanner.hasNextLine()){
                
                    if(!texto.equals("")){
                        texto+= "#";//mete um delimitador no fim da linha 
                    }
                    
                    texto+= scanner.nextLine().trim();
                }
                
                if(!texto.equals("")){
                
                    linhas = texto.split("#");//separa por delimitador e conforme o tamanho do string, conforme o numero de linhas
                }
            
            }catch (FileNotFoundException fnfe){
                
                System.out.println("Houve um problema ao ler o ficheiro.");
            }          
        }
        return linhas;
    }

}
