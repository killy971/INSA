c(*****************************************************************************)
(* TP 02 - Analyse lexicale et crible : extension                             *)
(* NARGEOT Guillaume - HOANG Thi Hong Hanh                                    *)
(******************************************************************************)

open Stream;;
open String;;
(*** Définitions de types ***)

(* Liste des etats de l'automate pour l'analyse lexicale *)
type etat = E0 | E1 | E2 | E3 | E4 | E5 | E6 | E7 | E8 | Ef;;     (* modifiee *)
(* Unites lexicales a produire *)
type ul = Ul_ident of string | Ul_egal | Ul_inf | Ul_sup | Ul_diff |
          Ul_infegal | Ul_supegal | Ul_ou | Ul_et | Ul_opar | Ul_fpar | (* modifiee *)
          Ul_eof | Ul_si | Ul_sinon | Ul_fsi | Ul_alors;;         (* modifiee *)


(*** Définitions des fonctions ***)


(*
  automate : (etat) -> (char) -> (string) -> (etat,boolean,string)
  
  La fonction automate définit l'automate utilisé pour notre analyse lexicale.
  
  Elle prend sous forme curryfiée les paramètres : 
  etat : l'état actuel
  caractere_suivant : le caractère par lequel on doit faire la transition
  chaine : le pre-lexeme déjà reconnu, ie la chaine a laquelle il faut ajouter,
           ou pas, le caractere lu et qui sera un lexeme quand on sera dans 
           l'etat final.
  
  Son retour est un triplet (etat,flot,chaine) avec :
  etat : l'etat dans lequel la transition nous emmene
  booleen : est ce qu'il faut consommer le caractère de tete ou pas ?
  chaine : le pre-lexeme auquel a ete rajoute, ou pas, le caractere utilise pour
  la transition.
  
*)
let automate = function etat -> function caractere_suivant -> function chaine ->
  match (etat,caractere_suivant) with
    |(E0,'<') -> (E7,true,chaine^Char.escaped(caractere_suivant))
    |(E0,'/') -> (E4,true,"")
    |(E0,'=') -> (Ef,true,chaine^Char.escaped(caractere_suivant))
    |(E0,'>') -> (E8,true,chaine^Char.escaped(caractere_suivant)) (* modifiee *)
    |(E0,')') -> (Ef,true,chaine^Char.escaped(caractere_suivant))
    |(E0,'(') -> (Ef,true,chaine^Char.escaped(caractere_suivant))
    |(E0,' ') -> (E0,true,chaine)
    |(E0,'\n') -> (E0,true,chaine)
    |(E0,'\t') -> (E0,true,chaine)
    |(E0,'\000') -> (Ef,true,"\000")
    |(E0,'a'..'z') -> (E1,true,chaine^Char.escaped(caractere_suivant))
    |(E0,'A'..'Z') -> (E1,true,chaine^Char.escaped(caractere_suivant))
    |(E0,_) -> failwith "caractere non reconnu"                   (* modifiee *)
       
    |(E1,'a'..'z') -> (E1,true,chaine^Char.escaped(caractere_suivant))
    |(E1,'A'..'Z') -> (E1,true,chaine^Char.escaped(caractere_suivant))
    |(E1,'0'..'9') -> (E1,true,chaine^Char.escaped(caractere_suivant))
    |(E1,'\n') -> (Ef,true,chaine)
    |(E1,'\t') -> (Ef,true,chaine)
    |(E1,'\000') -> (Ef,true,chaine)
    |(E1,_) -> (Ef,false,chaine)                                  (* modifiee *)
       
    |(E4,'*') -> (E5,true,"")
    |(E4,_) -> failwith "erreur lexicale, '/' non suivi de *"     (* modifiee *)
       
    |(E5,'*') -> (E6,true,"")
    |(E5,'\000') -> failwith "fin de fichier trouvee au milieu du commentaire"    (* modifiee *)
    |(E5,_) -> (E5,true,"")                                       (* modifiee *)
       
    |(E6,'/') -> (E0,true,"")
    |(E6,'*') -> (E6,true,"")
    |(E6,_) -> (E5,true,"")                                       (* modifiee *)
       
    |(E7,'>') -> (Ef,true,chaine^Char.escaped(caractere_suivant))
    |(E7,'=') -> (Ef,true,chaine^Char.escaped(caractere_suivant)) (* modifiee *)
    |(E7,' ') -> (Ef,true,chaine)
    |(E7,'\n') -> (Ef,true,chaine)
    |(E7,'\t') -> (Ef,true,chaine)
    |(E7,_) -> (Ef,false,chaine)                                  (* modifiee *)

    |(E8,'=') -> (Ef,true,chaine^Char.escaped(caractere_suivant)) (* modifiee *)
    |(E8,' ') -> (Ef,true,chaine)                                 (* modifiee *)
    |(E8,'\n') -> (Ef,true,chaine)                                (* modifiee *)
    |(E8,'\t') -> (Ef,true,chaine)                                (* modifiee *)
    |(E8,_) -> (Ef,false,chaine)                                  (* modifiee *)
    | _ - > failwith "erreur non connu";;                         (* modifiee *)
 
