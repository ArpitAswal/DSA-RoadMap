// Count Frequency of Each Element in the Array

// Given an array of N integers, find and print the frequency of each element in the array.
// The frequency of an element is the number of times it appears in the array.

// Examples:

// Input: arr[] = {10, 5, 10, 15, 10, 5}
// Output:
// 10 -> 3
// 5 -> 2
// 15 -> 1
// Explanation: 10 appears 3 times, 5 appears 2 times, 15 appears 1 time.

// Input: arr[] = {2, 2, 2, 2}
// Output:
// 2 -> 4
// Explanation: 2 appears 4 times.

// Input: arr[] = {}
// Output: (no output)
// Explanation: Empty array has no frequencies.

import java.util.HashMap;
import java.util.Map;

class CountFrequency {

    /*
     * [Alternative/Naive Approach] Nested Loops with Visited Flags - O(n^2) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If array is empty, return.
     *   2. Initialize a boolean array `visited` of size n to track elements already processed.
     *   3. Outer loop runs from i = 0 to n - 1:
     *        a. If `visited[i] == true`, skip since it's already counted.
     *        b. Count frequency of `arr[i]`: `count = 1`.
     *        c. Inner loop runs from j = i + 1 to n - 1:
     *             - If `arr[j] == arr[i]`, mark `visited[j] = true` and increment `count`.
     *        d. Print `arr[i]` and its `count`.
     *
     * Drawback:
     *   - Uses nested loops resulting in O(n^2) operations, which is too slow for arrays with size > 10^5.
     *
     * Time Complexity  : O(n^2) - due to nested comparisons.
     * Space Complexity : O(n)   - auxiliary boolean visited array of size n.
     */
    static void countFrequencyNaive(int[] arr, int n) {
        if (arr == null || n == 0) {
            return;
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            // Skip if this element is already processed
            if (visited[i]) {
                continue;
            }

            int count = 1;
            // Check remaining elements in the array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == arr[i]) {
                    visited[j] = true; // Mark as visited
                    count++;
                }
            }
            // Print the element frequency
            System.out.println(arr[i] + " -> " + count);
        }
    }

    /*
     * [Optimal/Interview Approach] Frequency Hashing - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If array is null or empty, return.
     *   2. Initialize a HashMap to store frequencies: `HashMap<Integer, Integer> frqMap`.
     *   3. Iterate through the array in a single pass:
     *        - Update key count: `frqMap.put(num, frqMap.getOrDefault(num, 0) + 1)`.
     *   4. Iterate through the entry set of the map:
     *        - Print the key and value.
     *
     * Why this is interview-preferred:
     *   - Processes frequencies in $O(n)$ average time.
     *   - Handles negative numbers, large range inputs, and duplicates cleanly.
     *
     * Time Complexity  : O(n) - single pass to populate map, and O(u) to print where u is number of unique elements.
     * Space Complexity : O(n) - auxiliary space to store at most n unique elements in the HashMap.
     */
    static void countFrequencyOptimal(int[] arr, int n) {
        if (arr == null || n == 0) {
            return; // Edge Case: empty array
        }

        HashMap<Integer, Integer> frqMap = new HashMap<>();

        // Single pass frequency counting
        for (int i = 0; i < n; i++) {
            frqMap.put(arr[i], frqMap.getOrDefault(arr[i], 0) + 1);
        }

        // Print frequency of each element
        for (Map.Entry<Integer, Integer> entry : frqMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        int[] arr = { 10, 5, 10, 15, 10, 5 };

        System.out.println("Testing CountFrequency (Naive):");
        countFrequencyNaive(arr, arr.length);

        System.out.println("\nTesting CountFrequency (Optimal):");
        countFrequencyOptimal(arr, arr.length);
    }
}
