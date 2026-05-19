import java.util.*;

public class Knapsack01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] value = new int[n];

        System.out.println("Enter weights:");

        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
        }

        System.out.println("Enter values:");

        for (int i = 0; i < n; i++) {
            value[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        // DP Table
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table
        for (int i = 1; i <= n; i++) {

            for (int w = 1; w <= capacity; w++) {

                // If item can be included
                if (weight[i - 1] <= w) {

                    dp[i][w] = Math.max(
                            value[i - 1] +
                            dp[i - 1][w - weight[i - 1]],

                            dp[i - 1][w]
                    );

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nMaximum Value = "
                           + dp[n][capacity]);

        // Find selected items
        System.out.println("Selected Items:");

        int w = capacity;

        for (int i = n; i > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println(
                        "Item " + i +
                        " Weight = " + weight[i - 1] +
                        " Value = " + value[i - 1]
                );

                w = w - weight[i - 1];
            }
        }

        sc.close();
    }
}