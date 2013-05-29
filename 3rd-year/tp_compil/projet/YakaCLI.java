import java.io.*;
import java.util.ArrayList;

/**
 * Classe pour l'execution de la compilation Yaka
 * en mode ligne de commande
 */
public class YakaCLI
{
	/**
	 * Constantes de localisation des types (indices)
	 */
	static final int IN   = 0; // type d'entrée
	static final int OUT  = 1; // type de sortie

	/**
	 * Constantes de localisation des options
	 */
	static final int OPT_COUNT = 2; // nombre d'options possibles
	static final int OV        = 0; // écraser le(s) fichier(s) de sortie (OVerwrite)
	static final int VERB      = 1; // affichage des messages d'erreurs (VERBose)

	/**
	 * Constantes représentants les différentes types
	 * d'entrée et de sortie
	 */
	static final int YAKA = 1; // code yaka
	static final int YVM  = 2; // code Yaka Virtual Machine
	static final int ASM  = 3; // code assembleur (pentium)
	static final int EXE  = 4; // executable (pentium)

	/**
	 * Attributs de l'interface ligne de commande
	 */
	private ArrayList _fileList; // liste des fichiers à traiter
	private boolean[] _options;  // tableau de status des options
	private int[]     _types;    // tableau des types
	private boolean   _multi;    // mode compilations multiples
	
	/*----------------------------------------*/
	
	/**
	 * Constructeur
	 */
	public YakaCLI()
	{
		this._fileList = new ArrayList();

		this._options  = new boolean[OPT_COUNT];
		for (int i=0 ; i<this._options.length ; i++)
		{
			this._options[i] = false;
		}
		
		this._types = new int[2];
		for (int i=0 ; i<this._types.length ; i++)
		{
			this._types[i] = 0;
		}
		
		this._multi = true;
	}
	
	/*----------------------------------------*/
	
	/**
	 * Retourne sur la sortie standard un message d'aide à l'utilisation
	 */
    static void helpMessage()
	{
		System.out.println("Utilisation :");
		System.out.println(" - Pour compiler un unique fichier");
		System.out.println("    java YakaCLI [-i] <fichier_entree> [-o fichier_sortie] [options]");
		System.out.println("    ");
		System.out.println("    Si on garde l'ordre des parametres fichiers, l'option -i");
		System.out.println("    peut etre omis. Sinon l'ordre n'est pas important.");
		System.out.println("    Lorsque l'on specifie un fichier de sortie, l'option -o est obligatoire.");
		System.out.println("    ");
		System.out.println("    \"options\" : sequence d'options, toutes separees par un espace");
		System.out.println("    Valeurs possibles :");
		System.out.println("      -it <type> : pour indiquer le type d'entree (yaka|yvm)");
		System.out.println("      -ot <type> [type] : pour indiquer le type de sortie (yvm|asm)");
		System.out.println("      -v : permet l'affichage des messages d'erreur a la compilation");
		System.out.println("      -ov : ecrase le fichier de sortie s'il existe deja");
		System.out.println("    ");
		System.out.println("    Par defaut, si les types d'entree et de sortie ne sont pas specifies,");
		System.out.println("    le comportement sera le suivant:");
		System.out.println("      - le type d'entree est determine a l'aide de l'extension du fichier");
		System.out.println("      - pour un fichier d'entree yaka, le type de sortie sera yvm");
		System.out.println("      - pour un fichier d'entree yvm, le type de sortie sera asm");
		System.out.println("    ");
		System.out.println(" - Pour compiler plusieurs fichiers");
		System.out.println("    java YakaCLI [options] <fichier> [fichiers]");
		System.out.println("    ");
		System.out.println("    \"options\" : sequence d'options, toutes separees par un espace");
		System.out.println("    Valeurs possibles :");
		System.out.println("      -v : permet l'affichage des messages d'erreur a la compilation");
		System.out.println("      -ov : ecrase le ou les fichiers de sortie s'il(s) existe(nt) deja");
		System.out.println("    ");
		System.out.println("    Les types d'entree sont determines a l'aide de l'extension");
		System.out.println("    de chacun des fichiers individuellement.");
		System.out.println("    Si le type de sortie n'a pas ete defini a l'aide de l'option -ot,");
		System.out.println("    les types de sortie sont deduits pour chaque fichiers");
		System.out.println("    individuellement ainsi :");
		System.out.println("      - pour un fichier d'entree yaka, le type de sortie sera yvm");
		System.out.println("      - pour un fichier d'entree yvm, le type de sortie sera asm");
		System.out.println("    ");
		System.out.println("Exemples :");
		System.out.println("    ");
		System.out.println("    java YakaCLI -i morpion.yaka -o morpion.asm -v");
		System.out.println("    ");
		System.out.println("    java YakaCLI fact.yaka -v -ov");
		System.out.println("    ");
		System.out.println("    java YakaCLI puiss.yaka -v -ot asm");
		System.out.println("    ");
		System.out.println("    java YakaCLI -v *.yaka");
    }
	
