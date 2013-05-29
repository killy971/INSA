// tp3View.h : interface of the CTp3View class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TP3VIEW_H__EB79972D_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_)
#define AFX_TP3VIEW_H__EB79972D_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

class CTp3View : public CScrollView
{
protected: // create from serialization only
	CTp3View();
	DECLARE_DYNCREATE(CTp3View)

// Attributes
public:
	CTp3Doc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTp3View)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	virtual void OnInitialUpdate(); // called first time after construct
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CTp3View();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTp3View)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in tp3View.cpp
inline CTp3Doc* CTp3View::GetDocument()
   { return (CTp3Doc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TP3VIEW_H__EB79972D_DE6D_11D1_8D89_00A024D8CAE5__INCLUDED_)
