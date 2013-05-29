namespace eval Zooming {
	set ::z {
		{./zoom/1.gif}
		{./zoom/2.gif}
		{./zoom/3.gif}
		{./zoom/4.gif}
		{./zoom/5.gif}
	}
	set ::cptz 0
}

##
# La méthode create crée le volet de zooming de photos.
# Elle fait appel à la fonction antialias() pour
# dessiner la zone de zooming/antialiasing de ce volet.
# @param [Notebook] nb le notebook dans lequel on place le volet
#
proc Zooming::create { nb } {
    set frame [$nb insert end zooming -text "Zooming"]

    set titf3 [TitleFrame $frame.titf3 -text "Photo antialiaser : "]

    antialias [$titf3 getframe]

    pack $titf3 -side right -padx 2 -fill both -expand yes
}

##
# La fonction antialias (que le monde entier nous envie)
# applique un puissant zoom sur la photo de base, puis est capable
# de redéfinir avec une précision incroyable les contours de la
# photo, pourtant aliasés au plus haut point.
# L'agent Ivan L., ancien agent infiltré dans un bar peu fréquentable
# de la banlieue nord de Eindhoven et actuellement membre éminent 
# d'un groupe de chercheurs en imagerie numérique,
# tente de nous voler cette méthode de traitement d'image depuis
# quelques temps, sans succès.
# @param [TitleFrame] parent le cadre parent
#
proc Zooming::antialias { parent } {
	image create photo image2a
	label $parent.image -image image2a
	pack $parent.image -padx 2m -pady 2m
	image2a configure -file "./zoom/1.gif"

	bind $parent.image <1> "Zooming::process_antialias"
}

##
# Derrière cette fonction apparemment anodine se cache le moteur
# d'antialiasing de notre soft.
# En réalité, la fonction est vraiment anodine et tout ceci n'est qu'une
# grosse arnaque.
#
proc Zooming::process_antialias { } {
	set len [llength $::z]
	set ::cptz [expr ($::cptz+1)%$len]
	image2a configure -file [lindex $::z $::cptz]
}

