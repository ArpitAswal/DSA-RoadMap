/*
 * Problem Statement: Longest Subarray with sum K
 * 
 * Given an array containing N integers and an integer K. Your task is to find the 
 * length of the longest Sub-Array with the sum of the elements equal to the given value K.
 * 
 * Example 1:
 * Input: nums = [10, 5, 2, 7, 1, 9], k = 15
 * Output: 4
 * Explanation: The longest sub-array with a sum equal to 15 is [5, 2, 7, 1], 
 * which has a length of 4.
 * 
 * Example 2:
 * Input: nums = [-1, 1, 1], k = 1
 * Output: 3
 * Explanation: The longest sub-array with a sum equal to 1 is [-1, 1, 1], 
 * which has a length of 3.
 */

import java.util.HashMap;

class SubArrayK {

    /*
     * [Naive Approach] 
     * Generate all possible subarrays and check their sum. If the sum equals K, 
     * update the maximum length.
     */
    public int longestSubarrayNaive(int[] nums, int k) {
        int maxLen = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                
                if (currentSum == k) {
                    int length = j - i + 1;
                    if (length > maxLen) {
                        maxLen = length;
                    }
                }
            }
        }
        return maxLen;
    }

    /*
     * [Optimal Approach for Array with Negatives] Prefix Sum and HashMap
     * We keep a running prefix sum and a hash map to store the first occurrence 
     * of each prefix sum. If (prefixSum - k) exists in the map, we have found a 
     * subarray summing to K.
     */
    public int longestSubarrayHashMap(int[] nums, int k) {
        int maxLen = 0;
        int prefixSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            
            // If the prefix sum itself is equal to k, the subarray is from index 0 to i
            if (prefixSum == k) {
                if (i + 1 > maxLen) {
                    maxLen = i + 1;
                }
            }
            
            // If prefixSum - k is found in map, it means there is a subarray with sum k
            int remaining = prefixSum - k;
            if (map.containsKey(remaining)) {
                int length = i - map.get(remaining);
                if (length > maxLen) {
                    maxLen = length;
                }
            }
            
            // Store the first occurrence of a prefixSum to maximize length
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        return maxLen;
    }

    /*
     * [Optimal Approach for ONLY Positives] Sliding Window / Two Pointers
     * Expand the window to the right until the sum >= K. If sum > K, shrink 
     * the window from the left. Note: This FAILS if the array contains negative numbers.
     */
    public int longestSubarraySlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        int left = 0, right = 0;
        long sum = 0; // use long to prevent overflow

        while (right < n) {
            sum += nums[right];

            // Shrink the window if sum exceeds k (only works for positive numbers)
            while (left <= right && sum > k) {
                sum -= nums[left];
                left++;
            }

            // Update max length if sum equals k
            if (sum == k) {
                int length = right - left + 1;
                if (length > maxLen) {
                    maxLen = length;
                }
            }

            // Expand window
            right++;
        }

        return maxLen;
    }
}

public class LongestSubArraySumK {
    public static void main(String[] args) {
        int[] arr1 = {10, 5, 2, 7, 1, 9};
        int k1 = 15;
        
        int[] arr2 = {-1, 1, 1};
        int k2 = 1;
        
        SubArrayK obj = new SubArrayK();
        
        System.out.println("--- Naive Approach ---");
        System.out.println("Max length (arr1): " + obj.longestSubarrayNaive(arr1, k1));
        System.out.println("Max length (arr2): " + obj.longestSubarrayNaive(arr2, k2));
        
        System.out.println("\n--- Optimal (HashMap) Approach (Handles Negatives) ---");
        System.out.println("Max length (arr1): " + obj.longestSubarrayHashMap(arr1, k1));
        System.out.println("Max length (arr2): " + obj.longestSubarrayHashMap(arr2, k2));
        
        System.out.println("\n--- Optimal (Sliding Window) Approach (Positives Only) ---");
        System.out.println("Max length (arr1): " + obj.longestSubarraySlidingWindow(arr1, k1));
        System.out.println("Max length (arr2): " + obj.longestSubarraySlidingWindow(arr2, k2) + " (Incorrect, sliding window fails on negatives)");
    } 
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), generating all subarrays.
 *    - Space Complexity: O(1), no extra space.
 * 
 * 2. Prefix Sum + HashMap Approach:
 *    - Time Complexity: O(N) or O(N log N) depending on the HashMap implementation 
 *      (O(N) on average for Java's HashMap).
 *    - Space Complexity: O(N), for storing the prefix sums in the map.
 * 
 * 3. Sliding Window Approach (For Positives):
 *    - Time Complexity: O(N), both left and right pointers move at most N times.
 *    - Space Complexity: O(1), no extra space.
 */