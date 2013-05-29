/*pointeur.c*/
int n = 5;
int * pt1  = (int *) 5 ;
int * pt2n = &n ; 
int * pt3n = &n ;
int * pt0;
int main(){

  int * pt4;
  n=6;
  *pt2n=7;
  printf(" pt1  = %u\n",pt1);
  printf(" pt2n = %u\n",pt2n);
  printf(" pt3n = %u\n",pt3n);
  printf(" pt0  = %u\n",pt0);
  printf(" pt4  = %u\n",pt4);
  printf(" n    = %d\n",n);
  printf("*pt2n = %d\n",*pt2n);
  printf("*pt3n = %d\n",*pt3n);
  printf(" *pt4 = %d\n",*pt4);
  printf(" *pt1 = %d\n",*pt1);

}//main
