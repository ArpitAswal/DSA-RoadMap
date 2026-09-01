/*
 * Problem Statement: Sort an array of 0s, 1s and 2s
 * 
 * Given an array nums with n objects colored red, white, or blue, sort them in-place 
 * so that objects of the same color are adjacent, with the colors in the order red, 
 * white, and blue. We will use the integers 0, 1, and 2 to represent the color red, 
 * white, and blue, respectively.
 * 
 * Example 1:
 * Input: nums = [1, 0, 2, 1, 0]
 * Output: [0, 0, 1, 1, 2]
 * Explanation: The nums array in sorted order has two 0s, two 1s, and one 2.
 * 
 * Example 2:
 * Input: nums = [0, 1, 2, 0, 2, 1, 1, 2, 0]
 * Output: [0, 0, 0, 1, 1, 1, 2, 2, 2]
 */

class Sort012 {
    
    /*
     * [Naive Approach] Counting Sort
     * Traverse the array once and count the occurrences of 0, 1, and 2.
     * Traverse the array again to overwrite the elements based on the counts.
     */
    void sort012Naive(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;

        // Step 1: Count occurrences
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) count0++;
            else if (nums[i] == 1) count1++;
            else count2++;
        }

        // Step 2: Overwrite the array
        int idx = 0;
        while (count0 > 0) {
            nums[idx++] = 0;
            count0--;
        }
        while (count1 > 0) {
            nums[idx++] = 1;
            count1--;
        }
        while (count2 > 0) {
            nums[idx++] = 2;
            count2--;
        }
    }

    /*
     * [Optimal Approach] Dutch National Flag Algorithm
     * Use three pointers (low, mid, high) to place 0s at the beginning, 2s at the end, 
     * and 1s in the middle in a single pass.
     */
    void sort012Optimal(int[] nums) {
        // Initialize three pointers: low and mid at 0, high at the end
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        // Continue processing until mid crosses high
        while (mid <= high) {
            // If current element is 0, swap with low and move both low and mid forward
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            }
            // If current element is 1, just move mid forward
            else if (nums[mid] == 1) {
                mid++;
            }
            // If current element is 2, swap with high and move only high backward
            else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}

public class SortArray012 {
    public static void main(String[] args) {
        int[] arr1 = {1, 0, 2, 1, 0};
        int[] arr2 = {0, 1, 2, 0, 2, 1, 1, 2, 0};

        Sort012 obj = new Sort012();
        
        System.out.println("--- Naive Approach ---");
        int[] arr1Naive = arr1.clone();
        obj.sort012Naive(arr1Naive);
        System.out.print("Sorted arr1: ");
        printArray(arr1Naive);

        System.out.println("\n--- Optimal Approach ---");
        int[] arr2Optimal = arr2.clone();
        obj.sort012Optimal(arr2Optimal);
        System.out.print("Sorted arr2: ");
        printArray(arr2Optimal);
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
 * 1. Naive Approach:
 *    - Time Complexity: O(N) + O(N) = O(N). We traverse the array twice.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach (Dutch National Flag Algorithm):
 *    - Time Complexity: O(N), we traverse the array exactly once.
 *    - Space Complexity: O(1), no extra space required.
 */
