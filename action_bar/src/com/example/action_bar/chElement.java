package com.example.action_bar;


public class chElement
{

    public chElement(String sym)
    {
        itsSymbol = "";
        itsCount = 0;
        itsSymbol = sym;
        itsCount = 0;
    }

    public chElement(String sym, int Count)
    {
        itsSymbol = "";
        itsCount = 0;
        itsSymbol = sym;
        itsCount = Count;
    }

    public int addCount()
    {
        return itsCount++;
    }

    public int addCount(int n)
    {
        return itsCount += n;
    }

    public void setCount(int n)
    {
        itsCount = n;
    }

    public int getCount()
    {
        return itsCount;
    }

    public String getSymbol()
    {
        return itsSymbol;
    }

    public chElement getCopy()
    {
        return new chElement(itsSymbol, itsCount);
    }

    public String toString()
    {
        return itsSymbol + " " + itsCount;
    }

    public boolean equals(Object o)
    {
        chElement ob2 = (chElement)o;
        return ob2.getSymbol().equals(getSymbol());
    }

    private String itsSymbol;
    private int itsCount;
}
