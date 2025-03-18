package coding.arrays;

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
        System.out.println("decode: " + decode("3[a]2[bc]"));
    }

    public static String decode(String input) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        char arr[] = input.toCharArray();
        int num = 0;
        String currStr = "";
        for (int i = 0; i < arr.length; i++) {
            if (Character.isDigit(arr[i])) {
                num = num * 10 + (arr[i] - '0');
            } else if (arr[i] == '[') {
                numStack.push(num);
                strStack.push(currStr);
                currStr = "";
                num = 0;
            } else if (arr[i] == ']') {
                int n = numStack.pop();
                String s = strStack.pop();
                while (n-- > 0) {
                    s += currStr;
                }
                currStr = s;
            } else {
                currStr += arr[i];
            }
        }
        return currStr;
    }
}