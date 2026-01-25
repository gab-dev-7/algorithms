
import java.util.Arrays;

public class coinChange {

  public static int minCoins(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    Arrays.fill(dp, amount + 1);

    // Base case:
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (i - coin >= 0) {
          // recursion
          dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
        }
      }
    }

    // If amont == 'amount + 1', it means the we never changed it.
    return dp[amount] > amount ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    int[] coins = { 1, 2, 5 };
    int amount = 11;
    System.out.println("Min coins for " + amount + ": " + minCoins(coins, amount)); // Should be 3 (5+5+1)
  }
}
