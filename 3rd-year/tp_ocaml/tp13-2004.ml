#load "camlp4o.cma";;
(*        Camlp4 Parsing version 3.04 *)
#load "pa_extend.cmo";;


open List;;

module Ensemble = 
  struct
      type t = int
      let compare = compare
  end;;
module Ens = Set.Make(Ensemble);;
module EnsEnt =
  struct 
      type t = int*int
      let compare = compare
end;;
module EnsEns =
  struct 
      type t = Ens.t
      let compare = compare
end;;
module EnsEntEnt = Set.Make(EnsEnt);;
module EnsEnsEnt = Set.Make(Ens);; 


(*********************************)
(* 1ere Question *)   
type  op = Et | Ou;;
type opb = Etb | Oub;;
type  arbre = Feuille of bool
               | Noeud of op *  arbre list;;

type   arbin = Feuilleb of bool
             | Noeudb of opb *  arbin  *   arbin;; 



let a = Noeud  (Et,[Noeud(Ou,[Feuille true;Noeud(Et,[Feuille false;Feuille false; Feuille true]);
Feuille false]);Feuille true]);;




let trad = function Et -> Etb
                   | Ou -> Oub;;

let rec traduc = function Feuille a -> Feuilleb a
               | Noeud(x,l) -> (match l with 
                     a::b::[] -> Noeudb(trad x, traduc a, traduc b)
                     | a::b::l -> Noeudb(trad x, traduc a, traduc(Noeud(x,b::l))));;

(**********************************)
(* 2eme Question *)

type op = Plus|Moins|Mul|Div;;
type expr = Const of int
            | Var of char
            | Oper of op*expr*expr;;


let rec simplifie = function Const i  -> Const i
                            |Var c -> Var c
                            | Oper(op, l,r)-> 
                              let (ll,rr) = (simplifie l,simplifie r) 
in match (op,ll,rr) with
                    (Plus,Const 0 ,e) -> e | (Plus,e,Const 0 ) -> e
                    | (Mul,Const 0,e) -> Const 0 | (Mul,e,Const 0) -> Const 0
                    |  (Mul,Const 1,e) -> e | (Mul,e,Const 1) -> e
                     | (Moins,Const 0,e) -> e | (Div,e,Const 1) -> e
                     | (x,y,z) -> Oper (x,y,z);;

(*************************************)
(*3eme Question*)

let ens =  Ens.add 1 (Ens.add 2(Ens.add 3(Ens.add 4(Ens.add 6
 (Ens.empty )))));;
let ens3 = Ens.add 1 (Ens.add 3  (Ens.empty));;
let ens2 = Ens.add 2 (Ens.add 4 (Ens.add 6 (Ens.empty)));;
let ens4 = EnsEnsEnt.add ens3(EnsEnsEnt.add ens2 (EnsEnsEnt.empty));;
let rel = EnsEntEnt.add (1,1)(EnsEntEnt.add (2,2)(EnsEntEnt.add (3,3)(EnsEntEnt.add (4,4)(EnsEntEnt.add (6,6)(EnsEntEnt.add (1,3)(EnsEntEnt.add (3,1)(EnsEntEnt.add (2,4)(EnsEntEnt.add (2,6)(EnsEntEnt.add (4,6)(EnsEntEnt.add (6,2)(EnsEntEnt.add (6,4)(EnsEntEnt.add (4,2)(EnsEntEnt.empty)))))))))))));;

let rel1 = EnsEntEnt.add (1,2)(EnsEntEnt.add (1,3)(EnsEntEnt.add (1,4)(EnsEntEnt.add (1,6)(EnsEntEnt.add (2,3)(EnsEntEnt.add (2,4)(EnsEntEnt.add (2,6)(EnsEntEnt.add (3,4)(EnsEntEnt.add (3,6)(EnsEntEnt.add(4,6)(EnsEntEnt.empty))))))))));;

(*Q-3-1*)
let fonc r = function x -> function y -> EnsEntEnt.mem (x,y) r;;
let ordretotal r = Ens.for_all (function x -> 
                                           (Ens.for_all (function y ->
((fonc r) x y) || ((fonc r) y x)) (Ens.remove x ens))) ens;;


(*Q-3-2*)
let unionens e t = EnsEnsEnt.union (EnsEnsEnt.add e (EnsEnsEnt.empty)) t;; 
let emptyens = EnsEnsEnt.empty;;
let fonc r = function x -> function y -> EnsEntEnt.mem (x,y) r;;
let part = function r ->  let foncr = (fonc r)  
 in 
 
     Ens.fold (function x -> function t -> 
               let (e1,e2) = Ens.partition (foncr x) ens in

              unionens e1  t)  ens 
                  (EnsEnsEnt.empty)       ;;

