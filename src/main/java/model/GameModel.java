package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final PlayerModel playerModel;
    private DungeonModel dungeonModel;
    private final int width;
    private final int height;
    private int mazeSize;
    private final int maxMazeSize;
    private State GameState;
    public enum State {MENU, PLAY, DONE}

    public GameModel(int width, int height) {
        this.GameState = State.PLAY;
        this.width = width;
        this.height = height;
        this.mazeSize = 10;
        this.maxMazeSize = 50;
        this.dungeonModel = new DungeonModel(mazeSize);
        this.playerModel = new PlayerModel(dungeonModel.getPlayer());
    }

    public void nextMaze() {
        if (mazeSize < maxMazeSize)
        {
            mazeSize += 10;
        }
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

}
