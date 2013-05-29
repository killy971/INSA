/*****************************************************************************/
/*
 Cocket Arnaud
 Bygot Julien
*/

    :- lib(fd).
    :- lib(clpq).

/*
[eclipse 9]: V::4..5, labeling([V]).

V = 4
Yes (0.00s cpu, solution 1, maybe more) ? ;

V = 5
Yes (0.00s cpu, solution 2)
*/

/*

chapie(C,P,X,Y) :- {4*C+2*P=X, C+P=Y}.*/
/*
[eclipse 12]: chapie(2,P,X,5).

P = 3
X = 14
Yes (0.00s cpu)
[eclipse 13]: chapie(C,P,3*Y,Y).

C = C
P = P
Y = Y


% Linear constraints:
{
P = C,
Y = 2 * C
}Yes (0.00s cpu)

*/

chapie(C,P,X,Y) :- C#>=0, P#>=0, 4*C+2*P#=X, C+P#=Y.
/* avec fd
[eclipse 5]: chapie(2,P,X,5).

P = 3
X = 14
Yes (0.00s cpu)

[eclipse 9]: chapie(C,P,3*Y,Y).

C = C{[0..7500000]}
P = P{[0..10000000]}
Y = Y{[0..10000000]}


Delayed goals:
0 + 4 * C{[0..7500000]} + 2 * P{[0..10000000]} - 3 * Y{[0..10000000]}#=0
0 + C{[0..7500000]} + P{[0..10000000]} - Y{[0..10000000]}#=0
Yes (0.00s cpu)

*/

chantier(Fo, Ma, Ch, To, Fe, Ca, Pe, Ja, Sa, Pl, Fa, Fin) :- {Fo>=3, Ma>=Fo+8, Ch>=Ma+3, To>=Ch+2, Fe>=Ma+1, Fe>=To+1, Ca>=3+Ma, Ca>=3+Sa, Ca>=3+Pl, Pe>=4+Fe, Pe>=4+Ca, Ja>=3+Fo, Sa>=2+Pl, Pl>=3+Ma, Fa>=3+Ma, Fa>=3+To, Fa>=3+Fe, Fin>=Fo, Fin>=Ma, Fin>=Ch, Fin>=To, Fin>=Fe, Fin>=Ca, Fin>=Pe, Fin>=Ca, Fin>=Pl, Fin>=Fa}.

/*
[eclipse 14]: chantier(_,_,_,_,_,_,_,_,_,_,_,Fin), minimize(Fin).

Fin = 23
Yes (0.01s cpu)
[eclipse 15]: chantier(Fo, Ma, Ch, To, Fe, Ca, Pe, Ja, Sa, Pl, Fa, Fin), minimize(Fin).

Fo = 3
Ma = 11
Ch = Ch
To = To
Fe = Fe
Ca = 19
Pe = 23
Ja = Ja
Sa = 16
Pl = 14
Fa = Fa
Fin = 23


% Linear constraints:
{
Fe =< 19,
Fa =< 23,
Ch >= 14,
Ja >= 6,
To - Ch >= 2,
Fe - To >= 1,
Fa - Fe >= 3
}Yes (0.01s cpu)

*/

rbst([],0).
rbst([R|Lv], C) :- rbst(Lv, Cr), { C>0, Cr = C+C*0.5-R }.

/*
[eclipse 37]: rbst([R,R,R],10), minimize(R).

R = 135/19
Yes (0.01s cpu)
*/

vabs(X,Y) :- Y#>=0 #=> X#=Y, Y#<0 #=> X#=0-Y.

suite(2,[9,5]).
suite(I,[Xi2,Xi1,Xi|R]) :- I>2, I1 is I-1, vabs(VaXi1,Xi1), Xi2 #= VaXi1-Xi, suite(I1, [Xi1,Xi|R]).

verifperiod([_, _, _, _, _, _, _, _, _]).
verifperiod([A1,A2,A3,A4,A5,A6,A7,A8,A9,A10|R]) :- A1=A10, verifperiod([A2,A3,A4,A5,A6,A7,A8,A9,A10|R]).

