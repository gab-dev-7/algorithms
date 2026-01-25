
import java.util.Arrays;

public class insertionSort {

  public static void sort(int[] arr) {

    // we keep a sorted subset on the left and each
    // pass add one element into the sorted portion.
    for (int i = 1; i < arr.length; i++) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        // swap
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }

  }

  public static void main(String[] args) {
    int[] data = { 12, 11, 13, 5, 6 };
    System.out.println("Original Array: " + Arrays.toString(data));

    sort(data);

    System.out.println("Sorted Array:   " + Arrays.toString(data));

    // Verification
    int[] expected = { 5, 6, 11, 12, 13 };
    System.out.println("Sort Successful: " + Arrays.equals(data, expected));
  }
}
