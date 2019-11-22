package algorithmjava;

public class SolveEquation {
	
    private final float[][] vector;
    private final int orden;
    private int flag;
    private long timeMillis;
    private long timeNano;

    public SolveEquation(float[][] vector, int orden) {
        this.vector = vector;
        this.orden = orden;
        this.flag = 0;
    }
    
    public void printTime(String scale){
        System.out.print("For " + orden + " vectors -> " + "Taken time: ");
        float print = (float) ((timeMillis == 0)? timeNano/1e9d : timeMillis/1e3d);
        if(scale == null){            
            System.out.printf("%.8f s.\n", print);
        }else{
            switch (scale) {
                case "ns": System.out.println(timeNano + " " + scale + '.');
                break;
                case "ms": System.out.println(timeMillis + " " + scale + '.');
                break;
                default: System.out.printf("%.8f s.\n", print);
                break;
            }  
        }
    } 
    
    public int getOrden(){
        return this.orden;
    }
    
    // Function to print the matrix 
    public void PrintMatrix() { 
        for (int i = 0; i < orden; i++) { 
            System.out.print("| ");
            for (int j = 0; j <= orden; j++){ 
                System.out.print(vector[i][j] + " | "); 
            }
            System.out.println(); 
        } 
    } 

    // function to reduce matrix to reduced row echelon form. 
    public int PerformOperation() { 
        int i, j, k, c; 

            // Performing elementary operations 
        long startTN = System.nanoTime();
        long startTM = System.currentTimeMillis();
        for (i = 0; i < orden; i++) {
            if (vector[i][i] == 0) {
                c = 1;

                while (vector[i + c][i] == 0 && (i + c) < orden) c++;		 

                if ((i + c) == orden) { 
                    flag = 1; 
                    break; 
                } 

                for (j = i, k = 0; k <= orden; k++) {
                    float temp = vector[j][k]; 
                    vector[j][k] = vector[j+c][k]; 
                    vector[j+c][k] = temp; 
                } 
            } 

            for (j = 0; j < orden; j++) { 
                // Excluding all i == j 
                if (i != j) { 
                    // Converting Matrix to reduced row 
                    // echelon form(diagonal matrix) 
                    float p = vector[j][i] / vector[i][i]; 

                    for (k = 0; k <= orden; k++){				 
                        vector[j][k] = vector[j][k] - (vector[i][k]) * p;
                    }
                } 
            } 
        } 
        long endTM = System.currentTimeMillis();
        long endTN = System.nanoTime();
        
        timeMillis = (endTM - startTM);
        timeNano = (endTN - startTN);
        return flag; 
    } 

    // Function to print the desired result 
    // if unique solutions exists, otherwise 
    // prints no solution or infinite solutions 
    // depending upon the input given. 
    public void PrintResult(){ 
	System.out.print("Result is : "); 
        
        switch (flag) {
            case 2:
                System.out.println("Infinite Solutions Exists");
                break;
            case 3:
                System.out.println("No Solution Exists");
                
                // Printing the solution by dividing constants by
                // their respective diagonal elements 
                break;
            default: 
                for (int i = 0; i < orden; i++)
                    System.out.print(vector[i][orden] / vector[i][i] + " ");
                break;
        }
    } 

    // To check whether infinite solutions 
    // exists or no solution exists 
    public void CheckConsistency() { 
	int i, j; 
	float sum; 
	
	// flag == 2 for infinite solution 
	// flag == 3 for No solution 
	flag = 3; 
	for (i = 0; i < orden; i++) { 
            sum = 0; 
            for (j = 0; j < orden; j++) sum = sum + vector[i][j]; 
            if (sum == vector[i][j]) flag = 2;	 
	} 
    }  
} 