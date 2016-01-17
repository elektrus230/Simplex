/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Formatter;
import static simplex.Main.logPath;

/**
 *
 * @author bruno
 */
public class Logs {

    public static void escreverLog(String mensagem) {

        /**
         * loga para 
         * validação de argumentos
         * validação de ficheiros
         * validação de espaços
         * validação de nullpointexception
         * validação da primeira linha
         * validação das linhas de restrições
         * validação de incógnitas nas linhs das restrições
         * validação de zeros nos divisores
         * 
         * 
         */
        try {

            File log = new File((logPath));
            FileWriter fileWriter = new FileWriter(log, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(buffer);
            printWriter.printf("***---%s---***", mensagem);
            printWriter.close();
        } catch (IOException ioe) {

        }
    }
}
