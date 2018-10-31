all:
	$gcc -g cmix.c func.S -Wall -o cmix	
	$gcc -nostdlib amix.S -Wall -o amix
    
clean:
	rm -f cmix.o cmix
	rm -f amix.o amix