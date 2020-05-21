package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.Wall;
import model.DungeonModel;
import model.GameModel;

import java.time.Instant;
import static java.time.temporal.ChronoUnit.SECONDS;

public class DungeonView {
    public void draw(Screen screen,GameModel model){
        TextGraphics graphics = screen.newTextGraphics();
        DungeonModel dungeon = model.getDungeonModel();
        long time =dungeon.getTimer().until(Instant.now(),SECONDS);
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));
        graphics.putString(new TerminalPosition(57,5), "TIME PLAYED", SGR.UNDERLINE);
        graphics.putString(57,7, Long.toString(time));
        graphics.putString(new TerminalPosition(61,7), "seconds");
        graphics.putString(new TerminalPosition(58,10), "MAZE SIZE",SGR.UNDERLINE);
        graphics.putString(new TerminalPosition(62,12), "X");
        graphics.putString(new TerminalPosition(58,12), Integer.toString(model.getMazeSize()));
        graphics.putString(new TerminalPosition(65,12), Integer.toString(model.getMazeSize()));
        graphics.drawLine(55,0,55,55,'|');
        dungeon.getExit().draw(graphics);
        for(Wall wall : dungeon.getWalls()) {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#D9D9D9"));
            wall.draw(graphics);
            if(!dungeon.isKeyObtained())
                dungeon.getExit().draw(graphics);
        }
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        if (!dungeon.isKeyObtained()) dungeon.getKey().draw(graphics);

    }
}
