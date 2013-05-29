// TpFigDoc.cpp : implementation of the CTpFigDoc class
//

#include "stdafx.h"
#include "TpFig.h"

#include "TpFigDoc.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#undef THIS_FILE
static char THIS_FILE[] = __FILE__;
#endif

/////////////////////////////////////////////////////////////////////////////
// CTpFigDoc

IMPLEMENT_DYNCREATE(CTpFigDoc, CDocument)

BEGIN_MESSAGE_MAP(CTpFigDoc, CDocument)
	//{{AFX_MSG_MAP(CTpFigDoc)
		// NOTE - the ClassWizard will add and remove mapping macros here.
		//    DO NOT EDIT what you see in these blocks of generated code!
	//}}AFX_MSG_MAP
END_MESSAGE_MAP()

/////////////////////////////////////////////////////////////////////////////
// CTpFigDoc construction/destruction

CTpFigDoc::CTpFigDoc()
{
	// TODO: add one-time construction code here
}

CTpFigDoc::~CTpFigDoc()
{
}

BOOL CTpFigDoc::OnNewDocument()
{
	if (!CDocument::OnNewDocument())
		return FALSE;

	// TODO: add reinitialization code here
	// (SDI documents will reuse this document)

	return TRUE;
}



/////////////////////////////////////////////////////////////////////////////
// CTpFigDoc serialization

void CTpFigDoc::Serialize(CArchive& ar)
{
	if (ar.IsStoring())
	{
		// TODO: add storing code here
	}
	else
	{
		// TODO: add loading code here
	}
}

/////////////////////////////////////////////////////////////////////////////
// CTpFigDoc diagnostics

#ifdef _DEBUG
void CTpFigDoc::AssertValid() const
{
	CDocument::AssertValid();
}

void CTpFigDoc::Dump(CDumpContext& dc) const
{
	CDocument::Dump(dc);
}
#endif //_DEBUG

/////////////////////////////////////////////////////////////////////////////
// CTpFigDoc commands
