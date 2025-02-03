package coding;

import java.util.Arrays;

public class ReverseArrayRecursion {

    public static void main(String[] args) {
        int[] input = { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(input));
        reverseArray(input, 0);
        System.out.println(Arrays.toString(input));
    }

    public static void reverseArray(int[] input, int index) {
        if (index == input.length / 2)
            return;
        int temp = input[index];
        input[index] = input[input.length - 1 - index];
        input[input.length - 1 - index] = temp;
        reverseArray(input, index + 1);
    }

}
