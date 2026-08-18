// Palindrome Number Check

// Given an integer n, determine if it is a palindrome.
// An integer is a palindrome when it reads the same backward as forward.

// Examples:

// Input: n = 12321
// Output: true
// Explanation: 12321 reads as 12321 from left to right and right to left.

// Input: n = -121
// Output: false
// Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

// Input: n = 10
// Output: false
// Explanation: Reads 01 from right to left, which is not equal to 10.

// Input: n = 0
// Output: true
// Explanation: Single-digit numbers are always palindromes.

class Palindrome {

    /*
     * [Alternative Approach] Reverse the Entire Number - O(log10 n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n is negative, it cannot be a palindrome (return false).
     *   2. Store the original value of n: `original = n`.
     *   3. Reverse all digits of n:
     *        a. If reversing causes overflow, it cannot match n anyway, but we should handle overflow safely.
     *        b. `reverse = reverse * 10 + n % 10`.
     *   4. Return true if `original == reverse`, else false.
     *
     * Time Complexity  : O(log10 n) - loops once for each digit.
     * Space Complexity : O(1)       - constant space.
     */
    static boolean isPalindromeFullReverse(int n) {
        // Negative numbers are not palindromes
        if (n < 0) {
            // e.g. -121 reversed is 121-
            return false;
        }

        int original = n;
        int reverse = 0;

        while (n > 0) {
            int mod = n % 10;

            // Check for integer overflow before multiplying
            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod > 7)) {
                return false; // Overflow implies it won't equal the original value n anyway
            }

            reverse = reverse * 10 + mod;
            n /= 10;
        }

        return original == reverse;
    }

    /*
     * [Optimal/Interview Approach] Reverse Only the Second Half - O(log10 n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Handle base exclusions:
     *        - If n is negative, return false.
     *        - If n is divisible by 10 and n is not 0 (e.g. 10, 100), it cannot be a palindrome because the first digit must be 0 (impossible). Return false.
     *   2. Initialize `revertedHalf = 0`.
     *   3. Loop while `n > revertedHalf`:
     *        a. Append the last digit of n to revertedHalf: `revertedHalf = revertedHalf * 10 + n % 10`.
     *        b. Discard the last digit of n: `n /= 10`.
     *   4. Compare:
     *        - For even digit length: `n == revertedHalf`.
     *        - For odd digit length: `n == revertedHalf / 10` (ignores the middle digit, e.g. for 12321, at loop end n = 12, revertedHalf = 123. 123 / 10 = 12).
     *   5. Return true if matches, else false.
     *
     * Why this is interview-preferred:
     *   - Reverses only half of the digits, which completely avoids any integer overflow potential.
     *   - Loops only log10(n) / 2 times (half the iterations).
     *
     * Time Complexity  : O(log10 n) - runs for half the number of digits.
     * Space Complexity : O(1)       - constant auxiliary space.
     */
    static boolean isPalindromeHalfReverse(int n) {
        // As discussed above, negative numbers cannot be palindromes.
        // Also, if the last digit of the number is 0, for the number to be a palindrome,
        // the first digit of the number also needs to be 0. Only 0 itself satisfies this.
        if (n < 0 || (n % 10 == 0 && n != 0)) {
            return false;
        }

        int revertedHalf = 0;
        
        // Loop until we reach the middle of the number.
        // When n <= revertedHalf, it means we have processed at least half of the digits.
        while (n > revertedHalf) {
            revertedHalf = revertedHalf * 10 + n % 10; // Add last digit to the reversed half
            n /= 10;                                   // Remove last digit from n
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedHalf/10.
        // For example, for 12321, at the end of the loop we have n = 12, revertedHalf = 123.
        // Since the middle digit doesn't matter in palindrome (it will always equal itself),
        // we can simply get rid of it by dividing by 10.
        return n == revertedHalf || n == revertedHalf / 10;
    }

    public static void main(String[] args) {
        int[] testNumbers = { 12321, -121, 10, 0, 2147483647, 1221 };

        System.out.println("Testing Palindrome:");
        for (int num : testNumbers) {
            System.out.println("Number: " + num + 
                               " -> Full Reverse: " + isPalindromeFullReverse(num) + 
                               " | Half Reverse: " + isPalindromeHalfReverse(num));
        }
    }
}