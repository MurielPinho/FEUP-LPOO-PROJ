package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import model.PlayerModel;

public class PlayerView {
    public void draw(TerminalScreen screen, PlayerModel model){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString(model.getColor()));
        graphics.setBackgroundColor(TextColor.Factory.fromString("#4D4D4D"));
        graphics.putString(new TerminalPosition(model.getPosition().getX(), model.getPosition().getY()), Character.toString(model.getCharacter()), SGR.BOLD);

    }
}
