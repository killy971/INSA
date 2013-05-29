(*
	TP3 : ANALYSE SYNTAXIQUE DESCENDANT
	Guillaume NARGEOT
	HOANG Hanh
	G_2.1
*)

(****************************************************************************
********        1ère SEANCE : DERIVATION          ***************************
*****************************************************************************)

type ul = UL_IDENT of String.t | UL_ET | UL_OU | UL_INF | UL_SUP | UL_EGAL |
          UL_DIFF | UL_POUV | UL_PFERM | UL_EOF
(*ajout*) | UL_SI | UL_ALORS | UL_SINON | UL_FSI | UL_SUPEGAL | UL_INFEGAL;;

type vn = Expr | SuiteExpr | Termb | SuiteTermb |
          Facteurb | Relation | Op | Ident;;
          (* Ident : bricolage, pas dans le but de modifier la grammaire*)

type v = Vn of vn | Ul of ul;;

exception PasDeDerivation of vn * ul;;
exception PasDeDerivation2 of ul;;

(* val derive : vn * ul -> v list = <fun> *)
let derive = function
    (Expr,unit_lex)        -> ( match unit_lex with 
	  UL_POUV    -> [Vn Termb ; Vn SuiteExpr]
        | UL_IDENT s -> [Vn Termb ; Vn SuiteExpr]
        | UL_SI      -> [Vn Termb ; Vn SuiteExpr])
  | (SuiteExpr,unit_lex)   -> ( match unit_lex with
	  UL_OU -> [Ul UL_OU ; Vn Termb ; Vn SuiteExpr]
      |    _    -> [])
  | (Termb,unit_lex)       -> ( match unit_lex with
	  UL_POUV    -> [Vn Facteurb ; Vn SuiteTermb]
        | UL_IDENT s ->[Vn Facteurb ; Vn SuiteTermb]
        | UL_SI      -> [Vn Facteurb ; Vn SuiteTermb])
  | (SuiteTermb,unit_lex)  -> ( match unit_lex with
          UL_ET -> [Ul UL_ET ; Vn Facteurb ; Vn SuiteTermb]
      |    _    -> [])
  | (Facteurb,unit_lex)    -> ( match unit_lex with
          UL_IDENT s -> [Vn Relation]
      |   UL_POUV    -> [Ul UL_POUV ; Vn Expr ; Ul UL_PFERM]
      |   UL_SI      -> [Ul UL_SI ; Vn Expr ; Ul UL_ALORS ; Vn Expr ;
                         Ul UL_SINON ; Vn Expr ; Ul UL_FSI])
  | (Relation,UL_IDENT s)  -> [Vn Ident ; Vn Op ; Vn Ident]
  | (Op,unit_lex)          -> ( match unit_lex with
          UL_SUP     -> [Ul UL_SUP]
      |   UL_EGAL    -> [Ul UL_EGAL]
      |   UL_DIFF    -> [Ul UL_DIFF]
      |   UL_SUPEGAL -> [Ul UL_SUPEGAL]
      |   UL_INFEGAL -> [Ul UL_INFEGAL]
      |   UL_INF     -> [Ul UL_INF]
      |   ul         -> raise (PasDeDerivation2 ul))
  | (Ident,UL_IDENT s)     -> [Ul (UL_IDENT s)]
  | (v,ul) -> raise (PasDeDerivation (v,ul));;





(****************************************************************************
********        2ème SEANCE : ARBRE CONCRET       ***************************
*****************************************************************************)


type arbre_concret = NoeudUl of ul | NoeudVn of vn * arbre_concret list;;

exception AnMotExcpt of v list * ul list;;

(* (analyse_caractere : v * (ul list) -> arbre_concret * (ul list)) *)
let rec analyse_caractere = function
    (Vn vLu,x::ulList) -> let (e,f) = analyse_mot (derive (vLu,x),x::ulList) in
                          (NoeudVn (vLu,e)),f
  | (Ul vLu,x::ulList)    -> (NoeudUl vLu),ulList
