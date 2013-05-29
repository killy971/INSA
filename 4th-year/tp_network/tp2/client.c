#include <string.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>

#define TP_CIBLE "localhost"
#define PORT 2000

#define BUF_SIZE 1000

char buffer[BUF_SIZE];

void initBuff(){
	int i;
	char c='a';
	for (i=0; i<BUF_SIZE-1; i++)
	{
		buffer[i]=c;
		c++;
		if (c=='z') c='a';
	}
	buffer[BUF_SIZE-1]=0;
}

int main (int argc, char** argv)
{
	int numSocket;
	int envoye;
	int i;
	struct sockaddr_in identif;
	struct hostent *ret_ghbn;
	
	initBuff();
	
	numSocket  = socket(AF_INET, SOCK_STREAM, 0);
	
	if (numSocket == -1){
		fprintf(stderr, "Erreur lors de la création du socket");
		exit(1);
	}
	
	ret_ghbn=gethostbyname(TP_CIBLE);
	
	identif.sin_family = AF_INET;
	identif.sin_port = htons(PORT);
	identif.sin_addr=*((struct in_addr*)(ret_ghbn->h_addr));
	bzero(identif.sin_zero,8);
	
	connect(numSocket, ((struct sockaddr*) &identif), sizeof(identif));
	
	for (i=0;;i++) {
		envoye = sendto(numSocket, buffer, BUF_SIZE, 0, ((struct sockaddr*) &identif), sizeof(identif));
		printf("%i] %i octets envoyés sur %i\n",i,envoye,BUF_SIZE);
	}
	
	close(numSocket);
	
	return 0;
}
