#include "ReadFile.h"

using namespace std;

ReadFile::ReadFile(std::string folder) {
    path = folder;
    orden = sizeRows();
}

float** ReadFile::ReadFolder(){ 
    
    if ((dir = opendir(path.c_str())) != NULL){
        float** vector = new float*[orden];
        int i = 0, j;
        
        while ((ent = readdir(dir)) != NULL){                
            std::string auxPath = path + "/" + ent->d_name; 
            
            if(ent->d_type == DT_REG){
                
                std::ifstream myfile;
                myfile.open(auxPath.c_str());
                
                if (myfile.is_open()) {
                    j = 0;
                    std::getline(myfile,line);                    
                    vector[i] = new float[std::stoi(line)];
                    while(std::getline(myfile,line)){
                        vector[i][j] = std::atof(line.c_str());
                        j++;
                    }
                    myfile.close();
                }else{
                    cout << "Unable to open file ->" << ent->d_name << '\n';
                }                 
                i++;
            } 
        }
        closedir(dir);
        return vector;
    }else{
        perror("Unable to open folder");
        exit(1);
    }  
}

int ReadFile::getOrden(){
    return orden;
}

int ReadFile::sizeRows() {
    int rowsV = 0;
    if ((dir = opendir(path.c_str())) != NULL){        
        while ((ent = readdir(dir)) != NULL){    
            if(ent->d_type == DT_REG) rowsV++;
        }
        return rowsV;
    }else{
        perror("Unable to open folder");
        exit(1);
    } 
}



