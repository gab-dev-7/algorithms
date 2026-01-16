package dp;

public class jumpGame {

  public static boolean canJump(int[] nums) {
    int n = nums.length;

    // dp[i] will be true if index i is reachable
    boolean[] dp = new boolean[n];

    // Base case:
    dp[0] = true;

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        // j is reachable & the jth jump reaches ith index
        if (dp[j] == true && nums[j] + j >= i) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n - 1];
  }

  public static void main(String[] args) {
    int[] nums1 = { 2, 3, 1, 1, 4 }; // Should be true
    int[] nums2 = { 3, 2, 1, 0, 4 }; // Should be false

    System.out.println("Can reach end (1): " + canJump(nums1));
    System.out.println("Can reach end (2): " + canJump(nums2));
  }
}
