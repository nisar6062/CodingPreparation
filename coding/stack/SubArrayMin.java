package coding.stack;

import java.util.Arrays;
import java.util.Stack;

public class SubArrayMin {
    public static void main(String[] args) {
        int arr[] = { 3, 1, 2, 4 };
        int result = subArrayMinSum(arr);
        System.out.println("result: " + result);
    }

    // 2.41
    public static int subArrayMinSum(int[] arr) {
        int nse[] = getNextSmallestElementArr(arr);
        int pse[] = getPreviousSmallestElementArr(arr);
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int right = nse[i] == -1 ? arr.length - i : nse[i] - i;
            int left = i - pse[i];
            sum += (right * left * arr[i]);
        }
        return sum;
    }

    public static int[] getNextSmallestElementArr(int[] arr) {
        int result[] = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.empty())
                result[i] = -1;
            else
                result[i] = stack.peek();
            stack.push(i);

        }
        System.out.println("Next_Small: " + Arrays.toString(result));
        return result;
    }

    public static int[] getPreviousSmallestElementArr(int[] arr) {
        int result[] = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (stack.empty())
                result[i] = -1;
            else
                result[i] = stack.peek();
            stack.push(i);

        }
        System.out.println("Prev_Small: " + Arrays.toString(result));
        return result;
    }
}
