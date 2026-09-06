/*
 * Problem Statement: Longest Consecutive Sequence
 * 
 * Given an unsorted array of integers nums, return the length of the longest 
 * consecutive elements sequence.
 * 
 * Example 1:
 * Input: nums = [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * 
 * Example 2:
 * Input: nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]  
 * Output: 9  
 * Explanation: The longest consecutive sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8], which has a length of 9.
 */

import java.util.HashSet;
import java.util.Set;

class LongestConsecutiveSequence {
    
    /*
     * [Naive Approach]
     * For every element, we try to find the next consecutive elements (x+1, x+2, ...) 
     * in the array using linear search. We keep track of the maximum sequence length found.
     */
    static int consecutiveLengthNaive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int longest = 1;
        
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int count = 1;
            
            // Look for x+1, x+2... in the array
            while (linearSearch(nums, x + 1)) {
                x += 1;
                count += 1;
            }
            
            if (count > longest) {
                longest = count;
            }
        }
        return longest;
    }
    
    // Helper function for naive approach
    private static boolean linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }

    /*
     * [Optimal Approach]
     * We use a HashSet to store all elements for O(1) lookups.
     * Then we iterate through the set. If a number `it` does not have `it-1` in the set, 
     * it means it is the starting point of a sequence. We then count consecutive numbers 
     * from this starting point.
     */
    static int consecutiveLengthOptimal(int[] nums) {
        int n = nums.length;
        // Edge case: If the array is empty, no sequence exists
        if (n == 0) return 0;

        // Variable to store the longest sequence length found
        int longest = 1; 

        // HashSet to store unique elements for O(1) lookup
        Set<Integer> set = new HashSet<>();

        // Add all elements to the set to remove duplicates and allow quick lookups
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        // Loop through each element in the set to find the starting point of consecutive sequences
        for (int it : set) {
            // If there is no number before 'it', it’s the start of a sequence
            if (!set.contains(it - 1)) {
                // Start the count for this sequence
                int count = 1; 
                // Store the current number
                int x = it; 

                // Keep checking for the next consecutive number
                while (set.contains(x + 1)) {
                    // Move to the next number in sequence
                    x = x + 1; 
                    // Increment the length of current sequence
                    count = count + 1; 
                }

                // Update the longest sequence length if needed
                if (count > longest) {
                    longest = count;
                }
            }
        }

        // Return the length of the longest sequence
        return longest;
    }

    public static void main (String[] args) {
        int[] arr1 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] arr2 = {100, 4, 200, 1, 3, 2};

        System.out.println("--- Naive Approach ---");
        System.out.println("Longest sequence (arr1): " + consecutiveLengthNaive(arr1));
        System.out.println("Longest sequence (arr2): " + consecutiveLengthNaive(arr2));
        
        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Longest sequence (arr1): " + consecutiveLengthOptimal(arr1));
        System.out.println("Longest sequence (arr2): " + consecutiveLengthOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2) or O(N^3) in the worst case, as for each element, we 
 *      could potentially search the entire array multiple times.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N). We traverse the array to populate the HashSet (O(N)). 
 *      Then we iterate through the set (O(N)). The inner while loop only runs for the 
 *      starting elements of sequences, meaning each element is visited at most twice. 
 *      Total time is strictly O(N).
 *    - Space Complexity: O(N), as we use a HashSet to store the elements of the array.
 */