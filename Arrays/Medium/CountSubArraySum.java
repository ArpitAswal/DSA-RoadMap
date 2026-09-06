/*
 * Problem Statement: Count Subarrays with Given Sum
 * 
 * Given an array of integers and an integer k, find the total number of continuous 
 * subarrays whose sum equals to k.
 * 
 * Example 1:
 * Input : N = 4, array[] = {3, 1, 2, 4}, k = 6
 * Output: 2
 * Explanation: The subarrays that sum up to 6 are [3, 1, 2] and [2, 4].
 * 
 * Example 2:
 * Input: N = 3, array[] = {1, 2, 3}, k = 3
 * Output: 2
 * Explanation: The subarrays that sum up to 3 are [1, 2], and [3].
 */

import java.util.HashMap;
import java.util.Map;

class CountSubArraySum {
    
    /*
     * [Naive Approach]
     * Generate all possible subarrays using two nested loops. The outer loop picks 
     * the starting point, and the inner loop calculates the sum from that starting 
     * point to every possible ending point. If the sum equals k, increment the count.
     */
    static int subArraySumNaive(int[] arr, int k) {
        int n = arr.length;
        int count = 0;

        // Traverse all possible start indices
        for (int i = 0; i < n; i++) {
            int sum = 0;

            // Traverse all possible end indices from start
            for (int j = i; j < n; j++) {
                sum += arr[j];

                // If sum equals k, increment count
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * [Optimal Approach] Prefix Sum + HashMap
     * Keep track of the running prefix sum. If (prefix_sum - k) has been seen before,
     * it means there is a subarray ending at the current index which sums to k.
     * We use a HashMap to store the frequencies of all prefix sums seen so far.
     */
    static int subArraySumOptimal(int[] arr, int k) {
        // HashMap to store (prefixSum -> frequency)
        Map<Integer, Integer> prefixSums = new HashMap<>();
        int res = 0;
        int currSum = 0;

        for (int i = 0; i < arr.length; i++) {
            // Add current element to sum so far
            currSum += arr[i];

            // If currSum itself is equal to k, we found a subarray from index 0
            if (currSum == k) {
                res++;
            }

            // Check if (currSum - k) exists in the map
            // If it does, there are 'prefixSums.get(currSum - k)' subarrays ending here with sum k
            int diff = currSum - k;
            if (prefixSums.containsKey(diff)) {
                res += prefixSums.get(diff);
            }

            // Add the current prefix sum to the map for future evaluations
            prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2, 4};
        int k1 = 6;
        
        int[] arr2 = {1, 2, 3};
        int k2 = 3;

        System.out.println("--- Naive Approach ---");
        System.out.println("Count for arr1: " + subArraySumNaive(arr1, k1));
        System.out.println("Count for arr2: " + subArraySumNaive(arr2, k2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Count for arr1: " + subArraySumOptimal(arr1, k1));
        System.out.println("Count for arr2: " + subArraySumOptimal(arr2, k2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), where N is the length of the array, since we generate 
 *      and evaluate all possible subarrays.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N) or O(N log N) depending on the Map used. With HashMap, 
 *      insertion and lookup are O(1) on average, resulting in an overall O(N) time complexity.
 *    - Space Complexity: O(N), as we store prefix sums and their frequencies in a HashMap. 
 *      In the worst case, all prefix sums are distinct, taking O(N) space.
 */