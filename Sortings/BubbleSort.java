// Bubble Sort Algorithm

// Given an array of N integers, write a program to sort the array in ascending order
// using the Bubble Sort algorithm. Bubble Sort works by repeatedly swapping adjacent elements
// if they are in the wrong order. This process is repeated until no more swaps are needed.

// Examples:

// Input: N = 6, arr[] = {13, 46, 24, 52, 20, 9}
// Output: [9, 13, 20, 24, 46, 52]
// Explanation:
// - Pass 1: Adjacent elements are compared and swapped if arr[j] > arr[j+1].
//           Largest element 52 bubbles to the end -> {13, 24, 46, 20, 9, 52}
// - Pass 2: Next largest element 46 bubbles to second last -> {13, 24, 20, 9, 46, 52}
// - Pass 3: Next largest 24 bubbles -> {13, 20, 9, 24, 46, 52}
// - Pass 4: Next largest 20 bubbles -> {13, 9, 20, 24, 46, 52}
// - Pass 5: Final bubble -> {9, 13, 20, 24, 46, 52}

// Input: N = 1, arr[] = {5}
// Output: [5]
// Explanation: Already sorted.

// Input: N = 0, arr[] = {}
// Output: []
// Explanation: Empty array remains empty.

import java.util.Arrays;

class BubbleSort {

    /*
     * Custom swap function to swap elements at two indices in an array.
     */
    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /*
     * [Alternative Approach] Naive Bubble Sort - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Outer loop runs from i = n - 1 down to 0.
     *   2. Inner loop runs from j = 0 to i - 1:
     *        - If `arr[j] > arr[j + 1]`, swap them.
     *
     * Drawback:
     *   - Runs the nested loop unconditionally. Even if the array is already sorted,
     *     it takes O(n^2) operations.
     *
     * Time Complexity  : O(n^2) - in all cases (best, worst, average) without early termination.
     * Space Complexity : O(1)   - in-place sorting.
     */
    static void bubbleSortNaive(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return;
        }

        // Loop to control unsorted subarray boundary
        for (int i = n - 1; i >= 0; i--) {
            // Compare adjacent elements
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1); // Swap if wrong order
                }
            }
        }
    }

    /*
     * [Optimal/Interview Approach] Optimized Bubble Sort with Swapped Flag - O(n^2) Worst/Average Time, O(n) Best Time
     *
     * Logic / Steps:
     *   1. Outer loop runs from i = n - 1 down to 0.
     *   2. Initialize `swapped = false` at the start of each outer pass.
     *   3. Inner loop runs from j = 0 to i - 1:
     *        - If `arr[j] > arr[j + 1]`, swap them and set `swapped = true`.
     *   4. After completing the inner pass, check the flag:
     *        - If `swapped == false`, it means no elements were swapped during this pass.
     *          Therefore, the array is already fully sorted. Break early to save computations!
     *
     * Why this is interview-preferred:
     *   - Improves the best-case time complexity to linear O(n) for already-sorted inputs.
     *   - Standard interview-expected optimization of Bubble Sort.
     *
     * Time Complexity  :
     *   - Worst Case   : O(n^2) - array is reverse sorted.
     *   - Average Case : O(n^2) - random order of elements.
     *   - Best Case    : O(n)   - array is already sorted (outer loop runs only once).
     * Space Complexity : O(1)   - auxiliary space, sorts in-place.
     */
    static void bubbleSortOptimal(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return; // Edge Case: empty or single-element array
        }

        // Loop to control sorted boundary
        for (int i = n - 1; i >= 0; i--) {
            boolean swapped = false; // Flag to track swaps in the current pass

            // Compare adjacent elements in the unsorted portion
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true; // Mark that a swap occurred
                }
            }

            // If no swaps occurred, the array is already sorted
            if (!swapped) {
                break; // Break the outer loop early
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {13, 46, 24, 52, 20, 9};
        int[] arr2 = {1, 2, 3, 4, 5, 6}; // Already sorted array for testing best-case

        System.out.println("Testing BubbleSort Naive:");
        int[] testNaive1 = arr1.clone();
        bubbleSortNaive(testNaive1, testNaive1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testNaive1));

        int[] testNaive2 = arr2.clone();
        bubbleSortNaive(testNaive2, testNaive2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testNaive2));

        System.out.println("\nTesting BubbleSort Optimal:");
        int[] testOptimal1 = arr1.clone();
        bubbleSortOptimal(testOptimal1, testOptimal1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testOptimal1));

        int[] testOptimal2 = arr2.clone();
        bubbleSortOptimal(testOptimal2, testOptimal2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testOptimal2));
    }
}
