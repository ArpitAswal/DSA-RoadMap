import java.util.*;

/**
 * Problem Description:
 * Recreate various star patterns based on a given integer 'n' representing the size or height of the pattern.
 * The goal of each pattern method is to use nested loops to control the row and column structure,
 * printing '*' or spaces dynamically to form specific shapes like squares, triangles, pyramids, diamonds, and hollow rectangles.
 *
 * Input:
 * - An integer 'n' representing the dimension of the pattern.
 *
 * Output:
 * - Printed characters on the console forming the respective star patterns.
 */
class StarPatterns {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the pattern value n: ");
        int n = sc.nextInt();
        System.out.println();
        System.out.print("Enter the row and column values: ");
        int r = sc.nextInt();
        int c = sc.nextInt();
        sc.close();

        System.out.println("Pattern 1: Solid Square");
        pattern1(n);

        System.out.println("\nPattern 2: Right-Angled Triangle");
        pattern2(n);

        System.out.println("\nPattern 3: Inverted Right-Angled Triangle");
        pattern3(n);

        System.out.println("\nPattern 4: Full Pyramid Pattern");
        pattern4(n);

        System.out.println("\nPattern 5: Inverted Full Pyramid Pattern");
        pattern5(n);

        System.out.println("\nPattern 6: Diamond Pattern");
        pattern6(n);

        System.out.println("\nPattern 7: Half Diamond / Side Pyramid");
        pattern7(n);

        System.out.println("\nPattern 8: Butterfly Outline (Hollow Center - Upper & Lower Wings)");
        pattern8(n);

        System.out.println("\nPattern 9: Symmetric Hourglass Butterfly Shape");
        pattern9(n);

        System.out.println("\nPattern 10: Hollow Square");
        pattern10(n);

        System.out.println("\n Inverted Left Half Pyramid");
        pattern11(n);

        System.out.println("\n Left Half Pyramid Pattern");
        pattern12(n);

        System.out.println("\n Alphabet 'A' Pattern");
        pattern13(n);

        System.out.println("\n Hollow Rectangle Pattern");
        pattern14(r, c);

