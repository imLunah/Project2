import java.util.Arrays;
import java.util.Random;

public class MedianOfMedians {
    public static int kthSmallest(int[] arr, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int n = right - left + 1;
            int i;
            int[] median = new int[(n + 4) / 5];

            for (i = 0; i < n / 5; i++) {
                median[i] = findMedian(arr, left + i * 5, 5);
            }

            if (i * 5 < n) {
                median[i] = findMedian(arr, left + i * 5, n % 5);
                i++;
            }

            int medOfMed = (i == 1) ? median[i - 1] : kthSmallest(median, 0, i - 1, i / 2);

            int position = partition(arr, left, right, medOfMed);

            if (position - left == k - 1) {
                return arr[position];
            } else if (position - left > k - 1) {
                return kthSmallest(arr, left, position - 1, k);
            } else {
                return kthSmallest(arr, position + 1, right, k - position + left - 1);
            }
        }

        return Integer.MAX_VALUE;
    }

    public static int findMedian(int[] arr, int left, int n) {
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[i] = arr[left + i];
        }

        Arrays.sort(temp);

        return temp[n / 2];
    }

    public static int partition(int[] arr, int left, int right, int x) {
        int i;
        for (i = left; i < right; i++) {
            if (arr[i] == x) {
                break;
            }
        }

        swap(arr, i, right);

        i = left;
        for (int j = left; j <= right - 1; j++) {
            if (arr[j] <= x) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);

        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        final int M = 10;
        int arr[] = new int[M];
        final int kth = 2;
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
            results[i] = kthSmallest(arr, 0, arr.length - 1, kth);
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
