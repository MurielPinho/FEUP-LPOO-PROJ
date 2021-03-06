package com.g38.view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.g38.model.GameModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView {
    public enum ACTION {LEFT, RIGHT, UP, DOWN, RETURN, NONE, QUIT, ENTER}


    private final GameModel model;
    private final DungeonView dungeonView;
    private final PlayerView playerView;
    private final MenuView menuView;
    private final Screen screen;


    public GameView(GameModel model) throws IOException {
        File fontFile = new File("src/main/resources/square.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loadedFont = font.deriveFont(Font.CENTER_BASELINE, 15);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        DefaultTerminalFactory terminal = new DefaultTerminalFactory();
        terminal.setForceAWTOverSwing(true);
        terminal.setTerminalEmulatorFontConfiguration(fontConfig);
        terminal.setInitialTerminalSize(new TerminalSize(model.getWidth(), model.getHeight()));
        terminal.setTerminalEmulatorTitle("Dungeon Escape");
        screen = terminal.createScreen();
        screen.setCursorPosition(null);
        screen.startScreen();

        this.model = model;

        this.dungeonView = new DungeonView();
        this.playerView = new PlayerView();
        this.menuView = new MenuView();
    }
    public void draw() throws IOException {
        screen.clear();
         if (model.getGameState() == GameModel.State.PLAY){
            dungeonView.draw(screen, model);
            playerView.draw(screen, model);
         }
         else if (model.getGameState() == GameModel.State.MENU){
             menuView.draw(screen,model);
         }


        screen.refresh();
    }

    public ACTION getAction() throws IOException {
        KeyStroke key = screen.readInput();

            if (key.getKeyType() == KeyType.ArrowLeft){
                return ACTION.LEFT;
            }
            if (key.getKeyType() == KeyType.ArrowRight) {
                return ACTION.RIGHT;
            }
            if (key.getKeyType() == KeyType.ArrowDown) {
                return ACTION.DOWN;
            }
            if (key.getKeyType() == KeyType.ArrowUp){
                return ACTION.UP;
            }
            if (key.getKeyType() == KeyType.Escape){
                return ACTION.QUIT;
            }
            if ((key.getKeyType() == KeyType.Character) &&(key.getCharacter() == 'q') ){
                return ACTION.RETURN;
            }
            if (key.getKeyType() == KeyType.Enter){
                return ACTION.ENTER;
            }
            else{
                return ACTION.NONE;
            }


    }

}
