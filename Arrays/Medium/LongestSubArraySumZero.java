/*
 * Problem Statement: Longest Subarray with sum 0
 * 
 * Given an array containing both positive and negative integers, we have to find 
 * the length of the longest subarray with the sum of all elements equal to zero.
 * 
 * Example 1:
 * Input: N = 6, array[] = {9, -3, 3, -1, 6, -5}  
 * Output: 5  
 * Explanation: The following subarrays sum to zero:
 * - {-3, 3}
 * - {-1, 6, -5}
 * - {-3, 3, -1, 6, -5}
 * The length of the longest subarray with sum zero is 5.
 * 
 * Example 2:
 * Input: N = 8, array[] = {6, -2, 2, -8, 1, 7, 4, -10}  
 * Output: 8  
 * Explanation: The longest subarray with sum zero is the entire array itself.
 */

import java.util.HashMap;

class SubArrayZero {

    /*
     * [Naive Approach]
     * Generate all possible subarrays, calculate the sum for each.
     * If the sum is 0, update the maximum length found so far.
     */
    int sumZeroNaive(int[] arr) {
        int maxCount = 0;
        int n = arr.length;

        // Outer loop picks the starting point
        for (int i = 0; i < n; i++) {
            int curSum = 0;
            // Inner loop picks the ending point
            for (int j = i; j < n; j++) {
                curSum += arr[j];
                
                // If sum becomes 0, calculate the length and update maxCount
                if (curSum == 0) {
                    int length = j - i + 1;
                    if (length > maxCount) {
                        maxCount = length;
                    }
                }
            }
        }
        return maxCount;
    }

    /*
     * [Optimal Approach] Prefix Sum & HashMap
     * We calculate the prefix sum of the elements. If the prefix sum at index i is 
     * equal to the prefix sum at index j (where i < j), it means the sum of elements 
     * between i+1 and j is 0. We store the first occurrence of each prefix sum in a Map.
     */
    int sumZeroOptimal(int[] arr) {
        int maxCount = 0;
        int currentSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            // If the prefix sum is 0, the subarray from index 0 to i has sum 0
            if (currentSum == 0) {
                if (i + 1 > maxCount) {
                    maxCount = i + 1;
                }
            } else {
                // If the currentSum has been seen before, it means the subarray 
                // between the previous index and the current index sums to 0
                if (map.containsKey(currentSum)) {
                    int length = i - map.get(currentSum);
                    if (length > maxCount) {
                        maxCount = length;
                    }
                } else {
                    // Only put the sum in the map if it's not present to keep the 
                    // earliest index and maximize the length
                    map.put(currentSum, i);
                }
            }
        }
        return maxCount;
    }
}

public class LongestSubArraySumZero {
    public static void main(String[] args) {
        int[] arr1 = {9, -3, 3, -1, 6, -5};
        int[] arr2 = {6, -2, 2, -8, 1, 7, 4, -10};
        int[] arr3 = {15, -2, 2, -8, 1, 7, 10, 23};
        int[] arr4 = {2, 10, 4};
        
        SubArrayZero obj = new SubArrayZero();
        
        System.out.println("--- Naive Approach ---");
        System.out.println("Max length (arr1): " + obj.sumZeroNaive(arr1));
        System.out.println("Max length (arr2): " + obj.sumZeroNaive(arr2));
        System.out.println("Max length (arr3): " + obj.sumZeroNaive(arr3));
        System.out.println("Max length (arr4): " + obj.sumZeroNaive(arr4));
        
        System.out.println("\n--- Optimal (HashMap) Approach ---");
        System.out.println("Max length (arr1): " + obj.sumZeroOptimal(arr1));
        System.out.println("Max length (arr2): " + obj.sumZeroOptimal(arr2));
        System.out.println("Max length (arr3): " + obj.sumZeroOptimal(arr3));
        System.out.println("Max length (arr4): " + obj.sumZeroOptimal(arr4));
    } 
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), as we generate all possible subarrays using two loops.
 *    - Space Complexity: O(1), no extra space is used.
 * 
 * 2. Optimal Approach (HashMap):
 *    - Time Complexity: O(N) or O(N log N) depending on the Map used. With HashMap, 
 *      insertion and retrieval are O(1) on average, giving O(N) overall time complexity.
 *    - Space Complexity: O(N), for storing the prefix sums and their corresponding 
 *      indices in the HashMap.
 */