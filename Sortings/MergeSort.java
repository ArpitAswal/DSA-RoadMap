/*
 * Problem Statement: Merge Sort
 * 
 * Implement the Merge Sort algorithm to sort an array of integers in ascending order.
 * 
 * Example:
 * Input: arr[] = {5, 2, 8, 4, 1}
 * Output: {1, 2, 4, 5, 8}
 * Explanation: The elements are rearranged in increasing order using the divide 
 * and conquer strategy of Merge Sort.
 */

class MergeSort {
    
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 4, 1};
        
        System.out.println("Original Array:");
        printArray(arr);

        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("Sorted Array:");
        printArray(arr);
    }

    /*
     * [Optimal Approach] Merge Sort (Divide and Conquer)
     * Divides the array into two halves, recursively sorts them, and then merges
     * the two sorted halves.
     */
    private static void mergeSort(int[] arr, int low, int high) {
        // Base condition: If the array has 1 or 0 elements, it is already sorted
        if (low >= high) {
            return;
        }

        // Find the middle point to divide the array into two halves
        int mid = low + (high - low) / 2; // Prevents integer overflow

        // Recursively sort the first half
        mergeSort(arr, low, mid);
        
        // Recursively sort the second half
        mergeSort(arr, mid + 1, high);
        
        // Merge the two sorted halves
        mergeArray(arr, low, mid, high);
    }

    /*
     * Merges two subarrays of arr[].
     * First subarray is arr[low..mid]
     * Second subarray is arr[mid+1..high]
     */
    private static void mergeArray(int[] arr, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;

        // Temporary array for merging
        int[] temp = new int[high - low + 1];
        int k = 0;

        // Traverse both arrays and copy the smaller element to temp
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
            }
        }

        // Copy remaining elements of the left half, if any
        while (left <= mid) {
            temp[k++] = arr[left++];
        }

        // Copy remaining elements of the right half, if any
        while (right <= high) {
            temp[k++] = arr[right++];
        }

        // Copy the merged elements back into the original array
        for (int i = low; i <= high; i++) {
            arr[i] = temp[i - low];
        }
    }

    // Helper method to print the array without using Arrays.toString()
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
 * - Time Complexity: O(N log N). The array is divided into two halves at each step 
 *   (log N steps), and merging the two halves takes O(N) time at each step. This gives 
 *   an overall time complexity of O(N log N) for best, average, and worst cases.
 * 
 * - Space Complexity: O(N), as we require a temporary array 'temp' of size N to merge 
 *   the elements. The recursive call stack uses O(log N) space.
 */
