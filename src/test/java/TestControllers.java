import com.g38.controller.DungeonController;
import com.g38.controller.GameController;
import com.g38.controller.InputController;
import com.g38.model.*;
import org.junit.Test;
import com.g38.view.GameView;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestControllers {



    @Test
    public void testGameController() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController gameController = new GameController(model, view);

        assertNotNull(gameController.getModel().getDungeonModel());
        assertNotNull(gameController.getModel().getPlayerModel());

    }

    @Test
    public void testInputControllerMenu() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController gameController = new GameController(model, view);
        InputController inputController = gameController.getInputController();
        model.setGameState(GameModel.State.MENU);
        inputController.executeAction(GameView.ACTION.UP);
        assertEquals(GameModel.menuState.QUIT,model.getMenuSelection());
        inputController.executeAction(GameView.ACTION.UP);
        inputController.executeAction(GameView.ACTION.UP);
        assertEquals(GameModel.menuState.PLAY,model.getMenuSelection());


    }

    @Test
    public void testInputControllerGame() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController gameController = new GameController(model, view);
        InputController inputController = gameController.getInputController();
        PlayerModel player = model.getPlayerModel();
        model.setGameState(GameModel.State.PLAY);
        Position pPos  = new Position(23,23) ;
        inputController.executeAction(GameView.ACTION.RIGHT);
        if (inputController.checkCollision(pPos))
        {
           pPos = new Position(24,23);
        }
        else   {
            pPos = new Position(23,23);

        }
        assertEquals(pPos.getX(),player.getPosition().getX());
        inputController.executeAction(GameView.ACTION.RETURN);
        assertEquals(GameModel.State.MENU,model.getGameState());
    }

    @Test
    public void testDungeonControllerKey() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController gameController = new GameController(model, view);
        DungeonController dungeonController = gameController.getDungeonController();
        PlayerModel player = model.getPlayerModel();
        assertFalse(model.getDungeonModel().isKeyObtained());
        player.setPosition(model.getDungeonModel().getKey().getPosition());
        dungeonController.isLevelOver();
        assertTrue(model.getDungeonModel().isKeyObtained());

    }

    @Test
    public void testDungeonControllerExit() throws IOException {
        GameModel model = new GameModel(85, 85);
        GameView view = new GameView(model);
        GameController gameController = new GameController(model, view);
        DungeonController dungeonController = gameController.getDungeonController();
        PlayerModel player = model.getPlayerModel();
        assertEquals(10,model.getMazeSize());
        player.setPosition(model.getDungeonModel().getKey().getPosition());
        dungeonController.isLevelOver();
        player.setPosition(model.getDungeonModel().getExit().getPosition());
        dungeonController.isLevelOver();
        assertEquals(20,model.getMazeSize());

    }



}
