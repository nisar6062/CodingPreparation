
package coding.arrays;

import java.util.Arrays;

public class QuickSelect {

    public static void main(String[] args) {
        int[] arr = { 8, 3, 7, 9, 1, 5 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr)); // Output: [1, 3, 5, 7, 8, 9]

        int k = 1;
        System.out.println("kth Small: " + kthSmallest(arr, k));
    }

    public static int kthSmallest(int[] arr, int k) {
        if (k == 0 || k > arr.length) {
            throw new IllegalArgumentException("invalid k");
        }
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int pivot = partition(arr, left, right);
        if (pivot == k) {
            return arr[k];
        } else if (pivot < k) {
            return quickSelect(arr, pivot + 1, right, k);
        } else {
            return quickSelect(arr, left, pivot - 1, k);
        }

    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int partition = partition(arr, left, right);
        quickSort(arr, left, partition - 1);
        quickSort(arr, partition + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int leftMost = left - 1;

        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                leftMost++;
                swap(arr, i, leftMost);

            }
        }
        swap(arr, leftMost + 1, right);
        return leftMost + 1;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}