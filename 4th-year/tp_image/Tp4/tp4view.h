// tp4View.h : interface of the CTp4View class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TP4VIEW_H__2652DEAD_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
#define AFX_TP4VIEW_H__2652DEAD_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

class CTp4View : public CScrollView
{
protected: // create from serialization only
	CTp4View();
	DECLARE_DYNCREATE(CTp4View)

// Attributes
public:
	CTp4Doc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTp4View)
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
	virtual ~CTp4View();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTp4View)
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};

#ifndef _DEBUG  // debug version in tp4View.cpp
inline CTp4Doc* CTp4View::GetDocument()
   { return (CTp4Doc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TP4VIEW_H__2652DEAD_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
