package model;

public class Ladder extends Element {
    public Ladder(int x, int y){
        this.position = new Position(x,y);
        this.ch = 'X';
        this.color = "#BF8040";
    }
}
