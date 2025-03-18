package coding.arrays;

import java.util.ArrayList;
import java.util.List;

public class SortSquares {
    public static void main(String[] args) {
        int arr[] = { -3, -2, 0, 2, 5 };
        System.out.println("ll: " + (7 >> 1));
        System.out.println("sortSquares: " + sortSquares(arr));
    }

    public static List<Integer> sortSquares(int[] arr) {
        int left = 0, right = arr.length - 1;
        int val = 0;
        List<Integer> result = new ArrayList<>();
        while (left <= right) {
            if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                val = arr[left] * arr[left];
                left++;
            } else {
                val = arr[right] * arr[right];
                right--;
            }
            result.add(val);
        }
        System.out.println(left + "---" + right);
        return result;
    }
}
