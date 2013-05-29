(* TP1 *)

(* Les transitions *)
type caractere = LEX_CHIFFRE of int| LEX_LETTRE of char | LEX_POUV | LEX_PFERM | LEX_INF | LEX_SUP | LEX_EGAL | LEX_SLASH | LEX_ETOILE;;

(* Les etats *)
type etats = E_INIT | E_DIFFOUPAS | E_IDENT | E_COMMOUPAS | E_COMM | E_FINCOMMOUPAS | E_FINCOMM | E_FIN | E_FINSANSCONSOMMER | E_ERREUR
(*ajout*) | E_SUP;;


(* Les unites lexicales *)
type unitlex = UL_IDENT of String.t | UL_ET | UL_OU | UL_INF | UL_SUP | UL_EGAL | UL_DIFF | UL_POUV | UL_PFERM | UL_EOF
(*ajout*) | UL_SI | UL_ALORS | UL_SINON | UL_FSI | UL_SUPEGAL | UL_INFEGAL;;


let (transition : char -> etats -> etats) = fun c e ->
  match e with
    E_INIT -> ( match c with
      '='|'>'|'('|')' -> E_FIN
    | '<' -> E_DIFFOUPAS
    | '/' -> E_COMMOUPAS
    | ' '|'\t'|'\n' -> E_INIT
    | 'a'..'z'|'A'..'Z' -> E_IDENT
    | '\000' -> E_FIN
(*ajout*)
    | '>' -> E_SUP
(*fin ajout*)
    | _ -> E_ERREUR )
  | E_DIFFOUPAS -> ( match c with
      '>' | '=' -> E_FIN
    | _ -> E_FINSANSCONSOMMER )
  | E_IDENT -> ( match c with
      'a'..'z'|'A'..'Z'|'0'..'9' -> E_IDENT
    | '='|'>'|'('|')'|'<' -> E_FINSANSCONSOMMER
    | ' '|'\t'|'\n'|'\000' -> E_FINSANSCONSOMMER
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
  | E_FINCOMM -> E_INIT
  | E_FIN -> E_FIN
  | E_FINSANSCONSOMMER -> E_FINSANSCONSOMMER
  | E_ERREUR -> E_ERREUR
(*ajout*)
  | E_SUP -> ( match c with
      '=' -> E_FIN
    | _ -> E_FINSANSCONSOMMER )
;;


let (crible : String.t -> unitlex * String.t) = function
    "et" as s -> UL_ET  ,s
  | "ou" as s-> UL_OU   ,s
  | "<"  as s-> UL_INF  ,s
  | ">"  as s-> UL_SUP  ,s
  | "="  as s-> UL_EGAL ,s
  | "<>" as s-> UL_DIFF ,s
  | "("  as s-> UL_POUV ,s
  | ")"  as s-> UL_PFERM,s
  | "\000" as s-> UL_EOF, ""
(* ajout *)
  | "si" -> UL_SI, "si"
  | "alors" -> UL_ALORS, "alors"
  | "sinon" -> UL_SINON, "sinon"
  | "fsi" -> UL_FSI, "fsi"
  | "<=" -> UL_INFEGAL, "<="
  | ">=" -> UL_SUPEGAL, ">="
(*fin des ajouts*)
  | s    -> UL_IDENT s   ,s
;;

let (get_token : in_channel -> (char -> etats -> etats) -> unitlex * String.t)  = fun in_chan automate ->
  let rec proc etat in_chan lexeme =
    let carLu = try (input_char in_chan) with End_of_file -> '\000'
    in
    let nouvelEtat = automate carLu etat
    in
    match nouvelEtat with
      E_FIN -> crible (lexeme ^ (String.make 1 carLu))
    | E_FINSANSCONSOMMER ->
        seek_in in_chan ((pos_in in_chan) - 1);
        crible lexeme
    | E_INIT ->  proc nouvelEtat in_chan ""
    | E_ERREUR -> raise (Failure "Erreur dans l'analyse lexicale")
    | E_FINCOMM -> proc nouvelEtat in_chan ""
    | _ -> proc nouvelEtat in_chan (lexeme ^ (String.make 1 carLu))
  in
  proc E_INIT in_chan "";;

let rec (scanner : in_channel -> (char -> etats -> etats) -> (unitlex * String.t) list) = fun in_chan automate -> 
match (get_token in_chan automate) with
  (UL_EOF , _) as u -> [u]
| u -> u::(scanner in_chan automate);;




let file1 = open_in "test1";;
scanner file1 transition;;


let file2 = open_in "test2";;
scanner file2 transition;;


let file3 = open_in "test3";;
scanner file3 transition;;
