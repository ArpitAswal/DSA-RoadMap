// Palindrome String Check using Recursion

// Given a string s, determine if it is a palindrome using a recursive function.
// A string is a palindrome if it reads the same forward and backward (case-sensitive).

// Examples:

// Input: s = "racecar"
// Output: true
// Explanation: "racecar" reversed is "racecar", which matches the original.

// Input: s = "ABCDCBABA"
// Output: false
// Explanation: Reversed string is "ABABCDCBA", which does not match the original.

// Input: s = "a"
// Output: true
// Explanation: A single character string is always a palindrome.

// Input: s = ""
// Output: true
// Explanation: An empty string reads the same forward and backward.

class StringPalindrome {

    /*
     * [Alternative Approach] Iterative Two-Pointer - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If string is null, return false.
     *   2. Initialize `left = 0` and `right = s.length() - 1`.
     *   3. While `left < right`:
     *        a. If `s.charAt(left) != s.charAt(right)`, return false.
     *        b. Move pointers inwards: `left++`, `right--`.
     *   4. If loop completes, return true.
     *
     * Time Complexity  : O(n) - checks at most n / 2 pairs.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static boolean isPalindromeIterative(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        // Loop until pointers meet in the middle
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false; // Mismatched characters
            }
            left++;
            right--;
        }
        return true;
    }

    /*
     * [Optimal/Interview Approach] Recursive Two-Pointer - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If string is null, return false.
     *   2. Base Case: if `left >= right`, all matching character pairs have been verified -> return true.
     *   3. Check step: if `s.charAt(left) != s.charAt(right)`, characters mismatch -> return false.
     *   4. Recursive Case: return `isPalRecursive(s, left + 1, right - 1)` to verify remaining inner characters.
     *
     * Why this is interview-preferred:
     *   - Solves the problem recursively by shrinking bounds by 2 at each step.
     *   - Avoids creating new substring objects (which would cost O(n^2) time and space).
     *
     * Time Complexity  : O(n) - checks at most n / 2 pairs.
     * Space Complexity : O(n) - call stack space of depth n / 2.
     */
    static boolean isPalRecursive(String s, int left, int right) {
        if (s == null) {
            return false;
        }

        // Base case: all checked characters matched successfully
        if (left >= right) {
            return true;
        }

        // Mismatch found at the current boundaries
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }

        // Recursive step: check the sub-string within the next inner boundary
        return isPalRecursive(s, left + 1, right - 1);
    }

    public static void main (String[] args) {
        String str1 = "racecar";
        String str2 = "ABCDCBABA";

        System.out.println("String: \"" + str1 + "\" -> Palindrome (Iterative): " + isPalindromeIterative(str1) 
                           + " | (Recursive): " + isPalRecursive(str1, 0, str1.length() - 1));

        System.out.println("String: \"" + str2 + "\" -> Palindrome (Iterative): " + isPalindromeIterative(str2) 
                           + " | (Recursive): " + isPalRecursive(str2, 0, str2.length() - 1));
    }
}