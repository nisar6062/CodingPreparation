package coding.binarysearch;

public class SampleBS {
    public static void main(String[] args) {
        int input[] = { 1, 3, 5, 7, 8, 9, 10 };
        System.out.println("Res: " + binarySearch(input, 0, input.length - 1, 10));
    }

    public static int binarySearch(int[] input, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (input[mid] == target) {
            return mid;
        } else if (input[mid] > target)
            return binarySearch(input, left, mid - 1, target);
        else
            return binarySearch(input, mid + 1, right, target);
    }
}
