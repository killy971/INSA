##
# La proc�dure every permet de faire rentrer en boucle �v�nementielle
# un appel de proc�dure, avec temporisation.
# @param [int] ms le d�lai entre chaque ex�cution de la proc�dure
# @param [call] body la proc�dure � rappeler
#
proc every {ms body} {
	eval $body
	after $ms [list every $ms $body]
}

##
# La proc�dure max calcule le maximum de deux nombres.
# @param [float] m1 le premier nombre � comparer
# @param [float] m2 le deuxi�me nombre � comparer
# @return [float] le plus grand des deux nombres
#
proc max {m1 m2} {
	if {$m1 > $m2} { return $m1
	} else { return $m2 }
}
