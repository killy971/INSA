/************** TP 4 - Elodie LEBOUVIER, Guillaume NARGEOT **********************/
	
	
	
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