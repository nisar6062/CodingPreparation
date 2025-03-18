package coding.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestToPoint {

    // Directions: right, left, down, up
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static int shortestPath(int[][] grid, int startX, int startY, int targetX, int targetY) {
        int m = grid.length;
        int n = grid[0].length;

        // Check if starting point or target point is blocked
        if (grid[startX][startY] == 1 || grid[targetX][targetY] == 1) {
            return -1;
        }

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // Each entry in the queue is an array: {x, y, steps}
        queue.offer(new int[] { startX, startY, 0 });
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int steps = current[2];

            // If we have reached the target, return the number of steps
            if (x == targetX && y == targetY) {
                return steps;
            }

            // Explore neighbors
            for (int[] d : DIRECTIONS) {
                int newX = x + d[0];
                int newY = y + d[1];

                // Check boundaries, obstacles, and if cell is unvisited
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && grid[newX][newY] == 0 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[] { newX, newY, steps + 1 });
                }
            }
        }

        // Target is unreachable
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 0, 0 }
        };

        int startX = 0, startY = 0;
        int targetX = 3, targetY = 2;

        int result = shortestPath(grid, startX, startY, targetX, targetY);
        System.out.println("Shortest path steps: " + result);
    }
}
