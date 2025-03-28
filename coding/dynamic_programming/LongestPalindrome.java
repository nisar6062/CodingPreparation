package coding.dynamic_programming;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println("Result : " + longestPalindrome("babad"));
    }

    public static String longestPalindrome(String input) {
        int n = input.length();
        boolean dp[][] = new boolean[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = true;

        int start = 0, maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len - 1;
                if (input.charAt(i) == input.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLength = len;
                }
            }
        }
        return input.substring(start, start + maxLength);
    }

}
