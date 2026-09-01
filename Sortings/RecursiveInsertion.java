/*
 * Problem Statement: Recursive Insertion Sort
 * 
 * Implement the Insertion Sort algorithm recursively to sort an array of integers 
 * in ascending order.
 * 
 * Example:
 * Input: arr[] = {13, 46, 24, 52, 20, 9}
 * Output: {9, 13, 20, 24, 46, 52}
 * Explanation: The elements are rearranged in increasing order.
 */

class RecursiveInsertion {

    /*
     * [Optimal Approach] Recursive Insertion Sort (Using Shifting instead of Swapping)
     * In each recursive call, the 'insert' index represents the current element being 
     * inserted into its correct position in the already sorted left portion of the array.
     */
    private static void insertionSort(int[] arr, int insert, int n) {
        // Base case: If we have reached the end of the array, return
        if (n <= 1 || insert == n) {
            return;
        }

        // Store the value to be inserted
        int key = arr[insert];
        int j = insert - 1;

        // Shift elements of the sorted portion that are greater than the key 
        // to one position ahead of their current position
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }

        // Place the key at its correct position
        arr[j + 1] = key;

        // Recursively sort the remaining array
        insertionSort(arr, insert + 1, n);
    }

    public static void main(String[] args) {
        int[] arr = { 13, 46, 24, 52, 20, 9 };
        int n = arr.length;

        System.out.println("Before Using Insertion Sort:");
        printArray(arr);

        // Start with inserting the element at index 1
        insertionSort(arr, 1, n);

        System.out.println("\nAfter Using Insertion Sort:");
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
 *     processed N times and placing an element in the sorted portion takes O(N) shifts.
 *   - Best Case: O(N), which happens when the array is already sorted. The inner while 
 *     loop doesn't execute.
 * 
 * - Space Complexity:
 *   - O(N) auxiliary stack space, due to the recursive call stack. In an iterative 
 *     Insertion Sort, this would be O(1).
 */
