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

val main :
  (Lexing.lexbuf  -> token) -> Lexing.lexbuf -> int
