import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private int height;
    private int width;
    private Player player;
    private Maze maze;
    private Ladder ladder;
    private List<Wall> walls = new ArrayList<>();
    private Key key;



    Dungeon(int w, int h, Maze maze){
        this.height = h;
        this.width = w;
        this.maze = maze;
        this.player = this.maze.getPlayer();
        this.walls = this.createWalls();
        this.key = this.maze.getKey();
        this.ladder = this.maze.getLadder();

    }

    public Player getPlayer() {
        return player;
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowUp:
                this.moveHero(player.moveUp());
                break;
            case ArrowDown:
                this.moveHero(player.moveDown());
                break;
            case ArrowLeft:
                this.moveHero(player.moveLeft());
                break;
            case ArrowRight:
                this.moveHero(player.moveRight());
                break;
            default:
                break;
        }
    }

    private void moveHero(Position position){
        if(this.canHeroMove(position)) {
            this.player.setPosition(position);
        }
        if(this.key != null)
            this.retrieveCoin();
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#4d4d4d"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));
        graphics.putString(new TerminalPosition(72,1), "TIME PLAYED",SGR.BOLD,SGR.UNDERLINE);
        graphics.disableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(76,3), "seconds");

        this.player.draw(graphics);
        this.player.draw(graphics);
        if(this.key == null)
            this.ladder.draw(graphics);
        for(Wall wall : walls)
            wall.draw(graphics);
        if(this.key != null)
            this.key.draw(graphics);
    }

    private boolean canHeroMove(Position position){
        for(Wall wall : walls){
            if(wall.getPosition().equals(position))
                return false;
        }
        return true;
    }

    private List<Wall> createWalls() {

        for (int i = 0; i < this.maze.getWalls().size(); i++) {
            for (int j = 0; j < this.maze.getWalls().get(i).length(); j++) {
                if(this.maze.getWalls().get(i).charAt(j) != ' '){
                    this.walls.add(new Wall(j,i,this.maze.getWalls().get(i).charAt(j)));
                }
            }
        }

        return walls;
    }


    public boolean isLevelOver(){
        if(this.player.getPosition().equals(this.ladder.getPosition()) && this.key == null)
            return true;
        return false;
    }

    public void retrieveCoin(){
        if(this.player.getPosition().equals(this.key.getPosition())){
            this.key = null;
        }

    }
}
