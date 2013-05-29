public class OccupeException extends Exception {
    public OccupeException() {
		super("Erreur: l'enseignement est occupé à cet horaire.");
    }
    public OccupeException(String s) {
		super(s);
    }
}