/* TP R�o 2 */
#include <stdio.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/types.h>

#define TAILLE_TAMPON 4096

void connexionTCP(int socket,char* name)
{

	//r�up�e la structure qui caract�ise la cible
	struct hostent* cible_nom=gethostbyname(name);
	
	//cr�tion d'une structure d'identification du destinataire
	struct sockaddr_in cible_struct;
	
	// initialisation des champs de la struct
	cible_struct.sin_family=AF_INET;
	cible_struct.sin_port=htons(119);
	struct in_addr addresse;
	memcpy(&(addresse.s_addr), cible_nom->h_addr, cible_nom->h_length);
	cible_struct.sin_addr = addresse;
	int i;
	for (i=0; i<8; i++) cible_struct.sin_zero[i]=0;
	
	// on cr� la connexion TCP
	int connex = connect(socket, (struct sockaddr*)(&cible_struct), sizeof(struct sockaddr_in));
	printf("valeur connexion %d\n",connex);
        
        char tampon[TAILLE_TAMPON];
	// Communication SMTP
        char * message1;
        int taille;
        
        /*
        message1="LIST\r\n";
        sendto(socket,message1,strlen(message1),0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
        while((taille = read(socket,tampon,TAILLE_TAMPON)) != 0)
        {
            printf("%.*s",taille,tampon);
}*/
        
        message1="GROUP fr.comp.ia\r\n";
        sendto(socket,message1,strlen(message1),0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
        taille = read(socket,tampon,TAILLE_TAMPON);
            printf("%.*s",taille,tampon);
        message1="ARTICLE 1249\r\n";
        sendto(socket,message1,strlen(message1),0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
        while((taille = read(socket,tampon,TAILLE_TAMPON)) != 0)
        {
            printf("%.*s",taille,tampon);
        }
        message1="QUIT\r\n";
        sendto(socket,message1,strlen(message1),0,(struct sockaddr*)(&cible_struct),sizeof(struct sockaddr_in));
        while((taille = read(socket,tampon,TAILLE_TAMPON)) != 0)
        {
            printf("%.*s",taille,tampon);
        }
        
}

int main()
{
	printf("debut\n");

	int socketEnvoi = socket(AF_INET,SOCK_STREAM,0);
	printf("valeur du socket %d \n",socketEnvoi);
	
	//envoi d'un message apr� avoir cr� la connexion
	connexionTCP(socketEnvoi,"stang.ens.insa-rennes.fr");
	
	// fermeture du socket
	close(socketEnvoi);
}




