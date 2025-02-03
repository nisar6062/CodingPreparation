package coding.graph;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceToNearestExit_gpt {

    public static int[][] updateDistances(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] distances = new int[rows][cols];

        // Initialize distances array with Integer.MAX_VALUE for cells that are not -1
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    distances[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    distances[i][j] = -1;
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();

        // Enqueue all exits (cells with value 0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                }
            }
        }

        // Directions array for moving up, down, left, right
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        // BFS to update distances
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];

            for (int[] dir : directions) {
                int newRow = currentRow + dir[0];
                int newCol = currentCol + dir[1];

                // Check if the new position is within bounds and is not -1
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] != -1) {
                    int newDistance = distances[currentRow][currentCol] + 1;
                    if (newDistance < distances[newRow][newCol]) {
                        distances[newRow][newCol] = newDistance;
                        queue.offer(new int[] { newRow, newCol });
                    }
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1 },
                { Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1 },
                { 0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE }
        };

        int[][] result = updateDistances(grid);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}