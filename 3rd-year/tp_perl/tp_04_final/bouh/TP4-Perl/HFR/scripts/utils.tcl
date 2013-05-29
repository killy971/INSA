##
# La procédure every permet de faire rentrer en boucle événementielle
# un appel de procédure, avec temporisation.
# @param [int] ms le délai entre chaque exécution de la procédure
# @param [call] body la procédure à rappeler
#
proc every {ms body} {
	eval $body
	after $ms [list every $ms $body]
}

##
# La procédure max calcule le maximum de deux nombres.
# @param [float] m1 le premier nombre à comparer
# @param [float] m2 le deuxième nombre à comparer
# @return [float] le plus grand des deux nombres
#
proc max {m1 m2} {
	if {$m1 > $m2} { return $m1
	} else { return $m2 }
}
