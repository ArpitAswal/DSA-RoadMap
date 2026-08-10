// First Non-Repeating Character in a String

// Given a string s, find and return the first character that does not repeat anywhere in the string.
// If all characters in the string repeat or the string is empty, return '$' (or '\0').

// Examples:

// Input: s = "swiss"
// Output: 'w'
// Explanation:
//   - 's' appears 3 times.
//   - 'w' appears 1 time.
//   - 'i' appears 1 time.
//   The non-repeating characters are 'w' and 'i'. 'w' comes first at index 1, so output is 'w'.

// Input: s = "racecar"
// Output: 'e'
// Explanation:
//   - 'r' appears 2 times, 'a' appears 2 times, 'c' appears 2 times, 'e' appears 1 time.
//   The first non-repeating character is 'e'.

// Input: s = "aabb"
// Output: '$'
// Explanation: All characters in "aabb" repeat. No non-repeating character exists.

import java.util.HashMap;

class FirstNonRepeating {

    /*
     * [Naive Approach] Brute Force using Nested Loops - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Iterate over each character s.charAt(i) with an outer loop pointer 'i'.
     *   2. Maintain a flag 'isUnique = true'.
     *   3. Run an inner loop pointer 'j' from 0 to n - 1.
     *   4. If i != j and s.charAt(i) == s.charAt(j), then the character is repeating!
     *      Set isUnique = false and break inner loop.
     *   5. If isUnique is still true after inner loop finishes, return s.charAt(i).
     *   6. If loop completes with no unique char found, return '$'.
     *
     * Time Complexity  : O(n^2) - for each of the n characters, scans up to n characters.
     * Space Complexity : O(1)   - only primitive boolean and loop index variables used.
     */
    static char firstNonRepeatingNaive(String s) {
        int n = s.length(); // Get length of string

        // Outer loop inspects each character one by one
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i); // Pick character at current index
            boolean isUnique = true;    // Assume current character is unique initially

            // Inner loop compares current character with every other character in string
            for (int j = 0; j < n; j++) {
                // Skip comparing character with itself at the same index
                if (i != j && s.charAt(j) == current) {
                    isUnique = false; // Match found at another index -> character is repeating
                    break;            // Stop inner loop early
                }
            }

            // If no match was found anywhere else in the string, return current character
            if (isUnique) {
                return current;
            }
        }

        // Return '$' if no non-repeating character exists
        return '$';
    }

    /*
     * [Better Approach] HashMap Frequency Count - O(n) Time and O(k) Space
     *
     * Logic / Steps:
     *   1. Create a HashMap<Character, Integer> to store character frequency counts.
     *   2. First Pass: Traverse string s from left to right, count occurrence of each char.
     *   3. Second Pass: Traverse string s from left to right again. Check HashMap for s.charAt(i).
     *   4. The first character with a frequency count of 1 is returned.
     *
     * Time Complexity  : O(n) - two passes over the string of length n.
     * Space Complexity : O(k) - HashMap stores k distinct characters (k <= 256).
     */
    static char firstNonRepeatingHashMap(String s) {
        HashMap<Character, Integer> freqMap = new HashMap<>(); // Store char -> frequency

        // Pass 1: Build frequency map
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Get character at index i
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1); // Increment count in map
        }

        // Pass 2: Find first character with count 1
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Read character in original string order
            if (freqMap.get(ch) == 1) {
                return ch; // Found first non-repeating character
            }
        }

        return '$'; // Return '$' if no non-repeating character exists
    }

    /*
     * [Optimal / Interview Approach] Fixed ASCII Frequency Array - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Create an integer array `count` of size 256 (covering all standard ASCII characters).
     *   2. First Pass: Iterate through string s, increment count[s.charAt(i)].
     *   3. Second Pass: Iterate through string s from left to right, inspect count[s.charAt(i)].
     *   4. The first character whose entry in `count` array is 1 is returned immediately.
     *
     * Why this is interview-preferred:
     *   - Replaces HashMap object overhead with a primitive array lookup -> faster memory & access speed.
     *   - Uses true O(1) auxiliary space (fixed 256 array size regardless of string size n).
     *
     * Time Complexity  : O(n) - pass 1 takes n iterations, pass 2 takes at most n iterations.
     * Space Complexity : O(1) - fixed array of size 256 (constant space).
     */
    static char firstNonRepeatingOptimised(String s) {
        int[] freq = new int[256]; // Array to store frequencies of all 256 ASCII characters

        // Pass 1: Populate frequency array
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Convert char to its ASCII index automatically
            freq[ch]++;            // Increment count for character ASCII code
        }

        // Pass 2: Find the first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Access character in original order
            if (freq[ch] == 1) {   // Check if frequency is exactly 1
                return ch;         // First non-repeating character found!
            }
        }

        // No non-repeating character found
        return '$';
    }

    public static void main(String[] args) {
        String s1 = "swiss";
        System.out.println("Input String 1: \"" + s1 + "\"");
        System.out.println("1. Naive Approach Result     : " + firstNonRepeatingNaive(s1));
        System.out.println("2. HashMap Approach Result   : " + firstNonRepeatingHashMap(s1));
        System.out.println("3. Optimised ASCII Result    : " + firstNonRepeatingOptimised(s1));

        System.out.println("\n--- Additional Tests ---");
        String[] testCases = { "racecar", "aabbcc", "geeksforgeeks", "a" };

        for (String test : testCases) {
            char res = firstNonRepeatingOptimised(test);
            System.out.println("String: \"" + test + "\" -> First Non-Repeating: '" + res + "'");
        }
    }
}