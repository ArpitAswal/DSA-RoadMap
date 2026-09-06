/*
 * Problem Statement: Binary Search Algorithm
 * 
 * Given an array of integers nums which is sorted in ascending order, and an integer target, 
 * write a function to search target in nums. If target exists, then return its index. 
 * Otherwise, return -1.
 * 
 * Example 1:
 * Input: nums = [-1, 0, 3, 5, 9, 12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4.
 * 
 * Example 2:
 * Input: nums = [-1, 0, 3, 5, 9, 12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1.
 */

class BinarySearchAlgo {

    /*
     * [Naive Approach] Linear Search
     * Iterate through each element of the array. If the current element matches the target,
     * return its index. If we reach the end of the array without finding it, return -1.
     */
    public static int linearSearchNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /*
     * [Optimal Approach] Iterative Binary Search
     * Since the array is sorted, we can divide the search space in half at each step.
     * We keep track of a 'low' and 'high' pointer. If the middle element is the target, 
     * return its index. If it's less than the target, we search the right half. Otherwise, 
     * we search the left half.
     */
    public static int binarySearchOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            // Prevents integer overflow compared to (low + high) / 2
            int mid = low + (high - low) / 2; 

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                low = mid + 1; // Target is greater, so search the right half
            } else {
                high = mid - 1; // Target is smaller, so search the left half
            }
        }
        return -1; // Target not found
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int target2 = 2;

        System.out.println("--- Naive Approach (Linear Search) ---");
        System.out.println("Index of " + target1 + ": " + linearSearchNaive(arr, target1)); 
        System.out.println("Index of " + target2 + ": " + linearSearchNaive(arr, target2)); 

        System.out.println("\n--- Optimal Approach (Binary Search) ---");
        System.out.println("Index of " + target1 + ": " + binarySearchOptimal(arr, target1)); 
        System.out.println("Index of " + target2 + ": " + binarySearchOptimal(arr, target2)); 
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach (Linear Search):
 *    - Time Complexity: O(N), where N is the size of the array. In the worst case, 
 *      we might have to check every single element.
 *    - Space Complexity: O(1), as no extra space is used.
 * 
 * 2. Optimal Approach (Binary Search):
 *    - Time Complexity: O(log N). At each step, the search space is divided by 2.
 *    - Space Complexity: O(1), as this is an iterative implementation using only a 
 *      few pointers.
 */
