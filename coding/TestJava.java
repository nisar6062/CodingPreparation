package coding;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestJava {
    public static void mainEE(String[] args) {
        Comparator<String> comp = (s1, s2) -> {
            int compare = Integer.compare(s2.length(), s1.length());
            if (compare == 0) {
                return s1.compareTo(s2);
            }
            return compare;
        };

        TreeMap<String, Integer> treeMap = new TreeMap<>(comp);

        treeMap.put("apple", 10);
        treeMap.put("cherry", 15);
        treeMap.put("banana", 20);

        treeMap.forEach((k, v) -> {
            System.out.println("k:" + k + ", v: " + v);
        });
    }

    public static int maxSubarraySumK(int[] nums, int k) {
        if (nums.length < k) {
            throw new IllegalArgumentException("Array length is less than k");
        }

        // Calculate the sum of the first window
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        int maxSum = currentSum;

        // Slide the window from k to the end of the array
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 4, 2, 10, -9, 3, 100 };
        int k = 3;
        System.out.println(maxSubarraySumK(nums, k)); // Output: 39
    }
}
