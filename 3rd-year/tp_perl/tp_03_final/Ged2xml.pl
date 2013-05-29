#!/usr/bin/perl

if($#ARGV!=1){
	print "$#ARGV\n";
	die "Syntaxe: $0 fichier_orig fichier_resul\n";
}

$fichier_orig=$ARGV[0];
$fichier_resul=$ARGV[1];

if(!open(FICHIER,"<$fichier_orig")){die "Problème d'ouverture du fichier origine!: $!\n";}

if(-e "$fichier_resul"){die "Fichier resultat déjà existant!: $!\n#";}

if(!open(RESULT,">$fichier_resul")){die "Problème de creation du fichire resultat!: $!\n";}

print RESULT "<?xml-stylesheet href=\"style.css\" type=\"text/css\"?>";

print RESULT "<GEN>\n";

$lastprof = 0;

while($ligne=<FICHIER>)
{
	$ligne=~s/^(\d+)\s(([A-Z]+)|\@(\w+)\@\s([A-Z]+))\s(.*)$/\1 \3\5/;

# reconnait un nombre, puis soit directement la nature, dans \3
# soit @.*@ suivit de la nature, dans \5
# \3 et \5 sont obligatoirement vides l'un XOR l'autre

	($profondeur,$nature,$numero,$info) = ($1,$3.$5,$4,$6);
	$info=~s/[\r\n]//g;

	if ($lastprof>$profondeur)
	{
		$bal = pop(@endpile);
		print RESULT $bal;
	}

	if($5 ne "")
	{
		print RESULT "<$nature ID=\"$numero\">\n";
		push(@endpile,"</$nature>\n");
	}
	elsif($nature eq "NAME")
	{
		$info=~s/\//<S>/;
		$info=~s/\//<\/S>/;
		print RESULT "<NAME>$info</NAME>\n";
	}
	elsif($nature eq "DEAT"| $nature eq "BIRT"| $nature eq "MARR")
	{
		print RESULT "<EVEN EV=\'$nature\'>\n";
		push(@endpile,"</EVEN>\n");
	}
	elsif($nature eq "FAMS"| $nature eq "HUSB"| $nature eq "WIFE"| $nature eq "CHIL" | $nature eq "FAMC")
	{
		$info=~s/\@//g;
		print RESULT "<$nature REF=\"$info\"></$nature>\n";
	}
	else
	{
		print RESULT "<$nature>$info </$nature>\n";
	}

	$lastprof = $profondeur;
}

while ($bal = pop(@endpile))
{
	print RESULT $bal;
}

print RESULT "</GEN>\n";
