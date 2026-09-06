/*
 * Problem Statement: Rearrange Array Elements by Sign
 * 
 * Given an integer array nums of even length consisting of an equal number of positive 
 * and negative integers, return the answer array in such a way that the given conditions are met:
 * 1. Every consecutive pair of integers have opposite signs.
 * 2. For all integers with the same sign, the order in which they were present in nums is preserved.
 * 3. The rearranged array begins with a positive integer.
 * 
 * Example 1
 * Input : nums = [2, 4, 5, -1, -3, -4]
 * Output : [2, -1, 4, -3, 5, -4]
 * Explanation:
 * The positive numbers 2, 4, 5 maintain their relative positions and -1, -3, -4 maintain their relative positions.
 * 
 * Example 2
 * Input : nums = [1, -1, -3, -4, 2, 3]
 * Output : [1, -1, 2, -3, 3, -4]
 * Explanation:
 * The positive numbers 1, 2, 3 maintain their relative positions and -1, -3, -4 maintain their relative positions.
 */

class RearrangeElements {
    
    /*
     * [Naive Approach]
     * Separate the positive and negative numbers into two different arrays.
     * Then, iterate over the arrays to place positive and negative numbers alternately
     * into the original (or a new) array.
     */
    public static int[] rearrangeNaive(int[] arr, int n) {
        int[] pos = new int[n / 2];
        int[] neg = new int[n / 2];
        
        int p = 0, ng = 0;
        
        // Step 1: Separate positive and negative elements
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                pos[p++] = arr[i];
            } else {
                neg[ng++] = arr[i];
            }
        }
        
        // Step 2: Combine them alternately
        int[] ans = new int[n];
        for (int i = 0; i < n / 2; i++) {
            ans[2 * i] = pos[i];
            ans[2 * i + 1] = neg[i];
        }
        
        return ans;
    }

    /*
     * [Optimal Approach]
     * Use a single-pass approach by maintaining two pointers:
     * one for the next positive position (starts at 0) and one for the next 
     * negative position (starts at 1). As we traverse the array, we place elements 
     * directly into their correct spots in the result array.
     */
    public static int[] rearrangeOptimal(int[] arr, int n) {
        // Result array initialized to size n
        int[] ans = new int[n];

        // posIndex for even indices (positive), negIndex for odd (negative)
        int posIndex = 0, negIndex = 1;

        // Traverse input array
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                // Place negative number at odd index
                ans[negIndex] = arr[i];
                negIndex += 2;
            } else {
                // Place positive number at even index
                ans[posIndex] = arr[i];
                posIndex += 2;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 5, -1, -3, -4};
        int[] arr2 = {1, -1, -3, -4, 2, 3};

        System.out.println("--- Naive Approach ---");
        int[] naiveAns1 = rearrangeNaive(arr1, arr1.length);
        System.out.print("Rearranged arr1: ");
        printArray(naiveAns1);

        System.out.println("\n--- Optimal Approach ---");
        int[] optimalAns2 = rearrangeOptimal(arr2, arr2.length);
        System.out.print("Rearranged arr2: ");
        printArray(optimalAns2);
    }

    // Helper method to print arrays (replacing Arrays.toString)
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
 *    - Time Complexity: O(N) + O(N/2) = O(N), as we traverse the array to separate elements, 
 *      and then traverse again to combine them.
 *    - Space Complexity: O(N) for storing the 'pos', 'neg', and 'ans' arrays.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N), as we traverse the input array exactly once.
 *    - Space Complexity: O(N), as we need to return an answer array of size N. 
 *      We cannot do this strictly in-place in O(N) time while maintaining order.
 */