package filegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class FileGenerator {
    public static void main(String[] args) { 
        Options options = new Options();
        init(options);  
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
                  
        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("h")){
                formatter.printHelp( "java -jar *.jar [-h][-v [digit > 0][-min minValue][-max maxValue]]", options );
            }else if(cmd.hasOption("v")){
                int numVect = Integer.parseInt(cmd.getOptionValue("v"));
                
                if (numVect <= 0){
                    error("El número de vectores debe ser mayor que 0", formatter, options);
                }
                
                int min = (cmd.hasOption("min"))? Integer.parseInt(cmd.getOptionValue("min")) : -100;
                int max = (cmd.hasOption("max"))? Integer.parseInt(cmd.getOptionValue("max")) : 100;
                
                if(min > max) error("El rango superior debe ser mayor que el inferior", formatter, options);
                
                createVectors(numVect, min, max);
                
            }else{
                error("", formatter, options);
            }
        } catch (ParseException exp) {
            error("Fallo de parseo: " + exp.getMessage(), formatter, options);
        }       
    }  
    
    public static void init(Options options){    
        Option vectors = new Option("v", "vectors", true, "Input number of vectors");
        Option minimum = new Option("min", "minimum", true, "Input de minumum value taken");
        Option maximum = new Option("max", "maximum", true, "Input the maximum value taken");
        Option help = new Option("h", "help", false, "Display help message");
        
        options.addOption(vectors);
        options.addOption(minimum);
        options.addOption(maximum);
        options.addOption(help);
    }
    
    public static void error(String error, HelpFormatter formatter, Options options){
        System.out.println(error);
        formatter.printHelp( "java -jar *.jar [-h][-v [digit > 0][-min minValue][-max maxValue]]", options );
        System.exit(1);
    }

    private static void createVectors(int nVect, int min, int max) {
        deleteFiles();
        String ruta = "../../Vectores/fichVect"; 
        int size = nVect+1;
              
        for(int i = 1; i <= nVect; i++){                
            try {
                File fichero = new File(ruta + i + ".txt");
                BufferedWriter bw;
                bw = new BufferedWriter(new FileWriter(fichero));
                bw.write(String.valueOf(size));            
                for (int line = 0; line < size; line++){
                    int n = ThreadLocalRandom.current().nextInt(min, max + 1);
                    bw.newLine();
                    bw.write(String.valueOf(n));
                }
                bw.close();
            }catch(IOException e){
                 System.out.println("Error IO:: " + e.getMessage());
            }        
        }
    }
    
    private static void deleteFiles(){        
        File file = new File("../../Vectores");        
        if(file.mkdir()){
            return;
        }
        File[] ficheros = file.listFiles();
        
        for (File fileAct : ficheros) {
            if(fileAct.isFile() && fileAct.getName().matches("fichVect" + "\\d+" +".txt")){
                fileAct.delete();
            }
        }
    }
}