/*
[eclipse 61]: suite(50,L), verifperiod(L).

L = [1, -5, 4, 9, 5, -4, -1, 5, 6, 1, -5, 4, 9, 5, -4, -1, 5, 6, 1, ...]
Yes (0.01s cpu, solution 1, maybe more) ? ;

No (0.01s cpu)
*/


/*****************************************************************************/

/******************
 *   Cochai Arno  *
 * Bigotte Julian *
 ******************/

/******* ****      ***
    *    *    *   *    *
    *    *    *      *
    *    *  *      *
    *    *        *
    *    *       *******/

    :- lib(fd).

taches(T) :- T = [](
 tache(3, []     , m1, _),
 tache(8, []     , m1, _),
 tache(8, [4,5]  , m1, _),
 tache(6, []     , m2, _),
 tache(3, [1]    , m2, _),
 tache(4, [1,7]  , m1, _),
 tache(8, [3,5]  , m1, _),
 tache(6, [4]    , m2, _),
 tache(6, [6,7]  , m2, _),
 tache(6, [9,12] , m2, _),
 tache(3, [1]    , m2, _),
 tache(6, [7,8]  , m2, _)
).

dates(Tab, Fin) :-
	dim(Tab, [Taille|_]),
	(for(Ind, 1, Taille), param(Tab, Fin) do
		tache(Duree, _, _, Debut) is Tab[Ind],
		Debut#>=0,
		Duree+Debut#<=Fin
	)
.

aprespred(Tab) :-
	dim(Tab, [Taille|_]),
	(for(Ind, 1, Taille), param(Tab) do
		tache(_, Pred, _, Debut) is Tab[Ind],
		(foreach(P, Pred), param(Debut, Tab) do
			tache(DureeP, _, _, DebutP) is Tab[P],
			Debut#>=DebutP+DureeP
		)
	)
.

pasenmemetemps(Tab) :-
	dim(Tab, [Taille|_]),
	(for(Ind, 1, Taille), param(Tab, Taille) do
		tache(Duree, _, Mach, Debut) is Tab[Ind],
		(for(Ind2, 1, Taille), param(Tab, Ind, Mach, Duree, Debut) do
			tache(Duree2, _, Mach2, Debut2) is Tab[Ind2],
			Mach #\= Mach2 #\/
			Ind #= Ind2 #\/
			Debut+Duree #=< Debut2 #\/
			Debut2+Duree2 #<= Debut
		)
	)
.

faittout(Fin, Lvar) :-
	taches(Tab),
	dates(Tab, Fin),
	aprespred(Tab),
	pasenmemetemps(Tab),
	dim(Tab, [Taille|_]),
	(for(Ind, 1, Taille), param(Tab), fromto([], In, Out, Lvar) do
		tache(_, _, _, Debut) is Tab[Ind],
		Out = [Debut|In]
	),
	min_max(labeling(Lvar),Fin)
.

/*

[eclipse 51]: faittout(Fin,Lvar).
Found a solution with cost 43

Fin = Fin{[43..10000000]}
Lvar = [25, 9, 37, 31, 12, 17, 25, 6, 0, 9, 29, 0]
Yes (0.02s cpu)

*/


/*****************************************************************************/

/* tp3(part1) de Cochet Arnaud et Bigot Julien */

    :- lib(fd).

tech(T) :-  T = [](  5,   7,  2,  6,  9,  3,   7,  5,  3 ).
qte(Q) :-   Q = [](140, 130, 60, 95, 70, 85, 100, 30, 45 ).
benef(B) :- B = [](  4,   5,  8,  5,  6,  4,   7, 10, 11 ).


v(V) :-
	dim(V, [9]),
	(for(Ind, 1, 9), param(V) do
		[V[Ind]] :: 0..1
	)
.

