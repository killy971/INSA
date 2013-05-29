/*Donnees du probleme*/

qteJour([](70,110,120,150,170)).
tauxOctane([](112,109,103,96,92)).
qte([](_,_,_,_,_)).

/*Contraintes*/

tauxOctaneTotal (V, Res) :- dim(V, [5]), produitScalaire(V,tauxOctane,Res).

produitScalaire(V1,V2,R) :- dim(V1, [T]), dim(V2, [T]), dim(V3, [T]), 
			(for(I,1,T), param(V1, V2, V3) do V3[I] #= (V1[I] * V2[I])),
			somme(V3,R).

somme(V,S) :- dim (V, [T]), (for(I,1,T), param(V), fromto(0,In,Out,S) do Out#=In+V[I]).
