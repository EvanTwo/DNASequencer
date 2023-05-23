package edu.cis.main;

public class Coordinate
{
    private int start;
    private int end;

    public Coordinate(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    public int getEnd()
    {
        return end;
    }

    public int getStart()
    {
        return start;
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public void setStart(int start)
    {
        this.start = start;
    }
}
