package coding.binarysearch;

public class MedianOfTwoLists {
    public static void main(String[] args) {
        int arr1[] = { 1, 3 };
        int arr2[] = { 2, 4, 5, 6 };
        double median = findMedianSortedArrays(arr1, arr2);
        System.out.println("median: " + median);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0, high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                // Found correct partition
                if ((x + y) % 2 == 0) {
                    return (Math.max(maxX, maxY) + Math.min(minX, minY)) / 2.0;
                } else {
                    return Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }

    public static double findMedianOfTwoLists(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length)
            return findMedianOfTwoLists(arr2, arr1);

        int low = 0, high = arr1.length;

        while (low <= high) {
            int mid = (low + high) / 2;

            int totalMid = (arr1.length + arr2.length) / 2;
            System.out.println("totalMid: " + totalMid);
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
