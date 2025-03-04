package coding.stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MonotonicStack {
    public static void main(String[] args) {
        Integer arr_1[] = { 1, 3, 4 };
        List<Integer> list_2 = Arrays.asList(arr_1);
        System.out.println("binarySearch:" + Collections.binarySearch(list_2, 3));
        // int arr[] = { 2, 1, 2, 4, 3 };
        // int result1[] = getNextGreatestElement(arr);
        // System.out.println("Previous Smallest: " + Arrays.toString(result1));
        // int result2[] = getNextGreatestElement_New(arr);
        // System.out.println("Next Greatest: " + Arrays.toString(result2));
    }

    // 21:25
    public static int[] getNextGreatestElement_New(int[] input) {
        Stack<Integer> stack = new Stack<>();
        int result[] = new int[input.length];
        for (int i = input.length - 1; i >= 0; i--) {
            if (stack.empty()) {
                stack.push(i);
                result[i] = -1;
                continue;
            }
            int index = stack.peek();
            while (!stack.empty() && input[index] <= input[i]) {
                stack.pop();// nairah
                if (stack.isEmpty())
                    break;
                index = stack.peek();
            }
            if (stack.empty()) {
                result[i] = -1;
            } else
                result[i] = input[stack.peek()];
            stack.push(i);
            System.out.println(stack);
        }
        return result;
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
                result[i] = input[indexInStack];
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
