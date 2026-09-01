/*
 * Problem Statement: Missing Number
 * 
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
 * find the one that is missing from the array.
 * 
 * Example 1:
 * Input: arr[] = {3, 0, 1}
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range 
 * [0, 3]. 2 is the missing number in the range since it does not appear in arr.
 * 
 * Example 2:
 * Input: arr[] = {8, 2, 4, 5, 3, 7, 1, 0}
 * Output: 6
 * Explanation: n = 8, so all numbers are in the range [0, 8]. 6 is the missing number.
 */

class FindMissing {
    
    /*
     * [Naive Approach]
     * For every number from 0 to n, check if it is present in the array.
     */
    public static int missingNumNaive(int[] arr) {
        int n = arr.length;

        // Iterate from 0 to n and check if the current number is present
        for (int i = 0; i <= n; i++) {
            boolean found = false;
            
            // Search for i in the array
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    found = true;
                    break;
                }
            }

            // If the current number is not present, it is the missing one
            if (!found) {
                return i;
            }
        }
        return -1;
    }

    /*
     * [Better Approach] Using a Hash Array (Frequency Array)
     * Create an array of size n+1 to track the occurrence of each number.
     */
    public static int missingNumHash(int[] arr) {
        int n = arr.length;

        // Create hash array of size n+1 initialized to 0
        int[] hash = new int[n + 1];

        // Store frequencies of elements
        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        // Find the missing number by checking which index has 0 frequency
        for (int i = 0; i <= n; i++) {
            if (hash[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /*
     * [Optimal Approach] Using Math (Sum of first N natural numbers)
     * Calculate the expected sum of numbers from 0 to n and subtract the actual sum.
     */
    public static int missingNumSumN(int[] arr) {
        long n = arr.length;
    
        // Calculate the actual sum of array elements
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
    
        // Calculate expected sum using formula n * (n + 1) / 2
        // Use long to avoid integer overflow for large n
        long expSum = n * (n + 1) / 2;
    
        // Return the difference, which is the missing number
        return (int)(expSum - sum);
    }
}

public class MissingNumber {

    public static void main(String[] args) {
        int[] arr = {8, 2, 4, 5, 3, 7, 1, 0};  
        
        System.out.println("--- Naive Approach ---");
        System.out.println("The missing number is: " + FindMissing.missingNumNaive(arr));
        
        System.out.println("\n--- Better (Hash) Approach ---");
        System.out.println("The missing number is: " + FindMissing.missingNumHash(arr));
        
        System.out.println("\n--- Optimal (Sum) Approach ---");
        System.out.println("The missing number is: " + FindMissing.missingNumSumN(arr));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), where N is the length of the array. The outer loop runs 
 *      N+1 times, and the inner loop runs N times.
 *    - Space Complexity: O(1), as no extra space is used.
 * 
 * 2. Better (Hash) Approach:
 *    - Time Complexity: O(N), as we iterate through the array once to build the hash 
 *      and once through the hash to find the missing number.
 *    - Space Complexity: O(N), as we require an extra array of size N+1.
 * 
 * 3. Optimal (Sum) Approach:
 *    - Time Complexity: O(N), as we iterate through the array exactly once to find the sum.
 *    - Space Complexity: O(1), as no extra space is used, just a few variables.
 */