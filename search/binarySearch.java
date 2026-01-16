package search;

public class binarySearch {

  public static int search(int[] arr, int target) {

    int low = 0;
    int high = arr.length - 1;
    int mid;

    while (low <= high) {
      mid = (high + low) / 2;

      // mid was already the correct index
      if (arr[mid] == target)
        return mid;

      // go right
      if (arr[mid] < target) {
        low = mid + 1;
      } else {
        // go left
        high = mid - 1;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    // The data must be sorted
    int[] data = { -5, 2, 4, 6, 9, 12, 15 };

    // Test Case 1: Searching for a value in the middle
    int target1 = 6;
    int result1 = search(data, target1);
    System.out.println(
        "Searching for " + target1 + ": " + (result1 == 3 ? "PASS" : "FAIL (Expected index 3, got " + result1 + ")"));

    // Test Case 2: Searching for a value that does not exist
    int target2 = 10;
    int result2 = search(data, target2);
    System.out.println(
        "Searching for " + target2 + ": " + (result2 == -1 ? "PASS" : "FAIL (Expected -1, got " + result2 + ")"));

    // Test Case 3: Searching for a boundary value (start)
    int target3 = -5;
    int result3 = search(data, target3);
    System.out.println(
        "Searching for " + target3 + ": " + (result3 == 0 ? "PASS" : "FAIL (Expected index 0, got " + result3 + ")"));
  }
}
