package graph;

import java.util.*;

public class bfs {

  public static void performBFS(List<List<Integer>> adj, int startNode) {
    int n = adj.size();
    boolean[] visited = new boolean[n];

    Queue<Integer> queue = new LinkedList<>();

    visited[startNode] = true;
    queue.add(startNode);

    System.out.print("BFS Traversal: ");

    while (!queue.isEmpty()) {

      // pop first element in the queue
      int current = queue.poll();
      System.out.println(current + " ");

      // get current's neighbors
      for (int neighbor : adj.get(current)) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          queue.add(neighbor);
        }
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Graph: 0 connected to 1, 2; 1 connected to 2;
    // 2 connected to 0, 3; 3 connected to 3
    int nodes = 4;
    List<List<Integer>> adj = new ArrayList<>(nodes);
    for (int i = 0; i < nodes; i++)
      adj.add(new ArrayList<>());

    adj.get(0).add(1);
    adj.get(0).add(2);
    adj.get(1).add(2);
    adj.get(2).add(0);
    adj.get(2).add(3);
    adj.get(3).add(3);

    performBFS(adj, 2); // Starting from node 2
  }
}
