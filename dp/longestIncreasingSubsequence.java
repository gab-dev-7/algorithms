import java.util.Arrays;

public class longestIncreasingSubsequence {

  public static int findLIS(int[] nums) {
    if (nums == null || nums.length == 0)
      return 0;

    int n = nums.length;
    int[] dp = new int[n];

    // Initialize dp array with 1s
    Arrays.fill(dp, 1);

    int maxLIS = 1;

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
      }
      if (dp[i] > maxLIS) {
        maxLIS = dp[i];
      }
    }

    return maxLIS;
  }

  public static void main(String[] args) {
    int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
    System.out.println("Length of LIS: " + findLIS(nums)); // Expected: 4
  }
}
