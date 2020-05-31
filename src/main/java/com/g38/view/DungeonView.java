package com.g38.view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.g38.model.Wall;
import com.g38.model.DungeonModel;
import com.g38.model.GameModel;

import java.time.Instant;
import static java.time.temporal.ChronoUnit.SECONDS;

public class DungeonView {
    public void draw(Screen screen,GameModel model){
        TextGraphics graphics = screen.newTextGraphics();
        DungeonModel dungeon = model.getDungeonModel();
        long time =dungeon.getTimer().until(Instant.now(),SECONDS);
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));
        graphics.putString(57,3, "TIME PLAYED", SGR.UNDERLINE);
        graphics.putString(57,5, Long.toString(time));
        graphics.putString(61,5, "seconds");
        graphics.putString(58,8, "MAZE SIZE",SGR.UNDERLINE);
        graphics.putString(62,10, "X");
        graphics.putString(58,10, Integer.toString(model.getMazeSize()));
        graphics.putString(65,10, Integer.toString(model.getMazeSize()));
        graphics.drawLine(55,0,55,13,'#');
        graphics.setCharacter(55,14,'#');
        graphics.drawLine(56,14,70,14,'#');
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
