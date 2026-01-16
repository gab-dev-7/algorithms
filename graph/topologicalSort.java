package graph;

import java.util.*;

public class topologicalSort {

  public static List<Integer> sort(int nodes, List<List<Integer>> adj) {
    int[] inDegree = new int[nodes];

    // Calculate In-degrees
    for (int i = 0; i < nodes; i++) {
      for (int neighbor : adj.get(i)) {
        inDegree[neighbor]++;
      }
    }

    // Add nodes with in-degree 0 to the Queue
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < nodes; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    List<Integer> result = new ArrayList<>();

    // Main Loop (Kahn's)
    while (!queue.isEmpty()) {
      int current = queue.poll();
      result.add(current);

      for (int neighbor : adj.get(current)) {
        inDegree[neighbor] -= 1;
        if (inDegree[neighbor] == 0) {
          queue.add(neighbor);
        }
      }
    }

    // Check for cycles
    if (result.size() != nodes) {
      System.out.println("Cycle detected! No topological sort possible.");
      return new ArrayList<>();
    }

    return result;
  }

  public static void main(String[] args) {
    int nodes = 6;
    List<List<Integer>> adj = new ArrayList<>(nodes);
    for (int i = 0; i < nodes; i++)
      adj.add(new ArrayList<>());

    // Representing dependencies (e.g., 5 and 4 must come before 2)
    adj.get(5).add(2);
    adj.get(5).add(0);
    adj.get(4).add(0);
    adj.get(4).add(1);
    adj.get(2).add(3);
    adj.get(3).add(1);

    System.out.println("Topological Sort: " + sort(nodes, adj));
  }
}
