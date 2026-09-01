/*
 * Problem Statement: Quick Sort
 * 
 * Implement the Quick Sort algorithm to sort an array of integers in ascending order.
 * 
 * Example:
 * Input: arr[] = {10, 7, 8, 9, 1, 5}
 * Output: {1, 5, 7, 8, 9, 10}
 * Explanation: The elements are rearranged in increasing order using the divide 
 * and conquer strategy of Quick Sort.
 */

class QuickSort {

    /*
     * Partition Method (Lomuto Partition Scheme)
     * This function takes the last element as the pivot, places the pivot element at its 
     * correct position in the sorted array, and places all smaller elements to the left 
     * of the pivot and all greater elements to the right of the pivot.
     */
    int partition(int[] arr, int low, int high) {
        // We choose the last element as the pivot
        int pivot = arr[high]; 
        
        // Index of the smaller element, indicates the right position of pivot found so far
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (the pivot element)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the partitioning index
        return i + 1;
    }

    /*
     * [Optimal Approach] Quick Sort
     * The main function that implements QuickSort.
     * arr[] --> Array to be sorted
     * low   --> Starting index
     * high  --> Ending index
     */
    void sort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is the partitioning index, arr[pi] is now at its correct place
            int pi = partition(arr, low, high);

            // Recursively sort elements before partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Original Array:");
        printArray(arr);

        QuickSort ob = new QuickSort();
        ob.sort(arr, 0, arr.length - 1);

        System.out.println("\nSorted Array:");
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
 *   - Best and Average Case: O(N log N). The pivot roughly divides the array in half 
 *     at each step.
 *   - Worst Case: O(N^2). Occurs when the array is already sorted (in ascending or 
 *     descending order) and we pick the last element as the pivot, leading to highly 
 *     unbalanced partitions.
 * 
 * - Space Complexity:
 *   - O(1) auxiliary space (in-place sorting algorithm).
 *   - O(log N) space is required for the recursive call stack in the best/average case.
 *   - O(N) space for the recursive call stack in the worst case.
 */