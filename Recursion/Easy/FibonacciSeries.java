// Fibonacci Series up to N-th Term

// Given an integer n, print the Fibonacci series up to the n-th term.
// The Fibonacci series is a sequence where each number is the sum of the two preceding ones,
// usually starting with 0 and 1.
// Sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, ...

// Examples:

// Input: n = 5
// Output: [0, 1, 1, 2, 3, 5]
// Explanation: The first 6 terms of the Fibonacci series (from 0th to 5th term).

// Input: n = 0
// Output: [0]
// Explanation: Only the 0th term is printed.

// Input: n < 0
// Output: []
// Explanation: Negative terms are undefined for Fibonacci series.

import java.util.Arrays;
import java.util.Scanner;

class FibonacciSeries {

    /*
     * [Naive Approach] Recursion (N-th Term Only) - O(2^n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base Case: if n <= 1, return n.
     *   2. Recursive Case: return fibonacci(n - 1) + fibonacci(n - 2).
     *
     * Drawback:
     *   - Recalculates same sub-problems repeatedly, leading to exponential time complexity O(2^n).
     *   - Fails to print the entire series directly without calling the function repeatedly in a loop (which would be O(n * 2^n)).
     *
     * Time Complexity  : O(2^n) - branching factor of 2 for recursion tree of depth n.
     * Space Complexity : O(n)   - call stack space due to recursion.
     */
    static int fibonacciRecursive(int n) {
        // Base case: F(0) = 0, F(1) = 1
        if (n <= 1) {
            return n;
        }

        // Compute recursively by summing previous two terms
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /*
     * [Optimal/Interview Approach] Iterative Single-Pass - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n < 0, return an empty array or handle error.
     *   2. Create an array of size n + 1.
     *   3. Set arr[0] = 0. If n >= 1, set arr[1] = 1.
     *   4. Loop from i = 2 to n:
     *        - arr[i] = arr[i - 1] + arr[i - 2].
     *   5. Return the array.
     *
     * Why this is interview-preferred:
     *   - Avoids recursive overhead and redundant calculations.
     *   - Generates the entire series up to N in linear time O(n) and O(1) auxiliary space (excluding result storage).
     *
     * Time Complexity  : O(n) - single pass to calculate all terms up to n.
     * Space Complexity : O(n) - space to store and return the series of size n + 1 (O(1) auxiliary space).
     */
    static int[] getFibonacciSeries(int n) {
        if (n < 0) {
            return new int[0]; // Return empty array for negative inputs
        }

        int[] series = new int[n + 1];
        series[0] = 0; // 0th term is always 0
        
        if (n >= 1) {
            series[1] = 1; // 1st term is always 1
        }

        // Fill series iteratively
        for (int i = 2; i <= n; i++) {
            series[i] = series[i - 1] + series[i - 2];
        }

        return series;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of fibonacci series: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println("\nIterative Series up to " + n + "th term:");
            System.out.println(Arrays.toString(getFibonacciSeries(n)));

            System.out.println("\nRecursively computed " + n + "th term:");
            if (n >= 0) {
                System.out.println("F(" + n + ") = " + fibonacciRecursive(n));
            } else {
                System.out.println("Undefined for negative values.");
            }
        }
        sc.close();
    }
}