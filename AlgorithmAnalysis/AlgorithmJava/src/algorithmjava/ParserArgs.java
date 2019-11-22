package algorithmjava;

import java.io.File;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class ParserArgs {
    private final String[] args;
    private final Options options;
    private final HelpFormatter formatter;
    private final CommandLineParser parser;
    private String scale;  
    private String message;
    
    public ParserArgs(String[] args){
        this.args = args;
        this.options = new Options();
        this.formatter = new HelpFormatter();
        this.parser = new DefaultParser();
        init();
    }
    
    private void init(){
        message = "\n\nThe program calculates Gauss-Jordan of a matrix. For the program to run\n"
                + "correctly, there should exist a file in the project which should contain\n"
                + "the set of vectors that form the matrix. For ease, FileGenerator creates\n"
                + "them. These vectors are ordered and named as follows: fichVect[0-9+].txt.\n"
                + "There is an '-a' option in which the vectors are treated as independent\n"
                + "polynomials and are summed.\n"
                + "For further information, use the option '-h'.\n\n";
        Option help = new Option("h", "help", false, "Display help usage");
        Option printIn = new Option("di", "input", false, "Display input data");
        Option printOut = new Option("do", "output", false, "Display ouput data");
        Option addPol = new Option("a", "add", false, "Add all polynomials");
        Option printTime = Option.builder("dt")
                          .longOpt("time")
                          .hasArg()
                          .optionalArg(true)
                          .desc("Display taken time in seconds or (arg [ms | ns])")
                          .build();
        Option folder = Option.builder("f")
                       .longOpt("folder")
                       .hasArg()
                       .desc("Read vectors from a Folder")
                       .build();
        
        options.addOption(help);
        options.addOption(printIn);
        options.addOption(printOut);
        options.addOption(printTime);
        options.addOption(folder);
        options.addOption(addPol);
    }
    
    public void parserArg(){          
        try {
            CommandLine cmd = parser.parse(options, args);
            
            if(cmd.hasOption("h")){
                formatter.printHelp( "java -jar *.jar [-h][-f PathDir [-a][-di][-do][-dt [s|ms|ns]]]"+message, options );
            }else if(cmd.hasOption("f")){
                String fich = cmd.getOptionValue("f");
                fileOption(fich, cmd);
            }else{
                error("");
            }
            
        } catch (ParseException exp) {
            error("Fallo de parseo: " + exp.getMessage());           
        }
    }
    
    private void fileOption(String fich, CommandLine cmd){
        if (fich != null && new File(fich).exists() && new File(fich).isDirectory()){
            ReadFile fichero = new ReadFile(fich);
            float[][] vector = fichero.readFolder();
            
            if (cmd.hasOption("a")){
                AddPoly addP = new AddPoly(vector, vector[0].length);
                addP.add();
                
                if(cmd.hasOption("di")){
                    System.out.println("Start Matrix is : "); 
                    new SolveEquation(vector, vector.length).PrintMatrix();
                    System.out.println();
                }
                
                if(cmd.hasOption("do")){
                    System.out.print("Result is: ");
                    addP.printPoly();
                    System.out.println();
                }
                
                if(cmd.hasOption("dt")){
                    scale = cmd.getOptionValue("dt");
                    addP.printTime(scale);
                }
                
            }else{  
                SolveEquation resSolve = new SolveEquation(vector, vector.length); 
                
                if (cmd.hasOption("di")){
                    System.out.println("Start Matrix is : "); 
                    resSolve.PrintMatrix();
                    System.out.println();
                } 
                
                if (resSolve.PerformOperation() == 1) resSolve.CheckConsistency();            
                
                if (cmd.hasOption("do")){
                    resSolve.PrintResult();
                    System.out.println();
                }
                
                if (cmd.hasOption("dt")){
                    scale = cmd.getOptionValue("dt");
                    resSolve.printTime(scale);
                } 
            }
        }                 
    }  
    
    private void error(String error){
        System.out.println(error);
        formatter.printHelp( "java -jar *.jar [-h][-f PathDir [-a][-di][-do][-dt [s|ms|ns]]]"+message, options );
        System.exit(1);
    }
}
