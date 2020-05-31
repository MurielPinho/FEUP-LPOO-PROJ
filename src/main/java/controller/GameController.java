package controller;

import model.GameModel;
import view.GameView;

import java.io.IOException;

public class GameController {
    private final DungeonController dungeonController;
    private final InputController inputController;


    private final GameView view;
    private final GameModel model;

    public GameController(GameModel model, GameView view) throws IOException {

        this.view = view;
        this.model = model;
        this.dungeonController = new DungeonController(model, view);
        this.inputController = new InputController(model,view);

    }

    public void start() throws IOException {
       dungeonController.start();
       inputController.start();
       update();
    }

    public void checkGameState(){
        if (model.getGameState() == GameModel.State.DONE)
        {
            System.exit(0);
        }
    }

    public GameModel getModel() {
        return model;
    }

    public InputController getInputController() {
        return inputController;
    }

    public DungeonController getDungeonController() {
        return dungeonController;
    }

    public void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        checkGameState();
                        view.draw();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}