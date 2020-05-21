package model.maze.model;

import model.maze.algo.generation.PassageTree;
import model.maze.algo.solving.Fugitive;

import java.util.function.Consumer;

import static java.lang.Integer.parseInt;
import static model.maze.model.Cell.Type.PASSAGE;
import static model.maze.model.Cell.Type.WALL;

/**
 * This class encapsulates the internal representation of the model.maze and provides
 * methods for creating, managing and extracting information about it.
 *
 * @author Philipp Malkovsky
 */
public class MazeGen {


    /**
     * The height of the model.maze in cells.
     */
    private final int height;

    /**
     * The width of the model.maze in cells.
     */
    private final int width;

    /**
     * Two-dimensional array of cells representing model.maze.
     */
    private final Cell[][] grid;

    /**
     * Indicates if a method for solving the model.maze has already
     * been called. It is used to prevent recalculation.
     */
    private boolean isSolved = false;

    /**
     * Generates a new model.maze of given height and width.
     *
     * @param height height of a model.maze
     * @param width  width of a model.maze
     */
    public MazeGen(int height, int width) {
        if (height < 3 || width < 3) {
            throw new IllegalArgumentException(
                "Both the height and the width " +
                    "of the model.maze must be at least 3");
        }
        this.height = height;
        this.width = width;
        grid = new Cell[height][width];
        fillGrid();
    }

    /**
     * Generates a new square model.maze of a given size.
     *
     * @param size size of a model.maze
     */
    public MazeGen(int size) {
        this(size, size);
    }

    /**
     * Fills the cells of the new model.maze such that the model.maze becomes
     * simply connected, i.e. containing no loops and no detached walls.
     */
    private void fillGrid() {
        fillAlternately();
        fillGaps();
        makeEntranceAndExit();
        generatePassages();
    }

    /**
     * Creates a new cell with given coordinates and a type in the grid.
     *
     * @param row    a row in the grid
     * @param column a column in the grid
     * @param type   the new cell type
     */
    private void putCell(int row, int column, Cell.Type type) {
        grid[row][column] = new Cell(row, column, type);
    }

