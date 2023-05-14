public class knapsack {

  public static int max(int a, int... b) {
    int maxValue = a;
    for (int value : b) {
      maxValue = (value > maxValue) ? value : maxValue;
    }
    return maxValue;
  }

  public static void main(String[] args) {
    int[] profits = { 0, 2, 3, 6, 3, 4, 5 };
    int[] weights = { 0, 2, 1, 6, 4, 2, 5 };

    final int MAX_CAPACITY = 10;
    final int NUM_ITEMS = 6;

    int[][] maxProfits = new int[NUM_ITEMS + 1][MAX_CAPACITY + 1];

    for (int i = 0; i <= NUM_ITEMS; i++) {
      for (int j = 0; j <= MAX_CAPACITY; j++) {
        if (i == 0 || j == 0) {
          maxProfits[i][j] = 0;
        } else if (2 * weights[i] <= j) {
          maxProfits[i][j] = max(maxProfits[i-1][j], maxProfits[i-1][j-weights[i]] + profits[i], maxProfits[i-1][j-2*weights[i]] + 2*profits[i]);
        } else if (weights[i] <= j) {
          maxProfits[i][j] = max(maxProfits[i-1][j], maxProfits[i-1][j-weights[i]] + profits[i]);
        } else {
          maxProfits[i][j] = maxProfits[i-1][j];
        }
      }
    }

    for (int i = 0; i < maxProfits.length; i++) {
      for (int j = 0; j < maxProfits[i].length; j++) {
        System.out.print(maxProfits[i][j] + " ");
      }
      System.out.println();
    }
  }
}