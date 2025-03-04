package coding.grid;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        String grid[][] = {
                { "1", "1", "1", "1", "0" },
                { "1", "1", "0", "1", "0" },
                { "1", "1", "0", "0", "0" },
                { "0", "0", "0", "0", "1" }
        };
        System.out.println("calcNoOfIslands: " + calcNoOfIslands(grid));
        ;
    }

    public static int calcNoOfIslands(String grid[][]) {

        int noOfIslands = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != "1") {
                    continue;
                }
                noOfIslands++;
                bfs(grid, i, j);
            }
        }

        return noOfIslands;
    }

    static int directions[][] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    private static void bfs(String grid[][], int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { i, j });
        while (!queue.isEmpty()) {
            int curr[] = queue.poll();
            for (int[] direction : directions) {
                int new_x = curr[0] + direction[0];
                int new_y = curr[1] + direction[1];
                if (new_x >= 0 && new_y >= 0 && new_x < grid.length && new_y < grid[0].length
                        && grid[new_x][new_y] == "1") {
                    grid[new_x][new_y] = "0";
                    queue.add(new int[] { new_x, new_y });
                }
            }
        }
    }
}
