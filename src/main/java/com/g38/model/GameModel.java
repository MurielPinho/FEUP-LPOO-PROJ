package com.g38.model;

public class GameModel {

    private final PlayerModel playerModel;
    private DungeonModel dungeonModel;
    private final int width;
    private final int height;
    private final int maxMazeSize;
    private int mazeSize;
    private menuState menuSelection;
    private menuState currentMenu;
    private State GameState;
    public enum State {MENU, PLAY, DONE}
    public enum menuState {PLAY,INSTRUCTIONS,QUIT}

    public GameModel(int width, int height) {
        this.GameState = State.MENU;
        this.width = width;
        this.height = height;
        this.mazeSize = 10;
        this.maxMazeSize = 50;
        this.dungeonModel = new DungeonModel(mazeSize);
        this.playerModel = new PlayerModel(dungeonModel.getPlayer());
        this.menuSelection = menuState.PLAY;
        this.currentMenu = menuState.PLAY;
    }

    public void nextMaze() {
        if (mazeSize == maxMazeSize)
        {
            mazeSize = 10;
            setGameState(State.MENU);
        }
        else
        {
            mazeSize += 10;
        }
        newMaze();

    }

    public void newMaze()
    {
        this.dungeonModel = new DungeonModel(mazeSize);
        this.playerModel.setPosition(dungeonModel.getPlayer());
    }
    public DungeonModel getDungeonModel() {
        return dungeonModel;
    }

    public PlayerModel getPlayerModel() { return playerModel; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMazeSize()
    {
        return mazeSize;
    }

    public State getGameState()
    {
        return GameState;
    }

    public void setGameState(State s)
    {
        this.GameState = s;
    }

    public menuState getMenuSelection() { return menuSelection;}

    public void setMenuSelection(menuState ms) {this.menuSelection = ms;}

    public menuState getCurrentMenu() { return currentMenu;}

    public void setCurrentMenu(menuState ms) {this.currentMenu = ms;}

}
