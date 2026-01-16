package graph;

import java.util.*;

public class prim {

  static class Edge {
    int to, weight;

    Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  public static int findMST(int nodes, List<List<Edge>> adj) {
    boolean[] inMST = new boolean[nodes];
    // Priority Queue stores edges as [weight, destinationNode]
    PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

    int totalWeight = 0;

    // Start from node 0
    pq.add(new Edge(0, 0));

    while (!pq.isEmpty()) {
      Edge current = pq.poll();
      int u = current.to;

      // If the node is already in MST, skip it
      if (inMST[u])
        continue;

      // 1. Add node to MST
      inMST[u] = true;
      totalWeight += current.weight;

      // 2. Explore neighbors
      for (Edge neighbor : adj.get(u)) {
        if (!inMST[neighbor.to]) {
          pq.add(neighbor);
        }
      }
    }
    return totalWeight;
  }

  public static void main(String[] args) {
    int nodes = 4;
    List<List<Edge>> adj = new ArrayList<>();
    for (int i = 0; i < nodes; i++) {
      adj.add(new ArrayList<>());
    }

    // Adding edges: u -> v with weight w
    // Since it's an undirected graph, we add the edge both ways
    addEdge(adj, 0, 1, 10);
    addEdge(adj, 0, 2, 6);
    addEdge(adj, 0, 3, 5);
    addEdge(adj, 1, 3, 15);
    addEdge(adj, 2, 3, 4);

    int mstWeight = findMST(nodes, adj);
    System.out.println("Total weight of Minimum Spanning Tree: " + mstWeight);
    // Expected Output: 19 (Edges: 0-3 (5), 3-2 (4), 0-1 (10))
  }

  // Helper method to add undirected edges
  private static void addEdge(List<List<Edge>> adj, int u, int v, int w) {
    adj.get(u).add(new Edge(v, w));
    adj.get(v).add(new Edge(u, w));
  }
}
