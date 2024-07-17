package coding.graph;
/* 
 Graph can be showed using adjacency matrix
     0 0 0 0
     0 0 0 0 
     0 0 0 0 
     0 0 0 0
 * 
 * 
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyGraph {
    int vertices;
    int adj[][];
    boolean isVisited[];

    MyGraph(int vertices) {
        this.vertices = vertices;
        adj = new int[vertices][vertices];
        isVisited = new boolean[vertices];
    }

    void addEdge(int i, int j) {
        adj[i][j] = 1;
        adj[j][i] = 1;
    }

    void dfs(int start) {
        this.isVisited[start] = true;
        System.out.print(start + ",");
        for (int i = 0; i < this.vertices; i++) {
            if (!this.isVisited[i])
                dfs(i);
        }
    }

    void bfs(int start) {
        this.isVisited = new boolean[this.vertices];
        System.out.println("visited: " + Arrays.toString(this.isVisited));
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        this.isVisited[start] = true;

        while (!queue.isEmpty()) {
            int elem = queue.poll();

            System.out.print(elem + ",");

            for (int i = 0; i < this.vertices; i++) {
                if (adj[elem][i] == 1 && !this.isVisited[i]) {
                    this.isVisited[i] = true;
                    queue.add(i);
                }
            }
        }

    }

    public static void main(String[] args) {
        MyGraph graph = new MyGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        graph.dfs(2);
        System.out.println();

        System.out.println("visited: " + Arrays.toString(graph.isVisited));
        System.out.println("BFS");
        graph.bfs(2);
    }

}
