#include <iostream>
#include <stdlib.h>
#include "IteratorOfCombination.h"
using namespace std;

int main(int argc, char** argv) {
    int* combinacion;
    IteradorCombinaciones it(argc - 1);
    while (!it.ultimaCombinacion()) {
        combinacion = it.siguienteCombinacion();
        for (int i = 0; i < argc - 1; i++) {
            if (combinacion[i] == 1) {
                cout << argv[i + 1] << " ";
            }
        }
        cout << endl;
    }
    return 0;
}


