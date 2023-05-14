import java.util.Random;

public class MS {

  public static void main(String[] args) {

    final int M = 10;
    int arr[] = new int[M];
    final int kth = 0;
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
      results[i] = mergeSort(arr, 0, arr.length - 1, kth);
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

  public static void merge(int arr[], int left, int middle, int right) {
    int temp[] = new int[right - left + 1];
    int i = left;
    int j = middle + 1;
    int k = 0;

    while (i <= middle && j <= right) {
      if (arr[i] <= arr[j]) {
        temp[k] = arr[i];
        i++;
      } else {
        temp[k] = arr[j];
        j++;
      }
      k++;
    }

    while (i <= middle && j <= right) {
      if (arr[i] <= arr[j]) {
        temp[k] = arr[i];
        i++;
      } else {
        temp[k] = arr[j];
        j++;
      }
      k++;
    }

    while (i <= middle) {
      temp[k] = arr[i];
      i++;
      k++;
    }

    while (j <= right) {
      temp[k] = arr[j];
      j++;
      k++;
    }

    for (k = 0; k < temp.length; k++) {
      arr[left + k] = temp[k];
    }
  }

  public static int mergeSort(int arr[], int left, int right, int k) {
    if (left < right) {
      int middle = (left + right) / 2;

      mergeSort(arr, left, middle, k);
      mergeSort(arr, middle + 1, right, k);
      merge(arr, left, middle, right);
    }

    if (left <= k - 1 && k - 1 <= right) {
      return arr[k - 1];
    }
    return -1;
  }
}