let parlist r = EnsEnsEnt.fold(function x -> function t -> (Ens.elements x)::t)
                 r [];;


(************************************************)
(* 4eme Question*)
type 'a arbre = Feuille of 'a
                | Noeud of 'a arbre list;;

(*Q-4-1*)

let rec liste_feuilles = function Feuille x -> [x]
                              | Noeud(l) -> flatten (map liste_feuilles l);;


let a = Noeud  ([Noeud([Feuille 4;Noeud([Feuille 10;Feuille 12; Feuille 13]);
Feuille 20]);Feuille 21]);;


(*Q-4-3*)

let rec rang e = function (a::l) -> if a = e then 1 else 1+ (rang e l);;
let fonct (l1,l2) = function t -> if  mem t l1 then  let n = (rang t l1) in
                                  nth l2 (n-1)  else t;;
                                
let (l1,l2) = ([3;5;7;9], [5;7;9;3]);;

(* Q-4-4*)
let rec tau sigm = function  Feuille x -> Feuille (sigm x)
                             | Noeud(l) -> Noeud ( map (tau sigm) l);;

(*Q-4-5*)

let tau_alea a l = let l1 = permut l in 
let n  = (Random.int(length l1 -1)) in tau (fonct(l, nth l1 n)) a;;



(****************************)
(*  5 ieme Question *)


type vartype = A | B | C | D | E;;   
type et = Int | Bool | Var of vartype | Fonc of et * et | Liste of et;;



    

open List;;

(************Q 5.1}***********************)

let rec pres i = function
 | Fonc(t1,t2)  -> pres i t1 or pres i t2
 | Liste t      -> pres i t
 | Var j        -> i=j
 |  _           -> false;;
 

(**** Q 5.2 *)
let fsoluble s = 
 for_all (function (i,et1) -> (for_all (function (j,et2) -> not (pres i et2)) s ))
 s ;;
 
fsoluble [A,Fonc (Liste Bool, Fonc (Var B,Liste Int));C, Fonc (Var B, 
Fonc (Var D,Var E))];; 

fsoluble [A,Fonc (Liste Bool, Fonc (Var B,Liste Int));C, Fonc (Var B, 
Fonc (Var A,Var E))];;

fsoluble [A,Fonc (Liste Bool, Fonc (Var C,Liste Int));C, Fonc (Var B, 
Fonc (Var D,Var E))];;


(**** Q 5.3  ****)


let oterdef s = filter (function (v,e) -> (exists (function (v1,e1) ->
 pres v e1) s)) s;; 



(**  Q 5.4 ****)
let rec jqastable f x = if x = f x then x else jqastable f (f x);;
let soluble s = [] = jqastable oterdef s;;

oterdef [A,Fonc (Liste Bool, Fonc (Var B,Liste Int));C, Fonc (Var B, Fonc 
(Var A,Var E))];; 
oterdef [A,Var B;B,Var C;C,Var D;D,Var E;E,Var A];;

soluble [A,Var B;B,Var C;C,Var D;D,Var E;E,Var A];;
soluble [A,Fonc (Liste Bool, Fonc (Var B,Liste Int));C, Fonc (Var B, 
Fonc (Var A,Var E))];; 


(** Q 5.5 **)
    
let rec remp i ei = function
   Fonc(t1,t2)    -> Fonc (remp i ei t1,remp i ei t2)
 | Liste t        -> Liste (remp i ei t)
 | Var j when i=j -> ei
 | ej             -> ej ;;


(**  Q5.6 *)
let fait_l_reecr l = map (function (v,e) -> remp v e) l;;


(* Q 5.7 *)
let comp_l_fct l = fold_right (function f -> function rf -> function x ->
 f (rf x)) l (fun x -> x) ;; 
let comp_l_fct2 l = fold_left (function f -> function rf -> f rf ) (fun x -> x) l;; 


comp_l_fct (fait_l_reecr [A,Var B;B,Var C;C,Var D;D,Var E;E,Var A]);;
comp_l_fct (fait_l_reecr [A,Fonc (Liste Bool, Fonc (Var B,Liste Int));C, 
Fonc (Var B, Fonc (Var A,Var E))]);;

(************* FLOTS  *************************)
open Stream;;

let rec filtre p = parser
  [<'x; r = filtre p>] -> if p x then [<'x ; r>] else r
| [<>] -> [<>];;

let rec lesents i = [<'i; lesents(i+1) >];;

let s1 = lesents 0;;

let pair = function x -> ( 0 == x mod 2);;

(*let s3 = filtre pair s1;; *)
(* ca boucle ! *)

let rec filtr p s= match s with parser
  [<'x>] -> if p x then [<'x ; filtr p s>] else filtr p s
| [<>] -> [<>];;

let s2 = filtr pair s1;;
(* ca marche ! *)

(***************FIN  FIN  FIN*********************************)

