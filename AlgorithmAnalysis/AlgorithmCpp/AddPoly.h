#ifndef ADDPOLY_H
#define ADDPOLY_H

#include <iostream> 
#include <chrono>
#include <string>

class AddPoly {
    private:
        float **vector;
        float *sum;
        int size;
        long int timeMS;
        long int timeNS;
    public:
        AddPoly(float ** vectorA, int sizeA);
        void printTime(std::string scale);
        void add();
        void printPoly();
};

#endif /* ADDPOLY_H */

