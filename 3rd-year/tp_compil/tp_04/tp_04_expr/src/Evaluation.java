import java.util.Stack;

public class Evaluation {
	
	static private int resultat=0;			 // valeur de l'évaluation de l'expr
	static public Stack pileOp=new Stack();  // pile des opérateurs
	static public Stack pileVal=new Stack(); // pile des valeurs
	
	public Evaluation() {}
	
	static public void opAdd()
	{
		int val1 = ((Integer)pileVal.pop()).intValue();
		int val2 = ((Integer)pileVal.pop()).intValue();
		String op = (String)pileOp.pop();
		if (op.equals("+"))
		{
			pileVal.push(new Integer(val1+val2));
		}
		else
		if (op.equals("-"))
		{
			pileVal.push(new Integer(val2-val1));
		}
	}

	static public void opMult()
	{
		int val1 = ((Integer)pileVal.pop()).intValue();
		int val2 = ((Integer)pileVal.pop()).intValue();
		String op = (String)pileOp.pop();
		if (op.equals("*"))
		{
			pileVal.push(new Integer(val1*val2));
		}
		else
		if (op.equals("/"))
		{
			pileVal.push(new Integer(val2/val1));
		}
	}

	static public void affResultat()
	{
		resultat = ((Integer)pileVal.peek()).intValue();
		System.out.println("Evaluation de l'expression : "+resultat);
	}
	
}
