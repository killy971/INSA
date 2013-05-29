#!/usr/bin/perl

if($#ARGV<1){
	die "\nSyntaxe: $0 rep_cible/ fichier1.tcl [fichier2.tcl ...]\n";
}

if (!(-e $ARGV[0]) || !(-d $ARGV[0]))
{
	die "Erreur : $ARGV[0] n'existe pas ou n'est pas un repertoire";
}

@ARGV = reverse(@ARGV);
$rep = pop(@ARGV);
@ARGV = reverse(@ARGV);

# conversion des .tcl en .h

foreach $f (@ARGV)
{
	if(!open(F_TCL,"<$f"))
	{
		die "Erreur d'ouverture fichier : $!\n";
	}
	
	# création du nom
	$f =~ /.*(\.[^\.]+)/; 
	$f_cpp = substr($f,0,-length($1));
	$f_cpp = "./includes/".$f_cpp.".h";	
	push(@files,$f_cpp);

	if(!open(F_CPP,">$f_cpp"))
	{
		die "Erreur de creation du fichire temporaire: $!\n";
	}
	
	# type du retour
	$ret = "void";
	# tableau associatif 'nom => type'
	%types;
	
	while($ligne=<F_TCL>)
	{
		# si on commence un header de commentaire,
		# on écrit "/**" et on met dans la pile "*/"
		# qui servira à se rappeler qu'on est dans un
		# commentaire
		if ($ligne =~ /^\#\#/)
		{
			print F_CPP "/**\n";
			$in_comment=1;
			undef %types;
			%types;
		}
		# si on est sur une ligne de commentaire
		elsif (($ligne =~ /^\#/) && $in_comment)
		{
			# ligne de commentaire @param
			if ($ligne =~ /^\#\s*\@param\s*\[(\w+)\]\s*(\w+)\s*(.*)$/)
			{
				$types{$2} = $1;
				print F_CPP " * \@param ".$2." ".$3."\n";
			}
			# ligne de commentaire @return
			elsif ($ligne =~ /^\#\s*\@return\s*\[(\w+)\]\s*(.*)$/)
			{
				$ret = $1;
				print F_CPP " * \@return ".$2."\n";
			}
			# ligne de commentaire simple
			elsif ($ligne =~ /^\# (.*)$/)
			{
				print F_CPP " * ".$1."\n";
			}
			# fin de l'header de commentaire
			else
			{
				print F_CPP " */\n";
				$in_comment=0;
			}
		}
		# ligne de début de fonction (pas besoin de matcher plus de trucs non ?)
		elsif ($ligne =~ /^proc\s(\w+)\s\{\s?(.*)\s?\}\s{/)
		{
			@parametres = split(/\s+/,$2);
			print F_CPP "public ".$ret." ".$1." (";
			undef $params;
			foreach $p (@parametres)
			{
					$params .= "$types{$p} $p,";
			}
			# suppression de la virgule en trop
			$params = substr($params,0,-1);
			print F_CPP $params;
			print F_CPP ");\n\n";
		}
	}
close(F_TCL);
close(F_CPP);
}

# création du fichier de config doxygen

if(!open(BASE_CFG,"<doxygen.cfg"))
{
	die "Erreur fichier de config doxygen source : $!\n";
}

if(!open(TEMP_CFG,">.doxygen.temp.cfg"))
{
	die "Erreur fichier de config doxygen sortie : $!\n";
}

while($ligne=<BASE_CFG>)
{
	if ($ligne =~ /OUTPUT_DIRECTORY/)
	{
# il sert à quoi ce foutu rep_cible en fait ?
# repertoire ou se trouve les .h ? rep pour la doc ?
#		print TEMP_CFG "OUTPUT_DIRECTORY = $rep\n";
		print TEMP_CFG "OUTPUT_DIRECTORY = ./doc/\n";

	}
	else
	{
		print TEMP_CFG $ligne;
	}
}

close(BASE_CFG);
close(TEMP_CFG);

# génération de la doc avec doxygen
`doxygen .doxygen.temp.cfg 2> erreur.txt`;

unlink(".doxygen.temp.cfg");

# on supprime les fichiers .h temporaires

foreach $f (@files)
{
	unlink($f);
}