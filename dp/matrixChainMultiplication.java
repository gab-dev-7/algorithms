public class matrixChainMultiplication {

  public static int minCost(int[] p) {
    int n = p.length - 1;
    int[][] dp = new int[n + 1][n + 1];

    for (int l = 2; l <= n; l++) {
      for (int i = 1; i <= n - l + 1; i++) {
        int j = i + l - 1;
        dp[i][j] = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
          int q = dp[i][k] + dp[k + 1][j] + (p[i - 1] * p[k] * p[j]);
          if (q < dp[i][j]) {
            dp[i][j] = q;
          }
        }
      }
    }
    return dp[1][n];
  }

  public static void main(String[] args) {
    int[] arr = { 10, 30, 5, 60 };
    System.out.println("Minimum cost: " + minCost(arr)); // Expected: 4500
  }
}
