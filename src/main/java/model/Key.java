package model;

public class Key extends Element {
    public Key(int x, int y){
        this.position = new Position(x,y);
        this.color = "#FFFF33";
        this.ch = '¶';
    }
}
