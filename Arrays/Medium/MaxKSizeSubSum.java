// Maximum Sum of Subarray of Size K

// Given an array of integers arr[] and a positive integer k, find the maximum sum of any
// contiguous subarray of size exactly k.

// Examples:

// Input: arr[] = [1, 2, 3, -2, 5, 4, 6, -1, 2], k = 3
// Output: 15
// Explanation: Subarrays of size 3 and their sums:
//   - [1, 2, 3]   => sum = 6
//   - [2, 3, -2]  => sum = 3
//   - [3, -2, 5]  => sum = 6
//   - [-2, 5, 4]  => sum = 7
//   - [5, 4, 6]   => sum = 15  (Maximum)
//   - [4, 6, -1]  => sum = 9
//   - [6, -1, 2]  => sum = 7
//   The maximum subarray sum of size 3 is 15.

// Input: arr[] = [100, 200, 300, 400], k = 2
// Output: 700
// Explanation: Subarray [300, 400] has max sum 700.

class MaxKSizeSubSum {

    /*
     * [Naive Approach] Brute Force using Nested Loops - O(n * k) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Check edge case: if array length n < k, return -1 (invalid window size).
     *   2. Initialize maxSum to smallest possible integer value (Integer.MIN_VALUE).
     *   3. Run an outer loop 'i' from index 0 up to n - k:
     *        a. Reset current window sum: currentSum = 0.
     *        b. Run an inner loop 'j' from i to i + k - 1, add arr[j] to currentSum.
     *        c. If currentSum > maxSum, update maxSum = currentSum.
     *   4. Return maxSum after inspecting all (n - k + 1) subarrays of size k.
     *
     * Time Complexity  : O(n * k) - for each of (n - k + 1) subarrays, inner loop runs k times.
     * Space Complexity : O(1)     - only primitive variables used.
     */
    static int maxKSizeSubSumNaive(int[] arr, int k) {
        int n = arr.length; // Length of the array

        // Return -1 if array size is smaller than window size k
        if (n < k) {
            System.out.println("Invalid input: Array size is smaller than k.");
            return -1;
        }

        int maxSum = Integer.MIN_VALUE; // Initialize max sum to lowest integer

        // Outer loop marks starting index of each subarray of size k
        for (int i = 0; i <= n - k; i++) {
            int currentSum = 0; // Reset sum for current window

            // Inner loop calculates sum of k elements starting at index i
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j]; // Add element at index j
            }

            // Update maxSum if current window sum is larger
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }

    /*
     * [Optimal / Interview Approach] Sliding Window Technique - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Check edge case: if array length n < k, return -1.
     *   2. Calculate sum of the first window of size k (from index 0 to k - 1).
     *   3. Set maxSum = currentSum.
     *   4. Slide window across the array from index k to n - 1:
     *        - Add element entering window: arr[i].
     *        - Subtract element leaving window: arr[i - k].
     *        - currentSum = currentSum + arr[i] - arr[i - k].
     *        - If currentSum > maxSum, update maxSum = currentSum.
     *   5. Return maxSum.
     *
     * Why this is interview-preferred:
     *   - Reduces time complexity from O(n * k) to O(n) by reusing window sum calculations.
     *   - Only computes addition of new element and subtraction of old element in O(1) per step.
     *
     * Time Complexity  : O(n) - one loop to compute first window + one loop to slide window.
     * Space Complexity : O(1) - constant auxiliary space.
     */
    static int maxKSizeSubSumOptimised(int[] arr, int k) {
        int n = arr.length; // Get length of array

        // Return -1 if array size is smaller than window size k
        if (n < k) {
            System.out.println("Invalid input: Array size is smaller than k.");
            return -1;
        }

        int currentSum = 0; // Holds sum of current window of size k

        // Step 1: Compute sum of first window (indices 0 to k - 1)
        for (int i = 0; i < k; i++) {
            currentSum += arr[i]; // Accumulate initial k elements
        }

        int maxSum = currentSum; // Initialize maxSum with first window sum

        // Step 2: Slide window from index k to end of array n - 1
        for (int i = k; i < n; i++) {
            // Update sum: add element entering window arr[i], subtract element leaving window arr[i - k]
            currentSum += arr[i] - arr[i - k];

            // Compare and update max sum if current window sum is higher
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum; // Return maximum contiguous subarray sum found
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, -2, 5, 4, 6, -1, 2 };
        int k = 3;

        System.out.println("Input Array: ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println("\nWindow Size k: " + k + "\n");

        // 1. Naive Approach - O(n * k) Time, O(1) Space
        int naiveResult = maxKSizeSubSumNaive(arr, k);
        System.out.println("1. Naive Approach Max Sum     : " + naiveResult);

        // 2. Sliding Window Approach - O(n) Time, O(1) Space (Interview-Preferred)
        int optResult = maxKSizeSubSumOptimised(arr, k);
        System.out.println("2. Sliding Window Max Sum      : " + optResult);
    }
}