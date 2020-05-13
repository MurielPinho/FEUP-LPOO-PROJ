import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {
    private Player getPlayer(){
        Player p = new Player(38,1);
        return  p;
    }
    private Key getKey(){
        Key k = new Key(50,39);
        return  k;
    }
    private Ladder getLadder(){
        Ladder l = new Ladder(2,25);
        return  l;
    }

    @Test
    public void playerPosition(){
        Player p = getPlayer();
        assertEquals(p.getPosition().getX(), 38);
        assertEquals(p.getPosition().getY(), 1);

    }

    @Test
    public void keyPosition(){
        Ladder l = getLadder();
        assertEquals(l.getPosition().getX(), 2);
        assertEquals(l.getPosition().getY(), 25);

    }

    @Test
    public void ladderPosition(){
        Key k = getKey();
        assertEquals(k.getPosition().getX(), 50);
        assertEquals(k.getPosition().getY(), 39);

    }
}
