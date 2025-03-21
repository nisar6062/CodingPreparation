package coding.cyclicsort;

import java.util.Arrays;

/*
 * arr has values [0,n] length = n + 1
 * arr = [1, 3, 4, 2, 2] => answer = 3 (index)
 * 
 
 * Use cyclic sort
 */
public class CycleDetector {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 2 };
        // 1, 3, 3, 3,
        // 1, 2, 3 2,3,2,3,2
        // slow2- 1,2,3,2,3
        doCycleDetection(arr);
        int arr2[][] = { { 1 } };
        System.out.println(Arrays.deepToString(arr2));
    }

    public static void doCycleDetection(int[] arr) {
        int slow = arr[0];
        int fast = arr[0];

        do {
            System.out.println("slow: " + slow + ", fast: " + fast);
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (slow != fast);

        System.out.println("slow: " + slow);
        System.out.println("fast: " + fast);

        int slow2 = arr[0];
        while (slow != slow2) {
            slow = arr[slow];
            slow2 = arr[slow2];
            System.out.println("slow: " + slow + "slow2: " + slow);
        }
        System.out.println("Result: " + slow);
    }
}
