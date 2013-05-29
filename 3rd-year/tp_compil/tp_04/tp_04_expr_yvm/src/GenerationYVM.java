import java.util.Stack;

public class GenerationYVM {
	
	static public Stack pileOp=new Stack();	// pile des opérateurs	
	static public String codeYVM = "";		// le code YVM de sortie
	
	public GenerationYVM() {}
	
	static public void opAdd()
	{
		String op = (String)pileOp.pop();
		if (op.equals("+"))
		{
			// bouh
		}
		else
		if (op.equals("-"))
		{
			// bouh
		}
	}

	static public void opMult()
	{
		String op = (String)pileOp.pop();
		if (op.equals("*"))
		{
			// bouh
		}
		else
		if (op.equals("/"))
		{
			// bouh
		}
	}

	static public void affYVMOutput()
	{
		System.out.println("Code Yaka Virtual Machine : \n\n"+codeYVM);
	}
	
}
