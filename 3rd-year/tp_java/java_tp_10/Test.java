public class Test {
	
	public static void main(String args[]) {
		BdGeographiqueImpl bdd = new BdGeographiqueImpl();
		
		Coordonnees c1 = new Coordonnees(1,1);
		Enregistrement e1 = new Enregistrement("Paris",c1,100);
		Coordonnees c2 = new Coordonnees(2,2);
		Enregistrement e2 = new Enregistrement("Rennes",c2,200);
		Coordonnees c3 = new Coordonnees(3,3);
		Enregistrement e3 = new Enregistrement("Nantes",c3,300);
		Coordonnees c4 = new Coordonnees(4,4);
		Enregistrement e4 = new Enregistrement("Nice",c4,400);
		
		try {
			bdd.ajout(e1);
			bdd.ajout(e2);
			bdd.ajout(e3);
			bdd.ajout(e4);
			System.out.println(bdd);
		}
		catch (PresentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}