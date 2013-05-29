#!/bin/sh
# The next line is executed by /bin/sh, but not tcl \
exec wish "$0" ${1+"$@"}

namespace eval Recognizer {
    variable notebook
    variable mainframe
    variable status
    variable prgtext
    variable prgindic

    set pwd [pwd]
    cd [file dirname [info script]]
    variable DEMODIR [pwd]
    cd $pwd

    foreach script {
	utils.tcl zooming.tcl matching.tcl
    } {
	namespace inscope :: source $DEMODIR/scripts/$script
    }

    #Ici on initialise les paramètres de l'application
}

##
# Crée la fenêtre principale de l'application. Elle charge chacun des autres
# modules et fait appel à la fonction intro().
#
proc Recognizer::create { } {
    global   tk_patchLevel
    variable notebook
    variable mainframe
    variable font
    variable prgtext
    variable prgindic

    set prgtext "Chargement de la police ..."
    set prgindic -1
    intro
    update
    SelectFont::loadfont
    font create myfont -family {Courier-new} -size 14 -weight bold

    set prgtext   "Création du panneau principal ..."
    set prgindic  0
    set mainframe [MainFrame .mainframe \
                       -textvariable Recognizer::status \
                       -progressvar  Recognizer::prgindic]

    # Onglets 
    set frame    [$mainframe getframe]
    set notebook [NoteBook $frame.nb]

    set prgtext   "Chargement du module de zooming ..."
    incr prgindic
    set f0 [Zooming::create $notebook]

    set prgtext   "Chargement du module de matching ..."
    incr prgindic
    set f1 [Matching::create $notebook]

    set prgtext   "OK !"
    incr prgindic
    $notebook compute_size
    pack $notebook -fill both -expand yes -padx 4 -pady 4
    $notebook raise [$notebook page 0]

    after 500

    pack $mainframe -fill both -expand yes
    update idletasks
    destroy .intro
}


##
# Affiche l'écran de démarrage ainsi que l'état d'avancement de ce
# chargement.
# Rq : la variable Recognizer::prgindic stocke le nombre de crans de la barre.
#
proc Recognizer::intro { } {
    variable DEMODIR

    set top [toplevel .intro -relief raised -borderwidth 2]

    wm withdraw $top
    wm overrideredirect $top 1

    set ximg  [label $top.x -bitmap @$DEMODIR/misc/recognizer.xbm \
	    -foreground grey90 -background white]
    set frame [frame $ximg.f -background white]
    set lab1  [label $frame.lab1 -text "Chargement de SaVaH beta-2 :" \
	    -background white -font {times 8}]
    set lab2  [label $frame.lab2 -textvariable Recognizer::prgtext \
	    -background white -font {times 8} -width 35]
    set prg   [ProgressBar $frame.prg -width 50 -height 10 -background white \
	    -variable Recognizer::prgindic -maximum 3]
    pack $lab1 $lab2 $prg
    place $frame -x 0 -y 0 -anchor nw
    pack $ximg
    BWidget::place $top 0 0 center
    wm deiconify $top
}

################################################################################


##
# Fonction principale du module
#
proc Recognizer::main {} {
    variable DEMODIR

    lappend ::auto_path [file dirname $DEMODIR]
    namespace inscope :: package require BWidget

    option add *TitleFrame.l.font {helvetica 11 bold italic}

    wm withdraw .
#    wm resizable . 0 0
    wm title . "Hidden face recognizer"

    Recognizer::create
    BWidget::place . 0 0 center
    wm deiconify .
    raise .
    focus -force .
}

Recognizer::main
#wm geom . [wm geom .]
wm geometry . 1024x768
