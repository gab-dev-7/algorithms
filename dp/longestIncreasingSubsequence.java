package dp;

import java.util.Arrays;
import java.util.ArrayList;

public class longestIncreasingSubsequence {

  // O(n^2) solution
  public static int findLIS_DP(int[] nums) {
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
  
  // O(n log n) solution
  public static int findLIS_Optimized(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }

    ArrayList<Integer> tails = new ArrayList<>();
    tails.add(nums[0]);

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] < tails.get(0)) {
            tails.set(0, nums[i]);
        } else if (nums[i] > tails.get(tails.size() - 1)) {
            tails.add(nums[i]);
        } else {
            int l = 0, r = tails.size() - 1;
            while (l < r) {
                int m = (l + r) / 2;
                if (tails.get(m) < nums[i]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            tails.set(r, nums[i]);
        }
    }

    return tails.size();
  }

  public static void main(String[] args) {
    int[] nums = { 10, 9, 2, 5, 3, 7, 101, 18 };
    System.out.println("--- O(n^2) DP Solution ---");
    System.out.println("Length of LIS: " + findLIS_DP(nums)); // Expected: 4

    System.out.println("\n--- O(n log n) Optimized Solution ---");
    System.out.println("Length of LIS: " + findLIS_Optimized(nums)); // Expected: 4
  }
}