package graph;

import java.util.ArrayList;
import java.util.List;

public class boruvka {

    static class Edge {
        int u, v, weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    }

    static class UnionFind {
        private int[] parent;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); // Path compression
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                count--;
            }
        }

        public int count() {
            return count;
        }
    }


    public static List<Edge> boruvkaMST(int nodes, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(nodes);
        
        while (uf.count() > 1 && mst.size() < nodes -1) {
            // cheapest[i] stores the cheapest edge for component with root i
            Edge[] cheapest = new Edge[nodes];

            for (Edge edge : edges) {
                int rootU = uf.find(edge.u);
                int rootV = uf.find(edge.v);

                if (rootU == rootV) {
                    continue;
                }

                if (cheapest[rootU] == null || edge.weight < cheapest[rootU].weight) {
                    cheapest[rootU] = edge;
                }
                if (cheapest[rootV] == null || edge.weight < cheapest[rootV].weight) {
                    cheapest[rootV] = edge;
                }
            }

            for (int i = 0; i < nodes; i++) {
                Edge edge = cheapest[i];
                if (edge != null) {
                    int rootU = uf.find(edge.u);
                    int rootV = uf.find(edge.v);
                    if (rootU != rootV) {
                        mst.add(edge);
                        uf.union(rootU, rootV);
                    }
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        int nodes = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> mst = boruvkaMST(nodes, edges);
        int totalWeight = 0;
        System.out.println("Edges in MST:");
        for (Edge edge : mst) {
            System.out.println(edge.u + " - " + edge.v + " : " + edge.weight);
            totalWeight += edge.weight;
        }
        System.out.println("Total MST weight: " + totalWeight); // Expected: 19
    }
}
