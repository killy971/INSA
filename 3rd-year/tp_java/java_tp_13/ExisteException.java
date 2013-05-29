public class ExisteException extends Exception {
    public ExisteException() {
		super("Erreur: Le mot existe déjà dans la table.");
    }
    public ExisteException(String s) {
		super(s);
    }
}