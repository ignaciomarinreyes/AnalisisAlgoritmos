#ifndef SOLVEEQUATION_H
#define SOLVEEQUATION_H

#include <bits/stdc++.h> 
#include <iostream>
#include <string>
#include <chrono>

class SolveEquation
{
    private:
        float **vector;
        int orden;
        int flag;
        long int timeNS;
        long int timeMS;
    public:
        SolveEquation(float **vectorA, int ordenA);
        void printTime(std::string scale);
        void PrintMatrix();
        int PerformOperation();
        void PrintResult();
        void CheckConsistency();
};

#endif /* SOLVEEQUATION_H */

