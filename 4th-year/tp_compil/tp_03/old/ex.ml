(*
TP12
ARNAUD Sylvain
COCHET Arnaud
*)

open Queue;;
open Set;;
open Stack;;

open List;;

(*  PARTIE I  a la charge des eleves                                       *)
(***************************************************************************)


(* question 1 ..... *)


module type SOMMET = 
  sig 
    type  t
  end;;


module Graphe = functor (Som : SOMMET) ->
  struct
    type sommet = Som.t
    type arc = Som.t * Som.t
	  
    type gr = sommet list * arc list
    
    let rec list_succ = function s -> function 
	(n,[] ) -> []
      |	(n,(x,y)::r ) when (x==s) -> [y]@(list_succ s (n,r))
      |	(n,(x,y)::r ) -> (list_succ s (n,r))
	    
    let rec appartientsomm = function s -> function 
	([],_) -> false
      |	(x::r,a) -> (x==s) || (appartientsomm s (r,a))
	  
    let rec appartientarc = function a -> function 
	(_,[]) -> false
      |	(s,x::r) -> (x==a) || (appartientarc a (s,r))
	  

    let ajtsom = function s -> function 
	(sl, al) when (appartientsomm s (sl,al)) -> (sl,al)
      |	(sl, al) -> ([s]@sl,al)

    let ajtarc = function a -> function 
	(sl, al) when (appartientarc a (sl,al)) -> (sl,al)
      |	(sl, al) -> (sl,[a]@al)

     let sommets = function (sl, al) -> sl

     let creer = function () -> ([],[])

    let rec itersom f (sl,al) = iter f sl
	 
    let rec iterarc f (sl,al) = iter f al

  end;;

	
   
   (* question 2 .....  Déjà faite*)


