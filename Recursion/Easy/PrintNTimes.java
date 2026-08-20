// Print Name N Times using Recursion

// Given an integer n, print a name (or a string value) exactly n times using recursion.
// Do not use iterative loops such as for, while, or do-while in the recursive solution.

// Examples:

// Input: n = 3
// Output:
// UserName
// UserName
// UserName
// Explanation: The string "UserName" is printed exactly 3 times.

// Input: n <= 0
// Output: (no output)
// Explanation: If n is 0 or negative, nothing should be printed.

import java.util.Scanner;

class PrintNTimes {

    /*
     * [Alternative Approach] Iterative - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n <= 0, return immediately.
     *   2. Use a standard for-loop to print the string n times.
     *
     * Time Complexity  : O(n) - loops n times.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static void printNTimesIterative(int n, String name) {
        // If n is zero or negative, do not print
        if (n <= 0) {
            return;
        }

        // Loop n times
        for (int i = 0; i < n; i++) {
            System.out.println(name);
        }
    }

    /*
     * [Optimal/Interview Approach] Tail Recursion - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base Case: if n <= 0, return.
     *   2. Print the target name/string.
     *   3. Recursive Case: Call ntimes(n - 1) to print the remaining n - 1 times.
     *
     * Why this is interview-preferred:
     *   - Implements recursion clearly without loop statements.
     *   - Safe parameter decrement: passes `n - 1` instead of mutating state with `--n` or `n--`.
     *
     * Time Complexity  : O(n) - exactly n recursive call frames.
     * Space Complexity : O(n) - call stack space of depth n.
     */
    static void ntimes(int n, String name) {
        // Base case: stop when count reaches 0 or below
        if (n <= 0) {
            return;
        }

        // Print the string for the current recursive step
        System.out.println(name);

        // Recursive call with decremented count
        ntimes(n - 1, name);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many times name has to print: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            String name = "UserName";
            
            System.out.println("\n--- Iterative Version ---");
            printNTimesIterative(n, name);

            System.out.println("\n--- Recursive Version ---");
            ntimes(n, name);
        }
        sc.close();
    }
}