    /**
     * Fills every second cell with a passage and the others with a wall.
     * After this method, a model.maze looks like this:
     * <pre>
     * ██████████
     * ██  ██  ██
     * ██████████
     * ██  ██  ██
     * ██████████
     * </pre>
     */
    private void fillAlternately() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i & 1) == 0 || (j & 1) == 0) {
                    putCell(i, j, WALL);
                } else {
                    putCell(i, j, PASSAGE);
                }
            }
        }
    }

    /**
     * If the model.maze has an even height or width it is needed to fill the
     * last row or column of the grid with a wall (or, otherwise, it will
     * contain passages at the outer border).
     * <pre>
     * ████████████
     * ██  ██  ██
     * ████████████
     * ██  ██  ██
     * ████████████
     * ██  ██  ██
     * </pre>
     * becomes
     * <pre>
     * ████████████
     * ██  ██  ████
     * ████████████
     * ██  ██  ████
     * ████████████
     * ████████████
     * </pre>
     */
    private void fillGaps() {
        if (height % 2 == 0) wallLastRow();
        if (width % 2 == 0) wallLastColumn();
    }

    /**
     * Fills the last column in the grid with a wall.
     */
    private void wallLastColumn() {
        for (int i = 0; i < height; i++)
            putCell(i, width - 1, WALL);
    }

    /**
     * Fills the last row in the grid with a wall.
     */
    private void wallLastRow() {
        for (int i = 0; i < width; i++)
            putCell(height - 1, i, WALL);
    }

    /**
     * Calculates the index of the passage in the last row. For a model.maze
     * with an odd (1) and even (2) width its indices differ:
     * <pre>
     * (1) ██████  ██
     * (2) ██████  ████
     * </pre>
     *
     * @return the index of the passage in the last row
     */
    private int getExitColumn() {
        return width - 3 + width % 2;
    }

    /**
     * Puts entrance and exit passages to upper left and lower right
     * corners. For example:
     * <pre>
     * ████████████
     * ██  ██  ████
     * ████████████
     * ██  ██  ████
     * ████████████
     * ████████████
     * </pre>
     * becomes
     * <pre>
     * ██  ████████
     * ██  ██  ████
     * ████████████
     * ██  ██  ████
     * ██████  ████
     * ██████  ████
     * </pre>
     */
    private void makeEntranceAndExit() {
        //putCell(0, 1, PASSAGE);
        putCell(height - 1, getExitColumn(), PASSAGE);
        if (height % 2 == 0)
            putCell(height - 2, getExitColumn(), PASSAGE);
    }

    /**
     * Creates random passages between isolated passage cells such
     * that every cell is connected to the other in one way and
     * has no cycles. For example:
     * <pre>
     * ██  ██████
     * ██  ██  ██
     * ██████████
     * ██  ██  ██
     * ██████  ██
     * </pre>
     * can become
     * <pre>
     * ██  ██████
     * ██      ██
     * ██████  ██
     * ██      ██
     * ██████  ██
     * </pre>
     *
     * @see PassageTree
     */
    private void generatePassages() {
        new PassageTree(height, width)
            .generate()
            .forEach(putCell());
    }

    /**
     * Puts a cell in the corresponding place in grid.
     *
     * @return lambda to put a cell
     */
    private Consumer<Cell> putCell() {
        return cell -> grid[cell.getRow()][cell.getColumn()] = cell;
    }

    /**
     * Finds a path in the model.maze from its entrance to its exit.
     * For example:
     * <pre>
     * ██░░██████████
     * ██░░░░░░██  ██
     * ██████░░██  ██
     * ██    ░░    ██
     * ██████░░██████
     * ██    ░░░░░░██
     * ██████████░░██
     * </pre>
     *
     * <p>If this method is called several times, the path is not
     * recalculated. It is stored in the grid so it is returned
     * immediately.</p>
     *
     * @return string representation of the model.maze containing a path
     * @see Fugitive
     */
    public String findEscape() {
        if (!isSolved) {
            new Fugitive(grid, getEntrance(), getExit())
                .findEscape()
                .forEach(putCell());
            isSolved = true;
        }
        return toString(true);
    }

    /**
     * Return the entrance cell.
     *
     * @return the entrance cell
     */
    private Cell getEntrance() {
        return grid[0][1];
    }

    /**
     * Return the exit cell.
     *
     * @return the exit cell
     */
    private Cell getExit() {
        return grid[height - 1][getExitColumn()];
    }

    /**
     * Return the string representation of the grid. The path
     * from the entrance to the exit can be displayed if it
     * is already found and {@code showEscape} is {@code true}.
     * The path is found on demand.
     *
     * <p>
     * For example:<br>
     * if path is already found and {@code showEscape} is
     * {@code true}
     * <pre>
     * ██░░██████████
     * ██░░░░░░██  ██
     * ██████░░██  ██
     * ██    ░░    ██
     * ██████░░██████
     * ██    ░░░░░░██
     * ██████████░░██
     * </pre>
     * if {@code showEscape} is {@code false}
     * <pre>
     * ██  ██████████
     * ██      ██  ██
     * ██████  ██  ██
     * ██          ██
     * ██████  ██████
     * ██          ██
     * ██████████  ██
     * </pre>
     *
     * @param showEscape show path or not
     * @return string representation of the model.maze
     */
    private String toString(boolean showEscape) {
        StringBuilder sb = new StringBuilder();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.isWall()) {
                    sb.append("██");
                } else if (showEscape && cell.isEscape()) {
                    sb.append("▓▓");
                } else {
                    sb.append("  ");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Return the string representation of the grid.
     * The path is never displayed. For example:
     * <pre>
     * ██  ██████████
     * ██      ██  ██
     * ██████  ██  ██
     * ██          ██
     * ██████  ██████
     * ██          ██
     * ██████████  ██
     * </pre>
     *
     * @return string representation of the model.maze
     */
    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Parses a serialized model.maze representation and
     * constructs a new model.maze from it.
     * <p>
     * MazeGen is serialized in the following form:
     * <pre>
     * height width
     * cell[0][0] cell[0][1] ... cell[0][width - 1]
     * cell[1][0] cell[1][1] ... cell[1][width - 1]
     * ...
     * cell[height - 1][0] cell[height - 1][1] ... cell[height - 1][width - 1],
     * </pre>
     * where cell[i][j] is 1 if the cell type is a wall and 0 if
     * the cell type is a passage. The escape path is not serialized.
     * <br>
     * For example:
     * <pre>
     * 5 7
     * 1 0 1 1 1 1 1
     * 1 0 0 0 1 0 1
     * 1 1 1 0 1 0 1
     * 1 0 0 0 0 0 1
     * 1 1 1 1 1 0 1
     * </pre>
     * (a serialized form)<br>
     * corresponds to
     * <pre>
     * ██  ██████████
     * ██      ██  ██
     * ██████  ██  ██
     * ██          ██
     * ██████████  ██
     * </pre>
     * (a regular form)<br>
     *
     * @param str a serialized model.maze representation
     * @return a new model.maze from a given string
     */
    public static MazeGen load(String str) {
        try {
            String[] whole = str.split("\n");
            String[] size = whole[0].split(" ");
            int height = parseInt(size[0]);
            int width = parseInt(size[1]);
            Cell[][] grid = new Cell[height][width];
            for (int i = 0; i < height; i++) {
                String[] row = whole[i + 1].split(" ");
                for (int j = 0; j < width; j++)
                    grid[i][j] = new Cell(
                        i, j, intToType(parseInt(row[j]))
                    );
            }
            return new MazeGen(height, width, grid);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Cannot load the model.maze. " +
                    "It has an invalid format"
            );
        }
    }

    /**
     * Creates a model.maze instance with given height, width and grid.
     *
     * @param height height of a model.maze
     * @param width  width of a model.maze
     * @param grid   grid of cells of a model.maze
     */
    private MazeGen(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    /**
     * Converts 1 to the WALL and 0 to the PASSAGE.
     * The path is not serialized so there are only 2 choices.
     *
     * @param val value to convert
     * @return converted WALL or PASSAGE
     */
    private static Cell.Type intToType(int val) {
        return val == 1 ? WALL : PASSAGE;
    }

    /**
     * Converts the model.maze to the serialized form.
     * <p>
     * MazeGen is serialized in the following form:
     * <pre>
     * height width
     * cell[0][0] cell[0][1] ... cell[0][width - 1]
     * cell[1][0] cell[1][1] ... cell[1][width - 1]
     * ...
     * cell[height - 1][0] cell[height - 1][1] ... cell[height - 1][width - 1],
     * </pre>
     * where cell[i][j] is 1 if the cell type is a wall and 0 if
     * the cell type is a passage. The escape path is not serialized.
     * <br>
     * For example:
     * <pre>
     * 5 7
     * 1 0 1 1 1 1 1
     * 1 0 0 0 1 0 1
     * 1 1 1 0 1 0 1
     * 1 0 0 0 0 0 1
     * 1 1 1 1 1 0 1
     * </pre>
     * (a serialized form)<br>
     * corresponds to
     * <pre>
     * ██  ██████████
     * ██      ██  ██
     * ██████  ██  ██
     * ██          ██
     * ██████████  ██
     * </pre>
     * (a regular form)<br>
     *
     * @return string in a serialized form
     */
    public String export() {
        StringBuilder sb = new StringBuilder();
        sb.append(height).append(' ')
          .append(width).append('\n');
        for (Cell[] row : grid) {
            for (Cell cell : row)
                sb.append(typeToInt(cell))
                  .append(' ');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Converts WALL to the 1 and PASSAGE to the 0.
     * The path is not serialized so there are only 2 choices.
     *
     * @param cell value to convert
     * @return converted 1 or 0
     */
    private int typeToInt(Cell cell) {
        return cell.isWall() ? 1 : 0;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
