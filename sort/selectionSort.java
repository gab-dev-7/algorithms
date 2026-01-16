package sort;

import java.util.Arrays;

public class selectionSort {

  public static void sort(int[] arr) {

    // track min element each pass and put it in the i-position``
    for (int i = 0; i < arr.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[minIndex]) {
          minIndex = j;
        }
      }
      // swap
      int temp = arr[minIndex];
      arr[minIndex] = arr[i];
      arr[i] = temp;
    }

  }

  public static void main(String[] args) {
    int[] data = { 64, 25, 12, 22, 11 };
    System.out.println("Original Array: " + Arrays.toString(data));

    sort(data);

    System.out.println("Sorted Array:   " + Arrays.toString(data));

    // Verification
    int[] expected = { 11, 12, 22, 25, 64 };
    System.out.println("Sort Successful: " + Arrays.equals(data, expected));
  }
}
