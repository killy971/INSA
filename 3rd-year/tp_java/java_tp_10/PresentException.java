public class PresentException extends Exception {
    public PresentException() {
	super("Erreur: la ville est d�j� pr�sente.");
    }
    public PresentException(String s) {
	super(s);
    }
}