package coding.dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    // 7.02
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        int candidates[] = { 2, 3, 6, 7 };
        System.out.println("result: " + combinationSum(candidates, 0, new ArrayList<>(), 6));
        System.out.println("combSumDp: " + combSumDp(candidates, 6));

        // List<Integer> result = new ArrayList<>();
        // System.out.println(Collections.binarySearch(result, 2));
        // result.add(2);
        // System.out.println("---" + Collections.binarySearch(result, 2));
        // System.out.println(Collections.binarySearch(result, 3));
        // System.out.println(Collections.binarySearch(result, 1));
    }

    private static int combSumDp(int[] candidates, int target) {
        int dp[] = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < candidates.length; j++) {
                if (i >= candidates[j]) {
                    dp[i] += dp[i - candidates[j]];
                }
            }
        }
        return dp[target];
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int sum, List<Integer> nums,
            int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (target < sum) {
            return result;
        }
        System.out.println("sum: " + sum + " nums:" + nums);

        if (target == sum) {
            System.out.println("EQQQ sum: " + sum);
            List<Integer> list = new ArrayList<>(nums);

            Collections.sort(list);
            if (!set.contains(list.toString())) {
                result.add(list);
            }
            set.add(list.toString());

            System.out.println("EQQQ result: " + result);
            return result;
        }
        for (int i = 0; i < candidates.length; i++) {
            nums.add(candidates[i]);
            result.addAll(combinationSum(candidates, sum + candidates[i], nums, target));
            nums.remove(nums.size() - 1);
        }
        return result;
    }

}
