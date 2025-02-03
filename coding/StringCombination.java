package coding;

import java.util.ArrayList;
import java.util.List;

public class StringCombination {
    public static void main(String[] args) {
        List<String> list = getCombinations("ABC");
        System.err.println("list: " + list);
    }

    public static List<String> getCombinations(String input) {
        return generateCombinations(input, 0, new ArrayList<>());
    }

    private static List<String> generateCombinations(String input, int index, List<Character> charArr) {
        ArrayList<String> result = new ArrayList<>();
        if (index == input.length()) {
            System.out.println("res: " + charArr);
            StringBuilder sb = new StringBuilder();
            for (Character ch : charArr) {
                sb.append(ch);
            }

            result.add(sb.toString());
            return result;
        }

        charArr.add(input.charAt(index));
        result.addAll(generateCombinations(input, index + 1, charArr));
        charArr.remove(charArr.size() - 1);
        result.addAll(generateCombinations(input, index + 1, charArr));

        return result;
    }
}
