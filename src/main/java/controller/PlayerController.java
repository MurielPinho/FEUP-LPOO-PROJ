package controller;

import model.*;
import view.GameView;

import java.io.IOException;
import java.util.List;

public class PlayerController {
    private GameModel model;
    private GameView view;

    public PlayerController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }
    public boolean checkCollision(Position position){
        List<Wall> walls = model.getDungeonModel().getWalls();
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }
        return true;
    }
    public void executeAction(GameView.ACTION action) {
        PlayerModel player = model.getPlayerModel();
        DungeonModel dungeon = model.getDungeonModel();
        if (action == GameView.ACTION.LEFT)
        {
            if (checkCollision(player.getPosition().left()))
            {
                player.setPosition(player.getPosition().left());
            }
        }
        else if (action == GameView.ACTION.RIGHT)
        {
            if (this.checkCollision(player.getPosition().right()))
            {
                player.setPosition(player.getPosition().right());
            }
        }
        else if (action == GameView.ACTION.DOWN)
        {
            if (this.checkCollision(player.getPosition().down()))
            {
                player.setPosition(player.getPosition().down());
            }
        }
        else if (action == GameView.ACTION.UP)
        {
            if (this.checkCollision(player.getPosition().up()))
            {
                player.setPosition(player.getPosition().up());
            }
        }

        }



    public void start() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        GameView.ACTION action = view.getAction();
                        executeAction(action);
                        view.draw();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
