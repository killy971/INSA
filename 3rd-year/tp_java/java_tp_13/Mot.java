public class Mot {
	private String mot;
	
	public Mot(String m) {
		mot = m.toLowerCase();
	}

	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public int charNumber(char c) {
		char[] tab = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
		'o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i=0 ; i<26 ; i++) {
			if(c == tab[i]) {
				return i+1;
			}
		}
		return 0;
	}
	
	public String toString() {
		return mot;
	}
	
	public boolean equals(Object o) {
		return mot.equals(((Mot)o).toString());
	}
	
	/**
	 * 
	 * @param 
	 * @return 
	 */	
	public int hashCode() {
		char c_0 = mot.charAt(0);
		char c_1 = mot.charAt(1);
		return (charNumber(c_0)*26)+charNumber(c_1);
	}
}