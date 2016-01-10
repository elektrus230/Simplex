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
public class StringsLib {
    
    //<editor-fold defaultstate="collapsed" desc="CAMINHOS">
    public static final String Path_DefaultOutput = "testfiles\\output.txt";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="NOMES DE FICHEIROS">
    public static final String FileName_DefaultOutput = "\\output.txt";
    //</editor-fold>
        
    //<editor-fold defaultstate="collapsed" desc="MENSAGENS">
    public static final String Msg_Saida = "O programa foi terminado.";
    public static final String Msg_SaidaInesperada = "O programa encontrou um "
            + "erro inesperado.";
    public static final String Msg_SaidaNoOutput = "Não foi possivel "
            + "criar/aceder o ficheiro de output.";
    public static final String Msg_CriarDirOutput = "O ficheiro de output não "
            + "foi encontrado ou ocorreu um erro ao processalo. O programa vai "
            + "criar o ficheiro e os directórios necessários.";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ERROS">
    
    public static final String Erro_LerVariaveis = "Erro ao ler um valor do ficheiro.";
    public static final String Erro_LerValorFraccao = "Erro ao ler um valor fraccionario do ficheiro.";
    public static final String Erro_FicheiroVazio = "Erro : o ficheiro estava vazio.";
    public static final String Erro_FicheiroNaoEncontrado = "Erro : o ficheiro não foi encontrado..";
    public static final String Erro_Escrever = "Surgiu um problema ao escrever para o ficheiro.";
    public static final String Erro_FicheiroInputNaoEncontrado = "O ficheiro de dados não foi encontrado.";
    public static final String Erro_FicheiroOutputNaoEncontrado = "O ficheiro de output não foi encontrado.";
    public static final String Erro_InpuArg = "Não foi possivel ler o caminho do ficheiro de input.";
    public static final String Erro_OutputArg = "Não foi possivel ler o caminho do ficheiro de output.";
    public static final String Erro_CriarFileOutput = "Não foi possivel criar o ficheiro de output.";
    public static final String Erro_SemArgs = "Não foram detectados nenhus argumentos."
            + "Para inicializar este programa, por favor indique o caminho do ficheiro"
            + "de entrada e o caminho do ficheiro de saí ao chamar o programa.";
    //</editor-fold>

}
