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