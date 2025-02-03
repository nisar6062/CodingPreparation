package coding.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSAndDFSGraph {
    static int directions[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public static void bfs(int[][] graph) {
        int len = graph.length;
        int width = graph[0].length;
        boolean isVisited[][] = new boolean[len][width];

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[] { 0, 0 });
        isVisited[0][0] = true;

        while (!queue.isEmpty()) {
            Integer[] current = queue.poll();
            System.out.print(graph[current[0]][current[1]] + ", ");
            for (int[] direction : directions) {
                int newX = current[0] + direction[0];
                int newY = current[1] + direction[1];
                if (newX >= 0 && newY >= 0 && newX <= len - 1 && newY <= width - 1
                        && !isVisited[newX][newY]) {
                    queue.add(new Integer[] { newX, newY });
                    isVisited[newX][newY] = true;
                }
            }
        }
    }

    public static void dfs(int[][] graph, int row, int col, boolean isVisited[][]) {
        int len = graph.length;
        int width = graph[0].length;
        if (row < 0 || col < 0 || row > len - 1 || col > width - 1 || isVisited[row][col]) {
            return;
        }
        isVisited[row][col] = true;
        System.out.print(graph[row][col] + ", ");
        for (int[] direction : directions) {
            int newX = row + direction[0];
            int newY = col + direction[1];

            dfs(graph, newX, newY, isVisited);
        }

    }

    public static void topological(int[][] graph) {
        int len = graph.length;
        boolean isVisited[] = new boolean[len];
        int inDegree[] = new int[len];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (graph[i][j] == 1) {
                    inDegree[j]++;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            isVisited[current] = true;
            System.out.print((current + 1) + ", ");

            for (int i = 0; i < len; i++) {
                if (graph[current][i] == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        queue.add(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
        };
        System.out.println("BFS");
        bfs(graph);
        System.out.println();
        System.out.println("DFS");
        int len = graph.length;
        int width = graph[0].length;
        boolean isVisited[][] = new boolean[len][width];
        dfs(graph, 0, 0, isVisited);

        int[][] actGraph = { // 1---> 2---> 3 : 2--->4 4 --> 5 6--->2
                { 0, 1, 0, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0 },
        };
        System.out.println();
        System.out.println("Topological");
        topological(actGraph);

        List<Integer> list = new ArrayList<>();
        int g = list.remove(list.size() - 1);
    }
}
