// Count Digits of an Integer

// Given an integer n, find the number of digits in it.
// The count should ignore the negative sign (if any) and handle zero correctly.

// Examples:

// Input: n = 12345
// Output: 5
// Explanation: The number 12345 contains 5 digits: '1', '2', '3', '4', and '5'.

// Input: n = 0
// Output: 1
// Explanation: The number 0 contains exactly 1 digit.

// Input: n = -987
// Output: 3
// Explanation: Ignoring the negative sign, -987 has 3 digits: '9', '8', and '7'.

// Input: n = -2147483648 (Integer.MIN_VALUE)
// Output: 10
// Explanation: Ignoring the sign, the digits are 2, 1, 4, 7, 4, 8, 3, 6, 4, 8 which is 10 digits.

class CountDigits {

    /*
     * [Alternative/Naive Approach] String Conversion - O(log10 N) Time and O(log10 N) Space
     *
     * Logic / Steps:
     *   1. Convert the integer to its string representation.
     *   2. If the number is negative, ignore the negative sign (subtract 1 from length).
     *   3. If the number is 0, string representation "0" has length 1.
     *   4. Return the length of the string.
     *
     * Note: String conversion allocates new objects and is not strictly a mathematical solution.
     *
     * Time Complexity  : O(log10 N) - as converting N to string takes time proportional to number of digits.
     * Space Complexity : O(log10 N) - space required for the string representation of N.
     */
    static int countDigitsNaive(int n) {
        // Special case: Integer.MIN_VALUE cannot be negated without overflow
        if (n == Integer.MIN_VALUE) {
            return 10;
        }

        // Convert the absolute value of n to string to exclude sign
        int absVal = n < 0 ? -n : n;
        String s = Integer.toString(absVal);
        return s.length();
    }

    /*
     * [Optimal/Interview Approach] Iterative Division - O(log10 N) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Handle edge case: if N is 0, it has exactly 1 digit.
     *   2. Initialize count to 0.
     *   3. Loop while the number N is not equal to 0:
     *        a. Increment count.
     *        b. Divide N by 10 (N /= 10).
     *   4. Return the count.
     *
     * Why this is interview-preferred:
     *   - Operates entirely mathematically without string overhead.
     *   - Uses only primitive integer arithmetic.
     *   - Handles negative numbers naturally without negation overflow (since division of negative integers by 10 moves them closer to 0).
     *
     * Time Complexity  : O(log10 N) - loop runs once for every digit in the number.
     * Space Complexity : O(1)       - constant auxiliary space.
     */
    static int countDigitsIterative(int n) {
        // Edge Case: If the number is 0, it has exactly 1 digit
        if (n == 0) {
            return 1;
        }

        int count = 0; // Initialize digit counter

        // Loop until all digits are extracted/removed
        while (n != 0) {
            count++;   // Increment digit count
            n /= 10;   // Divide by 10 to discard the last digit
        }

        return count; // Return total counted digits
    }

    public static void main(String args[]) {
        int n1 = 12345;
        int n2 = 0;
        int n3 = -987;
        int n4 = Integer.MIN_VALUE; // -2147483648

        System.out.println("Testing CountDigits:");
        System.out.println("Number: " + n1 + " -> Digits (Naive): " + countDigitsNaive(n1) + " | (Iterative): " + countDigitsIterative(n1));
        System.out.println("Number: " + n2 + " -> Digits (Naive): " + countDigitsNaive(n2) + " | (Iterative): " + countDigitsIterative(n2));
        System.out.println("Number: " + n3 + " -> Digits (Naive): " + countDigitsNaive(n3) + " | (Iterative): " + countDigitsIterative(n3));
        System.out.println("Number: " + n4 + " -> Digits (Naive): " + countDigitsNaive(n4) + " | (Iterative): " + countDigitsIterative(n4));
    }
}