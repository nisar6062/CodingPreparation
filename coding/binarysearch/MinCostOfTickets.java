package coding.binarysearch;

class Solution {
    public int minCostTickets(int[] days, int[] costs) {
        int sumArr[] = new int[days.length];
        for (int i = 0; i < days.length; i++) {
            int dayCharge = (i >= 1 ? sumArr[i - 1] : 0) + costs[0];
            int weekStartIndex = binarySearchMin(days, 0, i, days[i] - 7);
            int weekStartCharge = weekStartIndex == -1 ? 0 : sumArr[weekStartIndex];
            int weekCharge = weekStartCharge + costs[1];

            int monthStartIndex = binarySearchMin(days, 0, i, days[i] - 30);
            int monthStartCharge = monthStartIndex == -1 ? 0 : sumArr[monthStartIndex];
            int monthCharge = monthStartCharge + costs[2];

            sumArr[i] = Math.min(Math.min(dayCharge, weekCharge), Math.min(weekCharge, monthCharge));
        }
        return sumArr[days.length - 1];

    }

    private static int binarySearchMin(int arr[], int left, int right, int target) {
        if (left > right) {
            return right;
        }
        int mid = (left + right) / 2;
        if (target == arr[mid]) {
            return mid;
        } else if (target < arr[mid]) {
            return binarySearchMin(arr, left, mid - 1, target);
        } else if (target > arr[mid]) {
            return binarySearchMin(arr, mid + 1, right, target);
        } else {
            return -1;
        }
    }
}
