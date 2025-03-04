package coding.grid;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        int[][] grid = {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 }
        };
        System.out.println(getTimeToRottAll(grid)); // Output: 4
    }

    // 9.41
    public static int getTimeToRottAll(int[][] input) {
        int freshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                if (input[i][j] == 1) {
                    freshOranges++;
                } else if (input[i][j] == 2) {
                    queue.add(new int[] { i, j });
                }
            }
        }

        int minutes = 0;
        int directions[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        while (!queue.isEmpty() && freshOranges > 0) {
            minutes++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr[] = queue.poll();
                for (int[] direction : directions) {
                    int new_x = curr[0] + direction[0];
                    int new_y = curr[1] + direction[1];
                    if (new_x >= 0 && new_x < input.length && new_y >= 0 && new_y < input[0].length
                            && input[new_x][new_y] == 1) {
                        freshOranges--;
                        input[new_x][new_y] = 2;
                        queue.add(new int[] { new_x, new_y });
                    }
                }
            }

        }
        return freshOranges == 0 ? minutes : -1;
    }

}
