package com.g38.model.maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static com.g38.model.maze.Cell.Type.PASSAGE;

public class PassageTree {

    private final int height;
    private final int width;
    public PassageTree(int height, int width) {
        this.height = (height - 1) / 2;
        this.width = (width - 1) / 2;
    }

    public List<Cell> generate() {
        List<Edge> edges = createEdges();
        Collections.shuffle(edges);
        List<Edge> tree = buildRandomSpanningTree(edges);
        return createPassages(tree);
    }
    private List<Edge> createEdges() {
        List<Edge> edges = new ArrayList<Edge>();
        for (int column = 1; column < width; column++) {
            edges.add(new Edge(toIndex(0, column),
                               toIndex(0, column - 1)));
        }
        for (int row = 1; row < height; row++) {
            edges.add(new Edge(toIndex(row, 0),
                               toIndex(row - 1, 0)));
        }
        for (int row = 1; row < height; row++) {
            for (int column = 1; column < width; column++) {
                edges.add(new Edge(toIndex(row, column),
                                   toIndex(row, column - 1)));
                edges.add(new Edge(toIndex(row, column),
                                   toIndex(row - 1, column)));
            }
        }
        return edges;
    }

    private int toIndex(int row, int column) {
        return row * width + column;
    }

    private List<Edge> buildRandomSpanningTree(List<Edge> edges) {
        DisjointSet disjointSets = new DisjointSet(width * height);
        return edges
            .stream()
            .filter(edge -> connects(edge, disjointSets))
            .collect(toList());
    }

    private boolean connects(Edge edge, DisjointSet disjointSet) {
        return disjointSet.union(edge.getFirstCell(), edge.getSecondCell());
    }

    private List<Cell> createPassages(List<Edge> spanningTree) {
        return spanningTree
            .stream()
            .map(edge -> {
                Cell first = fromIndex(edge.getFirstCell());
                Cell second = fromIndex(edge.getSecondCell());
                return getPassage(first, second);
            }).collect(toList());
    }

    private Cell fromIndex(int index) {
        int row = index / width;
        int column = index % width;
        return new Cell(row, column, PASSAGE);
    }

    private Cell getPassage(Cell first, Cell second) {
        int row = first.getRow() + second.getRow() + 1;
        int column = first.getColumn() + second.getColumn() + 1;
        return new Cell(row, column, PASSAGE);
    }
}
