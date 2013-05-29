
namespace eval Matching {
	set ::l {
		{./images/img_0171.gif}
		{./images/img_0173.gif}
		{./images/img_0174.gif}
		{./images/arnomarie.gif}
		{./images/arthurine.gif}
		{./images/derreck.gif}
		{./images/maitre.gif}
		{./images/yann.gif}
	}
	set ::cpt 0

	set ::l2 {
		{./images/derrick.gif}
		{./images/derrick2.gif}
	}
	set ::cpt2 0
}

##
# La méthode create crée le volet de matching de photos.
# Elle fait appel aux fonctions wanted(), identity() et research() pour
# dessiner les trois parties de ce volet.
# @param [Notebook] nb le notebook dans lequel on place le volet
#
proc Matching::create { nb } {
    set frame [$nb insert end matching -text "Matching"]

	set pw    [PanedWindow $frame.pw -side bottom]
	set pane  [$pw add -weight 0]

	set pw2   [PanedWindow $pane.pw -side left]
	set pane2 [$pw2 add -weight 0]

    set titf2 [TitleFrame $pane2.titf2 -text "Wanted : "]

	set pane2 [$pw2 add -weight 1]
	
    set titf3 [TitleFrame $pane2.titf3 -text "Identity : "]

	set pane  [$pw add -weight 1]

    set titf4 [TitleFrame $pane.titf4 -text "Research : "]

    wanted   [$titf2 getframe]
    identity [$titf3 getframe]
    research [$titf4 getframe]

    pack $titf4 -side right -fill both -padx 2 -expand yes
    pack $titf2 $titf3 -side top -padx 2 -fill both -expand yes

	pack $pw $pw2 -fill both -expand yes
}

##
# La méthode wanted crée le cadre dans lequel apparaît la photo
# du méchant Sempai qu'il faut retrouver à tout prix.
# @param [TitleFrame] parent le cadre parent
#
proc Matching::wanted { parent } {
	image create photo image0a
	label $parent.image -image image0a
	pack $parent.image -padx 2m -pady 2m
	image0a configure -file "./images/mini.gif"
}

##
# identity fournit les informations nécessaires à l'identification
# du malfrat exhibé par le moteur de reconnaissance des visages
# cachés.
# @param [TitleFrame] parent le cadre parent
#
proc Matching::identity { parent } {
	label $parent.lab1 -text "Last Name :"
	label $parent.lab2 -text "First Name :"
	label $parent.lab5 -text "Known As :"
	label $parent.lab6 -text "Activity :"

	Entry $parent.text1  -textvariable nom      -editable false
	Entry $parent.text2  -textvariable prenom   -editable false
	Entry $parent.text5  -textvariable surnom   -editable false
	Entry $parent.text6  -textvariable activite -editable false

	pack $parent.lab1 $parent.text1 $parent.lab2 $parent.text2 $parent.lab5 $parent.text5 $parent.lab6 $parent.text6 -anchor w
}

##
# La fonction research fournit à l'inspecteur Derreck un aperçu
# totalement inutile des méchants de la base de données du FBI.
# Cette fonctionnalité est essentielle à toute série de mauvaise
# qualité, c'est pourquoi nous l'avons développée.
# @param [TitleFrame] parent le cadre parent
#
proc Matching::research { parent } {

	image create photo imagea
	label $parent.image -image imagea
	pack $parent.image -padx 2m -pady 2m
	imagea configure -file "./images/blank.gif"
	
	bind $parent.image <1> "
		after cancel \"every 250 Matching::loadTueur\"
		bind $parent.image <1> \"\"
		every 50 Matching::loadImage
	"

	bind $parent.image <3> "
		after cancel \"every 50 Matching::loadImage\"
		bind $parent.image <3> \"\"
		every 250 Matching::loadTueur
	"
}

##
# Cette fonction permet de modifier la photo présentée dans le
# cadre créé par la fonction research.
#
proc Matching::loadImage { } {
	set len [llength $::l]
	set ::cpt [expr ($::cpt+1)%$len]
	imagea configure -file [lindex $::l $::cpt]
}

##
# Ici, on complète les informations sur le méchant. Le développeur
# avisé se rendra compte que les informations sont entrées en dur,
# et que la reconnaissance des visages cachés n'est donc pas
# effective ...
#
proc Matching::loadTueur { } {
	set ::cpt2 [expr ($::cpt2+1)%2]
	imagea configure -file [lindex $::l2 $::cpt2]

	set ::nom "XXXXXXXX"
	set ::prenom "xxxxxxxx"
	set ::surnom "Le Sempaï"
	set ::activite "Méchant"
}

