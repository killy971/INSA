// filename="main.cpp"
//====================

#include "stdafx.h"
#include <math.h>
#include <stdio.h>
#include <stddef.h>
#include <stdlib.h>
#include <string.h>
//#include <strings>
#include <iostream>
#include <iomanip>
#include <fstream>
#include <strstream>

#include "Ensemble.h"

using namespace std;
Ensemble<int> lire(char* fname) {
  Ensemble<int> res;
  ifstream input(fname,ios::in);
  if (!input) {
    cerr << "Erreur de lecture de " << fname << endl;
  } else {
    input >> res;
  }
  input.close();
  return res;
}

int _tmain(int argc, _TCHAR* argv[])
{
  Ensemble<int> e1 = lire("ensemble1.txt");
  Ensemble<int> e2 = lire("ensemble2.txt");
  std::cout << e1;
  std::cout << e2;

  std::cout << "e1 = " << e1 << endl;
  std::cout << "e2 = " << e2 << endl;
  cout << "Union        :: " << (e1 + e2) << endl;
  cout << "Intersection :: " << (e1 * e2)<< endl;
  cout << "Soustraction :: " << (e1 - e2)<< endl;
  cout << "Difference   :: " << (e1 / e2)<< endl;
  while(1){}
  return 0;
}
