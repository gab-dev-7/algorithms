public class longestCommonSubsequence {

  public static int findLCS(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          // one character matches
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          // lcs without one char from either s1 | s2
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[n][m];
  }

  public static void main(String[] args) {
    String s1 = "abcde";
    String s2 = "ace";
    System.out.println("Length of LCS: " + findLCS(s1, s2)); // Expected: 3 ("ace")
  }
}
