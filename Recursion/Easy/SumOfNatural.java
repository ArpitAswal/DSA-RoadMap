// Sum of First N Natural Numbers

// Given an integer n, find the sum of the first n natural numbers.
// Natural numbers start from 1 (i.e., 1, 2, 3, ...).

// Examples:

// Input: n = 5
// Output: 15
// Explanation: The sum of the first 5 natural numbers is 1 + 2 + 3 + 4 + 5 = 15.

// Input: n = 1
// Output: 1
// Explanation: Only 1 itself is included.

// Input: n <= 0
// Output: 0
// Explanation: No natural numbers exist for n <= 0.

import java.util.Scanner;

class SumOfNatural {

    /*
     * [Alternative Approach] Recursion - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base case: if n <= 0, return 0 (no positive numbers to add).
     *   2. Recursive case: return n + sumN(n - 1).
     *
     * Drawback:
     *   - If n is large (e.g. 10^5), it can cause a StackOverflowError due to recursion depth.
     *   - Fails to prevent integer overflow if the sum exceeds Integer.MAX_VALUE.
     *
     * Time Complexity  : O(n) - makes n recursive calls.
     * Space Complexity : O(n) - call stack space of depth n.
     */
    static long sumNRecursive(int n) {
        // Base case: natural numbers are positive integers >= 1
        if (n <= 0) {
            return 0;
        }

        // Recursive formula: Sum(n) = n + Sum(n - 1)
        return n + sumNRecursive(n - 1);
    }

    /*
     * [Optimal/Interview Approach] Mathematical Formula - O(1) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n <= 0, return 0.
     *   2. Use the arithmetic progression sum formula: Sum = n * (n + 1) / 2.
     *   3. To prevent integer overflow for large n, perform calculation using the 'long' data type:
     *      `(long) n * (n + 1) / 2`.
     *   4. Return the result.
     *
     * Why this is interview-preferred:
     *   - Runs in constant time O(1) and constant space O(1).
     *   - Prevents stack overflow errors completely.
     *   - Handles integer overflow of product `n * (n + 1)` safely.
     *
     * Time Complexity  : O(1) - single constant-time mathematical formula evaluation.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static long sumNOptimal(int n) {
        // Return 0 for non-positive inputs
        if (n <= 0) {
            return 0;
        }

        // Compute sum using mathematical formula with long to prevent integer overflow
        return (long) n * (n + 1) / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n for sum of first natural numbers: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            
            // Call optimal method (preferred to prevent StackOverflowError)
            System.out.println("Sum (Optimal Formula) : " + sumNOptimal(n));
            
            // Call recursive method (only if n is reasonably small to prevent crash)
            if (n <= 10000) {
                System.out.println("Sum (Recursive method): " + sumNRecursive(n));
            } else {
                System.out.println("Recursive sum skipped to prevent StackOverflowError for large N.");
            }
        }
        sc.close();
    }
}