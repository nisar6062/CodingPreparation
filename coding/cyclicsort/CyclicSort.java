package coding.cyclicsort;

import java.util.Arrays;

/*
 * arr = [2, 5, 1, 3, 4] => sort in O(N)
 * [1, 5, 2, 3, 4] => index = 1 [1, 4, 2, 3, 5]  => index = 1 [1, 3, 2, 4, 5] => index = 1 [1, 2, 3, 4, 5]
 
 * Use cyclic sort
 */
public class CyclicSort {

    public static void main(String[] args) {
        int arr[] = { 2, 5, 1, 3, 4 };
        doCyclicSort(arr);
    }

    public static void doCyclicSort(int[] arr) {
        int index = 0;
        while (index < arr.length - 1) {
            if (arr[index] != index + 1) {
                arr = swap(arr, index, arr[index] - 1);

                System.out.println("temp: " + Arrays.toString(arr));
            } else {
                index++;
            }
        }
        System.out.println("sorted: " + arr);
    }

    public static int[] swap(int[] arr, int i, int j) {
        System.out.println("swap => i: " + i + " j: " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
}
