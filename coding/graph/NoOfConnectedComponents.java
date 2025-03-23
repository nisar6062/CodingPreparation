package coding.graph;

public class NoOfConnectedComponents {
    public static void main(String[] args) {
        int edges[][] = { { 0, 1 }, { 1, 2 }, { 3, 4 } };
        System.out.println("NoOfConnectedComponents: " + countComponents(5, edges));
    }

    public static int countComponents(int n, int[][] edges) {
        int[][] adjList = new int[n][n];
        for (int i = 0; i < edges.length; i++) {
            adjList[edges[i][0]][edges[i][1]] = 1;
            adjList[edges[i][1]][edges[i][0]] = 1;
        }
        boolean isVisited[] = new boolean[n];
        int noOfComponents = 0;
        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                noOfComponents++;
                dfs(n, adjList, i, isVisited);
            }
        }
        return noOfComponents;
    }

    private static void dfs(int n, int[][] adjList, int start, boolean isVisited[]) {
        isVisited[start] = true;

        for (int i = 0; i < n; i++) {
            if (adjList[start][i] == 1 && !isVisited[i]) {
                dfs(n, adjList, i, isVisited);
            }
        }

    }
}