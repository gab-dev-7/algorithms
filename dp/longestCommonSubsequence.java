
public class longestCommonSubsequence {

  public static int[][] createLCSTable(String s1, String s2) {
    int n = s1.length();
    int m = s2.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp;
  }

  public static int findLCSLength(String s1, String s2, int[][] dp) {
      return dp[s1.length()][s2.length()];
  }

  public static String findLCS(String s1, String s2, int[][] dp) {
    int n = s1.length();
    int m = s2.length();

    StringBuilder lcs = new StringBuilder();
    int i = n, j = m;
    while (i > 0 && j > 0) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            lcs.append(s1.charAt(i - 1));
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            i--;
        } else {
            j--;
        }
    }
    return lcs.reverse().toString();
  }

  public static void main(String[] args) {
    String s1 = "abcde";
    String s2 = "ace";
    int[][] dpTable = createLCSTable(s1, s2);
    System.out.println("Length of LCS: " + findLCSLength(s1, s2, dpTable)); // Expected: 3
    System.out.println("LCS: " + findLCS(s1, s2, dpTable)); // Expected: "ace"
  }
}