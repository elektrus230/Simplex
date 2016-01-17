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
public class StringsLib {
    
    //<editor-fold defaultstate="collapsed" desc="CAMINHOS">
    public static final String Path_DefaultOutput = "testfiles\\output.txt";
    public static final String Path_ScriptFile = "script.gp";
    
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
    public static final String Msg_TipoProblemaPPL = "O presente problema é de %s.";
    public static final String Msg_ResultadosEsperados = "\nResultados finais:\n------------------------\n";
    public static final String Msg_DashesParagraph = "------------------------\n";
    public static final String Msg_ProgramaTerminadoSucesso = "Programa terminado com sucesso.\n";
    public static final String Msg_ApresentacaoResultado = "O resultado final de %3s = %s %n";
    public static final String Msg_DefaultGrafico = "Opção selecionada inválida. Formato PNG definido por defeito.";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="ERROS">
    public static  final String Erro_VariavelInfiltrada = "Erro: Foi encontrada uma uma variavel nas linhas de restrições que não existe nas linha de função";
    public static final String Erro_DivisorIgualZero = "Erro: Detectado divisor 0. Impossivel calcular resultado. Por favor altere o seu ficheiro de input.";
    public static final String Erro_InconsistenciaOperador = "Erro: O ficheiro de input continha operadores contraditórios nas diferentes linhas com equações de restrição.";
    public static final String Erro_ParseStringParaDouble = "Erro: Erro ao converter uma string para double.";
    public static final String Erro_LerVariaveis = "Erro: Falha na leitura de um valor do ficheiro.";
    public static final String Erro_LerValorFraccao = "Erro: Falha na leitura de um valor fraccionario do ficheiro.";
    public static final String Erro_LerFicheiro = "Erro : Houve um problema ao ler o ficheiro.";
    public static final String Erro_FicheiroVazio = "Erro : O ficheiro estava vazio.";
    public static final String Erro_FicheiroNaoEncontrado = "Erro : Ficheiro não encontrado.";
    public static final String Erro_Escrever = "Erro: Surgiu um problema ao escrever para o ficheiro.";
    public static final String Erro_FicheiroInputNaoEncontrado = "Erro: O ficheiro de dados não foi encontrado.";
    public static final String Erro_FicheiroOutputNaoEncontrado = "O ficheiro de output não foi encontrado.";
    public static final String Erro_InpuArg = "Erro: Não foi possivel ler o caminho do ficheiro de input.";
    public static final String Erro_OutputArg = "Erro: Não foi possivel ler o caminho do ficheiro de output.";
    public static final String Erro_CriarFileOutput = "Erro: Não foi possivel criar o ficheiro de output.";
    public static final String Erro_SemArgs = "Erro: Não foram detectados nenhus argumentos."
            + "Para inicializar este programa, por favor indique o caminho do ficheiro"
            + "de entrada e o caminho do ficheiro de saí ao chamar o programa.";
    public static final String Erro_RestricoesInvalidas = "Erro: A linhas de restrições não estão validades para a resolução do PPL.";
    public static final String Erro_EscreverHeader = "Erro: Não foi possivel imprimir o header.";
    public static final String Erro_EspaçoesEntreCarateresInvalido = "Erro: Foi detectada uma linha com mais do que 2 espaços de seguida.";
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="REGEX">
    
    public static final String Regex_ValidaPrimeiraLinha = "\\uFEFF?[zZwW]\\s{0,2}=(\\s{0,2}([+-]?\\s{0,2})?\\d*(\\.\\d{1,2})?(/\\d+(\\.\\d{1,2})?)?[xXyY]\\d+)(\\s{0,2}([+-]\\s{0,2})\\d*(\\.\\d{1,2})?(/\\d+(\\.\\d{1,2})?)?[xXyY]\\d+)*";
    public static final String Regex_ValidaLinhaRestricoes = "\\uFEFF?([+-]\\s{0,1})?\\d*(\\.\\d{1,2})?(/\\d{1,2}(\\.\\d{1,2})?)?[xXYY]\\d+\\s{0,2}(\\s{0,2}[+-]\\s{0,2}\\d*(\\.\\d{1,2})?(/\\d+(\\.\\d{1,2})?)?[xXyY]\\d+)*\\s{0,2}(=>|>=|<=|=<|\\u2264|\\u2265)\\s{0,2}([+-]\\s{0,2})?\\d*(\\.\\d{1,2})?(/\\d+(\\.\\d{1,2})?)?";
    public static final String Regex_PrimeiraLinha = "\\s{0,2}((([+-]\\s{0,2})?)(\\d*(\\.\\d{1,2})?(/\\d+(\\.\\d{1,2})?)?))([xXyY]\\d+)";
    public static final String Regex_ValidaMaisDe2Espacos = "([xXyY]\\d+)";

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="OUTROS">
    
    public static final String Maximizacao = "maximizacao";
    public static final String Minimizacao = "minimizacao";
    public static final String PNG = ".png";
    public static final String TXT = ".txt";
    public static final String EPS = ".eps";
    public static final String Header_Console = "-----------------------------------------------\n\t\t   SIMPLEX\n-----------------------------------------------\n";
    public static final String Header_File = "-----------------------------------------------\n\t\t\t\t\tSIMPLEX\n-----------------------------------------------\n";
    
    //</editor-fold>
    
    public static final String Menu_TipoGráfico = "\nPor favor seleccione o formato do ficheiro do gráfico:"
                + "\n\t1 - PNG"
                + "\n\t2 - TXT"
                + "\n\t3 - EPS";
}
