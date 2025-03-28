package coding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneCharDiff {
    public static List<Integer> findExtraCharacterIndices(String str1, String str2) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0, start = 0;
        char prev = ' ';

        // Traverse through both strings
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                // Character in str1 at index i is extra, so add it to the result
                // result.add(i);
                int k = 0;
                while (start + k < i) {
                    result.add(start + k);
                    k++;
                }
                i++;
            }
            if (prev != str1.charAt(i)) {
                start = i;
            }
            prev = str1.charAt(i);
        }
        int k = 0;
        while (start + k < str1.length()) {
            result.add(start + k);
            k++;
        }

        // If there are any remaining characters in str1, they are extra
        // while (i < str1.length()) {
        // result.add(i);
        // i++;
        // }
        Arrays.asList(new int[] { 1 });
        return result.isEmpty() ? List.of(-1) : result;
    }

    public static void main(String[] args) {
        // Example 1: str1 = "aabbb", str2 = "aabb"
        System.out.println(findExtraCharacterIndices("abcd", "abc")); // Output: [2, 3, 4]
    }
}
