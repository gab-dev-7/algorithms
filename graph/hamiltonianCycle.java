package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hamiltonianCycle {

    private int nodes;
    private int[][] adjMatrix;
    private int[] path;

    public hamiltonianCycle(int nodes) {
        this.nodes = nodes;
        adjMatrix = new int[nodes][nodes];
        path = new int[nodes];
        Arrays.fill(path, -1);
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        adjMatrix[v][u] = 1;
    }

    private boolean isSafe(int v, int pos) {
        if (adjMatrix[path[pos - 1]][v] == 0) {
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        return true;
    }

    private boolean solveHC(int pos) {
        if (pos == nodes) {
            return adjMatrix[path[pos - 1]][path[0]] == 1;
        }

        for (int v = 1; v < nodes; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;
                if (solveHC(pos + 1)) {
                    return true;
                }
                path[pos] = -1;
            }
        }
        return false;
    }

    public int[] findHamiltonianCycle() {
        path[0] = 0;
        if (!solveHC(1)) {
            return null;
        }
        return path;
    }

    public static void main(String[] args) {
        hamiltonianCycle g1 = new hamiltonianCycle(5);
        /* (0)--(1)--(2)
            |   / \   |
            |  /   \  |
           (4)-------(3) */
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        g1.addEdge(4, 0);
        g1.addEdge(1, 4);
        g1.addEdge(1, 3);

        int[] result = g1.findHamiltonianCycle();
        if (result == null) {
            System.out.println("No Hamiltonian Cycle exists in g1");
        } else {
            System.out.print("Hamiltonian Cycle in g1: ");
            for (int node : result) System.out.print(node + " ");
            System.out.println(result[0]);
        }
    }
}
