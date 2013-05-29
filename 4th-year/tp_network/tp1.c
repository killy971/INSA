#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

void echoUDP (char* machine, char* msg, int sizeMsg)
{
    int socketEm;
    if (socketEm = socket(AF_INET, SOCK_DGRAM, 0))
    {
        struct sockaddr_in destInfo;
        destInfo.sin_family = AF_INET;
        destInfo.sin_port = htons(7);
        int i;
        for (i=0 ; i<8 ; i++)
        {
            destInfo.sin_zero[i] = 0;
        }
        
        struct hostent* host = gethostbyname(machine);
        
        memcpy(&destInfo.sin_addr, host->h_addr, host->h_length);
        sendto(socketEm, msg, sizeMsg, 0, (struct sockaddr*) &destInfo, sizeof(destInfo));
        char tempRcpt[100];
        int* pointLgAddr = &host->h_length;
        recvfrom(socketEm, tempRcpt, 100, 0, 0, pointLgAddr);
        close(socketEm);
        printf("%s\n", tempRcpt);
    }
    else
    {
        printf("Ca marche pas.");
    }
}

void echoTCP (char* machine, char* msg, int sizeMsg)
{
    int socketEm;
    if (socketEm = socket(AF_INET, SOCK_STREAM, 0))
    {
        struct sockaddr_in destInfo;
        destInfo.sin_family = AF_INET;
        destInfo.sin_port = htons(7);
        int i;
        for (i=0 ; i<8 ; i++)
        {
            destInfo.sin_zero[i] = 0;
        }
        
        struct hostent* host = gethostbyname(machine);
        
        memcpy(&destInfo.sin_addr, host->h_addr, host->h_length);
        
        connect(socketEm, (struct sockaddr*) &destInfo, sizeof(destInfo));

        sendto(socketEm, msg, sizeMsg, 0, (struct sockaddr*) &destInfo, sizeof(destInfo));
        
        char tempRcpt[100];
        int* pointLgAddr = &host->h_length;
        read(socketEm, tempRcpt, 100);
        close(socketEm);
        printf("%s\n", tempRcpt);
    }
    else
    {
        printf("Ca marche pas.");
    }
}

int main ()
{
    //echoUDP("fou", "Echo en UDP", 12);
    echoTCP("fou", "Echo en TCP", 12);
}
