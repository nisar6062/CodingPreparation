package coding.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubArraySum {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, -2, 2, 1 };
        // 1, 3, 6, 4, 6, 1
        int sum = 3;
        // System.out.println("count: " + subArraySum(nums, sum));
        System.out.println("subArraySumMap count: " + subArraySumMap(nums, sum));
    }

    public static int subArraySumMap(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Initialize with prefix sum 0 occurring once

        for (int num : nums) {
            prefixSum += num;

            // Check if (prefixSum - k) exists in the map
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }

            // Update the map with the current prefix sum
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static int subArraySum(int[] input, int sum) {
        int count = 0;
        int i = 0, j = 0, currSum = 0;
        List<Integer> list = new ArrayList<>();
        while (i < input.length) {
            currSum += input[j];
            list.add(input[j]);
            if (currSum == sum) {
                count++;
                System.out.println("list: " + list);
            }
            j++;
            if (j == input.length) {
                i++;
                j = i;
                currSum = 0;
                list = new ArrayList<>();
            }
        }
        return count;
    }
}
