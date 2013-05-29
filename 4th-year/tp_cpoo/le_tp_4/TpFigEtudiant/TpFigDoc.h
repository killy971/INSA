// TpFigDoc.h : interface of the CTpFigDoc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TPFIGDOC_H__CADC1DAA_E1BD_11D6_9221_00C04F684A5C__INCLUDED_)
#define AFX_TPFIGDOC_H__CADC1DAA_E1BD_11D6_9221_00C04F684A5C__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "GestionFigures.h"

class CTpFigDoc : public CDocument
{
protected: // create from serialization only
	CTpFigDoc();
	DECLARE_DYNCREATE(CTpFigDoc)

// Attributes
public:
	GestionFigures lesFig;

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTpFigDoc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CTpFigDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTpFigDoc)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TPFIGDOC_H__CADC1DAA_E1BD_11D6_9221_00C04F684A5C__INCLUDED_)
