// TpFigView.h : interface of the CTpFigView class
//
/////////////////////////////////////////////////////////////////////////////

#if !defined(AFX_TPFIGVIEW_H__CADC1DAC_E1BD_11D6_9221_00C04F684A5C__INCLUDED_)
#define AFX_TPFIGVIEW_H__CADC1DAC_E1BD_11D6_9221_00C04F684A5C__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000


class CTpFigView : public CView
{
protected: // create from serialization only
	CTpFigView();
	DECLARE_DYNCREATE(CTpFigView)

// Attributes
public:
	CTpFigDoc* GetDocument();

// Operations
public:

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTpFigView)
	public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	protected:
	//}}AFX_VIRTUAL

// Implementation
public:
	virtual ~CTpFigView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	//{{AFX_MSG(CTpFigView)
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnTimer(UINT nIDEvent);
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()

private:
	//membres pour la gestion du déplacement des figures
	static int _dy[];
	static int _dx[];
	static int _animIndex;
	static int _animSize;
	int m_timer;
};

#ifndef _DEBUG  // debug version in TpFigView.cpp
inline CTpFigDoc* CTpFigView::GetDocument()
   { return (CTpFigDoc*)m_pDocument; }
#endif

/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Visual C++ will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TPFIGVIEW_H__CADC1DAC_E1BD_11D6_9221_00C04F684A5C__INCLUDED_)
