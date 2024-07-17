package coding.graph;

import java.util.Arrays;
/* 
 Graph can be showed using adjacency matrix
     0 0 0 0
     0 0 0 0 
     0 0 0 0 
     0 0 0 0
 * 
 * 
 */
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int vertices;
    private int[][] adjMatrix;
    private boolean[] visited;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
        visited = new boolean[vertices];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1;
    }

    // Helper method to print the adjacency matrix
    public void printAdjMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public void dfs() {
        Arrays.fill(visited, false);
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public void bfs(int start) {
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        System.out.println("Adjacency Matrix:");
        graph.printAdjMatrix();

        System.out.println("DFS Traversal:");
        graph.dfs(2);

        System.out.println("\nBFS Traversal:");
        graph.bfs(2);
    }
}
