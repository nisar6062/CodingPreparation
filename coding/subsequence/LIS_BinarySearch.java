package coding.subsequence;

import java.util.ArrayList;
import java.util.Collections;

public class LIS_BinarySearch {
    public static int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            int i = Collections.binarySearch(sub, num);
            if (i < 0)
                i = -(i + 1); // Convert to insertion point
            if (i < sub.size()) {
                sub.set(i, num); // Replace element
            } else {
                sub.add(num); // Extend sequence
            }
            System.out.println(sub);
        }

        return sub.size(); // Length of LIS
    }

    public static void main(String[] args) {
        // int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
        int[] nums = { 2, 3, 1, 4 }; // 6, 7, 0, 8, 3, 4, 5
        System.out.println(lengthOfLIS(nums)); // Output: 4 (subsequence: {2,3,7,18})
    }
}
