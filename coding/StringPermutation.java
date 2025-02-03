package coding;

import java.util.HashSet;
import java.util.Set;

public class StringPermutation {

    public static void main(String[] args) {
        Set<String> list = getPermutations("123");
        System.err.println("list: " + list);
    }

    public static Set<String> getPermutations(String input) {
        return generatePermutations(input, 0);

    }

    private static Set<String> generatePermutations(String input, int index) {
        Set<String> result = new HashSet<>();
        if (index == input.length() - 1) {
            System.out.println(input);
            result.add(input);
            return result;
        }
        for (int i = index; i < input.length(); i++) {
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
