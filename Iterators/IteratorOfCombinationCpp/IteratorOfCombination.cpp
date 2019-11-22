#include "IteratorOfCombination.h"

using namespace std;

IteradorCombinaciones::IteradorCombinaciones(int N) {
    valorMaximo = 1;
    valorMinimo = 0;
    tamano = N;
    combinacion = new int[tamano];
    for (int i = 0; i < N; i++) {
        combinacion[i] = 0;
    }
}

bool IteradorCombinaciones::ultimaCombinacion() {
    for (int i = 0; i < tamano; i++) {
        if (combinacion [i] == 0) {
            return false;
        }
    }
    return true;
}

int* IteradorCombinaciones::siguienteCombinacion() {
    for (int i = 0; i < tamano; i++) {
        if (combinacion[i] == valorMaximo) {
            combinacion[i] = valorMinimo;
        } else {
            combinacion[i] = combinacion[i] + 1;
            return combinacion;
        }
    }
    return combinacion;
}

