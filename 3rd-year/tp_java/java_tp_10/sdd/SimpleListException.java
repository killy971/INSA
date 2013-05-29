package sdd;

public class SimpleListException extends Exception {
    public SimpleListException() {
	super("erreur");
    }
    public SimpleListException(String s) {
	super(s);
    }
}