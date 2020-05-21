/*
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import model.MazeGen;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;

class Game {
    private Screen screen;
    private KeyStroke key;
    private TextGraphics graphics;
    private Difficulty difficulty;
    private List<MazeGen> mazes = new ArrayList<>();
    private Instant timer;
    private Long time;
    private List<Integer> timeList = new ArrayList<>();
    private boolean gameStatus=false;
    private Integer scoreValue;


    Game() {
        try {
            DefaultTerminalFactory terminal = new DefaultTerminalFactory();
            terminal.setTerminalEmulatorTitle("Dungeon Escape");
            terminal.setInitialTerminalSize(new TerminalSize(85,45));
            screen = terminal.createScreen();
            screen.setCursorPosition(null);
            screen.startScreen();
            graphics = screen.newTextGraphics();
            this.mainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void mainMenu() throws Exception {
        int selector;
        if(!timeCounter.isAlive()) timeCounter.start();
        screen.clear();
        graphics.clearModifiers();
        readScreen("main","#D9D9D9","#4D4D4D");
        graphics.putString(34,30, ">>>",SGR.BLINK);
        screen.refresh();
        graphics.clearModifiers();
        selector=menuChooser(30,4);
        screen.clear();
        switch (selector) {
            case 0:
                break;
            case 1:
                howTo();
                screen.refresh();
                pressContinue();
                mainMenu();
                break;
            case 2:
                readScreen("about","#D9D9D9","#4D4D4D");
                screen.refresh();
                pressContinue();
                mainMenu();
                break;
            default:
                this.screen.close();
                System.exit(0);
                break;
        }
        screen.clear();
        readScreen("difficulty","#D9D9D9","#4D4D4D");
        graphics.putString(34,14, ">>>",SGR.BLINK);
        graphics.disableModifiers(SGR.BLINK);

        screen.refresh();
        selector=menuChooser(14,4);

        switch (selector) {
            case 0:
                this.difficulty = Difficulty.EASY;
                break;
            case 1:
                this.difficulty = Difficulty.MEDIUM;
                break;
            case 2:
                this.difficulty = Difficulty.HARD;
                break;
            default:
                mainMenu();
        }

        this.loadMazes();
        timer = Instant.now();
        timeList.clear();
        gameStatus = true;
        for (MazeGen model.maze : mazes) {
            this.dungeon = new Dungeon(390, 390, model.maze);
            gameStatus = this.run();
            timeList.add(time.intValue());
            timer = Instant.now();
            if (!gameStatus) break;
        }
        screen.clear();
        if(gameStatus) {
            scoreValue=0;
            switch (difficulty){
                case EASY:
                    scoreValue+=(5000-(timeList.get(0)*10));
                    break;
                case MEDIUM:
                    scoreValue+=(5000-(timeList.get(0)*10));
                    scoreValue+=(10000-(timeList.get(1)*10));
                    scoreValue+=(15000-(timeList.get(2)*10));
                    break;
                case HARD:
                    scoreValue+=(5000-(timeList.get(0)*10));
                    scoreValue+=(10000-(timeList.get(1)*10));
                    scoreValue+=(15000-(timeList.get(2)*10));
                    scoreValue+=(20000-(timeList.get(3)*10));
                    scoreValue+=(25000-(timeList.get(4)*10));
                    break;
            }
            playerWon();
        } else playerLost();
        gameStatus=false;
        pressContinue();
        graphics.clearModifiers();
        screen.refresh();
        this.mazes.clear();
        this.mainMenu();
    }

    private void draw() throws IOException {
        screen.clear();
        time =timer.until(Instant.now(),SECONDS);
        this.dungeon.draw(this.graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));
        graphics.putString(72,3, Long.toString(time));
        screen.refresh();
    }
    private boolean run() {
      try {
          draw();
        do {
                this.key = screen.readInput();
                this.processKey(key);
                if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                  return false;
          } while(!dungeon.isLevelOver());

      } catch (IOException e){
          e.printStackTrace();
      }
        return true;
    }

    private void processKey(KeyStroke key) throws IOException {
        this.dungeon.processKey(key);
        this.draw();
    }


    private void playerLost() throws IOException {
        readScreen("lose","#D9D9D9","#841717");

    }

    private void playerWon() throws IOException {
        readScreen("win","#D9D9D9","#006600");
        graphics.putString(37,25, "Your Score:",SGR.BOLD,SGR.UNDERLINE);
        graphics.clearModifiers();
        graphics.putString(40,27, scoreValue.toString());


    }

    private void howTo() throws IOException {
        readScreen("howto","#D9D9D9","#4D4D4D");

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.setCharacter(44,18,'¶');
        graphics.setForegroundColor(TextColor.Factory.fromString("#8B4513"));
        graphics.setCharacter(24,22,'ꍞ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#D9D9D9"));


    }

    private void pressContinue() throws IOException {
        graphics.putString(31,34, "Press Enter to Continue",SGR.BLINK);
        screen.refresh();
        do{
            this.key = screen.readInput();
        }while(key.getKeyType()!=KeyType.Enter);
    }
    private Integer menuChooser(int row,int n) throws IOException {
    n--;
    int selector = 0;
        do {
            this.key = screen.readInput();
            graphics.putString(34,row+selector*2, "   ");
            if(key.getKeyType()==KeyType.ArrowDown) {
                if(selector==n) selector=0;
                else selector++;
            }
            else if(key.getKeyType()==KeyType.ArrowUp)
            {
                if(selector==0) selector=n;
                else selector--;
            }
            graphics.putString(34,row+selector*2, ">>>",SGR.BLINK);
            graphics.disableModifiers(SGR.BLINK);
            screen.refresh();
        }while(key.getKeyType()!=KeyType.Enter);
        return selector;
    }

    private void loadMazes(){
        int levels = 0;
        if(this.difficulty == Difficulty.EASY)
            levels = 1;
        else if(this.difficulty == Difficulty.MEDIUM)
            levels = 3;
        else if(this.difficulty == Difficulty.HARD)
            levels = 5;

        for(int i = 1; i <= levels; i++)
            this.mazes.add(new MazeGen(i));
    }
    private void readScreen(String filename,String fore,String back) throws IOException {
        List<String> lines = new ArrayList<>();
        String path = "src/main/resources/displays/"+filename+".txt";
        BufferedReader br = openFile(path);
        String line;
        while ((line = br.readLine()) != null)
            lines.add(line);
        graphics.setForegroundColor(TextColor.Factory.fromString(fore));
        graphics.setBackgroundColor(TextColor.Factory.fromString(back));
        graphics.clearModifiers();
        graphics.fill(' ');

        for (int i = 0; i <lines.size(); i++) {
            graphics.putString(0,i, lines.get(i));

        }
        screen.refresh();

    }

    static BufferedReader openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new BufferedReader(new FileReader(file));
    }

    enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }
    private Thread timeCounter = new Thread(new Runnable() {

        @Override
        public void run() {
            while (true) {
                try {
                    if (gameStatus){
                        draw();
                    }
                    Thread.sleep(1000);



                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

}
*/
