package coding;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;

public class TestJava {
    public static void mainee(String[] args) {
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

        int intervals[][] = { { 1, 2 } };
        System.out.println(Arrays.deepToString(intervals));
        int arr[] = Arrays.stream(intervals).mapToInt(a -> a[0]).toArray();

        System.out.println(Arrays.toString(arr));
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

    public static int minDistance(int[] arr1, int[] arr2) {
        int i = 0, j = 0;
        int minDistance = Integer.MAX_VALUE;
        while (i < arr1.length && j < arr2.length) {
            minDistance = Math.min(minDistance, Math.abs(arr1[i] - arr2[j]));
            if (arr1[i] > arr2[j]) {
                j++;
            } else
                i++;
        }
        return minDistance;
    }

    public static void recur(String input, String prefix, int index) {
        if (index == input.length()) {
            System.out.println(prefix);
            return;
        }
        recur(input, prefix + input.charAt(index), index + 1);
        recur(input, prefix, index + 1);
    }

    public static void main(String[] args) {
        int[] nums = { 1, 4, 2, 10, -9, 3, 100 };
        int k = 3;
        System.out.println(maxSubarraySumK(nums, k)); // Output: 39

        int[] nums1 = { 1, 3, 7, 9 };
        int[] nums2 = { 2, 11 };
        System.out.println("Min: " + minDistance(nums1, nums2));

        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        q.offer(1);
        q.offer(2);

        System.out.println(q.poll());

        recur("abc", "", 0);
    }
}
