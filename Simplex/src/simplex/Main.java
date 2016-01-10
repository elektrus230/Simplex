/**
 * Valida os dados de entrada e interliga as 
 * várias funcionalidades do programa
 */
package simplex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;



/**
 *
 * @author Grupo 9
 */
public class Main {
    
    public static Scanner Leitor = new Scanner(System.in);
    
    public static String inputPath;
    public static String outputPath;
    public static double [][] matrizSimplex;
    public static Formatter writer;
    
    /**
     * Inicia o programa
     * @param args  > 0 = caminho do ficheiro input | 1 -> caminho ficheiro de output
     */
    public static void main(String[] args) {

        inputPath = "testfiles\\input.txt";
        outputPath = "testfiles\\Output.txt";

        //validarInputs(args);
        Writer.escreverHeader(outputPath);    
        
        Simplex.matrizSimplex = InputDataProcessing.lerDadosEConstruirMatriz(inputPath, outputPath);
        if(Simplex.matrizSimplex != null){
            Simplex.executarSimplex(outputPath);
        }else{
            Writer.forcarSaida(StringsLib.Msg_SaidaInesperada, writer);
        }
    }

    /**
     * Valida os argumentos recebidos pelo programa 
     *  - se são nulos
     *  - se são mais que 1
     *  - se são caminhos de ficheiros
     *  - se os ficheir0s existem
     * 
     * Retorna um boolean que indica se é necessário
     * criar um directório de output
     * @param args 
     */
    private static void validarInputs(String[] args) {
        validarArgs(args);
        validarFicheiros();
    }

    //<editor-fold defaultstate="collapsed" desc="Validações iniciais">
    /**
     * Valida se os argumentos recebidos existem e se
     * o numero de argumentos é aceitavel
     * @param args 
     */
    private static void validarArgs(String[] args) {   
        if(args != null && args.length >= 1){   
            if(args[0] != null){ 
                inputPath = args[0];
                if(args.length < 1 && args[1] != null){
                    outputPath = args[1];
                }
            }else{
                Writer.forcarSaida(StringsLib.Erro_InpuArg, null);
            }
        }else{           
            Writer.forcarSaida(StringsLib.Erro_SemArgs, null);
        }
    }
    
    /**
     * Verifica se os ficheiros são válidos e lida com problemas que possam 
     * surgir
     * 
     */
    private static void validarFicheiros() {
        
        //<editor-fold defaultstate="collapsed" desc="Validar ficheiro input">

        if(inputPath != null){
            File inputFile = new File(inputPath);
            if(!inputFile.exists()){
                Writer.forcarSaida(StringsLib.Erro_FicheiroInputNaoEncontrado, null);
            }else{
                //<editor-fold defaultstate="collapsed" desc="Validar ficheiro output">
        
                String outPath;                
                if(outputPath != null){
                    File outputFile = new File(outputPath);                    
                    if(outputFile.exists()){
                        outPath = outputPath;                        
                    }else if(outputFile.isDirectory()){
                        
                        outPath = outputPath + StringsLib.FileName_DefaultOutput; 

                    }else{ 
                        if(!outputFile.mkdirs()){
                            Writer.forcarSaida(StringsLib.Erro_CriarFileOutput, null);
                        }
                        outPath = null;
                    }
                }else{
                    outPath = getDefaultOutputPath(inputPath);
                }
                if(outPath == null){
                    Writer.forcarSaida(StringsLib.Erro_CriarFileOutput, null);
                }
                //</editor-fold> 
            }
        }else{
            Writer.forcarSaida(StringsLib.Erro_InpuArg, null);
        }
        
        try{
            writer = new Formatter(outputPath);
        }catch(FileNotFoundException ex){
            Writer.forcarSaida(StringsLib.Erro_Escrever + "\n" + ex.getMessage(), null);
        }
        //</editor-fold>
    }

    /**
     * Através do ficheiro de input, que se assume já se encontrar válido,
     * extrai a pasta desse ficheiro e cria um ficheiro nesse directorio
     * para os outputs.
     * @param inputPath
     * @return 
     */
    private static String getDefaultOutputPath(String inputPath) {
        
        String defaultPath;
        Writer.escreverGenerico(inputPath, null);
        File inputFile = new File(inputPath);
        String dir = inputFile.getParent();
        File directorio = new File(dir);
        defaultPath = directorio.isDirectory() ? 
                dir + StringsLib.FileName_DefaultOutput : null;
        return defaultPath;
    }
        
    //</editor-fold>

}
