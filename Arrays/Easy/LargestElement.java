/*
 * Problem Statement: Find the Largest Element in an Array
 * 
 * Given an array of integers, find the largest element in the array.
 * 
 * Example 1:
 * Input: arr[] = {2, 5, 1, 3, 0}
 * Output: 5
 * Explanation: 5 is the largest element in the array.
 * 
 * Example 2:
 * Input: arr[] = {8, 10, 5, 7, 9}
 * Output: 10
 * Explanation: 10 is the largest element in the array.
 */

class Largest {

    /*
     * [Naive Approach] 
     * For every element, compare it with all other elements. If an element 
     * is greater than or equal to all other elements, it is the maximum.
     */
    public static int findLargestNaive(int[] arr) {
        // Edge case: Empty array
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        int n = arr.length;
        
        // Pick each element one by one
        for (int i = 0; i < n; i++) {
            boolean isLargest = true;
            
            // Compare the chosen element with every other element
            for (int j = 0; j < n; j++) {
                if (arr[j] > arr[i]) {
                    isLargest = false;
                    break;
                }
            }
            
            // If no element was strictly greater than arr[i], it is the largest
            if (isLargest) {
                return arr[i];
            }
        }
        
        return -1; // Should not reach here for valid inputs
    }

    /*
     * [Optimal Approach] 
     * Single pass iteration tracking the maximum element.
     */
    public static int findLargestOptimal(int[] arr) {
        // Edge case: Empty array
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }

        // Initialize max with the first element in the array
        int max = arr[0];  
        int n = arr.length;

        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            // If the current element is strictly greater than max, update max
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Return the largest element found
        return max;
    }
}

public class LargestElement {

    public static void main(String[] args) {
        int[] arr1 = {2, 5, 1, 3, 0};
        int[] arr2 = {8, 10, 5, 7, 9};

        System.out.println("--- Naive Approach ---");
        System.out.println("Largest in arr1: " + Largest.findLargestNaive(arr1));
        System.out.println("Largest in arr2: " + Largest.findLargestNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Largest in arr1: " + Largest.findLargestOptimal(arr1));
        System.out.println("Largest in arr2: " + Largest.findLargestOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2) in the worst case, as we use nested loops to compare
 *      every element with all other elements.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N), as we traverse the array exactly once. This is the 
 *      theoretical minimum because we must examine every element at least once.
 *    - Space Complexity: O(1), no extra space required.
 */