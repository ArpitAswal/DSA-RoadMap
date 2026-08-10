// Next Permutation

// A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
// The next permutation of an array of integers is the lexicographically next greater permutation of its integer.
// If such an arrangement is not possible (the array is sorted in descending order), the array must be rearranged
// as the lowest possible order (i.e., sorted in ascending order).
// The replacement must be in-place and use only constant extra memory.

// Examples:

// Input: nums[] = [1, 2, 3]
// Output: [1, 3, 2]
// Explanation: The lexicographically next permutation after [1, 2, 3] is [1, 3, 2].

// Input: nums[] = [3, 2, 1]
// Output: [1, 2, 3]
// Explanation: [3, 2, 1] is the highest permutation, so next permutation wraps around to the lowest [1, 2, 3].

// Input: nums[] = [1, 2, 3, 6, 5, 4]
// Output: [1, 2, 4, 3, 5, 6]
// Explanation: Next lexicographical arrangement is [1, 2, 4, 3, 5, 6].

import java.util.Arrays;

class NextPermutation {

    /*
     * [Naive Approach] Generate All Permutations - O(n! * n) Time and O(n!) Space
     *
     * Logic / Steps:
     *   1. Generate all n! permutations in lexicographical order.
     *   2. Search for input array in generated list of permutations.
     *   3. Return next permutation in list (or first permutation if input is last).
     *
     * Drawback:
     *   - Factorial time O(n!) and exponential space O(n!) make it completely unviable for n > 10.
     *
     * Time Complexity  : O(n! * n) - generating and sorting all n! permutations of length n.
     * Space Complexity : O(n!)     - storing all permutations in memory.
     */

    /*
     * [Optimal / Interview Approach] Single-Pass In-Place Algorithm - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Step 1 (Find Pivot): Scan backwards from right to left (index n - 2 down to 0)
     *      to find first index `pivot` where nums[pivot] < nums[pivot + 1].
     *   2. Step 2 (Edge Case): If no pivot exists (pivot == -1), array is sorted in descending order (highest permutation).
     *      Reverse entire array to convert to lowest ascending order and return.
     *   3. Step 3 (Find Swap Element): If pivot != -1, scan backwards from right to left (index n - 1 down to pivot + 1)
     *      to find first element nums[j] strictly greater than nums[pivot].
     *   4. Step 4 (Swap): Swap nums[pivot] and nums[j].
     *   5. Step 5 (Reverse Suffix): Reverse sub-array from index `pivot + 1` to `n - 1` to get smallest lexicographical order suffix.
     *
     * Why this is interview-preferred:
     *   - In-place modification with O(1) auxiliary memory.
     *   - Complete traversal requires at most 3 linear passes (O(n) time total).
     *
     * Time Complexity  : O(n) - linear scan for pivot + linear scan for swap target + reverse pass.
     * Space Complexity : O(1) - constant auxiliary variables used.
     */
    static void nextPermutation(int[] nums) {
        int n = nums.length; // Length of input array
        int pivot = -1;      // Initialize pivot index

        // Step 1: Find first decreasing element from right (pivot)
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pivot = i; // Found pivot index
                break;     // Stop scan
            }
        }

        // Step 2: If no pivot found, array is in descending order -> reverse to ascending order
        if (pivot == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // Step 3: Find first element from right strictly greater than nums[pivot]
        for (int i = n - 1; i > pivot; i--) {
            if (nums[i] > nums[pivot]) {
                // Step 4: Swap nums[pivot] and nums[i]
                swap(nums, i, pivot);
                break;
            }
        }

        // Step 5: Reverse suffix after pivot index (from pivot + 1 to n - 1)
        reverse(nums, pivot + 1, n - 1);
    }

    // Helper method to reverse array section between start and end indices
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Helper method to swap two elements in array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3 };
        System.out.println("Original 1: " + Arrays.toString(nums1));
        nextPermutation(nums1);
        System.out.println("Next Permutation: " + Arrays.toString(nums1) + "\n");

        int[] nums2 = { 3, 2, 1 };
        System.out.println("Original 2: " + Arrays.toString(nums2));
        nextPermutation(nums2);
        System.out.println("Next Permutation: " + Arrays.toString(nums2) + "\n");

        int[] nums3 = { 1, 2, 3, 6, 5, 4 };
        System.out.println("Original 3: " + Arrays.toString(nums3));
        nextPermutation(nums3);
        System.out.println("Next Permutation: " + Arrays.toString(nums3));
    }
}