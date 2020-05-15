package view;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.GameModel;

import java.io.IOException;

public class GameView {
    public enum ACTION {LEFT, RIGHT, UP, DOWN};


    private final GameModel model;
    private final DungeonView dungeonView;
    private final PlayerView playerView;
    private final TerminalScreen screen;


    public GameView(GameModel model) throws IOException {
        Terminal terminal =
                new DefaultTerminalFactory().setInitialTerminalSize(
                        new TerminalSize(model.getWidth(), model.getHeight())
                ).createTerminal();

        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        this.model = model;

        this.dungeonView = new DungeonView();
        this.playerView = new PlayerView();
    }
    public void draw() throws IOException {
        screen.clear();

        dungeonView.draw(screen, model.getDungeonModel());
        playerView.draw(screen, model.getPlayerModel());

        screen.refresh();
    }

    public ACTION getAction() throws IOException {
        KeyStroke key = screen.readInput();

        while (true) {
            if (key.getKeyType() == KeyType.ArrowLeft) return ACTION.LEFT;
            if (key.getKeyType() == KeyType.ArrowRight) return ACTION.RIGHT;
            if (key.getKeyType() == KeyType.ArrowDown) return ACTION.DOWN;
            if (key.getKeyType() == KeyType.ArrowUp) return ACTION.UP;
        }
    }

}
