#include "ParserArgs.h"

using namespace std;

int main(int argc, char** argv){ 
    
    ParserArgs* parser = new ParserArgs(argc, argv);    
    parser->parseArg();
    
    delete parser;
    return 0; 
} 




