// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TokenManager.java

import java.io.InputStream;

public class TokenManager
    implements Constants
{

    public TokenManager(InputStream inputstream)
    {
        flot = inputstream;
        ligne = 1;
        lireCarLu();
    }

    public void finalize()
    {
        Lecture.fermer(flot);
    }

    private char lireCarLu()
    {
        if(!Lecture.finFichier(flot))
        {
            carLu = Lecture.lireChar(flot);
            if(carLu == '\n')
                ligne++;
        } else
        {
            carLu = '\0';
        }
        return carLu;
    }

    private void lireIdent()
    {
        lexeme = "";
        for(; Character.isLetter(carLu); lireCarLu())
            lexeme += carLu;

        identLu = lexeme;
    }

    private void lireEntier()
    {
        lexeme = "";
        for(; Character.isDigit(carLu); lireCarLu())
            lexeme += carLu;

        entierLu = Integer.parseInt(lexeme);
    }

    private int lireBalise()
    {
        lexeme = "";
        lexeme += carLu;
        lireCarLu();
        while(carLu != '>' && carLu != 0) 
        {
            lexeme += carLu;
            lireCarLu();
        }
        lexeme += carLu;
        lireCarLu();
        for(int i = 1; i <= 8; i++)
            if(Constants.images[i].equals(lexeme))
                return i;

        return 0;
    }

    public int lireUnite()
    {
        for(; Character.isWhitespace(carLu); lireCarLu());
        if(Character.isLetter(carLu))
        {
            lireIdent();
            return 9;
        }
        if(Character.isDigit(carLu))
        {
            lireEntier();
            return 10;
        }
        switch(carLu)
        {
        case 60: // '<'
            return lireBalise();

        case 46: // '.'
            lireCarLu();
            lexeme = ".";
            return 11;

        case 44: // ','
            lireCarLu();
            lexeme = ",";
            return 12;

        case 0: // '\0'
            lexeme = "EOF";
            return 13;
        }
        lireCarLu();
        return 0;
    }

    public Token getToken()
    {
        Token token = new Token();
        token.unite = lireUnite();
        token.lexeme = lexeme;
        token.ligne = ligne;
        return token;
    }

    public static void main(String args[])
    {
        String s = args[0];
        InputStream inputstream = Lecture.ouvrir(s);
        TokenManager tokenmanager = new TokenManager(inputstream);
        for(Token token = tokenmanager.getToken(); token.unite != 13; token = tokenmanager.getToken())
            System.out.println(token);

        tokenmanager.finalize();
    }

    public String identLu;
    public int entierLu;
    private String lexeme;
    private int ligne;
    private InputStream flot;
    private char carLu;
    private static final char charEOF = 0;
}
