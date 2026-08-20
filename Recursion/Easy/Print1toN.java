// Print 1 to N and N to 1 using Recursion

// Given an integer n, print all numbers from 1 to n and from n to 1 using recursive functions.
// Loops like for, while, or do-while should not be used in the recursive solutions.

// Examples:

// Input: n = 5
// Output:
// Numbers from 1 to 5: 1 2 3 4 5
// Numbers from 5 to 1: 5 4 3 2 1

// Input: n = 1
// Output:
// Numbers from 1 to 1: 1
// Numbers from 1 to 1: 1

// Input: n <= 0
// Output: (no output or ignored)
// Explanation: The functions should return immediately for non-positive inputs.

import java.util.Scanner;

class Print1toN {

    /*
     * [Alternative Approach] Iterative Loops - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Print 1 to N using a standard forward for-loop.
     *   2. Print N to 1 using a standard backward for-loop.
     *
     * Time Complexity  : O(n) - loops n times.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static void printIterative(int n) {
        if (n <= 0) return;

        System.out.print("1 to N (Iterative): ");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.print("N to 1 (Iterative): ");
        for (int i = n; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /*
     * [Optimal/Interview Approach] Recursive Backtracking (1 to N) - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base Case: if n == 0, return.
     *   2. Recursive Case: Call print1ToN(n - 1) first. This pushes all frames to call stack.
     *   3. Print step: After returning from the recursive call (backtracking), print the current number `n`.
     *      This prints numbers in ascending order from 1 to n.
     *
     * Why this is interview-preferred:
     *   - Only uses a single parameter `n` without needing to pass a state/accumulator variable.
     *   - Leverages call stack backtracking elegantly to reverse print order.
     *
     * Time Complexity  : O(n) - exactly n recursive calls.
     * Space Complexity : O(n) - implicit call stack space of depth n.
     */
    static void from1toN(int n) {
        // Base case: stop recursion when n becomes 0
        if (n <= 0) {
            return;
        }

        // Recursive step: solve for n - 1 first
        from1toN(n - 1);

        // Backtracking print: prints after preceding numbers are printed
        System.out.print(n + " ");
    }

    /*
     * [Optimal/Interview Approach] Head Recursion (N to 1) - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base Case: if n == 0, return.
     *   2. Print step: Print the current number `n` first.
     *   3. Recursive Case: Call printNTo1(n - 1) to print the remaining numbers.
     *
     * Why this is interview-preferred:
     *   - Avoids side-effects by passing `n - 1` instead of using mutation operators like `--n` or `n--`.
     *   - Straightforward head recursion.
     *
     * Time Complexity  : O(n) - exactly n recursive calls.
     * Space Complexity : O(n) - implicit call stack space of depth n.
     */
    static void fromNto1(int n) {
        // Base case: stop recursion when n reaches 0
        if (n <= 0) {
            return;
        }

        // Print step: print the current number first
        System.out.print(n + " ");

        // Recursive step: print the remaining numbers (n - 1 down to 1)
        fromNto1(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            
            System.out.println("\n--- Iterative Version ---");
            printIterative(n);

            System.out.println("\n--- Recursive Version ---");
            System.out.print("1 to N: ");
            from1toN(n);
            System.out.println();

            System.out.print("N to 1: ");
            fromNto1(n);
            System.out.println();
        }
        sc.close();
    }
}