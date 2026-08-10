// Smallest Missing Positive Number

// Given an unsorted integer array arr[], find the smallest missing positive integer.
// Smallest positive integer means integers starting from 1 (i.e. 1, 2, 3, ...).

// Examples:

// Input: arr[] = [2, -3, 4, 1, 1, 7]
// Output: 3
// Explanation: Positive numbers present are 1, 2, 4, 7. The smallest missing positive integer is 3.

// Input: arr[] = [3, 4, -1, 1]
// Output: 2
// Explanation: Positive numbers present are 1, 3, 4. The smallest missing positive integer is 2.

// Input: arr[] = [7, 8, 9, 11, 12]
// Output: 1
// Explanation: 1 is missing, so output is 1.

import java.util.Arrays;
import java.util.HashSet;

class SmallestMissingPositive {

    /*
     * [Naive Approach] Sorting - O(n log n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Clone and sort the array in ascending order.
     *   2. Maintain target positive integer `target = 1`.
     *   3. Iterate through sorted array:
     *        - If element == target: increment target++.
     *        - If element > target: target remains unchanged.
     *   4. Return target.
     *
     * Time Complexity  : O(n log n) - due to array sorting step.
     * Space Complexity : O(1)       - in-place search after sort.
     */
    static int findSmallestMissingNaive(int[] arr) {
        int[] sortedArr = arr.clone(); // Clone array to preserve original
        Arrays.sort(sortedArr);         // Sort array ascending

        int target = 1; // First expected positive integer

        // Traverse sorted array
        for (int i = 0; i < sortedArr.length; i++) {
            // If current number matches expected target positive, increment target
            if (sortedArr[i] == target) {
                target++;
            }
        }

        return target;
    }

    /*
     * [Better Approach] Using HashSet - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Add all positive elements of array into a HashSet.
     *   2. Loop integer `i` starting from 1 up to n + 1:
     *        - If HashSet does not contain `i`, then `i` is the smallest missing positive! Return `i`.
     *
     * Time Complexity  : O(n) - single pass to populate set + single loop up to n + 1.
     * Space Complexity : O(n) - HashSet stores up to n positive numbers.
     */
    static int findSmallestMissingHashSet(int[] arr) {
        HashSet<Integer> set = new HashSet<>(); // HashSet for positive numbers

        // Insert positive integers into set
        for (int num : arr) {
            if (num > 0) {
                set.add(num);
            }
        }

        // Search for first missing positive starting from 1
        for (int i = 1; i <= arr.length + 1; i++) {
            if (!set.contains(i)) {
                return i; // Found smallest missing positive
            }
        }

        return 1;
    }

    /*
     * [Optimal / Interview Approach] Cycle Sort / Index Placement - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. The smallest missing positive must lie in range [1, n + 1].
     *   2. Place each positive number `val` (where 1 <= val <= n) at its correct index `val - 1` using swapping (Cycle Sort logic):
     *        while (arr[i] > 0 && arr[i] <= n && arr[i] != arr[arr[i] - 1]) {
     *            swap arr[i] with arr[arr[i] - 1]
     *        }
     *   3. Pass 2: Iterate array from 0 to n - 1:
     *        - If arr[i] != i + 1: return i + 1 (first index violating element placement).
     *   4. If all indices 0 to n - 1 match i + 1, return n + 1.
     *
     * Why this is interview-preferred:
     *   - Runs in linear time O(n) with strictly O(1) extra space.
     *   - Re-uses array indices as hash slots without extra allocation.
     *
     * Time Complexity  : O(n) - each element is placed in its correct position in at most n swaps overall.
     * Space Complexity : O(1) - modifies array in-place without extra storage.
     */
    static int findSmallestMissingInPlace(int[] arr) {
        int n = arr.length; // Length of input array

        // Step 1: Cycle Sort - place element x at index x - 1 if 1 <= x <= n
        for (int i = 0; i < n; i++) {
            // While current element is in range [1, n] and not at its correct position arr[i] - 1
            while (arr[i] > 0 && arr[i] <= n && arr[i] != arr[arr[i] - 1]) {
                // Swap arr[i] with element at its target index arr[i] - 1
                int temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
            }
        }

        // Step 2: First index where arr[i] != i + 1 is the missing positive number
        for (int i = 0; i < n; i++) {
            if (arr[i] != i + 1) {
                return i + 1; // Return smallest missing positive
            }
        }

        // Step 3: If all positions 1 to n are filled correctly, answer is n + 1
        return n + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 2, -3, 4, 1, 1, 7 };

        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("1. Naive (Sorting) Result    : " + findSmallestMissingNaive(arr));
        System.out.println("2. HashSet Result            : " + findSmallestMissingHashSet(arr));

        int[] arrCopy = arr.clone();
        System.out.println("3. In-Place Cycle Sort Result: " + findSmallestMissingInPlace(arrCopy));

        System.out.println("\n--- Additional Tests ---");
        int[][] testCases = {
            { 1, 2, 0 },
            { 3, 4, -1, 1 },
            { 7, 8, 9, 11, 12 },
            { 1, 2, 3, 4 }
        };

        for (int[] test : testCases) {
            int[] testCloned = test.clone();
            System.out.println("Array: " + Arrays.toString(test) + 
                               " -> Smallest Missing Positive: " + findSmallestMissingInPlace(testCloned));
        }
    }
}