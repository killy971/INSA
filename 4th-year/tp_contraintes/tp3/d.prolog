/************************************************************ 1) Totalement immoral ***********************************/
    
    :- lib(fd).

tech(T) :-  T = [](  5,   7,  2,  6,  9,  3,   7,  5,  3 ).
qte(Q) :-   Q = [](140, 130, 60, 95, 70, 85, 100, 30, 45 ).
benef(B) :- B = [](  4,   5,  8,  5,  6,  4,   7, 10, 11 ).
fabr(S)  :- S = [](_  , _  ,  _,  _,  _,  _,   _,  _,  _ ).
        
/* Le predicat sorte verifie que les predicat passe en parametre est bien de type "Sorte" */
sorte(Tab) :-
dim(Tab, [Dim]),
(for(Ind, 1, Dim), param(Tab) do
            X is Tab[Ind],
            X #= 0 #\/ X #= 1
).
            
/* Somme tous les éléments d'un vecteur */
somme(V,S):- dim(V,[D]), (for(I,1,D), param(V), fromto(0,In,Out,S) do Out#=In+V[I]).
            
produitScalaire(V1, V2, R) :- dim(V1, [T]), dim(V2, [T]), dim(V3, [T]),
                              (for(I, 1, T), param(V1, V2, V3) do V3[I] #= (V1[I] * V2[I])), somme(V3, R).



/* maxTech impose la contrainte stipulant que l'unite ne dispose au plus que de 22 techniciens */
maxTech(S) :- tech(T), produitScalaire(S, T, R), R #<= 22.

/*
[eclipse 2]: maxOuvriers([](1,0,1,0,0,1,1,0,1)).

Yes (0.00s cpu, solution 1, maybe more) ? ;

Yes (0.00s cpu, solution 2)
[eclipse 3]: maxOuvriers([](1,1,1,0,0,1,1,0,1)).

No (0.00s cpu)
*/

/* Calcule le vecteur qui fait etat du benefice par type de telephone */
benefParTel(Tab) :- qte(Q), benef(B), dim(Q, [T]), dim(Tab, [T]), (for(I, 1, T), param(Q, B, Tab) do Tab[I] #= (Q[I] * B[I])).

/*
[eclipse 2]: benefParTel(V).

V = [](560, 650, 480, 475, 420, 340, 700, 300, 495)
Yes (0.00s cpu)
*/
        
contrainteBenef(Tab,Benef):- 
                                fabr(Tab),
                                sorte(Tab),
                                maxTech(Tab),
                                benefParTel(V),
                                produitScalaire(Tab, V, Benef),
                                B #= 0 - Benef,
                                 =..(Tab,[_|L]),
                                 min_max(labeling(L),B).



/************************************************************ 2) Sur une balançoire ***********************************/

/* les données */
poids([](24,39,85,60,165,6,32,123,7,14)).
place([](_ , _, _, _,  _,_, _,  _,_, _)).



/**********prédicats de services****************/
vectSignes(Tab) :-
dim(Tab, [Dim]),
(for(Ind, 1, Dim), param(Tab) do
            X is Tab[Ind],
            X #= 1 #\/ X #= -1
).

sigma(V,S) :- somme(V,S).

/* calcule le moment exercé par chaque personne */
vectPoidsPlace(VarPlace,Vect):-
        poids(P),
	dim(P,[D]),
	dim(Vect,[D]),
	(
	for(I,1,D),
	param(P,VarPlace,Vect)
	do
		Vect[I]#=P[I]*VarPlace[I]
	).

/* calcule le moment total */
moment(VarPlace,M):-
	vectPoidsPlace(VarPlace,V),
	somVect(V,M).

/************ les contraintes *****************/

/* toutes les personnes sont à une place différente */
placeDif(Places):-
	=..(Places,[_|LV]),
	alldistinct(LV).

/*5 personnes à gauche, 5 personnes à droite*/
equidistrib(Places) :- 
        =..(Places,[_|LV]),
       compterPlus(LV, 5),
       compterMoins(LV, 5).
      
compterPlus([], 0).
compterPlus([A|B], R) :- A>0, compterPlus(B,R2), is(R,R2+1).
compterPlus([A|B], R) :- A<0, compterPlus(B,R).

compterMoins([], 0).
compterMoins([A|B], R) :- A<0, compterMoins(B,R2), is(R,R2+1).
compterMoins([A|B], R) :- A>0, compterMoins(B,R).

/* le père est le plus à gauche et la mère la plus à droite*/
pereGmereD(Places):-
    dim(Places,[D]),
    (
        for(I,1,D)
        do
            Places[I]#<=Places[4],
            Places[I]#>=Places[8]
    ).

/* Dan et Max sont devant leur papa ou leur maman */
danMax(Places):-
            (Places[6]#=Places[4]+1 #\/ Places[6]#=Places[8]-1),
            (Places[9]#=Places[4]+1 #\/ Places[9]#=Places[8]-1).  
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
                                             

	


/* minimise le moment total */
minimMom(M,P):-
	place(P),
	dim(P,[D]),
	(
	for(I,1,D),
	param(P)
	do
		P[I]::(-8..8),
		P[I]#\=0
	),
	placeDif(P),
	/*pereMere(P),*/
	danMax(P),
	/*verif5(P),*/
	moment(P,M),
	=..(P,[_|TabP]),
	min_max(labeling([M|TabP]),M).


