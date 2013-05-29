// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Token.java


public class Token
{

    public Token()
    {
    }

    public String toString()
    {
        return "lexeme = " + lexeme + " - unite=" + unite + " - ligne n\260" + ligne;
    }

    public int unite;
    public int ligne;
    public String lexeme;
}
