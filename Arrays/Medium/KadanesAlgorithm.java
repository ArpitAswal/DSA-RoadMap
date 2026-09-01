/*
 * Problem Statement: Maximum Subarray (Kadane's Algorithm)
 * 
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum. Optionally, also return the elements of the 
 * maximum sum subarray.
 * 
 * Example 1:
 * Input: arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4}
 * Output: 6
 * Explanation: The subarray [4, -1, 2, 1] has the largest sum = 6.
 * 
 * Example 2:
 * Input: arr[] = {2, 3, 5, -2, 7, -4}
 * Output: 15
 * Explanation: The subarray [2, 3, 5, -2, 7] has the largest sum = 15.
 */

class MaxSubArray {

    /*
     * [Naive Approach] Check all possible subarrays
     * We can find the sum of all possible subarrays by using two loops.
     * The outer loop picks the starting point, and the inner loop calculates 
     * the sum from that starting point to every possible ending point.
     */
    int maxSumNaive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxSum = arr[0];
        
        for (int i = 0; i < arr.length; i++) {
            int currentSum = 0;
            for (int j = i; j < arr.length; j++) {
                currentSum += arr[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    /*
     * [Optimal Approach] Kadane's Algorithm
     * Iterate through the array keeping a running total (currentSum).
     * If currentSum drops below 0, it means the current subarray is dragging 
     * the sum down, so we reset currentSum to 0 (effectively starting a new subarray).
     * Keep track of the maximum sum seen so far (maxSum).
     */
    int maxSumOptimal(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxSum = arr[0];
        int currentSum = 0;

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            
            // Update maxSum if currentSum is greater
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
            
            // If currentSum is negative, reset it to zero
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }

    /*
     * [Follow-up] Print the subarray with the maximum sum
     * Keep track of the starting and ending indices of the subarray.
     * Update the start index whenever currentSum is reset to 0.
     */
    int[] getMaximumSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int maxSum = arr[0];
        int currentSum = 0;
        int start = 0;
        int ansStart = -1, ansEnd = -1;

        for (int i = 0; i < arr.length; i++) {
            if (currentSum == 0) {
                start = i; // starting a new subarray
            }
            
            currentSum += arr[i];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                ansStart = start;
                ansEnd = i;
            }

            if (currentSum < 0) {
                currentSum = 0;
            }
        }

        // Handle case where all elements might be negative
        if (ansStart == -1 || ansEnd == -1) {
            // Find the maximum single element index
            int maxIdx = 0;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > arr[maxIdx]) {
                    maxIdx = i;
                }
            }
            ansStart = maxIdx;
            ansEnd = maxIdx;
        }

        // Extract the subarray
        int length = ansEnd - ansStart + 1;
        int[] subArray = new int[length];
        for (int i = 0; i < length; i++) {
            subArray[i] = arr[ansStart + i];
        }

        return subArray;
    }
}

public class KadanesAlgorithm {

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 5, -2, 7, -4};
        int[] arr2 = {-2, -3, -7, -2, -10, -4}; // all negative array

        MaxSubArray obj = new MaxSubArray();
        
        System.out.println("--- Naive Approach ---");
        System.out.println("Max sum (arr1): " + obj.maxSumNaive(arr1));
        System.out.println("Max sum (arr2): " + obj.maxSumNaive(arr2));

        System.out.println("\n--- Optimal Approach (Kadane's) ---");
        System.out.println("Max sum (arr1): " + obj.maxSumOptimal(arr1));
        System.out.println("Max sum (arr2): " + obj.maxSumOptimal(arr2));

        System.out.println("\n--- Subarray Extraction ---");
        System.out.print("Max subarray (arr1): ");
        printArray(obj.getMaximumSubArray(arr1));
        
        System.out.print("Max subarray (arr2): ");
        printArray(obj.getMaximumSubArray(arr2));
    }
    
    // Helper function to print array without using Arrays.toString
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
 *    - Time Complexity: O(N^2), as there are two nested loops to evaluate all subarrays.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach (Kadane's Algorithm):
 *    - Time Complexity: O(N), because we traverse the array only once.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 3. Subarray Extraction:
 *    - Time Complexity: O(N) to find the indices, plus O(K) where K is the length of 
 *      the result subarray. Total Time: O(N).
 *    - Space Complexity: O(K) to store the result subarray, where K <= N.
 */