import java.util.Scanner;

public class QuickSortHoare {

    static int hoarePartition(int arr[], int low, int high) {

        int pivot = arr[low];

        int i = low - 1;
        int j = high + 1;

        while (true) {

            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j)
                return j;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    static void quickSort(int arr[], int low, int high) {

        if (low < high) {

            int p = hoarePartition(arr, low, high);

            quickSort(arr, low, p);
            quickSort(arr, p + 1, high);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of parcel weights: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter parcel weights:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        long startTime = System.nanoTime();

        quickSort(arr, 0, n - 1);

        long endTime = System.nanoTime();

        System.out.println("\nSorted Parcel Weights:");

        for (int x : arr) {
            System.out.print(x + " ");
        }

        System.out.println("\n\nExecution Time: "
                + (endTime - startTime)
                + " nanoseconds");

        sc.close();
    }
}