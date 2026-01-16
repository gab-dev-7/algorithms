package dp;

public class fibonacci {

  // Recursive approach
  public static int fibRecursive(int n) {
    if (n <= 1)
      return n;
    return fibRecursive(n - 1) + fibRecursive(n - 2);
  }

  // Memoization (Top-Down) - Wrapper method
  public static int fibMemo(int n) {
      if (n < 0) return -1; // Or throw an exception
      if (n == 0) return 0;
      int[] memo = new int[n + 1];
      return fibMemo(n, memo);
  }

  private static int fibMemo(int n, int[] memo) {
    if (n <= 1)
      return n;

    if (memo[n] == 0) {
      memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }

    return memo[n];
  }

  // Tabulation (Bottom-Up)
  public static int fibTab(int n) {
    if (n < 0) return -1; // Or throw an exception
    if (n <= 1) return n;

    int[] f = new int[n + 1];
    f[0] = 0;
    f[1] = 1;

    for (int i = 2; i <= n; i++) {
      f[i] = f[i - 1] + f[i - 2];
    }

    return f[n];
  }

  public static void main(String[] args) {
    int n = 10;
    System.out.println("For n = " + n);
    System.out.println("Recursive: " + fibRecursive(n));
    System.out.println("Memoization: " + fibMemo(n));
    System.out.println("Tabulation: " + fibTab(n));
    
    // A larger n to show the performance difference
    int n_large = 40;
    System.out.println("\nFor n = " + n_large);

    long startTime = System.nanoTime();
    int recursiveResult = fibRecursive(n_large);
    long endTime = System.nanoTime();
    System.out.println("Recursive: " + recursiveResult + " (took " + (endTime - startTime) / 1000000 + " ms)");

    startTime = System.nanoTime();
    int memoResult = fibMemo(n_large);
    endTime = System.nanoTime();
    System.out.println("Memoization: " + memoResult + " (took " + (endTime - startTime) / 1000000 + " ms)");

    startTime = System.nanoTime();
    int tabResult = fibTab(n_large);
    endTime = System.nanoTime();
    System.out.println("Tabulation: " + tabResult + " (took " + (endTime - startTime) / 1000000 + " ms)");
  }
}