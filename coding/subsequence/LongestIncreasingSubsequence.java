package coding.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(Arrays.toString(arr));
        lengthOfLIS(arr);
    }

    public static int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            int idx = Collections.binarySearch(sub, num);
            if (idx < 0)
                idx = -(idx + 1); // Get the correct insertion index

            if (idx < sub.size()) {
                sub.set(idx, num); // Replace element at idx
            } else {
                sub.add(num); // Append new element
            }
        }

        return sub.size();
    }

    public static int findLIS(int[] input) {
        int subSeqLen[] = new int[input.length];
        int maxSubSeqLen = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++)
            subSeqLen[i] = 1;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    subSeqLen[i] = Math.max(subSeqLen[i], subSeqLen[j] + 1);
                    maxSubSeqLen = Math.max(maxSubSeqLen, subSeqLen[i]);
                }
            }
        }
        System.out.println(maxSubSeqLen);
        return maxSubSeqLen;
    }

}
