// Maximum Circular Subarray Sum

// Given a circular integer array arr[] of size n, find the maximum possible sum of a non-empty subarray.
// A circular array means the end of the array connects to the beginning of the array.
// A subarray may only include each element of the fixed buffer arr at most once.

// Examples:

// Input: arr[] = [8, -8, 9, -9, 10, -11, 12]
// Output: 22
// Explanation: The circular subarray [12, 8, -8, 9, -9, 10] has the maximum sum = 22.

// Input: arr[] = [10, -3, -4, 7, 65, -100]
// Output: 75
// Explanation: Subarray [7, 65, 10] circular sum = 75.

// Input: arr[] = [-1, -2, -3]
// Output: -1
// Explanation: All elements negative; maximum single-element subarray sum is -1.

import java.util.Arrays;

class MaximumCircularSubArraySum {

    /*
     * [Naive Approach] Explore All Circular Subarrays - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Outer loop 'i' considers each starting position from index 0 to n - 1.
     *   2. Inner loop 'j' considers subarrays of lengths 1 to n starting at i.
     *   3. Access elements using circular index: `arr[(i + k) % n]`.
     *   4. Accumulate current sum and update maxCircularSum.
     *
     * Time Complexity  : O(n^2) - double loop over array elements.
     * Space Complexity : O(1)   - constant auxiliary variables.
     */
    static int findCircularSumNaive(int[] arr) {
        int n = arr.length;                // Length of array
        int maxCircularSum = Integer.MIN_VALUE; // Global max sum

        // Outer loop picks start element of circular subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0; // Reset sum for subarray starting at i

            // Inner loop adds up to n elements in circular fashion
            for (int k = 0; k < n; k++) {
                int index = (i + k) % n; // Circular indexing
                currentSum += arr[index];

                // Update max sum seen so far
                if (currentSum > maxCircularSum) {
                    maxCircularSum = currentSum;
                }
            }
        }

        return maxCircularSum;
    }

    /*
     * [Optimal / Interview Approach] Kadane's Dual Pass (Max Subarray & Min Subarray Sum) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. A maximum circular subarray sum can occur in two cases:
     *        - Case 1 (Non-wrapping subarray): Standard Kadane's max subarray sum (`maxKadane`).
     *        - Case 2 (Wrapping circular subarray): Total sum of array MINUS minimum subarray sum (`totalSum - minKadane`).
     *   2. Traverse array in a single pass O(n):
     *        - Compute `maxKadane`: standard Kadane algorithm for maximum subarray sum.
     *        - Compute `minKadane`: Kadane algorithm for minimum subarray sum.
     *        - Compute `totalSum`: sum of all elements in array.
     *   3. Special Edge Case: If all elements are negative (`minKadane == totalSum`), wrapping sum is 0 (empty set invalid).
     *      Return `maxKadane`.
     *   4. Otherwise return `max(maxKadane, totalSum - minKadane)`.
     *
     * Why this is interview-preferred:
     *   - Replaces circular array simulation with elegant mathematical relation: Wrapping Max Sum = Total Sum - Minimum Non-Wrapping Subarray.
     *   - Operates in linear time O(n) and O(1) space.
     *
     * Time Complexity  : O(n) - single pass over array of size n.
     * Space Complexity : O(1) - primitive variables used.
     */
    static int findCircularSumKadane(int[] arr) {
        int currMax = 0;
        int currMin = 0;
        int maxKadane = arr[0];
        int minKadane = arr[0];
        int totalSum = 0;

        // Traverse array to compute max subarray, min subarray, and total sum
        for (int i = 0; i < arr.length; i++) {
            // Standard Kadane for Max Subarray Sum
            if (currMax + arr[i] > arr[i]) {
                currMax += arr[i];
            } else {
                currMax = arr[i];
            }

            if (currMax > maxKadane) {
                maxKadane = currMax;
            }

            // Standard Kadane for Min Subarray Sum
            if (currMin + arr[i] < arr[i]) {
                currMin += arr[i];
            } else {
                currMin = arr[i];
            }

            if (currMin < minKadane) {
                minKadane = currMin;
            }

            // Total Array Sum accumulation
            totalSum += arr[i];
        }

        // Edge case: If all elements are negative, minKadane == totalSum
        if (minKadane == totalSum) {
            return maxKadane;
        }

        // Return maximum of non-wrapping sum (maxKadane) and wrapping sum (totalSum - minKadane)
        int wrappingSum = totalSum - minKadane;
        return (maxKadane > wrappingSum) ? maxKadane : wrappingSum;
    }

    public static void main(String[] args) {
        int[] arr1 = { 8, -8, 9, -9, 10, -11, 12 };

        System.out.println("Input Array: " + Arrays.toString(arr1) + "\n");
        System.out.println("1. Naive Approach Result    : " + findCircularSumNaive(arr1));
        System.out.println("2. Kadane Dual Approach     : " + findCircularSumKadane(arr1));

        System.out.println("\n--- Additional Tests ---");
        int[][] testCases = {
            { 10, -3, -4, 7, 65, -100 },
            { -1, -2, -3 },
            { 5, -3, 5 }
        };

        for (int[] test : testCases) {
            System.out.println("Array: " + Arrays.toString(test) + 
                               " -> Max Circular Sum: " + findCircularSumKadane(test));
        }
    }
}