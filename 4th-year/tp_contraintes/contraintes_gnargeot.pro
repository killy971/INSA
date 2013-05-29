/*
[eclipse 9]: X::(4..6),Y::(4..6),X#<Y, labeling([X,Y]).

X = 4
Y = 5
Yes (0.00s cpu, solution 1, maybe more) ? ;

X = 4
Y = 6
Yes (0.00s cpu, solution 2, maybe more) ? ;

X = 5
Y = 6
Yes (0.00s cpu, solution 3)
[eclipse 11]: X::(4..6),Y::(4..6),X#<Y.

X = X{[4, 5]}
Y = Y{[5, 6]}


Delayed goals:
        Y{[5, 6]} - X{[4, 5]}#>=1
Yes (0.00s cpu)
*/
    :-lib(fd).
chapie(C,P,X,Y) :- X #= 4*C+2*P, Y #= C+P, X#>=0, Y#>=0, C #>=0, P#>=0.

/*
[eclipse 2]: chapie(2, P, X, 5).

P = 3
X = 14
Yes (0.00s cpu)

[eclipse 4]: chapie(C,P,X,Y), X#=3*Y, labeling([X, Y]).

C = 0
P = 0
X = 0
Y = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;

C = 1
P = 1
X = 6
Y = 2
Yes (0.00s cpu, solution 2, maybe more) ? ;

C = 2
P = 2
X = 12
Y = 4
Yes (0.00s cpu, solution 3, maybe more) ?
*/

    :-lib(clpq).

chantier([Fondation,
          Maconnerie,
          Charpente,
          Toit,
          Fenetres,
          Carrelage,
          Peinture,
          Jardin,
          Sanitaire,
          Plomberie,
          Facade,
          FinChantier]) :- Fondation #>= 3,
                      Maconnerie #>= 8 + Fondation,
                      Charpente #>= 3 + Maconnerie,
                      Toit #>= 2 + Charpente,
                      Fenetres #>= 1 + Maconnerie,
                      Fenetres #>= 1 + Toit,
                      Carrelage #>= 3 + Maconnerie,
                      Carrelage #>= 3 + Sanitaire,
                      Carrelage #>= 3 + Plomberie,
                      Peinture #>= 4 + Fenetres,
                      Peinture #>= 4 + Carrelage,
                      Jardin #>= 3 + Fondation,
                      Sanitaire #>= 2 + Plomberie,
                      Plomberie #>= 3 + Maconnerie,
                      Facade #>= 3 + Maconnerie,
                      Facade #>= 3 + Toit,
                      Facade #>= 3 + Fenetres,
                      FinChantier #>= Fondation,
                      FinChantier #>= Maconnerie,
                      FinChantier #>= Charpente,
                      FinChantier #>= Toit,
                      FinChantier #>= Fenetres,
                      FinChantier #>= Carrelage,
                      FinChantier #>= Peinture,
                      FinChantier #>= Jardin,
                      FinChantier #>= Sanitaire,
                      FinChantier #>= Plomberie,
                      FinChantier #>= Facade.

ordonancement([Fondation,
                Maconnerie,
                Charpente,
                Toit,
                Fenetres,
                Carrelage,
                Peinture,
                Jardin,
                Sanitaire,
                Plomberie,
                Facade,
                FinChantier]) :- chantier([Fondation,
                                            Maconnerie,
                                            Charpente,
                                            Toit,
                                            Fenetres,
                                            Carrelage,
                                            Peinture,
                                            Jardin,
                                            Sanitaire,
                                            Plomberie,
                                            Facade,
                                            FinChantier]),
                    min_max(labeling([Fondation,
                                    Maconnerie,
                                    Charpente,
                                    Toit,
                                    Fenetres,
                                    Carrelage,
                                    Peinture,
                                    Jardin,
                                    Sanitaire,
                                    Plomberie,
                                    Facade]),
                            FinChantier).
/*
[eclipse 2]: ordonancement(L).
Found a solution with cost 23

L = [3, 11, 14, 16, 17, 19, 23, 6, 16, 14, 20, FinChantier{[23..10000000]}]
Yes (0.00s cpu)
*/

rbst([R|F], C, T) :- rbst(F, E, T), {E = C + C*T - R}.
rbst([], C, T) :- {C = 0}.

/*
[eclipse 3]: rbst([A,B,C], 1000, 0.05), {A >= 0, B>= 0, C>= 0}.

A = A
B = B
C = C


% Linear constraints:
{
  C = 54139866653285968131275347433240548849156886441754625 / 46768052394588893382517914646921056628989841375232 - 75660473739824333 / 72057594037927936 * B - 5724507286534647490719061698894889 / 5192296858534827628530496329220096 * A,
  B + 75660473739824333 / 72057594037927936 * A =< 715563410816830936339882712361861125 / 649037107316853453566312041152512,
  B >= 0,
  A >= 0
}Yes (0.00s cpu)
*/

