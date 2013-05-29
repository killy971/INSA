#include <string.h>
#include <unistd.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>

#define TP_CIBLE "clyde.ens.insa-rennes.fr"
#define PORT 25

#define BUF_SIZE 1024
char buffer[BUF_SIZE];

int main (int argc, char** argv)
{
	int numSocket;
	struct sockaddr_in identif;
	struct hostent *ret_ghbn;
	int taille_rep;
	
	
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
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %.*s\n", taille_rep, taille_rep, buffer);
	
	char *msg="HELO ens.insa-rennes.fr\r\n";
	sendto(numSocket, msg, strlen(msg), 0, ((struct sockaddr*) &identif), sizeof(identif));
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %.*s\n", taille_rep, taille_rep, buffer);
	
	sendto(numSocket, "MAIL From: <truc@ens.insa-rennes.fr>\r\n", 38, 0, ((struct sockaddr*) &identif), sizeof(identif));
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %.*s\n", taille_rep, taille_rep, buffer);
	
	sendto(numSocket, "RCPT To: <elebouvi@ens.insa-rennes.fr>\r\n", 40, 0, ((struct sockaddr*) &identif), sizeof(identif));
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %.*s\n", taille_rep, taille_rep, buffer);
	
	sendto(numSocket, "DATA\r\n", 6, 0, ((struct sockaddr*) &identif), sizeof(identif));
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %.*s\n", taille_rep, taille_rep, buffer);
	
	sendto(numSocket, "Subject: Pouet\r\n", 16, 0, ((struct sockaddr*) &identif), sizeof(identif));
	
	sendto(numSocket, "rien\r\n", 6, 0, ((struct sockaddr*) &identif), sizeof(identif));
	
	sendto(numSocket, ".\r\n", 3, 0, ((struct sockaddr*) &identif), sizeof(identif));
	
	sendto(numSocket, "QUIT\r\n", 6, 0, ((struct sockaddr*) &identif), sizeof(identif));
	taille_rep = read(numSocket, buffer, BUF_SIZE);
	printf("reponse : taille %i, %s\n", taille_rep, buffer);
	
	close(numSocket);
	
	return 0;
}
