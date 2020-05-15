package model;

public class Wall extends Element {
    public Wall(int x, int y, char ch){
        this.position = new Position(x,y);
        this.ch = ch;
        this.color = "#FFFFFF";
    }
}
