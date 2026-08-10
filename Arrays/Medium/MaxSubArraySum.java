// Maximum Subarray Sum (Kadane's Algorithm)

// Given an array of integers arr[], find the contiguous subarray (containing at least one number)
// which has the maximum sum, and return that maximum sum.

// Examples:

// Input: arr[] = [1, 2, 3, -2, 5]
// Output: 9
// Explanation: The contiguous subarray [1, 2, 3, -2, 5] has the maximum sum = 1 + 2 + 3 + (-2) + 5 = 9.

// Input: arr[] = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
// Output: 6
// Explanation: The contiguous subarray [4, -1, 2, 1] has the maximum sum = 4 + (-1) + 2 + 1 = 6.

// Input: arr[] = [-1, -2, -3, -4]
// Output: -1
// Explanation: All elements are negative. The single-element subarray [-1] gives maximum sum -1.

class MaxSubArraySum {

    /*
     * [Naive Approach] Brute Force (All Subarrays) - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize maxSum with arr[0].
     *   2. Outer loop 'i' marks starting index of subarray (from 0 to n - 1).
     *   3. Reset currentSum = 0.
     *   4. Inner loop 'j' marks ending index of subarray (from i to n - 1).
     *        - Add arr[j] to currentSum.
     *        - If currentSum > maxSum, update maxSum = currentSum.
     *   5. Return maxSum after testing all n*(n+1)/2 subarrays.
     *
     * Time Complexity  : O(n^2) - two nested loops over n elements.
     * Space Complexity : O(1)   - only integer variables used.
     */
    static int maxSubArraySumNaive(int[] arr) {
        int n = arr.length;  // Length of the array
        int maxSum = arr[0]; // Initialize max sum with first element

        // Outer loop picks starting element of subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0; // Reset sum for subarray starting at i

            // Inner loop picks ending element of subarray
            for (int j = i; j < n; j++) {
                currentSum += arr[j]; // Add current element to sum

                // Check if current subarray sum exceeds max sum seen so far
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }

        return maxSum; // Return maximum contiguous subarray sum
    }

    /*
     * [Optimal / Interview Approach] Kadane's Algorithm - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain two variables:
     *        - 'currentSum': max subarray sum ending at current index. Initialize to arr[0].
     *        - 'maxSum': global max subarray sum found so far. Initialize to arr[0].
     *   2. Loop through array starting from index 1 to n - 1:
     *        - For element arr[i], choose whether to extend existing subarray (currentSum + arr[i])
     *          or start a brand new subarray from arr[i] itself.
     *        - Statement without shortcut functions:
     *          if (currentSum + arr[i] > arr[i]) {
     *              currentSum += arr[i]; // Extend current subarray
     *          } else {
     *              currentSum = arr[i];  // Start new subarray at arr[i]
     *          }
     *        - Update maxSum if currentSum > maxSum:
     *          if (currentSum > maxSum) {
     *              maxSum = currentSum;
     *          }
     *   3. Return maxSum.
     *
     * Why this is interview-preferred:
     *   - Solves the problem in a single pass O(n) using dynamic programming / greedy paradigm.
     *   - Requires no extra array or data structure -> O(1) auxiliary space.
     *
     * Time Complexity  : O(n) - single pass through array of size n.
     * Space Complexity : O(1) - only two variables needed.
     */
    static int maxSubArraySumOptimised(int[] arr) {
        // Initialize currentSum and maxSum with the first element of array
        int currentSum = arr[0];
        int maxSum = arr[0];

        // Traverse array from index 1 onwards
        for (int i = 1; i < arr.length; i++) {
            // Decide whether to add arr[i] to current sub-array or start fresh from arr[i]
            if (arr[i] + currentSum > arr[i]) {
                currentSum += arr[i]; // Add arr[i] to expand existing subarray
            } else {
                currentSum = arr[i];  // Discard previous sum and start new subarray at index i
            }

            // Update overall global maximum subarray sum if current sum is greater
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum; // Return maximum contiguous sum found
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, -2, 5 };
        System.out.println("--- Test Case 1: Positive and Negative mix ---");
        System.out.println("1. Naive Approach Max Sum    : " + maxSubArraySumNaive(arr1));
        System.out.println("2. Kadane's Algorithm Max Sum : " + maxSubArraySumOptimised(arr1));

        System.out.println();

        int[] arr2 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println("--- Test Case 2: Classic Kadane array ---");
        System.out.println("1. Naive Approach Max Sum    : " + maxSubArraySumNaive(arr2));
        System.out.println("2. Kadane's Algorithm Max Sum : " + maxSubArraySumOptimised(arr2));

        System.out.println();

        int[] arr3 = { -1, -2, -3, -4 };
        System.out.println("--- Test Case 3: All negative elements ---");
        System.out.println("1. Naive Approach Max Sum    : " + maxSubArraySumNaive(arr3));
        System.out.println("2. Kadane's Algorithm Max Sum : " + maxSubArraySumOptimised(arr3));
    }
}