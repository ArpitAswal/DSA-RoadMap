import java.util.*;

/**
 * Problem Description:
 * Recreate various number patterns based on a given integer 'n' representing the size or height of the pattern.
 * The goal of each pattern method is to use nested loops to control the row and column structure,
 * printing numbers or spaces dynamically to form specific mathematical and geometric shapes.
 *
 * Input:
 * - An integer 'n' representing the dimension of the pattern.
 *
 * Output:
 * - Printed numbers and characters on the console forming the respective patterns.
 */
class NumberPatterns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the pattern value n: ");
        int n = sc.nextInt();
        sc.close();

        System.out.println("Pattern 1: Increasing Number Triangle");
        pattern1(n);

        System.out.println("\nPattern 2: Same-Number Rows Triangle");
        pattern2(n);

        System.out.println("\nPattern 3: Inverted Number Triangle");
        pattern3(n);

        System.out.println("\nPattern 4: Alternating Binary Triangle");
        pattern4(n);

        System.out.println("\nPattern 5: Hourglass-like Number Butterfly Symmetry");
        pattern5(n);

        System.out.println("\nPattern 6: Floyd's Triangle");
        pattern6(n);

        System.out.println("\nPattern 7: Concentric Square Number Pattern");
        pattern7(n);
    }

    /**
     * Pattern 1: Increasing Number Triangle
     * Problem: For N = 5, prints numbers starting from 1 up to row index.
     * Output:
     * 1
     * 12
     * 123
     * 1234
     * 12345
     * Explanation:
     * - Row i (1-based) prints numbers from 1 to i.
     */
    private static void pattern1(int n) {
        // Outer loop controls rows (from 1 to n)
        for (int i = 1; i <= n; i++) {
            // Inner loop prints numbers from 1 to i in the current row
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 2: Same-Number Rows Triangle
     * Problem: For N = 5, prints the row number repeated.
     * Output:
     * 1
     * 22
     * 333
     * 4444
     * 55555
     * Explanation:
     * - Row i (1-based) prints the value of i exactly i times.
     */
    private static void pattern2(int n) {
        // Outer loop controls rows (from 1 to n)
        for (int i = 1; i <= n; i++) {
            // Inner loop prints the row number 'i' exactly 'i' times
            for (int j = 0; j < i; j++) {
                System.out.print(i);
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 3: Inverted Number Triangle
     * Problem: For N = 5, prints numbers in descending rows.
     * Output:
     * 12345
     * 1234
     * 123
     * 12
     * 1
     * Explanation:
     * - Row count decreases from N down to 1. Each row prints numbers from 1 to row count.
     */
    private static void pattern3(int n) {
        // Outer loop starts from n and decreases down to 1 to represent row length
        for (int i = n; i > 0; i--) {
            // Inner loop prints numbers from 1 to i
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 4: Alternating Binary Triangle
     * Problem: For N = 5, prints alternating 1s and 0s.
     * Output:
     * 1
     * 0 1
     * 1 0 1
     * 0 1 0 1
     * 1 0 1 0 1
     * Explanation:
     * - Even rows (0-indexed) start with 1, odd rows start with 0.
     * - Value alternates between 1 and 0 across columns.
     */
    private static void pattern4(int n) {
        int start = 0; // State variable to hold the binary digit to print

        // Outer loop controls rows from 0 to n-1
        for (int i = 0; i < n; i++) {
            // If row index is even, start printing with 1
            if (i % 2 == 0) {
                start = 1;
            }
            // If row index is odd, start printing with 0
            else {
                start = 0;
            }

            // Inner loop prints i+1 binary numbers
            for (int j = 0; j <= i; j++) {
                System.out.print(start);
                // Alternate the value of start between 1 and 0: (1 - 1 = 0, 1 - 0 = 1)
                start = 1 - start;
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 5: Hourglass-like Number Butterfly Symmetry
     * Problem: For N = 5, prints ascending numbers on left, spaces in middle, descending numbers on right.
     * Output:
     * 1        1
     * 12      21
     * 123    321
     * 1234  4321
     * 1234554321
     * Explanation:
     * - Row i (1-based) prints numbers 1 to i on left, 2*(N-i) spaces, and numbers i down to 1 on right.
     */
    private static void pattern5(int n) {
        // Outer loop controls rows from 1 to n
        for (int i = 1; i <= n; i++) {
            // Inner loop 1: Print left ascending number block from 1 to i
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            // Inner loop 2: Print middle spaces: (2 * n - 2 * i) spaces
            for (int space = 1; space <= ((2 * n) - (2 * i)); space++) {
                System.out.print(" ");
            }
            // Inner loop 3: Print right descending number block from i down to 1
            for (int k = i; k > 0; k--) {
                System.out.print(k);
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 6: Floyd's Triangle
     * Problem: For N = 5, prints continuously increasing integers.
     * Output:
     * 1
     * 2 3
     * 4 5 6
     * 7 8 9 10
     * 11 12 13 14 15
     * Explanation:
     * - Starts from 1 and increments continuously across rows.
     */
    private static void pattern6(int n) {
        int count = 1; // Counter variable to keep track of the sequence number

        // Outer loop controls rows from 1 to n
        for (int i = 1; i <= n; i++) {
            // Inner loop prints 'i' numbers in row i
            for (int j = 1; j <= i; j++) {
                // Print the counter and then increment it
                System.out.print(count++ + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 7: Concentric Square Number Pattern
     * Problem: For N = 5, prints a square matrix of size 2*N - 1 with concentric layers.
     * Output:
     * 5 5 5 5 5 5 5 5 5
     * 5 4 4 4 4 4 4 4 5
     * 5 4 3 3 3 3 3 4 5
     * 5 4 3 2 2 2 3 4 5
     * 5 4 3 2 1 2 3 4 5
     * 5 4 3 2 2 2 3 4 5
     * 5 4 3 3 3 3 3 4 5
     * 5 4 4 4 4 4 4 4 5
     * 5 5 5 5 5 5 5 5 5
     * Explanation:
     * - For any cell (i, j), its value is N minus the minimum distance of the cell to the four boundaries (top, bottom, left, right).
     */
    private static void pattern7(int n) {
        int size = 2 * n - 1; // Total size of the square grid (rows and columns)

        // Outer loop controls rows (from 0 to size - 1)
        for (int i = 0; i < size; i++) {
            // Inner loop controls columns (from 0 to size - 1)
            for (int j = 0; j < size; j++) {
                // Calculate distance to all four edges: top, left, bottom, right
                int top = i;
                int left = j;
                int bottom = (size - 1) - i;
                int right = (size - 1) - j;

                // Find the minimum distance to any of the four boundaries
                int minDist = Math.min(Math.min(top, bottom), Math.min(left, right));

                // Print the value: n minus the minimum distance, followed by space
                System.out.print((n - minDist) + " ");
            }
            // Move to the next line
            System.out.println();
        }
    }
}
