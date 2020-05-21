package model;
import com.googlecode.lanterna.Symbols;

import static com.googlecode.lanterna.Symbols.*;

public class PlayerModel {

    private Position position;
    private final char character;
    private final String color;

    public PlayerModel(Position player){
        this.position = player;
        this.character = '@';
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
