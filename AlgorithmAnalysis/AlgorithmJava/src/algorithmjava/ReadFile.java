package algorithmjava;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class ReadFile {
    private final File fileVect;
    private String entero;
    private FileReader f;
    private BufferedReader b;
    
    public ReadFile(String fichS){        
        this.fileVect = new File(fichS);
    }
    
    public float[][] readFolder(){     
        File[] ficheros = fileVect.listFiles();
        float[][] vector = new float[ficheros.length][];        
        int j, i = 0;
         
        for (File fich : ficheros) {  
            j = 0;
            try {
                f = new FileReader(fich);
                b = new BufferedReader(f);
                vector[i] = new float[Integer.parseInt(b.readLine())];
                
                while((entero = b.readLine()) != null) {
                    vector[i][j] = Integer.parseInt(entero);
                    j++;
                }
                b.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Fichero no encontrado: " + ex.getMessage());
                System.exit(1);
            } catch (IOException ex){
                System.out.println("Fallo de IO: " + ex.getMessage());
                System.exit(1);
            }
            i++;
        }        
        return vector;
    }
}
