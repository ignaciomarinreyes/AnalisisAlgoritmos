// C++ Implementation for Gauss-Jordan 
// Elimination Method 
#include "SolveEquation.h"

using namespace std; 

SolveEquation::SolveEquation(float **vectorA, int ordenA){    
    orden = ordenA;
    flag = 0;
    vector = vectorA;
}

void SolveEquation::printTime(std::string scale){
    printf("For %d vectors -> Taken time: ", orden); 
    float print = (float) ((timeMS == 0)? timeNS/1e9 : timeMS/1e3);
    if(scale.empty()){
        printf("%.8f s.\n", print);
    }else{
        if(scale == "ns"){
            cout << timeNS << " " << scale << '.' << endl;
        }else if(scale == "ms"){
            cout << timeMS << " " << scale << '.' << endl;
        }else{
            printf("%.8f s.\n", print);
        }
    }
} 

// Function to print the matrix 
void SolveEquation::PrintMatrix(){ 
    for (int i = 0; i < orden; i++) { 
        cout << "| ";
	for (int j = 0; j <= orden; j++){ 
            cout << vector[i][j] << " | "; 
        }
        cout << endl; 
    } 
} 

// function to reduce matrix to reduced 
// row echelon form. 
int SolveEquation::PerformOperation() { 
    int i, j, c, k;  
	
    // Performing elementary operations 
    auto startTM = chrono::steady_clock::now();
    for (i = 0; i < orden; i++) { 
        if (vector[i][i] == 0) { 
            c = 1; 
                
            while (vector[i + c][i] == 0 && (i + c) < orden) c++;
                  
            if ((i + c) == orden) { 
                flag = 1; 
                break; 
            } 
                    
            for (j = i, k = 0; k <= orden; k++){ 
                swap(vector[j][k], vector[j+c][k]); 
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
    auto endTM = chrono::steady_clock::now();
    timeMS = chrono::duration_cast<chrono::milliseconds>(endTM - startTM).count();
    timeNS = chrono::duration_cast<chrono::nanoseconds>(endTM - startTM).count();
    return flag; 
} 

// Function to print the desired result 
// if unique solutions exists, otherwise 
// prints no solution or infinite solutions 
// depending upon the input given. 
void SolveEquation::PrintResult(){ 
    cout << "Result is : "; 

    if (flag == 2){	 
        cout << "Infinite Solutions Exists" << endl;	 
    }else if (flag == 3){	 
        cout << "No Solution Exists" << endl; 
        // Printing the solution by dividing constants by 
	// their respective diagonal elements 
    }else{ 
        for (int i = 0; i < orden; i++){		 
            cout << vector[i][orden] / vector[i][i] << " ";
        }
        cout << endl;
    } 
} 

// To check whether infinite solutions 
// exists or no solution exists 
void SolveEquation::CheckConsistency(){ 
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

