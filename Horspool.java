import java.util.*;

public class Horspool{

    static final int SIZE = 256;

    // Function to create shift table
    static void shiftTable(String pattern, int[] table) {

        int m = pattern.length();

        // Default shift value
        for (int i = 0; i < SIZE; i++) {
            table[i] = m;
        }

        // Fill shift values
        for (int j = 0; j < m - 1; j++) {

            table[(int) pattern.charAt(j)] = m - 1 - j;
        }
    }

    // Horspool Algorithm
    static int horspool(String text, String pattern) {

        int[] table = new int[SIZE];

        shiftTable(pattern, table);

        int n = text.length();
        int m = pattern.length();

        int i = m - 1;

        while (i < n) {

            int k = 0;

            while (k < m &&
                   pattern.charAt(m - 1 - k) ==
                   text.charAt(i - k)) {

                k++;
            }

            // Pattern found
            if (k == m) {
                return i - m + 1;
            }

            // Shift pattern
            i = i + table[(int) text.charAt(i)];
        }

        return -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter pattern: ");
        String pattern = sc.nextLine();

        int position = horspool(text, pattern);

        if (position != -1) {

            System.out.println(
                "Pattern found at position: " + position);

        } else {

            System.out.println("Pattern not found");
        }

        sc.close();
    }
}