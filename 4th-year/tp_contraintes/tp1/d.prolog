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