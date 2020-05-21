package model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DungeonModel {

    private int height;
    private int width;
    private Maze maze;
    private Ladder ladder;
    private List<Wall> walls = new ArrayList<>();
    private Key key;
    private Instant timer;
    private boolean keyObtained;
    private boolean mazeDone;



    DungeonModel(Maze maze){
        this.height = 390;
        this.width = 390;
        this.maze = maze;
        this.key = this.maze.getKey();
        this.ladder = this.maze.getLadder();
        this.keyObtained = false;
        this.mazeDone = false;
        this.timer = Instant.now();
        createWalls();

    }
    private void createWalls() {
        model.maze.model.Maze M = new  model.maze.model.Maze(30,30);

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

    public Instant getTimer() { return timer; }

    public boolean isKeyObtained() { return keyObtained; }

    public void setKeyObtained(boolean keyObtained) {
        this.keyObtained = keyObtained;
        this.key.setPosition(new Position(78,5));
    }

    public boolean isMazeDone() {
        return mazeDone;
    }

    public void setMazeDone(boolean mazeDone) {
        this.mazeDone = mazeDone;
    }
}
