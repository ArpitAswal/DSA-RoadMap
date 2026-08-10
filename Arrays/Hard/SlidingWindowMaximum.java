// Sliding Window Maximum

// Given an array of integers arr[] and a sliding window of size k moving from left to right,
// find the maximum element in each sliding window of size k.

// Examples:

// Input: arr[] = [1, 2, 3, 1, 4, 5, 2, 3, 6], k = 3
// Output: [3, 3, 4, 5, 5, 5, 6]
// Explanation:
//   Window position                Max
//   ----------------               ---
//   [1  2  3] 1  4  5  2  3  6      3
//    1 [2  3  1] 4  5  2  3  6      3
//    1  2 [3  1  4] 5  2  3  6      4
//    1  2  3 [1  4  5] 2  3  6      5
//    1  2  3  1 [4  5  2] 3  6      5
//    1  2  3  1  4 [5  2  3] 6      5
//    1  2  3  1  4  5 [2  3  6]     6

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class SlidingWindowMaximum {

    /*
     * [Naive Approach] Brute Force per Window - O(n * k) Time and O(1) Space
     *
     * Logic / Steps:
     * 1. Outer loop 'i' from 0 to n - k:
     * - Inner loop 'j' from i to i + k - 1 finds maximum element in current window.
     * - Add maximum element to result list.
     * 2. Return result.
     *
     * Time Complexity : O(n * k) - for each of (n - k + 1) windows, scans k
     * elements.
     * Space Complexity : O(1) - auxiliary space (excluding output result array).
     */
    static int[] findMaxKWindowNaive(int[] arr, int k) {
        int n = arr.length; // Array length
        if (n == 0 || k <= 0)
            return new int[0];

        int[] res = new int[n - k + 1]; // Result array for n - k + 1 windows

        // Traverse each window start index
        for (int i = 0; i <= n - k; i++) {
            int maxVal = arr[i]; // Assume first element of window is max

            // Scan window elements to find max
            for (int j = i; j < i + k; j++) {
                if (arr[j] > maxVal) {
                    maxVal = arr[j];
                }
            }

            res[i] = maxVal; // Record max for current window
        }

        return res;
    }

    /*
     * [Optimal / Interview Approach] Monotonic Deque (Double-Ended Queue) - O(n)
     * Time and O(k) Space
     *
     * Logic / Steps:
     * 1. Create a Deque `dq` of Integers to store indices of array elements in
     * monotonically decreasing value order.
     * 2. First Window (indices 0 to k - 1):
     * - Remove indices from back of `dq` whose array values are <= current element
     * arr[i] (`dq.pollLast()`).
     * - Add current index `i` to back (`dq.addLast(i)`).
     * 3. Remaining Windows (indices k to n - 1):
     * - Add max of previous window: `arr[dq.peekFirst()]`.
     * - Remove indices from front of `dq` that fall out of current window
     * boundaries (`dq.peekFirst() <= i - k`).
     * - Remove indices from back of `dq` whose array values are <= arr[i].
     * - Add `i` to back of `dq`.
     * 4. Add max of final window `arr[dq.peekFirst()]`.
     *
     * Why this is interview-preferred:
     * - Maintains indices in decreasing order of element values, allowing O(1)
     * retrieval of window max at front (`peekFirst()`).
     * - Each index is added and removed at most once from deque -> linear time
     * O(n).
     *
     * Time Complexity : O(n) - each element index pushed and popped at most once.
     * Space Complexity : O(k) - deque stores at most k element indices.
     */
    static int[] findMaxKWindowDeque(int[] arr, int k) {
        int n = arr.length;
        if (n == 0 || k <= 0)
            return new int[0];

        int[] result = new int[n - k + 1]; // Store max of each window
        Deque<Integer> dq = new ArrayDeque<>(); // Deque stores element indices

        // Process first window (0 to k - 1)
        for (int i = 0; i < k; i++) {
            // Remove smaller elements from back of deque
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.addLast(i); // Add current index
        }

        int idx = 0; // Output index tracker

        // Process rest of the elements from index k to n - 1
        for (int i = k; i < n; i++) {
            // Maximum element for current window is at front of deque
            result[idx++] = arr[dq.peekFirst()];

            // Remove elements out of current window boundary
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // Remove smaller elements from back of deque
            while (!dq.isEmpty() && arr[i] >= arr[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.addLast(i); // Add current index
        }

        // Record maximum for last window
        result[idx] = arr[dq.peekFirst()];

        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        int k = 3;

        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Window Size k: " + k + "\n");

        System.out.println("1. Naive Approach Result    : " + Arrays.toString(findMaxKWindowNaive(arr, k)));
        System.out.println("2. Monotonic Deque Result   : " + Arrays.toString(findMaxKWindowDeque(arr, k)));
    }
}