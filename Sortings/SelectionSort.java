// Selection Sort Algorithm

// Given an array of N integers, write a program to sort the array in ascending order
// using the Selection Sort algorithm. Selection Sort works by repeatedly finding the
// minimum element from the unsorted part of the array and swapping it with the first
// element of the unsorted part.

// Examples:

// Input: N = 6, arr[] = {13, 46, 24, 52, 20, 9}
// Output: [9, 13, 20, 24, 46, 52]
// Explanation:
// - Step 1: Minimum in arr[0...5] is 9. Swap 9 with 13 -> {9, 46, 24, 52, 20, 13}
// - Step 2: Minimum in arr[1...5] is 13. Swap 13 with 46 -> {9, 13, 24, 52, 20, 46}
// - Step 3: Minimum in arr[2...5] is 20. Swap 20 with 24 -> {9, 13, 20, 52, 24, 46}
// - Step 4: Minimum in arr[3...5] is 24. Swap 24 with 52 -> {9, 13, 20, 24, 52, 46}
// - Step 5: Minimum in arr[4...5] is 46. Swap 46 with 52 -> {9, 13, 20, 24, 46, 52}

// Input: N = 1, arr[] = {5}
// Output: [5]
// Explanation: Single element array is already sorted.

// Input: N = 0, arr[] = {}
// Output: []
// Explanation: Empty array remains empty.

import java.util.Arrays;

class SelectionSort {

    /*
     * Custom swap function to swap elements at two indices in an array.
     */
    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /*
     * [Alternative Approach] Selection Sort with Suboptimal Swapping - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Outer loop runs from i = 0 to n - 2.
     *   2. Track the minimum value: `minVal = arr[i]`.
     *   3. Inner loop runs from j = i to n - 1:
     *        - If `arr[j] < minVal`, update `minVal = arr[j]` and immediately swap `arr[i]` with `arr[j]`.
     *
     * Drawback:
     *   - Performs multiple swaps inside the inner loop whenever a smaller element is encountered.
     *   - This increases write operations (swaps) to the array unnecessarily, which is suboptimal.
     *
     * Time Complexity  : O(n^2) - in all cases (best, worst, average) because of nested loops.
     * Space Complexity : O(1)   - in-place sorting.
     */
    static void selectionSortSuboptimal(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return;
        }

        // Loop to control sorted boundary
        for (int i = 0; i <= n - 2; i++) {
            int minVal = arr[i];
            
            // Loop through unsorted part
            for (int j = i; j <= n - 1; j++) {
                if (arr[j] < minVal) {
                    minVal = arr[j];
                    // Suboptimal: Swap immediately whenever a smaller element is found
                    swap(arr, i, j);
                }
            }
        }
    }

    /*
     * [Optimal/Interview Approach] Standard Selection Sort - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Outer loop runs from i = 0 to n - 2 (representing the boundary of the sorted portion).
     *   2. Assume the current position `i` holds the minimum element: `minIndex = i`.
     *   3. Inner loop scans the unsorted portion from j = i + 1 to n - 1:
     *        - If `arr[j] < arr[minIndex]`, update `minIndex = j`.
     *   4. After scanning the entire unsorted subarray, swap `arr[i]` with the absolute minimum found `arr[minIndex]`
     *      only if a smaller element was found (`minIndex != i`).
     *
     * Why this is interview-preferred:
     *   - Performs at most ONE swap per outer loop iteration.
     *   - Minimizes array write operations (great for hardware where memory writes are expensive).
     *
     * Time Complexity  : O(n^2) - best, worst, and average time complexities are all O(n^2)
     *                            since we must scan the unsorted part completely in each step.
     * Space Complexity : O(1)   - auxiliary space, sorts in-place.
     */
    static void selectionSortOptimal(int[] arr, int n) {
        if (arr == null || n <= 1) {
            return; // Edge Case: empty or single-element array
        }

        // Move the boundary of the unsorted subarray
        for (int i = 0; i <= n - 2; i++) {
            int minIndex = i; // Store the index of the minimum element

            // Scan the unsorted subarray to find the index of the minimum element
            for (int j = i + 1; j <= n - 1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // Update the index of the minimum element
                }
            }

            // Swap the found minimum element with the first element of the unsorted subarray
            // only if the minimum element is not already at index i
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {13, 46, 24, 52, 20, 9};
        int[] arr2 = {13, 32, 32, 4, 23, 42};

        System.out.println("Testing SelectionSort Suboptimal:");
        int[] testSuboptimal1 = arr1.clone();
        selectionSortSuboptimal(testSuboptimal1, testSuboptimal1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testSuboptimal1));

        int[] testSuboptimal2 = arr2.clone();
        selectionSortSuboptimal(testSuboptimal2, testSuboptimal2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testSuboptimal2));

        System.out.println("\nTesting SelectionSort Optimal:");
        int[] testOptimal1 = arr1.clone();
        selectionSortOptimal(testOptimal1, testOptimal1.length);
        System.out.println("Sorted Array 1: " + Arrays.toString(testOptimal1));

        int[] testOptimal2 = arr2.clone();
        selectionSortOptimal(testOptimal2, testOptimal2.length);
        System.out.println("Sorted Array 2: " + Arrays.toString(testOptimal2));
    }
}
