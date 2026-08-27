// Insertion Sort Algorithm

// Given an array of N integers, write a program to sort the array in ascending order
// using the Insertion Sort algorithm. Insertion Sort builds the final sorted array
// one element at a time by repeatedly taking the next unsorted element and inserting
// it into its correct position relative to the already sorted portion.

// Examples:

// Input: N = 6, arr[] = {13, 46, 24, 52, 20, 9}
// Output: [9, 13, 20, 24, 46, 52]
// Explanation:
// - Step 1: 13 is sorted.
// - Step 2: 46 is inserted. Since 46 > 13, position is unchanged -> {13, 46, 24, 52, 20, 9}
// - Step 3: 24 is inserted. 46 is shifted right. 24 is placed at index 1 -> {13, 24, 46, 52, 20, 9}
// - Step 4: 52 is inserted. Since 52 > 46, position is unchanged -> {13, 24, 46, 52, 20, 9}
// - Step 5: 20 is inserted. 52, 46, 24 are shifted. 20 is placed at index 1 -> {13, 20, 24, 46, 52, 9}
// - Step 6: 9 is inserted. All elements shift right. 9 is placed at index 0 -> {9, 13, 20, 24, 46, 52}

// Input: N = 1, arr[] = {5}
// Output: [5]
// Explanation: Single element array is already sorted.

// Input: N = 0, arr[] = {}
// Output: []
// Explanation: Empty array remains empty.

import java.util.Arrays;

class InsertionSort {

    /*
     * Custom swap function to swap elements at two indices in an array.
     */
    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /*
     * [Alternative Approach] Insertion Sort with Repeated Swapping - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Loop from i = 0 to n - 1.
     *   2. Loop from j = i down to 1:
     *        - If `arr[j] < arr[j - 1]`, swap them.
     *        - Otherwise, the element is in its correct place relative to sorted section, so break.
     *
     * Drawback:
     *   - Uses repeated swaps to shift elements backwards.
     *   - Each swap performs 3 write operations, which is slower than shifts.
     *
     * Time Complexity  : O(n^2) - worst/average cases. O(n) best-case (when already sorted).
     * Space Complexity : O(1)   - in-place sorting.
     */
    static void insertionSortNaive(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return;
        }

        // Loop through all elements
        for (int i = 0; i <= n - 1; i++) {
            // Shift backwards using swaps
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else {
                    break; // Correct position found, stop swapping
                }
            }
        }
    }

    /*
     * [Optimal/Interview Approach] Insertion Sort with Shifting - O(n^2) Worst/Average Time, O(n) Best Time
     *
     * Logic / Steps:
     *   1. Loop i from 1 to n - 1 (since the first element at index 0 is already sorted).
     *   2. Store the current element in a variable: `key = arr[i]`.
     *   3. Initialize `j = i - 1`.
     *   4. While `j >= 0` AND `arr[j] > key`:
     *        a. Shift `arr[j]` one position to the right: `arr[j + 1] = arr[j]`.
     *        b. Decrement `j` to check the previous element.
     *   5. Insert the stored `key` into its correct position: `arr[j + 1] = key`.
     *
     * Why this is interview-preferred:
     *   - Replaces expensive swaps with simple shifts. Shifting takes only 1 write operation per iteration
     *     instead of 3 writes for a swap, making it significantly faster in practice.
     *
     * Time Complexity  :
     *   - Worst Case   : O(n^2) - array is reverse sorted (requires shifting all sorted elements in each step).
     *   - Average Case : O(n^2) - random order of elements.
     *   - Best Case    : O(n)   - array is already sorted (inner loop conditions fail instantly).
     * Space Complexity : O(1)   - auxiliary space, sorts in-place.
     */
    static void insertionSortOptimal(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return; // Edge Case: empty or single-element array
        }

        // Loop from the second element up to the last element
        for (int i = 1; i <= n - 1; i++) {
            int key = arr[i]; // Element to be inserted into the sorted subarray
            int j = i - 1;

            // Shift elements of the sorted subarray that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift element right
                j--;
            }

            // Place key in its correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {13, 46, 24, 52, 20, 9};
        int[] arr2 = {1, 2, 3, 4, 5, 6}; // Already sorted array

        System.out.println("Testing InsertionSort Naive:");
        int[] testNaive1 = arr1.clone();
        insertionSortNaive(testNaive1, testNaive1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testNaive1));

        int[] testNaive2 = arr2.clone();
        insertionSortNaive(testNaive2, testNaive2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testNaive2));

        System.out.println("\nTesting InsertionSort Optimal:");
        int[] testOptimal1 = arr1.clone();
        insertionSortOptimal(testOptimal1, testOptimal1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testOptimal1));

        int[] testOptimal2 = arr2.clone();
        insertionSortOptimal(testOptimal2, testOptimal2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testOptimal2));
    }
}
