package coding.graph;

import java.util.LinkedList;
import java.util.Queue;

/*
    Below input array with -1 as obstacle, 0 as exit.
    INF -1   0   INF
    INF INF INF  -1
    INF -1  INF  -1
    0   -1  INF  INF
 * 
 *  1 2  3  4
 *  5 6  7  8 
 *  9 10 11 12
 */
public class ShortestExitPath {
    public static void main(String[] args) {
        // int arr1[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        int max = Integer.MAX_VALUE;
        int arr[][] = { { max, -1, 0, max }, { max, max, max, -1 }, { max, -1, max, -1 }, { 0, -1, max, max } };
        // System.out.println("---exit----" + arr[2][3]);
        shortestPath(arr);
        // print(arr);
    }

    public static void shortestPath(int arr[][]) {
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr.length; y++) {
                if (arr[x][y] == 0) {
                    bfs(arr, x, y);
                    print(arr);
                }
            }
        }
    }

    private static void bfs(int arr[][], int start_i, int start_j) {
        boolean isVisited[][] = new boolean[arr.length][arr[0].length];
        int directions[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        isVisited[start_i][start_j] = true;
        Queue<int[]> queue = new LinkedList<>();
        int start[] = { start_i, start_j };
        queue.add(start);
        System.out.println("queue start i: " + start_i + " j: " + start_j);

        while (!queue.isEmpty()) {
            int curr[] = queue.poll();
            // System.out.println("queue start i: " + curr[0] + " j: " + curr[1]);
            for (int xy[] : directions) {
                int newX = curr[0] + xy[0];
                int newY = curr[1] + xy[1];
                if (newX >= 0 && newX < arr.length && newY >= 0 && newY < arr[0].length
                        && arr[newX][newY] != -1 && !isVisited[newX][newY]) {
                    isVisited[newX][newY] = true;
                    int newDistance = Math.min(arr[newX][newY], arr[curr[0]][curr[1]] + 1);
                    System.out.println("newDistance : " + newDistance);
                    arr[newX][newY] = newDistance;
                    queue.offer(new int[] { newX, newY });
                }
            }
        }
    }

    private static void dfs(int arr[][], int i, int j, boolean isVisited[][], int distance) {
        if (i < 0 || i > arr.length - 1 || j < 0 || j > arr[0].length - 1 || isVisited[i][j] || arr[i][j] == -1) {
            return;
        }
        isVisited[i][j] = true;
        int min = Integer.MAX_VALUE;
        if (i > 0 && arr[i - 1][j] > 0) {
            min = Math.min(min, arr[i - 1][j]);
        }
        if (j > 0 && arr[i][j - 1] > 0) {
            min = Math.min(min, arr[i][j - 1]);
        }
        if (i < arr.length - 1 && arr[i + 1][j] > 0) {
            min = Math.min(min, arr[i + 1][j]);
        }
        if (j < arr[0].length - 1 && arr[i][j + 1] > 0) {
            min = Math.min(min, arr[i][j + 1]);
        }
        min = Math.min(arr[i][j], min + 1);
        distance = Math.min(min, distance);
        System.out.println("arr[" + i + "][" + j + "] ----->>> " + arr[i][j] + ", dist:" + distance);
        arr[i][j] = distance;

        dfs(arr, i + 1, j, isVisited, distance + 1);
        dfs(arr, i, j + 1, isVisited, distance + 1);
        dfs(arr, i - 1, j, isVisited, distance + 1);
        dfs(arr, i, j - 1, isVisited, distance + 1);
    }

    private static void print(int arr[][]) {
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr.length; y++) {
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }
    }
}
