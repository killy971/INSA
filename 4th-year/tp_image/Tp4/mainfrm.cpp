//=======================================================================
// Nom      : MainFrm.cpp
//
// But      : Gestion du cadre du quatrièeme travil pratique
//
// Auteur   : I. Leplumey
//
// Création : 10/05/98
//=======================================================================


#include "stdafx.h"
#include "tp4.h"

#include "MainFrm.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

//=======================================================================
//	Associations entre les messages et les traitants de ces messages
//	Genere par Visual C++ et Class Wizard
//=======================================================================

IMPLEMENT_DYNCREATE(CMainFrame, CFrameWnd)

BEGIN_MESSAGE_MAP(CMainFrame, CFrameWnd)
	//{{AFX_MSG_MAP(CMainFrame)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	ON_WM_CREATE()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

static UINT indicators[] =
{
	ID_SEPARATOR,           // status line indicator
	ID_INDICATOR_CAPS,
	ID_INDICATOR_NUM,
	ID_INDICATOR_SCRL,
};

//=======================================================================
// Methode   : 	CMainFrame::CMainFrame()
// But       : 	Constructeur
// Parametres: 	-
//=======================================================================
CMainFrame::CMainFrame()
{
}

//=======================================================================
// Methode   : 	CMainFrame::~CMainFrame()
// But       : 	Destructeur
// Parametres: 	-
//=======================================================================
CMainFrame::~CMainFrame()
{
}

//=======================================================================
// Methode   : 	int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
// But       : 	Gestion de la création
// Parametres: 	lpCreateStruct	: 
// Retour    : 	0 si tout s'est bien passé, -1 sinon
//=======================================================================
int CMainFrame::OnCreate(LPCREATESTRUCT lpCreateStruct)
{
	if (CFrameWnd::OnCreate(lpCreateStruct) == -1)
		return -1;
	
	if (!m_wndToolBar.Create(this) ||
		!m_wndToolBar.LoadToolBar(IDR_MAINFRAME))
	{
		TRACE0("Failed to create toolbar\n");
		return -1;      // fail to create
	}

	if (!m_wndStatusBar.Create(this) ||
		!m_wndStatusBar.SetIndicators(indicators,
		  sizeof(indicators)/sizeof(UINT)))
	{
		TRACE0("Failed to create status bar\n");
		return -1;      // fail to create
	}

	// TODO: Remove this if you don't want tool tips or a resizeable toolbar
	m_wndToolBar.SetBarStyle(m_wndToolBar.GetBarStyle() |
		CBRS_TOOLTIPS | CBRS_FLYBY | CBRS_SIZE_DYNAMIC);

	// TODO: Delete these three lines if you don't want the toolbar to
	//  be dockable
	m_wndToolBar.EnableDocking(CBRS_ALIGN_ANY);
	EnableDocking(CBRS_ALIGN_ANY);
	DockControlBar(&m_wndToolBar);

	return 0;
}

//=======================================================================
// Methode   : 	BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
// But       : 	Appelée au début de la vie du cadre, permet entre autre 
//				le dimensionnement
// Parametres: 	cs	: paramètre de la fenêtre cadre
// Retour    : 	Vrai si la fonction s'est bien déroulée
//=======================================================================
BOOL CMainFrame::PreCreateWindow(CREATESTRUCT& cs)
{
	cs.cy=512+98;	// Pour une vue de 512*512 (calculé par tatonnement)
	cs.cx=512+12;	// 12 colonnes et 98 lignes supplémentaires

	return CFrameWnd::PreCreateWindow(cs);
}

//=======================================================================
//	But:		Fonctions correspondant au mode de mise au point
//=======================================================================
#ifdef _DEBUG
void CMainFrame::AssertValid() const
{
	CFrameWnd::AssertValid();
}

void CMainFrame::Dump(CDumpContext& dc) const
{
	CFrameWnd::Dump(dc);
}

#endif //_DEBUG
