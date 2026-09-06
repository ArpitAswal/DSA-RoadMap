/*
 * Problem Statement: Find Minimum in Rotated Sorted Array
 * 
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 * 
 * Example 1:
 * Input: arr = [4, 5, 6, 7, 0, 1, 2, 3]
 * Output: 0
 * Explanation: The original array was [0, 1, 2, 3, 4, 5, 6, 7] and it was rotated 4 times.
 * The minimum element is 0.
 * 
 * Example 2:
 * Input: arr = [11, 13, 15, 17]
 * Output: 11
 * Explanation: The original array was [11, 13, 15, 17] and it was rotated 4 times 
 * (which is equivalent to 0 times).
 */

class MinimumRotatedElement {

    /*
     * [Naive Approach] Linear Search
     * Iterate through the array and keep track of the minimum element found so far.
     */
    private static int findMinNaive(int[] nums) {
        int minValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
            }
        }
        return minValue;
    }

    /*
     * [Optimal Approach] Binary Search
     * We can find the minimum element in O(log N) time using binary search.
     * The array is divided into two sorted halves. We check which half is sorted 
     * and narrow our search space to the unsorted half (since the minimum element 
     * must lie in the unsorted half).
     */
    private static int findMinOptimal(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // If the array is not rotated at all, the first element is the minimum
        if (nums[low] <= nums[high]) {
            return nums[low];
        }

        // Binary search loop
        while (low < high) {
            // Calculate mid index
            int mid = low + (high - low) / 2;

            // Check which half to discard
            if (nums[mid] > nums[high]) {
                // Minimum lies in the right half
                low = mid + 1;
            } else {
                // Minimum lies in the left half (including mid)
                high = mid;
            }
        }

        // Return the minimum element
        return nums[low];
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2, 3};
        int[] arr2 = {11, 13, 15, 17};

        System.out.println("--- Naive Approach ---");
        System.out.println("Minimum element (arr1): " + findMinNaive(arr1));
        System.out.println("Minimum element (arr2): " + findMinNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Minimum element (arr1): " + findMinOptimal(arr1));
        System.out.println("Minimum element (arr2): " + findMinOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we iterate through the entire array.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). At each step, the search space is reduced by half.
 *    - Space Complexity: O(1), as this is an iterative binary search using only 
 *      a few pointers.
 */