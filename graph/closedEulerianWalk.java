
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class closedEulerianWalk {

    private int nodes;
    private List<List<Integer>> adj;

    public closedEulerianWalk(int nodes) {
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

    private boolean hasEulerianCycle() {
        // Check if all vertices with non-zero degree are connected
        int startNode = -1;
        for (int i = 0; i < nodes; i++) {
            if (adj.get(i).size() > 0) {
                startNode = i;
                break;
            }
        }
        if (startNode == -1) return true; // Empty graph

        boolean[] visited = new boolean[nodes];
        dfs(startNode, visited);

        for (int i = 0; i < nodes; i++) {
            if (adj.get(i).size() > 0 && !visited[i]) {
                return false; // Not connected
            }
        }

        // Check if all vertices have even degree
        for (int i = 0; i < nodes; i++) {
            if (adj.get(i).size() % 2 != 0) {
                return false;
            }
        }
        return true;
    }
    
    private void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    public List<Integer> findEulerianCycle() {
        if (!hasEulerianCycle()) {
            return null;
        }

        List<Integer> cycle = new ArrayList<>();
        if (nodes == 0) return cycle;

        // Find a starting node
        int startNode = 0;
        for(int i=0; i<nodes; i++){
            if(!adj.get(i).isEmpty()){
                startNode = i;
                break;
            }
        }


        List<List<Integer>> tempAdj = new ArrayList<>(nodes);
        for(int i=0; i<nodes; i++){
            tempAdj.add(new ArrayList<>(adj.get(i)));
        }
        
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        
        while(!stack.isEmpty()){
            int u = stack.peek();
            if(tempAdj.get(u).isEmpty()){
                cycle.add(stack.pop());
            } else {
                int v = tempAdj.get(u).get(0);
                stack.push(v);
                tempAdj.get(u).remove(Integer.valueOf(v));
                tempAdj.get(v).remove(Integer.valueOf(u));
            }
        }

        Collections.reverse(cycle);
        return cycle;
    }


    public static void main(String[] args) {
        closedEulerianWalk g1 = new closedEulerianWalk(5);
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(1, 3);
        g1.addEdge(3, 4);
        g1.addEdge(4, 1);

        List<Integer> cycle1 = g1.findEulerianCycle();
        if (cycle1 != null) {
            System.out.println("Eulerian Cycle in g1: " + cycle1);
        } else {
            System.out.println("g1 does not have an Eulerian Cycle.");
        }

        closedEulerianWalk g2 = new closedEulerianWalk(3);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        List<Integer> cycle2 = g2.findEulerianCycle();
        if (cycle2 != null) {
            System.out.println("Eulerian Cycle in g2: " + cycle2);
        } else {
            System.out.println("g2 does not have an Eulerian Cycle.");
        }
    }
}
