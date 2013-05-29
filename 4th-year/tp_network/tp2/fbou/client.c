/* TP Rézo 2 */
#include <stdio.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/types.h>


void connexionTCP(int socket,char* name,char* msg, int length)
{
	//récupère la structure qui caractérise la cible
	struct hostent* cible_nom=gethostbyname(name);
	
	//création d'une structure d'identification du destinataire
	struct sockaddr_in cible_struct;
	
	// initialisation des champs de la struct
	cible_struct.sin_family=AF_INET;
	cible_struct.sin_port=htons(1337);
	struct in_addr addresse;
	memcpy(&(addresse.s_addr),cible_nom->h_addr,cible_nom->h_length);
	cible_struct.sin_addr = addresse;
	int i;
	for (i=0; i<8; i++) cible_struct.sin_zero[i]=0;
	
	// on crée la connexion TCP
	int connex = connect(socket, (struct sockaddr*)(&cible_struct), sizeof(struct sockaddr_in));
	printf("valeur connexion %d\n",connex);
	
	int count = 0;
	while (1)
	  {
		//envoi du message
		int envoi = sendto(socket,msg,length,0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
		printf("valeur envoi %d - ",envoi);
		count+=length;
		printf("%d octets envoyés\n",count);
	  }
	
}

int main()
{
	printf("debut\n");
	//création d'une socket
	int socketEnvoi = socket(AF_INET,SOCK_STREAM,0);
	printf("valeur du socket %d \n",socketEnvoi);
	char * message="hello";
	

	//envoi d'un message après avoir créé la connexion
	connexionTCP(socketEnvoi,"localhost",message,strlen(message)+1);
	
	
	//char tampon[10];
	//read(socketEnvoi,tampon,10);
	// fermeture du socket
	close(socketEnvoi);
	//printf("%s\n",tampon);


	
}
