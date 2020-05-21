package model.maze;

import static java.util.stream.IntStream.range;

public class DisjointSet {

    private final int[] parent;

    private final int[] rank;

    private int size;

    public DisjointSet(int size) {
        this.size = size;
        parent = new int[size];
        rank = new int[size];
        range(0, size).forEach(this::makeSet);
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    public int getSize() {
        return size;
    }

    public int find(int i) {
        if (i != parent[i])
            parent[i] = find(parent[i]);
        return parent[i];
    }

    public boolean union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        if (iRoot == jRoot)
            return false;
        if (rank[iRoot] < rank[jRoot]) {
            parent[iRoot] = jRoot;
        } else {
            parent[jRoot] = iRoot;
            if (rank[iRoot] == rank[jRoot])
                rank[iRoot]++;
        }
        size--;
        return true;
    }
}
