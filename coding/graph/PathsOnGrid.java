package coding.graph;

/*
 *  1 1 0       1 1 0
 *  1 1 0       1 2 0
 *  1 1 1  =>   1 3 3
 *  No of paths = 3 (right & left)
 */
public class PathsOnGrid {
    public static void main(String[] args) {
        int arr[][] = { { 1, 1, 0 }, { 1, 1, 0 }, { 1, 1, 1 } };
        System.out.println("result: " + uniquePaths(arr));
    }

    public static int uniquePaths(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != 0 && i > 0 && j > 0) {
                    arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
                } else if (j == 0 && i > 0) {
                    arr[i][j] = arr[i - 1][j];
                } else if (i == 0 && j > 0) {
                    arr[i][j] = arr[i][j - 1];
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        return arr[arr.length - 1][arr[0].length - 1];
    }
}
