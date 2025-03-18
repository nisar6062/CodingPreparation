package coding.binarysearch;

public class AggressiveCows {

    public static void main(String[] args) {
        int spots[] = { 1, 4, 7, 14, 9 };
        int result = getAggressiveCowsSpots(spots, 4);
        System.out.println("results: " + result);

    }

    public static int getAggressiveCowsSpots(int spots[], int cowsCount) {
        int minDistance = 0;
        int low = 1, high = spots[spots.length - 1] - spots[0];

        while (low <= high) {
            int mid = (low + high) / 2;
            boolean isPossible = isPossible(spots, mid, cowsCount);
            System.out.println("mid: " + mid + ", isPossible: " + isPossible);
            if (isPossible) {
                low = mid + 1;
                minDistance = mid;
            } else {
                high = mid - 1;
            }
        }
        return minDistance;
    }

    private static boolean isPossible(int spots[], int mid, int cowsCount) {
        int count = 1;
        int prev = 0;
        for (int i = 1; i < spots.length; i++) {
            int diff = spots[i] - spots[prev]; // 1,2,3,4
            // System.out.println("i: " + i + " prev: " + prev + "diff: " + diff);
            if (diff >= mid) {
                count++;
                prev = i;
            }
        }
        return count >= cowsCount;
    }

}
