package algorithmjava;

public class AddPoly {
    private final float[][] vector;
    private float[] sum;
    private final int size;
    private long timeMillis;
    private long timeNano;
    
    public AddPoly(float[][] vector, int size){
        this.vector = vector;
        this.size = size;
    }
    
    public void printTime(String scale){
        System.out.print("For " + vector.length + " vectors -> " + "Taken time: ");
        float print = (float) ((timeMillis == 0)? timeNano/1e9d : timeMillis/1e3d);
        if(scale == null){            
            System.out.printf("%.8f s.%n", print);
        }else{
            switch (scale) {
                case "ns": System.out.println(timeNano + " " + scale + '.');
                break;
                case "ms": System.out.println(timeMillis + " " + scale + '.');
                break;
                default: System.out.printf("%.8f s.%n", print);
                break;
            }  
        }
    }
       
    public void add() { 
        sum = new float[size]; 
        
        long startTN = System.nanoTime();
        long startTM = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            for(int j = 0; j < size-1; j++){                
                sum[i] += vector[j][i];
            } 
        } 
        long endTM = System.currentTimeMillis();
        long endTN = System.nanoTime();
        
        timeMillis = (endTM - startTM);
        timeNano = (endTN - startTN);
    } 
  
    // A utility function to print a polynomial  
    public void printPoly() { 
        int aux = size - 1;
        for (int i = 0; i < size; i++) { 
            System.out.print(sum[i]); 
            if (i != size - 1) { 
                System.out.print("x^" + aux + " + ");
            }
            aux--;                       
        } 
    } 
}
