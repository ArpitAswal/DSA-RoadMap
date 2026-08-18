// Add Two Binary Strings

// Given two binary strings s1 and s2, return their sum as a binary string.
// The addition should be done directly on the strings without converting them to decimal integers,
// which prevents overflow for very long binary inputs.

// Examples:

// Input: s1 = "1101", s2 = "111"
// Output: "10100"
// Explanation: 
//     1101  (decimal 13)
//   +  111  (decimal 7)
//   ------
//    10100  (decimal 20)

// Input: s1 = "0", s2 = "0"
// Output: "0"
// Explanation: 0 + 0 = 0.

// Input: s1 = "1", s2 = "11"
// Output: "100"
// Explanation: 1 + 3 = 4, which is "100" in binary.

class AddTwoBinary {

    /*
     * [Alternative/Naive Approach] Parse to Integer/Long - O(n + m) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Convert binary string s1 to a decimal integer using Integer.parseInt(s1, 2).
     *   2. Convert binary string s2 to a decimal integer using Integer.parseInt(s2, 2).
     *   3. Compute sum = val1 + val2.
     *   4. Convert sum back to a binary string using Integer.toBinaryString(sum).
     *   5. Return the binary string.
     *
     * Drawback:
     *   - Fails for large binary inputs. If the binary string length exceeds 31 bits,
     *     Integer.parseInt() will throw NumberFormatException. Even Long.parseLong() fails beyond 63 bits.
     *
     * Time Complexity  : O(n + m) - parsing and converting values.
     * Space Complexity : O(1)     - constant auxiliary space.
     */
    static String addBinaryNaive(String s1, String s2) {
        try {
            int num1 = Integer.parseInt(s1, 2);
            int num2 = Integer.parseInt(s2, 2);
            int sum = num1 + num2;
            return Integer.toBinaryString(sum);
        } catch (NumberFormatException e) {
            // Fallback for overflow in naive method: return an error placeholder
            return "Overflow Error in Naive Method";
        }
    }

    /*
     * [Optimal/Interview Approach] Bit-by-bit Addition - O(max(n, m)) Time and O(max(n, m)) Space
     *
     * Logic / Steps:
     *   1. Initialize `i = s1.length() - 1` and `j = s2.length() - 1` to point to the ends of both strings.
     *   2. Initialize `carry = 0`.
     *   3. Create a `StringBuilder` to accumulate the result characters.
     *   4. Loop while `i >= 0` OR `j >= 0` OR `carry > 0`:
     *        a. Start with `sum = carry`.
     *        b. If `i >= 0`, add digit of s1: `sum += s1.charAt(i) - '0'`, then decrement `i`.
     *        c. If `j >= 0`, add digit of s2: `sum += s2.charAt(j) - '0'`, then decrement `j`.
     *        d. Append the remainder to the builder: `builder.append(sum % 2)`.
     *        e. Compute the new carry: `carry = sum / 2`.
     *   5. The collected digits are in reverse order. Reverse the StringBuilder and return its string representation.
     *
     * Why this is interview-preferred:
     *   - Performs binary addition bit-by-bit, identical to manual paper addition.
     *   - Scale-invariant: can easily handle binary strings containing millions of digits.
     *   - Avoids built-in conversion functions that could throw overflow exceptions.
     *
     * Time Complexity  : O(max(n, m)) - loops for the length of the longer string.
     * Space Complexity : O(max(n, m)) - to store the result string in the StringBuilder.
     */
    static String addBinaryOptimal(String s1, String s2) {
        // Safe check for null or empty strings
        if (s1 == null || s1.length() == 0) return s2;
        if (s2 == null || s2.length() == 0) return s1;

        StringBuilder sb = new StringBuilder(); // Holds the reversed binary sum digits
        int i = s1.length() - 1;               // Pointer at the end of s1
        int j = s2.length() - 1;               // Pointer at the end of s2
        int carry = 0;                         // Carry digit initialized to 0

        // Traverse both strings from right to left
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry; // Start with the carry value from the previous column

            // Add digit from s1 if pointer is valid
            if (i >= 0) {
                sum += s1.charAt(i) - '0'; // Convert character '0'/'1' to numeric 0/1
                i--;                       // Move pointer left
            }

            // Add digit from s2 if pointer is valid
            if (j >= 0) {
                sum += s2.charAt(j) - '0'; // Convert character '0'/'1' to numeric 0/1
                j--;                       // Move pointer left
            }

            sb.append(sum % 2); // The binary digit at the current position is sum % 2 (either 0 or 1)
            carry = sum / 2;    // The carry for the next position is sum / 2 (either 0 or 1)
        }

        // Reverse the accumulated string as we appended digits from least to most significant
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "1101";
        String s2 = "111";
        String s3 = "0";
        String s4 = "0";
        String s5 = "11111111111111111111111111111111"; // 32 bits, overflows standard 32-bit signed int
        String s6 = "1";

        System.out.println("Testing AddTwoBinary:");
        System.out.println("s1: " + s1 + ", s2: " + s2 + " -> Naive: " + addBinaryNaive(s1, s2) + " | Optimal: " + addBinaryOptimal(s1, s2));
        System.out.println("s3: " + s3 + ", s4: " + s4 + " -> Naive: " + addBinaryNaive(s3, s4) + " | Optimal: " + addBinaryOptimal(s3, s4));
        System.out.println("Large binary + 1 -> Optimal Result: " + addBinaryOptimal(s5, s6));
    }
}