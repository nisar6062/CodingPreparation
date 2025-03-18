package coding.arrays;

import java.util.Arrays;

public class MergeKArrays {
    public static void main(String[] args) {
        int arr[][] = { { 1, 9, 10, 11 }, { 5, 6, 12, 13 }, { 4, 8, 14 } };
        System.out.println(Arrays.toString(arr));
        merge(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void merge(int[][] arr) {
        arr[0][0] = 1;
    }
}