(*
  crible : chaine -> (ul, chaine)
  
  Prend en entree un lexeme correct vis a vis du langage, et rend en retour un
  couple unite lexicale/chaine en fonction du lexeme recu en entree.
*) 

let crible = function chaine -> match chaine with
  |"et"    -> (Ul_et,"et")
  |"ou"    -> (Ul_ou,"ou")
  |"si"    -> (Ul_si,"si")                                        (* modifiee *)
  |"alors" -> (Ul_alors,"alors")                                  (* modifiee *)
  |"sinon" -> (Ul_sinon,"sinon")                                  (* modifiee *)
  |"fsi"   -> (Ul_fsi,"fsi")                                      (* modifiee *)
  |"<"     -> (Ul_inf,"<")
  |">"     -> (Ul_sup,">")
  |"<>"    -> (Ul_diff,"<>")
  |"="     -> (Ul_egal,"=")    
  |"<="    -> (Ul_infegal,"<=")                                   (* modifiee *)
  |">="    -> (Ul_supegal,">=")                                   (* modifiee *)
  |"("     -> (Ul_opar,"(")
  |")"     -> (Ul_fpar,")")
  |"\000"  -> (Ul_eof,"eof")
  |a       -> (Ul_ident a,a);;


(*
  peek : (input_stream) -> char
  Cette fonction réalise la même chose que Stream.peek, c'est à dire qu'elle
  délivre sans le consommer
  le premier caractère du flot
*)

let peek = function flot ->
  let car = try (input_char flot) with End_of_file -> '\000'
  in (seek_in flot ((pos_in flot) - 1)); 
    car;;
    

(*
 analex : (string) -> (stream) -> (etat) -> (fonction automate) -> (string)
 Cette fonction recursive fait l'analyse jusqu'a obtenir un
 etat final dans l'automate.
 Parametres : 
 chaine : la chaine a rajouter
 flot : le flot de caracteres source
 etat : l'etat dans lequel on est
 automate : un automate du langage
 Le retour est une chaine, qui correspond au 1er lexeme du flot
*)

let rec analex = function chaine -> function flot -> function etat -> function automate ->
  let caractere_suivant = peek flot in (
      let (etat_suivant,depiler,chaine_reco) = (automate etat caractere_suivant chaine) in 
	(match etat_suivant with 
	     Ef ->if depiler then (seek_in flot ((pos_in flot) +1)); chaine_reco
	   | _ -> if depiler then (seek_in flot ((pos_in flot) +1));
	                          (analex chaine_reco
	                                  flot
	                                  etat_suivant
	                                  automate)
	)
    );;

(*
  la fonction get_token réutilise la fonction automate en lui précisant les 
  arametres de depart c'est a dire l'etat initial e0
  le lexeme ainsi obtenu passe par la fonction crible, et get_token rend une
  unite lexicale
*)

let get_token = function flot -> function automate -> crible (analex
                                                              ""
                                                              flot
                                                              E0
                                                              automate) ;;

let rec scanner flot automate =
  let (token_suivant,st) = (get_token flot automate) in
    (match token_suivant with
       | Ul_eof -> [token_suivant]
       | _ -> [token_suivant]@(scanner flot automate)
    );;
let flot_test = open_in "test2";;



(******************************************************************************)
(* Jeux de test                                                               *)
(******************************************************************************)

1) Test les ident et les operateurs, sans espaces, avec reussite
si alors sinon fsi <><=>==<>><

2) Test les ident et les operateurs, avec reussite
si alors sinon fsi bouh < > <= >= = <> ) ( 

3) Test simples
jojo et ou ( ) /* fgjkdlgsf */ = <> sinon

4) test d'erreur
-

5) test d'erreur
~

6)  Test les commentaires
/* ~|+=\`-_*&^%$ */


(******************************************************************************)
(* Questions                                                                  *)
(******************************************************************************)

- Quel est l'impact de l'extension de la grammaire sur l'automate correspondant
  au langage de depart ?
- La representation choisie est-elle facilement extensible ?

Plusieurs branches de l'automate sont a modifier. Malgre tout, cette
representation est assez facilement extensible, les modifications sont tres
ciblees, et les modifications ne concernant pas l'automate sont triviales.
