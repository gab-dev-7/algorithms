
public class maxSubarraySum {

  public static int findMaxSum(int[] nums) {
    if (nums == null || nums.length == 0)
      return 0;

    int currentMax = nums[0];
    int totalMax = nums[0];

    for (int i = 1; i < nums.length; i++) {
      currentMax = Math.max(nums[i], currentMax + nums[i]);
      totalMax = Math.max(totalMax, currentMax);
    }

    return totalMax;
  }

  public static void main(String[] args) {
    int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
    System.out.println("Maximum Subarray Sum: " + findMaxSum(nums)); // Expected: 6
  }
}
