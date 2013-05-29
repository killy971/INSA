public class Couple {
	private Mot mot;
	private Mot traduction;
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public Couple(Mot m, Mot t) {
		mot = m;
		traduction = t;
	}

	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public Couple(String m, String t) {
		mot = new Mot(m);
		traduction = new Mot(t);
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public String toString() {
		return mot.toString()+" : "+traduction.toString();
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public Mot compCoupleMot(Mot m) {
		if (mot.toString().equals(m.toString())) {
			return traduction;
		} else {
			return null;
		}
	}
	
	public static void main(String args[]) {

	}

}