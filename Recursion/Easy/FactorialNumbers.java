// Factorial of a Number using Recursion

// Given an integer n, print its factorial.
// The factorial of a non-negative integer n (written as n!) is the product of all positive integers less than or equal to n.
// By mathematical definition:
// 0! = 1
// 1! = 1
// n! = n * (n - 1) * (n - 2) * ... * 1

// Examples:

// Input: n = 5
// Output: 120
// Explanation: 5! = 5 * 4 * 3 * 2 * 1 = 120.

// Input: n = 0
// Output: 1
// Explanation: By definition, the factorial of 0 is 1.

// Input: n < 0
// Output: -1
// Explanation: Factorial is undefined for negative numbers; we return -1 as a sentinel value.

import java.util.Scanner;

class FactorialNumbers {

    /*
     * [Alternative Approach] Iterative Multiplication - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n < 0, return -1 (factorial undefined).
     *   2. Initialize result = 1.
     *   3. Loop from i = 1 to n:
     *        - Multiply result by i: `result *= i`.
     *   4. Return result.
     *
     * Why this is useful:
     *   - Avoids recursive call stack frames, saving memory.
     *   - Safe against stack overflow.
     *
     * Time Complexity  : O(n) - loops n times.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static long factIterative(int n) {
        if (n < 0) {
            return -1; // Undefined for negative integers
        }

        long result = 1;
        // Multiply all integers up to n
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /*
     * [Optimal/Interview Approach] Recursion - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If n < 0, return -1 (factorial undefined).
     *   2. Base Case: if n == 0 or n == 1, return 1 (since 0! = 1 and 1! = 1).
     *   3. Recursive Case: return n * fact(n - 1).
     *
     * Why this is interview-preferred:
     *   - Expresses the mathematical induction definition of factorial clearly.
     *   - Corrects the base case where F(0) = 1 (instead of 0).
     *
     * Time Complexity  : O(n) - exactly n recursive frames.
     * Space Complexity : O(n) - call stack space of depth n.
     */
    static long fact(int n) {
        // Edge Case: Factorial is undefined for negative numbers
        if (n < 0) {
            return -1;
        }

        // Base Case: 0! = 1 and 1! = 1
        if (n == 0 || n == 1) {
            return 1;
        }

        // Recursive case: multiply current number by factorial of (n - 1)
        return n * fact(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of n to calculate factorial: ");
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            
            long recursiveResult = fact(n);
            long iterativeResult = factIterative(n);

            if (n >= 0) {
                System.out.println("Factorial (Recursive): " + n + "! = " + recursiveResult);
                System.out.println("Factorial (Iterative): " + n + "! = " + iterativeResult);
            } else {
                System.out.println("Factorial is undefined for negative numbers.");
            }
        }
        sc.close();
    }
}