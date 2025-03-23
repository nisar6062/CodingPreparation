package coding.dynamic_programming;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        System.out.println(commonSeq("abc", "abe"));
    }

    public static int commonSeq(String input1, String input2) {
        int m = input1.length();
        int n = input2.length();
        int dp[][] = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
