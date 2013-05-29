#*----------------------------------------------------------------------------*/
#* TP 01                                                                      */
#*----------------------------------------------------------------------------*/

# Question 1)

# Question 2)

grep '^[[:blank:]]*[a-zA-Z][a-zA-Z0-9]*[[:blank:]]*:' source.c

# Question 3)

grep '^[[:blank:]]*[^a-zA-Z0-9[:blank:]]' source.c

# Question 4)

1ere solution : grep '[^a-z0-9]while[^a-z0-9]' source.c
2eme solution : grep '\while\b' source.c

# Question 5)

grep '.*[a-zA-Z0-9]*\[[0-9]*\]' source.c

# Question 6)

ls *.c

# Question 7)

grep -c '++' source.c

# Question 8)

grep -v ';$' source.c

# marche pas ?

# Question 9)

1ere solution : grep \\\\begin f.tex
2eme solution : grep '\\begin' f.tex

# Question 10)

# Question 11)

- 11.2)

#!/usr/bin/perl -n
/\s*[a-zA-Z]\w*\s*:/ && print;

- 11.3)

perl -n -e "/^\s*[^\w\s]/ && print;" source.c

- 11.4)

perl -n -e "/while\b/ && print;" source.c

- 11.5)

perl -n -e "/.*\w*\[\d*\]/ && print;" source.c

- 11.7)

perl -n -e "/.*\w*\[\d*\]/ && print;" source.c | wc -l | e

- 11.8)

# ??

- 11.10)

a)

perl -n -e "/^[\s]*[A-Z]+[A-Z\s]*$/ && print;" source.c

b)

# perl -n -e ""


# Question 12)

#!/bin/perl -n
!/^\s*$/ && print;

# Question 12 bis)


# Question 13)

#!/bin/perl -p
s/^[\s]*//;

# ou bien

perl -p -e "s/^[\s]*//;" source.c

# efface la première suite d'espaces rencontrés en début de chaque ligne

# Question 14)

#!/bin/perl -p
/\/\*/ && s/[ ][ ]*/ /g;

# ou bien :

perl -p -e "/\/\*/ && s/[ ][ ]*/ /g;" source.c

# substitue toutes les suites d'un espace ou plus par un seul espace dans les lignes contenant /*

# Question 15)

#!/bin/perl -p
y/bdfhjlnprtvxz/acegikmoqsuwy/;

# remplace toutes les lettres (minuscules) de rang impair
# par la lettre qui les precede dans l'alphabet


#*----------------------------------------------------------------------------*/
#* TP 02                                                                      */
#*----------------------------------------------------------------------------*/

#!/usr/bin/perl

use File::Copy;

