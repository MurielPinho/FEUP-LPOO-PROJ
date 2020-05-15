package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Maze {
    private List<String> walls = new ArrayList<>();
    private Ladder ladder;
    private Key key;
    private PlayerModel player;

    public Maze(int n){
        try {
            List<Integer> positions;
            positions=this.readMaze(n);
            this.ladder = new Ladder(positions.get(0),positions.get(1));
            this.key = new Key(positions.get(2), positions.get(3));
            this.player = new PlayerModel(positions.get(4),positions.get(5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Integer> readMaze(int n) throws Exception {
        List<Integer> positions = new Vector<>();
        String path = "src/main/resources/mazes/maze"+n+".txt";
        BufferedReader br = openFile(path);
        String st=br.readLine();
        String[] parts = st.split(",");
        for (String x:parts) {
        positions.add(Integer.parseInt(x));
        }
        while ((st = br.readLine()) != null)
        this.walls.add(st);
        return positions;
    }
    static BufferedReader openFile(String path) throws FileNotFoundException {
        File file = new File(path);
        return new BufferedReader(new FileReader(file));
    }

    public List<String> getWalls() {
        return walls;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public Key getKey() {
        return key;
    }

    public PlayerModel getPlayer() {
        return player;
    }


}
