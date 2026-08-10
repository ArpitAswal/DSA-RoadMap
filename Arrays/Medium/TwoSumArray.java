// Two Sum - Find Pair with Given Sum in Array

// Given an array of integers arr[] and a target integer target, find two elements in the array
// such that their sum equals the target. Return or print the pair of numbers (or their indices).

// Examples:

// Input: arr[] = [3, 6, 2, 7, 5, 1], target = 13
// Output: Pair found: 6 and 7 (Indices: 1 and 3)
// Explanation: arr[1] + arr[3] = 6 + 7 = 13, which equals the target.

// Input: arr[] = [1, 2, 3, 4, 5, 6, 7], target = 10
// Output: Pair found: 4 and 6 (Indices: 3 and 5)
// Explanation: arr[3] + arr[5] = 4 + 6 = 10, which equals the target.

// Input: arr[] = [1, 2, 3, 4], target = 20
// Output: No pair found
// Explanation: No two elements in the array add up to 20.

import java.util.HashMap;

public class TwoSumArray {

    /*
     * [Naive Approach] Brute Force using Nested Loops - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Traverse the array with an outer loop index 'i' from 0 to n - 1.
     *   2. For each element, traverse with an inner loop index 'j' from i + 1 to n - 1.
     *   3. Check if arr[i] + arr[j] == target.
     *   4. If a pair is found, print the pair and return true.
     *   5. If no pair is found after inspecting all pairs, return false.
     *
     * Drawback:
     *   - Checks all n*(n-1)/2 pairs, resulting in quadratic time O(n^2).
     *
     * Time Complexity  : O(n^2) - two nested loops over the array.
     * Space Complexity : O(1)   - only integer variables used.
     */
    static boolean twoSumNaive(int[] arr, int target) {
        int n = arr.length; // Length of the input array

        // Outer loop selects the first element of the pair
        for (int i = 0; i < n; i++) {
            // Inner loop selects the second element of the pair (starting after i)
            for (int j = i + 1; j < n; j++) {
                // Check if the sum of arr[i] and arr[j] equals the target
                if (arr[i] + arr[j] == target) {
                    System.out.println("Pair found (Naive): " + arr[i] + " + " + arr[j] + " = " + target);
                    return true; // Found the pair, exit early
                }
            }
        }

        System.out.println("No pair found (Naive) for target: " + target);
        return false; // Return false if no pair satisfies the condition
    }

    /*
     * [Better Approach] Two-Pointer Technique (For Sorted Arrays) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain two pointers: 'start' at index 0 and 'end' at index n - 1.
     *   2. Calculate current sum: sum = arr[start] + arr[end].
     *   3. If sum == target: pair found! Print pair and return true.
     *   4. If sum < target: move 'start' pointer rightward (start++) to increase sum.
     *   5. If sum > target: move 'end' pointer leftward (end--) to decrease sum.
     *   6. Repeat until start >= end.
     *
     * Note: If the array is unsorted, sorting it first takes O(n log n) time and O(1) space.
     *
     * Time Complexity  : O(n) for sorted array; O(n log n) if sorting is required.
     * Space Complexity : O(1) - constant extra auxiliary space.
     */
    static boolean twoSumTwoPointer(int[] arr, int target) {
        int start = 0;              // Pointer starting at the beginning of the array
        int end = arr.length - 1;   // Pointer starting at the end of the array

        // Continue until the two pointers meet or cross each other
        while (start < end) {
            int currentSum = arr[start] + arr[end]; // Calculate sum of elements at start and end pointers

            if (currentSum == target) {
                // Exact sum matched
                System.out.println("Pair found (Two-Pointer): " + arr[start] + " + " + arr[end] + " = " + target);
                return true;
            } else if (currentSum < target) {
                // Sum is too small -> increment start to pick a larger value
                start++;
            } else {
                // Sum is too large -> decrement end to pick a smaller value
                end--;
            }
        }

        System.out.println("No pair found (Two-Pointer) for target: " + target);
        return false;
    }

    /*
     * [Optimal / Interview Approach] Hash Map Single-Pass - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Initialize a HashMap to store (element_value -> index).
     *   2. Iterate through each element arr[i] in the array:
     *        a. Compute required complement: comp = target - arr[i].
     *        b. Check if 'comp' exists in the HashMap.
     *        c. If it exists: we found our pair (comp, arr[i]) at indices (map.get(comp), i)!
     *        d. If it doesn't exist: store current element in map: map.put(arr[i], i).
     *   3. If loop finishes without returning, no pair exists.
     *
     * Why this is interview-preferred:
     *   - Works on UNSORTED arrays in a single linear scan O(n).
     *   - Replaces inner search loop with O(1) average lookup in HashMap.
     *
     * Time Complexity  : O(n) - single pass through the array.
     * Space Complexity : O(n) - HashMap stores up to n elements.
     */
    static boolean twoSumHashMap(int[] arr, int target) {
        // Map stores element value as key and its array index as value
        HashMap<Integer, Integer> map = new HashMap<>();

        // Single pass traversal over array elements
        for (int i = 0; i < arr.length; i++) {
            int comp = target - arr[i]; // Calculate needed complement to reach target

            // Check if complement has already been seen in the array
            if (map.containsKey(comp)) {
                // Retrieve index of complement from map
                int compIndex = map.get(comp);
                System.out.println("Pair found (HashMap): " + comp + " (at index " + compIndex + ") + " 
                                   + arr[i] + " (at index " + i + ") = " + target);
                return true; // Return immediately after finding first matching pair
            }

            // Store current element and its index in map for future comparisons
            map.put(arr[i], i);
        }

        System.out.println("No pair found (HashMap) for target: " + target);
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Unsorted Array
        int[] unsortedArr = { 3, 6, 2, 7, 5, 1 };
        int target1 = 13;

        System.out.println("--- Test Case 1: Unsorted Array ---");
        System.out.println("1. Naive Approach:");
        twoSumNaive(unsortedArr, target1);

        System.out.println("\n2. HashMap Approach (Interview-Preferred for Unsorted):");
        twoSumHashMap(unsortedArr, target1);

        System.out.println();

        // Test Case 2: Sorted Array
        int[] sortedArr = { 1, 2, 3, 4, 5, 6, 7 };
        int target2 = 10;

        System.out.println("--- Test Case 2: Sorted Array ---");
        System.out.println("1. Two-Pointer Approach (Interview-Preferred for Sorted):");
        twoSumTwoPointer(sortedArr, target2);

        System.out.println("\n2. HashMap Approach:");
        twoSumHashMap(sortedArr, target2);
    }
}