if($#ARGV!=2)
{
    print "$#ARGV\n";
    die "Syntaxe: $0 fichier_resultat numero_ident type_de_sortie\n";
}

$fichier=@ARGV[0];
$numero_ident=@ARGV[1];
$sortie=@ARGV[2];

if(!open(FICHIER,"<$fichier")) {die "Problème d'ouverture du fichier!: $!\n";}
if($sortie ne "-s" && $sortie ne "-f") {die "Paramètre de sortie invalide!\n";}

$temp1=".${fichier}1";
$temp2=".${fichier}2";

open TEMP1,">$temp1" || die "impossible de créer le fichier temporaire :$!\n";
open TEMP2,">$temp2" || die "impossible de créer le fichier temporaire :$!\n";

$i=0;
while($ligne=<FICHIER>)
{
    if($ligne=~s/$numero_ident :[:blank:]*(.*$)/\1/) 
    {
        $i++;
        $ligne=~s/[ ]+/\n/g;
        if ($i==1) { print TEMP1 $ligne; }
        if ($i==2) { print TEMP2 $ligne; }
    }
}

$commande="| gnuplot";
if($sortie eq "-f")
{
    $commande="$commande > ${fichier}.ps";
}
open PIPE,"$commande";

print PIPE "set label \"Ohhh le graphe\"\n";

if($sortie eq "-s")
{
    print PIPE "set term X11 \n";
}
else
{
    print PIPE "set term post \n";
}

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

#*----------------------------------------------------------------------------*/
#* TP 03                                                                      */
#*----------------------------------------------------------------------------*/

#!/usr/bin/perl

if($#ARGV!=1){
    print "$#ARGV\n";
    die "Syntaxe: $0 fichier_orig fichier_resul\n";
}

$fichier_orig  = $ARGV[0];
$fichier_resul = $ARGV[1];

if(!open(FICHIER,"<$fichier_orig"))
{
    die "Problème d'ouverture du fichier origine!: $!\n";
}
if(-e "$fichier_resul")
{
    die "Fichier resultat déj?existant!: $!\n#";
}
if(!open(RESULT,">$fichier_resul"))
{
    die "Problème de creation du fichire resultat!: $!\n";
}

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
    elsif($nature eq "DEAT"|
          $nature eq "BIRT"|
          $nature eq "MARR")
    {
        print RESULT "<EVEN EV=\'$nature\'>\n";
        push(@endpile,"</EVEN>\n");
    }
    elsif($nature eq "FAMS"|
          $nature eq "HUSB"|
          $nature eq "WIFE"|
          $nature eq "CHIL"|
          $nature eq "FAMC")
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

#*----------------------------------------------------------------------------*/
#* TP 04                                                                      */
#*----------------------------------------------------------------------------*/

#!/usr/bin/perl

if($#ARGV<1)
{
    die "\nSyntaxe: $0 rep_cible/ fichier1.tcl [fchier2.tcl ...]\n";
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
        die "Erreur de creation du fichier temporaire: $!\n";
    }

    # type du retour
    $ret = "void";
    # tableau associatif 'nom => type'
    %types;

    while($ligne=<F_TCL>)
    {
        if($ligne=~ /namespace\seval\s(\w*)\s{/)
        {
            print F_CPP "namespace $1 {\n\n";
        }

        # si on commence un header de commentaire,
        # on écrit "/**" et on met dans la pile "*/"
        # qui servira ?se rappeler qu'on est dans un
        # commentaire
        if ($ligne =~ /^\#\#/)
        {
            print F_CPP "\t/**\n";
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
                print F_CPP "\t * \@param ".$2." ".$3."\n";
            }
            # ligne de commentaire @return
            elsif ($ligne =~ /^\#\s*\@return\s*\[(\w+)\]\s*(.*)$/)
            {
                $ret = $1;
                print F_CPP "\t * \@return ".$2."\n";
            }
            # ligne de commentaire simple
            elsif ($ligne =~ /^\# (.*)$/)
            {
                print F_CPP "\t * ".$1."\n";
            }
            # fin de l'header de commentaire
            else
            {
                print F_CPP "\t */\n";
                $in_comment=0;
            }
        }
        # ligne de début de fonction (pas besoin de matcher plus de trucs non ?)
        elsif ($ligne =~ /^proc\s\w+::(\w+)\s\{\s?(.*)\s?\}\s{/)
        {
            @parametres = split(/\s+/,$2);
            print F_CPP "\tpublic ".$ret." ".$1." (";
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
print F_CPP "}";
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
        # il sert ?quoi ce foutu rep_cible en fait ?
        # repertoire ou se trouve les .h ? rep pour la doc ?
        # print TEMP_CFG "OUTPUT_DIRECTORY = $rep\n";
        
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

#*----------------------------------------------------------------------------*/
#* TP 05                                                                      */
#*----------------------------------------------------------------------------*/

#!/usr/bin/perl -w

require 5.003;
use strict;
use Socket;
use IO::Socket::INET;

my (%eMail, %ePhoto, %ePrenom); # Données assoc. ?chaque personne du trombinoscope

my $proxy="www.insa-rennes.fr";# Nom des serveurs
my $serveur="webi.insa-rennes.fr";

sub getinfo{
    my($NOM,$PRENOM)=@_; # Récupération des paramètres

    # Requête ?envoyer au serveur
    my $requete= "pnom=$NOM&pprenom=$PRENOM&cycle=NULL&cycle1choix=1?E8re+ann?".
                 "E9e&cycle2choix=3?E8me+ann?E9e&lib_dept=&cycle3choix=";
    my $LONG=length($requete); # Taille de la requête
    my $socket; # Socket pour discuter avec le serveur
    $socket= IO::Socket::INET->new("$proxy:8080"); # Connexion au serveur
    select($socket); $| = 1; select(STDOUT); # Vidage immédiat du buffer de sortie

    # Envoi de la requête d'interrogation
    print $socket <<ENTETE;
POST http://$serveur/pls/ann/package_annu.fait_req_simp HTTP/1.0
Referer: http://$serveur/pls/ann/package_annu.fait_req_simp
Connection: Keep-Alive
User-Agent: Mozilla/4.78 [en] (X11; U; Linux 2.4.9-13 i686)
Host: $serveur
Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, image/png, */*
Accept-Encoding: gzip Accept-Language: en
Accept-Charset: iso-8859-1,*,utf-8
Content-type: application/x-www-form-urlencoded
Content-length: $LONG

$requete
ENTETE
 
    my($nom);my($prenom);my($email);my($img);

    my($count)=3;
    while (<$socket>) {
        if($_=~/<H4>([A-Z']+)\s([^<]+)<\/H4>/)
        {
            $nom=$1;
            $prenom=$2;
            $count--;
        }
        elsif($_=~/<IMG SRC="([^"]*)/)
        {
            $img=$1;
            $count--;
        }
        elsif($_=~/<A HREF="mailto:([^"]*)/)
        {
            $email=$1;
            $count--;
        }
        if ($count==0) { last; }
    }
    # Fermeture complète de la connexion
    close($socket) || die "close: $!";

    return ($nom,$prenom,$email,$img);
}

sub genererHTML{
    my ($nomFic,$nbCol,$larg)=@_; # Récupération des paramètres
    # Ouverture du fichier contenant le nom des étudiants
    open(FICHTML,">$nomFic") || die "On ne peut ouvrir le fichier $nomFic\n";
    
    print FICHTML <<ENTETE; # Génération de l'entête
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <meta name="Author" content="Ivan Leplumey">
    <meta name="Description" content="Trombinoscope">
    <title>Trombinoscope</title>
</head>
<body>
<p><br>
<table BORDER COLS=$_[1] WIDTH="100%" ><tr>
ENTETE

    my($pouet) = "";
    my(@noms) = keys(%ePrenom);
    my($n);

    foreach $n (@noms) {
        $pouet .= '<td>'."\n".'<center><a href="mailto:'.$eMail{$n}.'">';
        $pouet .= $n.'</br>'.$ePrenom{$n}."</br>\n";
        $pouet .= '<img SRC="http://'.$serveur.$ePhoto{$n}.'" width='.$larg.'>';
        $pouet .= "\n</a></center></td>\n";
    }

    # Génération de la fin du fichier
    print FICHTML <<FIN;
$pouet
</tr></table>
</body>
</html>
FIN

    # Fermeture du fichier HTML
    close(FICHTML);
}

# =======================================================
# Programme principal
# =======================================================

if($#ARGV!=1)
{
    print "$#ARGV\n";
    die "Syntaxe: $0 fichier_entree fichier_sortie\n";
}

my $fichier_entree=$ARGV[0];
my $fichier_sortie=$ARGV[1];
my $ligne;

if(!open(ENTREE,"<$fichier_entree"))
{
    die "Problème d'ouverture du fichier d'entrée!: $!\n";
}

if(-e "$fichier_sortie")
{
    die "Fichier sortie déj?existant!: $!\n#";
}

my($nb_col);

while($ligne=<ENTREE>)
{
    if($ligne=~/\s*(\d+)/)
    {
        $nb_col=$1;
    }
    elsif($ligne=~/\s*([a-zA-Z\*]+);\s*([a-zA-Z\*]+)/)
    {
        my($nom)    = $1;
        my($prenom) = $2;
        ($nom,$prenom,my($email),my($photo)) = getinfo($nom,$prenom);
        $eMail{$nom}   = $email;
        $ePhoto{$nom}  = $photo;
        $ePrenom{$nom} = $prenom;
    }
}
genererHTML($fichier_sortie,$nb_col,'100%');