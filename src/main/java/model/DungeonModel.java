package model;

import java.util.ArrayList;
import java.util.List;

public class DungeonModel {

    private int height;
    private int width;
    private Maze maze;
    private Ladder ladder;
    private List<Wall> walls = new ArrayList<>();
    private Key key;



    DungeonModel(int w, int h, Maze maze){
        this.height = h;
        this.width = w;
        this.maze = maze;
        this.key = this.maze.getKey();
        this.ladder = this.maze.getLadder();
        createWalls();

    }
    private void createWalls() {

        for (int i = 0; i < this.maze.getWalls().size(); i++) {
            for (int j = 0; j < this.maze.getWalls().get(i).length(); j++) {
                if(this.maze.getWalls().get(i).charAt(j) != ' '){
                    this.walls.add(new Wall(j,i,this.maze.getWalls().get(i).charAt(j)));
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public Key getKey() {
        return key;
    }

    public List<Wall> getWalls() {
        return walls;
    }
}
