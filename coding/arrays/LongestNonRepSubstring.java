package coding.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestNonRepSubstring {
    public static void main(String[] args) {
        String input = "abacdaedfgha";
        System.out.println("LongestNonRepSubstring: " + nonRepeatingSeq(input));
        System.out.println("LongestNonRepSubstring: " + longestUniqueSubstring(input));
        // Integer a = 1, b = 30;
        // System.out.println(b.compareTo(a));
        // String aaa = "ABCD";
        // System.out.println(aaa.substring(1));
    }

    public static String longestUniqueSubstring(String s) {
        int left = 0, right = 0;
        int maxLength = 0, start = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < s.length()) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
            right++;
        }

        return s.substring(start, start + maxLength);
    }

    public static String nonRepeatingSeq_New(String input) {
        char chars[] = input.toCharArray();
        Set<Character> charSet = new HashSet<>();
        String result = "";
        int maxLength = Integer.MIN_VALUE;
        String maxLengthStr = "";
        for (int i = 0; i < chars.length; i++) {
            if (charSet.contains(chars[i])) {
                int index = result.indexOf(chars[i]);
                if (maxLength <= result.length()) {
                    maxLength = result.length();
                    maxLengthStr = result;
                }
                result = result.substring(index + 1);
            }
            charSet.add(chars[i]);
            result += chars[i] + "";
        }
        if (maxLength <= result.length()) {
            maxLength = result.length();
            maxLengthStr = result;
        }
        return maxLengthStr;
    }

    public static String nonRepeatingSeq(String input) {
        char chars[] = input.toCharArray();
        String result = "";
        Set<Character> charsSet = new HashSet<>();
        Map<Integer, String> seqLengthToSeqMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (charsSet.contains(chars[i])) {
                seqLengthToSeqMap.put(result.length(), result);
                int index = result.indexOf(chars[i]);
                result = result.substring(index + 1);
                charsSet.remove(chars[i]);
            }

            result += (chars[i]);
            charsSet.add(chars[i]);
        }
        seqLengthToSeqMap.put(result.length(), result);

        int maxLength = Integer.MIN_VALUE;
        for (Integer len : seqLengthToSeqMap.keySet()) {
            maxLength = Math.max(maxLength, len);
        }
        return seqLengthToSeqMap.get(maxLength);
    }
}
