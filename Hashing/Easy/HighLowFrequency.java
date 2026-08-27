// Find Highest and Lowest Frequency Elements in an Array

// Given an array of size N, find the elements that have the highest and the lowest frequencies.
// If there are multiple elements with the highest or lowest frequency, a standard tie-breaker
// (such as choosing the smaller numerical element) can be applied.

// Examples:

// Input: arr[] = {10, 5, 10, 15, 10, 5}
// Output: Highest: 10, Lowest: 15
// Explanation: The frequencies are: 10 -> 3 (highest), 5 -> 2, 15 -> 1 (lowest).

// Input: arr[] = {2, 2, 3, 3, 4}
// Output: Highest: 2, Lowest: 4
// Explanation: Frequencies: 2 -> 2, 3 -> 2, 4 -> 1.
//              For highest frequency, both 2 and 3 appear 2 times; tie-breaker picks smaller element 2.
//              Lowest frequency is 4.

// Input: arr[] = {1}
// Output: Highest: 1, Lowest: 1
// Explanation: Single element has frequency 1, representing both highest and lowest.

// Input: arr[] = {}
// Output: Highest: -1, Lowest: -1
// Explanation: Return sentinel -1 or handle empty bounds gracefully.

import java.util.HashMap;
import java.util.Map;

class HighLowFrequency {

    /*
     * [Alternative/Naive Approach] Nested Loops - O(n^2) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If array is empty, return.
     *   2. Use a visited boolean array of size n to avoid reprocessing duplicates.
     *   3. Track `maxFreq = 0`, `minFreq = n + 1`.
     *   4. Track `maxEle = -1`, `minEle = -1`.
     *   5. Outer loop i from 0 to n - 1:
     *        a. If `visited[i] == true`, skip.
     *        b. Count frequency of `arr[i]`: `count = 1`.
     *        c. Inner loop j from i + 1 to n - 1:
     *             - If `arr[j] == arr[i]`, mark `visited[j] = true` and increment `count`.
     *        d. Update max and min tracking variables based on `count`.
     *
     * Drawback:
     *   - Uses nested loops resulting in O(n^2) operations, which performs slowly on larger arrays.
     *
     * Time Complexity  : O(n^2) - nested iterations for counting frequencies.
     * Space Complexity : O(n)   - visited boolean array of size n.
     */
    static void findHighLowFreqNaive(int[] arr, int n) {
        if (arr == null || n == 0) {
            System.out.println("Highest: -1, Lowest: -1");
            return;
        }

        boolean[] visited = new boolean[n];
        int maxFreq = 0, minFreq = n + 1;
        int maxEle = -1, minEle = -1;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == arr[i]) {
                    visited[j] = true;
                    count++;
                }
            }

            // Update highest frequency element
            if (count > maxFreq) {
                maxFreq = count;
                maxEle = arr[i];
            } else if (count == maxFreq) {
                if (arr[i] < maxEle) {
                    maxEle = arr[i]; // Tie-breaker: pick smaller element
                }
            }

            // Update lowest frequency element
            if (count < minFreq) {
                minFreq = count;
                minEle = arr[i];
            } else if (count == minFreq) {
                if (arr[i] < minEle) {
                    minEle = arr[i]; // Tie-breaker: pick smaller element
                }
            }
        }

        System.out.println("Highest Frequency Element: " + maxEle + " (Frequency: " + maxFreq + ")");
        System.out.println("Lowest Frequency Element : " + minEle + " (Frequency: " + minFreq + ")");
    }

    /*
     * [Optimal/Interview Approach] Frequency Hashing - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If array is empty, return.
     *   2. Use a HashMap to count the frequency of each element in a single pass.
     *   3. Initialize:
     *        - `maxFreq = 0`, `minFreq = Integer.MAX_VALUE`.
     *        - `maxEle = -1`, `minEle = -1`.
     *   4. Iterate through the entry set of the HashMap:
     *        - For each entry (element, count):
     *            - If `count > maxFreq` -> update `maxFreq` and `maxEle`.
     *              Else if `count == maxFreq` -> tie break: if `element < maxEle`, update `maxEle`.
     *            - If `count < minFreq` -> update `minFreq` and `minEle`.
     *              Else if `count == minFreq` -> tie break: if `element < minEle`, update `minEle`.
     *   5. Print result.
     *
     * Why this is interview-preferred:
     *   - Strict linear time O(n) frequency tracking.
     *   - Elegant and robust logic that accurately handles cases where all elements have the same frequency.
     *
     * Time Complexity  : O(n) - single pass to populate HashMap, and one loop through unique elements to find boundaries.
     * Space Complexity : O(n) - auxiliary space for storing frequencies in the HashMap.
     */
    static void findHighLowFreqOptimal(int[] arr, int n) {
        if (arr == null || n == 0) {
            System.out.println("Highest: -1, Lowest: -1");
            return; // Edge Case
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        // Populate frequency map
        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        int maxEle = -1, minEle = -1;
        int maxFreq = 0;
        int minFreq = Integer.MAX_VALUE;

        // Find highest and lowest frequency elements
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int element = entry.getKey();
            int count = entry.getValue();

            // Track highest frequency
            if (count > maxFreq) {
                maxFreq = count;
                maxEle = element;
            } else if (count == maxFreq) {
                if (element < maxEle) {
                    maxEle = element; // Tie-breaker: pick smaller element
                }
            }

            // Track lowest frequency
            if (count < minFreq) {
                minFreq = count;
                minEle = element;
            } else if (count == minFreq) {
                if (element < minEle) {
                    minEle = element; // Tie-breaker: pick smaller element
                }
            }
        }

        System.out.println("Highest Frequency Element: " + maxEle + " (Frequency: " + maxFreq + ")");
        System.out.println("Lowest Frequency Element : " + minEle + " (Frequency: " + minFreq + ")");
    }

    public static void main(String[] args) {
        int[] arr = { 10, 5, 10, 15, 10, 5 };

        System.out.println("Testing HighLowFrequency (Naive):");
        findHighLowFreqNaive(arr, arr.length);

        System.out.println("\nTesting HighLowFrequency (Optimal):");
        findHighLowFreqOptimal(arr, arr.length);
    }
}
