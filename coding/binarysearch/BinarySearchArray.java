package coding.binarysearch;

public class BinarySearchArray {
    public static void main(String[] args) {
        int arr[] = { 4, 1, -2, 5, 12 };
        System.out.println("result: " + doBinarySearch(arr, 5));
    }

    public static int doBinarySearch(int[] arr, int find) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == find)
                return mid;
            else if (arr[mid] < find)
                low = mid + 1;
            else if (arr[mid] > find)
                high = mid - 1;
        }

        return -1;
    }
}
