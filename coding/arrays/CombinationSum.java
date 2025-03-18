package coding.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    // 7.02
    private static List<List<Integer>> RESULT = new ArrayList<>();
    private static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        int candidates[] = { 2, 3, 6, 7 };
        System.out.println("result: " + combinationSum(candidates, 0, 0, new ArrayList<>(), 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int index, int sum, List<Integer> nums,
            int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (index == candidates.length || target < sum) {
            return result;
        }
        System.out.println("sum: " + sum + " nums:" + nums);

        if (target == sum) {
            System.out.println("EQQQ sum: " + sum);
            List<Integer> list = new ArrayList<>();
            for (int k : nums)
                list.add(k);

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
            result.addAll(combinationSum(candidates, index, sum + candidates[i], nums, target));
            nums.remove(nums.size() - 1);
        }
        return result;
    }

}
