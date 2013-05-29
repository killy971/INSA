public class ExceptionHorsListe extends Exception {
	
	public ExceptionHorsListe() {
		super("erreur vous êtes hors liste");
	}
    
	public ExceptionHorsListe(String s) {
		super(s);
	}
}