nbtech(V, TechTot) :-
	tech(Tech),
	dim(Tech, [Taille|_]),
	(for(Ind, 1, Taille), param(Tech, V), fromto(0, In, Out, TechTot) do
		Out #= In + Tech[Ind] * V[Ind]
	)
.

benefice(V, BenefTot) :-
	benef(Benef),
	qte(Qte),
	dim(Benef, [Taille|_]),
	(for(Ind, 1, Taille), param(Benef, Qte, V), fromto(0, In, Out, BenefTot) do
		Qi is Qte[Ind],
		Vi is V[Ind],
		Bi is Benef[Ind],
		Blaaargh #= Bi * Vi,
		Out #= In + Blaaargh * Qi
	)
.

contrainte(V, BenefTot) :-
	v(V),
	TechTot#<=22,
	nbtech(V, TechTot),
	benefice(V, BenefTot)
.

enumere(V, Benef) :-
	contrainte(V, Benef),
	(for(Ind, 1, 9), param(V), fromto([], In, Out, LVar) do
		X is V[Ind],
		Out = [X|In]
	),
	Blaaaargh2 #=  0 - Benef,
	min_max(labeling(LVar), Blaaaargh2)
.

/*
[eclipse 18]: enumere(V, Benef).
Found a solution with cost 0
Found a solution with cost -560
Found a solution with cost -650
Found a solution with cost -1210
Found a solution with cost -1690
Found a solution with cost -2165
Found a solution with cost -2390
Found a solution with cost -2525
Found a solution with cost -2575
Found a solution with cost -2665

V = [](0, 1, 1, 0, 0, 1, 1, 0, 1)
Benef = 2665
Yes (0.01s cpu)
*/

contrainte2(V, TechTot, BenefTot) :-
	v(V),
	TechTot#<=22,
	nbtech(V, TechTot),
	benefice(V, BenefTot),
	BenefTot #>= 1000
.

enumere127(V, Benef, Tech) :-
	contrainte2(V, Tech, Benef),
	(for(Ind, 1, 9), param(V), fromto([], In, Out, LVar) do
		X is V[Ind],
		Out = [X|In]
	),
	min_max(labeling(LVar), Tech)
.

/*
[eclipse 20]: enumere127(V, Benef, Tech).
Found a solution with cost 12
Found a solution with cost 7

V = [](1, 0, 1, 0, 0, 0, 0, 0, 0)
Benef = 1040
Tech = 7
Yes (0.00s cpu)
*/

/*****************************************************************************/

/* tp3(part2) de Cochet Arnaud et Bigot Julien */
/* PARTIE 2 : Ze balan�ire */
    :- lib(fd).


poid(P) :- P = [](24, 39, 85, 60, 165, 6, 32, 123, 7, 14).
nom(Dan, Max, Lou, Tom) :-
	Dan = 6,
	Max = 9,
	Lou = 4,
	Tom = 8
.

v(V) :- 
	dim(V, [10]),
	(for(Ind, 1, 10), param(V), fromto([], In, Out, Places) do
		[V[Ind]] :: -8..8,
		V[Ind] #\= 0,
		Out = [V[Ind]|In]
	),
	alldistinct(Places)
.

