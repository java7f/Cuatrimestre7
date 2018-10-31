#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

void switchBytes(int, int, int);
void printHigher(unsigned int*, int);
void countOnes(unsigned int);
void lowerToUpper(char *);

int main (int argc, char **argv)
{
	char* cvalue = NULL;
		int c;
			 while ((c = getopt (argc, argv, "o:")) != -1)
				 {
					 switch(c)
					 {
					 case 'o':
						 cvalue = optarg;
						 break;
					 default:
							 abort();
					 }

					 if (strcmp("switch", cvalue)==0)
					 {
						 int x = atoi(argv[3]);
						 int y = atoi(argv[4]);
						 int z = atoi(argv[5]);
						 switchBytes(x,y,z);
						return 0;
					 }
					 if (strcmp("higher", cvalue)==0)
					 {
						 unsigned int* array;
						 array = malloc(argc*4);
						 for (int i = 3; i < argc; i++)
						 {
							 array[(i-3)] = (unsigned int)atoi(argv[i]);
						 }
						 printHigher(array, argc-3);
						 return 0;
					 }
					 if (strcmp("count", cvalue)==0)
					 {
						 unsigned int i = (unsigned int)strtol(argv[3], NULL, 10);
						 countOnes(i);
						 return 0;
					 }
					 if (strcmp("l2u", cvalue)==0)
					 {
						lowerToUpper(argv[3]);
						 return 0;
					 }
				 }
	return 0;
}
