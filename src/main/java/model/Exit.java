package model;

public class Exit extends Element {
    public Exit(int x, int y){
        this.position = new Position(x,y);
        this.ch = 'X';
        this.color = "#D9D9D9";
    }
}
