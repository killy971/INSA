type token =
  | IDENT of (string)
  | BEGIN
  | END
  | VIRG
  | PTVIRG
  | INT
  | BOOL
  | EOF
  | FLECHE
  | AND
  | INF
  | PLUS
  | PAROUV
  | PARFERM

open Parsing;;
let yytransl_const = [|
  258 (* BEGIN *);
  259 (* END *);
  260 (* VIRG *);
  261 (* PTVIRG *);
  262 (* INT *);
  263 (* BOOL *);
    0 (* EOF *);
  264 (* FLECHE *);
  265 (* AND *);
  266 (* INF *);
  267 (* PLUS *);
  268 (* PAROUV *);
  269 (* PARFERM *);
    0|]

let yytransl_block = [|
  257 (* IDENT *);
    0|]

let yylhs = "\255\255\
\001\000\002\000\003\000\003\000\005\000\006\000\006\000\004\000\
\004\000\007\000\007\000\008\000\008\000\008\000\008\000\008\000\
\000\000"

let yylen = "\002\000\
\002\000\005\000\001\000\003\000\002\000\001\000\001\000\001\000\
\003\000\001\000\003\000\003\000\003\000\003\000\003\000\001\000\
\002\000"

let yydefred = "\000\000\
\000\000\000\000\000\000\017\000\000\000\006\000\007\000\000\000\
\000\000\000\000\001\000\000\000\000\000\005\000\000\000\010\000\
\000\000\000\000\004\000\000\000\002\000\000\000\016\000\000\000\
\000\000\009\000\000\000\000\000\000\000\000\000\015\000\000\000\
\000\000\012\000"

let yydgoto = "\002\000\
\004\000\016\000\008\000\017\000\009\000\010\000\018\000\025\000"

let yysindex = "\003\000\
\005\255\000\000\251\254\000\000\015\000\000\000\000\000\019\255\
\013\255\029\255\000\000\031\255\251\254\000\000\028\255\000\000\
\034\255\033\255\000\000\255\254\000\000\031\255\000\000\255\254\
\017\255\000\000\012\255\255\254\255\254\255\254\000\000\024\255\
\030\255\000\000"

let yyrindex = "\000\000\
\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\035\255\000\000\000\000\000\000\000\000\000\000\000\000\000\000\
\000\000\036\255\000\000\000\000\000\000\000\000\000\000\000\000\
\026\255\000\000\000\000\000\000\000\000\000\000\000\000\003\255\
\000\255\000\000"

let yygindex = "\000\000\
\000\000\041\000\030\000\022\000\000\000\000\000\000\000\246\255"

let yytablesize = 44
let yytable = "\023\000\
\006\000\007\000\013\000\001\000\013\000\014\000\003\000\014\000\
\013\000\013\000\024\000\014\000\013\000\027\000\011\000\014\000\
\013\000\032\000\033\000\034\000\028\000\029\000\030\000\012\000\
\031\000\028\000\029\000\030\000\011\000\014\000\011\000\015\000\
\003\000\029\000\030\000\020\000\021\000\022\000\008\000\003\000\
\030\000\005\000\019\000\026\000"

let yycheck = "\001\001\
\006\001\007\001\003\001\001\000\005\001\003\001\002\001\005\001\
\009\001\010\001\012\001\009\001\013\001\024\000\000\000\013\001\
\004\001\028\000\029\000\030\000\009\001\010\001\011\001\005\001\
\013\001\009\001\010\001\011\001\003\001\001\001\005\001\001\001\
\002\001\010\001\011\001\008\001\003\001\005\001\003\001\005\001\
\011\001\001\000\013\000\022\000"

let yynames_const = "\
  BEGIN\000\
  END\000\
  VIRG\000\
  PTVIRG\000\
  INT\000\
  BOOL\000\
  EOF\000\
  FLECHE\000\
  AND\000\
  INF\000\
  PLUS\000\
  PAROUV\000\
  PARFERM\000\
  "

let yynames_block = "\
  IDENT\000\
  "

