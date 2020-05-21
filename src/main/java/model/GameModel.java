package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private PlayerModel playerModel;
    private DungeonModel dungeonModel;
    private int width;
    private int height;
    private int currentMaze = 0;
    private List<Maze> mazes = new ArrayList<>();

    public GameModel(int width, int height) {
        this.width = width;
        this.height = height;
        for(int i = 1; i <= 4; i++)
            this.mazes.add(new Maze(i));
        this.dungeonModel = new DungeonModel(mazes.get(currentMaze));
        this.playerModel = new PlayerModel(mazes.get(currentMaze).getPlayer());
    }

    public void nextMaze() {
        currentMaze++;
        this.dungeonModel = new DungeonModel((mazes.get(currentMaze)));
        this.playerModel.setPosition(mazes.get(currentMaze).getPlayer());
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

}
