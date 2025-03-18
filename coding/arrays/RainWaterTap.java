package coding.arrays;

import java.util.Arrays;

public class RainWaterTap {
    public static void main(String[] args) {
        int[] heights = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        System.out.println("getRainWaterTapped: " + getRainWaterTapped(heights)); // Output: 6
    }

    public static int getRainWaterTapped(int[] heights) {
        int rainWater = 0;
        int leftMaxArr[] = new int[heights.length];
        int rightMaxArr[] = new int[heights.length];

        leftMaxArr[0] = heights[0];
        for (int i = 1; i < heights.length; i++) {
            leftMaxArr[i] = Math.max(heights[i], leftMaxArr[i - 1]);
        }
        rightMaxArr[heights.length - 1] = heights[heights.length - 1];
        for (int i = heights.length - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(heights[i], rightMaxArr[i + 1]);
        }
        System.out.println("leftMaxArr: " + Arrays.toString(leftMaxArr));
        System.out.println("rightMaxArr: " + Arrays.toString(rightMaxArr));
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] < leftMaxArr[i] && heights[i] < rightMaxArr[i]) {
                rainWater += Math.min(leftMaxArr[i], rightMaxArr[i]) - heights[i];
            }
        }
        return rainWater;
    }
}
