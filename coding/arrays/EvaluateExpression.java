package coding.arrays;

import java.util.PriorityQueue;
import java.util.Stack;

public class EvaluateExpression {
    public static void main(String[] args) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        System.out.println("Res: " + evaluateExpression("-(1+2)+(8+2)"));
    }

    public static int evaluateExpression(String exp) {
        char arr[] = exp.toCharArray();
        int num = 0, sign = 1, total = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                num = num * 10 + (arr[i] - '0');
            } else if (arr[i] == '+') {
                total += sign * num;
                sign = 1;
                num = 0;
            } else if (arr[i] == '-') {
                total += sign * num;
                sign = -1;
                num = 0;
            } else if (arr[i] == '(') {
                stack.add(sign);
                stack.add(total);
                total = 0;
                sign = 1;
            } else if (arr[i] == ')') {
                int prevTotal = stack.pop();
                int prevSign = stack.pop();
                total += sign * num;
                total *= prevSign;
                total += prevTotal;
                num = 0;
            }
        }
        return total;
    }
}
