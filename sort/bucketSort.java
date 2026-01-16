import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bucketSort {

  public static void bucketSort(float[] arr, int n) {
    if (n <= 0)
      return;

    // ArrayLists to represent buckets
    @SuppressWarnings("unchecked")
    List<Float>[] buckets = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      buckets[i] = new ArrayList<>();
    }

    for (int i = 0; i < n; i++) {
      // Calculate index
      int bucketIndex = (int) (arr[i] * n);

      if (bucketIndex >= n) {
        bucketIndex = n - 1;
      }

      buckets[bucketIndex].add(arr[i]);
    }

    // Sort individual buckets
    for (int i = 0; i < n; i++) {
      Collections.sort(buckets[i]);
    }

    // Concatenate all buckets into arr[]
    int index = 0;
    for (int i = 0; i < n; i++) {
      for (float val : buckets[i]) {
        arr[index++] = val;
      }
    }
  }

  public static void main(String[] args) {
    float[] arr = { 0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f };
    int n = arr.length;

    System.out.println("Original Array:");
    printArray(arr);

    bucketSort(arr, n);

    System.out.println("\nSorted Array:");
    printArray(arr);
  }

  private static void printArray(float[] arr) {
    for (float num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }
}
