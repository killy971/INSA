public class ExisteException extends Exception {
    public ExisteException() {
		super("Erreur: Le mot existe d�j� dans la table.");
    }
    public ExisteException(String s) {
		super(s);
    }
}