        System.out.println("\n Stair Case Patterns");
        pattern15(n);
    }

    /**
     * Pattern 1: Solid Square
     * Problem: For N = 5, prints a 5x5 grid of stars.
     * Output:
     * *****
     * *****
     * *****
     * *****
     * *****
     * Explanation: Every row contains exactly N stars.
     */
    private static void pattern1(int n) {
        // Outer loop runs n times to print n rows
        for (int i = 0; i < n; i++) {
            // Inner loop runs n times to print n stars in the current row
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 2: Right-Angled Triangle
     * Problem: For N = 5, prints a right-angled triangle.
     * Output:
     * *
     * **
     * ***
     * ****
     * *****
     * Explanation: The number of stars in a row matches its 1-based index (row i has i stars).
     */
    private static void pattern2(int n) {
        // Outer loop controls the row number (0 to n-1)
        for (int i = 0; i < n; i++) {
            // Inner loop prints stars up to the current row index (i + 1 stars)
            for (int j = 0; j <= i; j++) {
                System.out.print('*');
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 3: Inverted Right-Angled Triangle
     * Problem: For N = 5, prints a descending right-angled triangle.
     * Output:
     * *****
     * ****
     * ***
     * **
     * *
     * Explanation: Row count decreases from N down to 1.
     */
    private static void pattern3(int n) {
        // Outer loop starts from n and decreases down to 1 to represent row length
        for (int i = n; i > 0; i--) {
            // Inner loop prints exactly 'i' stars for the current row
            for (int j = 0; j < i; j++) {
                System.out.print('*');
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 4: Full Pyramid Pattern
     * Problem: For N = 5, prints a centered pyramid.
     * Output:
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     * Explanation:
     * - Row i (1-based) has (N - i) leading spaces and (2*i - 1) stars.
     */
    private static void pattern4(int n) {
        // Outer loop controls the row index from 1 to n
        for (int i = 1; i <= n; i++) {
            // First inner loop prints (n - i) spaces to center-align the pyramid
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Second inner loop prints (2 * i - 1) stars for the current level
            for (int k = 1; k <= ((2 * i) - 1); k++) {
                System.out.print("*");
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 5: Inverted Full Pyramid Pattern
     * Problem: For N = 5, prints an inverted pyramid.
     * Output:
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     * Explanation:
     * - Row i (starts from N down to 1) has (N - i) leading spaces and (2*i - 1) stars.
     */
    private static void pattern5(int n) {
        // Outer loop starts from n and decreases down to 1
        for (int i = n; i > 0; i--) {
            // First inner loop prints (n - i) spaces to align the inverted pyramid
            for (int j = 1; j <= (n - i); j++) {
                System.out.print(" ");
            }
            // Second inner loop prints (2 * i - 1) stars
            for (int k = 1; k <= ((2 * i) - 1); k++) {
                System.out.print('*');
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 6: Diamond Pattern
     * Problem: For N = 5, prints a diamond.
     * Output:
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     * *********
     *  *******
     *   *****
     *    ***
     *     *
     * Explanation: Combines a normal pyramid of height N and an inverted pyramid of height N.
     */
    private static void pattern6(int n) {
        // Variable to keep track of spaces in the upper half
        int space = n - 1;

        // Upper Half: Print a pyramid of height n
        for (int i = 1; i <= n; i++) {
            // Print leading spaces
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            // Print stars: (2 * i - 1)
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
            space--; // Space count decreases for the next row
        }

        // Variable to keep track of spaces in the lower half
        space = 0;

        // Lower Half: Print an inverted pyramid of height n
        for (int i = n; i > 0; i--) {
            // Print leading spaces
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            // Print stars: (2 * i - 1)
            for (int k = (i * 2 - 1); k > 0; k--) {
                System.out.print('*');
            }
            System.out.println();
            space++; // Space count increases for the next row
        }
    }

    /**
     * Pattern 7: Half Diamond / Side Pyramid
     * Problem: For N = 5, prints a sideways pyramid pointing to the right.
     * Output:
     * *
     * **
     * ***
     * ****
     * *****
     * ****
     * ***
     * **
     * *
     * Explanation:
     * - The pattern has 2*N - 1 rows.
     * - For rows <= N: prints 'i' stars.
     * - For rows > N: prints (2*N - i) stars.
     */
    private static void pattern7(int n) {
        // Loop runs for 2*n - 1 rows
        for (int i = 1; i <= (n * 2) - 1; i++) {
            // Inner loop 1: Runs only if i <= n (upper half) to print i stars
            for (int j = 1; i <= n && j <= i; j++) {
                System.out.print('*');
            }
            // Inner loop 2: Runs only if i > n (lower half) to print stars
            // Starts k at i and runs while k < 2*n, printing (2*n - i) stars
            for (int k = i; k > n && (k < (n * 2)); k++) {
                System.out.print('*');
            }
            // Move to the next line
            System.out.println();
        }
    }

    /**
     * Pattern 8: Butterfly Outline (Hollow Center - Upper & Lower Wings)
     * Problem: For N = 5, prints a hollow hourglass-like shape.
     * Output:
     * **********
     * **** ****
     * ***   ***
     * **     **
     * *       *
     * *       *
     * **     **
     * ***   ***
     * **** ****
     * **********
     * Explanation:
     * - Upper half: Stars decrease, spaces increase.
     * - Lower half: Stars increase, spaces decrease.
     */
    private static void pattern8(int n) {
        int space = 0; // Initialize space count between left and right stars

        // Upper Half: Stars decrease on both sides, hollow spaces increase in middle
        for (int i = 0; i < n; i++) {
            // Print left star block: (n - i) stars
            for (int j = 1; j <= (n - i); j++) {
                System.out.print('*');
            }
            // Print middle spaces: 'space' spaces
            for (int k = 0; k < space; k++) {
                System.out.print(" ");
            }
            // Print right star block: (n - i) stars
            for (int l = 1; l <= (n - i); l++) {
                System.out.print('*');
            }
            space += 2; // Spaces increase by 2 for each subsequent row
            System.out.println();
        }

        // Lower Half: Stars increase on both sides, hollow spaces decrease in middle
        for (int i = 0; i < n; i++) {
            // Print left star block: (i + 1) stars
            for (int j = 0; j <= i; j++) {
                System.out.print('*');
            }
            // Print middle spaces: starts at 'space - 2' and decreases by 2 per row
            for (int k = space - 2; k > 0; k--) {
                System.out.print(" ");
            }
            // Print right star block: (i + 1) stars
            for (int l = 0; l <= i; l++) {
                System.out.print('*');
            }
            space -= 2; // Spaces decrease by 2 for each subsequent row
            System.out.println();
        }
    }

    /**
     * Pattern 9: Symmetric Hourglass Butterfly Shape
     * Problem: For N = 5, prints a butterfly pattern.
     * Output:
     * *        *
     * **      **
     * ***    ***
     * ****  ****
     * **********
     * ****  ****
     * ***    ***
     * **      **
     * *        *
     * Explanation:
     * - Upper half: Stars increase, spaces decrease.
     * - Lower half: Stars decrease, spaces increase.
     */
    private static void pattern9(int n) {
        // Upper Half: Stars increase from 1 to n, spaces decrease from 2*(n-1) to 0
        for (int i = 1; i <= n; i++) {
            // Print left stars
            for (int j = 0; j < i; j++) {
                System.out.print('*');
            }
            // Print middle spaces: 2 * (n - i) spaces
            for (int k = 1; k <= (n - i) * 2; k++) {
                System.out.print(" ");
            }
            // Print right stars
            for (int j = 0; j < i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }

        // Lower Half: Stars decrease from n-1 down to 1, spaces increase from 2 to 2*(n-1)
        for (int i = n - 1; i > 0; i--) {
            // Print left stars
            for (int j = 1; j <= i; j++) {
                System.out.print('*');
            }
            // Print middle spaces: 2 * (n - i) spaces
            for (int k = 1; k <= (n - i) * 2; k++) {
                System.out.print(" ");
            }
            // Print right stars
            for (int j = 1; j <= i; j++) {
                System.out.print('*');
            }
            System.out.println();
        }
    }

    /**
     * Pattern 10: Hollow Square
     * Problem: For N = 5, prints a hollow square of size N.
     * Output:
     * *****
     * *   *
     * *   *
     * *   *
     * *****
     * Explanation: Prints stars at boundaries (first/last rows and columns) and spaces in-between.
     */
    private static void pattern10(int n) {
        // Outer loop controls rows (0 to n-1)
        for (int i = 0; i < n; i++) {
            // Inner loop controls columns (0 to n-1)
            for (int j = 0; j < n; j++) {
                // If it is the first/last row or first/last column, print a star
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    System.out.print("*");
                }
                // Otherwise, print space for the hollow interior
                else {
                    System.out.print(" ");
                }
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 11: Inverted Left Half Pyramid
     * Problem: For N = 5, prints an inverted left half pyramid.
     * Output:
     *  *****
     *   ****
     *    ***
     *     **
     *      *
     * Explanation:
     * - Row i (starts from N down to 1) has (N - i) leading spaces and i stars.
     */
    private static void pattern11(int n) {
        // Outer loop controls the number of rows, starting from n down to 1
        for (int i = n; i > 0; i--) {
            // Inner loop prints leading space to align the stars to the right
            for (int k = 0; k < n - i; k++) {
                System.out.print(' ');
            }
            // Inner loop prints stars for the current row 
            for (int j = 0; j < i; j++) {
                System.out.print('*');
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 12: Left Half Pyramid Pattern
     * Problem: For N = 5, prints a left half pyramid.
     * Output:
     *     *
     *    **
     *   ***
     *  ****
     * *****
     * Explanation:
     * - Row i (from 1 to N) has (N - i) leading spaces and i stars.
     */
    private static void pattern12(int n) {
        // Outer loop controls the number of rows, starting from 1 up to n
        for (int i = 1; i <= n; i++) {
            // Inner loop prints leading space to align the stars to the right
            for (int k = 0; k < n - i; k++) {
                System.out.print(' ');
            }
            // Inner loop prints stars for the current row
            for (int j = 0; j < i; j++) {
                System.out.print('*');
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }
   
    /**
     * Pattern 13: Alphabet 'A' Pattern
     * Problem: For N = 5, prints the letter 'A' using stars.
     * Output:
     *  * 
     * * *
     * ***
     * * *
     * * *
     * Explanation:
     * - Top boundary (row 0) has hollow corners.
     * - Left boundary (col 0) and right boundary (col N/2) are filled with stars.
     * - Middle row (row N/2) is filled to form the horizontal bar.
     */
    private static void pattern13(int n) {
        // Outer loop controls the height of letter 'A'
        for (int i = 0; i < n; i++) {
            // Inner loop controls the width of letter 'A'
            for (int j = 0; j <= n / 2; j++) {
                // Print star if on the boundary (top, left, right, or middle row)
                if (i == 0 || j == 0 || j == n / 2 || i == n / 2) {
                    // Hollow out the top-left and top-right corners for a rounded 'A'
                    if (i == 0 && (j == 0 || j == n / 2)) {
                        System.out.print(" ");
                    } else {
                        System.out.print("*");
                    }
                }
                // Otherwise print spaces for the hollow interior
                else {
                    System.out.print(" ");
                }
            }
            // Move to the next line after completing the current row
            System.out.println();
        }
    }

    /**
     * Pattern 14: Hollow Rectangle Pattern
     * Problem: For N = 4 rows, M = 16 columns prints a hollow rectangle.
     * Output:
     * ****************
     * *              *
     * *              *
     * ****************
     * Explanation:
     * - Prints stars on boundary positions (first/last rows and columns) and spaces in between.
     */
    private static void pattern14(int r, int c) {
        System.out.println("r-> " + r + " c-> " + c);
        // Outer loop controls rows (from 1 to r)
        for (int i = 1; i <= r; i++) {
            // Inner loop controls columns (from 1 to c)
            for (int j = 1; j <= c; j++) {
                // Print stars at boundaries, spaces elsewhere
                if (i == 1 || i == r || j == 1 || j == c) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            // Move to next line
            System.out.println();
        }
    }

    /**
     * Pattern 15: Stair Case Patterns
     * Problem: For N = 6, prints two different staircase patterns.
     * Output:
     *  **
     *  **
     *  ****
     *  ****
     *  ******
     *  ******
     * 
     *       ** 
     *       ** 
     *     **** 
     *     **** 
     *   ****** 
     *   ****** 
     * Explanation:
     * - The first pattern prints stars in pairs matching the next even number.
     * - The second pattern aligns the staircases to the right with leading spaces.
     */
    private static void pattern15(int n) {
        // First Staircase Pattern (Left-aligned)
        for (int i = 1; i <= n; i++) {
            int k;
            // Determine pairing level (round up to next even number)
            if (i % 2 == 0) {
                k = i;
            } else {
                k = i + 1;
            }
            // Print k stars
            for (int j = 1; j <= k; j++) {
                System.out.print('*');
            }     
            System.out.println();
        }

        System.out.println();

        // Second Staircase Pattern (Right-aligned)
        for (int i = 1; i <= n; i++) {
            int k;
            // Determine pairing level (round up to next even number)
            if (i % 2 != 0) {
                k = i + 1;
            } else {
                k = i;
            }
    
            // Print leading spaces to right-align the stairs
            for (int g = n; g > k; g--) {
                System.out.print(" ");
            }
    
            // Print k stars (each followed by a space for better formatting)
            for (int j = 0; j < k; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
     
}