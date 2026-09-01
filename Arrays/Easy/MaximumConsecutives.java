/*
 * Problem Statement: Max Consecutive Ones
 * 
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * 
 * Example 1:
 * Input: prices = {1, 1, 0, 1, 1, 1}
 * Output: 3
 * Explanation: There are two consecutive 1's and three consecutive 1's in the array 
 * out of which maximum is 3.
 */

class ConsecutivesOne {

    /*
     * [Naive Approach]
     * For every element that is a 1, start a new loop to count how many consecutive
     * 1s follow it. Track the maximum count found.
     */
    public int findMaxConsecutiveOnesNaive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                int count = 0;
                // Count consecutive ones starting from index i
                for (int j = i; j < n; j++) {
                    if (nums[j] == 1) {
                        count++;
                    } else {
                        break;
                    }
                }
                // Manual check for maximum without using Math.max
                if (count > max) {
                    max = count;
                }
            }
        }
        return max;
    }

    /*
     * [Optimal Approach]
     * Iterate through the array once. Maintain a running count of 1s.
     * Reset the count to 0 when a 0 is encountered. 
     * Continuously update the maximum count.
     */
    public int findMaxConsecutiveOnesOptimal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        int max = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++; // Increment count if 1 is found
            } else {
                count = 0; // Reset count if 0 is found
            }
            
            // Manual check for maximum without using Math.max
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}

public class MaximumConsecutives {

    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        ConsecutivesOne obj = new ConsecutivesOne();
        
        System.out.println("--- Naive Approach ---");
        System.out.println("The maximum consecutive ones are: " + obj.findMaxConsecutiveOnesNaive(nums));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("The maximum consecutive ones are: " + obj.findMaxConsecutiveOnesOptimal(nums));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2) in the worst case (if all elements are 1, the inner 
 *      loop will scan to the end repeatedly).
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N), because we traverse the array exactly once.
 *    - Space Complexity: O(1), no extra space required.
 */