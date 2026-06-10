import java.util.*;

public class GaleShapley {

    static int N;

    static boolean prefersNewMan(int[][] womenPref,
                                 int woman,
                                 int newMan,
                                 int currentMan) {

        for (int i = 0; i < N; i++) {

            if (womenPref[woman][i] == newMan)
                return true;

            if (womenPref[woman][i] == currentMan)
                return false;
        }

        return false;
    }

    static void stableMarriage(int[][] menPref,
                               int[][] womenPref) {

        int[] womanPartner = new int[N];
        boolean[] manEngaged = new boolean[N];
        int[] nextProposal = new int[N];

        Arrays.fill(womanPartner, -1);

        int freeMen = N;

        while (freeMen > 0) {

            int man;

            for (man = 0; man < N; man++) {
                if (!manEngaged[man])
                    break;
            }

            int woman = menPref[man][nextProposal[man]];
            nextProposal[man]++;

            if (womanPartner[woman] == -1) {

                womanPartner[woman] = man;
                manEngaged[man] = true;
                freeMen--;

            } else {

                int currentMan = womanPartner[woman];

                if (prefersNewMan(womenPref,
                                  woman,
                                  man,
                                  currentMan)) {

                    womanPartner[woman] = man;

                    manEngaged[man] = true;
                    manEngaged[currentMan] = false;
                }
            }
        }

        System.out.println("\nStable Matching:");

        for (int i = 0; i < N; i++) {

            System.out.println(
                    "Woman " + (char) ('W' + i)
                            + " <---> Man "
                            + (char) ('A' + womanPartner[i]));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of men/women: ");
        N = sc.nextInt();

        int[][] menPref = new int[N][N];
        int[][] womenPref = new int[N][N];

        System.out.println("\nEnter Men's Preferences");
        System.out.println("Use W X Y Z ...");

        for (int i = 0; i < N; i++) {

            System.out.println("Man " + (char) ('A' + i));

            for (int j = 0; j < N; j++) {

                char ch = Character.toUpperCase(sc.next().charAt(0));

                menPref[i][j] = ch - 'W';
            }
        }

        System.out.println("\nEnter Women's Preferences");
        System.out.println("Use A B C D ...");

        for (int i = 0; i < N; i++) {

            System.out.println("Woman " + (char) ('W' + i));

            for (int j = 0; j < N; j++) {

                char ch = Character.toUpperCase(sc.next().charAt(0));

                womenPref[i][j] = ch - 'A';
            }
        }

        stableMarriage(menPref, womenPref);

        sc.close();
    }
}