type ul = UL_IDENT of String.t | UL_ET | UL_OU | UL_INF | UL_SUP | UL_EGAL | UL_DIFF | UL_POUV | UL_PFERM | UL_EOF
(*ajout*) | UL_SI | UL_ALORS | UL_SINON | UL_FSI | UL_SUPEGAL | UL_INFEGAL;;

type vn = Expr | SuiteExpr | Termb | SuiteTermb | Facteurb | Relation | Op | Ident;; (* Ident : bricolage, pas dans le but de modifier la grammaire*)

type v = Vn of vn | Ul of ul;;

type arbre_concret = NoeudUl of ul | NoeudVn of vn * arbre_concret list;;

let derive = function
    (Expr,unit_lex)        -> ( match unit_lex with 
	  UL_POUV    -> [Vn Termb ; Vn SuiteTermb]
        | UL_IDENT s -> [Vn Termb ; Vn SuiteTermb]
        | UL_SI      -> [Vn Termb ; Vn SuiteTermb])
  | (SuiteExpr,unit_lex)   -> ( match unit_lex with
	  UL_OU -> [Ul UL_OU ; Vn Termb ; Vn SuiteExpr]
      |    _    -> [])
  | (Termb,unit_lex)       -> ( match unit_lex with
	  UL_POUV -> [Vn Facteurb ; Vn SuiteTermb]
        | UL_IDENT s ->[Vn Facteurb ; Vn SuiteTermb]
        | UL_SI -> [Vn Facteurb ; Vn SuiteTermb])
  | (SuiteTermb,unit_lex)  -> ( match unit_lex with
          UL_ET -> [Ul UL_ET ; Vn Facteurb ; Vn SuiteTermb]
      |    _    -> [])
  | (Facteurb,unit_lex)    -> ( match unit_lex with
          UL_IDENT s -> [Vn Relation]
      |   UL_POUV    -> [Ul UL_POUV ; Vn Expr ; Ul UL_PFERM]
      |   UL_SI      -> [Ul UL_SI ; Vn Expr ; Ul UL_ALORS ; Vn Expr ; Ul UL_SINON ; Vn Expr ; Ul UL_FSI])
  | (Relation,UL_IDENT s)  -> [Vn Ident ; Vn Op ; Vn Ident] ;;
