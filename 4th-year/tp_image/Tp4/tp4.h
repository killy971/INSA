// tp4.h : main header file for the TP4 application
//

#if !defined(AFX_TP4_H__2652DEA5_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
#define AFX_TP4_H__2652DEA5_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_

#if _MSC_VER >= 1000
#pragma once
#endif // _MSC_VER >= 1000

#ifndef __AFXWIN_H__
	#error include 'stdafx.h' before including this file for PCH
#endif

#include "resource.h"       // main symbols

/////////////////////////////////////////////////////////////////////////////
// CTp4App:
// See tp4.cpp for the implementation of this class
//

class CTp4App : public CWinApp
{
public:
	CTp4App();

// Overrides
	// ClassWizard generated virtual function overrides
	//{{AFX_VIRTUAL(CTp4App)
	public:
	virtual BOOL InitInstance();
	//}}AFX_VIRTUAL

// Implementation

	//{{AFX_MSG(CTp4App)
	afx_msg void OnAppAbout();
		// NOTE - the ClassWizard will add and remove member functions here.
		//    DO NOT EDIT what you see in these blocks of generated code !
	//}}AFX_MSG
	DECLARE_MESSAGE_MAP()
};


/////////////////////////////////////////////////////////////////////////////

//{{AFX_INSERT_LOCATION}}
// Microsoft Developer Studio will insert additional declarations immediately before the previous line.

#endif // !defined(AFX_TP4_H__2652DEA5_E7E5_11D1_8D8A_00A024D8CAE5__INCLUDED_)
