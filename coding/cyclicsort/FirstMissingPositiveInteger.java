package coding.cyclicsort;

import java.util.Arrays;

/*
 * arr = [-1, 2, 5, 1] => least missing +ve integer is 3
 * Use cyclic sort
 */
public class FirstMissingPositiveInteger {

    public static void main(String[] args) {
        int arr[] = { 3, 2, -1, 5, };
        // int arr[] = { 1, 3, 3 };
        // System.out.println("result_1:" + findLeastMissingInteger(arr));
        System.out.println("result:" + findLeastMissingInteger_New(arr));
    }

    public static int findLeastMissingInteger_New(int[] arr) {
        // cyclic sort
        boolean isFoundOne = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                isFoundOne = true;
            }
            if (arr[i] <= 0 || arr[i] > arr.length) {
                arr[i] = 1;
            }
        }
        if (!isFoundOne)
            return 1;

        // cyclic sort
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                swap(arr, i, arr[i] - 1);
            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                return arr[i - 1] + 1;
            }
        }
        return arr.length;
    }

    public static int findLeastMissingInteger(int[] arr) {
        int i = 0;
        boolean oneExists = false;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                oneExists = true;
            }
            if (arr[i] <= 0 || arr[i] > arr.length) {
                arr[i] = 1;
            }
        }

        if (!oneExists)
            return 1;
        System.out.println("arr:" + Arrays.toString(arr));

        i = 0;
        while (i < arr.length) {
            // System.out.println("i:" + i);
            if (arr[i] != i + 1 && arr[i] != 1 && arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
                // System.out.println("arr:" + Arrays.toString(arr));
            } else {
                i++;
            }
        }
        // System.out.println("arr:" + Arrays.toString(arr));

        for (i = 1; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                return i + 1;
            }
        }
        return arr.length + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
