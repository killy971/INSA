public class OccupeException extends Exception {
    public OccupeException() {
		super("Erreur: l'enseignement est occup� � cet horaire.");
    }
    public OccupeException(String s) {
		super(s);
    }
}