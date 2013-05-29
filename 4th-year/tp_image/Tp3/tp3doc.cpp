//=======================================================================
// Nom      : tp3Doc.cpp
//
// But      : Classe document du troisième travail pratique
//
// Auteur   : I. Leplumey
//
// Création : 30/04/98
//=======================================================================

#include "stdafx.h"
#include "tp3.h"
#include <cmath>

#include "tp3Doc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

// Macro d'accès à la matrice image
#define Mat(P,Y,X) P->pImage[(Y)*P->largeur+(X)]

//=======================================================================
//	Associations entre les messages et les traitants de ces messages
//	Genere par Visual C++ et Class Wizard
//=======================================================================

IMPLEMENT_DYNCREATE(CTp3Doc, CDocument)

BEGIN_MESSAGE_MAP(CTp3Doc, CDocument)
	//{{AFX_MSG_MAP(CTp3Doc)
	ON_COMMAND(ID_F1, OnF1)
	ON_COMMAND(ID_F2, OnF2)
	ON_COMMAND(ID_F3, OnF3)
	ON_COMMAND(ID_F4, OnF4)
	ON_COMMAND(ID_F5, OnF5)
	ON_COMMAND(ID_F6, OnF6)
	ON_COMMAND(ID_F7, OnF7)
	ON_COMMAND(ID_BRUIT1, OnBruit1)
	ON_COMMAND(ID_BRUIT2, OnBruit2)
	ON_COMMAND(ID_ORIGINE, OnOrigine)
	ON_COMMAND(ID_RECOPIE, OnRecopie)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

//=======================================================================
// Methode   : 	CTp3Doc::CTp3Doc()
// But       : 	Constructeur
// Parametres: 	-
//=======================================================================
CTp3Doc::CTp3Doc()
{
	pIm=NULL;		// Il n'y a pas d'image a l'origne
	pImBis=NULL;
}

//=======================================================================
// Methode   : 	void CTp3Doc::DeleteContents()
// But       : 	Destruction du contenu d'un document
// Parametres: 	-
//=======================================================================
void CTp3Doc::DeleteContents() {
	// Destruction des images
	if (pIm)		{delete pIm; pIm=NULL;}
	if (pImBis)		{delete pImBis; pImBis=NULL;}

	CDocument::DeleteContents();
}

//=======================================================================
// Methode   : 	CTp3Doc::~CTp3Doc()
// But       : 	Destructeur
// Parametres: 	-
//=======================================================================
CTp3Doc::~CTp3Doc()
{
	DeleteContents();
}

//=======================================================================
// Methode   : 	BOOL CTp3Doc::OnNewDocument()
// But       : 	
// Parametres: 	-
// Retour    : 	Vrai si la fonction s'est bien déroulée
//=======================================================================
BOOL CTp3Doc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	return TRUE;
}

//=======================================================================
// Methode   : 	BOOL CTp3Doc::OnOpenDocument(LPCTSTR lpszPathName)
// But       : 	Gestion de l'ouverture d'un document
// Parametres: 	lpszPathName	: 
// Retour    : 	Vrai si la fonction s'est bien déroulée
//=======================================================================
BOOL CTp3Doc::OnOpenDocument(LPCTSTR lpszPathName) 
{
	// Essai d'ouverture du document au niveau de la classe mere
	if (!CDocument::OnOpenDocument(lpszPathName))
		return FALSE;

	// Affichage du sablier
	CWaitCursor wait;

	TRY
	{	
		// Utilisation d'un objet interface fichier-image
	 	CFichierVersImage fVI;

		// Chargement de l'image
		fVI.charger(lpszPathName);

		// Si l'image chargee possede bien 1 bit par pixel
		if ((fVI.profondeur == 8)&&(fVI.isImageNg()))   
		{
			// Libération de la place préalablement allouée
			DeleteContents();

			// Allocation de la nouvelle image
			pIm=new ImageNg(fVI);

			// Allocation d'une image 8 bits résultat
			pImBis=new ImageNg(pIm->hauteur, pIm->largeur);
		}
		else return FALSE;
	}
 
	CATCH( CException, e )
	{
		AfxMessageBox("ERREUR: survenue dans la procedure [CTp3Doc::OnOpenDocument]");
		EndWaitCursor(); // remet le curseur normal
		return FALSE;
	}
	END_CATCH

	// Remise du curseur en mode normal	
	EndWaitCursor();
	
	return TRUE;  
}

