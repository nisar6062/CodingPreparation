package coding.graph;

import java.util.*;

class DirectedGraphBFS {
    private int V;
    private List<List<Integer>> adj;

    public DirectedGraphBFS(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public boolean hasCycle() {
        int[] inDegree = new int[V];
        for (List<Integer> neighbors : adj)
            for (int neighbor : neighbors)
                inDegree[neighbor]++;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++)
            if (inDegree[i] == 0)
                queue.add(i);

        int count = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;

            for (int neighbor : adj.get(node)) {
                if (--inDegree[neighbor] == 0)
                    queue.add(neighbor);
            }
        }

        return count != V; // If count != V, there was a cycle
    }

    private boolean dfs(int node, boolean[] visited, int parent) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, visited, node))
                    return true;
            } else if (neighbor != parent) {
                // Found a cycle (visited node that is NOT the parent)
                return true;
            }
        }
        return false;
    }

    public boolean isCycle() {
        boolean[] visited = new boolean[V];

        // Check each component (important for disconnected graphs)
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, visited, -1)) // Start DFS
                    return true;
            }
        }
        return false;
    }

    public boolean isCycleBfs() {
        boolean[] visited = new boolean[V];

        // Check for each disconnected component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfs(i, visited)) // Call BFS for this component
                    return true;
            }
        }
        return false;
    }

    private boolean bfs(int start, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>(); // {node, parent}
        queue.add(new int[] { start, -1 });
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int node = pair[0], parent = pair[1];

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    queue.add(new int[] { neighbor, node });
                    visited[neighbor] = true;
                } else if (neighbor != parent) {
                    // Found a cycle (visited node that is NOT the parent)
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DirectedGraphBFS g = new DirectedGraphBFS(5);
        int courses[][] = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 } };
        // { 1, 2 }, { 1, 4 } };
        g.addEdge(0, 1);
        // g.addEdge(1, 2);
        g.addEdge(2, 3);
        // g.addEdge(1, 3);
        // g.addEdge(1, 4);

        System.out.println("Cycle detected: " + g.isCycleBfs());
        System.out.println("Cycle detected: " + g.isCycle());
        System.out.println("Cycle detected: " + validTree(4, courses));
    }

    public static boolean validTree(int num, int[][] edges) {
        if (edges.length != num - 1)
            return false;
        int graph[][] = new int[num][num];
        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]][edges[i][1]] = 1;
            graph[edges[i][1]][edges[i][0]] = 1;
        }
        boolean[] isVisited = new boolean[num];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        isVisited[0] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] == 1 && !isVisited[i]) {
                    queue.add(i);
                    isVisited[i] = true;
                    count++;
                }
            }
        }
        return num == count;
    }

}
