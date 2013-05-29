(* TP1 *)

(* Les transitions *)
type caractere = LEX_CHIFFRE of int| LEX_LETTRE of char | LEX_POUV | LEX_PFERM | LEX_INF | LEX_SUP | LEX_EGAL | LEX_SLASH | LEX_ETOILE;;

(* Les etats *)
type etats = E_INIT | E_DIFFOUPAS | E_IDENT | E_COMMOUPAS | E_COMM | E_FINCOMMOUPAS | E_FINCOMM | E_FIN | E_FINSANSCONSOMMER | E_ERREUR;;


(* Les unites lexicales *)
type unitlex = UL_IDENT of char | UL_ET | UL_OU | UL_INF | UL_SUP | UL_EGAL | UL_DIFF | UL_POUV | UL_PFERM;;


let transition c e =
  match e with 
    E_INIT -> ( match c with
      '='|'>'|'('|')' -> E_FIN
    | '<' -> E_DIFFOUPAS
    | '/' -> E_COMMOUPAS
    | ' '|'\t'|'\n' -> E_FIN
    | 'a'..'z'|'A'..'Z' -> E_IDENT 
    | _ -> E_ERREUR )
  | E_DIFFOUPAS -> ( match c with
      '>' -> E_FIN
    | _ -> E_FINSANSCONSOMMER )
  | E_IDENT -> ( match c with
      'a'..'z'|'A'..'Z'|'0'..'9' -> E_IDENT
    | '='|'>'|'('|')'|'<' -> E_FINSANSCONSOMMER
    | ' '|'\t'|'\n'|'\000' -> E_FIN
    | _ -> E_ERREUR )
  | E_COMMOUPAS -> ( match c with
      '*' -> E_COMM
    | _ -> E_ERREUR )
  | E_COMM -> (match c with
      '*' -> E_FINCOMMOUPAS
    | '\000' -> E_ERREUR
    | _ -> E_COMM )
  | E_FINCOMMOUPAS -> (match c with 
      '/' -> E_FINCOMM
    | '\000' -> E_ERREUR
    | _ -> E_COMM )
  | E_FINCOMM -> E_FINSANSCONSOMMER
  | E_FIN -> E_FIN 
  | E_FINSANSCONSOMMER -> E_FINSANSCONSOMMER
  | E_ERREUR -> E_ERREUR;;

let rec proc etat in_chan lexeme = 

    let carLu = input_char in_chan 
    in
    let nouvelEtat = transition carLu etat
    in

    match nouvelEtat with
      E_FIN -> lexeme ^ (String.make 1 carLu)
    | E_FINSANSCONSOMMER -> 
	seek_in in_chan ((pos_in in_chan) - 1);
	lexeme
    | E_ERREUR -> ""
    | E_FINCOMM -> proc nouvelEtat in_chan ""
    | _ -> proc nouvelEtat in_chan (lexeme ^ (String.make 1 carLu));;

let analex in_chan = proc E_INIT in_chan "";;

(*
let car in_chan = input_char in_chan in
let rec analex = function chan ->
  match car chan with
    '0'..'9' as c -> String.make(1,c).concat(analex str)|
    'a'..'z' as c -> String.make(1,c).concat(analex str);;
  
*)  
  
