import java.util.*;

public class kruskal {
  static class Edge implements Comparable<Edge> {
    int u, v, weight;

    Edge(int u, int v, int weight) {
      this.u = u;
      this.v = v;
      this.weight = weight;
    }

    public int compareTo(Edge other) {
      return this.weight - other.weight;
    }
  }

  // Helper class for Union-Find
  static class DSU {
    int[] parent;

    DSU(int n) {
      parent = new int[n];
      for (int i = 0; i < n; i++)
        parent[i] = i;
    }

    int find(int i) {
      if (parent[i] == i)
        return i;
      return parent[i] = find(parent[i]);
    }

    void union(int i, int j) {
      int rootI = find(i);
      int rootJ = find(j);
      if (rootI != rootJ)
        parent[rootI] = rootJ;
    }
  }

  public static int findMST(int nodes, List<Edge> edges) {
    // 1. Sort edges by weight
    Collections.sort(edges);

    DSU dsu = new DSU(nodes);
    int totalWeight = 0;
    int edgesCount = 0;

    for (Edge edge : edges) {
      // if the roots are != they are in different components
      if (dsu.find(edge.u) != dsu.find(edge.v)) {
        dsu.union(edge.u, edge.v);
        totalWeight += edge.weight;
        edgesCount++;
      }
      return totalWeight;
    }
  }
}
