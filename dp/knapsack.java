
public class knapsack {

  public static int solveKnapsack(int[] weights, int[] values, int capacity) {

    int n = weights.length;

    // dp[item][weight]
    int[][] dp = new int[n + 1][capacity + 1];

    // Bottom-up approach
    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= capacity; w++) {
        // Base Case: No items or 0 capacity
        if (i == 0 || w == 0) {
          dp[i][w] = 0;
        }
        // If we still haven't reached capacity w
        else if (weights[i - 1] <= w) {
          // MAX of (Take it) or (Leave it)
          dp[i][w] = Math.max(
              values[i - 1] + dp[i - 1][w - weights[i - 1]],
              dp[i - 1][w]);
        } else {
          // Weight is too much, must leave it
          dp[i][w] = dp[i - 1][w];
        }
      }
    }
    return dp[n][capacity];
  }

  public static void main(String[] args) {
    int[] values = { 60, 100, 120 };
    int[] weights = { 10, 20, 30 };
    int capacity = 50;

    System.out.println("Max Value: " + solveKnapsack(weights, values, capacity));
  }
}
