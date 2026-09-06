/*
 * Problem Statement: Single Element in a Sorted Array
 * 
 * You are given a sorted array consisting of only integers where every element 
 * appears exactly twice, except for one element which appears exactly once.
 * Find this single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 * 
 * Example 1:
 * Input : arr[] = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6}
 * Output: 4
 * Explanation: Only the number 4 appears once in the array.
 * 
 * Example 2:
 * Input: arr[] = {3, 3, 7, 7, 10, 11, 11}
 * Output: 10
 * Explanation: Only the number 10 appears once in the array.
 */

class SingleElementSearch {

    /*
     * [Naive Approach] XOR Operation
     * We can use the XOR bitwise operator. XORing a number with itself results in 0. 
     * XORing all elements in the array will cancel out all the pairs, and the 
     * remaining value will be the single element. (Alternatively, a linear scan checking 
     * adjacent elements also works in O(N)).
     */
    private static int findSingleElementNaive(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        return xor;
    }

    /*
     * [Optimal Approach] Binary Search
     * We can use Binary Search to find the single element in O(log N) time.
     * Before the single element, pairs exist at (even, odd) indices (e.g., index 0 and 1).
     * After the single element, pairs exist at (odd, even) indices.
     * We can check the middle element's index pattern to decide whether to search left or right.
     */
    private static int findSingleElementOptimal(int[] arr) {
        int n = arr.length;

        // Edge case: only one element in the array
        if (n == 1) return arr[0];

        // Edge case: first element is the unique one
        if (arr[0] != arr[1]) return arr[0];

        // Edge case: last element is the unique one
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        // Initialize binary search bounds (exclude first and last index which are checked above)
        int low = 1, high = n - 2;

        while (low <= high) {
            // Calculate middle index
            int mid = low + (high - low) / 2;

            // Check if middle element is the unique one
            if (arr[mid] != arr[mid + 1] && arr[mid] != arr[mid - 1]) {
                return arr[mid];
            }

            // If mid is in the left half, the pairing pattern is (even, odd)
            if ((mid % 2 == 1 && arr[mid] == arr[mid - 1]) ||
                (mid % 2 == 0 && arr[mid] == arr[mid + 1])) {
                // The single element is on the right
                low = mid + 1;
            } else {
                // The single element is on the left
                high = mid - 1;
            }
        }

        return -1; // Dummy return
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        int[] arr2 = {3, 3, 7, 7, 10, 11, 11};

        System.out.println("--- Naive Approach ---");
        System.out.println("Single element in arr1: " + findSingleElementNaive(arr1));
        System.out.println("Single element in arr2: " + findSingleElementNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Single element in arr1: " + findSingleElementOptimal(arr1));
        System.out.println("Single element in arr2: " + findSingleElementOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we iterate through all elements to compute the XOR sum.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). Using binary search, the search space is divided 
 *      by 2 at each step.
 *    - Space Complexity: O(1), as this is an iterative binary search using a few pointers.
 */