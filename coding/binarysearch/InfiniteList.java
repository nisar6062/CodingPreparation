package coding.binarysearch;

public class InfiniteList {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 5, 7, 10, 12, 15, 20, 25, 30, 40, 50, 60, 70, 80, 90, 100 }; // Example sorted array
        int target = 25;

        int result = binarySearchInInfiniteList(arr, target);
        System.out.println("Index of " + target + ": " + result);
    }

    private static int binarySearchInInfiniteList(int[] arr, int target) {
        int low = 0, high = 1;
        while (high < arr.length && arr[high] < target) {
            low = high;
            high = 2 * high;
        }

        return binarySearch(arr, low, high >= arr.length ? arr.length - 1 : high, target);
    }

    private static int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, mid + 1, high, target);
        } else if (arr[mid] > target) {
            return binarySearch(arr, low, mid - 1, target);
        }
        return -1;
    }
}
