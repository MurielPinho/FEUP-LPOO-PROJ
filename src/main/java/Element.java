import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

abstract public class Element {
    protected Position position;
    char ch;
    String color;

    Element(){
        this.position = new Position(10,10);
    }

    Position getPosition() {
        return position;
    }

    void setPosition(Position position) {
        this.position = position;
    }

    void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString(this.color));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), Character.toString(this.ch), SGR.BOLD);
    }
}
