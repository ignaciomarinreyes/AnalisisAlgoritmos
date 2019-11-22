#ifndef PARSERARGS_H
#define PARSERARGS_H

#include <string>
#include <iostream>
#include <getopt.h>
#include "longOptions.h"
#include "ReadFile.h"
#include "SolveEquation.h"
#include "AddPoly.h"

class ParserArgs {
    private:
        int argc;
        char** args;       
        std::string fichero;
        std::string scale = "s";
        void print();
        int option_index = 0; 
        std::string usage  = "\nusage: *.exe [-h][-f PathDir [-a][-di][-do][-dt [s|ms|ns]]]"; 
        std::string message = "\nThe program calculates Gauss-Jordan of a matrix. For the program to run\n"
                              "correctly, there should exist a file in the project which should contain\n"
                              "the set of vectors that form the matrix. For ease, FileGenerator creates\n"
                              "them. These vectors are ordered and named as follows: fichVect[0-9+].txt.\n"
                              "There is an '-a' option in which the vectors are treated as independent\n"
                              "polynomials and are summed.\n"
                              "For further information, use the option '-h'.\n";
        std::string add    = " -a,--add           Add all polynomials"; bool bAdd =  false;
        std::string input  = " -i,--input         Display input data"; bool bInput =  false;
        std::string output = " -o,--output        Display output data"; bool bOutput =  false;
        std::string time   = " -t,--time          Display taken time in seconds or ([--time=[ns | ms | s]])"; bool bTime =  false;
        std::string folder = " -f,--folder <arg>  Read vectors from a folder"; bool bFolder =  false;
        std::string help   = " -h,--help          Display help usage"; bool bHelp =  false;
    public:
        ParserArgs(int argcA, char** argsA);
        void parseArg();
        void fileOption(std::string fich);
        void error(std::string error);
};

#endif /* PARSERARGS_H */