vabs(X, X) :- X>=0.
vabs(X, Y) :- X<0, is(Y, -X).

/*
[eclipse 2]: vabs(-5, X).

X = 5
Yes (0.00s cpu)
[eclipse 3]: vabs(4,X).

X = 4
Yes (0.00s cpu, solution 1, maybe more) ? ;

No (0.00s cpu)
*/
        
vabs2(X,Y):- #\/(
#/\( #>=(X,0), #=(X,Y) ),
#/\( #<(X,0),  #=(X,-Y))
                ).
/*
[eclipse 2]: vabs2(0, X).
X = 0
Yes (0.00s cpu)
[eclipse 3]: vabs2(2, X).

X = 2
Yes (0.00s cpu)
[eclipse 4]: vabs2(-6, X).

X = 6
Yes (0.00s cpu)
*/

suite([X2,X1,X0|R]) :- X2 #= A - X0, vabs2(X1,A), suite([X1,X0|R]).
suite([X2,X1,X0])   :- X2 #= A - X0, vabs2(X1,A).
/*
On voit bien que la suite est periode de periode 9

[eclipse 4]: suite([A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,3,2,-1]).

A = -1
B = -1
C = 2
D = 3
E = 1
F = -2
G = 1
H = 3
I = 2
J = -1
K = -1
L = 2
M = 3
N = 1
O = -2
P = 1

*/

/*****************************************************************************/

/* tp 2 */
    :-lib(fd).


taches2(T) :- T = [](tache(3, [],     m1, _),
                 tache(8, [],     m1, _),
                 tache(8, [4,5],  m1, _),
                 tache(6, [],     m2, _),
                 tache(3, [1],    m2, _),
                 tache(4, [1,7],  m1, _),
                 tache(8, [3,5],  m1, _),
                 tache(6, [4],    m2, _),
                 tache(6, [6,7],  m2, _),
                 tache(6, [9,12], m2, _),
                 tache(3, [1],    m2, _),
                 tache(6, [7,8],  m2, _)).
   
taches(T) :- T = [](tache(5, [],     m2, _),
                 tache(8, [1],     m1, _),
                 tache(7, [1],     m1, _),
                 tache(6, [],      m1, _),
                 tache(3, [],      m1, _),
                 tache(6, [3,4],   m2, _),
                 tache(5, [5,6],   m1, _),
                 tache(4, [2,7],   m2, _),
                 tache(7, [3,4],   m2, _),
                 tache(6, [5,6],   m2, _),
                 tache(3, [8,10],  m1, _),
                 tache(4, [9],     m2, _)).          
                