(*  GRAPHE est une signature. Cela va permettre de considerer les graphes *)
(*    comme des types abstraits, puisque on cache l'implementation        *)

module type GRAPHE = functor (Som : SOMMET) -> 
  sig 
    type sommet = Som.t 
    type arc = Som.t * Som.t 
    type gr    (* abstrait ; on ne sait pas que le graphe est le produit  *)
               (*            cartesien de 2 listes                        *)
   
    val list_succ : sommet -> gr -> sommet list
    val ajtsom : sommet -> gr -> gr
    val ajtarc : arc -> gr -> gr
    val sommets : gr -> sommet list
    val creer : unit -> gr
    val itersom : (sommet -> unit) -> gr -> unit
    val iterarc : (arc -> unit) -> gr -> unit
  end;;


   (*question 3 .....*)

module GraphAbstr : GRAPHE = Graphe;;

(* PARTIE II  : Construction d'un graphe                                   *)
(***************************************************************************)


(*   question B ..... *)
module SomEnt = struct 
  type t = int
end;;
module GrapheAbstrEnt =GraphAbstr(SomEnt);;



open GrapheAbstrEnt;;

let g = ajtarc (2,3) (ajtsom 2 (ajtsom 3 (ajtsom 1 (creer ()))));;
itersom print_int g;;

iterarc (function (a,b) -> print_int a; print_char ','; print_int b) g;;

(******************************************************************************)
    
#load "camlp4o.cma";;
#load "pa_extend.cmo";;  
    
type lexemes = CH of int | PG | PD | AG | AD | VG;;
  
let rec blancs = parser [<'' '|'\t'|'\n';() = blancs>] -> ()
  | [<>] -> ();;
	
	
(* les lexemes *)

let chiffre = parser [<''0'..'9' as c>] -> CH (int_of_char c-int_of_char '0');;                       
let parg = parser [<''('>]              -> PG;;
let pard = parser [<'')'>]              -> PD;;
let accg = parser [<''{'>]              -> AG;;
let accd = parser [<''}'>]              -> AD;;
let virg = parser [<'','>]              -> VG;;

let rec flot_lex s=  blancs s; 
    match s with parser  [<ul = chiffre>] -> [<'ul;flot_lex s>]
			|[<ul = parg>] -> [<'ul;flot_lex s>]
			|[<ul = pard>] -> [<'ul;flot_lex s>]
			|[<ul = accg>] -> [<'ul;flot_lex s>]
			|[<ul = accd>] -> [<'ul;flot_lex s>]
			|[<ul = virg>] -> [<'ul;flot_lex s>]
			|[<'x>] -> failwith "erreur lexicale";;


			
let rec slsom g= parser 
     [<'VG; 'CH i; gps = slsom g>] -> GrapheAbstrEnt.ajtsom i gps
   | [<>] -> g;;

let lsom g= parser 
     [<'CH i; gps = slsom g>] -> GrapheAbstrEnt.ajtsom i gps
   | [<>] -> g;;
let som g= parser [<'AG; gps = lsom g; 'AD>] -> gps;;

let rec slarc g= parser 
     [<'VG;'PG;'CH i;'VG;'CH j;'PD;gpa = slarc g>]-> GrapheAbstrEnt.ajtarc (i,j) gpa
   | [<>] -> g;;
let larc g= parser 
     [<'PG;'CH i;'VG;'CH j;'PD;gpa = slarc g>] -> GrapheAbstrEnt.ajtarc (i,j) gpa
   | [<>] -> g;;                                    
let arc g= parser [<'AG; gpa = larc g; 'AD>] -> gpa;;

let graphe = parser [<'PG; g = som (GrapheAbstrEnt.creer ()); 'VG ;gc = arc g; 'PD>] -> gc;;
	
		
   (*question 4 .....*)
let graphe_of_string s = graphe (flot_lex (Stream.of_string s));;


(******************************************************************************)
let g1=graphe_of_string "({1,2,3,4,5,6,7},{(1,2),(1,4),(2,3),(2,6),(3,4),(3,6),
                                    (4,2),(5,4),(6,7),(7,2)})";;

                           


let s = GrapheAbstrEnt.list_succ 2 (graphe_of_string "({1,2,3,4,5,6,7},{(1,2),(1,4),(2,3),(2,6),(3,4),(3,6),
                                    (4,2),(5,4),(6,7),(7,2)})");;
(******************************************************************************)
   
   (*question 5 .....*)

let print_stack =Stack.iter print_int;;

let rec print_queue = function 
    p when (Queue.is_empty(p)) -> ()
  | p -> 
      print_int (take p);
      print_queue p;;

let print_queue =Queue.iter print_int;;


(*  PARTIE III : Utilisation du type abstrait pour coder un algo              *)
(******************************************************************************)



(* parcours en profondeur *)

let a=Stack.create ();;
let pfdab  gr s =	
  let rec ajtfrt dehors =
    let ls = GrapheAbstrEnt.list_succ (let s = Stack.pop a in print_int s;s) gr
    in
    List.iter (fun x -> if List.mem x dehors then Stack.push x a) ls;
    ajtfrt (List.find_all (fun x -> not(List.mem x ls)) dehors)  
  in
  Stack.push s a;
  try ajtfrt (List.find_all ((<>) s) (GrapheAbstrEnt.sommets gr)) with Stack.Empty -> "fin parcours";;

pfdab g1 1;;
(*
142673- : string = "fin parcours"
*)

(* parcours en largeur *)

let b=Queue.create ();;
let lgdab  gr s =	
  let rec ajtfrt dehors =
    let ls = GrapheAbstrEnt.list_succ (let s = Queue.take b in print_int s;s) gr
    in
    List.iter (fun x -> if List.mem x dehors then Queue.push x b) ls;
    ajtfrt (List.find_all (fun x -> not(List.mem x ls)) dehors)  
  in
  Queue.push s b;
  try ajtfrt (List.find_all ((<>) s) (GrapheAbstrEnt.sommets gr)) with Queue.Empty -> "fin parcours";;

lgdab g1 1;;
(*
124367- : string = "fin parcours"
*)
