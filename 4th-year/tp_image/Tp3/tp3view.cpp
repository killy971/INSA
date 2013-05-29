//=======================================================================
// Nom      : tp3View.cpp
//
// But      : Classe Vue du troisième travail pratique
//
// Auteur   : I. Leplumey
//
// Création : 30/04/98
//=======================================================================

#include "stdafx.h"
#include "tp3.h"

#include "tp3Doc.h"
#include "tp3View.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//=======================================================================
//	Associations entre les messages et les traitants de ces messages
//	Genere par Visual C++ et Class Wizard
//=======================================================================

IMPLEMENT_DYNCREATE(CTp3View, CScrollView)

BEGIN_MESSAGE_MAP(CTp3View, CScrollView)
	//{{AFX_MSG_MAP(CTp3View)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, CScrollView::OnFilePrintPreview)
END_MESSAGE_MAP()

//=======================================================================
// Methode   : 	CTp3View::CTp3View()
// But       : 	Constructeur
// Parametres: 	-
//=======================================================================
CTp3View::CTp3View()
{
}

//=======================================================================
// Methode   : 	CTp3View::~CTp3View()
// But       : 	Destructeur
// Parametres: 	-
//=======================================================================
CTp3View::~CTp3View()
{
}

//=======================================================================
// Methode   : 	BOOL CTp3View::PreCreateWindow(CREATESTRUCT& cs)
// But       : 	Fonction appelée à l'origine de la création de la vue
// Parametres: 	cs	: Structure de description de la fenêtre
// Retour    : 	Vrai si la fonction s'est bien déroulée
//=======================================================================
BOOL CTp3View::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CScrollView::PreCreateWindow(cs);
}

//=======================================================================
// Methode   : 	void CTp3View::OnDraw(CDC* pDC)
// But       : 	Calcul de l'affichage à effectuer
// Parametres: 	pDC	: 
//=======================================================================
void CTp3View::OnDraw(CDC* pDC)
{
	CTp3Doc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);

	// S'il n'y a pas d'image, arrêter
	if (!pDoc->pIm) return;

	// Construction d'un objet d'affichage (spécifique à la bibliothèque
	// d'affichage)
	DCStruct dcs(pDC);

	// Affichage de l'image associée au document
	if (pDoc->Etat==pDoc->VISRES)
			pDoc->pImBis->affiche(pDC);
	else	pDoc->pIm->affiche(pDC);
}

//=======================================================================
// Methode   : 	void CTp3View::OnInitialUpdate()
// But       : 	Détermination initiale de la taille de la vue
// Parametres: 	-
//=======================================================================
void CTp3View::OnInitialUpdate()
{
	CScrollView::OnInitialUpdate();
	CSize sizeTotal;

	sizeTotal.cx = sizeTotal.cy = 100;
	SetScrollSizes(MM_TEXT, sizeTotal);
}

//=======================================================================
//	But:		Gestion de l'impression
//=======================================================================
BOOL CTp3View::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CTp3View::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add extra initialization before printing
}

void CTp3View::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: add cleanup after printing
}

//=======================================================================
//	But:		Fonctions correspondant au mode de mise au point
//=======================================================================
#ifdef _DEBUG
void CTp3View::AssertValid() const
{
	CScrollView::AssertValid();
}

void CTp3View::Dump(CDumpContext& dc) const
{
	CScrollView::Dump(dc);
}

CTp3Doc* CTp3View::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CTp3Doc)));
	return (CTp3Doc*)m_pDocument;
}
#endif //_DEBUG