contrainte1(T,Fin) :- dim(T,[Y]), (for(I,1,Y), param(T,Fin) do tache(A,_,_,D) is T[I], D #>= 0, D+A #=< Fin).
/*
[eclipse 3]: taches(T), contrainte1(T, 10).

T = [](tache(5, [], m2, D{[0..5]}), tache(8, [1], m1, D{[0..2]}), tache(7, [1], m1, D{[0..3]}), tache(6, [], m1, D{[0..4]}), tache(3, [], m1, D{[0..7]}), tache(6, [3, 4], m2, D{[0..4]}), tache(5, [5, 6], m1, D{[0..5]}), tache(4, [2, 7], m2, D{[0..6]}), tache(7, [3, 4], m2, D{[0..3]}), tache(6, [5, 6], m2, D{[0..4]}), tache(3, [8, 10], m1, D{[0..7]}), tache(4, [9], m2, D{[0..6]}))
        Yes (0.00s cpu)
*/

/*
appartient(E,[E]).
appartient(E,[E|R]).
appartient(E,[A|R]) :- \=(A,E),  appartient(E,R).


contrainte2(T) :- dim(T,[Y]), 
                (for(I,1,Y), param(T, Y) do tache(A,B,_,D) is T[I], 
                        (for (J,1,Y), param(T, A, B, D) do tache(A2,_,_,D2) is T[J], %appartient(J,B), 
                        D #>= D2 + A2)).

*/

contrainte2(T) :- dim(T,[Y]), 
        (for(I,1,Y), param(T) do tache(_,B,_,D) is T[I], 
         (foreach(P, B), param(T, D) do tache(A2, _, _, D2) is T[P], D #>= D2 + A2)).
                    

/*
[eclipse 7]: taches(T), contrainte1(T, 35), contrainte2(T).
T = [](tache(5, [], m2, D{[0..5]}), tache(8, [1], m1, D{[5..20]}), tache(7, [1],  m1, D{[5..10]}), tache(6, [], m1, D{[0..11]}), tache(3, [], m1, D{[0..20]}), ta che(6, [3, 4], m2, D{[12..17]}), tache(5, [5, 6], m1, D{[18..23]}), tache(4, [2,  7], m2, D{[23..28]}), tache(7, [3, 4], m2, D{[12..24]}), tache(6, [5, 6], m2, D {[18..26]}), tache(3, [8, 10], m1, D{[27..32]}), tache(4, [9], m2, D{[19..31]}))

    There are 15 delayed goals. Do you want to see them? (y/n)

Delayed goals:
    D{[27..32]} - D{[23..28]}#>=4
    D{[23..28]} - D{[5..20]}#>=8
    D{[5..20]} - D{[0..5]}#>=5
    D{[23..28]} - D{[18..23]}#>=5
    D{[18..23]} - D{[0..20]}#>=3
    D{[18..23]} - D{[12..17]}#>=6
    D{[12..17]} - D{[5..10]}#>=7
    D{[5..10]} - D{[0..5]}#>=5
    D{[12..17]} - D{[0..11]}#>=6
    D{[27..32]} - D{[18..26]}#>=6
    D{[18..26]} - D{[0..20]}#>=3
    D{[18..26]} - D{[12..17]}#>=6
    D{[19..31]} - D{[12..24]}#>=7
    D{[12..24]} - D{[5..10]}#>=7
    D{[12..24]} - D{[0..11]}#>=6
    Yes (0.00s cpu)
*/

contrainte3(T) :- dim(T,[Y]),
        (for(I, 1, Y), param(T, Y) do tache(A,_,C,D) is T[I], 
         (for(J, 1, Y), param(T, I, A, C, D) do tache(A2, _, C2, D2) is T[J],
          I #= J #\/ C #\= C2 #\/ A2 + D2 #<= D #\/ A + D #<= D2)).

predicatFinal(Fin, V) :- taches2(T),
                    contrainte1(T, Fin),
                    contrainte2(T),
                    contrainte3(T),
                    dim(T, [Y]),
                    (for(I, 1, Y), param(T), fromto([], In, Out, V) do tache(_, _, _, Deb) is T[I], Out = [Deb|In] ),
                    min_max(labeling(V),Fin).
/*
[eclipse 2]: predicatFinal(Fin, V).
Found a solution with cost 43

Fin = Fin{[43..10000000]}
V = [25, 9, 37, 31, 12, 17, 25, 6, 0, 9, 29, 0]
Yes (0.02s cpu)
*/

/*****************************************************************************/

/*
  TP3
  Elodie Lebouvier
  Guillaume Nargeot
*/   

/******************* 1) Totalement immoral ***********************************/
    
    :- lib(fd).

/***Donn�s du probl�e***/
/*Vecteur de valeurs Techniciens*/
tech(T) :-  T = [](  5,   7,  2,  6,  9,  3,   7,  5,  3 ).
/*Vecteur de valeurs Quantit�*/
qte(Q) :-   Q = [](140, 130, 60, 95, 70, 85, 100, 30, 45 ).
/*Vecteur de valeurs Benefices*/
benef(B) :- B = [](  4,   5,  8,  5,  6,  4,   7, 10, 11 ).

/***Variable du probl�e***/
/*Vecteur de valeurs Sorte (que des 1 et des 0) que l'on cherche �optimiser*/
fabr(S)  :- S = [](_  , _  ,  _,  _,  _,  _,   _,  _,  _ ).
        
/***Pr�icat de service***/
/* Somme tous les elements d'un vecteur */
somme(V,S):- dim(V,[D]), (for(I,1,D), param(V), fromto(0,In,Out,S) do Out#=In+V[I]).

/*Effectue le produit scalaire R de deux vecteurs V1 et V2*/
produitScalaire(V1, V2, R) :- dim(V1, [T]), dim(V2, [T]), dim(V3, [T]),
                              (for(I, 1, T), param(V1, V2, V3) do V3[I] #= (V1[I] * V2[I])), somme(V3, R).

/* Le predicat sorte verifie que le vecteur passe en parametre est bien de type "Sorte" */
sorte(Tab) :-
dim(Tab, [Dim]),
(for(Ind, 1, Dim), param(Tab) do
            Tab[Ind]::(0..1)
).

/* Calcule le vecteur qui fait etat du benefice par type de telephone */
benefParTel(Tab) :- qte(Q), benef(B), dim(Q, [T]), dim(Tab, [T]), (for(I, 1, T), param(Q, B, Tab) do Tab[I] #= (Q[I] * B[I])).
/*
[eclipse 2]: benefParTel(V).
V = [](560, 650, 480, 475, 420, 340, 700, 300, 495)
Yes (0.00s cpu)
*/


/***Contraintes***/            
/* maxTech impose la contrainte stipulant que l'unite ne dispose au plus que de 22 techniciens */
maxTech(S) :- tech(T), produitScalaire(S, T, R), R #<= 22.

/*
[eclipse 2]: maxOuvriers([](1,0,1,0,0,1,1,0,1)).
Yes (0.00s cpu, solution 1, maybe more) ? ;
[eclipse 3]: maxOuvriers([](1,1,1,0,0,1,1,0,1)).
No (0.00s cpu)
*/

/*Pr�icat qui pose les contraintes uniquement : on remarque les contraintes sous forme d'equations illisibles !*/  
contraintes(Tab):- 
                   fabr(Tab),
                   sorte(Tab),
                   maxTech(Tab).
/*
[eclipse 3]: contraintes(T).
T = [](_183{[0, 1]}, _201{[0, 1]}, _219{[0, 1]}, _237{[0, 1]}, _255{[0, 1]}, _273{[0, 1]}, _291{[0, 1]}, _309{[0, 1]}, _327{[0, 1]})
There are 17 delayed goals. Do you want to see them? (y/n)
Delayed goals:
        0 - 5 * _183{[0, 1]} + _415{[0..5]}#=0
        0 - 7 * _201{[0, 1]} + _554{[0..7]}#=0
        0 - 2 * _219{[0, 1]} + _693{[0..2]}#=0
        0 - 6 * _237{[0, 1]} + _832{[0..6]}#=0
        0 - 9 * _255{[0, 1]} + _971{[0..9]}#=0
        0 - 3 * _273{[0, 1]} + _1110{[0..3]}#=0
        0 - 7 * _291{[0, 1]} + _1249{[0..7]}#=0
        0 - 5 * _309{[0, 1]} + _1388{[0..5]}#=0
        0 - 3 * _327{[0, 1]} + _1527{[0..3]}#=0
        0 - _415{[0..5]} - _554{[0..7]} + Out{[0..12]}#=0
        0 - _693{[0..2]} - Out{[0..12]} + Out{[0..14]}#=0
        0 - _832{[0..6]} - Out{[0..14]} + Out{[0..20]}#=0
        0 - _1527{[0..3]} - Out{[0..22]} + R{[0..22]}#=0
        0 - _1388{[0..5]} - Out{[0..22]} + Out{[0..22]}#=0
        0 - _1249{[0..7]} - Out{[0..22]} + Out{[0..22]}#=0
        0 - _1110{[0..3]} - Out{[0..22]} + Out{[0..22]}#=0
        0 - _971{[0..9]} - Out{[0..20]} + Out{[0..22]}#=0
Yes (0.00s cpu)
*/

/*Pr�icat qui pose les contraintes et �um�e les solutions du vecteur variable avec le b��ice associ�/
contraintesEtSolutions(Tab,Benef):- 
                                contraintes(Tab),
                                benefParTel(V),
                                produitScalaire(Tab, V, Benef),
					  =..(Tab,[_|L]),
                                labeling(L).
/*
[eclipse 24]: contraintesEtSolutions(T,B).
T = [](0, 0, 0, 0, 0, 0, 0, 0, 0)
B = 0
Yes (0.00s cpu, solution 1, maybe more) ? ;
T = [](0, 0, 0, 0, 0, 0, 0, 0, 1)
B = 495
Yes (0.00s cpu, solution 2, maybe more) ? ;
T = [](0, 0, 0, 0, 0, 0, 0, 1, 0)
B = 300
Yes (0.00s cpu, solution 3, maybe more) ? ;
T = [](0, 0, 0, 0, 0, 0, 0, 1, 1)
B = 795
Yes (0.00s cpu, solution 4, maybe more) ?
*/

/*Predicat qui pose les contraintes et cherche une solution optimale du vecteur Sorte avec son b��ice*/
contrainteBenefOptimal(Tab,Benef):- 
                            contraintes(Tab),
                            benefParTel(V),
                            produitScalaire(Tab, V, Benef),
                            =..(Tab,[_|L]),
                            MoinsB #= 0 - Benef,
                            min_max(labeling(L),MoinsB).

/*
[eclipse 16]: contrainteBenefOptimal(T,D).
Found a solution with cost 0
Found a solution with cost -495
Found a solution with cost -795
Found a solution with cost -1195
Found a solution with cost -1495
Found a solution with cost -1535
Found a solution with cost -1835
Found a solution with cost -1955
Found a solution with cost -1970
Found a solution with cost -2010
Found a solution with cost -2015
Found a solution with cost -2315
Found a solution with cost -2490
Found a solution with cost -2665

T = [](0, 1, 1, 0, 0, 1, 1, 0, 1)
D = 2665
Yes (0.01s cpu)
*/

/*Pour la nouvelle politique, il suffit d'ajouter la contrainte "Benefice >= 1000" et minimiser non plus le benefice mais le nombre d'ouvriers. Du coup, il nous faut aussi un pr�icat qui donne le nombre d'ouvriers.*/
/* Donne le nombre de techniciens qui travaillent */
totalTech(S,Nb):-tech(N), produitScalaire(S,N,Nb).

/*Predicat qui pose les contraintes et cherche une solution optimale du nombre d'ouvriers.*/
contrainteOuvriersOptimal(Tab,Nb,Benef):- 
                                contraintes(Tab),
                                totalTech(Tab,Nb),
                                benefParTel(V),
                                produitScalaire(Tab, V, Benef),
                                Benef #>= 1000,
                                =..(Tab,[_|L]),
                                min_max(labeling(L),Nb).
/*
[eclipse 33]: contrainteOuvriersOptimal(T,N,B).
Found a solution with cost 10
Found a solution with cost 9
Found a solution with cost 8
Found a solution with cost 7

T = [](1, 0, 1, 0, 0, 0, 0, 0, 0)
N = 7
B = 1040
Yes (0.01s cpu)
*/

/************************ 2) Sur une balancoire ******************************/

/*** les donnees ***/
poids([](24,39,85,60,165,6,32,123,7,14)).
place([](_ , _, _, _,  _,_, _,  _,_, _)).

/***predicats de services***/
sigma(V,S) :- somme(V,S).

/*Rend le vecteur des signes*/
vecteur_de_signe(V,VS) :-
    dim(V,[X]),
    dim(VS,[X]),
    (for(I,1,X),
     param(V,VS)
     do VI is V[I],
        VSI is VS[I],
        VI #> 0 #=> VSI #= 1,
        VI #< 0 #=> VSI #= -1).


/************ les contraintes *****************/

/* Le predicat verifPlaces verifie que le vecteur passe en parametre est bien de type "Place" */
verifPlaces(Tab, Dim) :-
dim(Tab, [Dim]),
(for(Ind, 1, Dim), param(Tab) do
            Tab[Ind]::(-8..8), Tab[Ind]#\=0
).

/* toutes les personnes sont a une place differente */
placeDif(Places):-
    =..(Places,[_|LV]),
    alldistinct(LV).

/*5 personnes a gauche, 5 personnes a droite*/
equidistrib(Places, Dim) :- dim(Places, [Dim]), =..(Places,[_|LV]),
       (foreach(P, LV), fromto(0, In, Out, S) do
        ((P #> 0 #/\ Out #= In+1) #\/ (P #< 0 #/\ Out #= In-1))),  S #= 0. 

/* les parents sont aux extremites*/
pereMereBouts(Places):-
    Lou is Places[4],
    Tom is Places[8],
    dim(Places,[X]),
    (for(I,1,X),
     param(Places,Lou,Tom)
     do VI is Places[I],
        Lou #< 0 #=> (VI #>= Lou #/\ VI #<= Tom),
        Lou #> 0 #=> (VI #<= Lou #/\ VI #>= Tom)).

/* Dan et Max sont devant leur papa ou leur maman */
danMax(Places):-
     Lou is Places[4],
     Tom is Places[8],
     Dan is Places[6],
     Max is Places[9],
     Lou #< 0 #=> ((Dan #= Lou + 1 #/\ Max #= Tom - 1) #\/ (Max #= Lou + 1 #/\ Dan #= Tom - 1)),
     Tom #< 0 #=> ((Dan #= Tom + 1 #/\ Max #= Lou - 1) #\/ (Max #= Tom + 1 #/\ Dan #= Lou - 1)).  
       
/*Predicat qui verifie l'equilibre*/
equilibre(P,V) :- produitScalaire(P,V,E), E #= 0.

/*Rassemble toutes les contraintes*/	
contraintes2(Places):-
        poids(P),
        dim(Places,[10]),
        verifPlaces(Places,10),
        placeDif(Places),
        equilibre(Places,P),
        pereMereBouts(Places),
        danMax(Places),
        equidistrib(Places, 10).

/* calcule le tableau des moments exerces par chaque personne */
vectPoidsPlace(VarPlace,Vect):-
        poids(P),
        dim(P,[D]),
        dim(Vect,[D]),
        (
        for(I,1,D),
        param(P,VarPlace,Vect)
        do Vect[I] #= P[I]*VarPlace[I]
        ).
                
/* calcule le moment total */
moment(VarPlace,M):-
        vectPoidsPlace(VarPlace,V),
        sigma(V,M).

momentDroit(V,M):-
poids(P),
dim(V,[D]),
(
        for(I,1,D),
        param(P,V),
        fromto(0,In,Out,M)
        do
        (V[I] #>0 #/\ Out #= In + P[I]*V[I]) #\/
        (V[I] #<0 #/\ Out #= In)
).
                
/* Predicat qui minimise le moment total VERSION 1*/
version1(P, MG):-
        contraintes2(P),
        moment(P,M),
        M #= 0,
        momentDroit(P,MG),
        =..(P,[_|TabP]),
        min_max(labeling(TabP),MG).

/*
    [eclipse 17]: version1(P,M).
    Found a solution with cost 1208
    Found a solution with cost 1198
    Found a solution with cost 1191
    Found a solution with cost 1113
    Found a solution with cost 1088
    Found a solution with cost 1073
    Found a solution with cost 1059
    Found a solution with cost 953
    Found a solution with cost 948
    Found a solution with cost 898
    Found a solution with cost 834
    Found a solution with cost 802

    P = [](-3, 1, -2, -6, -1, 4, 3, 5, -5, 2)
    M = 802
    Yes (181.98s cpu)
*/
                
% Version 2

labeling2(L) :- 
       (fromto(L,In,Out,[]) 
        do deleteffc(X,In,Out),
        indomain(X)).

version2(P,MG) :-
       contraintes2(P),
        moment(P,M),
        M #= 0,
        momentDroit(P,MG),
        =..(P,[_|TabP]),
       min_max(labeling2(TabP), MG).

/*
[eclipse 2]: version2(P, M).
Found a solution with cost 933
Found a solution with cost 852
Found a solution with cost 834
Found a solution with cost 802

P = [](-3, 1, -2, -6, -1, 4, 3, 5, -5, 2)
M = 802
Yes (29.24s cpu)
*/

% Version 3

insert(A,[],[A]).
insert(A,[B|R],L) :- A>0, B>0, A<B, !, L=[A,B|R].
insert(A,[B|R],L) :- A>0, B<0, C is -B, A<C, !, L=[A,B|R].
insert(A,[B|R],L) :- A<0, B>0, C is -A, C<B, !, L=[A,B|R].
insert(A,[B|R],L) :- A<0, B<0, A>B, !, L=[A,B|R].
insert(A,[B|R],[B|S]) :- insert(A,R,S).

ordo([],[]).
ordo([A|R],L):-ordo(R,R1), insert(A,R1,L).

indom2(X) :- nonvar(X).
indom2(X) :- var(X), dom(X, List), ordo(List,L0), member(X,L0).

labeling3(L) :- 
       (fromto(L,In,Out,[]) do In = [X|Out], indom2(X)).

version3(P,MG) :-   
       contraintes2(P),
       moment(P,M),
       M #= 0,
       momentDroit(P,MG),
       =..(P,[_|TabP]),
       min_max(labeling3(TabP), MG).

/*
[eclipse 2]: version3(P,M).
Found a solution with cost 1153
Found a solution with cost 933
Found a solution with cost 875
Found a solution with cost 852
Found a solution with cost 802

P = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 802
Yes (146.39s cpu)
*/

% Version 4

labeling4(L) :- 
       (fromto(L, In, Out, []) 
        do deleteffc(X, In, Out),
        indom2(X)).

version4(P,MG ) :-   
       contraintes2(P),
       moment(P,M),
       M #= 0,
       momentDroit(P,MG),
       =..(P,[_|TabP]),
       min_max(labeling4(TabP), MG).

/*
[eclipse 3]: version4(P, M).
Found a solution with cost 945
Found a solution with cost 802

P = [](3, -1, 2, 6, 1, -4, -3, -5, 5, -2)
M = 802
Yes (17.94s cpu)
*/

/*****************************************************************************/

/************** TP 4 - Elodie LEBOUVIER, Guillaume NARGEOT *******************/
	
	
	
    :-lib(clpq).


/*Donnees du probleme*/
qteJour(Q) :- Q = [](70,110,120,150,170).
tauxOctanePL(T) :- T = [](112,109,103,96,92).
tauxOctaneMelange(T) :- T = [](111,107,102,98,95).
demandeMaxMelange(D) :- D = [](25,1200,17,1500,20).
benefMelange(B) :- B = ([]([](104,97,92,85,72), [](98,95,88,82,70), [](95,91,87,80,68), [](90,89,81,77,68), [](88,88,80,75,68))).

qtePLParMelange(Q) :- Q = ([]([](_,_,_,_,_), [](_,_,_,_,_), [](_,_,_,_,_), [](_,_,_,_,_), [](_,_,_,_,_))).


/*Contraintes*/

/*somme les valeurs d'un vecteur v*/
sigma(V,S) :- dim(V, [T]), (for(I,1,T), param(V), fromto(0,In,Out,S) do is(C, V[I]), {Out = In + C}).

/*Produit scalaire des vecteurs V1 et V2*/
ps(V1,V2,R) :- dim(V1, [T]), dim(V2, [T]), dim(V3, [T]), 
			(for(I,1,T), param(V1, V2, V3) do is(A, V3[I]), is(B, V1[I]), is(C, V2[I]), {A = (B * C)}),
			sigma(V3,R).

                    
/*Rend le vecteur des valeurs situees en colonne i de la matrice m*/	
colonne(I,M,C) :-  dim(M, [NbLignes,_]),
                   dim(C, [NbLignes]),
                   (for(J,1,NbLignes),
                    param(I,C,M) do is(D, C[J]), is(E, M[J,I]), {D = E}).
		   
					
/*Contraint la variable m a representer une liste de n_l elements, chaque element etant une liste de n_c nombres positifs*/
matrice(M,N_L,N_C) :- dim(M, [N_L,N_C]), 
                      (for(I,1,N_L), param(M, N_C) do 
                        (for(J,1,N_C), param(M, I) do 
                        is(D, M[I,J]), {D >= 0})).
		

/*Calcul du benefice total*/
benefTotal(Tab, Res) :- benefMelange(B), 
		dim(B,[Ligne,Col]),
		dim(Tab,[Ligne,Col]),
		(for(I,1,Ligne), fromto(0,In,Out, Res), param(B,Tab) do
			is(A, B[I]), is(C, Tab[I]),
                        ps(A,C,Som),
{Out = In + Som}).
		
/*
[eclipse 35]: benefTotal([]([](104,97,92,85,72), [](98,95,88,82,70), [](95,91,87,80,68), [](90,89,81,77,68), [](88,88,80,75,68)), R).
R = 180266
*/ 
                        
/*Contrainte taux d'octane des mélanges*/
contrTauxOctane(T) :- tauxOctaneMelange(TT),
                      tauxOctanePL(TL),
                      dim(T, [NbLignes, NbCol]),
                      dim(TT,[NbCol]),
                      dim(TL,[NbCol]), 
                      (for(I,1,NbCol), param(T,TL,TT) do
                       is(E, T[I]),
                       ps(E,TL,S),
                       sigma(E,Res),
                       is(D, TT[I]),  
{S >= D * Res}).
		      
/*Contrainte sur la production de p�role*/
contrProdPetrole(T) :-  dim(T, [_, NbCol]),
                        qteJour(U),
                        dim(U, [NbCol]), 
                        (for(I,1,NbCol), param(T, U) do
                        colonne(I, T, C), sigma(C, Somme),
                        is(D, U[I]),
{Somme =< D}).
                       

/*Contrainte demande de melange > quantite de melange produit*/	                       
contrDemandeMelange(T) :-  demandeMaxMelange(Dem),
                           dim(Dem,[NbCol]),
                           dim(T, [_, NbCol]),
                           (for(I,1,NbCol), param(T, Dem) do
                            is(F, Dem[I]),
                            is(G, T[I]), 
                            sigma(G, Somme),
{F >= Somme}).


/*Resolution du probleme*/                 
resoutLeProbleme(T, Res) :-  matrice(T,5,5),
                             contrTauxOctane(T),
                             contrProdPetrole(T),
                             contrDemandeMelange(T),
                             benefTotal(T, Res),
                             maximize(Res).
                            
/*
[eclipse 2]: resoutLeProbleme(T, Res).

T = []([](200 / 9, 0, 25 / 9, 0, 0),
       [](430 / 9, 113 / 9, 66, 0, 0),
       [](0, 0, 102 / 7, 17 / 7, 0),
       [](0, 14369 / 153, 2309 / 63, 1033 / 7, 2610 / 17),
       [](0, 60 / 17, 0, 0, 280 / 17))
Res = 53841748 / 1071
Yes (0.37s cpu)

*/  

/*****************************************************************************/

    /*
       TP 5
       Elodie Lebouvier
       Guillaume Nargeot
    */   
    
    :-lib(fd).
        dom(X,Y,Z,AffZ,AffZselonX,AffX,Aff1Y,Aff2Y) :-
            X::[homme,femme],
            Y::[homme,femme],
            Z::[homme,femme],
            AffZ::[0,1],
            AffZselonX::[0,1],
            AffX::[0,1],
            Aff1Y::[0,1],
            Aff2Y::[0,1].

        dit(S, A) :- S #= homme #=> A #=1.
        dit(S, A1, A2) :- S #= femme #=> (A1 #=1 #/\ A2 #= 0 #\/ A1 #=0 #/\ A2 #= 1).
        couple(X,Y) :- X #\= Y.
        
        solution(X,Y,Z) :-
            dom(X,Y,Z,AffZ,AffZselonX,AffX,Aff1Y,Aff2Y),
            couple(X,Y),
            dit(Z,AffZ),
            dit(X,AffX),
            dit(Y,Aff1Y),
            dit(Y,Aff2Y),
            dit(Y,Aff1Y,Aff2Y),
            AffZselonX #<=> Z#=homme,
            AffX       #<=> (AffZselonX #= AffZ),
            Aff1Y      #<=> (Z #= femme),
            Aff2Y      #<=> (AffZ #= 0),
            labeling([X,Y,Z]).

/*****************************************************************************/

/* TP6 - Elodie Lebouvier, Guillaume Nargeot */

    :-lib(fd).
	
/*Donnees du probleme*/
capaVoiliers(T) :- T = [](10, 10, 9, 8, 8, 8, 8, 8, 8, 7, 6, 4, 4).
tailleEq(T) :- T = [](7, 6, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2).

/*Variables du probleme*/
varLignes(V1) :- V1 = []([](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),
			[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),
			[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),
			[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),
			[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),
			[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_),[](_,_,_,_,_,_,_)).
		
varColonnes(V2) :- V2 = []([](1,2,3,4,5,6,6,7,8,8,9,10,12,11,11,13,13,10,9,9,7,7,5,4,3,3,2,2,1),
			   [](2,1,4,3,6,5,7,5,9,10,8,9,13,10,12,11,8,11,11,7,8,6,7,1,2,1,4,3,3),
			   [](3,4,1,5,2,7,8,6,7,9,12,8,11,13,10,10,11,9,6,10,9,5,1,6,4,2,3,1,2),
			   [](4,3,2,1,7,2,5,8,10,6,13,11,9,3,7,9,12,8,12,11,10,9,8,5,1,6,1,6,5),
			   [](5,6,8,2,1,1,2,9,4,11,7,13,10,8,3,6,4,12,10,12,3,11,3,3,7,9,9,7,4),
			   [](6,5,7,9,3,4,1,1,11,3,2,12,8,9,13,1,10,2,5,8,4,2,10,2,11,10,8,4,7),
			   [](7,8,9,10,4,3,11,2,5,2,6,1,1,4,9,5,3,3,13,2,1,8,13,12,12,11,6,5,6)
			   ).
		
/*Contraintes*/
/*construire les 2 tables en indiquant qu'elles contiennent les memes variables*/
constrTables(NbConfront,V1,V2) :- tailleEq(T),
				capaVoiliers(TV),
				dim(TV,[Taille]),	
				dim(T,[PG]),
				dim(V1,[PG,NbConfront]), dim(V2,[NbConfront,PG]),
				(for(I,1,NbConfront), param(V1,V2,PG,Taille) do
					(for(J,1,PG), param(I,V1,V2,Taille) do
						V1[J,I]#=V2[I,J],
						V1[J,I]#>=1,
						V1[J,I]#<=Taille)).

	
/*imposer que les capacites d'accueil sont respectees*/	
capaRespect(T) :- varColonnes(T),
			dim(T,[NbLignes,NbCol]),
			capaVoiliers(C),
			dim(C,[NbVoil]),		/*NbVoil =13*/
			tailleEq(TE),
			/*pour chaque confrontation*/	
			(for(I,1,NbLignes), param(T, NbVoil, TE, C, NbCol) do
				/*verifier pour chaque voilier*/
				(for(Voil,1,NbVoil), param(T, NbCol, I, TE, C) do
					(for(J,1,NbCol), param(T, I, TE, Voil), fromto(0,In,Out,S) do
					(T[I,J] #= Voil #<=> Out #= In + TE[J],
					 T[I,J] #\= Voil #<=> Out #= In)), 
					S#<= C[Voil]
				)).


/*imposer que les equipes invitees ne retournent pas sur le meme voilier*/
unSeulVoilier(T) :- varLignes(T),
		    dim(T,[NbLignes]),
		    (for(I,1,NbLignes), param(T) do 
			=..(T[I],[_|L]),
			alldifferent(L)).

	
/*imposer que les equipes invitees ne retrouvent pas la meme equipe partenaire
pasMemeEqu(T) :- 	
*/