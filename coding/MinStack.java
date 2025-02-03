package coding;

import java.util.Arrays;
import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        int arr[] = { 4, 6, 1, 9, 0 };
        // Stack<Integer> res = getMinStack(arr);
        // System.out.println("res: " + res);

        int nums[] = { 3, 1, 2, 4 };
        System.out.println("res: " + nextSmallerElement(nums));
    }

    public static Stack<Integer> getMinStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            if (!stack.isEmpty()) {
                min = stack.peek();
            }
            if (min > arr[i]) {
                stack.add(arr[i]);
            }
        }

        return stack;
    }

    public static int[] nextSmallerElement(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int nse[] = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }

        System.out.println("nse: " + Arrays.toString(nse));
        System.out.println("stack: " + stack);
        return nse;
    }
}
