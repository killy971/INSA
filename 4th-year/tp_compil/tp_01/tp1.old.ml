# load "camlp4o.cma";;
# load "pa_extend.cmo";;

open Stream;;

type etat = E1 | E2 | E3 | E4 | E5 | E6 | E7 | E8;;

(* exception Erreur_entree_automate;; *)

(* Fonction qui renvoit l'etat suivant selon l'etat actuel et le caractere lu *)

exception EndOfStream of string;;
exception NoTransitionError of string;;

let fct_transition = function
    (E1,' ')                                                  -> E1
|   (E1,'a'..'z') | (E1,'A'..'Z') | (E2,'0'..'9')
|   (E2,'a'..'z') | (E2,'A'..'Z')                             -> E2
|   (E2,_)                                                    -> E3
|   (E1,'(') | (E1,')') | (E1,'=') | (E1,'>') | (E1,'\000')   -> E8
|   (E1,'/')                                                  -> E4
|   (E5,'*')                                                  -> E6
|   (E6,'/')                                                  -> E8
|   (E4,'*') | (E5,_) | (E6,_)                                -> E5
|   (E1,'<')                                                  -> E7
|   (E7,'>')                                                  -> E8
|   (E7,_)                                                    -> E3
|   _ -> raise(NoTransitionError "pas de transition");;

(* val fct_transition : etat * char -> etat = <fun> *)

type transition = (etat * char -> etat);;
type automate = (etat * transition * etat list);;

let test = Stream.of_string "jojo et /* commentaires */ ) cheval = sinon (";;
let test = Stream.of_string "jojo et ) cheval = sinon ( < > <> ";;

type unite_lexicale = UL_IDENT of string | UL_OU | UL_ET | UL_PARFERM |
                      UL_PAROUV | UL_EGAL | UL_DIFF | UL_INF | UL_SUP | UL_EOF;;

(*
 - depart   : etat de depart de l'automate
 - fonction : fonction de transition de l'automate
 - arrivee  : liste des etats d'arrivee de l'automate
*)

let get_token (depart,fonction,arrivee) flot =
    let rec get_token_aux (depart,fonction,arrivee) chaine flot =
    	let create_the_string chayne car = String.concat "" [chayne;if car=' ' then "" else String.make 1 car]
        in match (Stream.peek flot) with
            Some x -> if ((List.mem (fonction (depart,x)) arrivee) & (fonction (depart,x))=E3)
                      then create_the_string chaine x
		      else
		      if ((List.mem (fonction (depart,x)) arrivee) & (fonction (depart,x))=E8)
                      then (Stream.junk flot;create_the_string chaine x)
                      else get_token_aux (fonction (depart,x),fonction,arrivee)
                                         (create_the_string chaine x)
                                         (Stream.junk flot; flot)
        |    None -> raise(EndOfStream "Fin de flot")
    in let lexeme = get_token_aux (depart,fonction,arrivee) "" flot
    in let unite_lex = match lexeme with
        "ou" -> UL_OU
    |   "et" -> UL_ET
    |   ")"  -> UL_PARFERM
    |   "("  -> UL_PAROUV
    |   "="  -> UL_EGAL
    |   "<>" -> UL_DIFF
    |   "<"  -> UL_INF
    |   ">"  -> UL_SUP
    |   _    -> UL_IDENT lexeme
    in (unite_lex,lexeme);;

(*
val get_token : 'a * ('a * char -> 'a) * 'a list -> char Stream.t -> unite_lexicale * string = <fun>
 *)

get_token (E1,fct_transition,[E8;E3]) test;;


let rec scanner (depart,fonction,arrivee) flot =
    (get_token (depart,fonction,arrivee) flot)
    ::
    (
     try (scanner (depart,fonction,arrivee) flot)
     with (EndOfStream "Fin de flot") -> [(UL_EOF, "")]
    );;