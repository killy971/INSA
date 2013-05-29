(* TP 09 - Les arbres binaires *)

type 'a arbin = Feuille of 'a | Nil |Noeud of 'a * 'a arbin *'a arbin;;

(* Compte le nombre de feuilles d'un arbre *)

let rec compter = function
	Nil -> 0
|	Feuille _ -> 1
|	Noeud (_,a1,a2) -> (compter a1)+(compter a2);;

let arbin1 = Noeud (1,Noeud (2,Feuille 3,Noeud(4,Feuille 41,Feuille 42)),Noeud (5,Feuille 6,Feuille 7));;

(*
# compter arbin1;;  
- : int = 5
*)

(* construit un tas a partir d'une liste *)

let rec insere e = function
	Nil -> Feuille e
|	Feuille f -> if (e>f) then Noeud (f,Nil,Feuille e)
			      else Noeud (f,Feuille e,Nil)
|	Noeud (n,f1,f2) -> if (e>n) then Noeud (n,f1,insere e f2)
				    else Noeud (n,insere e f1,f2);;

let constr l =
		let rec constr_aux a = function
			[] -> a
		|	x::reste -> constr_aux (insere x a) reste
	in constr_aux Nil l;;

let liste1 = ["orge";"mais";"ble";"tuyau"];;

(*
# constr liste1;;
- : string arbin = Noeud ("orge", Noeud ("mais", Feuille "ble", Nil), Feuille "tuyau")
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
|	N (a1,a2) -> (compter_huff a1)+(compter_huff a2);;

(* calcule le décalage de l'abcisse *)
let decal_abs_g = function
	F v -> 0
|	N (fg,fd) -> ((compter_huff fd)*2-1)*e;;
let decal_abs_d = function
	F v -> 0
|	N (fg,fd) -> ((compter_huff fg)*2-1)*e;;

(*
à chaque placement, en renvoie le noeud courant, puis on place ses fils aux coordonnées attendus en y ajoutant un décalage calculer sur le sous arbre de chacun des fils
*)
let placerhuff =
	let rec placerhuffp_aux a (x,y) = match a with
		F v -> Fp ((x,y),v) 
	|	N (fg,fd) -> Np ((x,y),placerhuffp_aux fg (x-(decal_abs_g fg)-e,y+d),
					placerhuffp_aux fd (x+(decal_abs_d fd)+e,y+d))
	in function 
		F v -> placerhuffp_aux (F v) (e,d)
	|	N (fg,fd) -> placerhuffp_aux (N (fg,fd)) (e+(decal_abs_d (N (fg,fd))),d);;

(*
# placerhuff (N (F 'j',N (N (N (F 'w',F 'k'),F 'z'),F 'y')));;
- : huffp =
Np ((8, 5), Fp ((4, 10), 'j'),
 Np ((32, 10),
  Np ((24, 15), Np ((16, 20), Fp ((12, 25), 'w'), Fp ((20, 25), 'k')),
   Fp ((28, 20), 'z')),
  Fp ((36, 15), 'y')))
*)