	/*----------------------------------------*/
	
	/**
	 * Teste si la chaine donnée est une option
	 *
	 * @param opt  chaine à tester
	 */
    static boolean isOption(String opt)
    {
        return opt.length()>1 && opt.charAt(0)=='-';
    }
	
	/**
	 * Teste si la chaine donnée est une option
	 * necessitant un argument
	 *
	 * @param opt  chaine à tester
	 */
	static boolean isUnaryOption(String opt)
	{
		return (isOption(opt)&&
		       (opt.equals("-v")||
		        opt.equals("-ov"))
		                        );
	}
	
	/**
	 * Teste si la chaine donnée est une option
	 * ne necessitant pas d'argument
	 *
	 * @param opt  chaine à tester
	 */
	static boolean isBinaryOption(String opt)
	{
		return (isOption(opt)&&
		       (opt.equals("-i") ||
		        opt.equals("-o") ||
		        opt.equals("-it")||
		        opt.equals("-ot")));
	}
	
	/*----------------------------------------*/
	
	/**
	 * Traite la ligne de commande et crée le tableau de config
	 * 
	 * @param cli  la ligne de commande
	 * @return  tableau de configuration
	 */
	public void parseCommandLine(String[] cl) throws CommandLineException
	{
		for (int i=0 ; i<cl.length ; i++)
		{
			if (isUnaryOption(cl[i]))
			{
				setOption(cl[i]);
			}
			else
			if (isBinaryOption(cl[i]))
			{
				if (i+1==cl.length)
				{
					throw new CommandLineException("Erreur : \""+cl[i]+"\" doit etre suivi d'un argument");
				}
				setArg(cl[i],cl[i+1]);
				i++;
			}
			else
			{
				this._fileList.add(cl[i]);
			}
		}
		
		/*
		 * Post processing
		 */
		
		// set single file mode
		if (this._fileList.size()==1)
		{
			this._multi = false;
		}
		
		// set types
		if (!this._multi)
		{
			// crée le fichier de sortie s'il n'existe pas
			if (this._fileList.size()==1)
			{
				if (this._types[OUT]==0)
				{
					this._types[OUT] = deductOutType(nameToType((String)this._fileList.get(IN)));
				}
				this._fileList.add(createFileName((String)(this._fileList.get(IN)),this._types[OUT]));
			}
			
			// s'il n'est pas définie, déduire le type du fichier d'entrée à partir de son nom
			if (this._types[IN]==0)
			{
				this._types[IN] = nameToType((String)this._fileList.get(IN));
			}
			// s'il n'est pas définie, déduire le type du fichier de sortie à partir de son nom
			if (this._types[OUT]==0)
			{
				this._types[OUT] = nameToType((String)this._fileList.get(OUT));
			}
		}
	}
	
	/*----------------------------------------*/
	
	/**
	 * Configure en fonction de l'option donnée
	 * 
	 * @param opt  l'option
	 */
	public void setOption(String opt)
	{
		if (opt.equals("-v"))
		{
			this._options[VERB] = true;
		}
		else
		if (opt.equals("-ov"))
		{
			this._options[OV] = true;
		}
	}
	
	/**
	 * Configure le type d'entrée/sortie
	 * 
	 * @param type    type (string)
	 * @param stream  IN|OUT
	 */
	public void setType(String type,int stream)
	{
		this._types[stream] = decodeType(type);
	}
	
	/**
	 * Configure en fonction de l'option et l'argument donnés
	 * 
	 * @param opt  option
	 * @param arg  argument
	 */
	public void setArg(String opt, String arg)
	{
		if (opt.equals("-i"))
		{
			this._fileList.add(IN,arg);
			this._multi = false;
		}
		else
		if (opt.equals("-o"))
		{
			this._fileList.add(OUT,arg);
			this._multi = false;
		}
		else
		if (opt.equals("-it"))
		{
			this._types[IN] = decodeType(arg);
		}
		else
		if (opt.equals("-ot"))
		{
			this._types[OUT] = decodeType(arg);
		}
	}
	
	/*----------------------------------------*/
	
	/**
	 * Renvoie le type du fichier de sortie en fonction du type
	 * du fichier d'entrée
	 * 
	 * @param inType  type du fichier d'entrée
	 */
	public int deductOutType(int inType)
	{
		return inType+1;
	}
	
