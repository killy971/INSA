let un_char = ['a'-'z']
let un_int  = ['0'-'9']

rule get_token = parse
    "begin" {BEGIN}
  | "end"   {END}
  | ","     {VIRG}
  | ";"     {PTVIRG}
  | "int"   {INT}
  | "bool"  {BOOL}
  | eof     {EOF}
  | "<-"    {FLECHE} 
  | "and"   {AND}
  | "<"     {INF}
  | "+"     {PLUS}
  | "("     {PAROUV}
  | ")"     {PARFERM}
  | un_char(un_int|un_char)* {IDENT (Lexing.lexeme lexbuf)}
