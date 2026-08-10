// Next Greater Element

// Given an array arr[] of size n, find the Next Greater Element (NGE) for every element.
// The Next Greater Element for an element x is the first greater element on the right side of x in the array.
// If no greater element exists on the right side, the next greater element for x is -1.

// Examples:

// Input: arr[] = [4, 7, 3, 15]
// Output: [7, 15, 15, -1]
// Explanation:
//   - For 4, next greater element on right is 7.
//   - For 7, next greater element on right is 15.
//   - For 3, next greater element on right is 15.
//   - For 15, no element on right is greater, so -1.

// Input: arr[] = [1, 3, 2, 4]
// Output: [3, 4, 4, -1]
// Explanation:
//   - For 1 -> 3
//   - For 3 -> 4
//   - For 2 -> 4
//   - For 4 -> -1

import java.util.Arrays;
import java.util.Stack;

class NextElementGreater {

    /*
     * [Naive Approach] Brute Force - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Create an array `result` of size n initialized with -1.
     *   2. Outer loop 'i' iterates through elements from 0 to n - 1.
     *   3. Inner loop 'j' iterates through elements to the right of i (from i + 1 to n - 1).
     *   4. As soon as arr[j] > arr[i] is found:
     *        - Set result[i] = arr[j] and break inner loop.
     *   5. Return result.
     *
     * Time Complexity  : O(n^2) - nested loop scans right side elements per index.
     * Space Complexity : O(1)   - auxiliary space (excluding output array).
     */
    static int[] findNextGreaterNaive(int[] arr) {
        int n = arr.length;               // Size of input array
        int[] result = new int[n];        // Output array
        Arrays.fill(result, -1);          // Fill default -1

        // Outer loop inspects each element arr[i]
        for (int i = 0; i < n; i++) {
            // Inner loop scans rightwards from i + 1
            for (int j = i + 1; j < n; j++) {
                // First right element strictly greater than arr[i]
                if (arr[j] > arr[i]) {
                    result[i] = arr[j]; // Set Next Greater Element
                    break;              // Stop inner loop early
                }
            }
        }

        return result;
    }

    /*
     * [Optimal / Interview Approach] Monotonic Stack - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Create a Stack of Integers to store array indices whose next greater element is not yet found.
     *   2. Create a `result` array of size n filled with -1.
     *   3. Traverse array from left to right (index 0 to n - 1):
     *        - While stack is NOT empty AND arr[i] > arr[stack.peek()]:
     *            - Pop index from stack: `index = stack.pop()`.
     *            - Set `result[index] = arr[i]` (since arr[i] is the first element to the right greater than arr[index]).
     *        - Push current index `i` onto stack.
     *   4. Return result.
     *
     * Why this is interview-preferred:
     *   - Each index is pushed onto stack once and popped at most once -> linear time O(n).
     *   - Monotonic decreasing stack structure elegantly resolves rightward greater element queries.
     *
     * Time Complexity  : O(n) - each element pushed and popped at most once.
     * Space Complexity : O(n) - stack stores at most n indices.
     */
    static int[] findNextGreaterMonotonicStack(int[] arr) {
        int n = arr.length;               // Array length
        int[] result = new int[n];        // Result array
        Arrays.fill(result, -1);          // Fill default with -1

        Stack<Integer> stack = new Stack<>(); // Stack stores element indices

        // Traverse array from left to right
        for (int i = 0; i < n; i++) {
            // While stack has elements and current element is greater than element at top index of stack
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int index = stack.pop(); // Pop top index
                result[index] = arr[i];  // Current element arr[i] is next greater element for index
            }

            stack.push(i); // Push current index onto stack to find its next greater element later
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = { 4, 7, 3, 15 };

        System.out.println("Input Array: " + Arrays.toString(arr) + "\n");
        System.out.println("1. Naive Approach Result    : " + Arrays.toString(findNextGreaterNaive(arr)));
        System.out.println("2. Monotonic Stack Result   : " + Arrays.toString(findNextGreaterMonotonicStack(arr)));

        System.out.println("\n--- Additional Tests ---");
        int[][] testCases = {
            { 1, 3, 2, 4 },
            { 6, 8, 0, 1, 3 },
            { 5, 4, 3, 2, 1 }
        };

        for (int[] test : testCases) {
            System.out.println("Array: " + Arrays.toString(test) + 
                               " -> Next Greater: " + Arrays.toString(findNextGreaterMonotonicStack(test)));
        }
    }
}