/*
  TP3
  Elodie Lebouvier
  Guillaume Nargeot
*/   

/*********************************************** 1) Totalement immoral ***********************************/
    
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

/***************************************************** 2) Sur une balancoire ***********************************/

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