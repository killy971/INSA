/* tp 2 */
    :-lib(fd).

   /* 
taches :- T = [](tache(3, [],     m1, _),
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
   */
   
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

appartient(E,[E]).
appartient(E,[E|R]).
appartient(E,[A|R]) :- \=(A,E),  appartient(E,R).

contrainte2(T) :- dim(T,[Y]), 
                (for(I,1,Y), param(T, Y) do tache(A,B,_,D) is T[I], 
                        (for (J,1,Y), param(T, A, B, D) do tache(A2,_,_,D2) is T[J], %appartient(J,B), 
                        D #>= D2 + A2)). 