// Longest Substring Without Repeating Characters

// Given a string s, find the length of the longest substring without repeating characters.

// Examples:

// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.

// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.

// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

import java.util.HashSet;

public class LongestSubstringWithoutRepeating {

    /*
     * [Naive Approach] Check All Substrings - O(n^2) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Outer loop 'i' marks starting index of substring from 0 to n - 1.
     *   2. Create a fresh HashSet for unique characters for each starting index 'i'.
     *   3. Inner loop 'j' expands ending index from i to n - 1.
     *   4. If s.charAt(j) is already in set, break inner loop (duplicate found).
     *   5. Else add s.charAt(j) to set and update maxLen = max(maxLen, j - i + 1).
     *
     * Time Complexity  : O(n^2) - two nested loops traversing up to n characters.
     * Space Complexity : O(n)   - HashSet stores up to n distinct characters per window.
     */
    static int longestSubstringNaive(String s) {
        int n = s.length(); // Get length of string
        int maxLen = 0;     // Initialize maximum length found

        // Outer loop: start index of substring
        for (int i = 0; i < n; i++) {
            HashSet<Character> set = new HashSet<>(); // Set to track unique characters in current substring

            // Inner loop: end index of substring
            for (int j = i; j < n; j++) {
                char ch = s.charAt(j); // Current character at end pointer

                // Mismatch/duplicate found -> substring cannot be extended further
                if (set.contains(ch)) {
                    break;
                }

                set.add(ch); // Add unique character to set

                // Calculate current length (j - i + 1) and update maxLen
                int currLen = j - i + 1;
                if (currLen > maxLen) {
                    maxLen = currLen;
                }
            }
        }

        return maxLen;
    }

    /*
     * [Better Approach] Sliding Window with HashSet - O(n) Time and O(min(n, m)) Space
     *
     * Logic / Steps:
     *   1. Maintain a sliding window [left, right] and a HashSet for characters in current window.
     *   2. Expand window by moving 'right' pointer from 0 to n - 1.
     *   3. While set contains s.charAt(right), remove s.charAt(left) and increment 'left' pointer.
     *   4. Add s.charAt(right) to set.
     *   5. Update maxLen = max(maxLen, right - left + 1).
     *
     * Time Complexity  : O(n) - each character visited at most twice (by left and right pointers).
     * Space Complexity : O(min(n, m)) - HashSet size bounded by alphabet size m.
     */
    static int longestSubstringSlidingWindow(String s) {
        int maxLen = 0; // Tracks maximum length
        int left = 0;   // Left boundary of sliding window

        HashSet<Character> set = new HashSet<>(); // Set for characters in window

        // Right boundary of sliding window expands rightward
        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right); // Character entering window

            // Shrink window from left until duplicate character is removed
            while (set.contains(currChar)) {
                set.remove(s.charAt(left)); // Remove element at left pointer
                left++;                      // Move left boundary rightward
            }

            set.add(currChar); // Add current character into window set

            int windowLen = right - left + 1; // Compute window size
            if (windowLen > maxLen) {
                maxLen = windowLen; // Update overall maximum
            }
        }

        return maxLen;
    }

    /*
     * [Optimal / Interview Approach] Direct ASCII Index / Last-Seen Array - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain an integer array `lastIndex` of size 256, initialized with -1.
     *   2. Use 'left' pointer to mark start of valid window (initially 0).
     *   3. For each character at index 'right':
     *        - If lastIndex[s.charAt(right)] >= left: duplicate character detected!
     *        - Move 'left' pointer directly to lastIndex[s.charAt(right)] + 1 (jump past duplicate).
     *        - Update lastIndex[s.charAt(right)] = right.
     *        - Update maxLen = max(maxLen, right - left + 1).
     *
     * Why this is interview-preferred:
     *   - Replaces HashSet deletion while-loop with direct index jump O(1).
     *   - Uses fixed ASCII array of size 256 -> true O(1) space.
     *
     * Time Complexity  : O(n) - single traversal over the string.
     * Space Complexity : O(1) - fixed array of size 256.
     */
    static int longestSubstringOptimised(String s) {
        int[] lastIndex = new int[256]; // Stores last seen index of each ASCII character

        // Initialize all char positions to -1
        for (int i = 0; i < 256; i++) {
            lastIndex[i] = -1;
        }

        int maxLen = 0; // Global maximum length
        int left = 0;   // Left window boundary

        // Traverse string with right pointer
        for (int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right); // Current character

            // If character was seen inside current window [left, right], jump left pointer past previous occurrence
            if (lastIndex[currChar] >= left) {
                left = lastIndex[currChar] + 1;
            }

            // Update last seen index of current character
            lastIndex[currChar] = right;

            // Calculate current window length
            int currLen = right - left + 1;
            if (currLen > maxLen) {
                maxLen = currLen; // Update max length
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String str1 = "abcabcbb";
        System.out.println("String: \"" + str1 + "\"");
        System.out.println("1. Naive Approach Result          : " + longestSubstringNaive(str1));
        System.out.println("2. Sliding Window (Set) Result    : " + longestSubstringSlidingWindow(str1));
        System.out.println("3. Optimised Index Array Result   : " + longestSubstringOptimised(str1));

        System.out.println("\n--- Additional Tests ---");
        String[] testCases = { "bbbbb", "pwwkew", "", "au", "dvdf" };
        for (String test : testCases) {
            System.out.println("String: \"" + test + "\" -> Max Length: " + longestSubstringOptimised(test));
        }
    }
}
