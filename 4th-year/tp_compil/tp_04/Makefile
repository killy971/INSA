all : lexer.ml parser.ml

lexer.ml : lexer.mll
	ocamllex lexer.mll

parser.ml : parser.mly
	ocamlyacc -v parser.mly # l'option -v génère parser.output qui contient la table SLR

clean :
	rm -f lexer.ml parser.ml parser.mli parser.output