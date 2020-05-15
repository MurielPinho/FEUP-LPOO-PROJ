package model;

public class PlayerModel {

    private Position position;
    private char character;
    private String color;

    public PlayerModel(int x, int y){
        this.position = new Position(x,y);
        this.character = '⚇';
        this.color = "#66b3ff";
    }
    public Position getPosition() {
        return position;
    }

    public String getColor() {
        return color;
    }

    public char getCharacter() {
        return character;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}