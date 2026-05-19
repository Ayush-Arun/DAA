import java.util.*;

public class NQueen {

    static int n;
    static char[][] board;

    static boolean[] row;
    static boolean[] diag1;
    static boolean[] diag2;

    static int solutionCount = 0;

    // Function to solve N Queen column by column
    static void solve(int col) {

        // All queens placed
        if (col == n) {

            solutionCount++;

            System.out.println("Solution " + solutionCount + ":");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }

                System.out.println();
            }

            System.out.println();

            return;
        }

        // Try every row
        for (int r = 0; r < n; r++) {

            int d1 = r - col + n - 1;
            int d2 = r + col;

            // Check if safe
            if (row[r] || diag1[d1] || diag2[d2])
                continue;

            // Place queen
            board[r][col] = 'Q';

            row[r] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Recur for next column
            solve(col + 1);

            // Backtrack
            board[r][col] = '.';

            row[r] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        n = sc.nextInt();

        board = new char[n][n];

        // Fill board with dots
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Hash arrays
        row = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];

        solve(0);

        if (solutionCount == 0) {
            System.out.println("No Solution Exists");
        } else {
            System.out.println("Total Solutions = " + solutionCount);
        }

        sc.close();
    }
}