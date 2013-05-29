// tp3Doc.h : interface of the CTp3Doc class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TP3DOC_H__EB79972B_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_)
#define AFX_TP3DOC_H__EB79972B_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#include "ImageNg.h"


class CTp3Doc : public CDocument
{
protected: // create from serialization only
	CTp3Doc();
	DECLARE_DYNCREATE(CTp3Doc)

// Attributes
public:
	typedef enum{VISORIG=0, VISRES=1} TypeVisualisation;
	TypeVisualisation Etat;		// Que faut-il visualiser ?

	ImageNg		*pIm;			// Image à analyser
	ImageNg		*pImBis;		// Image résultat

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTp3Doc)
	public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
	virtual BOOL OnOpenDocument(LPCTSTR lpszPathName);
	virtual void DeleteContents();
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CTp3Doc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTp3Doc)
	afx_msg void OnF1();
	afx_msg void OnF2();
	afx_msg void OnF3();
	afx_msg void OnF4();
	afx_msg void OnF5();
	afx_msg void OnF6();
	afx_msg void OnF7();
	afx_msg void OnBruit1();
	afx_msg void OnBruit2();
	afx_msg void OnOrigine();
	afx_msg void OnRecopie();
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TP3DOC_H__EB79972B_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_)
