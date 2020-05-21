package controller;

import model.GameModel;
import view.GameView;

import java.io.IOException;

public class GameController {
    private final DungeonController dungeonController;
    private final PlayerController playerController;

    private final GameView view;
    private final GameModel model;

    public GameController(GameModel model, GameView view) throws IOException {

        this.view = view;
        this.model = model;
        this.dungeonController = new DungeonController(model, view);
        this.playerController = new PlayerController(model,view);
    }

    public void start() throws IOException {
       dungeonController.start();
       playerController.start();
       update();
    }

    public GameModel getModel() {
        return model;
    }

    public void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                        if (model.getGameState() == GameModel.State.DONE)
                        {
                            System.exit(0);
                        }
                    }
                }
        }).start();
    }

}