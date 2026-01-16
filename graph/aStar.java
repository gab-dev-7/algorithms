import java.util.*;

public class aStar {

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        int g; // Cost from start to this node
        int h; // Heuristic cost from this node to the end
        int f; // Total estimated cost (g + h)

        Node(int id, int g, int h) {
            this.id = id;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public static List<Integer> findShortestPath(int nodes, List<List<Edge>> adj, int startNode, int goalNode, int[] h) {
        int[] dist = new int[nodes];
        int[] parent = new int[nodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0, h[startNode]));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            if (u == goalNode) {
                // Reconstruct path
                List<Integer> path = new ArrayList<>();
                int at = goalNode;
                while (at != -1) {
                    path.add(at);
                    at = parent[at];
                }
                Collections.reverse(path);
                return path;
            }

            if (current.f > dist[u] + h[u]) {
                continue;
            }

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;
                int newG = dist[u] + weight;

                if (newG < dist[v]) {
                    parent[v] = u;
                    dist[v] = newG;
                    pq.add(new Node(v, newG, h[v]));
                }
            }
        }

        return null; // Path not found
    }

    // Heuristic function (Manhattan distance, for example)
    // This is just a dummy heuristic for demonstration
    public static int[] createHeuristic(int nodes, int goalNode) {
        int[] h = new int[nodes];
        // In a real scenario, this would be based on coordinates
        for (int i = 0; i < nodes; i++) {
            h[i] = Math.abs(i - goalNode);
        }
        return h;
    }


    public static void main(String[] args) {
        int nodes = 5;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(new Edge(1, 4));
        adj.get(0).add(new Edge(2, 2));
        adj.get(1).add(new Edge(3, 5));
        adj.get(2).add(new Edge(1, 1));
        adj.get(2).add(new Edge(3, 8));
        adj.get(2).add(new Edge(4, 10));
        adj.get(3).add(new Edge(4, 2));

        int startNode = 0;
        int goalNode = 4;

        int[] h = createHeuristic(nodes, goalNode);

        List<Integer> path = findShortestPath(nodes, adj, startNode, goalNode, h);

        if (path != null) {
            System.out.println("Shortest path from " + startNode + " to " + goalNode + ": " + path);
        } else {
            System.out.println("No path found from " + startNode + " to " + goalNode);
        }
    }
}