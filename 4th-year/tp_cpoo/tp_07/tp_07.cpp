// tp_07.cpp : définit le point d'entrée pour l'application console.
//

#include "stdafx.h"

using namespace std;

#include "bandeson.h"
#include "doiseau.h"
#include "dvoiture.h"

int _tmain(int argc, _TCHAR* argv[])
{
	BandeSon b = BandeSon();
	DOiseau *oiseau1 = new DOiseau("o1", "cuicui", "galvarezidé", "alkatrazus");
	b.ajouter(oiseau1);
	b.inventaire();
}

