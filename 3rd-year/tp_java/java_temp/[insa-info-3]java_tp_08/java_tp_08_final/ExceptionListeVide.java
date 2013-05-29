public class ExceptionListeVide extends Exception {
	
	public ExceptionListeVide() {
		super("erreur la liste est vide");
	}
    
	public ExceptionListeVide(String s) {
		super(s);
	}
}