; CLW file contains information for the MFC ClassWizard

[General Info]
Version=1
LastClass=CTp4Doc
LastTemplate=CDialog
NewFileInclude1=#include "stdafx.h"
NewFileInclude2=#include "tp4.h"
LastPage=0

ClassCount=5
Class1=CTp4App
Class2=CTp4Doc
Class3=CTp4View
Class4=CMainFrame

ResourceCount=2
Resource1=IDD_ABOUTBOX
Class5=CAboutDlg
Resource2=IDR_MAINFRAME

[CLS:CTp4App]
Type=0
HeaderFile=tp4.h
ImplementationFile=tp4.cpp
Filter=N

[CLS:CTp4Doc]
Type=0
HeaderFile=tp4Doc.h
ImplementationFile=tp4Doc.cpp
Filter=N
BaseClass=CDocument
VirtualFilter=DC
LastObject=ID_OTSU

[CLS:CTp4View]
Type=0
HeaderFile=tp4View.h
ImplementationFile=tp4View.cpp
Filter=C
LastObject=CTp4View

[CLS:CMainFrame]
Type=0
HeaderFile=MainFrm.h
ImplementationFile=MainFrm.cpp
Filter=T



[CLS:CAboutDlg]
Type=0
HeaderFile=tp4.cpp
ImplementationFile=tp4.cpp
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
Class=?
Command1=ID_FILE_OPEN
Command2=ID_BANDE
Command3=ID_OTSU
Command4=ID_APP_ABOUT
CommandCount=4

