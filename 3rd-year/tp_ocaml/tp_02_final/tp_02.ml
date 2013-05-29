(* TP CAML
Guillaume Nargeot
Francois Dugast
*)

(* Pair et impair *)

let pred = function x -> x-1;;
(*
# pred 45;;
- : int = 44
*)

let rec pair n =
  	if n<0 then pair (-n)
  	else if (n==0) then true
  	else if (n==1) then false
  	else pair (n-2);;
(*
# pair 1034;;
- : bool = true
# pair 1035;;
- : bool = false
*)

let rec impair n =
  	if n<0 then impair (-n)
  	else if (n==0) then false
  	else if (n==1) then true
  	else impair (n-2);;
(*
# impair 1034;;
- : bool = false
# impair 1035;;
- : bool = true
*)

(* Variations sur sigma *)

let rec sigma (a,b) =
  	if (a>b) then raise (Failure "intervalle non conforme")
  	else if (a==b) then a
  	else a + (sigma (a+1,b));;

let rec sigma2 f (a,b) =
  	if (a>b) then raise (Failure "intervalle non conforme")
  	else if (a==b) then f a
  	else (f a) + (sigma2 f (a+1,b));;
(*
# sigma2 (fun x -> x*x) (0,10);;
- : int = 385
*)

let rec sigma3 f (a,i,b) (fc,vi) =
	if a>b then vi
	else fc (f a) (sigma3 f (a+i,i,b) (fc,vi));;

let rec sigma4 f (predarret,fctincrement) (fctcumul,vini) vdea =
	if (predarret vdea) then vini
	else fctcumul (f vdea)
		      (sigma4 f
			      (predarret,fctincrement)
			      (fctcumul,vini)
			      (fctincrement vdea));;

let cum f (a,b,dx) (fctcumul,vini) = sigma4 f ((fun x -> (x>b)),(fun x -> x+.dx)) (fctcumul,vini) a;;

let integre f (a,b,dx) = cum (fun x -> (f x)*.dx) (a,b,dx) ((fun x -> fun y -> x+.y),0.);;

integre (fun x -> 1./.x) (1.,2.,0.0001);;
(*
# integre (fun x -> 1./.x) (1.,2.,0.0001);;
- : float = 0.693222181184964925
# log 2.;;
- : float = 0.693147180559945286
*)

(* Recherche du maximum d'une fonction *)

let rec maxi f (a,b) =
	let x = (2.*.a+.b)/.3. in
	let y = (a+.2.*.b)/.3. in
	if x>=y then (f ((x+.y)/.2.))
	else if (f x)>(f y) then maxi f (a,y) else maxi f (x,b);;

(*
# maxi (fun x -> (0.-.((x-.1.)*.(x-.1.)-.1.))) (0.,2.);;
- : float = 1.
# maxi cos (0.-3.14,3.14);;
- : float = 1.
# maxi sin (0.,2.*.3.14);;
- : float = 1.
# maxi (fun x -> 2.*.(sin x)) (0.,2.*.3.14);;
- : float = 2.
*)
