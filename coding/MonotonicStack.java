package coding;

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {
    public static void main(String[] args) {
        int arr[] = { 2, 1, 2, 4, 3 };
        int result[] = getPreviousSmallestElement(arr);
        System.out.println("Res: " + Arrays.toString(result));
    }

    public static int[] getNextGreatestElement(int[] input) {
        int[] result = new int[input.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = input.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                result[i] = -1;
                stack.add(i);
                continue;
            }
            int indexInStack = stack.peek();
            while (input[indexInStack] <= input[i]) {
                stack.pop();
                if (stack.isEmpty())
                    break;
                indexInStack = stack.peek();
            }
            if (stack.isEmpty())
                result[i] = -1;
            else
                result[i] = indexInStack;
            stack.add(i);
        }
        return result;
    }

    public static int[] getPreviousSmallestElement(int[] input) {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            if (stack.isEmpty()) {
                result[i] = -1;
                stack.add(i);
                continue;
            }
            int numInStack = stack.peek();
            while (!stack.isEmpty() && input[numInStack] > input[i]) {
                stack.pop();
                if (stack.isEmpty())
                    break;
                numInStack = stack.peek();
            }
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = input[numInStack];
            }
            stack.add(i);
        }

        return result;

    }

}
