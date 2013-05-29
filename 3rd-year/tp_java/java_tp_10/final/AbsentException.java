public class AbsentException extends Exception {
    public AbsentException() {
	super("Erreur: la ville n'est pas présente.");
    }
    public AbsentException(String s) {
	super(s);
    }
}