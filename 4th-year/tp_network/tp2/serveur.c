#include <string.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>

#define PORT 2000
#define BUF_SIZE 1024

char buffer[BUF_SIZE];

int main (int argc, char** argv)
{
	int numSocket, sockAccept;
	int recu;
	struct sockaddr_in identif;
	
	numSocket  = socket(AF_INET, SOCK_STREAM, 0);
	
	if (numSocket == -1){
		fprintf(stderr, "Erreur lors de la création du socket");
		exit(1);
	}
	
	identif.sin_family = AF_INET;
	identif.sin_port = htons(PORT);
	bzero(&identif.sin_addr,sizeof(struct in_addr));
	bzero(identif.sin_zero,8);
	
	if (bind(numSocket, ((struct sockaddr*) &identif), sizeof(identif))) {
		fprintf(stderr, "Erreur lors du bind");
		exit(1);
	}
	
	if (listen(numSocket,1)) {
		fprintf(stderr, "Erreur lors du listen");
		exit(1);
	}
	
	sockAccept=accept(numSocket,NULL,0);
	
	if (sockAccept==-1) {
		fprintf(stderr, "Erreur lors du accept");
		exit(1);
	}
	
	printf("pret\n");
	getchar();
	
	for (;;) {
		
		recu = read(sockAccept, buffer, BUF_SIZE);
		buffer[recu]=0;
		
		printf("donnee recue : taille %i, donnee %s\n", recu, buffer);
		
	}
	
	close(numSocket);
	
	return 0;
}