	/**
	 * Renvoie le nom par defaut du fichier de sortie en analysant
	 * le nom du fichier d'entrée
	 * 
	 * @param inName  nom du fichier d'entrée
	 */
	public String deductOutName(String inName) throws CommandLineException
	{
		int ind = inName.lastIndexOf(".");
		if (ind == -1)
		{
			throw new CommandLineException("Erreur : \""+inName+"\" n'est pas un nom de fichier");
		}
		String outName = inName.substring(0,ind+1);
		String inExt = inName.substring(ind+1);
		String outExt = typeToExt(deductOutType(extToType(inExt)));
		outName += outExt;
		return outName;
	}
	
	/**
	 * Renvoie un nom de fichier à partir d'un nom de base et d'une extension
	 * 
	 * @param base  nom de base
	 * @param type  extension du nom de fichier à créer
	 */
	public String createFileName(String base,int type) throws CommandLineException
	{
		String fileName;
		int ind = base.lastIndexOf(".");
		if (ind == -1)
		{
			throw new CommandLineException("Erreur : \""+base+"\" n'est pas un nom de fichier");
		}
		fileName  = base.substring(0,ind+1);
		fileName += typeToExt(type);
		return fileName;
	}
	
	/*----------------------------------------*/

	/**
	 * Rend le type (int) à partir du type (string)
	 *
	 * @param t  type (string)
	 * @return   type (int)
	 */
	static int decodeType(String t)
	{
		int type;
		if (t.equals("yaka"))
		{
			type = YAKA;
		}
		else
		if (t.equals("yvm"))
		{
			type = YVM;
		}
		else
		if (t.equals("asm"))
		{
			type = ASM;
		}
		else
		if (t.equals("exe"))
		{
			type = EXE;
		}
		else
		{
			type = -1;
		}
		return type;
	}

	/**
	 * Rend le type (string) à partir du type (int)
	 *
	 * @param t  type (int)
	 * @return   type (string)
	 */
	static String decodeType(int t)
	{
		String type;
		switch(t)
		{
			case YAKA:
				type = "yaka";
			break;
			case YVM:
				type = "yvm";
			break;
			case ASM:
				type = "asm";
			break;
			case EXE:
				type = "exe";
			break;
			default:
				type = null;
		}
		return type;
	}

	/**
	 * Renvoie le type en analysant l'extension donnée
	 * 
	 * @param ext  extension
	 * @return  type
	 */
	static int extToType(String ext)
	{
		return decodeType(ext);
	}
	
	/**
	 * Renvoie l'extension à partir du type
	 * 
	 * @param t  type
	 * @return   extension
	 */
	static String typeToExt(int t)
	{
		return decodeType(t);
	}
	
	/**
	 * Renvoie le type d'un fichier en analysant son extension
	 * 
	 * @param name  nom de fichier
	 * @return  type
	 */
	static int nameToType(String name)
	{
		int ind = name.lastIndexOf(".");
		String ext = name.substring(ind+1);
		return extToType(ext);
	}
	
	/*----------------------------------------*/
	
	/**
	 * Retourne si la ligne de commande est correcte ou non
	 * Dans le cas ou celle ci est correcte, il renvoie un tableau contenant
	 * le(s) tableau(x) de configuration de compilation
	 *
	 * @return  tableau d'unités de compilation
	 */
	public CompileUnit[] checkMainConfig() throws CommandLineException
	{
		if (this._fileList.size()==0)
		{
			throw new CommandLineException("Erreur : aucun fichier d'entree n'a ete specifie");
		}
		
		for (int i=0 ; i<this._fileList.size() ; i++)
		{
			if (isOption((String)this._fileList.get(i)))
			{
				throw new CommandLineException("Erreur : '"+((String)this._fileList.get(i)).toString()+"' aurait du être nom de fichier");
			}
		}
		
		if (this._multi)
		{
			if (this._fileList.size()<2)
			{
				// je doute qu'on puisse ne arriver la ...
				throw new CommandLineException("Erreur");
			}
		}
		else
		{
			if (this._fileList.size()!=2)
			{
				// je doute qu'on puisse ne arriver la ...
				throw new CommandLineException("Erreur");
			}
		}
		
		// at this point, everything has been checked
		
		CompileUnit[] cu;
		
		if (this._multi)
		{
			cu = new CompileUnit[this._fileList.size()];
			
			for(int i=0 ; i<this._fileList.size() ; i++)
			{
				String fIn  = (String)this._fileList.get(i);
				String fOut = deductOutName(fIn);
				int tIn  = nameToType(fIn);
				int tOut;
				if (this._types[OUT] == 0)
				{
					tOut = deductOutType(tIn);
				}
				else
				{
					tOut = this._types[OUT];
				}
				boolean ov = this._options[OV];
				CompileUnit c = new CompileUnit(fIn,fOut,tIn,tOut,ov);
				cu[i] = c;
			}
		}
		else
		{
			cu = new CompileUnit[1];
			String fIn  = (String)this._fileList.get(IN);
			String fOut = (String)this._fileList.get(OUT);
			int tIn  = this._types[IN];
			int tOut = this._types[OUT];
			boolean ov = this._options[OV];
			CompileUnit c = new CompileUnit(fIn,fOut,tIn,tOut,ov);
			cu[0] = c;
		}

		return cu;
	}
	
