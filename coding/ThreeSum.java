package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int arr[] = { -1, 0, 1, 2, -1, -4 }; // -4, -1, -1, 0, 1, 2
        // [[-1,-1,2],[-1,0,1]]
        List<List<Integer>> results = threeSum(arr, 0);
        System.out.println("results: " + results);
    }

    public static List<List<Integer>> threeSum(int[] arr, int sum) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (arr.length < 3) {
            return results;
        }
        Arrays.sort(arr);
        int i = 0, j = 1, k = arr.length - 1;

        while (i < arr.length && j < arr.length) {
            if (j == k || (i > 0 && arr[i] == arr[i - 1])) {
                i++;
                j = i + 1;
                k = arr.length - 1;
                continue;
            }
            // System.out.println("Sum:::: =>> i: " + i + ", j: " + j + ", k:" + k + " :sum:
            // " + (arr[i] + arr[j]
            // + arr[k]));
            int diff = sum - arr[i];
            int jkSum = arr[j] + arr[k];
            if (diff == jkSum) {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i]);
                temp.add(arr[j]);
                temp.add(arr[k]);
                results.add(temp);
                // System.out.println("Add =>> i: " + i + ", j: " + j + ", k:" + k);
                // System.out.println("sum: " + (arr[i] + arr[j] + arr[k]));
                if (j < k - 1) {
                    j++;
                    k--;
                } else {
                    j = k;
                }
            } else if (diff > jkSum) {
                j++;
            } else if (diff < jkSum) {
                k--;
            }
            while (j < arr.length && j < k && arr[j] == arr[j - 1]) {
                j++;
            }
        }
        return results;
    }
}
