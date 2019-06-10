import greenfoot.*;
public class Location extends Actor
{
    private int x;
    private int y;
    
    public Location( int x, int y )
    {
        this.x = x;
        this.y = y;
    }
    
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
    
    public int hashCode()
    {
        return toString().hashCode();
    }
    
    public boolean equals( Location other )
    {
        return toString().equals( other.toString() );
    }
}
