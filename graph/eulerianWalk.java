package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class eulerianWalk {

    private int nodes;
    private List<List<Integer>> adj;

    public eulerianWalk(int nodes) {
        this.nodes = nodes;
        adj = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    public List<Integer> findEulerianWalk() {
        int oddDegreeNodes = 0;
        int startNode = -1;

        for (int i = 0; i < nodes; i++) {
            if (adj.get(i).size() % 2 != 0) {
                oddDegreeNodes++;
                if (startNode == -1) startNode = i;
            }
        }

        if (oddDegreeNodes != 0 && oddDegreeNodes != 2) {
            return null; // No Eulerian Walk possible
        }

        // If no odd degree nodes, it's an Eulerian Cycle, start from any node with an edge
        if (startNode == -1) {
            for (int i = 0; i < nodes; i++) {
                if (!adj.get(i).isEmpty()) {
                    startNode = i;
                    break;
                }
            }
        }
        
        if (startNode == -1) return new ArrayList<>(); // Empty graph

        List<Integer> walk = new ArrayList<>();
        List<List<Integer>> tempAdj = new ArrayList<>(nodes);
        for (int i = 0; i < nodes; i++) {
            tempAdj.add(new ArrayList<>(adj.get(i)));
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            int u = stack.peek();
            if (tempAdj.get(u).isEmpty()) {
                walk.add(stack.pop());
            } else {
                int v = tempAdj.get(u).get(0);
                stack.push(v);
                tempAdj.get(u).remove(Integer.valueOf(v));
                tempAdj.get(v).remove(Integer.valueOf(u));
            }
        }

        // Connectivity check: The walk should include all edges. 
        // We check if any node with edges was not visited.
        // Simplified check: result size should be (totalEdges + 1)
        int totalEdges = 0;
        for(List<Integer> list : adj) totalEdges += list.size();
        totalEdges /= 2;

        if (walk.size() != totalEdges + 1) {
            return null; // Graph is not connected enough
        }

        Collections.reverse(walk);
        return walk;
    }

    public static void main(String[] args) {
        // Case 1: Eulerian Path exists (0-1-2-0-3)
        eulerianWalk g1 = new eulerianWalk(4);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(0, 3);
        System.out.println("Eulerian Walk g1: " + g1.findEulerianWalk());

        // Case 2: Eulerian Cycle exists
        eulerianWalk g2 = new eulerianWalk(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        System.out.println("Eulerian Walk g2 (Cycle): " + g2.findEulerianWalk());

        // Case 3: No Eulerian Walk
        eulerianWalk g3 = new eulerianWalk(4);
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(0, 3);
        g3.addEdge(1, 3);
        g3.addEdge(2, 3);
        System.out.println("Eulerian Walk g3: " + g3.findEulerianWalk());
    }
}
