package controller;

import model.DungeonModel;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class DungeonController {
    private GameModel model;
    private GameView view;


    public DungeonController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public boolean isLevelOver(){

        if (model.getPlayerModel().getPosition().equals(model.getDungeonModel().getKey().getPosition()))
        {
            model.getDungeonModel().setKeyObtained(true);
        }
        else if (model.getPlayerModel().getPosition().equals(model.getDungeonModel().getLadder().getPosition()))
        {
            model.getDungeonModel().setMazeDone(true);
        }
    return true;
    }




    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    isLevelOver();

                    try {
                        view.draw();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
