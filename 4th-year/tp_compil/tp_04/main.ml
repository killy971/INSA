#use "parser.ml";;
#use "lexer.ml";;

main get_token (Lexing.from_channel (open_in "test"));;
