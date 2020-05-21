package model.maze;

public class Cell {


    public enum Type {
        PASSAGE,
        WALL
    }

    private final int row;

    private final int column;

    private final Type type;

    public Cell(int row, int column, Type type) {
        this.row = row;
        this.column = column;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isPassage() {
        return type == Type.PASSAGE;
    }

    public boolean isWall() {
        return type == Type.WALL;
    }




}