let yyact = [|
  (fun _ -> failwith "parser")
; (fun parser_env ->
    let _1 = (peek_val parser_env 1 : 'r_bloc) in
    Obj.repr(
# 16 "parser.mly"
           (1)
# 120 "parser.ml"
               : int))
; (fun parser_env ->
    let _2 = (peek_val parser_env 3 : 'r_sdecl) in
    let _4 = (peek_val parser_env 1 : 'r_sinst) in
    Obj.repr(
# 20 "parser.mly"
                                     ()
# 128 "parser.ml"
               : 'r_bloc))
; (fun parser_env ->
    let _1 = (peek_val parser_env 0 : 'r_decl) in
    Obj.repr(
# 23 "parser.mly"
           ()
# 135 "parser.ml"
               : 'r_sdecl))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : 'r_decl) in
    let _3 = (peek_val parser_env 0 : 'r_sdecl) in
    Obj.repr(
# 24 "parser.mly"
                        ()
# 143 "parser.ml"
               : 'r_sdecl))
; (fun parser_env ->
    let _1 = (peek_val parser_env 1 : 'r_type) in
    let _2 = (peek_val parser_env 0 : string) in
    Obj.repr(
# 27 "parser.mly"
                 ()
# 151 "parser.ml"
               : 'r_decl))
; (fun parser_env ->
    Obj.repr(
# 30 "parser.mly"
        ()
# 157 "parser.ml"
               : 'r_type))
; (fun parser_env ->
    Obj.repr(
# 31 "parser.mly"
         ()
# 163 "parser.ml"
               : 'r_type))
; (fun parser_env ->
    let _1 = (peek_val parser_env 0 : 'r_inst) in
    Obj.repr(
# 34 "parser.mly"
           ()
# 170 "parser.ml"
               : 'r_sinst))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : 'r_inst) in
    let _3 = (peek_val parser_env 0 : 'r_sinst) in
    Obj.repr(
# 35 "parser.mly"
                          ()
# 178 "parser.ml"
               : 'r_sinst))
; (fun parser_env ->
    let _1 = (peek_val parser_env 0 : 'r_bloc) in
    Obj.repr(
# 38 "parser.mly"
           ()
# 185 "parser.ml"
               : 'r_inst))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : string) in
    let _3 = (peek_val parser_env 0 : 'r_expr) in
    Obj.repr(
# 39 "parser.mly"
                        ()
# 193 "parser.ml"
               : 'r_inst))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : 'r_expr) in
    let _3 = (peek_val parser_env 0 : 'r_expr) in
    Obj.repr(
# 42 "parser.mly"
                       ()
# 201 "parser.ml"
               : 'r_expr))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : 'r_expr) in
    let _3 = (peek_val parser_env 0 : 'r_expr) in
    Obj.repr(
# 43 "parser.mly"
                       ()
# 209 "parser.ml"
               : 'r_expr))
; (fun parser_env ->
    let _1 = (peek_val parser_env 2 : 'r_expr) in
    let _3 = (peek_val parser_env 0 : 'r_expr) in
    Obj.repr(
# 44 "parser.mly"
                       ()
# 217 "parser.ml"
               : 'r_expr))
; (fun parser_env ->
    let _2 = (peek_val parser_env 1 : 'r_expr) in
    Obj.repr(
# 45 "parser.mly"
                          ()
# 224 "parser.ml"
               : 'r_expr))
; (fun parser_env ->
    let _1 = (peek_val parser_env 0 : string) in
    Obj.repr(
# 46 "parser.mly"
           ()
# 231 "parser.ml"
               : 'r_expr))
(* Entry main *)
; (fun parser_env -> raise (YYexit (peek_val parser_env 0)))
|]
let yytables =
  { actions=yyact;
    transl_const=yytransl_const;
    transl_block=yytransl_block;
    lhs=yylhs;
    len=yylen;
    defred=yydefred;
    dgoto=yydgoto;
    sindex=yysindex;
    rindex=yyrindex;
    gindex=yygindex;
    tablesize=yytablesize;
    table=yytable;
    check=yycheck;
    error_function=parse_error;
    names_const=yynames_const;
    names_block=yynames_block }
let main (lexfun : Lexing.lexbuf -> token) (lexbuf : Lexing.lexbuf) =
   (yyparse yytables 1 lexfun lexbuf : int)
