import java.util.HashMap;
import java.util.Map;

// Count Frequency of Each Element in an Array

// Given an array arr[] of n integers, count the frequency of each element,
// i.e., find how many times each distinct element appears in the array.

// Examples:

// Input: arr[] = [1, 2, 2, 3, 3, 3, 4, 4, 4, 4]
// Output: {1=1, 2=2, 3=3, 4=4}
// Explanation: 1 appears 1 time, 2 appears 2 times, 3 appears 3 times, 4 appears 4 times.

// Input: arr[] = [5, 5, 5, 1]
// Output: {5=3, 1=1}
// Explanation: 5 appears 3 times, 1 appears 1 time.

// Input: arr[] = [10]
// Output: {10=1}
// Explanation: Only one element, appears once.

class CountElementFrequency {

    /*
     * [Naive Approach] Using Nested Loops - O(n^2) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Create a boolean array 'visited' to track elements we have already counted.
     *   2. For each element arr[i], skip it if it has been visited.
     *   3. Otherwise, iterate through the rest of the array to count
     *      how many times arr[i] appears.
     *   4. Print the element and its count, then mark arr[i] as visited.
     *
     * Time Complexity  : O(n^2) - two nested loops over the array.
     * Space Complexity : O(n)   - extra boolean array of size n.
     */
    static void countFrequencyNaive(int[] arr) {
        int n = arr.length;

        // Track which indices have already been counted
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            // Skip if this element was already counted in a previous iteration
            if (visited[i])
                continue;

            // Start counting from 1 (arr[i] itself)
            int count = 1;

            for (int j = i + 1; j < n; j++) {
                // If duplicate found, increment count and mark it visited
                if (arr[j] == arr[i]) {
                    count++;
                    visited[j] = true;
                }
            }

            // Print the frequency of the current element
            System.out.println(arr[i] + " -> " + count);
        }
    }

    /*
     * [Optimised Approach] Using HashMap - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Create a HashMap to store each element as a key and its count as the value.
     *   2. Traverse the array once; for each element, increment its count in the map
     *      using getOrDefault (default is 0 if the key is not yet present).
     *   3. After building the map, iterate over its entries and print each element
     *      with its frequency.
     *
     * Why this is interview-preferred:
     *   - Single pass over the array (O(n)) vs. two nested loops (O(n^2)).
     *   - HashMap lookups and insertions are O(1) on average.
     *
     * Time Complexity  : O(n) - one pass to build the map + one pass to print entries.
     * Space Complexity : O(n) - HashMap stores at most n distinct keys.
     */
    static void countFrequencyOptimised(int[] arr) {
        // HashMap to store element -> frequency mapping
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        // Single pass: build the frequency map
        for (int num : arr) {
            // If num is already present, add 1 to its count; otherwise start at 1
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Print each element along with its frequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};

        // Naive Approach - O(n^2)
        System.out.println("Naive Approach (Nested Loops):");
        countFrequencyNaive(arr);

        System.out.println();

        // Optimised Approach - O(n) using HashMap
        System.out.println("Optimised Approach (HashMap):");
        countFrequencyOptimised(arr);
    }
}