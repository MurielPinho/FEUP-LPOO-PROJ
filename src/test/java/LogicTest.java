import controller.GameController;
import model.*;
import org.junit.Test;
import view.GameView;

import java.io.IOException;

import static org.junit.Assert.*;

public class LogicTest {
    private PlayerModel getPlayer(){
        PlayerModel p = new PlayerModel(new Position(38,1));
        return  p;
    }
    private Key getKey(){
        Key k = new Key(50,39);
        return  k;
    }
    private Exit getLadder(){
        Exit l = new Exit(2,25);
        return  l;
    }

    @Test
    public void modelsNotNull() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);

        assertNotNull(controller.getModel().getDungeonModel());
        assertNotNull(controller.getModel().getPlayerModel());
    }

    @Test
    public void playerPosition(){
        PlayerModel p = getPlayer();
        assertEquals(p.getPosition().getX(), 38);
        assertEquals(p.getPosition().getY(), 1);
    }

    @Test
    public void keyPosition(){
        Exit l = getLadder();
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
