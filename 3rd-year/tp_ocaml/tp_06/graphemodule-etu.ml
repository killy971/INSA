open Queue;;
open Set;;
open Stack;;

(*  PARTIE I  a la charge des eleves                                       *)
(***************************************************************************)


   question 1 .....
   
   question 2 .....
   
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

   question 3 .....

(* PARTIE II  : Construction d'un graphe                                   *)
(***************************************************************************)


   question B .....

(******************************************************************************)
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
			
   question 4 .....



(******************************************************************************)
let g1=graphe_of_string "({1,2,3,4,5,6,7},{(1,2),(1,4),(2,3),(2,6),(3,4),(3,6),
                                    (4,2),(5,4),(6,7),(7,2)})";;

                           


let s = GrapheAbstrEnt.list_succ 2 (graphe_of_string "({1,2,3,4,5,6,7},{(1,2),(1,4),(2,3),(2,6),(3,4),(3,6),
                                    (4,2),(5,4),(6,7),(7,2)})");;
(******************************************************************************)
   
   question 5 .....

(*  PARTIE III : Utilisation du type abstrait pour coder un algo              *)
(******************************************************************************)



(* parcours en profondeur *)

let a=Stack.create ();;
let pfdab  gr s =	
  let rec ajtfrt dehors =
    let ls = GrapheAbstrEnt.list_succ (pop a) gr
    in
     List.iter (fun x -> if List.mem x dehors then Stack.push x a;print_stack a) ls;
     ajtfrt (List.find_all (fun x -> not(List.mem x ls)) dehors)  
  in
  Stack.push s a;
  try ajtfrt (List.find_all ((<>) s) (GrapheAbstrEnt.sommets gr)) with Stack.Empty -> "fin parcours";;

(* parcours en largeur *)

   question 6 .....
   
