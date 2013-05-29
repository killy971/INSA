(*
ocamlc -c carteduTP6.mli
   cree un carteduTP6.cmi
*)

type haut;;
type coul;;
type t;;

val draw_cart : t -> unit;;
val haut : t -> haut;;
val coul : t -> coul;;
val random_carte : unit -> t;;
