void affiche(int i){

  printf("i= %d\n",i);
}//affiche
int n=17;
int t[] = {0,1,2,3,4};
int main (){ 

 int i=-1;
  while (i<5) {
    affiche(t[i++]);
  }//while
}//main


