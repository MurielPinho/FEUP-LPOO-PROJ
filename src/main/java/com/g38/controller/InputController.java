package com.g38.controller;

import com.g38.model.*;
import com.g38.view.GameView;

import java.io.IOException;
import java.util.List;

public class InputController {
    private final GameModel model;
    private final GameView view;

    public InputController(GameModel model, GameView view) {
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

    public void playerAction(GameView.ACTION action){
        PlayerModel player = model.getPlayerModel();
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
        else if(action ==GameView.ACTION.RETURN)
        {
            model.setGameState(GameModel.State.MENU);
        }
        else if(action ==GameView.ACTION.QUIT)
        {
            model.setGameState(GameModel.State.DONE);
        }

    }
    public void menuAction(GameView.ACTION action){
        GameModel.menuState mState = model.getMenuSelection();
        GameModel.menuState cMenu = model.getCurrentMenu();
        if (action == GameView.ACTION.ENTER)
        {
            if (mState == GameModel.menuState.PLAY)
            {
                model.setCurrentMenu(GameModel.menuState.PLAY);
                model.newMaze();
                model.setGameState(GameModel.State.PLAY);
            }
            else if(mState == GameModel.menuState.INSTRUCTIONS){
                if (cMenu == GameModel.menuState.INSTRUCTIONS)
                {
                    model.setCurrentMenu(GameModel.menuState.PLAY);
                }
                else{
                    model.setCurrentMenu(GameModel.menuState.INSTRUCTIONS);
                }
            }
            else {
                model.setCurrentMenu(GameModel.menuState.QUIT);
                model.setGameState(GameModel.State.DONE);

            }
        }
        else if ((action == GameView.ACTION.DOWN) && (cMenu != GameModel.menuState.INSTRUCTIONS))
        {
            if (mState == GameModel.menuState.PLAY)
            {
                model.setMenuSelection(GameModel.menuState.INSTRUCTIONS);
            }
            else if(mState == GameModel.menuState.INSTRUCTIONS){
                model.setMenuSelection(GameModel.menuState.QUIT);
            }
            else {
                model.setMenuSelection(GameModel.menuState.PLAY);
            }
        }
        else if ((action == GameView.ACTION.UP) && (cMenu != GameModel.menuState.INSTRUCTIONS))
        {
            if (mState == GameModel.menuState.PLAY)
            {
                model.setMenuSelection(GameModel.menuState.QUIT);
            }
            else if(mState == GameModel.menuState.INSTRUCTIONS){
                model.setMenuSelection(GameModel.menuState.PLAY);
            }
            else {
            model.setMenuSelection(GameModel.menuState.INSTRUCTIONS);
            }

        }
        else if(action ==GameView.ACTION.QUIT)
        {
            model.setGameState(GameModel.State.DONE);
        }

    }
    public void executeAction(GameView.ACTION action) {
        GameModel.State currentState = model.getGameState();
        if(currentState == GameModel.State.PLAY){
            playerAction(action);

        }
        else if (currentState  == GameModel.State.MENU){
            menuAction(action);
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
