package coding;

import java.util.Stack;

public class EvaluateExpression {
    public static void main(String[] args) {
        System.out.println("Res: " + evaluateExpression("(1+2)-(1+2)"));
    }

    public static int evaluateExpression(String exp) {
        char arr[] = exp.toCharArray();
        int num = 0, sign = 1, total = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (isDigit(arr[i])) {
                num = arr[i] - '0';
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
                total += prevSign * prevTotal;
                total += sign * num;
                num = 0;
            }
        }
        return total;// + sign * num;
    }

    private static boolean isDigit(char c) {
        int diff = ((int) c) - '0';
        return diff <= 9 && diff >= 0;
    }
}
