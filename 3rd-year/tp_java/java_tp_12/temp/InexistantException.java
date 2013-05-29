public class InexistantException extends Exception {
    public InexistantException() {
		super("Erreur: l'enseignant n'existe pas.");
    }
    public InexistantException(String s) {
		super(s);
    }
}