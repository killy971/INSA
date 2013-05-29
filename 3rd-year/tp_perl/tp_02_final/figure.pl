#!/usr/bin/perl

use File::Copy;

if($#ARGV!=2){
  print "$#ARGV\n";
  die "Syntaxe: $0 fichier_resultat numero_ident type_de_sortie\n";
}

$fichier=@ARGV[0];
$numero_ident=@ARGV[1];
$sortie=@ARGV[2];

if(!open(FICHIER,"<$fichier")){die "Problème d'ouverture du fichier!: $!\n";}
if($sortie ne "-s" && $sortie ne "-f"){die "Paramètre de sortie invalide!\n";}

$temp1=".${fichier}1";
$temp2=".${fichier}2";

open TEMP1,">$temp1" || die "impossible de créer le fichier temporaire :$!\n";
open TEMP2,">$temp2" || die "impossible de créer le fichier temporaire :$!\n";

$i=0;
while($ligne=<FICHIER>){
  if($ligne=~s/$numero_ident :[:blank:]*(.*$)/\1/) 
    {
      $i++;
      $ligne=~s/[ ]+/\n/g;
      if ($i==1) { print TEMP1 $ligne; }
      if ($i==2) { print TEMP2 $ligne; }
    }
}

$commande="| gnuplot";
if($sortie eq "-f"){$commande="$commande > ${fichier}.ps";}
open PIPE,"$commande";

print PIPE "set label \"Ohhh le graphe\"\n";

if($sortie eq "-s"){
  print PIPE "set term X11 \n";
} else  {print PIPE "set term post \n";}

print PIPE "plot \"${temp1}\" with lines, \"${temp2}\" with lines\n";
print PIPE "pause 15";

close(FICHIER);
close(TEMP1);
close(TEMP2);

# ???? marche pas ?

# $ ./figure.pl resultats.tex 42 -f
# gnuplot> plot ".resultats.tex1" with lines, ".resultats.tex2" with lines
#                                             ^
#         can't read data file ".resultats.tex2"
#         line 0: util.c: No such file or directory

# unlink($temp1);
# unlink($temp2);
