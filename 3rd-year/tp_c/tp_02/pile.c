
/* pile.c*/

int* max( ){
  int  i=4 ;
  int  j=5 ; 
  int* ptmax;
	if (i>j) ptmax = &i;
	else ptmax = &j;
return ptmax;
}//max

int  bidon(){
  int a=11 ;
  int b=9 ;
  
}//bidon

int main() 
{
 int* ptmax = max();
 printf(" lemax = %d \n",*ptmax);

 bidon();

 printf(" lemax = %d \n",*ptmax);

}//main 





