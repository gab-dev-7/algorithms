public class fibonacci {

  // Recursive approach
  public static int fibRecursive(int n) {
    if (n <= 1)
      return n;
    return fibRecursive(n - 1) + fibRecursive(n - 2);
  }

  // Memoization (Top-Down)
  public static int fibMemo(int n, int[] memo) {

    if (n <= 1)
      return n;

    if (memo[n] == 0) {
      memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }

    return memo[n];
  }

  // Tabulation (Bottom-Up)
  public static int fibTab(int n) {

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
    System.out.println("Fibonacci of " + n + " is: " + fibTab(n));
  }
}