(* (analyse_mot : (v list) * (ul list) -> (arbre_concret list) * (ul list) *)
and analyse_mot = function
    (x::vList,y::ulList) -> let (a,b) = analyse_caractere (x,y::ulList) in
                            let (c,d) = analyse_mot (vList,b) in
                            a::c,d
  | ([],l)               -> [],l;;


(******** TEST de CONSTRUCTION D'arbre concret **************)
let test = [UL_IDENT "t" ; UL_SUP ; UL_IDENT "s" ; UL_ET ; UL_POUV ;
            UL_IDENT "x" ; UL_EGAL ; UL_IDENT "y" ; UL_PFERM ; UL_EOF] ;;
analyse_caractere (Vn Expr,test);;
(*

# - : arbre_concret * ul list =
(NoeudVn (Expr,
  [NoeudVn (Termb,
    [NoeudVn (Facteurb,
      [NoeudVn (Relation,
        [NoeudVn (Ident, [NoeudUl (UL_IDENT "t")]);
         NoeudVn (Op, [NoeudUl UL_SUP]);
         NoeudVn (Ident, [NoeudUl (UL_IDENT "s")])])]);
     NoeudVn (SuiteTermb,
      [NoeudUl UL_ET;
       NoeudVn (Facteurb,
        [NoeudUl UL_POUV;
         NoeudVn (Expr,
          [NoeudVn (Termb,
            [NoeudVn (Facteurb,
              [NoeudVn (Relation,
                [NoeudVn (Ident, [NoeudUl (UL_IDENT "x")]);
                 NoeudVn (Op, [NoeudUl UL_EGAL]);
                 NoeudVn (Ident, [NoeudUl (UL_IDENT "y")])])]);
             NoeudVn (SuiteTermb, [])]);
           NoeudVn (SuiteExpr, [])]);
         NoeudUl UL_PFERM]);
       NoeudVn (SuiteTermb, [])])]);
   NoeudVn (SuiteExpr, [])]),
 [UL_EOF])

*)





(****************************************************************************
********        3ème SEANCE : ARBRE ABSTRAIT      ***************************
*****************************************************************************)


type arbre_abstrait = Feuille of ul | Noeud of ul * arbre_abstrait list;;
exception ConvertExcpt of string ;;


(* val convert_arbre : arbre_concret -> arbre_abstrait = <fun> *)
let rec convert_arbre = function
    NoeudUl ul         -> Feuille ul
  | NoeudVn (vn,list)  ->
      (match vn with
           Expr       ->
	     (match list with
		  [(NoeudVn (Termb,l1))
		  ;(NoeudVn (SuiteExpr,l2))] -> (match l2 with
						     [] -> convert_arbre (NoeudVn (Termb, l1))
						   | (NoeudUl UL_OU)::(NoeudVn (Termb, l3))::(NoeudVn (SuiteExpr, l4))::[] ->
						       Noeud ( UL_OU,
							       [(convert_arbre (NoeudVn (Termb,l1)))
							       ;(convert_arbre (NoeudVn (Expr,[(NoeudVn(Termb,l3))
											      ;(NoeudVn (SuiteExpr,l4)) 
											      ])))
							       ]))
		| _  -> raise (ConvertExcpt "Expression"))
	 | SuiteExpr  -> 
	     (match list with
		  (NoeudUl UL_OU)::(NoeudVn (Termb,l2))::(NoeudVn (SuiteExpr,l3))::[] -> 
		    Noeud (UL_OU,
			   [(convert_arbre (NoeudVn (Termb,l2)))
			   ;(convert_arbre (NoeudVn (SuiteExpr,l3)))	
			   ])
		| _  -> raise (ConvertExcpt "Expression"))
         | Termb      -> 
	     (match list with
		  [(NoeudVn (Facteurb,l1))
		  ;(NoeudVn (SuiteTermb,l2))] -> (match l2 with
						      [] -> convert_arbre (NoeudVn (Facteurb, l1))
						    | [(NoeudUl UL_ET);(NoeudVn (Facteurb,l3));(NoeudVn (SuiteTermb, l4))] ->
							Noeud (UL_ET,
							       [(convert_arbre (NoeudVn (Facteurb,l1)))
							       ;(convert_arbre (NoeudVn (Termb,[(NoeudVn(Facteurb,l3))
											       ;(NoeudVn (SuiteTermb,l4)) 
											       ])))
							       ]))
		| _  -> raise (ConvertExcpt "Termb"))
         | SuiteTermb -> 
	     (match list with
		  (NoeudUl UL_ET)::(NoeudVn (Facteurb,l2))::(NoeudVn (SuiteTermb,l3))::[] -> 
		    Noeud (UL_ET,
			   [(convert_arbre (NoeudVn (Facteurb,l2)))
			   ; (convert_arbre (NoeudVn (SuiteTermb,l3)))	
			   ])
		| _  -> raise (ConvertExcpt "SuiteTermb"))
         | Facteurb   ->
	     (match list with
                  (NoeudVn (Relation,l))::[] -> convert_arbre (NoeudVn (Relation,l))
                | (NoeudUl UL_POUV)::(NoeudVn (Expr,l))::(NoeudUl UL_PFERM)::[] -> convert_arbre (NoeudVn (Expr,l))
                | (NoeudUl UL_SI)::
                    (NoeudVn (Expr,l1))::
                    (NoeudUl UL_ALORS)::
                    (NoeudVn (Expr,l2))::
                    (NoeudUl UL_SINON)::
                    (NoeudVn (Expr,l3))::
                    (NoeudUl UL_FSI)::[] -> (Noeud (UL_SI,
                                                    [convert_arbre (NoeudVn (Expr,l1));
                                                     Noeud (UL_ALORS,
                                                            [convert_arbre (NoeudVn (Expr,l2));
							     Noeud (UL_SINON,
                                                                    [convert_arbre (NoeudVn (Expr,l3));
                                                                     Feuille UL_FSI])])])
                                            )
		| _  -> raise (ConvertExcpt "Facteurb")
             )
         | Relation   -> (match list with 
			      [(NoeudVn (Ident,(NoeudUl a)::[]))
			      ; (NoeudVn (Op,(NoeudUl b)::[]))
			      ; (NoeudVn (Ident,(NoeudUl c)::[]))
			      ] -> (Noeud (b,[Feuille a; Feuille c])))
         | _          -> failwith "erreur"
      );;

(********** TEST DE convert_arbre **********)
convert_arbre (fst (analyse_caractere (Vn Expr,test)));;
(*
- : arbre_abstrait =
Noeud (UL_ET,
 [Noeud (UL_SUP, [Feuille (UL_IDENT "t"); Feuille (UL_IDENT "s")]);
  Noeud (UL_EGAL, [Feuille (UL_IDENT "x"); Feuille (UL_IDENT "y")])])
*)
