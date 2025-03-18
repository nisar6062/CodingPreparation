package coding.arrays;

import java.util.ArrayList;
import java.util.List;

public class SubSequenceSum {
    public static void main(String[] args) {
        int arr[] = { 5, 2, 3, 10, 6, 8 };
        subSeqSum(arr, 10);
    }

    public static void subSeqSum(int[] nums, int target) {
        subSeqSum(nums, target, 0, 0, new ArrayList<>());
    }

    public static void subSeqSum(int[] nums, int target, int index, int sum, List<Integer> list) {
        if (index == nums.length) {
            if (sum == target) {
                System.out.println(list);
            }
            return;
        }
        list.add(nums[index]);
        subSeqSum(nums, target, index + 1, sum + nums[index], list);
        list.remove(list.size() - 1);
        subSeqSum(nums, target, index + 1, sum, list);
    }
}
