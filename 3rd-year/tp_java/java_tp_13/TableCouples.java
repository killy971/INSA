public class TableCouples {
	private Couple[] table;
	
	public TableCouples() {
		table = new Couple[702]; // 26*26+26 = 702
		// initialisation à null
		for (int i=0 ; i<702 ; i++) {
			table[i] = null;
		}
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public boolean ajouter(Mot m, Mot t) {
		if (table[m.hashCode()] != null) {
			return false;
		} else {
			table[m.hashCode()] = new Couple(m,t);
			return true;
		}
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public Mot traduire(Mot m) {
		return table[m.hashCode()].compCoupleMot(m);
	}
}