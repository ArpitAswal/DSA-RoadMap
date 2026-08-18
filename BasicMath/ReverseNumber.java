// Reverse a 32-bit Signed Integer

// Given a 32-bit signed integer n, reverse its digits.
// If reversing n causes the value to exceed the 32-bit signed integer range [-2^31, 2^31 - 1],
// then return 0.

// Examples:

// Input: n = 12345
// Output: 54321
// Explanation: The digits are reversed in order.

// Input: n = -123
// Output: -321
// Explanation: Negative sign is preserved.

// Input: n = 120
// Output: 21
// Explanation: Leading zeros in the reversed number are discarded.

// Input: n = 1534236469
// Output: 0
// Explanation: The reversed value 9646324351 exceeds Integer.MAX_VALUE (2147483647), so we return 0.

class ReverseNumber {

    /*
     * [Alternative/Naive Approach] String Reversal - O(log10 n) Time and O(log10 n) Space
     *
     * Logic / Steps:
     *   1. Convert the integer to string.
     *   2. Reverse the string (ignoring the negative sign).
     *   3. Try to parse the reversed string back into an integer using Long.parseLong().
     *   4. If the parsed value exceeds 32-bit limits, return 0.
     *   5. Return the value with the original sign.
     *
     * Drawback: Uses memory heap allocations for string objects and relies on exception handling/long types for overflow checking.
     *
     * Time Complexity  : O(log10 n) - proportional to the number of digits.
     * Space Complexity : O(log10 n) - to store the string representation.
     */
    static int reverseNaive(int n) {
        boolean isNegative = n < 0;
        
        // Handle Integer.MIN_VALUE edge case as Math.abs(Integer.MIN_VALUE) overflows
        if (n == Integer.MIN_VALUE) {
            return 0; // Reversing it will definitely overflow
        }

        int absVal = isNegative ? -n : n;
        String s = Integer.toString(absVal);
        
        // Reverse string manually
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        
        // Parse reversed string into long to check for overflow
        long reversedLong = Long.parseLong(new String(chars));
        if (isNegative) {
            reversedLong = -reversedLong;
        }

        // Return 0 if the reversed value is out of 32-bit signed integer range
        if (reversedLong < Integer.MIN_VALUE || reversedLong > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) reversedLong;
    }

    /*
     * [Optimal/Interview Approach] Mathematical Digit Extraction with Overflow Guard - O(log10 n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize `reverse = 0`.
     *   2. Loop while `n != 0`:
     *        a. Extract the last digit: `mod = n % 10`.
     *        b. Check for positive overflow before multiplying:
     *           If `reverse > Integer.MAX_VALUE / 10` or (`reverse == Integer.MAX_VALUE / 10` and `mod > 7`), return 0.
     *        c. Check for negative underflow before multiplying:
     *           If `reverse < Integer.MIN_VALUE / 10` or (`reverse == Integer.MIN_VALUE / 10` and `mod < -8`), return 0.
     *        d. Update `reverse = reverse * 10 + mod`.
     *        e. Divide `n` by 10 to discard the last digit.
     *   3. Return `reverse`.
     *
     * Why this is interview-preferred:
     *   - Strict constant space O(1).
     *   - Detects overflow BEFORE it actually occurs, without using larger types like `long` (highly critical in constraints of some environments).
     *   - Naturally handles negative numbers because Java `%` operator preserves sign (e.g. -123 % 10 = -3).
     *
     * Time Complexity  : O(log10 n) - loop runs for each digit.
     * Space Complexity : O(1)       - constant space.
     */
    static int reverseOptimal(int n) {
        int reverse = 0; // Accumulated reversed value

        while (n != 0) {
            int mod = n % 10; // Extract last digit (preserves negative sign)

            // Positive overflow check:
            // Integer.MAX_VALUE is 2147483647
            if (reverse > Integer.MAX_VALUE / 10 || (reverse == Integer.MAX_VALUE / 10 && mod > 7)) {
                return 0; // Return 0 to indicate overflow
            }

            // Negative underflow check:
            // Integer.MIN_VALUE is -2147483648
            if (reverse < Integer.MIN_VALUE / 10 || (reverse == Integer.MIN_VALUE / 10 && mod < -8)) {
                return 0; // Return 0 to indicate underflow
            }

            reverse = reverse * 10 + mod; // Shift digits left and add new digit
            n /= 10;                      // Discard last digit
        }

        return reverse; // Return reversed integer
    }

    public static void main(String args[]) {
        int n1 = 12345;
        int n2 = -123;
        int n3 = 120;
        int n4 = 1534236469; // Overflows when reversed

        System.out.println("Testing ReverseNumber:");
        System.out.println("Original: " + n1 + " -> Reversed (Naive): " + reverseNaive(n1) + " | (Optimal): " + reverseOptimal(n1));
        System.out.println("Original: " + n2 + " -> Reversed (Naive): " + reverseNaive(n2) + " | (Optimal): " + reverseOptimal(n2));
        System.out.println("Original: " + n3 + " -> Reversed (Naive): " + reverseNaive(n3) + " | (Optimal): " + reverseOptimal(n3));
        System.out.println("Original: " + n4 + " -> Reversed (Naive): " + reverseNaive(n4) + " | (Optimal): " + reverseOptimal(n4));
    }
}