package coding.binarysearch;

import java.util.Arrays;
import java.util.List;

public class RotatedSortedArray {

    // Search in a pivoted sorted array
    public static int pivotedSearch(List<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("mid: " + mid + ", left: " + low + ", right: " + high);
            // Case 1: Find key
            if (arr.get(mid) == key)
                return mid;

            // Case 2: Left half is sorted
            if (arr.get(mid) >= arr.get(low)) {
                if (key >= arr.get(low) && key < arr.get(mid))
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            // Case 3: Right half is sorted
            else {
                if (key > arr.get(mid) && key <= arr.get(high))
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return -1; // Key not found
    }

    public static int binarySearch(int[] nums, int target, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left > right) {
            return -1;
        }
        System.out.println("mid: " + mid + ", left: " + left + ", right: " + right);
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[left] <= nums[mid]) { // left half sorted
            if (nums[left] <= target && target < nums[mid]) { // left
                return binarySearch(nums, target, left, mid - 1);
            } else {
                return binarySearch(nums, target, mid + 1, right);
            }
        } else { // right half sorted
            if (nums[mid] < target && target <= nums[right]) { // right
                return binarySearch(nums, target, mid + 1, right);
            } else {
                return binarySearch(nums, target, left, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        // List<Integer> arr1 = Arrays.asList(4, 5, 6, 7, 0, 1, 2);
        // int key1 = 0;
        // int result1 = pivotedSearch(arr1, key1);
        // System.out.println(result1); // Output: 4

        List<Integer> arr2 = Arrays.asList(3, 1);
        // 4, 5, 6, 7, 10, 1,2,3, 15
        int key2 = 2;
        int result2 = pivotedSearch(arr2, key2);
        System.out.println(result2); // Output: -1
        System.out.println("------------->>>>>");
        int arr[] = { 3, 1 };
        int res = binarySearch(arr, 2, 0, arr.length - 1);
        System.out.println("binarysearch: " + res);
    }
}