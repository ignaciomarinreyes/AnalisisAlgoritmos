#include "ParserArgs.h"

using namespace std;

ParserArgs::ParserArgs(int argcA, char** argsA) {
    argc = argcA;
    args = argsA;
    if(argc <= 1) ParserArgs::error("ERROR! No arguments\n");
}

void ParserArgs::print(){       
    cout << usage << endl;
    cout << message << endl;
    cout << add << endl; 
    cout << input << endl; 
    cout << output << endl; 
    cout << time << endl; 
    cout << folder << endl;   
    cout << help << endl;
}

void ParserArgs::parseArg(){
    int opt;
    while ((opt = getopt_long(argc,args,"hf:aiot::",long_options,&option_index)) != -1){
        switch(opt) {
            case '0': break;
            case 'h':
                bHelp = true; break;
            case 'f': 
                bFolder = true; fichero = optarg; break;
            case 'a': 
                bAdd = true; break;
            case 'i':
                bInput = true; break;
            case 'o':
                bOutput = true; break;
            case 't':
                bTime = true; if(optarg) scale = optarg; break;  
            case '?': 
                if(optopt == 'f') ParserArgs::error(""); break;
            default: ParserArgs::error("ERROR! Wrong option\n");
        }
    }
    
    if(bHelp){
        ParserArgs::print(); 
        exit(0);
    }
    
    if(bFolder){
        ParserArgs::fileOption(fichero);    
    }else{
        ParserArgs::error("ERROR! Folder not selected\n");
    }
}

void ParserArgs::fileOption(std::string fich){
    DIR* directory;
    
    if((directory = opendir(fich.c_str())) != NULL){
        closedir(directory);
        ReadFile* file = new ReadFile(fich);

        float** matrix = file->ReadFolder();
        
        if(bAdd){
            AddPoly* addP = new AddPoly(matrix, file->getOrden()+1);
            addP->add();
            
            if(bInput){
                cout << "Start Matrix is : " << endl; 
                SolveEquation* aux = new SolveEquation(matrix, file->getOrden());
                aux->PrintMatrix();
                cout << endl;
                delete (aux);
            }
            
            if(bOutput){
                cout << "Result is: ";
                addP->printPoly();
                cout << endl;
            }
            
            if(bTime){
                addP->printTime(scale);
            }
            delete (addP);
        }else{
            SolveEquation* resSolve = new SolveEquation(matrix, file->getOrden());
            
            if(bInput){
                cout << "Start Matrix is : " << endl; 
                resSolve->PrintMatrix();
                cout << endl; 
            }
            
            if(resSolve->PerformOperation() == 1) resSolve->CheckConsistency();
            
            if(bOutput){
                // Printing Solutions(if exist) 
                resSolve->PrintResult();
            }
            
            if(bTime){
                resSolve->printTime(scale);
            }
            delete (resSolve);
        } 
        delete (file); 
    }else{
        perror("Unable to open folder");
        exit(1);
    }            
}

void ParserArgs::error(std::string error){
    fprintf(stderr, error.c_str());
    ParserArgs::print();
    exit(1);
}




