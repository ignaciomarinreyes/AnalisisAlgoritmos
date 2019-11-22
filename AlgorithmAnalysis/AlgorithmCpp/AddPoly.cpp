// Simple C++ program to add two polynomials 
#include "AddPoly.h"

using namespace std;

AddPoly::AddPoly(float** vectorA, int sizeA) {
    vector = vectorA;
    size = sizeA;
}

void AddPoly::printTime(std::string scale){
    printf("For %d vectors -> Taken time: ", size-1); 
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

// A[] represents coefficients of first polynomial 
// B[] represents coefficients of second polynomial 
// m and n are sizes of A[] and B[] respectively 
void AddPoly::add(){  
    sum = new float[size]{0};
    
    // Initialize the porduct polynomial 
    auto startTM = chrono::steady_clock::now();
    for (int i = 0; i < size; i++) {
        for(int j = 0; j < size-1; j++){
            sum[i] += vector[j][i];
        }        
    }
    auto endTM = chrono::steady_clock::now();
    
    timeMS = chrono::duration_cast<chrono::milliseconds>(endTM - startTM).count();
    timeNS = chrono::duration_cast<chrono::nanoseconds>(endTM - startTM).count();
} 

// A utility function to print a polynomial 
void AddPoly::printPoly() { 
    int aux = size - 1;
    for (int i = 0; i < size; i++) { 
	cout << sum[i]; 
	if (i != size - 1) { 
            cout << "x^" << aux << " + ";
        }
        aux--; 
    } 
} 
