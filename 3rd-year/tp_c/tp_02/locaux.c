
/* locaux.c*/

int f(int i)
{
   int s;
   if (i==0) s=0;
   else s+=i;
 return s;
}

int g(int i)
{
  return f(i);
}

int main() {
 int i=1;
 int r;


  for( i=0;i<=4;i++){
   printf(" i = %d : %d\n",i,f(i));
   }//for

 printf(" i = %d : %d\n",i,g(i));

}//main




