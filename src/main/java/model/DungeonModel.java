package model;

import model.maze.model.MazeGen;
import sun.tools.tree.ThisExpression;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DungeonModel {

    private int height;
    private int width;
    private Exit exit;
    private List<Wall> walls = new ArrayList<>();
    private Key key;
    private Instant timer;
    private boolean keyObtained;
    private boolean mazeDone;
    private MazeGen M;
    private Position center;
    private Position player;


    DungeonModel(int mazeSize){
        this.height = mazeSize+1;
        this.width = mazeSize+1;
        this.center = new Position(27,27);
        this.M = new MazeGen(height,width);
        this.keyObtained = false;
        this.mazeDone = false;
        this.timer = Instant.now();
        createElements();

    }
    private void createElements() {
        int baseW = center.getX()-(width /2);
        int baseH = center.getY()-(height/2);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(this.M.getGrid()[i][j].isWall()){
                    this.walls.add(new Wall(baseW+j,baseH+i));
                }
            }
        }
        this.key = new Key(baseW+width-2,baseH+1);
        this.exit = new Exit(baseW+width-2,baseH+height-1);
        this.player = new Position(baseW+1,baseH+1);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Exit getExit() {
        return exit;
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
        exit.setColor("#BF8040");
    }

    public boolean isMazeDone() {
        return mazeDone;
    }

    public void setMazeDone(boolean mazeDone) {
        this.mazeDone = mazeDone;
    }

    public Position getPlayer() {
        return player;
    }
}
