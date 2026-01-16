import java.util.*;

public class bellmanFord {

  static class Edge {
    int u, v, weight;

    Edge(int u, int v, int weight) {
      this.u = u;
      this.v = v;
      this.weight = weight;
    }
  }

  public static void findShortestPaths(int nodes, List<Edge> edges, int startNode) {
    int[] dist = new int[nodes];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[startNode] = 0;

    // Relax all edges (nodes - 1) times
    for (int i = 1; i < nodes; i++) {
      for (Edge edge : edges) {
        if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight < dist[edge.v]) {
          dist[edge.v] = dist[edge.u] + edge.weight;
        }
      }
    }

    // 2. Check for negative cycles
    for (Edge edge : edges) {
      if (dist[edge.u] != Integer.MAX_VALUE && dist[edge.u] + edge.weight < dist[edge.v]) {
        System.out.println("There is a negative cycle in this graph!");
      }
    }
  }
}
