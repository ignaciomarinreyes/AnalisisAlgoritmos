#ifndef OPTION_H
#define OPTION_H

#include <getopt.h>

static struct option long_options[] = {
            {"help",    no_argument,       0, 'h'},
            {"folder",  required_argument, 0, 'f'},
            {"add",     no_argument,       0, 'a'},
            {"input",   no_argument,       0, 'i'},
            {"output",  no_argument,       0, 'o'},
            {"time",    optional_argument, 0, 't'},
            {0, 0, 0, 0} 
        };

#endif /* OPTION_H */

