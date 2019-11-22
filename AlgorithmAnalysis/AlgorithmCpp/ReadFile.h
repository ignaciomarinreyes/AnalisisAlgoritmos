#ifndef READFILE_H
#define READFILE_H

#include <dirent.h>
#include <iostream>
#include <fstream>
#include <string>

class ReadFile 
{
    private:
        std::string path;
        DIR *dir;
        struct dirent *ent;
        std::string line;
        int orden;
        int sizeRows();
    public:
        ReadFile(std::string folder);
        float** ReadFolder();
        int getOrden();
};

#endif /* READFILE_H */

