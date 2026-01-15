import java.util.Arrays;

public class bubbleSort {

  public static void sort(int[] arr) {

    // while 'j' > 'j+1' bubble it up
    for (int i = 0; i < arr.length; i++) {
      boolean swapped = false;
      for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j + 1]) {
          // swap
          int temp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = temp;
          swapped = true;
        }
      }
      // if we didnt swap anything -> it is sorted
      if (!swapped)
        break;
    }
  }

  public static void main(String[] args) {
    int[] data = { 64, 34, 25, 12, 22, 11, 90 };
    System.out.println("Original Array: " + Arrays.toString(data));

    sort(data);

    System.out.println("Sorted Array:   " + Arrays.toString(data));

    // Verification
    int[] expected = { 11, 12, 22, 25, 34, 64, 90 };
    System.out.println("Sort Successful: " + Arrays.equals(data, expected));
  }
}
