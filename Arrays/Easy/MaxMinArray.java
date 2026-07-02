// Find Maximum and Minimum Element in an Array

// Given an array arr[] of n integers, find the maximum and minimum element
// present in the array.

// Examples:

// Input: arr[] = [12, 4, 56, 2, 18]
// Output: Max = 56, Min = 2
// Explanation: 56 is the largest and 2 is the smallest element.

// Input: arr[] = [3, 3, 3]
// Output: Max = 3, Min = 3
// Explanation: All elements are equal, so max and min are the same.

// Input: arr[] = [-10, -3, -1, -8]
// Output: Max = -1, Min = -10
// Explanation: Works correctly with negative numbers too.

class MaxMinArray {

    /*
     * [Naive Approach] Using Sorting - O(n log n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Sort the array in ascending order.
     *   2. The first element (index 0) is the minimum.
     *   3. The last element (index n-1) is the maximum.
     *
     * Drawback: Sorting modifies the original array and costs O(n log n),
     *           which is unnecessary when we only need max and min.
     *
     * Time Complexity  : O(n log n) - due to sorting.
     * Space Complexity : O(1)       - no extra space used (in-place sort).
     */
    static void findMaxMinNaive(int[] arr) {
        int n = arr.length;

        // Sort the array to bring min to front, max to end
        java.util.Arrays.sort(arr);

        // After sorting: first element = min, last element = max
        System.out.println("Max: " + arr[n - 1]);
        System.out.println("Min: " + arr[0]);
    }

    /*
     * [Optimised Approach] Single Linear Scan - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Assume the first element is both max and min.
     *   2. Traverse the array from index 1 to n-1.
     *   3. If the current element is greater than maxVal, update maxVal.
     *   4. If the current element is less than minVal, update minVal.
     *   5. After one full pass, maxVal and minVal hold the answer.
     *
     * Why this is interview-preferred:
     *   - Only one pass through the array → O(n).
     *   - No extra memory, no sorting, original array unchanged.
     *
     * Time Complexity  : O(n) - single traversal of the array.
     * Space Complexity : O(1) - only two extra variables used.
     */
    static void findMaxMinOptimised(int[] arr) {
        // Start by assuming first element is both max and min
        int maxVal = arr[0];
        int minVal = arr[0];

        // Traverse from index 1 onwards (index 0 already considered)
        for (int i = 1; i < arr.length; i++) {
            // Update max if current element is larger
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
            // Update min if current element is smaller
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
        }

        System.out.println("Max: " + maxVal);
        System.out.println("Min: " + minVal);
    }

    public static void main(String[] args) {
        int[] arr = {12, 4, 56, 2, 18};

        // Naive Approach - O(n log n) using sorting
        System.out.println("Naive Approach (Sorting):");
        // Note: we pass a copy so sorting doesn't affect the optimised test
        findMaxMinNaive(arr.clone());

        System.out.println();

        // Optimised Approach - O(n) using single linear scan
        System.out.println("Optimised Approach (Single Pass):");
        findMaxMinOptimised(arr);
    }
}