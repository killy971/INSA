public class ExceptionHorsListe extends Exception {
	
	public ExceptionHorsListe() {
		super("erreur vous �tes hors liste");
	}
    
	public ExceptionHorsListe(String s) {
		super(s);
	}
}