	/*----------------------------------------*/
	
	/**
	 * Excecute la ou les compilations
	 *
	 * @param cu  tableau d'unités de compilation
	 */
	public void execCompilation(CompileUnit[] cu) throws CommandLineException
	{
		for (int i=0 ; i<cu.length ; i++)
		{
			cu[i].compile();
		}
	}
	
	/*----------------------------------------*/
	
	public static void main(String args[])
	{
		YakaCLI cli = new YakaCLI();
		
		// display help if requested
		if (args.length==0 || args[0].equals("--help"))
		{
			cli.helpMessage();
			System.exit(0);
		}
		
		try
		{
			// process the commande line
			cli.parseCommandLine(args);
			
			// check if the command line call is ok
			CompileUnit[] cu;
			cu = cli.checkMainConfig();
			
			// execute each compilation unit
			cli.execCompilation(cu);
		}
		catch (CommandLineException e)
		{
			System.out.println(e.getMessage());
			//e.printStackTrace();
			//System.out.println("");
			//cli.helpMessage();
		}

	} // main
	
	/*--------------------*/

	/**
	 * Classe d'exception précompilation
	 */
	public class CommandLineException extends Exception
	{
		public CommandLineException(String m)
		{
			super(m);
		}
		
		public CommandLineException()
		{
			super("Erreur inconnue");
		}
	} // CommandLineException
	
	/*--------------------*/

	/**
	 * Classe d'unité de compilation
	 */
	private class CompileUnit
	{
		private String  _inputFile;  // Nom du fichier d'entrée
		private String  _outputFile; // Nom du fichier de sorite
		private int     _inputType;  // Type d'entrée
		private int     _outputType; // Type de sortie
		private boolean _overWrite;  // Peut-on écraser le fichier de sortie ?
		
		/**
		 * Constantes de status
		 */
		static final int FAILURE = 0;
		static final int SUCCESS = 1;
		
		public CompileUnit(String inF, String outF, int inT, int outT, boolean ov)
		{
			this._inputFile  = inF;
			this._outputFile = outF;
			this._inputType  = inT;
			this._outputType = outT;
			this._overWrite  = ov;
		}
		
		/**
		 * Méthode pour declencher la compilation
		 */
		public int compile() throws CommandLineException
		{
			int status = FAILURE;
			
			// vérification de l'existence du fichier d'entrée et du fichier
			// de sortie au cas où l'option overwrite n'ait pas été utilisée
			File in  = new File(_inputFile);
			File out = new File(_outputFile);
			if (!in.exists())
			{
				throw new CommandLineException("Le fichier d'entree \""+_inputFile+"\" n'existe pas");
			}
			if (out.exists())
			{
				if (!_overWrite)
				{
					throw new CommandLineException("Le fichier de sortie \""+_outputFile+"\" existe deja"
												+"\nUtilisez l'option -ov pour l'ecraser");
				}
				else
				{
					// warning / info
					System.out.println("Warning : Le fichier de sortie \""+_outputFile+"\" va etre ecrase");
				}
			}
			
			// on peut maintenant lancer la compilation
			// selon les paramètres de types
			switch(_inputType)
			{
				case YAKA:
					switch(_outputType)
					{
						case YVM:
							//debug
							System.out.println("Demarrage compilation YAKA->YVM ("+_inputFile+"->"+_outputFile+")");
						
							// création objets puis appels
							try
							{
								FileInputStream  inputStream  = new FileInputStream(this._inputFile);
								FileOutputStream outputStream = new FileOutputStream(this._outputFile);;
								FileToYaka analyseur  = new FileToYaka(inputStream);;
								YVMToFile generateur  = new YVMToFile(outputStream);
								analyseur._generator = new YakaToYVM(generateur);
								analyseur.analyse();
							}
							catch (ParseException e)
							{
								String msg = e.getMessage();
								msg = msg.substring(0,msg.indexOf("\n"));
								System.out.println("Erreur de syntaxe : "+msg);
							}
						break;
						case ASM:
							// création objets puis appels
							
							//debug
							System.out.println("Demarrage compilation YAKA->ASM ("+_inputFile+"->"+_outputFile+")");
						break;
						case EXE:
							// création objets puis appels
							
							//debug
							System.out.println("Demarrage compilation YAKA->EXE ("+_inputFile+"->"+_outputFile+")");
						break;
					}
				break;
				case YVM:
					// création objets puis appels
					
					//debug
					System.out.println("Demarrage compilation YVM->[???] ("+_inputFile+"->"+_outputFile+")");
				break;
			}
			
			return status;
		}
	} // CompileUnit
	
}
