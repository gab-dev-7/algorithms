package graph;

import java.util.*;

public class dfs {

  public static void performDFS(List<List<Integer>> adj, int current, boolean[] visited) {
    // Mark the current node as visited
    visited[current] = true;
    System.out.print(current + " ");

    // Explore neighbors
    for (int neighbor : adj.get(current)) {
      // Check if we've been here before
      if (!visited[neighbor]) {
        performDFS(adj, neighbor, visited);
      }
    }
  }

  public static void main(String[] args) {
    int nodes = 5;
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < nodes; i++) {
      adj.add(new ArrayList<>());
    }

    adj.get(0).add(1);
    adj.get(0).add(2);
    adj.get(1).add(3);
    adj.get(2).add(4);

    boolean[] visited = new boolean[nodes];
    System.out.print("DFS Traversal starting from node 0: ");
    performDFS(adj, 0, visited);
  }
}