//=======================================================================
//	But:		Fonctions de gestion de la sérialisation
//=======================================================================
void CTp3Doc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
	}
	else
	{
	}
}

//=======================================================================
//	But:		Fonctions correspondant au mode de mise au point
//=======================================================================
#ifdef _DEBUG
void CTp3Doc::AssertValid() const
{
	CDocument::AssertValid();
}

void CTp3Doc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

//=======================================================================
// Methode   : 	void CTp3Doc::OnBruit1()
// But       : 	Ajout de 10% de bruit sel sur l'image d'origine
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnBruit1() 
{	int i;	// Indice pour créer des points bruits

	if (!pIm) return;

	for (i=0; i<(int) (pIm->hauteur*pIm->largeur)/100; i++)
		pIm->pImage[(rand()%pIm->hauteur)*pIm->largeur+(rand()%pIm->largeur)]=0;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnBruit2()
// But       : 	Ajout de 10% de bruit poivre sur l'image d'origine
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnBruit2() 
{	int i;	// Indice pour créer des points bruits

	if (!pIm) return;

	for (i=0; i<(int) (pIm->hauteur*pIm->largeur)/100; i++)
		pIm->pImage[(rand()%pIm->hauteur)*pIm->largeur+(rand()%pIm->largeur)]=255;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnOrigine()
// But       : 	Visualiser l'image d'origine
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnOrigine() 
{
	// Visualiser l'image non filtrée
	Etat=VISORIG;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnRecopie()
// But       : 	Reopie de l'image résultat dans l'image d'origine
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnRecopie() 
{
	int i,j;				// Indices de balayage de l'image d'origine 

	if ((!pIm)||(!pImBis)) return;

	for (i=0; i<(int) pIm->hauteur; i++)
		for (j=0; j<(int) pIm->largeur; j++)
			Mat(pIm,i,j)=Mat(pImBis,i,j);
		
	// Visualiser l'image résultat
	Etat=VISORIG;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF1()
// But       : 	Filtrage dérivateur monodimensionnel (-1, 1)
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF1() 
{
	int i,j;	// Indices de balayage de l'image d'origine 

	if(!pIm) return;

	for (i=0; i<(int) pIm->hauteur; i++)
		for (j=0; j<(int) pIm->largeur-1; j++)
		{
			int val = - Mat(pIm,i,j) + Mat(pIm,i,j+1);
			val = (val + 255) / 2;
			
			Mat(pImBis,i,j) = val;
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF2()
// But       : 	Filtrage passe-bas bidimensionnel	(1,	2,	1,
//													 2,	4,	2,
//													 1,	2,	1)
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF2() 
{
	int i,j;	// Indices de balayage de l'image d'origine 

	if(!pIm) return;

	for (i=1; i<(int) pIm->hauteur-1; i++)
		for (j=1; j<(int) pIm->largeur-1; j++)
		{
			Mat(pImBis,i,j) =  (1 * Mat(pIm,i-1,j-1) + 
								2 * Mat(pIm,i,  j-1) + 
								1 * Mat(pIm,i+1,j-1) + 
								2 * Mat(pIm,i-1,j)   + 
								4 * Mat(pIm,i,  j)   + 
								2 * Mat(pIm,i+1,j)   + 
								1 * Mat(pIm,i-1,j+1) + 
								2 * Mat(pIm,i,  j+1) + 
								1 * Mat(pIm,i+1,j+1))/16.;
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF3()
// But       : 	Détecteur de contours de Roberts 
//				Utilisation de la somme des valeurs absolues comme distance
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF3() 
{
	int i,j;	// Indices de balayage de l'image d'origine 

	if(!pIm) return;
 
	for (i=0; i<(int) pIm->hauteur-1; i++)
		for (j=0; j<(int) pIm->largeur-1; j++)
		{
			int val = (abs(Mat(pIm,i,j) - Mat(pIm,i+1,j+1)) + abs(Mat(pIm,i,j+1) - Mat(pIm,i+1,j)));
			if (val > 255) val = 255;
			Mat(pImBis,i,j) = val;
		}

			// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF4()
// But       : 	Filtrage médian monodimensionnel sur 3 points
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF4() 
{
	int i,j;				// Indices de balayage de l'image d'origine 
	int val1, val2, val3;	// Valeurs de trois points successifs sur une même ligne

	if(!pIm) return;

	for (i=0; i<(int) pIm->hauteur; i++)
		for (j=0; j<(int) pIm->largeur-2; j++)
		{
			val1=Mat(pIm,i,j);
			val2=Mat(pIm,i,j+1);
			val3=Mat(pIm,i,j+2);
			
			int max = MAX(val1, val2);
			int max2 = MAX(max, val3);
			int min = MIN(val1, val2);
			int min2 = MIN(min, val3);

			int val = val1 + val2 + val3 - max2 - min2;

			Mat(pImBis,i,j+1) = val;
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF5()
// But       : 	Filtrage morphologique érosion bidimensionnel sur les 4-voisins
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF5() 
{
	int i,j;				// Indices de balayage de l'image d'origine 

	if(!pIm) return;

	for (i=1; i<(int) pIm->hauteur-1; i++)
		for (j=1; j<(int) pIm->largeur-1; j++)
		{
			int val = MIN(Mat(pIm,i-1,j),
				          MIN(Mat(pIm,i,j-1),
						      MIN(Mat(pIm,i,j),
							      MIN(Mat(pIm,i+1,j),Mat(pIm,i,j+1)))));
			Mat(pImBis,i,j) = val;
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF6()
// But       : 	Filtrage morphologique dilatation bidimensionnel sur les 4-voisins
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF6() 
{
	int i,j;				// Indices de balayage de l'image d'origine 

	if(!pIm) return;

	for (i=1; i<(int) pIm->hauteur-1; i++)
		for (j=1; j<(int) pIm->largeur-1; j++)
		{
			int val = MAX(Mat(pIm,i-1,j),
				          MAX(Mat(pIm,i,j-1),
						      MAX(Mat(pIm,i,j),
							      MAX(Mat(pIm,i+1,j),Mat(pIm,i,j+1)))));
			Mat(pImBis,i,j) = val;
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp3Doc::OnF7()
// But       : 	Gradient morphologique bidimensionnel sur les 4-voisins
// Parametres: 	-
//=======================================================================
void CTp3Doc::OnF7() 
{
	int i,j;				// Indices de balayage de l'image d'origine 

	if(!pIm) return;

	for (i=1; i<(int) pIm->hauteur-1; i++)
		for (j=1; j<(int) pIm->largeur-1; j++)
		{
			int min = MIN(Mat(pIm,i-1,j),
				          MIN(Mat(pIm,i,j-1),
						      MIN(Mat(pIm,i,j),
							      MIN(Mat(pIm,i+1,j),Mat(pIm,i,j+1)))));
			int max = MAX(Mat(pIm,i-1,j),
				          MAX(Mat(pIm,i,j-1),
						      MAX(Mat(pIm,i,j),
							      MAX(Mat(pIm,i+1,j),Mat(pIm,i,j+1)))));
			Mat(pImBis,i,j) = abs(max - min);
		}

	// Visualiser l'image résultat
	Etat=VISRES;
	UpdateAllViews(NULL);
}
