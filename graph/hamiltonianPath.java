
import java.util.Arrays;

public class hamiltonianPath {

    private int nodes;
    private int[][] adjMatrix;
    private int[] path;

    public hamiltonianPath(int nodes) {
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

    private boolean solveHP(int pos) {
        if (pos == nodes) {
            return true;
        }

        for (int v = 0; v < nodes; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;
                if (solveHP(pos + 1)) {
                    return true;
                }
                path[pos] = -1;
            }
        }
        return false;
    }

    public int[] findHamiltonianPath() {
        for (int startNode = 0; startNode < nodes; startNode++) {
            Arrays.fill(path, -1);
            path[0] = startNode;
            if (solveHP(1)) {
                return path;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        hamiltonianPath g1 = new hamiltonianPath(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);

        int[] result = g1.findHamiltonianPath();
        if (result == null) {
            System.out.println("No Hamiltonian Path exists in g1");
        } else {
            System.out.print("Hamiltonian Path in g1: ");
            for (int node : result) System.out.print(node + " ");
            System.out.println();
        }
    }
}
