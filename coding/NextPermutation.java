package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NextPermutation {
    private static List<List<Integer>> PERMUTATIONS = new ArrayList<>();
    private static boolean IS_BREAK = false;

    public static void main(String[] args) {
        int nums[] = { 3, 2, 1 };
        nextPermutation(nums);
    }

    public static void nextPermutation(int[] nums) {
        int numsCopy[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numsCopy[i] = nums[i];
        }
        Arrays.sort(numsCopy);
        generatePermutation(nums, numsCopy, 0);
    }

    public static void generatePermutation(int[] nums, int[] numsChanged, int index) {
        if (IS_BREAK)
            return;
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < numsChanged.length; i++) {
                list.add(numsChanged[i]);
            }
            if (PERMUTATIONS.size() == 0) {
                PERMUTATIONS.add(list);
                return;
            }
            List<Integer> lastList = PERMUTATIONS.get(PERMUTATIONS.size() - 1);
            boolean isSame = true;
            System.out.println("PERMUTATIONS: " + PERMUTATIONS);
            System.out.println("lastList: " + lastList);
            System.out.println("nums:::" + Arrays.toString(nums));
            for (int i = 0; i < nums.length; i++) {
                if (lastList.get(i) != nums[i]) {
                    isSame = false;
                    break;
                }
            }
            System.out.println("isSame: " + isSame);
            PERMUTATIONS.add(list);
            if (isSame) {
                System.out.println("lastList: " + lastList);
                System.out.println(Arrays.toString(numsChanged));
                IS_BREAK = true;
            }

            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(numsChanged, i, index);
            generatePermutation(nums, numsChanged, index + 1);
            swap(numsChanged, i, index);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}