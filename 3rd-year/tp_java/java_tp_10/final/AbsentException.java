public class AbsentException extends Exception {
    public AbsentException() {
	super("Erreur: la ville n'est pas pr�sente.");
    }
    public AbsentException(String s) {
	super(s);
    }
}