// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Arbre.java

import java.io.PrintStream;

public class Arbre
{

    public Arbre(Object obj, Arbre arbre, Arbre arbre1)
    {
        racine = obj;
        filsg = arbre;
        filsd = arbre1;
    }

    public Arbre(Object obj)
    {
        this(obj, new Arbre(), new Arbre());
    }

    public Arbre()
    {
        racine = null;
    }

    public boolean vide()
    {
        return racine == null;
    }

    public Object racine()
    {
        if(vide())
        {
            System.out.println("racine : Erreur, arbre vide !");
            System.exit(-1);
        }
        return racine;
    }

    public Arbre filsg()
    {
        if(vide())
        {
            System.out.println("filsg : Erreur, arbre vide !");
            System.exit(-1);
        }
        return filsg;
    }

    public Arbre filsd()
    {
        if(vide())
        {
            System.out.println("filsd : Erreur, arbre vide !");
            System.exit(-1);
        }
        return filsd;
    }

    public void vider()
    {
        if(!vide())
        {
            filsg.vider();
            filsd.vider();
            racine = null;
        }
    }

    public void modifracine(Object obj)
    {
        if(vide())
        {
            System.out.println("modifracine : Erreur, arbre vide !");
            System.exit(-1);
        }
        racine = obj;
    }

    public void modiffilsg(Arbre arbre)
    {
        if(vide())
        {
            System.out.println("modiffilsg : Erreur, arbre vide !");
            System.exit(-1);
        }
        filsg = arbre;
    }

    public void modiffilsd(Arbre arbre)
    {
        if(vide())
        {
            System.out.println("modiffilsd : Erreur, arbre vide !");
            System.exit(-1);
        }
        filsd = arbre;
    }

    public void oterracine()
    {
        if(vide())
        {
            System.out.println("oterracine : Erreur, arbre vide !");
            System.exit(-1);
        }
        if(filsg.vide())
            racine = filsd;
        else
        if(filsd.vide())
        {
            racine = filsg;
        } else
        {
            System.out.println("oterracine : Erreur, les deux sous-arbres existent !");
            System.exit(-1);
        }
    }

    public String toString()
    {
        String s = "Arbre(";
        if(!vide())
            s = s + racine() + ", " + filsg() + ", " + filsd();
        return s + ")";
    }

    public static void main(String args[])
    {
        Arbre arbre = new Arbre("10", new Arbre("1", new Arbre("3"), new Arbre("4")), new Arbre("2"));
        System.out.println(arbre);
        afficher_arbre(arbre, 0);
    }

    static void afficher_arbre(Arbre arbre, int i)
    {
        if(!arbre.vide())
        {
            afficher_arbre(arbre.filsd(), i + 1);
            afficher_espaces(i);
            System.out.println(arbre.racine());
            afficher_arbre(arbre.filsg(), i + 1);
        }
    }

    static void afficher_espaces(int i)
    {
        for(int j = 1; j <= i; j++)
            System.out.print("  ");

    }

    protected Object racine;
    protected Arbre filsg;
    protected Arbre filsd;
}