moment(V, MomentD, MomentG) :-
	poid(Poid),
	(for(Ind, 1, 10), param(V, Poid), fromto(0, InD, OutD, MomentD), fromto(0, InG, OutG, MomentG) do
		( V[Ind]#>0 #/\ OutD #= InD + V[Ind] * Poid[Ind] #/\ OutG #= InG ) #\/
		( V[Ind]#<0 #/\ OutG #= InG - V[Ind] * Poid[Ind] #/\ OutD #= InD )
	)
.

devant(Parent, Fils) :-
	( ( Parent #< 0 ) #/\ ( Fils #= ( Parent + 1 ) ) ) #\/
	( ( Parent #> 0 ) #/\ ( Fils #= ( Parent - 1 ) ) )
.

danetmax(V) :-
	nom(D, M, L, T),
	Dan is V[D],
	Max is V[M],
	Lou is V[L],
	Tom is V[T],
	Dan * Max #< 0,
	/*( devant(Lou, Dan) #/\ devant(Tom, Max) ) #\/*/
	( devant(Tom, Dan) #/\ devant(Lou, Max) )
.

chaque5(V) :-
	(for(Ind, 1, 10), param(V), fromto(0, InG, OutG, Gauche), fromto(0, InD, OutD, Droite)  do
		( V[Ind] #<0 #/\ OutG #= InG + 1 #/\ OutD #= InD ) #\/
		( V[Ind] #>0 #/\ OutG #= InG #/\ OutD #= InD + 1 )
	),
	Droite #= 5,
	Gauche #= 5
.

contrainte(V, MomentD, MomentG) :-
	v(V),
	moment(V, MomentD, MomentG),
	MomentD #= MomentG,
	danetmax(V), 
	chaque5(V)
.

enumere(V, MomentD, MomentG) :-
	contrainte(V, MomentD, MomentG),
	(for(Ind, 1, 10), param(V), fromto([], In, Out, LVar) do
		X is V[Ind],
		Out = [X|In]
	),
	SumMom #= MomentD+MomentG,
	min_max(labeling(LVar), SumMom)
.

/*
avec le 1er ou :

[eclipse 6]: enumere(V, Moment).
Found a solution with cost 1387684
Found a solution with cost 1331716
Found a solution with cost 1276900
Found a solution with cost 1223236
Found a solution with cost 1190281
Found a solution with cost 1138489
Found a solution with cost 1106704
Found a solution with cost 1056784
Found a solution with cost 978121
Found a solution with cost 948676
Found a solution with cost 935089
Found a solution with cost 889249
Found a solution with cost 817216
Found a solution with cost 790321
Found a solution with cost 777924
Found a solution with cost 736164
Found a solution with cost 670761
Found a solution with cost 646416
Found a solution with cost 538756
Found a solution with cost 516961
Found a solution with cost 473344
Found a solution with cost 465124
Found a solution with cost 432964
Found a solution with cost 393129
Found a solution with cost 374544
Found a solution with cost 323761
Found a solution with cost 306916
Found a solution with cost 273529
Found a solution with cost 267289
Found a solution with cost 243049
Found a solution with cost 213444
Found a solution with cost 199809
Found a solution with cost 163216
Found a solution with cost 151321
Found a solution with cost 128164
Found a solution with cost 123904
Found a solution with cost 107584
Found a solution with cost 88209
Found a solution with cost 79524
Found a solution with cost 57121
Found a solution with cost 50176
Found a solution with cost 37249
Found a solution with cost 34969
Found a solution with cost 26569
Found a solution with cost 17424
Found a solution with cost 13689
Found a solution with cost 10000
Found a solution with cost 7225
Found a solution with cost 3721
Found a solution with cost 1849
Found a solution with cost 784
Found a solution with cost 16
Found a solution with cost 4
Found a solution with cost 0

V = [](7, -1, 5, 2, 3, 1, -5, -7, -6, -8)
Moment = 0
Yes (0.11s cpu)

avec le 2eme ou
[eclipse 8]: enumere(V, Moment).
Found a solution with cost 373321
Found a solution with cost 344569
Found a solution with cost 316969
Found a solution with cost 290521
Found a solution with cost 274576
Found a solution with cost 250000
Found a solution with cost 235225
Found a solution with cost 212521
Found a solution with cost 178084
Found a solution with cost 165649
Found a solution with cost 160000
Found a solution with cost 141376
Found a solution with cost 113569
Found a solution with cost 103684
Found a solution with cost 99225
Found a solution with cost 84681
Found a solution with cost 63504
Found a solution with cost 56169
Found a solution with cost 27889
Found a solution with cost 23104
Found a solution with cost 14641
Found a solution with cost 13225
Found a solution with cost 8281
Found a solution with cost 3600
Found a solution with cost 2025
Found a solution with cost 4
Found a solution with cost 1
Found a solution with cost 0

V = [](8, 4, -5, -7, 3, 1, -3, 2, -6, -8)
Moment = 0
Yes (0.09s cpu)

*/

/*****************************************************************************/

/* Tipi fore */
/*
 *	with, in gest star : Cocher Arn� *	and Bygot Julian
 */



    :- lib(clpq).

qte(Q) :- Q =[[70,112],[110,109],[120,103],[150,96],[170,92]].
dem(D) :- D =[[111,25],[107,1200],[102,17],[98,1500],[95,20]].
ben(B) :- B =[[104,98,95,90,88],[97,95,91,89,88],[92,88,87,81,80],[85,82,80,77,75],[72,70,68,68,68]].

matrice(M,N_L,N_C) :- length(M,N_L), 
	(foreach(X,M),param(N_C) do
		length(X,N_C), 
		(foreach(Y,X) do
{Y >= 0}
		)
	)
.

colonne(1,[M1|_],M1).
colonne(I,[_|M],C) :- I > 1, Imoins1 is I-1, colonne(Imoins1, M, C).

ligne(I, M, L) :- (foreach(X,M), fromto([],In,Out,Lrev), param(I) do
		  	colonne(I,X,Y),
			Out = [Y|In]
		  ),
		  reverse(Lrev,L)
.

pssss(V1,V2,V) :- (foreach(X,V1), foreach(Y,V2), fromto(0,In,Out,V) do
{Out = In + X*Y}
		  )
.


sigma(V,S) :- (foreach(X,V), fromto(0,In,Out,S) do
{Out = In + X}
	      )
.
/*les 25 (4400/176) variables*/
the4400on176(T) :- matrice(T,5,5).

conqte(T) :- qte(Q),(foreach(X,T), foreach(Y,Q) do
			[Qtemax|_] = Y,
			sigma(X,Qte1),
{ Qte1 =< Qtemax }
		    )
.

contaux(T) :-
	dem(D),
	qte(Q),
	ligne(2,Q,Q2),
	ligne(1,D,D1),
	ligne(2,D,D2),
	( for(I,1,5), fromto(120,In,Out,_), foreach(Y,D1), foreach(D2I,D2), param(T, Q2) do
		ligne(I,T,Tp),
		pssss(Tp,Q2,Res),
		sigma(Tp,S),
{Res >= Y*S},
{Res =< In*S},
{Out = Y},
{S =< D2I}
	)
.

benef(T, B) :-
	ben(Ben),
	( foreach(TI,T), foreach(BI,Ben), fromto(0,In,Out,B) do
		pssss(TI,BI,S),
{Out=In+S}
	)
.

balanceLaSauce(T,B) :- the4400on176(T), conqte(T), contaux(T), benef(T, B).

/*
[eclipse 24]:  balanceLaSauce(T,B) .

T = [[_137, _181, _217, _253, _289], [_335, _379, _415, _451, _487], [_533, _577, _613, _649, _685], [_731, _775, _811, _847, _883], [_929, _973, _1009, _1045,_1081]]
B = B


% Linear constraints:
{
  B = ... + ... + ... * ... + 82 * _775 + 85 * _731 + 80 * _685 + 81 * _649 + 87 * _613 + 88 * _577 + 92 * _533 + 88 * _487 + 89 * _451 + 91 * _415 + 95 * _379+ 97 * _335 + 88 * _289 + 90 * _253 + 95 * _217 + 98 * _181 + 104 * _137,
  _289 + _253 + _217 + _181 + _137 =< 70,
  _487 + _451 + _415 + _379 + _335 =< 110,
  _685 + _649 + _613 + _577 + _533 =< 120,
  _883 + _847 + _811 + _775 + _731 =< 150,
  _929 + _731 + _533 + _335 + _137 =< 25,
  _1009 + _811 + _613 + _415 + _217 =< 17,
  _1081 + _883 + _685 + _487 + _289 =< 20,
  _1081 + _1045 + _1009 + _973 + _929 =< 170,
  _929 + 15 / 19 * _731 + 8 / 19 * _533 + 2 / 19 * _335 - 1 / 19 * _137 =< 0,
  _973 + 11 / 15 * _775 + 4 / 15 * _577 - 2 / 15 * _379 - 1 / 3 * _181 =< 0,
  _1009 + 3 / 5 * _811 - 1 / 10 * _613 - 7 / 10 * _415 - _217 =< 0,
  _1045 + 1 / 3 * _847 - 5 / 6 * _649 - 11 / 6 * _451 - 7 / 3 * _253 =< 0,
  _1081 - 1 / 3 * _883 - 8 / 3 * _685 - 14 / 3 * _487 - 17 / 3 * _289 =< 0,
  _181 >= 0,
  _217 >= 0,
  _253 >= 0,
  _289 >= 0,
  _335 >= 0,
  _379 >= 0,
  _415 >= 0,
  _451 >= 0,
  _487 >= 0,
  _533 >= 0,
  _577 >= 0,
  _613 >= 0,
  _649 >= 0,
  _685 >= 0,
  _731 >= 0,
  _775 >= 0,
  _811 >= 0,
  _847 >= 0,
  _883 >= 0,
  _929 >= 0,
  _973 >= 0,
  _1009 >= 0,
  _1045 >= 0,
  _1081 >= 0,
  _973 + 15 / 19 * _775 + 8 / 19 * _577 + 2 / 19 * _379 - 1 / 19 * _181 >= 0,
  _1009 + 11 / 15 * _811 + 4 / 15 * _613 - 2 / 15 * _415 - 1 / 3 * _217 >= 0,
  _1045 + 3 / 5 * _847 - 1 / 10 * _649 - 7 / 10 * _451 - _253 >= 0,
  _1081 + 1 / 3 * _883 - 5 / 6 * _685 - 11 / 6 * _487 - 7 / 3 * _289 >= 0
}Yes (0.07s cpu, solution 1, maybe more) ? ;

No (0.96s cpu)
*/

onvagagner_dessous(T,B) :- balanceLaSauce(T,B), maximize(B).

/*
[eclipse 25]: onvagagner_dessous(T,B) .

T = [[200 / 9, 430 / 9, 0, 0, 0], [0, 113 / 9, 0, 14369 / 153, 60 / 17], [25 / 9, 66, 102 / 7, 2309 / 63, 0], [0, 0, 17 / 7, 1033 / 7, 0], [0, 0, 0, 2610 / 17,280 / 17]]
B = 53841748 / 1071
Yes (0.33s cpu, solution 1, maybe more) ? ;

No (0.33s cpu)
*/

/*****************************************************************************/

/*
tp sqrt(25)
Cochet ->			<- Julien
Bigot  ->			<- Arnaud
*/

    :- lib(fd).

xyz(X,Y,Z) :- X::[homme, femme], Y::[homme, femme], Z::[homme, femme].

dit(S,A) :- S #= homme #=> A #= 1.

dit(S,A1,A2) :- S #= femme #=> (A1 #= 1  #<=> A2 #= 0).

affirmations(AffZ, AffZselonX, AffX, Aff1Y, Aff2Y) :- AffZ::[0,1], AffZselonX::[0,1], AffX::[0,1], Aff1Y::[0,1], Aff2Y::[0,1].

affzselonx(AffZselonX,Z) :- AffZselonX isd (Z #= homme).

affx(AffX,AffZselonX,AffZ) :- AffX isd (AffZselonX #= AffZ).

aff1y(Aff1Y,Z) :- Aff1Y isd (Z #= femme).

aff2y(Aff2Y,AffZ) :- Aff2Y isd (AffZ #= 0).

contraintMoiLaGueule(X,Y,Z,AffZ, AffZselonX, AffX, Aff1Y, Aff2Y) :-
	xyz(X,Y,Z),
	X #\= Y,	
	affirmations(AffZ, AffZselonX, AffX, Aff1Y, Aff2Y),
	dit(Z,AffZ),
	dit(X,AffX),
	dit(Y,Aff1Y),
	dit(Y,Aff2Y),
	dit(Y,Aff1Y, Aff2Y),
	LVar = [X,Y,Z],
	labeling(LVar)
.
/*
[eclipse 13]: contraintMoiLaGueule(X,Y,Z,AffZ, AffZselonX, AffX, Aff1Y, Aff2Y).

X = femme
Y = homme
Z = femme
AffZ = AffZ{[0, 1]}
AffZselonX = AffZselonX{[0, 1]}
AffX = AffX{[0, 1]}
Aff1Y = 1
Aff2Y = 1


Delayed goals:
#=(AffZ{[0, 1]}, 1, _386{[0, 1]})
#=(AffX{[0, 1]}, 1, _464{[0, 1]})
Yes (0.00s cpu, solution 1, maybe more) ?
[eclipse 14]:
*/

/*****************************************************************************/

    :-lib(fd).

voiliers(V) :- V = [](10,10,9,8,8,8,8,8,8,7,6,4,4).

equipes(E) :- E=[](7,6,5,5,5,4,4,4,4,4,4,4,4,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2).

resultats(R1,R2) :-
	dim(R1,[7,29]),
	dim(R2,[29,7]),
	(for(I,1,7), param(R1,R2) do
		(for(J,1,29), param(I,R1,R2) do
			X is R1[I,J],
			[X] :: 1 .. 13,
			R1[I,J] #= R2[J,I]
		)
	)
.

sumVect(Vect, Sum) :-
	dim(Vect, [D]),
	(for(I,1,D), param(Vect), fromto(0,In,Out,Sum) do
		Out #= In + Vect[I]
	)
.

capacites(V,E,R1) :-
	dim(R1,[D1,_]),
	(for(I,1,D1), param(V,E,R1) do
		dim(V,[D2]),
		( for(J,1, D2), param(V,E,R1,I) do
			dim(R1,[_,D3]),
			(for(K,1,D3), param(R1,E,I,J), fromto(0,In,Out,Nb) do
				R1[I,K] #= J #=> Out #= In + E[K],
				R1[I,K] #\= J #=> Out #= In
			),
			Nb #=< V[J]
		)
	)
.

voiliersdiff(R2) :- 
	dim(R2,[I,U]),
	(for(J,1,I), param(R2,U) do
		(for(K,1,U), param(R2,J), fromto([],In,Out,Res) do
			Y is R2[J,K],
			Out =[Y|In]
		),
		alldifferent(Res)
	)
.

partenairesparticuliers(R2) :-
	dim(R2,[D1,D2]),
	(for(I,1,D1), param(R2,D1,D2) do
		(for(J,1,D2), param(I,R2,D1), fromto([],In,Out,Part) do
			Nous is R2[I,J],
			(for(K,1,D1), param(R2,Nous,I,J), fromto(In,InBis,OutBis,Out) do
				Eux is R2[K,J],
				K #\= I #/\ Nous #= Eux #=> Z #= K, /*#= ??*/
				K #= I #\/ Nous #\= Eux #=> Z #= 0,
				OutBis = [Z|InBis]
			)
		),
		(foreach(X,Part), fromto([],In,Out,Videe) do
			( X == 0 -> Out = In; Out = [X|In] )
		),
		alldifferent(Videe)
	)
.

contraintes(R1,R2) :- voiliers(V), equipes(E), resultats(R1,R2), capacites(V,E,R1), voiliersdiff(R2), partenairesparticuliers(R2).


labelingperso() :-

enumeration(R1,Liste) :-
	contraintes(R1,_),
	dim(R1,[D1,D2]),
	(for(I,1,D1), param(R1,D2), fromto([],It,O,Liste) do
		(for(J,1,D2), param(I,R1), fromto(It,In,Out,O) do
			X is R1[I,J],
			Out = [X|In]
		)
	),
	labeling(Liste)
.

/*****************************************************************************/



/*****************************************************************************/



/*****************************************************************************/


