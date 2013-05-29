
/* tableau.c*/
int t[]={1,2,3,4};
int n=5 ;

int main() {
 	int i;
  	printf(" n = %d \n",n);
	for( i=0;i<=4;i++){
   		t[i]++;
	}//for

  	for( i=0;i<=4;i++){
   		printf(" i = %d : %d\n",i,t[i]);
	}//for
	printf(" n = %d \n",n);
}//main


