/*
 * Problem Statement: Find out how many times the array has been rotated
 * 
 * Given an integer array nums of unique elements that is sorted in ascending order 
 * and then rotated an unknown number of times. Find out how many times the array 
 * has been rotated.
 * 
 * Example 1:
 * Input : arr = [4, 5, 6, 7, 0, 1, 2, 3]
 * Output: 4
 * Explanation: The original array should be [0, 1, 2, 3, 4, 5, 6, 7]. We can notice 
 * that the array has been rotated 4 times to the right. The index of the minimum 
 * element (0) is 4, which corresponds to the number of rotations.
 * 
 * Example 2:
 * Input : arr = [1, 2, 3, 4, 5]
 * Output: 0
 * Explanation: The array is not rotated, so the minimum element is at index 0.
 */

class NRotateArray {

    /*
     * [Naive Approach] Linear Search
     * The number of times the array is rotated is equal to the index of the minimum 
     * element in the array. We can linearly search for the minimum element and return 
     * its index.
     */
    private static int findRotationCountNaive(int[] nums) {
        int minIndex = 0;
        int minValue = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minValue) {
                minValue = nums[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /*
     * [Optimal Approach] Binary Search
     * We use binary search to find the index of the minimum element in O(log N) time.
     * The array is divided into two sorted halves. The minimum element always lies 
     * in the unsorted half.
     */
    private static int findRotationCountOptimal(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // If the array is not rotated at all
        if (nums[low] <= nums[high]) {
            return low;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than or equal to high element,
            // it means the left half [low..mid] is sorted, so the minimum 
            // element must be in the right half [mid+1..high]
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } 
            // Else the right half is sorted, the minimum element could be 
            // mid itself or in the left half [low..mid]
            else {
                high = mid;
            }
        }
        
        // At the end of the loop, low == high, pointing to the minimum element
        return low;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2, 3};
        int[] arr2 = {1, 2, 3, 4, 5};

        System.out.println("--- Naive Approach ---");
        System.out.println("Rotation count (arr1): " + findRotationCountNaive(arr1));
        System.out.println("Rotation count (arr2): " + findRotationCountNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Rotation count (arr1): " + findRotationCountOptimal(arr1));
        System.out.println("Rotation count (arr2): " + findRotationCountOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we perform a linear scan of the array.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). At each step, the search space is reduced by half.
 *    - Space Complexity: O(1), no extra space required.
 */