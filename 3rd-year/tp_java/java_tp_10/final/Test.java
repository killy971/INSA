public class Test {
	
	public static void main(String args[]) {
		BdGeographiqueImpl bdd = new BdGeographiqueImpl();
		
		Coordonnees c1 = new Coordonnees((float)2.33,(float)48.87);
		Enregistrement e1 = new Enregistrement("Paris",c1,100);
		Coordonnees c2 = new Coordonnees((float)-1.68,(float)48.08);
		Enregistrement e2 = new Enregistrement("Rennes",c2,200);
		Coordonnees c3 = new Coordonnees((float)-1.55,(float)47.22);
		Enregistrement e3 = new Enregistrement("Nantes",c3,300);
		Coordonnees c4 = new Coordonnees((float)7.25,(float)43.7);
		Enregistrement e4 = new Enregistrement("Nice",c4,400);
		Coordonnees c5 = new Coordonnees((float)2.33,(float)48.87);
		Enregistrement e5 = new Enregistrement("Paris",c5,100);
		
		try {
			bdd.ajout(e1);
			System.out.println(bdd);
			bdd.ajout(e2);
			System.out.println(bdd);
			bdd.ajout(e3);
			System.out.println(bdd);
			bdd.ajout(e4);
			System.out.println(bdd);
			//bdd.ajout(e5);
			System.out.println(bdd.ville("Nice"));
			//System.out.println(bdd.ville("bouh"));
			System.out.println(bdd.coord(new Coordonnees((float)-1.55,(float)47.22)));
			System.out.println(bdd.population());
			System.out.println(bdd.distanceMax());
			System.out.println(bdd.distanceMin());
			System.out.println(bdd.superficie());
			System.out.println(bdd.densite());
			bdd.retrait("Rennes");
			System.out.println(bdd);


		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}