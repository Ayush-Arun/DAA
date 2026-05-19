import java.util.*;

public class SubsetSum {

    static boolean found = false;

    // Function to check subset sum
    static void subsetSum(int[] arr, int n, int index,
                          int currentSum, int target,
                          ArrayList<Integer> subset) {

        // If target achieved
        if (currentSum == target) {

            found = true;

            System.out.println("Subset Found: " + subset);

            return;
        }

        // Stop condition
        if (index == n || currentSum > target) {
            return;
        }

        // Include current element
        subset.add(arr[index]);

        subsetSum(arr, n, index + 1,
                  currentSum + arr[index],
                  target, subset);

        // Backtrack
        subset.remove(subset.size() - 1);

        // Exclude current element
        subsetSum(arr, n, index + 1,
                  currentSum,
                  target, subset);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        ArrayList<Integer> subset = new ArrayList<>();

        subsetSum(arr, n, 0, 0, target, subset);

        if (!found) {
            System.out.println("No subset found.");
        }

        sc.close();
    }
}