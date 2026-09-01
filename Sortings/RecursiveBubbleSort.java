/*
 * Problem Statement: Recursive Bubble Sort
 * 
 * Implement the Bubble Sort algorithm recursively to sort an array of integers 
 * in ascending order.
 * 
 * Example:
 * Input: arr[] = {13, 46, 24, 52, 20, 9}
 * Output: {9, 13, 20, 24, 46, 52}
 * Explanation: The elements are rearranged in increasing order.
 */

class RecursiveBubbleSort {

    /*
     * [Optimal Approach] Recursive Bubble Sort with early termination
     * In each recursive call, the largest element of the unsorted portion bubbles 
     * up to its correct position. The parameter 'n' denotes the size of the array 
     * that remains to be sorted.
     */
    private static void bubbleSort(int[] arr, int n) {
        // Base case: If array size is 1, it's already sorted
        if (n == 1) {
            return;
        }

        boolean didswap = false;

        // One pass of bubble sort. The largest element will bubble to the end.
        for (int i = 0; i <= n - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                // Swap if the current element is greater than the next element
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                didswap = true;
            }
        }

        // Optimization: If no swaps occurred, the array is already sorted
        if (!didswap) {
            return;
        }

        // Recursively sort the remaining array of size n-1
        bubbleSort(arr, n - 1);
    }

    public static void main(String[] args) {
        int[] arr = { 13, 46, 24, 52, 20, 9 };
        int n = arr.length;

        System.out.println("Before Using Bubble Sort:");
        printArray(arr);

        bubbleSort(arr, n);

        System.out.println("\nAfter Using Bubble Sort:");
        printArray(arr);
    }
    
    // Helper function to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

/*
 * Complexity Analysis:
 * 
 * - Time Complexity:
 *   - Worst and Average Case: O(N^2), where N is the size of the array. The array is 
 *     processed N times and each pass takes O(N) comparisons.
 *   - Best Case: O(N), which happens when the array is already sorted. The 'didswap' 
 *     check ensures early termination.
 * 
 * - Space Complexity:
 *   - O(N) auxiliary stack space, due to the recursive call stack. In an iterative 
 *     Bubble Sort, this would be O(1).
 */
