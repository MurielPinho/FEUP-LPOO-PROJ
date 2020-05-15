import controller.GameController;
import model.GameModel;
import view.GameView;

import java.io.IOException;

public class DungeonEscape {
    public static void main(String[] args) throws IOException {
        GameModel model = new GameModel(85, 45);
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);

        controller.start();
    }
}
