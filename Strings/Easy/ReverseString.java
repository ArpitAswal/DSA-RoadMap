// Reverse a String

// Given a string s, return the string with its characters in reversed order.

// Examples:

// Input: s = "string"
// Output: "gnirts"
// Explanation: Characters read from the end to the start.

// Input: s = "hello"
// Output: "olleh"
// Explanation: "hello" reversed character by character gives "olleh".

// Input: s = "a"
// Output: "a"
// Explanation: A single character reversed is itself.

// Input: s = "abcd"
// Output: "dcba"
// Explanation: The order of all characters is flipped.

class ReverseString {

    /*
     * [Naive Approach] Using StringBuilder - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Iterate the original string from the last character to the first.
     *   2. Append each character to a StringBuilder.
     *   3. Convert the StringBuilder to a String and return it.
     *
     * Note: Using StringBuilder instead of String concatenation avoids creating
     *       a new String object on every append, making it cleaner in practice.
     *       However, it still requires O(n) extra space for the StringBuilder buffer.
     *
     * Time Complexity  : O(n) - one pass through the string.
     * Space Complexity : O(n) - extra space for the StringBuilder / result string.
     */
    static String reverseStringNaive(String s) {
        StringBuilder reversed = new StringBuilder();

        // Traverse from the last index down to 0
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i)); // Append character in reverse order
        }

        return reversed.toString();
    }

    /*
     * [Optimised Approach] Two-Pointer In-Place Swap on char[] - O(n) Time and O(1) Space*
     *
     * Logic / Steps:
     *   1. Convert the String to a char array (Strings in Java are immutable,
     *      so we need a mutable char array to do in-place swapping).
     *   2. Place a 'start' pointer at index 0 and an 'end' pointer at index n-1.
     *   3. Swap chars[start] and chars[end].
     *   4. Move start forward and end backward.
     *   5. Repeat until start >= end (pointers have crossed / met in the middle).
     *   6. Convert the char array back to a String and return.
     *
     * *Note on "O(1) space": The char array of size n is mandatory in Java because
     *  strings are immutable. In languages with mutable strings (e.g., C++), this
     *  approach is truly O(1) extra space. For Java, we mention O(1) auxiliary space
     *  (excluding the char array that replaces the string itself).
     *
     * Why this is interview-preferred:
     *   - No extra StringBuilder/String created → minimal auxiliary space.
     *   - Only n/2 swaps needed → efficient and clean.
     *
     * Time Complexity  : O(n)  - n/2 swaps, which is O(n) overall.
     * Space Complexity : O(n)  - char array of size n (unavoidable in Java due to
     *                           String immutability); O(1) auxiliary extra space.
     */
    static String reverseStringOptimised(String s) {
        // Convert String to mutable char array
        char[] chars = s.toCharArray();

        int start = 0;               // Left pointer at the beginning
        int end = chars.length - 1;  // Right pointer at the end

        // Swap characters from both ends, moving towards the centre
        while (start < end) {
            // Classic three-step swap using a temp variable
            char temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;

            start++; // Move left pointer rightward
            end--;   // Move right pointer leftward
        }

        // Convert the reversed char array back to a String
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String str = "string";

        // Naive Approach - O(n) time, O(n) space (StringBuilder)
        System.out.println("Naive Approach (StringBuilder):");
        System.out.println("Reverse of \"" + str + "\" is: " + reverseStringNaive(str));

        System.out.println();

        // Optimised Approach - O(n) time, O(1) auxiliary space (Two Pointers + char[])
        System.out.println("Optimised Approach (Two Pointers / In-Place Swap):");
        System.out.println("Reverse of \"" + str + "\" is: " + reverseStringOptimised(str));

        System.out.println();

        // Additional test cases
        System.out.println("--- Additional Tests ---");
        String[] tests = {"hello", "abcd", "a", "racecar"};
        for (String t : tests) {
            System.out.println("\"" + t + "\" -> \"" + reverseStringOptimised(t) + "\"");
        }
    }
}