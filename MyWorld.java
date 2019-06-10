import greenfoot.*;
import java.util.*;
import java.awt.Color;
public class MyWorld extends World
{
    
    public MyWorld()
    {    
        super(961, 604, 1);
        randomLocations( 60 );
    }
    
    public void act()
    {
        drawEdges( buildAllEdges() );
    }
    
    public void randomLocations( int n )
    {
        for ( int i = 0; i < n; i++ )
        {
            int x = ( int ) ( Math.random() * getWidth() );
            int y = ( int ) ( Math.random() * getHeight() );
            while ( getColorAt( x, y ).equals( Color.WHITE ) )
            {
                x = ( int ) ( Math.random() * getWidth() );
                y = ( int ) ( Math.random() * getHeight() );
            }
            Location loc = new Location( x, y );
            addObject( loc, x, y );
        }
    }
    
    public List<Edge> buildAllEdges()
    {
        List<Edge> edges = new ArrayList<>();
        List<Location> locs = getObjects( Location.class );
        for ( int i = 0; i < locs.size(); i++ )
        {
            for ( int k = 0; k < locs.size(); k++ )
            {
                if ( !locs.get( i ).equals( locs.get( k ) ) )
                {
                    Edge e = new Edge( locs.get( i ), locs.get( k ) );
                    edges.add( e );
                }
            }
        }
        return edges;
    }
    
    public void drawEdges( List<Edge> edges )
    {
        for ( int i = 0; i < edges.size(); i++ )
        {
            edges.get( i ).show( this );
        }
    }
    
    public List<Edge> buildRandomConnections( int n )
    {
        List<Edge> edges = new ArrayList<>();
        List<Location> locs = getObjects( Location.class );
        for ( int i = 0; i < n; i++ )
        {
            Location locOne = locs.get( ( int ) ( Math.random() * locs.size() ) );
            Location locTwo = locs.get( ( int ) ( Math.random() * locs.size() ) );
            while ( locOne.equals( locTwo ) || edges.contains( new Edge( locOne, locTwo ) ) )
            {
                locOne = locs.get( ( int ) ( Math.random() * locs.size() ) );
                locTwo = locs.get( ( int ) ( Math.random() * locs.size() ) );
            }
            Edge e = new Edge( locOne, locTwo );
            edges.add( e );
        }
        drawEdges( edges );
        return edges;
    }
    
    public void prims()
    {
        List<Location> locs = getObjects( Location.class );
        List<Edge> edges = buildAllEdges();
        Collections.sort( edges );
        Stack<Location> covered = new Stack<>();
        Location start = locs.get( ( int ) ( Math.random() * locs.size() ) );
        int size = locs.size();
        locs.remove( start );
        covered.push( start );
        for ( int i = 0; i < edges.size(); i++ )
        {
            if ( edges.get( i ).getLocationOne().equals( start ) &&
                !covered.contains( edges.get( i ).getLocationTwo() ) )
            {
                edges.get( i ).show( this );
                covered.push( edges.get( i ).getLocationTwo() );
                start = edges.get( i ).getLocationTwo();
                edges.remove( edges.get( i ) );
                locs.remove( edges.get( i ).getLocationTwo() );
                if ( covered.size() == size )
                {
                    return;
                }
                else
                {
                    i = 0;
                }
            }
            else if ( edges.get( i ).getLocationTwo().equals( start ) && 
                     !covered.contains( edges.get( i ).getLocationOne() ) )
            {
                edges.get( i ).show( this );
                covered.push( edges.get( i ).getLocationOne() );
                start = edges.get( i ).getLocationOne();
                edges.remove( edges.get( i ) );
                locs.remove( edges.get( i ).getLocationOne() );
                if ( covered.size() == size )
                {
                    return;
                }
                else
                {
                    i = 0;
                }
            }
        }
    }
}

