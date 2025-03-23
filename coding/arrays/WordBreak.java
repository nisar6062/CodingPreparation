package coding.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WordBreak {
    public static void main(String[] args) {
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println("Result: " + wordBreak("catsanddog", wordDict));
        System.out.println("Is: " + wordBreakDp_New("catsanddog", wordDict));
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, 0, new StringBuilder(), wordDict);
    }

    private static boolean wordBreakDp_New(String s, List<String> words) {
        boolean dp[] = new boolean[s.length() + 1];

        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    private static boolean wordBreakDp(String s, List<String> words) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    System.out.println(s.substring(j, i));
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    private static List<String> wordBreak(String s, int index, StringBuilder currStr, List<String> wordDict) {
        if (index == s.length()) {
            if (currStr.length() >= s.length()) {
                return Arrays.asList(currStr.toString());
            }
            return new ArrayList<>();
        }

        StringBuilder word = new StringBuilder();
        List<String> result = new ArrayList<>();
        for (int i = index; i < s.length(); i++) {
            word.append(String.valueOf(s.charAt(i)));

            if (wordDict.contains(word.toString())) {
                StringBuilder newCurrStr = new StringBuilder();
                result.addAll(wordBreak(s, i + 1, newCurrStr.append(currStr).append(" ").append(word), wordDict));
            }
        }
        return result;
    }
}