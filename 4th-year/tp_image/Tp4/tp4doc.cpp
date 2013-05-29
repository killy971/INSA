//=======================================================================
// Nom      : tp4Doc.cpp
//
// But      : Classe document du quatri�eme travail pratique
//
// Auteur   : I. Leplumey
//
// Cr�ation : 10/05/98
//=======================================================================


#include "stdafx.h"
#include "tp4.h"

#include "tp4Doc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//=======================================================================
//	Associations entre les messages et les traitants de ces messages
//	Genere par Visual C++ et Class Wizard
//=======================================================================

IMPLEMENT_DYNCREATE(CTp4Doc, CDocument)

BEGIN_MESSAGE_MAP(CTp4Doc, CDocument)
	//{{AFX_MSG_MAP(CTp4Doc)
	ON_COMMAND(ID_BANDE, OnBande)
	ON_COMMAND(ID_OTSU, OnOtsu)
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

//=======================================================================
// Methode   : 	CTp4Doc::CTp4Doc()
// But       : 	Constructeur
// Parametres: 	-
//=======================================================================
CTp4Doc::CTp4Doc()
{
	pIm=NULL;	// Pas d'image � l'origine
}

//=======================================================================
// Methode   : 	CTp4Doc::~CTp4Doc()
// But       : 	Destructeur
// Parametres: 	-
//=======================================================================
CTp4Doc::~CTp4Doc()
{
}

//=======================================================================
// Methode   : 	BOOL CTp4Doc::OnNewDocument()
// But       : 	Action � effectuer sur la cr�ation d'un nouveau document
// Parametres: 	-
// Retour    : 	Vrai si la fonction s'est bien d�roul�e
//=======================================================================
BOOL CTp4Doc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	return TRUE;
}

//=======================================================================
// Methode   : 	BOOL CTp4Doc::OnOpenDocument(LPCTSTR lpszPathName)
// But       : 	Action � effectuer sur l'ouverture d'un document
// Parametres: 	lpszPathName	: 
// Retour    : 	Vrai si la fonction s'est bien d�roul�e
//=======================================================================
BOOL CTp4Doc::OnOpenDocument(LPCTSTR lpszPathName) 
{
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
			// Lib�ration de la place pr�alablement allou�e
			DeleteContents();

			// Allocation de la nouvelle image
			pIm=new Image8bits(fVI);

			// Allocation d'une image 8 bits r�sultat
			pImBis=new Image8bits(pIm->hauteur, pIm->largeur);
		}
		else return FALSE;
	}
 
	CATCH( CException, e )
	{
		AfxMessageBox("ERREUR: survenue dans la procedure [CTp4Doc::OnOpenDocument]");
		EndWaitCursor(); // remet le curseur normal
		return FALSE;
	}
	END_CATCH

	// Remise du curseur en mode normal	
	EndWaitCursor();

	return TRUE;
}

//=======================================================================
//	But:		Fonctions de gestion de la s�rialisation
//=======================================================================
void CTp4Doc::Serialize(CArchive& ar)
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
void CTp4Doc::AssertValid() const
{
	CDocument::AssertValid();
}

void CTp4Doc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG


//=======================================================================
// Methode   : 	void CTp4Doc::OnBande()
// But       : 	Gestion de la table des couleurs pour l'image des 4 bandes
// Parametres: 	-
//=======================================================================
void CTp4Doc::OnBande() 
{	

	// V�rification qu'une image a �t� charg�e
	if (!pIm) return;

	// R�cup�ration de la palette associ�e � l'image d'origine
	Tdc * pTdcCour = pIm->getPalette();

	// D�finition d'une variable de couleur
	RvbEntree couleurRouge;
	RvbEntree couleurVert;
	RvbEntree couleurBleu;
	RvbEntree couleurBlanc;

	// Exemple d'utilisation :
	couleurRouge.rouge=255; couleurRouge.vert=0; couleurRouge.bleu=0;		// Couleur rouge
	couleurVert.rouge=0; couleurVert.vert=255; couleurVert.bleu=0;		// Couleur rouge
	couleurBleu.rouge=0; couleurBleu.vert=0; couleurBleu.bleu=255;		// Couleur rouge
	couleurBlanc.rouge=255; couleurBlanc.vert=255; couleurBlanc.bleu=255;		// Couleur rouge

	// Mise de l'entr�e 3 de la table des couleurs de l'image � la couleur rouge :
	//					pTdcCour->setRVB(&couleur, 3, 1);

	for (int i=0 ; i<256 ; i++)
	{
		if (46 < i && i < 70)
		{
			pTdcCour->setRVB(&couleurRouge, i, 1);
		}
		else
		if (92 < i && i < 117)
		{
			pTdcCour->setRVB(&couleurVert, i, 1);
		}
		else
		if (122 < i && i < 157)
		{
			pTdcCour->setRVB(&couleurBleu, i, 1);
		}
		else
		if (158 < i && i < 205)
		{
			pTdcCour->setRVB(&couleurBlanc, i, 1);
		}
		else
		{
			//
		}

	}

	// Reaffichage
	UpdateAllViews(NULL);
}

//=======================================================================
// Methode   : 	void CTp4Doc::OnOtsu()
// But       : 	Calcul du seuil d'une image par la m�thode de Otsu, et
//				construction d'une image r�sultat par application du seuil 
//				calcul� pr�c�demment
// Parametres: 	-
//=======================================================================
void CTp4Doc::OnOtsu() 
{
	// V�rification qu'une image a �t� charg�e
	if (!pIm) return;

	// Allocation de l'histogramme
	Histogramme *pHistogramme = new Histogramme (256);

	// Calcul de l'histogramme et de sa fonction de r�partition
	pHistogramme->calculeHistogramme(pIm);

	// On acc�de � la indice i�me entr�e de l'histogramme par la fonction :
	// unsigned long int& Histogramme::getHistogramme(unsigned int indice)

	int seuil=0;		// Seuil calcul� automatiquement par la m�thode de Otsu

	double varianceMax = 0;

	double surfaceTotale = pIm->largeur * pIm->hauteur;

	double p = 0;

	double moyenne = 0;

	for (int i = 0; i < 256; i++) {
		p = pHistogramme->getHistogramme(i) / surfaceTotale;
		moyenne += i * p;
	}

	double moyenne0 = 0;
	double surface0 = 0;

	for (int i = 0; i < 256; i++) {
		
		p = pHistogramme->getHistogramme(i) / surfaceTotale;
		moyenne0 += i * p;

		surface0 = surface0 + pHistogramme->getHistogramme(i);

		double varianceTmp = (moyenne * surface0 - moyenne0 * surfaceTotale) * (moyenne * surface0 - moyenne0 * surfaceTotale) / ( surface0 * ( surfaceTotale - surface0) );

		if (varianceTmp > varianceMax) {
			varianceMax = varianceTmp;
			seuil = i;
		}
	}


	// Affichage de la valeur du seuil
	CString A;	
	A.Format("Valeur du seuil : %d",seuil);
	AfxMessageBox(A);

	// Application du seuil sur l'image

	// R�cup�ration de la palette associ�e � l'image d'origine
	Tdc * pTdcCour = pIm->getPalette();

	// D�finition d'une variable de couleur
	RvbEntree couleur;

	// Mise de la couleur rouge sur la premi�re r�gion
	for (int i=0; i<=seuil; i++)
	{
		couleur.rouge=255; couleur.vert=i; couleur.bleu=i;
		pTdcCour->setRVB(&couleur, i, 1);
	}

	// Mise de la couleur noire sur la seconde r�gion
	couleur.rouge=0; couleur.vert=0; couleur.bleu=0;
	for (i=seuil+1; i<=255; i++)
		pTdcCour->setRVB(&couleur, i, 1);

	// Reaffichage
	UpdateAllViews(NULL);
}
