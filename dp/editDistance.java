package dp;

public class editDistance {

  public static int minDistance(String str1, String str2) {
    int n = str1.length();
    int m = str2.length();

    // dp[i][j]
    int[][] dp = new int[n + 1][m + 1];

    // Base cases:
    for (int i = 0; i <= n; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= m; j++) {
      dp[0][j] = j;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i][j - 1], // insert
              Math.min(dp[i - 1][j], // delete
                  dp[i - 1][j - 1])); // replace
        }
      }
    }

    return dp[n][m];
  }

  public static void main(String[] args) {
    String s1 = "horse";
    String s2 = "ros";
    System.out.println("Edit Distance: " + minDistance(s1, s2)); // Expected: 3
  }
}
