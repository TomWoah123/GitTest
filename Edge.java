import greenfoot.*;
import java.awt.Color;
public class Edge implements Comparable<Edge>
{
    private Location locOne;
    private Location locTwo;
    
    public Edge( Location locOne, Location locTwo )
    {
        this.locOne = locOne;
        this.locTwo = locTwo;
    }
    
    public Location getLocationOne()
    {
        return locOne;
    }
    
    public Location getLocationTwo()
    {
        return locTwo;
    }
    
    public double getDistance()
    {
        return Math.sqrt( Math.pow( ( locOne.getX() - locTwo.getX() ), 2 ) + Math.pow( ( locOne.getY() - locTwo.getY() ), 2 ) );
    }
    
    public int compareTo( Edge other )
    {
        return (int)( getDistance() - other.getDistance() );
    }
    
    public void show( World w )
    {
        GreenfootImage bg = w.getBackground();
        bg.setColor( Color.ORANGE );
        bg.drawLine( locOne.getX(), locOne.getY(), locTwo.getX(), locTwo.getY() );
    }
}
