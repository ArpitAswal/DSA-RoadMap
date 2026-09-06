/*
 * Problem Statement: Find First and Last Position of Element in Sorted Array
 * 
 * Given an array of integers sorted in non-decreasing order, find the starting 
 * and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * 
 * Example 1:
 * Input: N = 7, target = 13, array[] = {3, 4, 13, 13, 13, 20, 40}  
 * Output: First: 2, Last: 4
 * Explanation: The target value 13 appears for the first time at index 2 and 
 * for the last time at index 4.
 * 
 * Example 2:
 * Input: N = 6, target = 6, array[] = {5, 7, 7, 8, 8, 10}
 * Output: First: -1, Last: -1
 * Explanation: The target value 6 does not exist in the array.
 */

class FirstLastOccurrence {

    /*
     * [Naive Approach] Linear Search
     * We can simply traverse the array from left to right.
     * The first time we see the target, we record it as the first occurrence.
     * We continue to update the last occurrence as long as we see the target.
     */
    private static int[] findOccurrencesNaive(int[] arr, int target) {
        int first = -1;
        int last = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                if (first == -1) {
                    first = i; // Record the first occurrence
                }
                last = i;      // Keep updating the last occurrence
            }
        }
        return new int[]{first, last};
    }

    /*
     * [Optimal Approach] Binary Search
     * We can find the exact boundaries of the target element using Binary Search.
     * We write a helper method to find the boundary depending on a boolean flag.
     * 1. Find the first occurrence.
     * 2. Find the last occurrence.
     */
    private static int[] findOccurrencesOptimal(int[] arr, int target) {
        int first = findBound(arr, target, true);
        
        // If the element is not found, we don't need to search for the last occurrence
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        int last = findBound(arr, target, false);
        return new int[]{first, last};
    }

    // Helper method to find the first or last boundary using Binary Search
    private static int findBound(int[] arr, int target, boolean isFirst) {
        int low = 0;
        int high = arr.length - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (arr[mid] == target) {
                res = mid; // Record candidate index
                
                if (isFirst) {
                    high = mid - 1; // Look on the left side for an earlier occurrence
                } else {
                    low = mid + 1;  // Look on the right side for a later occurrence
                }
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 4, 13, 13, 13, 20, 40};
        int target1 = 13;

        int[] arr2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;

        System.out.println("--- Naive Approach ---");
        int[] res1Naive = findOccurrencesNaive(arr1, target1);
        int[] res2Naive = findOccurrencesNaive(arr2, target2);
        System.out.println("Target " + target1 + " in arr1 -> First: " + res1Naive[0] + ", Last: " + res1Naive[1]);
        System.out.println("Target " + target2 + " in arr2 -> First: " + res2Naive[0] + ", Last: " + res2Naive[1]);

        System.out.println("\n--- Optimal Approach ---");
        int[] res1Optimal = findOccurrencesOptimal(arr1, target1);
        int[] res2Optimal = findOccurrencesOptimal(arr2, target2);
        System.out.println("Target " + target1 + " in arr1 -> First: " + res1Optimal[0] + ", Last: " + res1Optimal[1]);
        System.out.println("Target " + target2 + " in arr2 -> First: " + res2Optimal[0] + ", Last: " + res2Optimal[1]);
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we traverse the entire array once.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). Finding the first occurrence takes O(log N) and 
 *      finding the last occurrence takes O(log N). Total time is strictly O(log N).
 *    - Space Complexity: O(1), as we only use a few integer variables in the iterative binary search.
 */