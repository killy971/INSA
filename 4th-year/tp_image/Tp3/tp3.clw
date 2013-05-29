; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CTp3Doc
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "tp3.h"
LastPage=0

ClassCount=5
Class1=CTp3App
Class2=CTp3Doc
Class3=CTp3View
Class4=CMainFrame

ResourceCount=2
Resource1=IDD_ABOUTBOX
Class5=CAboutDlg
Resource2=IDR_MAINFRAME

[CLS:CTp3App]
Type=0
HeaderFile=tp3.h
ImplementationFile=tp3.cpp
Filter=N

[CLS:CTp3Doc]
Type=0
HeaderFile=tp3Doc.h
ImplementationFile=tp3Doc.cpp
Filter=N
BaseClass=CDocument
VirtualFilter=DC
LastObject=ID_RECOPIE

[CLS:CTp3View]
Type=0
HeaderFile=tp3View.h
ImplementationFile=tp3View.cpp
Filter=C
LastObject=CTp3View

[CLS:CMainFrame]
Type=0
HeaderFile=MainFrm.h
ImplementationFile=MainFrm.cpp
Filter=T



[CLS:CAboutDlg]
Type=0
HeaderFile=tp3.cpp
ImplementationFile=tp3.cpp
Filter=D

[DLG:IDD_ABOUTBOX]
Type=1
Class=CAboutDlg
ControlCount=4
Control1=IDC_STATIC,static,1342177283
Control2=IDC_STATIC,static,1342308480
Control3=IDC_STATIC,static,1342308352
Control4=IDOK,button,1342373889

[MNU:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_FILE_OPEN
Command2=ID_FILE_MRU_FILE1
Command3=ID_APP_EXIT
Command4=ID_VIEW_TOOLBAR
Command5=ID_VIEW_STATUS_BAR
Command6=ID_APP_ABOUT
CommandCount=6

[ACL:IDR_MAINFRAME]
Type=1
Class=CMainFrame
Command1=ID_FILE_NEW
Command2=ID_FILE_OPEN
Command3=ID_FILE_SAVE
Command4=ID_FILE_PRINT
Command5=ID_EDIT_UNDO
Command6=ID_EDIT_CUT
Command7=ID_EDIT_COPY
Command8=ID_EDIT_PASTE
Command9=ID_EDIT_UNDO
Command10=ID_EDIT_CUT
Command11=ID_EDIT_COPY
Command12=ID_EDIT_PASTE
Command13=ID_NEXT_PANE
Command14=ID_PREV_PANE
CommandCount=14

[TB:IDR_MAINFRAME]
Type=1
Command1=ID_FILE_OPEN
Command2=ID_BRUIT1
Command3=ID_BRUIT2
Command4=ID_ORIGINE
Command5=ID_RECOPIE
Command6=ID_F1
Command7=ID_F2
Command8=ID_F3
Command9=ID_F4
Command10=ID_F5
Command11=ID_F6
Command12=ID_F7
Command13=ID_APP_ABOUT
CommandCount=13

