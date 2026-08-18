// Armstrong Number Check

// Given a number n, check if it is an Armstrong number.
// An Armstrong number (or narcissistic number) of order k (number of digits) is a number
// that is equal to the sum of its own digits each raised to the power of k.

// Examples:

// Input: n = 153
// Output: true
// Explanation: 153 has 3 digits. 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153.

// Input: n = 1634
// Output: true
// Explanation: 1634 has 4 digits. 1^4 + 6^4 + 3^4 + 4^4 = 1 + 1296 + 81 + 256 = 1634.

// Input: n = 120
// Output: false
// Explanation: 120 has 3 digits. 1^3 + 2^3 + 0^3 = 1 + 8 + 0 = 9 != 120.

// Input: n = 0
// Output: true
// Explanation: 0 has 1 digit. 0^1 = 0.

// Input: n < 0
// Output: false
// Explanation: Negative numbers cannot be Armstrong numbers.

class Armstrong {

    /*
     * Custom implementation of power function to avoid using built-in Math.pow().
     * This follows the rule: "Do not use built-in functions to make the code short".
     *
     * Time Complexity  : O(exp) - loops exp times.
     * Space Complexity : O(1)   - constant auxiliary space.
     */
    private static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }

    /*
     * Custom implementation of digit count mathematically without using String conversions.
     *
     * Time Complexity  : O(log10 n) - counts digits by dividing by 10.
     * Space Complexity : O(1)       - constant space.
     */
    private static int countDigits(int n) {
        if (n == 0) {
            return 1;
        }
        int count = 0;
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    /*
     * [Optimal/Interview Approach] Digit Extraction + Custom Exponentiation - O(log10 n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n < 0, return false (Armstrong numbers are defined for non-negative integers).
     *   2. Store the original number in a variable: `temp = n`.
     *   3. Compute the number of digits `k` of n mathematically.
     *   4. Initialize `sum = 0`.
     *   5. While `temp > 0`:
     *        a. Get the last digit: `digit = temp % 10`.
     *        b. Raise digit to power k: `powVal = power(digit, k)`.
     *        c. Add `powVal` to `sum`.
     *        d. Discard the last digit: `temp /= 10`.
     *   6. Compare `sum` with `n` and return true if equal, else false.
     *
     * Time Complexity  : O(log10 n) - we extract log10(n) digits, and for each digit we compute power of order k (which is log10(n)).
     * Space Complexity : O(1)       - constant space.
     */
    static boolean isArmstrong(int n) {
        // Negative numbers cannot be Armstrong numbers
        if (n < 0) {
            return false;
        }

        int k = countDigits(n); // Number of digits in n
        int original = n;       // Copy of n to preserve the original value
        int sum = 0;            // Accumulator for powers of digits

        int temp = n;
        while (temp > 0) {
            int digit = temp % 10; // Extract last digit
            
            // To prevent potential integer overflow during summation
            int p = power(digit, k);
            if (sum > Integer.MAX_VALUE - p) {
                return false; // Overflow occurred, sum cannot equal n (since n fits in integer)
            }
            
            sum += p;             // Add powered digit to sum
            temp /= 10;           // Remove last digit
        }

        // Return if the sum of powered digits matches the original number
        return sum == original;
    }

    public static void main(String args[]) {
        int[] testNumbers = { 153, 1634, 120, 0, 5, -153 };

        System.out.println("Testing Armstrong Number Verification:");
        for (int num : testNumbers) {
            System.out.println("Number: " + num + " -> Is Armstrong? " + isArmstrong(num));
        }
    }
}