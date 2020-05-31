package com.g38.model.maze;

class Edge {

    private final int firstCell;
    private final int secondCell;

    Edge(int firstCell, int secondCell) {
        this.firstCell = firstCell;
        this.secondCell = secondCell;
    }

    int getFirstCell() {
        return firstCell;
    }

    int getSecondCell() {
        return secondCell;
    }
}
