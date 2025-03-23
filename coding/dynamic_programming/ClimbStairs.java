package coding.dynamic_programming;

public class ClimbStairs {

    public static void main(String[] args) {
        int n = 4;
        System.out.println(climbStairs(n, new int[n + 1]));
        System.out.println(climbStairsIter(n));
    }

    public static int climbStairs(int n, int[] dp) {
        if (n <= 2)
            return n;
        if (dp[n] != 0)
            return dp[n];

        dp[n] = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
        return dp[n];
    }

    public static int climbStairsIter(int n) {
        if (n <= 2)
            return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
