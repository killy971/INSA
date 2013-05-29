%token <string> IDENT
%token BEGIN END
%token VIRG PTVIRG
%token INT BOOL
%token EOF
%token FLECHE AND INF PLUS
%left AND
%left INF
%left PLUS
%token PAROUV PARFERM
%start main
%type <int> main
%%

main:
r_bloc EOF {1}
;
  
  r_bloc:
    BEGIN r_sdecl PTVIRG r_sinst END {};

  r_sdecl:
    r_decl {}
  | r_decl VIRG r_sdecl {};

  r_decl:
    r_type IDENT {};

  r_type:
    INT {}
  | BOOL {};

  r_sinst:
    r_inst {}
  | r_inst PTVIRG r_sinst {};

  r_inst:
    r_bloc {}
  | IDENT FLECHE r_expr {};

  r_expr:
    r_expr PLUS r_expr {}
  | r_expr INF  r_expr {}
  | r_expr AND  r_expr {}
  | PAROUV r_expr PARFERM {}
  | IDENT  {};
