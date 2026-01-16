package graph;

import java.util.Arrays;

public class floydWarshall {
  final static int INF = 99999; // wo`rks like MAX_VALUE, but avoids overflow

  public static void compute(int[][] graph, int nodes) {
    int[][] dist = new int[nodes][nodes];

    // Initialize the distance matrix with the graph's weights
    for (int i = 0; i < nodes; i++) {
      for (int j = 0; j < nodes; j++) {
        dist[i][j] = graph[i][j];
      }
    }

    // The Triple Loop
    for (int k = 0; k < nodes; k++) {
      for (int i = 0; i < nodes; i++) {
        for (int j = 0; j < nodes; j++) {
          // If i -> k and k -> j both exist
          if (dist[i][k] != INF && dist[k][j] != INF) {
            // Update if path through k is shorter
            if (dist[i][k] + dist[k][j] < dist[i][j]) {
              dist[i][j] = dist[i][k] + dist[k][j];
            }
          }
        }
      }
    }
  }
}
