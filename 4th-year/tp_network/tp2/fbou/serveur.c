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
	
	//envoi du message
	int envoi = sendto(socket,msg,length,0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
	printf("valeur envoi %d\n",envoi);
	
}

int main()
{
	printf("debut\n");
	//création d'une socket
	int socketEnvoi = socket(AF_INET,SOCK_STREAM,0);
	printf("valeur du socket %d \n",socketEnvoi);
	char * message="hello";
	// structure d'indentification
	struct sockaddr_in cible_struct;
	// initialisation des champs de la struct
	cible_struct.sin_family=AF_INET;
	cible_struct.sin_port=htons(1337);
	struct in_addr addresse;
	addresse.s_addr=(u_long)0;
	cible_struct.sin_addr = addresse;
	int i;
	for (i=0; i<8; i++) cible_struct.sin_zero[i]=0;
	// ///////////
	
	bind(socketEnvoi, (struct sockaddr*)&cible_struct, sizeof(struct sockaddr_in));
	
	listen(socketEnvoi,5);
	
	struct sockaddr emetteur;
	int longueur=sizeof(emetteur);
	accept(socketEnvoi, &emetteur, &longueur);
	while(1);

}


