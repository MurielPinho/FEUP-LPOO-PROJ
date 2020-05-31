package com.g38.controller;

import com.g38.model.GameModel;
import com.g38.view.GameView;

public class DungeonController {
    private final GameModel model;
    private final GameView view;


    public DungeonController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }


    public void isLevelOver(){

        if (model.getPlayerModel().getPosition().equals(model.getDungeonModel().getKey().getPosition()))
        {
            model.getDungeonModel().setKeyObtained(true);
        }
        else if (model.getPlayerModel().getPosition().equals(model.getDungeonModel().getExit().getPosition()))
        {
            model.nextMaze();
        }
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
                }
            }
        }).start();
    }

}
