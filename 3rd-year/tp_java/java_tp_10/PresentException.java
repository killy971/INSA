public class PresentException extends Exception {
    public PresentException() {
	super("Erreur: la ville est déjà présente.");
    }
    public PresentException(String s) {
	super(s);
    }
}