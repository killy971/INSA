# load "camlp4o.cma";;
# load "pa_extend.cmo";;

open Stream;;

type etat = E1 | E2 | E3 | E4 | E5 | E6 | E7 | E8;;

exception AnalexException of string;;

(* Fonction qui renvoit l'etat suivant selon l'etat actuel et le caractere lu *)

let fct_transition = function
    (E1,' ') | (E1,'\n') | (E1,'\t') | (E1,'\000')            -> E1
  | (E1,'a'..'z') | (E1,'A'..'Z') | (E2,'0'..'9')
  | (E2,'a'..'z') | (E2,'A'..'Z')                             -> E2
  | (E2,_)                                                    -> E3
  | (E1,'(') | (E1,')') | (E1,'=') | (E1,'>')                 -> E8
  | (E1,'/')                                                  -> E4
  | (E5,'*')                                                  -> E6
  | (E6,'/')                                                  -> E1
  | (E4,'*') | (E5,_) | (E6,_)                                -> E5
  | (E1,'<')                                                  -> E7
  | (E7,'>')                                                  -> E8
  | (E7,_)                                                    -> E3
  |  _              -> raise(AnalexException "Pas de transition");;

(* val fct_transition : etat * char -> etat = <fun> *)

type transition = (etat * char -> etat);;
type automate = (etat * transition * etat list);;

let test = Stream.of_string "jojo et /* commentaires */ ) cheval = sinon (";;

type unite_lexicale = UL_IDENT of string | UL_OU | UL_ET | UL_PARFERM |
                      UL_PAROUV | UL_EGAL | UL_DIFF | UL_INF | UL_SUP | UL_EOF;;

(*

Parametres :

* triplet representant l'automate
  - depart   : etat de depart de l'automate
  - fonction : fonction de transition de l'automate
  - arrivee  : liste des etats d'arrivee de l'automate

* flot du programme a compiler
  - flot

*)

let is_ghost = function
    ' ' | '\t' | '\n' | '\000' -> true
  | _ -> false;;

let ghostify c = if (is_ghost c) then "" else (String.make 1 c);;

let get_token (depart,fonction,arrivee) flot =
  let rec get_token_aux (depart_aux,fonction,arrivee) chaine flot =
    let string_n_char s c = s^(ghostify c)
    in match (Stream.peek flot) with
        Some x ->
          let next_state = try (fonction (depart_aux,x))
          with AnalexException "Pas de transition"
              -> raise(AnalexException "Erreur lors de l'analyse lexicale")
          in let is_final_state = List.mem next_state arrivee
          in if (is_final_state & next_state=E3)
            then chaine
            else
              if (is_final_state & next_state=depart)
              then get_token_aux (depart,fonction,arrivee)
                ""
                (Stream.junk flot;flot)
              else
                if (is_final_state & next_state=E8)
                then (Stream.junk flot;string_n_char chaine x)
                else get_token_aux (next_state,fonction,arrivee)
                  (string_n_char chaine x)
                  (Stream.junk flot;flot)
      |   None -> chaine
  in let lexeme = get_token_aux (depart,fonction,arrivee) "" flot
  in let unite_lex = match lexeme with
      "ou" -> UL_OU
    | "et" -> UL_ET
    | ")"  -> UL_PARFERM
    | "("  -> UL_PAROUV
    | "="  -> UL_EGAL
    | "<>" -> UL_DIFF
    | "<"  -> UL_INF
    | ">"  -> UL_SUP
    | ""   -> UL_EOF
    | _    -> UL_IDENT lexeme
  in (unite_lex,lexeme);;

(*
  val get_token : 'a * ('a * char -> 'a) * 'a list -> char Stream.t -> unite_lexicale * string = <fun>
*)

let rec scanner (depart,fonction,arrivee) flot =
  let couple = get_token (depart,fonction,arrivee) flot in
    if (couple = (UL_EOF,""))
    then [(UL_EOF,"")]
    else couple::(scanner (depart,fonction,arrivee) flot);;

let auto1 = (E1,fct_transition,[E1;E3;E8]);;

let test = Stream.of_string "jojo et /* commentaires */ ) cheval = sinon ( <  <>then";;
get_token auto1 test;;
scanner auto1 test;;

(* Tests *)

(* let test01 = Stream.of_string "azeerty123456789<<>()=>/*&~#[|`\^@]}%£$!§/<><()*&~#[|`\^@]}%£$!§/<><()*/";; *)

(* Test passant par tous les etats de l'automate *)
let test01 = Stream.of_string "azeerty123456789<<>()=>";;
scanner auto1 test01;;
(* Test sur un identificateur incorrect *)
let test02 = Stream.of_string "234fds";;
scanner auto1 test02;;
(* Test ne contenant que le caractere EOF *)
let test03 = Stream.of_string (String.make 1 '\000');;
scanner auto1 test03;;

let test04 = Stream.of_string "*";;
scanner auto1 test04;;

let test05 = Stream.of_string "";;
scanner auto1 test05;;
let test06 = Stream.of_string "";;
scanner auto1 test06;;
let test07 = Stream.of_string ;;
scanner auto1 test07;;
let test08 = Stream.of_string "";;
let test09 = Stream.of_string "";;
let test10 = Stream.of_string "";;
