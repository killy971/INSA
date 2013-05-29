//=======================================================================
// Nom      : tp4View.cpp
//
// But      : Classe vue du quatrièeme travail pratique de l'option images 
//				numériques
//
// Auteur   : I. Leplumey
//
// Création : 10/05/98
//=======================================================================


#include "stdafx.h"
#include "tp4.h"

#include "tp4Doc.h"
#include "tp4View.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//=======================================================================
//	Associations entre les messages et les traitants de ces messages
//	Genere par Visual C++ et Class Wizard
//=======================================================================

IMPLEMENT_DYNCREATE(CTp4View, CScrollView)

BEGIN_MESSAGE_MAP(CTp4View, CScrollView)
	//{{AFX_MSG_MAP(CTp4View)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
	// Standard printing commands
	ON_COMMAND(ID_FILE_PRINT, CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, CScrollView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, CScrollView::OnFilePrintPreview)
END_MESSAGE_MAP()

//=======================================================================
// Methode   : 	CTp4View::CTp4View()
// But       : 	Constructeur
// Parametres: 	-
//=======================================================================
CTp4View::CTp4View()
{
}

//=======================================================================
// Methode   : 	CTp4View::~CTp4View()
// But       : 	Destructeur
// Parametres: 	-
//=======================================================================
CTp4View::~CTp4View()
{
}

//=======================================================================
// Methode   : 	BOOL CTp4View::PreCreateWindow(CREATESTRUCT& cs)
// But       : 	
// Parametres: 	cs	: 
// Retour    : 	Vrai si la fonction s'est bien déroulée
//=======================================================================
BOOL CTp4View::PreCreateWindow(CREATESTRUCT& cs)
{
	return CScrollView::PreCreateWindow(cs);
}

//=======================================================================
// Methode   : 	void CTp4View::OnDraw(CDC* pDC)
// But       : 	
// Parametres: 	pDC	: 
//=======================================================================
void CTp4View::OnDraw(CDC* pDC)
{
	CTp4Doc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);

	// S'il n'y a pas d'image, arrêter
	if (!pDoc->pIm) return;

	// Construction d'un objet d'affichage (spécifique à la bibliothèque
	// d'affichage)
	DCStruct dcs(pDC);

	// Affichage de l'image associée au document
	pDoc->pIm->affiche(pDC);
}

//=======================================================================
// Methode   : 	void CTp4View::OnInitialUpdate()
// But       : 	Gestion du dimensionnment de la vue (dim. barres de scrolling)
// Parametres: 	-
//=======================================================================
void CTp4View::OnInitialUpdate()
{
	CScrollView::OnInitialUpdate();
	CSize sizeTotal;

	CTp4Doc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);

	if (pDoc->pIm)
	{
		sizeTotal.cy = pDoc->pIm->hauteur ;
		sizeTotal.cx = pDoc->pIm->largeur ;

		SetScrollSizes(MM_TEXT, sizeTotal);
		GetParentFrame()->RecalcLayout();
		ResizeParentToFit();
	}
	else
	{
		sizeTotal.cx = sizeTotal.cy = 100;
		SetScrollSizes(MM_TEXT, sizeTotal);
	}
}

//=======================================================================
//	But:		Gestion de l'impression
//=======================================================================
BOOL CTp4View::OnPreparePrinting(CPrintInfo* pInfo)
{
	// default preparation
	return DoPreparePrinting(pInfo);
}

void CTp4View::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
}

void CTp4View::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
}

//=======================================================================
//	But:		Fonctions correspondant au mode de mise au point
//=======================================================================
#ifdef _DEBUG
void CTp4View::AssertValid() const
{
	CScrollView::AssertValid();
}

void CTp4View::Dump(CDumpContext& dc) const
{
	CScrollView::Dump(dc);
}

CTp4Doc* CTp4View::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CTp4Doc)));
	return (CTp4Doc*)m_pDocument;
}
#endif //_DEBUG
