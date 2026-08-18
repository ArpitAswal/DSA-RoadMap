// Anagram String Check

// Given two strings s1 and s2, check if they are anagrams of each other.
// An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
// typically using all the original letters exactly once.

// Examples:

// Input: s1 = "listen", s2 = "silent"
// Output: true
// Explanation: Both strings contain 'l', 'i', 's', 't', 'e', 'n' with exactly the same frequency count of 1.

// Input: s1 = "anagram", s2 = "nagaram"
// Output: true
// Explanation: Both contain 'a' (3 times), 'n' (1 time), 'g' (1 time), 'r' (1 time), 'm' (1 time).

// Input: s1 = "hello", s2 = "world"
// Output: false
// Explanation: The letters and their frequencies do not match.

// Input: s1 = "Triangle", s2 = "Integral"
// Output: false (if case-sensitive) or true (if case-insensitive)
// Explanation: In our default check, it is case-sensitive, so 'T' and 't' are distinct.

import java.util.Arrays;

class AnagramString {

    /*
     * [Alternative/Naive Approach] Sorting - O(n log n) Time and O(n) Space
     *
     * Logic / Steps:
     * 1. If lengths of s1 and s2 are different, they cannot be anagrams -> return
     * false.
     * 2. Convert both strings to character arrays: `char1 = s1.toCharArray()`,
     * `char2 = s2.toCharArray()`.
     * 3. Sort both arrays using Arrays.sort().
     * 4. Compare both sorted arrays using Arrays.equals().
     * 5. Return the result.
     *
     * Drawback: Converting strings to char arrays and sorting them takes extra
     * memory and O(n log n) time.
     *
     * Time Complexity : O(n log n) - sorting character arrays of length n.
     * Space Complexity : O(n) - auxiliary space for character arrays of size n.
     */
    static boolean areAnagramsSorting(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return s1 == s2; // Handle null inputs safely
        }
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();

        // Sort both character arrays
        Arrays.sort(char1);
        Arrays.sort(char2);

        // Compare elements index-by-index
        return Arrays.equals(char1, char2);
    }

    /*
     * [Optimal/Interview Approach] Frequency Count Array - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     * 1. If lengths are different, return false.
     * 2. Initialize an integer frequency count array of size 256 (covering standard
     * ASCII characters).
     * 3. Loop through s1 and s2 simultaneously or sequentially:
     * a. Increment count for character in s1: `count[s1.charAt(i)]++`.
     * b. Decrement count for character in s2: `count[s2.charAt(i)]--`.
     * 4. Loop through the count array:
     * - If any character has a non-zero count, it means the character frequencies
     * do not match -> return false.
     * 5. If all counts are 0, return true.
     *
     * Why this is interview-preferred:
     * - Solves the problem in a single pass O(n) without sorting overhead.
     * - Uses true O(1) constant auxiliary space (fixed 256 size count array).
     *
     * Time Complexity : O(n) - single pass over strings of length n.
     * Space Complexity : O(1) - fixed count array of size 256.
     */
    static boolean areAnagramsOptimal(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return s1 == s2; // Safe check for null references
        }
        if (s1.length() != s2.length()) {
            return false; // Anagrams must have the same length
        }

        int[] count = new int[256]; // Store frequency of all 256 ASCII characters

        // Count frequencies
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i)]++; // Increment for s1
            count[s2.charAt(i)]--; // Decrement for s2
        }

        // Verify if all counts returned back to 0
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0) {
                return false; // Mismatched frequency
            }
        }

        return true; // All character counts matched perfectly
    }

    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";
        String s3 = "hello";
        String s4 = "world";

        System.out.println("Testing AnagramString:");
        System.out.println("\"" + s1 + "\" and \"" + s2 + "\" -> Sorting: " + areAnagramsSorting(s1, s2)
                + " | Optimal: " + areAnagramsOptimal(s1, s2));
        System.out.println("\"" + s3 + "\" and \"" + s4 + "\" -> Sorting: " + areAnagramsSorting(s3, s4)
                + " | Optimal: " + areAnagramsOptimal(s3, s4));
    }
}