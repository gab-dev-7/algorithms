
import java.util.*;

public class johnson {

    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static int[][] allPairsShortestPaths(int nodes, List<Edge> edges) {
        // Step 1: Add a new source node s and edges (s, v) with weight 0
        int s = nodes;
        List<Edge> augmentedEdges = new ArrayList<>(edges);
        for (int i = 0; i < nodes; i++) {
            augmentedEdges.add(new Edge(s, i, 0));
        }

        // Step 2: Run Bellman-Ford from s to find potentials h(v)
        int[] h = new int[nodes + 1];
        Arrays.fill(h, 999999);
        h[s] = 0;

        for (int i = 0; i < nodes; i++) {
            for (Edge edge : augmentedEdges) {
                if (h[edge.from] != 999999 && h[edge.from] + edge.weight < h[edge.to]) {
                    h[edge.to] = h[edge.from] + edge.weight;
                }
            }
        }

        // Check for negative cycles
        for (Edge edge : augmentedEdges) {
            if (h[edge.from] != 999999 && h[edge.from] + edge.weight < h[edge.to]) {
                System.out.println("Graph contains a negative weight cycle");
                return null;
            }
        }

        // Step 3: Reweight edges: w'(u,v) = w(u,v) + h(u) - h(v)
        List<List<int[]>> adj = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) adj.add(new ArrayList<>());
        for (Edge edge : edges) {
            int newWeight = edge.weight + h[edge.from] - h[edge.to];
            adj.get(edge.from).add(new int[]{edge.to, newWeight});
        }

        // Step 4: Run Dijkstra from each node
        int[][] dist = new int[nodes][nodes];
        for (int i = 0; i < nodes; i++) {
            dist[i] = dijkstra(nodes, adj, i);
            // Correct the distances: d(u,v) = d'(u,v) - h(u) + h(v)
            for (int j = 0; j < nodes; j++) {
                if (dist[i][j] != 999999) {
                    dist[i][j] = dist[i][j] - h[i] + h[j];
                }
            }
        }

        return dist;
    }

    private static int[] dijkstra(int nodes, List<List<int[]>> adj, int src) {
        int[] dist = new int[nodes];
        Arrays.fill(dist, 999999);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];

            if (d > dist[u]) continue;

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int nodes = 3;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, -2));
        edges.add(new Edge(1, 2, -1));
        edges.add(new Edge(2, 0, 4));

        int[][] results = allPairsShortestPaths(nodes, edges);
        if (results != null) {
            for (int i = 0; i < nodes; i++) {
                System.out.println("Distances from " + i + ": " + Arrays.toString(results[i]));
            }
        }
    }
}
