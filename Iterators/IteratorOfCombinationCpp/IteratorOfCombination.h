#ifndef ITERADORCOMBINACIONES_H
#define	ITERADORCOMBINACIONES_H

#include <iostream>
#include <stdlib.h>

class IteradorCombinaciones {
public:
    IteradorCombinaciones(int);
    bool ultimaCombinacion();
    int* siguienteCombinacion();
private:
    int valorMaximo;
    int valorMinimo;
    int* combinacion;
    int tamano;
};

#endif	/* ITERADORCOMBINACIONES_H */

