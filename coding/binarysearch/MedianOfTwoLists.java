package coding.binarysearch;

public class MedianOfTwoLists {
    public static void main(String[] args) {
        int arr1[] = { 4 };
        int arr2[] = { 1, 2, 3 };
        double median = findMedianOfTwoLists(arr1, arr2);
        System.out.println("median: " + median);
    }

    public static double findMedianOfTwoLists(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length)
            return findMedianOfTwoLists(arr2, arr1);

        int low = 0, high = arr1.length;

        while (low <= high) {
            int mid = (low + high) / 2;

            int totalMid = (arr1.length + arr2.length) / 2;
            System.out.println("totalMid: " + totalMid);
            // int elemsFromArr1 = Math.min(arr1.length, mid);
            int left1 = mid <= 0 ? Integer.MIN_VALUE : arr1[mid - 1];
            int left2 = totalMid - mid >= arr2.length || totalMid - mid < 0 ? Integer.MIN_VALUE
                    : arr2[totalMid - mid - 1];
            int left = Math.max(left1, left2);

            int right1 = mid < 0 ? Integer.MAX_VALUE : arr1[mid];
            int right2 = totalMid - mid >= arr2.length ? Integer.MAX_VALUE
                    : arr2[totalMid - mid];
            int right = Math.min(right1, right2);

            System.out.println("mid: " + mid + " isPossible: " + (left <= right));
            System.out.println("left1: " + left1 + " left2: " + left2 + ", right1: " + right1 + ", right2: " + right2);
            System.out.println("left: " + left + "right: " + right);
            if (left <= right) {
                System.out.println("possible-mid: " + mid + " left:" + left + ", right:" + right);
                boolean isOdd = (arr1.length + arr2.length) % 2 != 0;
                if (isOdd) {
                    return right;
                }
                return (double) (left + right) / 2;
            } else if (left1 >= left2) {
                high = mid - 1;
            } else if (left1 < left2) {
                low = mid + 1;
            }
            System.out.println("==>> low: " + low + ", high: " + high);
        }
        return -1;
    }
}
