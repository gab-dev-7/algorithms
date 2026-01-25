
public class linearSearch {

  public static int search(int[] arr, int target) {

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target) {
        return i;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[] data = { 5, 3, 8, 4, 2, 7, 1, 9 };

    // Test Case 1: Searching for a value that exists
    int target1 = 4;
    int result1 = search(data, target1);
    System.out.println(
        "Searching for " + target1 + ": " + (result1 == 3 ? "PASS" : "FAIL (Expected index 3, got " + result1 + ")"));

    // Test Case 2: Searching for a value that does not exist
    int target2 = 10;
    int result2 = search(data, target2);
    System.out.println(
        "Searching for " + target2 + ": " + (result2 == -1 ? "PASS" : "FAIL (Expected -1, got " + result2 + ")"));

    // Test Case 3: Searching for the first element (Edge case)
    int target3 = 5;
    int result3 = search(data, target3);
    System.out.println(
        "Searching for " + target3 + ": " + (result3 == 0 ? "PASS" : "FAIL (Expected index 0, got " + result3 + ")"));
  }
}
