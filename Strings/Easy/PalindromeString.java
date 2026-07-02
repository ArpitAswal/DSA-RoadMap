// Palindrome String Check

// Given a string s, determine if it is a palindrome.
// A string is a palindrome if it reads the same forwards and backwards.
// The check is case-sensitive (i.e., "Madam" is NOT a palindrome by default).

// Examples:

// Input: s = "madam"
// Output: The string is a palindrome
// Explanation: "madam" reversed is "madam" - same string.

// Input: s = "hello"
// Output: The string is not a palindrome
// Explanation: "hello" reversed is "olleh" - different strings.

// Input: s = "racecar"
// Output: The string is a palindrome
// Explanation: "racecar" reversed is "racecar" - same string.

// Input: s = "a"
// Output: The string is a palindrome
// Explanation: A single character is always a palindrome.

class PalindromeString {

    /*
     * [Naive Approach] Build Reversed String and Compare - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Iterate the original string from the last character to the first.
     *   2. Concatenate each character to build a reversed string.
     *   3. Compare the reversed string with the original using .equals().
     *   4. If equal, it is a palindrome; otherwise, it is not.
     *
     * Drawback: Creates a new String object of size n → O(n) extra space.
     *           String concatenation in a loop is also inefficient (use StringBuilder
     *           in production code, but string reversal shows the core idea clearly).
     *
     * Time Complexity  : O(n) - one pass to build the reversed string + O(n) to compare.
     * Space Complexity : O(n) - extra space for the reversed string.
     */
    static boolean isPalindromeNaive(String s) {
        String rev = "";

        // Build reversed string by reading characters from end to start
        for (int i = s.length() - 1; i >= 0; i--) {
            rev += s.charAt(i); // Append each character in reverse order
        }

        // Compare original and reversed strings
        return s.equals(rev);
    }

    /*
     * [Optimised Approach] Two-Pointer Technique - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Place one pointer 'start' at the beginning (index 0).
     *   2. Place another pointer 'end' at the last character (index n-1).
     *   3. Compare characters at both pointers:
     *        - If they are NOT equal → not a palindrome, return false immediately.
     *        - If they ARE equal → move start forward and end backward.
     *   4. Continue until the two pointers meet or cross each other.
     *   5. If all pairs matched, the string is a palindrome → return true.
     *
     * Why this is interview-preferred:
     *   - No extra string created → O(1) space.
     *   - Can short-circuit early on mismatch → often faster in practice.
     *
     * Time Complexity  : O(n) - at most n/2 character comparisons.
     * Space Complexity : O(1) - only two integer pointer variables.
     */
    static boolean isPalindromeOptimised(String s) {
        int start = 0;           // Left pointer starting at the beginning
        int end = s.length() - 1; // Right pointer starting at the end

        // Move both pointers towards the centre
        while (start < end) {
            // Mismatch found → not a palindrome
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++; // Move left pointer rightward
            end--;   // Move right pointer leftward
        }

        // All characters matched → it's a palindrome
        return true;
    }

    public static void main(String[] args) {
        String str = "madam";

        // Naive Approach - O(n) time, O(n) space (reversed string comparison)
        System.out.println("Naive Approach (Reversed String):");
        if (isPalindromeNaive(str)) {
            System.out.println("\"" + str + "\" is a palindrome");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome");
        }

        System.out.println();

        // Optimised Approach - O(n) time, O(1) space (two-pointer)
        System.out.println("Optimised Approach (Two Pointers):");
        if (isPalindromeOptimised(str)) {
            System.out.println("\"" + str + "\" is a palindrome");
        } else {
            System.out.println("\"" + str + "\" is not a palindrome");
        }

        System.out.println();

        // Additional test cases
        System.out.println("--- Additional Tests ---");
        String[] tests = {"racecar", "hello", "a", "abba"};
        for (String t : tests) {
            System.out.println("\"" + t + "\": " + (isPalindromeOptimised(t) ? "Palindrome" : "Not Palindrome"));
        }
    }
}