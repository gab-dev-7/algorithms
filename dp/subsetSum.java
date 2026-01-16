package dp;

public class subsetSum {

  public static boolean isSumPossible(int[] set, int targetSum) {
    int n = set.length;
    boolean[][] dp = new boolean[n + 1][targetSum + 1];

    for (int i = 0; i <= n; i++)
      dp[i][0] = true;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= targetSum; j++) {
        if (set[i - 1] > j) {
          // Number is too big, must exclude it
          dp[i][j] = dp[i - 1][j];
        } else {
          // exclude it or include it
          dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
        }
      }
    }

    return dp[n][targetSum];
  }

  public static void main(String[] args) {
    int[] set = { 3, 34, 4, 12, 5, 2 };
    int sum = 9;
    System.out.println("Is sum possible? " + isSumPossible(set, sum)); // Expected: true
  }
}
