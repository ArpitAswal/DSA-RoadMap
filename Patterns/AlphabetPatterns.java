import java.util.*;

/**
 * Problem Description:
 * Recreate various alphabet patterns based on a given integer 'n' representing the size or height of the pattern.
 * The goal of each pattern method is to use nested loops to control the row and column structure,
 * printing alphabetic characters ('A', 'B', etc.) or spaces dynamically to form specific shapes.
 *
 * Input:
 * - An integer 'n' representing the dimension of the pattern.
 *
 * Output:
 * - Printed alphabet characters and spaces on the console forming the respective patterns.
 */
class AlphabetPatterns {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the pattern value n: ");
        int n = sc.nextInt();
        sc.close();

        System.out.println("Pattern 1: Increasing Alphabet Triangle");
        pattern1(n);

        System.out.println("\nPattern 2: Inverted Alphabet Triangle");
        pattern2(n);

        System.out.println("\nPattern 3: Same-Letter Rows Alphabet Triangle");
        pattern3(n);

        System.out.println("\nPattern 4: Palindromic Alphabet Pyramid");
        pattern4(n);

        System.out.println("\nPattern 5: Reverse-Aligned Alphabet Triangle");
        pattern5(n);
    }

    /**
     * Pattern 1: Increasing Alphabet Triangle
     * Problem: For N = 5, prints alphabets starting from A in increasing order per column.
     * Output:
     * A
     * A B
     * A B C
     * A B C D
     * A B C D E
     * Explanation:
     * - Row i (0-based) prints characters from 'A' up to 'A' + i.
     */
    private static void pattern1(int n) {
        // Outer loop controls rows from 0 to n-1
        for (int i = 0; i < i + 1 && i < n; i++) { // Guarded condition matching row boundary
            // Inner loop prints character starting from 'A' up to 'A' + i
            for (char ch = 'A'; ch <= 'A' + i; ch++) {
                System.out.print(ch + " "); // Print character followed by space
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 2: Inverted Alphabet Triangle
     * Problem: For N = 5, prints an inverted alphabet triangle.
     * Output:
     * A B C D E
     * A B C D
     * A B C
     * A B
     * A
     * Explanation:
     * - Starts with row size N-1 down to 0, printing from 'A' to 'A' + row index.
     */
    private static void pattern2(int n) {
        // Outer loop starts from n-1 and decreases down to 0 to control row length
        for (int i = n - 1; i >= 0; i--) {
            // Inner loop prints characters from 'A' to 'A' + i
            for (char ch = 'A'; ch <= 'A' + i; ch++) {
                System.out.print(ch + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 3: Same-Letter Rows Alphabet Triangle
     * Problem: For N = 5, prints rows with duplicate letters.
     * Output:
     * A
     * B B
     * C C C
     * D D D D
     * E E E E E
     * Explanation:
     * - Row i (0-based) prints character ('A' + i) exactly i + 1 times.
     */
    private static void pattern3(int n) {
        // Outer loop controls character value chi, starting from 'A' up to 'A' + (n - 1)
        for (char chi = 'A'; chi <= 'A' + (n - 1); chi++) {
            // Inner loop prints the current character chi repeatedly
            // Runs from 'A' up to the current character chi, representing row index
            for (char chj = 'A'; chj <= chi; chj++) {
                System.out.print(chi + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 4: Palindromic Alphabet Pyramid
     * Problem: For N = 5, prints a centered pyramid with palindromic letters.
     * Output:
     *     A
     *    ABA
     *   ABCBA
     *  ABCDCBA
     * ABCDEDCBA
     * Explanation:
     * - Row i (0-based) prints (N - i - 1) spaces.
     * - Character sequence increases from 'A' to ('A' + i), and then decreases back to 'A'.
     */
    private static void pattern4(int n) {
        // Outer loop controls rows from 0 to n-1
        for (int i = 0; i < n; i++) {

            // First inner loop prints (n - i - 1) spaces to center the pyramid
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }

            char ch = 'A'; // Start character for each row
            int breakpoint = (2 * i + 1) / 2; // Midpoint index of the characters in the row

            // Second inner loop prints (2 * i + 1) characters
            for (int j = 1; j <= 2 * i + 1; j++) {
                System.out.print(ch);

                // Increment character up to the breakpoint, then decrement it
                if (j <= breakpoint) {
                    ch++; // Ascending part
                } else {
                    ch--; // Descending part
                }
            }

            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 5: Reverse-Aligned Alphabet Triangle
     * Problem: For N = 5, prints characters starting from 'E' down to 'A' in a staggered order.
     * Output:
     * E
     * D E
     * C D E
     * B C D E
     * A B C D E
     * Explanation:
     * - Row i (0-based) starts printing from character ('A' + N - 1 - i) up to ('A' + N - 1).
     */
    private static void pattern5(int n) {
        // Outer loop controls row offset from 0 to n-1
        for (char i = 0; i < n; i++) {
            // Inner loop starts printing from character ('A' + n - 1 - i) up to the maximum character ('A' + n - 1)
            for (char ch = (char) ('A' + n - 1 - i); ch <= (char) ('A' + n - 1); ch++) {
                System.out.print(ch + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }
}