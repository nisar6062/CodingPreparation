package coding.arrays;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1); // Sort left part
            quickSort(arr, pivotIndex + 1, high); // Sort right part
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choose last element as pivot
        System.out.println("pivot: " + pivot);
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
            System.out.println(Arrays.toString(arr));
        }
        swap(arr, i + 1, high); // Move pivot to correct position
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 8, 3, 7, 9, 1, 5 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr)); // Output: [1, 3, 5, 7, 8, 9]
    }
}