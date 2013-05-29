(* Definition des donnees *)

let m1=((0.0,0.0),(0.172,0.496))
let m2=((0.076,0.3122),(0.257,0.204))
let m3=((0.821,(-0.028)),(0.030,0.845))
let m4=((0.024,(-0.356)),(0.323,0.074))

let v1=(1.496,(-0.091))
let v2=(0.494,0.133)
let v3=(0.088,0.176)
let v4=(0.470,0.260);;

(* La construction des transformations affines *)


let mt mat vec = match (mat,vec) with
  	(((a,b),(c,d)),(e,f)) -> (a*.e+.b*.f,c*.e+.d*.f);;

let sv v1 v2 = match (v1,v2) with
	((a,b),(c,d)) -> (a+.c,b+.d);;

let genapplin = function mat -> function x -> mt mat x;;

let gentraffine = function applin -> function vec -> function x -> sv (applin x) vec;;

let applin1 = genapplin m1
let applin2 = genapplin m2
let applin3 = genapplin m3
let applin4 = genapplin m4;;

let les4tr = (gentraffine applin1 v1,gentraffine applin2 v2,gentraffine applin3 v3,gentraffine applin4 v4);;

(* Le choix d'une transformation parmi les 4 *)

let elemrang (a,b,c,d) = match (Random.int 4) with
  	0 -> a
  |	1 -> b
  |	2 -> c
  |	3 | _ -> d;;

let traff = function () -> elemrang les4tr;;

(* La suite des points transformes *)

(* voir en bas *)

(* L'affichage de la suite des points *)

# load "graphics.cma";;

Graphics.set_color Graphics.magenta;;

let aff = function (x,y) -> Graphics.plot (int_of_float(x*.500.)) (int_of_float(y*.500.)); (x,y);;

let rec suite = function
		0 -> (0.5,0.)
	|	n -> aff (traff () (suite (n-1)));;

let tout n=
	Graphics.open_graph "";
	suite n;
	read_int();
	Graphics.close_graph();;

tout 65000;;
