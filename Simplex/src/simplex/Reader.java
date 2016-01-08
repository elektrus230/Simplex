/*
 * Classe com métodos para leitura e extração de dados de ficheiros
 */
package simplex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static simplex.Simplex.caminhoDoFicheiroInput;
import static simplex.Simplex.caminhoDoFicheiroOutput;

/**
 *
 * @author Grupo 9
 */
public class Reader { 

public static Scanner ler = new Scanner(System.in);
    
    /**
     * este método serve para ler o ficheiro .txt e passar para uma variável
     * @param caminhoDoFicheiroInput
     * @return 
     */
    public static String[] lerFicheiro(String caminhoDoFicheiroInput){
    
        String[] linhas = null;
        
        File file = new File(caminhoDoFicheiroInput);
        
        if(file.exists()){
        
            try{
            
                Scanner scanner = new Scanner(file);
                String texto = "";
                
                while(scanner.hasNextLine()){
                
                    if(!texto.equals("")){
                        texto+= "#";//verifica se a linha do texto é vazia senão
                    }               //for acrescenta um # ao final do String texto
                    
                    texto+= scanner.nextLine().trim();//acrescenta a nova linha ao String texto 
                }
//                scanner.close();    // Dinis, não falta aqui fechar o ficheiro??
                if(!texto.equals("")){
                
                    linhas = texto.split("#");//envia para o array linhas o string texto com split #
                    
                }
            
            }catch (FileNotFoundException fnfe){
                
                System.out.println("Houve um problema ao ler o ficheiro.");
            }          
        }
         for (int i=0;i<linhas.length;i++){
            linhas[i]=linhas[i].toUpperCase();
        }
        return linhas;
    }
    public static void validacaoParametrosEntrada(String[] args) {
        /**
         * modo de validação de entrada de dados através dos argumentos e também
         * para verificar se existem os ficheiros
         */
        if (args.length <= 1) {
            System.out.printf("Não foram introduzidos correctamente os argumentos\n"
                    + "quer utilizar os caminhos pré-definidos pelo programa?\n"
                    + "(s/n)");
            String resposta = ler.next();
            switch (resposta) {
                case "s":
                    caminhoDoFicheiroInput = "Ficheiros de Teste\\input.txt";
                    caminhoDoFicheiroOutput = "Ficheiros de Teste\\output.txt";
                    break;
                case "S":
                    caminhoDoFicheiroInput = "Ficheiros de Teste\\input.txt";
                    caminhoDoFicheiroOutput = "Ficheiros de Teste\\output.txt";
                    break;
                default:
                    System.out.println("Senão quer utilizar os ficheiro pré-definidos, introduza\n"
                            + "correctamente os argumentos na próxima vez que chamar o programa");
                    System.exit(0);

            }

        } else {

            caminhoDoFicheiroInput = args[0];
            caminhoDoFicheiroOutput = args[1];
            File input = new File(caminhoDoFicheiroInput);
             File output = new File(caminhoDoFicheiroOutput);
            if (!input.exists()) {
                System.out.println("\n\nO ficheiro que introduziu como"
                        + "ficheiro de leitura não existe \n"
                        + "verifique esta situação na próxima vez que\n"
                        + "chamar o programa.\n"
                        + "Obrigado desde já:)\n\n\n\n");
                System.exit(0);
            }
            if (!output.exists()) {
                System.out.println("\n\nO ficheiro que introduziu como "
                        + "ficheiro de escrita não existe \n"
                        + "verifique esta situação na próxima vez que\n"
                        + "chamar o programa.\n"
                        + "Obrigado desde já:)\n\n\n\n");
                System.exit(0);
            }
        }
    }
    

}
