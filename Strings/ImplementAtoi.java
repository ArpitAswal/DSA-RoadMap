// Implement Atoi (String to Integer)

// Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
// The algorithm is as follows:
// 1. Read in and ignore any leading whitespace.
// 2. Check if the next character is '-' or '+'. Read this character in if it is.
//    This determines if the final result is negative or positive respectively.
// 3. Read in the next characters until a non-digit character or the end of the input is reached.
//    The rest of the string is ignored.
// 4. Convert these digits into an integer (e.g. "123" -> 123). If no digits were read, return 0.
// 5. If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], clamp it to boundaries.

// Examples:

// Input: s = "   -42"
// Output: -42
// Explanation: Whitespace is skipped, sign is negative, digits are '4' and '2'.

// Input: s = "4193 with words"
// Output: 4193
// Explanation: Stops reading when ' ' is encountered (non-digit).

// Input: s = "words and 987"
// Output: 0
// Explanation: The first non-whitespace character is 'w' (not a digit or sign), so conversion fails.

// Input: s = "-91283472332"
// Output: -2147483648 (Integer.MIN_VALUE)
// Explanation: The value -91283472332 is out of range, so it is clamped to Integer.MIN_VALUE.

class ImplementAtoi {

    /*
     * [Optimal/Interview Approach] Step-by-Step Scan with Overflow Clamp - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If string is null or empty, return 0.
     *   2. Step 1 (Whitespace): Ignore leading whitespace characters. Increment index.
     *   3. Step 2 (Sign): Check if sign character exists ('+' or '-').
     *      Set sign = -1 if '-' is found, sign = 1 otherwise. Increment index if a sign is read.
     *   4. Step 3 (Digit Parsing & Overflow Guard): Loop while characters are digits ('0' to '9'):
     *        a. Get numeric value: `digit = s.charAt(index) - '0'`.
     *        b. Check for positive overflow before actual calculation:
     *           If `res > Integer.MAX_VALUE / 10` or (`res == Integer.MAX_VALUE / 10` and `digit > 7`), return clamped value.
     *        c. Update `res = res * 10 + digit`.
     *        d. Increment index.
     *   5. Return `res * sign`.
     *
     * Why this is interview-preferred:
     *   - Strict linear time O(n) and O(1) space complexity.
     *   - Replaces float/double/long overhead checks with safe 32-bit integer arithmetic overflow prevention.
     *   - Properly exits early on non-digit characters without throwing exceptions.
     *
     * Time Complexity  : O(n) - parses through the string at most once.
     * Space Complexity : O(1) - constant auxiliary variables.
     */
    static int atoi(String s) {
        // Base edge case: empty or null string
        if (s == null || s.length() == 0) {
            return 0;
        }

        int index = 0;
        int n = s.length();

        // 1. Skip leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        // Check if string contains only whitespaces
        if (index == n) {
            return 0;
        }

        int sign = 1; // Default sign is positive

        // 2. Check for optional sign character
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            if (s.charAt(index) == '-') {
                sign = -1; // Set negative sign
            }
            index++; // Advance past sign character
        }

        int res = 0; // Accumulated numerical result

        // 3. Read valid digit characters and convert them
        while (index < n) {
            char curr = s.charAt(index);
            // Stop parsing if a non-digit character is encountered
            if (curr < '0' || curr > '9') {
                break;
            }

            int digit = curr - '0'; // Convert char digit to numerical value

            // 4. Handle 32-bit signed integer overflow/underflow clamping
            // Max value boundary is 2147483647, Min value boundary is -2147483648
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) {
                // If positive sign, return MAX_VALUE. If negative, return MIN_VALUE.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit; // Append digit to result
            index++;                // Move to next character
        }

        return res * sign; // Apply sign and return
    }

    public static void main(String[] args) {
        String[] testStrings = {
            "   -42",
            "4193 with words",
            "words and 987",
            "-91283472332",
            "2147483647",
            "2147483648",
            "-2147483648",
            "-2147483649",
            "  +460ctdg"
        };

        System.out.println("Testing ImplementAtoi:");
        for (String test : testStrings) {
            System.out.println("String: \"" + test + "\" -> Integer: " + atoi(test));
        }
    }
}
