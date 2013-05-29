// TpFigView.cpp : implementation of the CTpFigView class
//

#include "stdafx.h"
#include "TpFig.h"

#include "TpFigDoc.h"
#include "TpFigView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CTpFigView

IMPLEMENT_DYNCREATE(CTpFigView, CView)

BEGIN_MESSAGE_MAP(CTpFigView, CView)
	//{{AFX_MSG_MAP(CTpFigView)
	ON_WM_LBUTTONDOWN()
	ON_WM_TIMER()
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CTpFigView construction/destruction

CTpFigView::CTpFigView()
{
	// TODO: add construction code here
	m_timer = 0;
}

CTpFigView::~CTpFigView()
{
}

BOOL CTpFigView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CView::PreCreateWindow(cs);
}

/////////////////////////////////////////////////////////////////////////////
// CTpFigView drawing

void CTpFigView::OnDraw(CDC* pDC)
{
	CTpFigDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	// TODO: add draw code for native data here

	//**************************************************
	//* Affichage de vos figures pour cette vue
	//**************************************************
	for (int i=0;i<pDoc->lesFig.ensFig.size();i++)
	{
		Figure* pfig=pDoc->lesFig.ensFig[i];
		pfig->dessiner(pDC);
	}
}

/////////////////////////////////////////////////////////////////////////////
// CTpFigView diagnostics

#ifdef _DEBUG
void CTpFigView::AssertValid() const
{
	CView::AssertValid();
}

void CTpFigView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CTpFigDoc* CTpFigView::GetDocument() // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CTpFigDoc)));
	return (CTpFigDoc*)m_pDocument;
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CTpFigView message handlers

/////////////////////////////////////////////////////////////////////////////
// CTpFigView static attributes

//déplacement circulaire
int CTpFigView::_dx[] = { 30,  60,  60,  30, -30, -60, -60, -30};
int CTpFigView::_dy[] = { 60,  30, -30, -60, -60, -30,  30,  60};
int CTpFigView::_animIndex = 0;
int CTpFigView::_animSize = 8;

/////////////////////////////////////////////////////////////////////////////
// CTpFigView message handlers

//mise en route ou arrêt du déplacement par un clic gauche
void CTpFigView::OnLButtonDown(UINT nFlags, CPoint point) 
{
	// TODO: Add your message handler code here and/or call default
	if (!m_timer)
	{
		// AfxMessageBox("Initializing Timer");
		m_timer = SetTimer(1,100,NULL);
	}
	else
	{
		// AfxMessageBox("Killing Timer");
		KillTimer(m_timer);
		m_timer = 0;
	}
	CView::OnLButtonDown(nFlags, point);
}

//gestion du déplacement par un timer
void CTpFigView::OnTimer(UINT nIDEvent) 
{
	// TODO: Add your message handler code here and/or call default
	CTpFigDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);

	CDC* pDC = GetDC();
	for (int i=0;i<pDoc->lesFig.ensFig.size();i++)
	{
		Figure* pfig=pDoc->lesFig.ensFig[i];
		pfig->deplacer(pDC, _dx[_animIndex],_dy[_animIndex]);
	}
	_animIndex = (_animIndex + 1) % _animSize;
	
	Invalidate();
}
