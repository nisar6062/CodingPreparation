package coding.dynamic_programming;

public class CoinChange {

    public static void main(String[] args) {
        int coins[] = { 7, 8, 3, 2 };
        int target = 13;
        System.out.println("Min: " + getMinNoOfCoins(coins, 0, target, 0));
    }

    public static int getMinNoOfCoins(int[] coins, int index, int target, int count) {
        if (index >= coins.length || target < 0 || target == 0) {
            if (target == 0) {
                return count;
            }
            return Integer.MAX_VALUE;
        }
        System.out.println("index: " + index + ", coins[index]: " + coins[index] + ", target: " + target);
        int minCount = getMinNoOfCoins(coins, index + 1, target, count);
        if (coins[index] <= target) {
            minCount = Math.min(minCount, getMinNoOfCoins(coins, index, target - coins[index], count + 1));
        } else {
            minCount = Math.min(minCount, getMinNoOfCoins(coins, index + 1, target - coins[index], count + 1));
        }
        // minCount = Math.min(minCount, getMinNoOfCoins(coins, index + 1, target,
        // count));
        return minCount;
    }

}
