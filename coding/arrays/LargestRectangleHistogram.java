package coding.arrays;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleHistogram {
    public static void main(String[] args) {
        int[] heights = { 2, 1, 5, 6, 2, 3 };
        System.out.println(largestRectangleArea(heights)); // Output: 10
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        // previous greatest element
        int prevSmallest[] = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                prevSmallest[i] = -1;
            } else {
                prevSmallest[i] = stack.peek();
            }
            stack.push(i);
        }
        // next greatest element
        stack = new Stack<>();
        int nextSmallest[] = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                nextSmallest[i] = -1;
            } else {
                nextSmallest[i] = stack.peek();
            }
            stack.push(i);
        }

        System.out.println("prevSmallest: " + Arrays.toString(prevSmallest));
        System.out.println("nextSmallest: " + Arrays.toString(nextSmallest));

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int diff = nextSmallest[i] - prevSmallest[i] - 1;
            System.out.println(i + "---" + diff + "---" + heights[i]);
            max = Math.max(max, diff * heights[i]);
        }
        return max;
    }
}
