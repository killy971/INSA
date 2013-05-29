// tp4Doc.h : interface of the CTp4Doc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TP4DOC_H__2652DEAB_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
#define AFX_TP4DOC_H__2652DEAB_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#include "Histogramme.h"
#include "Image8bits.h"

class CTp4Doc : public CDocument
{
protected: // create from serialization only
	CTp4Doc();
	DECLARE_DYNCREATE(CTp4Doc)

// Attributes
public:
	Image8bits		*pIm;			// Image à analyser
	Image8bits		*pImBis;		// Image résultat

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTp4Doc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	virtual BOOL OnOpenDocument(LPCTSTR lpszPathName);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CTp4Doc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTp4Doc)
	afx_msg void OnBande();
	afx_msg void OnOtsu();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TP4DOC_H__2652DEAB_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
