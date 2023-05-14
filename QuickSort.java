import java.util.Random;

public class QuickSort {

  public static int quicksort(int A[], int n, int k) {
    int M = 0;
    int J = n - 1;

    while (true) {
      int pivot_p = partition(A, M, J);
      if (k == pivot_p) {
        return A[k];
      } else if (k < pivot_p) {
        J = pivot_p - 1;
      } else {
        M = pivot_p + 1;
        k -= pivot_p - M + 1;
      }
    }
  }

  public static int partition(int A[], int M, int J) {
    int pivot = A[M];
    int i = M + 1;
    int j = J;

    while (true) {
      while (i <= j && A[j] >= pivot) {
        j--;
      }
      while (i <= j && A[i] <= pivot) {
        i++;
      }
      if (i <= j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
      } else {
        break;
      }
    }
    int temp = A[M];
    A[M] = A[j];
    A[j] = temp;
    return j;
  }

  public static void main(String[] args) {
    final int M = 20000;
    int arr[] = new int[M];
    final int kth = 3;
    long times[] = new long[M];
    int results[] = new int[M];
    long totalTime = 0;
    Random rand = new Random();

    System.out.println("The array is: ");

    for (int i = 0; i < arr.length; i++) {
      arr[i] = rand.nextInt(101);
      System.out.print(arr[i] + ",");
    }
    System.out.println();

    for (int i = 0; i < M; i++) {
      long startTime = System.nanoTime();
      results[i] = quicksort(arr, arr.length - 1, kth);
      long endTime = System.nanoTime();

      times[i] = endTime - startTime;
      totalTime += times[i];

    }

    for (int i = 0; i < M; i++) {
      System.out.println("The kth value is: " + results[i]);
      System.out.println("The time taken in nanoseconds: " + times[i]);
    }
    System.out.println("The average time is: " + totalTime / M);

  }
}
