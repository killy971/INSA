(*----------------------------------------------------------------------------*)
(* TP 01                                                                      *)
(*----------------------------------------------------------------------------*)

(* Exercice 1 *)

(* Etant donné un nombre, rend ce nombre multiplié par deux. *)
let mul2 = function x -> x*2;;

(* Etant donné un nombre, rend sa valeur absolue *)
let absolu = function x -> if(x > 0) then x else (-x);;

(* Etant donné un nombre, indique s'il est compris entre 12 et 29 *)
let compris = function x ->  x >= 12 && x <= 29;;
 
(* Etant donné un nombre, indique s'il est égal à 2, 5, 9, ou 53 *)
let egaly = function 
    2 -> true
|   5 -> true
|   9 -> true
|   53 -> true
|   _ -> false;;

let prem12 = function 
    (12,_) -> true
|   _ -> false;;

(* Exercice 2 *)

let proj1 = function (x,_,_) -> x;;
let proj23 = function (_,y,z) -> (y,z);;

let inv2 = function ((x,y),(z,t)) -> (t,z);;


(* Exercice 3 *)

let incrpaire = function (g,d) -> (g+1,d+1);;

(* prend en argument une fonction f de type (a->b) et rend une fonction qui
étant donné une paire (g,d) rend en résultat la paire (f(g),f(d)) *)

let appliqueapaire = function f -> function (a,b) -> (f a, f b);;

let rapport = function (f,g) -> function x -> (f(x)/.g(x));;

let tangente = rapport(sin,cos);;

let pi = 3.1415926535897932384626433822790;;

incrpaire (4,18);;
appliqueapaire ((+) 1) (4,18);;
tangente(pi/.4.);;

(** 
function x-> x*x : int -> int
(function x-> x*x) 3 : int (9)
function f -> function x -> (f(x+1))-1 : (int -> int) -> (int -> int)

(function f -> function x -> (f(x+1))-1) 3
marche pas (3 n'est pas une fonction)

(function f -> function x -> (f(x+1))-1) (function x -> x*x) : int -> int
((function f -> function x -> (f(x+1))-1) (function x -> x*x)) 3 : int (15)

(function f -> function x -> (f(x+1))-1) ((function x -> x*x) 3)
marche pas : 9 n'est pas une fonction
**)

(* Exercice 5 *)

let lex = function () -> if compris 3 then "erreur" else "bouh";;
(*
il n'y avait pas de else dans la conditionnelle
# let lex = function () -> if compris 3 then "erreur" else "bouh";;
val lex : unit -> string = <fun>
*)

(*----------------------------------------------------------------------------*)
(* TP 02                                                                      *)
(*----------------------------------------------------------------------------*)

(* Pair et impair *)

let pred = function x -> x-1;;

let rec pair n =
    if n<0 then pair (-n)
    else if (n==0) then true
    else if (n==1) then false
    else pair (n-2);;

let rec impair n =
    if n<0 then impair (-n)
    else if (n==0) then false
    else if (n==1) then true
    else impair (n-2);;

(* Variations sur sigma *)

let rec sigma (a,b) =
    if (a>b) then raise (Failure "intervalle non conforme")
    else if (a==b) then a
    else a + (sigma (a+1,b));;

let rec sigma2 f (a,b) =
    if (a>b) then raise (Failure "intervalle non conforme")
    else if (a==b) then f a
    else (f a) + (sigma2 f (a+1,b));;
(*
# sigma2 (fun x -> x*x) (0,10);;
- : int = 385
*)

let rec sigma3 f (a,i,b) (fc,vi) =
    if a>b then vi
    else fc (f a) (sigma3 f (a+i,i,b) (fc,vi));;

let rec sigma4 f (predarret,fctincrement) (fctcumul,vini) vdea =
    if (predarret vdea) then vini
    else fctcumul (f vdea)
                  (sigma4 f
                          (predarret,fctincrement)
                          (fctcumul,vini)
                          (fctincrement vdea));;

let cum f (a,b,dx) (fctcumul,vini) = sigma4 f
                                            ((fun x -> (x>b)),(fun x -> x+.dx))
                                            (fctcumul,vini)
                                            a;;

let integre f (a,b,dx) = cum (fun x -> (f x)*.dx)
                             (a,b,dx)
                             ((fun x -> fun y -> x+.y),0.);;
(*
# integre (fun x -> 1./.x) (1.,2.,0.0001);;
- : float = 0.693222181184964925
# log 2.;;
- : float = 0.693147180559945286
*)

(* Recherche du maximum d'une fonction *)

let rec maxi f (a,b) =
    let x = (2.*.a+.b)/.3. in
    let y = (a+.2.*.b)/.3. in
    if x>=y then (f ((x+.y)/.2.))
    else if (f x)>(f y) then maxi f (a,y) else maxi f (x,b);;

(*
# maxi (fun x -> (0.-.((x-.1.)*.(x-.1.)-.1.))) (0.,2.);;
- : float = 1.
# maxi cos (0.-3.14,3.14);;
- : float = 1.
# maxi sin (0.,2.*.3.14);;
- : float = 1.
# maxi (fun x -> 2.*.(sin x)) (0.,2.*.3.14);;
- : float = 2.
*)

(*----------------------------------------------------------------------------*)
(* TP 03                                                                      *)
(*----------------------------------------------------------------------------*)

(* Definition des donnees *)

let m1=((0.0,0.0),(0.172,0.496))
let m2=((0.076,0.3122),(0.257,0.204))
let m3=((0.821,(-0.028)),(0.030,0.845))
let m4=((0.024,(-0.356)),(0.323,0.074))

let v1=(1.496,(-0.091))
let v2=(0.494,0.133)
let v3=(0.088,0.176)
let v4=(0.470,0.260);;

(* La construction des transformations affines *)


let mt mat vec = match (mat,vec) with
    (((a,b),(c,d)),(e,f)) -> (a*.e+.b*.f,c*.e+.d*.f);;

let sv v1 v2 = match (v1,v2) with
    ((a,b),(c,d)) -> (a+.c,b+.d);;

let genapplin = function mat -> function x -> mt mat x;;

let gentraffine = function applin
                          -> function vec
                                     -> function x -> sv (applin x) vec;;

let applin1 = genapplin m1
let applin2 = genapplin m2
let applin3 = genapplin m3
let applin4 = genapplin m4;;

let les4tr = (gentraffine applin1 v1,
              gentraffine applin2 v2,
              gentraffine applin3 v3,
              gentraffine applin4 v4);;

(* Le choix d'une transformation parmi les 4 *)

let elemrang (a,b,c,d) = match (Random.int 4) with
    0 -> a
  | 1 -> b
  | 2 -> c
  | 3 | _ -> d;;

let traff = function () -> elemrang les4tr;;

(* La suite des points transformes *)

(* voir en bas *)

(* L'affichage de la suite des points *)

# load "graphics.cma";;

Graphics.set_color Graphics.magenta;;

let aff = function (x,y) -> Graphics.plot (int_of_float(x*.500.))
                                          (int_of_float(y*.500.)); (x,y);;

let rec suite = function
        0 -> (0.5,0.)
    |   n -> aff (traff () (suite (n-1)));;

let tout n=
    Graphics.open_graph "";
    suite n;
    read_int();
    Graphics.close_graph();;

tout 65000;;

(*----------------------------------------------------------------------------*)
(* TP 04 - Les listes                                                         *)
(*----------------------------------------------------------------------------*)

(* 1 : calcule le nombre d'elements de l *)

let rec longueur = function
    [] -> 0
  | x::r -> 1 + longueur r;;

(* 2 : teste si un element appartient a la liste *)

let rec appartient e = function
    [] -> false
  | x::r -> if (x==e) then true else appartient e r;;

(* 3 : calcule le rang de e dans l si e est dans l, 0 sinon *)

let rang e l =
    let rec rang_aux e l n = match l with
        [] -> 0
    |   x::r -> if (x==e) then n else rang_aux e r (n+1)
    in rang_aux e l 1;;

let rec rang_v2 e = function
        [] -> 0
    |   x::r when (x==e) -> 1
    |   x::r -> if (appartient e (x::r)) then (1+(rang_v2 e r)) else 0;; 

(* 4 : rend une liste constituee des n premiers elements de l*)

let rec debliste l n = match l with
    _ when n<0 -> raise (Failure "erreur: n<0")
  | [] when n>0 -> raise (Failure "n > longueur l")
  | [] when n>=0 -> []
  | x::r -> if (n>0) then x::(debliste r (n-1)) else [];;

(* 5 : rend une liste constituee des n derniers elements de l *)

let finliste l n = match l with
    [] -> []
  | liste when ((longueur liste)-n<=0) -> liste
  | x::r -> finliste r ((longueur r)-n);;

(* 6 : remplace toutes les occurrences de x par y dans la liste *)

let rec remplace x y l = match l with
    [] -> []
  | t::q -> if (t==x) then y::(remplace x y q) else t::(remplace x y q);;

(* 7 : calcule si la liste l commence l1 *)

let rec entete l l1 = match (l,l1) with
    ([],x) -> true
  | (x,[]) -> raise (Failure "longueur l > longueur l1")
  | (x1::r1,x2::r2) -> if (x1==x2) then (entete r1 r2) else false;;

(* 8 : calcule si la liste l est une sous-liste de l1 *)

let rec sousliste l l1 = match (l,l1) with
        ([],_) -> true
    |   (liste,x2::r2) -> if (entete liste (x2::r2))
                then true else (sousliste liste r2)
    |   _ -> false;;

(* 9 : ote de l1 la liste l si l commence l1, rend l1 sinon *)

let oter l l1 =
    let rec oter_aux l l1 = match (l,l1) with
        ([],[]) -> []
    |   ([],liste) -> liste
    |   (x1::r1,x2::r2) -> oter_aux r1 r2
    in if (entete l l1) then (oter_aux l l1) else l1;;


let oter_v2 l l1 = if (entete l l1)
        then finliste l1 ((longueur l1)-(longueur l))
        else l1;;

(* 10 : remplace les occurences disjointes de l1 dans l par l2 *)

let rec remplacel l1 l2 l = match (l1,l2,l) with
        (_,_,[]) -> []
    |   (liste1,liste2,x::r) -> if (entete liste1 (x::r))
                then liste2@(remplacel liste1
                            liste2
                            (finliste (x::r)
                                ((longueur (x::r))
                                -
                                (longueur liste1))
                            )
                        )
                else x::(remplacel liste1 liste2 r);;

(* 11 : supprime les occurences disjointes de l1 dans l *)

let rec supprimel l1 l = match (l1,l) with
        (_,[]) -> []
    |   (liste,x::r) -> if (entete liste (x::r))
                then (supprimel liste (finliste (x::r)
                                ((longueur (x::r))
                                -
                                (longueur liste))
                            )
                        )
                else x::(supprimel liste r);;

let supprimel_v2 = function l1 -> function l -> remplacel l1 [] l;;  

(*----------------------------------------------------------------------------*)
(* TP 05 - Les intervalles                                                    *)
(*----------------------------------------------------------------------------*)

(* Quelques fonctions sur les intervalles *)

let dans e (binf,bsup) = (e>=binf)&&(e<=bsup);;

let disjoint (binf1,bsup1) (binf2,bsup2) = (bsup1<binf2) || (bsup2<binf1);;

let recouvre (binf1,bsup1) (binf2,bsup2) = (min binf1 binf2,max bsup1 bsup2);;


(* Fonctions recursives sur les listes d'intervalles *)

let rec enveloppe = function
    x::[]-> x
  | x::r -> recouvre x (enveloppe r);;

(*      [] -> raise (Failure "erreur: liste vide") *)


(* Fonction qui dit si un entier appartient à un des intervalles d'une liste *)
let rec dans_liste e = function
        [] -> false
    |   x::r -> if (dans e x) then true else dans_liste e r;;

let endehors l =
    let rec endehors_aux = function
            (a,b) when a==b -> []
        |   (a,b) -> if(dans_liste a l) then (endehors_aux (a+1,b))
                            else a::(endehors_aux (a+1,b))
    in endehors_aux (enveloppe l);;

let endehorsli l = match (endehors l) with
    [] -> []
|   debut::[] -> [(debut,debut)]
|   debut::reste ->
    let rec endehorsli_aux = function
            (x::[],deb) -> (deb,x)::[] 
        |   (x::y::r,deb) -> if (x+1==y) then endehorsli_aux(y::r,deb)
                            else (deb,x)::(endehorsli_aux (r,y))
    in endehorsli_aux (debut::reste,debut);;


(* deb est le début de l'intervalle courant. Si les deux premiers éléments sont
consécutifs, on rappelle la fonction avec la liste sans son premier élément.
Sinon, on "ferme" l'intervalle avec deb et le premier élément de la liste, puis
on concatène au rappel de la fonction sur le reste de la liste. *)

let rec elem_final = function
        x::[] -> x
    |   x::r -> elem_final r;;

let borne_sup = function (binf,bsup) -> bsup;;

let simplifier l = match l with
        [] -> []
    |   (binf,_)::_ -> 
    let rec simplifier_aux = function
            (deb,ec) when (ec==(borne_sup (enveloppe l))) -> [(deb,ec)]
        |   (deb,ec) -> if (dans_liste ec l) then (simplifier_aux (deb,ec+1))
                             else (deb,ec-1)::(simplifier_aux(ec,ec+1))
    in simplifier_aux (binf,binf);;

(* marche pas ? *)

(*----------------------------------------------------------------------------*)
(* TP 06 - Reussite                                                           *)
(*----------------------------------------------------------------------------*)

(* Definition de la nature des objets *)

type coul = Trefle | Carreau | Coeur | Pique;;

type haut = Sept | Huit | Neuf | Dix | Valet | Dame | Roi | As;;

type carte = Carte of coul * haut;;

# load "carteduTP6.cmo";;

type coul = Trefle | Carreau | Coeur | Pique;;

type haut = Sept | Huit | Neuf | Dix | Valet | Dame | Roi | As;;

type carte = Carte of coul * haut;;


(* Accès à la couleur et la hauteur *)

let coul (Carte (h,c)) = c;;

let haut (Carte (h,c)) = h;;


(* Construction d'un paquet "battu" de 32 cartes *)

let random_carte =
    let random_carte_aux = function () -> match (Random.int 8) with
        0 -> Sept
    |   1 -> Huit
    |   2 -> Neuf
    |   3 -> Dix
    |   4 -> Valet
    |   5 -> Dame
    |   6 -> Roi
    |   7 -> As
    |   _ -> raise (Failure "-_-")
    in  function () -> match (Random.int 4) with
        0 -> Carte (Trefle,random_carte_aux ())
    |   1 -> Carte (Carreau,random_carte_aux ())
    |   2 -> Carte (Coeur,random_carte_aux ())
    |   3 -> Carte (Pique,random_carte_aux ())
    |   _ -> raise (Failure "-_-");;

let ajtcarte l = (random_carte ())::l;;

let faitjeu n =
    let rec faitjeu_aux n l = match (n,l) with
        (i,[]) -> faitjeu_aux (i-1) (ajtcarte [])
    |   (0,x::r) -> if (List.mem x r) then (faitjeu_aux 0 (ajtcarte r))
                                      else x::r
    |   (i,x::r) -> if (List.mem x r) then (faitjeu_aux i (ajtcarte r))
                                      else (faitjeu_aux (i-1) (ajtcarte (x::r)))
    in faitjeu_aux n [];;

faitjeu 32;;

(* On programme une reussite *)

let faitjeupaquets n = List.map (fun x -> [x]) (faitjeu n);;

faitjeupaquets 32;;

let rec reduc = function
    l when List.length l < 3 -> l
|   (x::r)::(y::s)::(z::t)::reste -> if ((coul x)=(coul z) || (haut x)=(haut z))
                    then ((y::s)@(x::r))::(z::t)::reste
                    else (x::r)::(reduc ((y::s)::(z::t)::reste));;

let rec reussite l =
    let l2 = reduc l
    in if ((List.length l2)=(List.length l)) then l else reussite l2;;

reussite (faitjeupaquets 32);;

(* Les modules *)

# load "graphics.cma";;

open CarteduTP6;;
open List;;
open Graphics;;

let rec draw_pile = function
    [] -> ()
|   x::r -> draw_cart x ; Graphics.rmoveto 0 15 ; draw_pile r;;
    
let draw_jeu l =
    let rec draw_jeu_aux = function
        [] -> ()
    |   x::r -> Graphics.moveto ((Graphics.current_x ())+30) 5;
                draw_pile x;
                draw_jeu_aux r
    in Graphics.open_graph "";
       Graphics.moveto 5 5;
       draw_jeu_aux l;
       Graphics.read_key ();
       Graphics.close_graph ();;

(* faudrait modif un peu pour bien ouvrir et fermer, etc *)

draw_jeu (reussite (faitjeupaquets 32));;

(*----------------------------------------------------------------------------*)
(* TP 07 - Tris                                                               *)
(*----------------------------------------------------------------------------*)

(* Le tri rapide (Quicksort) *)

(* attention !! bien penser au [x] !! *)

let rec qs = function
    [] -> []
|   x::r ->
    let (l1,l2) = List.partition (fun y -> if y<x then true else false) r
    in (qs l1)@[x]@(qs l2);;

(*
# qs [6;4;3;1;2;5;7;8];;
- : int list = [1; 2; 3; 4; 5; 6; 7; 8]
*)


(* Kieme plus petit element *)

let rec kieme n = function
    [] -> []
|   x::r ->
    let (l1,l2) = List.partition (fun y -> if y<x then true else false) r in
    let long1 = List.length l1 in
    let long2 = List.length l2 in
    if long1+1=n then [x] else
    if long1+1>n then kieme n l1 else kieme (n-long1-1) l2;;


(* Le trie a bulle *)

let rec jqastable x f = if (f x)=x then x else jqastable (f x) f;;

(* attention !! bien penser au x::[] !! *)

let rec bulle = function
    [] -> []
|   x::[] -> x::[]
|   x::y::r -> if x>y then y::(bulle (x::r)) else x::(bulle (y::r));;

let tribulle = function l -> jqastable l bulle;;

(*
# tribulle [6;4;3;1;2;5;7;8];;
- : int list = [1; 2; 3; 4; 5; 6; 7; 8]
*)


(* Partition d'un entier n *)

let rec parti = function
    (0,0) -> [[]]
|   (n,0) -> []
|   (n,k) when k>n -> parti(n,n)
|   (n,k) ->
    let parti2 = function (m,k) -> List.map (fun x -> k::x) (parti(m,k))
    in
    parti2(n-k,k)@parti(n,k-1);;

let rec partitions = function n -> parti(n,n);;

(*
# partitions 3;;
- : int list list = [[3]; [2; 1]; [1; 1; 1]]
# partitions 5;;
- : int list list =
[[5]; [4; 1]; [3; 2]; [3; 1; 1]; [2; 2; 1]; [2; 1; 1; 1]; [1; 1; 1; 1; 1]]
*)

(*----------------------------------------------------------------------------*)
(*                                                                            *)
(*----------------------------------------------------------------------------*)

(* TP n°8 - Les Fonctionnelles *)

(* Ensembles *)

let liste1 = [3;5;7;9];;
let liste2 = [6;20;9;7];;

let union l1 l2 = List.fold_left (function liste
                                          -> function x
                                            -> if (List.mem x liste)
                                               then liste
                                               else x::liste)
                                 l1
                                 l2;;

(*
# union liste1 liste2;;
- : int list = [20; 6; 3; 5; 7; 9]
*)

let intersection l1 l2 = List.fold_left
                                 (function liste
                                          -> function x
                                            -> if (List.mem x l1)
                                               then x::liste
                                               else liste)
                                 []
                                 l2;;

(*
# intersection liste1 liste2;;
- : int list = [7; 9]
*)

let touscouples l1 l2 = List.fold_left
                                (function liste
                                         -> function x
                                           -> liste@(List.map
                                                         (function y -> (x,y))
                                                         l2))
                                []
                                l1;;

(*
# touscouples [1;4;7] [8;3];;
- : (int * int) list = [(1, 8); (1, 3); (4, 8); (4, 3); (7, 8); (7, 3)]
*)

let ajout e l = (List.map (function x -> e::x) l)@l;;

let partitions = function l -> List.fold_right ajout l [[]];;
(*
# partitions [1;2;3];;
- : int list list = [[1; 2; 3]; [1; 2]; [1; 3]; [1]; [2; 3]; [2]; [3]; []]
*)

(* Combinateur point fixe *)

(* 1 *)

(* annexe *)

(* 2 - calcule la taille d'une liste *)

g [1;2] = y f [1;2]
        = f (y f) [1;2]
        = 1+(y f) [2]
        = 1+f (y f) [2]
        = 1+1+(y f) []
        = 1+1+f (y f) []
        = 1+1+0
        = 2

let g = y (function h -> (function [] -> 0 | a::l -> 1+h l));; 

(* 3 *)

let fact = y (function h -> (function 0 -> 1 | x -> x*(h (x-1))));;

(*----------------------------------------------------------------------------*)
(* TP 09 - Les arbres binaires                                                *)
(*----------------------------------------------------------------------------*)

type 'a arbin = Feuille of 'a | Nil | Noeud of 'a * 'a arbin *'a arbin;;

(* Compte le nombre de feuilles d'un arbre *)

let rec compter = function
    Nil -> 0
|   Feuille _ -> 1
|   Noeud (_,a1,a2) -> (compter a1)+(compter a2);;

let arbin1 = Noeud (1,
                    Noeud (2,
                           Feuille 3,
                           Noeud(4,Feuille 41,Feuille 42)),
                    Noeud (5,Feuille 6,Feuille 7));;

(*
# compter arbin1;;  
- : int = 5
*)

(* Construit un tas a partir d'une liste *)

let rec insere e = function
    Nil -> Feuille e
|   Feuille f -> if (e>f)
                 then Noeud (f,Nil,Feuille e)
                 else Noeud (f,Feuille e,Nil)
|   Noeud (n,f1,f2) -> if (e>n)
                       then Noeud (n,f1,insere e f2)
                       else Noeud (n,insere e f1,f2);;

let constr l =
        let rec constr_aux a = function
            [] -> a
        |   x::reste -> constr_aux (insere x a) reste
    in constr_aux Nil l;;

let liste1 = ["orge";"mais";"ble";"tuyau"];;

(*
# constr liste1;;
- : string arbin = Noeud ("orge",
                          Noeud ("mais", Feuille "ble", Nil),
                          Feuille "tuyau")
*)


(* Mise en page de l'arbre de Huffman *)

type coord = int * int;;
type huffp = Fp of coord * char | Np of coord * huffp * huffp;;
type huff = F of char | N of huff * huff;;
let d = 5;;
let e = 4;;

(* idem que compter mais pour les arbres de huffman *)

let rec compter_huff = function
    F _ -> 1
|   N (a1,a2) -> (compter_huff a1)+(compter_huff a2);;

(* Calcule le décalage de l'abcisse *)

let decal_abs_g = function
    F v -> 0
|   N (fg,fd) -> ((compter_huff fd)*2-1)*e;;
let decal_abs_d = function
    F v -> 0
|   N (fg,fd) -> ((compter_huff fg)*2-1)*e;;

(* A chaque placement, en renvoie le noeud courant, puis on place ses fils aux
coordonnées attendus en y ajoutant un décalage calculer sur le sous arbre de
chacun des fils *)

let placerhuff =
    let rec placerhuffp_aux a (x,y) = match a with
        F v -> Fp ((x,y),v) 
    |   N (fg,fd) -> Np ((x,y),
                         placerhuffp_aux fg (x-(decal_abs_g fg)-e,y+d),
                         placerhuffp_aux fd (x+(decal_abs_d fd)+e,y+d))
    in function 
        F v -> placerhuffp_aux (F v) (e,d)
    |   N (fg,fd) -> placerhuffp_aux (N (fg,fd))
                                     (e+(decal_abs_d (N (fg,fd))),d);;

(*
# placerhuff (N (F 'j',N (N (N (F 'w',F 'k'),F 'z'),F 'y')));;
- : huffp =
Np ((8, 5), Fp ((4, 10), 'j'),
 Np ((32, 10),
  Np ((24, 15), Np ((16, 20), Fp ((12, 25), 'w'), Fp ((20, 25), 'k')),
   Fp ((28, 20), 'z')),
  Fp ((36, 15), 'y')))
*)

(*----------------------------------------------------------------------------*)
(* TP 10 - Les arbres n-aires                                                 *)
(*----------------------------------------------------------------------------*)

type 'a arbre = Feuille of 'a | Noeud of 'a * 'a arbre list;;

let arbre1 = Noeud(5,
                   [Noeud(3,
                          [Feuille 4;
                           Noeud(7,[Feuille 10;Feuille 12;Feuille 13]);
                           Feuille 20]);
                    Feuille 21]);;

let arbre2 = Noeud (7,
                    [Feuille 1; Feuille 2; Feuille 0; Feuille 14; Feuille 15]);;

let arbre3 = Noeud(5,
                   [Noeud(3,
                          [Feuille 4;
                           Noeud(7,[Feuille 10;Feuille 12;Feuille 13]);
                           Feuille 20]);
                    Feuille 21]);;

(* Compte le nombre de feuilles d'un arbre *)

let rec compter = function
    Feuille _ -> 1
|   Noeud (_,[]) -> 0
|   Noeud (_,l) -> List.fold_left (+) 0 (List.map compter l);;

(*
# compter arbre1;;
- : int = 6
*)
                
let rec compter = function
    Feuille _ -> 1
|   Noeud (_,[]) -> 0
|   Noeud (_,l) -> List.fold_left (fun x -> fun y -> x + (compter y))
                                  0
                                  l;;

(* Determine la longueur de la plus haute
branche d'un arbre (hauteur de l'arbre) *)

let rec plushaute = function
    Feuille _ -> 0
|   Noeud (_,[]) -> 0
|   Noeud (_,l) -> 1 + List.fold_left (fun x -> fun y -> max x (plushaute y))
                                      0
                                      l;;

(*
# plushaute arbre1;;
- : int = 3
*)

let rec plushaute = function
    Feuille _ -> 0
|   Noeud (_,[]) -> 0
|   Noeud (_,l) -> 1 + List.fold_left (fun x -> fun y -> max x y)
                                      0
                                      (List.map plushaute l);;

(* Retourne la liste des sous arbre d'un arbre *)

let rec listsa = function
    Feuille f -> [Feuille f]
|   Noeud (v,l) -> List.fold_left (@) [Noeud (v,l)] (List.map listsa l);; 


(* Retourne la liste des branches d'un arbre *)

(* la fonction listbr_aux prend en 1er arg la liste des valeurs de
noeud parents, et en 2ème argument l'arbre fils *)

let listbr a =
    let rec listbr_aux l = function
        Feuille f -> [l@[f]]
    |   Noeud (v,n) -> List.fold_left (@) [] (List.map (listbr_aux (l@[v])) n)
in listbr_aux [] a;;

(* Teste l'égalité de 2 arbres *)

let rec egal a1 a2 = match (a1,a2) with
    (Feuille f1,Feuille f2) when f1=f2 -> true
|   (Noeud (v1,l1),Noeud (v2,l2)) when v1=v2 ->
        List.fold_left (&)
                       true
                       (List.map (fun (x,y) -> egal x y) (List.combine l1 l2))
|   _ -> false;;

let rec egal = function
    Noeud(v1,l1),Noeud(v2,l2) -> (v1 = v2) 
                              && (for_all2 (fun x y -> (egal (x,y))) l1 l2)
  | Feuille(v1),Feuille(v2) -> v1 = v2
  | _ -> false;;

(* Remplace a1 par a2 dans a *)

let rec remplace a1 a2 a = match a with
    Feuille f -> if (egal a1 (Feuille f))
                 then a2
                 else Feuille f
|   Noeud (v,l) -> if (egal a1 (Noeud (v,l)))
                   then a2
                   else Noeud (v,(List.map (remplace a1 a2) l));;

(*----------------------------------------------------------------------------*)
(* TP11 - Flots                                                               *)
(*----------------------------------------------------------------------------*)

open List;;

#load "camlp4o.cma";;
#load "pa_extend.cmo";;

(* Analyseur lexical *)

let rec blcs = parser
    [<''\t'|'\n'|' ';()=blcs>] -> ()
|   [<>] -> ();;

(*
let s = Stream.of_string "    dsf s   ";;
blcs s;;
val blcs : char Stream.t -> unit = <fun>
val s : char Stream.t = <abstr>
- : unit = ()
*)

type unitlex =  Parg | Pard | Ident of char |
                Chiffre of int | Fleche | Virg | Ptvirg;;

let parg    = parser [<''('>]           -> Parg;;
let pard    = parser [<'')'>]           -> Pard;;
let ident   = parser [<''a'..'z' as i>] -> Ident i;;
let chiffre = parser [<''0'..'9' as c>] -> Chiffre ((int_of_char c) -
                                                    (int_of_char '0'));;
let fleche  = parser [<''-';''>'>]      -> Fleche;;
let virg    = parser [<'','>]           -> Virg;;
let ptvirg  = parser [<'';'>]           -> Ptvirg;;

let rec analex s = 
    blcs s;
    match s with parser
        [<u=parg>]    -> [<'u;analex s>]
    |   [<u=chiffre>] -> [<'u;analex s>]
    |   [<u=virg>]    -> [<'u;analex s>]
    |   [<u=ident>]   -> [<'u;analex s>]
    |   [<u=pard>]    -> [<'u;analex s>]
    |   [<u=fleche>]  -> [<'u;analex s>]
    |   [<u=ptvirg>]  -> [<'u;analex s>];;


let bouh = analex (Stream.of_string 
"
(9,c) -> 4      (9,n) -> 2       (9,e) -> 9
(5,e) -> 3
(4,j) -> 9      (2,k) -> 4;
");;


(* Analyseur syntaxique *)

let trans = parser [<'Parg;'Chiffre e_init;'Virg;'Ident i_trans;
                     'Pard;'Fleche;'Chiffre e_final>] -> ();;

let rec auto = parser 
    [<()=trans;()=auto>] -> ()
  | [<'Ptvirg>] -> ();;

(* auto bouh;; *)


(* Compilateur d'automate *)

type etat    = Q of int;;
type symbole = X of char;;
type auto    = etat * symbole -> etat;;
exception Automate of string * (etat * symbole);;

let ftransvide = function Q(e),X(s) -> raise(Automate ("fonction de transition 
                                       non definie en ",(Q e,X s)));;

let deffonctpart a b = function 
    x when (x=a) -> b
|   _ -> raise(Failure "rate");;

let ajttrans f g = function x -> try (f x) with (Failure "rate") -> g x;;

ajttrans (deffonctpart (Q 9, X 'n') (Q 2)) (ajttrans (deffonctpart (Q 9, X 'c')
                                                     (Q 4))
                                                     (ftransvide));;

let trans = parser [<'Parg;'Chiffre e_init;'Virg;'Ident i_trans;
                     'Pard;'Fleche;'Chiffre e_final>] -> 
    deffonctpart (Q e_init,X i_trans) (Q e_final);;
(* val trans : unitlex Stream.t -> etat * symbole -> etat = <fun> *)

let rec auto = parser 
    [<g=trans;f=auto>] -> (ajttrans g f)
|   [<'Ptvirg>] -> ftransvide;;
(* val auto : unitlex Stream.t -> etat * symbole -> etat = <fun> *)

let automate = auto bouh;;
(*
# (automate (Q 4,X 'h'));;
Exception: Automate ("fonction de transition non definie en ", (Q 4, X 'h')).
# (automate (Q 4,X 'j'));;
- : etat = Q 9
# (automate (Q 9,X 'e'));;
- : etat = Q 9
*)

(*----------------------------------------------------------------------------*)
(*                                                                            *)
(*----------------------------------------------------------------------------*)
