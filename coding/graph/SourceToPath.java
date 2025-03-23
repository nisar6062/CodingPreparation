package coding.graph;

import java.util.ArrayList;
import java.util.List;

public class SourceToPath {

    public static void main(String[] args) {
        int[][] graph = { { 4, 3, 1 }, { 3, 2, 4 }, { 3 }, { 4 }, {} };
        int n = graph.length;
        int[][] adjList = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                adjList[i][graph[i][j]] = 1;
            }
        }
        List<Integer> list = new ArrayList<>();
        list.add(0);
        List<List<Integer>> result = dfs(adjList, 0, list);
        System.out.println("result: " + result);
    }

    private static List<List<Integer>> dfs(int[][] adjList, int start, List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        int n = adjList.length;
        if (start == n - 1) {
            result.add(new ArrayList<>(list));
            return result;
        }
        for (int i = 0; i < n; i++) {
            if (adjList[start][i] != 1)
                continue;
            list.add(i);
            result.addAll(dfs(adjList, i, list));
            list.remove(list.size() - 1);
        }
        return result;
    }

}
