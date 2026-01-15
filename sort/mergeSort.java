import java.util.Arrays;

public class mergeSort {

  public static void sort(int[] arr) {
    if (arr == null || arr.length <= 1)
      return;

    int[] temp = new int[arr.length];
    mergeSort(arr, temp, 0, arr.length - 1);
  }

  private static void mergeSort(int[] arr, int[] temp, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;

      // Sort the left half recursively
      mergeSort(arr, temp, left, mid);

      // Sort the right half recursively
      mergeSort(arr, temp, mid + 1, right);

      // Merge the two sorted halves
      merge(arr, temp, left, mid, right);
    }
  }

  private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
    int i = left;
    int j = mid + 1;
    int k = left;

    // merge items together
    while (i <= mid && j <= right) {
      if (arr[i] <= arr[j]) {
        temp[k] = arr[i];
        i++;
      } else {
        temp[k] = arr[j];
        j++;
      }
      k++;
    }

    // copy extra elements if one array smaller than the other
    while (i <= mid) {
      temp[k] = arr[i];
      i++;
      k++;
    }

    while (j <= right) {
      temp[k] = arr[j];
      j++;
      k++;
    }

    for (i = left; i <= right; i++) {
      arr[i] = temp[i];
    }
  }

  public static void main(String[] args) {
    int[] data = { 38, 27, 43, 3, 9, 82, 10 };
    System.out.println("Original Array: " + Arrays.toString(data));

    sort(data);

    System.out.println("Sorted Array:   " + Arrays.toString(data));

    // Verification
    int[] expected = { 3, 9, 10, 27, 38, 43, 82 };
    System.out.println("Sort Successful: " + Arrays.equals(data, expected));
  }
}
