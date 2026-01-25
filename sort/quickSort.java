
import java.util.Arrays;

public class quickSort {

  public static void sort(int[] arr) {
    if (arr == null || arr.length == 0)
      return;
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(int[] arr, int low, int high) {
    if (low < high) {
      // Partition index
      int pi = partition(arr, low, high);

      // Sort elements before partition
      quickSort(arr, low, pi - 1);

      // Sort elements after partition
      quickSort(arr, pi + 1, high);
    }
  }

  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        i++;
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
      }
    }

    // Place pivot in correct position
    int temp2 = arr[high];
    arr[high] = arr[i + 1];
    arr[i + 1] = temp2;

    return i + 1;
  }

  public static void main(String[] args) {
    int[] data = { 10, 7, 8, 9, 1, 5 };
    System.out.println("Original Array: " + Arrays.toString(data));

    sort(data);

    System.out.println("Sorted Array:   " + Arrays.toString(data));

    // Verification
    int[] expected = { 1, 5, 7, 8, 9, 10 };
    System.out.println("Sort Successful: " + Arrays.equals(data, expected));
  }
}
