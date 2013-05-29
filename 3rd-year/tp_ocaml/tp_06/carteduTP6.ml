(*
ocamlc -c carteduTP6.ml
  cree un carteduTP6.cmo
*)

open Graphics;;

type haut = Valet | Dame | Roi | As | Dix | Neuf | Huit | Sept;;
type coul = Trefle | Carreau | Coeur | Pique;;
type t = Cart of haut*coul;;

let b = white;;
let n = black;;

let carr   = [| [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|];
                [|b;b;b;n;n;n;n;n;b;b;b|];
                [|b;b;n;n;n;n;n;n;n;b;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;b;n;n;n;n;n;n;n;b;b|];
                [|b;b;b;n;n;n;n;n;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|] |];;

let tref   = [| [|b;b;b;b;b;n;n;b;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|];
                [|b;b;n;n;b;n;n;b;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;n|];
                [|b;n;n;n;n;n;n;n;n;n;n|];
                [|b;b;n;n;b;n;n;b;n;n;b|];
                [|b;b;b;b;b;n;n;b;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|] |];;

let coeu   = [| [|b;b;n;n;b;b;b;n;n;b;b|];
                [|b;n;n;n;n;b;n;n;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;b;n;n;n;n;n;n;n;b;b|];
                [|b;b;b;n;n;n;n;n;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|] |];;


let piqu   = [| [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|];
                [|b;b;n;n;n;n;n;n;n;b;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;n;n;n;b;n;b;n;n;n;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|] |];;


let random_coul = fun () ->
   match Random.int 4 with
        0 -> Trefle
       |1 -> Carreau
       |2 -> Coeur
       |3 -> Pique;;
(* production aléatoire des hauteurs *)   
let random_haut = fun () ->
   match Random.int 8 with
        0 -> Sept
       |1 -> Huit
       |2 -> Neuf
       |3 -> Dix
       |4 -> Valet
       |5 -> Dame
       |6 -> Roi
       |7 -> As;;
(* production aléatoire des cartes de couleur c *)   
let random_carte = fun () -> Cart(random_haut(),random_coul());;

let haut = fun (Cart(h,c)) -> h;;
let coul = fun (Cart(h,c)) -> c;;

let draw_haut = function
          Sept -> draw_string " 7"
        | Huit -> draw_string " 8"
        | Neuf -> draw_string " 9"
        | Dix -> draw_string "10"
        | Valet -> draw_string " V"
        | Dame -> draw_string " D"
        | Roi -> draw_string " R"
        | As -> draw_string " A";;

let draw_coul c l = function
          Carreau -> draw_image (make_image carr) c (l+2)
        | Trefle -> draw_image (make_image tref) c (l+2)
        | Coeur -> draw_image (make_image coeu) c (l+2)
        | Pique -> draw_image (make_image piqu) c (l+2);;
       
let draw_cart  ca = 
  let (c,l)=current_point() in
  draw_haut (haut ca);draw_coul (c+12) l (coul ca);moveto c (l+14);;
