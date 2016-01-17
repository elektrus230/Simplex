/**
 * TODO: StringLib
 * TODO: Comments
 * TODO: Testes
 * TODO: Integrar gnuplot
 * TODO: Colocar novas validações
 * TODO: log - add linhas a ficheiro com 
 */
package simplex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Grupo 9
 */
public class Main {

    public static String inputPath;
    public static String outputPath;
    public static double [][] matrizSimplexInicial;
    public static boolean TEST_MODE = false;

    /**
     * Inicia o programa
     * @param args  > 0 = caminho do ficheiro input | 1 -> caminho ficheiro de output
     */
    public static void main(String[] args) {

        inputPath = "testfiles\\input_Min.txt";
        outputPath = "testfiles\\Output.txt";

        //validarInputs(args);
        
        Writer.escreverHeader(outputPath);
        
        //formatoDoGrafico = getFormatoDoGrafico();
        
        String[] linhasFicheiro = Reader.lerFicheiro(inputPath);
        
        //validarLinhas(linhasFicheiro);
        
        matrizSimplexInicial = InputDataProcessing.extrairValoresDasLinhas(linhasFicheiro);
        
        if(InputDataProcessing.MAXIMIZACAO){
            Simplex.matrizSimplex = InputDataProcessing.criarMatrizComFolgas(matrizSimplexInicial);
        }else{
            Simplex.matrizSimplex = InputDataProcessing.criarMatrizComFolgas(Utils.transporMatriz(matrizSimplexInicial));
        }
        
        if(Simplex.matrizSimplex != null){
            Simplex.executarSimplex(outputPath);
        }else{
            //TODO fix messages
            Writer.forcarSaida(StringsLib.Msg_SaidaInesperada, Writer.Escritor);
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
                    outPath = getCaminhoFicheiroOutput(inputPath);
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
            Writer.Escritor = new Formatter(outputPath);
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
    private static String getCaminhoFicheiroOutput(String inputPath) {
        
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
    
    //<editor-fold defaultstate="" desc="Validacoes a linhas do ficheiro">
    public static void validarLinhas(String[] linhas){
        validarEquacoes(linhas);
        //TODO: add other validations
    }
    
    public static boolean validarEquacoes(String[] leitura) {
        boolean op = false;
        String linhaFuncao = leitura[0];

        String[] linhasRestricao = new String[leitura.length - 1];
        for (int i = 0; i < linhasRestricao.length; i++) {
            linhasRestricao[i] = leitura[i + 1];
        }
        if (validacaoPrimeiraLinha(linhaFuncao) == true && validacaoLinhaRestricoes(linhasRestricao) == true) {
            op = true;
        }else {
            op=false;
            System.exit(0);
        }
        return op;
    }
    
    public static boolean validacaoPrimeiraLinha(String linha) {
        boolean op = false;
        Simplex.variaveis = new String[2];
        Pattern p1linha = Pattern.compile(StringsLib.Regex_ValidaPrimeiraLinha);
        Matcher m = p1linha.matcher(linha);
        op = m.matches();
        return op;
    }
    
    public static boolean validacaoLinhaRestricoes(String[] linhas) {
        boolean op = false;
        Pattern linhaVariaveis = Pattern.compile(StringsLib.Regex_ValidaLinhaRestricoes);
        for (String linha : linhas) {
            Matcher m = linhaVariaveis.matcher(linha);
            op = m.matches();
            if (op == false) {
                Writer.forcarSaida(StringsLib.Erro_RestricoesInvalidas, Writer.Escritor);
            }
        }
        return op;
    }
   
    //</editor-fold>

}
