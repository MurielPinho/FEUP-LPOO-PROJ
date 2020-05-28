package view;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import model.GameModel;

public class MenuView {

    public void draw(Screen screen, GameModel model){
        TextGraphics graphics = screen.newTextGraphics();
        GameModel.menuState mState = model.getMenuSelection();
        GameModel.menuState cMenu = model.getCurrentMenu();
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));
        if (cMenu == GameModel.menuState.INSTRUCTIONS)
        {
            drawInstructions(graphics);
        }
        else   {
        graphics.putString(29,35, "    PLAY    ");
        graphics.putString(29,39, "INSTRUCTIONS");
        graphics.putString(29,43, "    QUIT    ");
        drawSelector(graphics,mState);
        drawTitle(graphics);
        }
    }

    public void drawInstructions(TextGraphics graphics){

        int c = 0;
        graphics.putString(29,10, "INSTRUCTIONS");
        graphics.putString(25,40, "PRESS ENTER TO RETURN",SGR.BLINK,SGR.UNDERLINE);
        for (int i = 0; i <16 ; i++) {
            for (int j = 0; j < 41; j++) {
                graphics.putString(j + 15, i + 15, Character.toString(instructionsMatrix[c]), SGR.BOLD);
                c++;
            }
        }
    }

    public void drawSelector(TextGraphics graphics, GameModel.menuState mState){
            if (mState == GameModel.menuState.PLAY)
            {
                graphics.putString(24,35, ">>");
            }
            else if(mState == GameModel.menuState.INSTRUCTIONS){
                graphics.putString(24,39, ">>");
            }
            else {
                graphics.putString(24,43, ">>");
            }
    }

    public void drawTitle(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#D9D9D9"));
        int c = 0;
        for (int i = 0; i <11 ; i++) {
            for (int j = 0; j < 34; j++) {
                if (titleMatrix[c] == '1')
                    graphics.putString(j + 18, i + 8, Character.toString(titleMatrix[c]), SGR.BOLD);
                c++;
            }
        }
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
    }

    char[] titleMatrix = (  "1110010010100100110011110011001001" +
                            "1001010010110101000010000100101101" +
                            "1001010010101101011011100100101011" +
                            "1001010010100101001010000100101001" +
                            "1110001100100100110011110011001001" +
                            "0000000000000000000000000000000000" +
                            "0011110011100110001100111001111000" +
                            "0010000100001001010010100101000000" +
                            "0011100011001000011110111001110000" +
                            "0010000000101001010010100001000000" +
                            "0011110111000110010010100001111000").toCharArray();

    char[] instructionsMatrix = (   "   You wake up imprisoned in a dungeon   " +
                                    "           with endless mazes,           " +
                                    "you must get through all mazes to escape." +
                                    " Each maze contains one key that must be " +
                                    "       acquired to unlock an exit.       " +
                                    "     After passing through all mazes     " +
                                    "     you are free from  the DUNGEON.     " +
                                    "                                         " +
                                    "                                         " +
                                    "                                         " +
                                    "                                         " +
                                    "                                         " +
                                    "                                         " +
                                    "  Navigate the maze with the arrow keys, " +
                                    "  While ingame press Q to acess the menu," +
                                    "     and ESC to close the application    ").toCharArray();
}
