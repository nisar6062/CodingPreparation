package coding.arrays;

import java.util.HashMap;
import java.util.Map;

class MaxSumWithEqualDigitsSum {
    public int maximumSum(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int maxSum = 0;
        boolean isExists = false;
        for (int i = 0; i < nums.length; i++) {
            String num = String.valueOf(nums[i]);
            int digitsSum = 0;
            for (int j = 0; j < num.length(); j++) {
                digitsSum += Integer.parseInt(num.charAt(j) + "");
            }
            // System.out.println(nums[i] + "----" + digitsSum);
            int[] arr = map.get(digitsSum);
            if (arr != null) {
                isExists = true;
                if (arr[0] < arr[1] && nums[i] > arr[0]) {
                    arr[0] = nums[i];
                } else if (arr[0] >= arr[1] && nums[i] > arr[1]) {
                    arr[1] = nums[i];
                }
                maxSum = Math.max(maxSum, arr[0] + arr[1]);
                map.put(digitsSum, arr);
            } else
                map.put(digitsSum, new int[] { nums[i], 0 });
        }
        System.out.println(map);
        return isExists ? maxSum : -1;
    }
}
