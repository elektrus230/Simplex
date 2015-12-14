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
    
    public static String[] lerFicheiro(String caminhoFicheiroInput){
    
        String[] linhas = null;
        
        File file = new File(caminhoFicheiroInput);
        
        if(file.exists()){
        
            try{
            
                Scanner scanner = new Scanner(file);
                String texto = "";
                
                while(scanner.hasNextLine()){
                
                    if(!texto.equals("")){
                        texto+= "#";
                    }
                    
                    texto+= scanner.nextLine().trim();
                }
                
                if(!texto.equals("")){
                
                    linhas = texto.split("#");
                }
            
            }catch (FileNotFoundException fnfe){
                
                System.out.println("Houve um problema ao ler o ficheiro.");
            }          
        }
        return linhas;
    }

}
