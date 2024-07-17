package coding;

import java.util.ArrayList;
import java.util.List;

public class StringPermutaion {

    public static void main(String[] args) {
        List<String> list = getPermutations("ABC");
        System.err.println("list: " + list);
    }

    public static List<String> getPermutations(String input) {
        return generatePermutations(input, 0);

    }

    private static List<String> generatePermutations(String input, int index) {
        ArrayList<String> result = new ArrayList<>();
        if (index == input.length() - 1) {
            // System.out.println(input);
            result.add(input);
            return result;
        }
        for (int i = 0; i < input.length(); i++) {
            input = swap(input, i, index);
            result.addAll(generatePermutations(input, index + 1));
            input = swap(input, i, index);
        }

        return result;
    }

    private static String swap(String input, int i, int index) {
        char arr[] = input.toCharArray();
        char temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
        return String.valueOf(arr);
    }
}
