package coding.cyclicsort;

import java.util.Arrays;

public class FindDuplicate {
    public static void main(String[] args) {
        int arr[] = { 3, 1, 3, 4, 2 };
        int result = findDuplicate(arr);
        System.out.println("Res: " + result);

    }

    public static int findDuplicate(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[input[i]] < 0) {
                return input[i];
            }
            input[input[i]] = -input[input[i]];
            System.out.println(Arrays.toString(input));
        }
        return -1;
    }
}
