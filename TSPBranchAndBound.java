import java.util.*;

public class TSPBranchAndBound {

    static int n;
    static int[][] cost;
    static boolean[] visited;
    static int minCost = Integer.MAX_VALUE;

    // Function to solve TSP
    static void tsp(int currentCity, int count, int currentCost) {

        // If all cities visited
        if (count == n && cost[currentCity][0] > 0) {

            int totalCost = currentCost + cost[currentCity][0];

            if (totalCost < minCost) {
                minCost = totalCost;
            }

            return;
        }

        // Visit next city
        for (int i = 0; i < n; i++) {

            if (!visited[i] && cost[currentCity][i] > 0) {

                // Branch and Bound condition
                if (currentCost + cost[currentCity][i] < minCost) {

                    visited[i] = true;

                    tsp(i,
                        count + 1,
                        currentCost + cost[currentCity][i]);

                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        n = sc.nextInt();

        cost = new int[n][n];
        visited = new boolean[n];

        System.out.println("Enter cost matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        // Start from city 0
        visited[0] = true;

        tsp(0, 1, 0);

        System.out.println("Minimum Delivery Cost = " + minCost);

        sc.close();
    }
}