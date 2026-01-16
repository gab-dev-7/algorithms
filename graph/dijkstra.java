import java.util.*;

public class dijkstra {

  static class Node implements Comparable<Node> {
    int id, distance;

    Node(int id, int distance) {
      this.id = id;
      this.distance = distance;
    }

    public int compareTo(Node other) {
      return Integer.compare(this.distance, other.distance);
    }
  }

  public static int[] findShortestPaths(int nodes, List<List<prim.Edge>> adj, int startNode) {
    int[] dist = new int[nodes];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[startNode] = 0;

    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.add(new Node(startNode, 0));

    while (!pq.isEmpty()) {
      Node current = pq.poll();
      int u = current.id;

      // If we found a better path already -> skip
      if (current.distance > dist[u])
        continue;

      for (prim.Edge edge : adj.get(u)) {
        int v = edge.to;
        int weight = edge.weight;

        if ((dist[u] + weight) < dist[v]) {
          dist[v] = weight + dist[u];
          pq.add(new Node(v, dist[v]));
        }
      }
    }
    return dist;
  }
}
