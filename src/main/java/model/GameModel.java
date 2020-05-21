package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private PlayerModel playerModel;
    private DungeonModel dungeonModel;
    private int width;
    private int height;
    private int mazeSize;
    private int maxMazeSize;

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.mazeSize = 10;
        this.maxMazeSize = 53;
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

}
