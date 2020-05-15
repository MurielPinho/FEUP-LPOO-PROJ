package controller;

import model.DungeonModel;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class DungeonController {
    private GameModel model;
    private GameView view;
    private DungeonModel dungeon;

    public DungeonController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public boolean isLevelOver(){
    return true;
    }




    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
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
