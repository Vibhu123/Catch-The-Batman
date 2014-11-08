package com.example.action_bar;

public class chSet
{

    public chSet(int start, int stop, int multiplier)
    {
        itsStart = start;
        itsStop = stop;
        itsMultiplier = multiplier;
    }

    public chSet(int start)
    {
        itsStart = start;
        itsMultiplier = 1;
        itsStop = 0;
    }

    public chSet()
    {
        itsStart = 0;
        itsStop = 0;
        itsMultiplier = 1;
    }

    public int getStart()
    {
        return itsStart;
    }

    public int getStop()
    {
        return itsStop;
    }

    public int getMultiplier()
    {
        return itsMultiplier;
    }

    public void setStart(int s)
    {
        itsStart = s;
    }

    public void setStop(int s)
    {
        itsStop = s;
    }

    public void setMultiplier(int m)
    {
        itsMultiplier = m;
    }

    public String toString()
    {
        return "From: " + itsStart + " To: " + itsStop + " Mult: " + itsMultiplier;
    }

    private int itsStart;
    private int itsStop;
    private int itsMultiplier;
}
