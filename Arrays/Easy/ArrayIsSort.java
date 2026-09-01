/*
 * Problem Statement: Check if an Array is Sorted
 * 
 * Given an array of integers, determine whether the array is sorted in ascending order.
 * 
 * Example 1:
 * Input: arr[] = {3, 5, 6, 9, 11}
 * Output: true
 * Explanation: Each element is smaller than or equal to the next element.
 * 
 * Example 2:
 * Input: arr[] = {5, 4, 6, 7, 8}
 * Output: false
 * Explanation: The element 5 is greater than 4, so the array is not sorted.
 */

class SortArray {

    /*
     * [Naive Approach] 
     * Compare every element with all subsequent elements.
     * If any subsequent element is strictly smaller than the current element, 
     * it means the array is not sorted.
     */
    boolean checkArrayNaive(int[] arr) {
        // Edge case: Empty or single-element array is always sorted
        if (arr == null || arr.length <= 1) {
            return true;
        }

        // Outer loop: pick each element
        for (int i = 0; i < arr.length; i++) {
            // Inner loop: check against all elements that come after it
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    return false; // Found an inversion
                }
            }
        }
        return true;
    }

    /*
     * [Optimal Approach] 
     * Single pass to check adjacent elements.
     * As long as every element is less than or equal to the next, it is sorted.
     */
    boolean checkArrayOptimal(int[] arr) {
        // Edge case: Empty or single-element array is always sorted
        if (arr == null || arr.length <= 1) {
            return true;
        }

        // Traverse the array and check adjacent pairs
        for (int i = 0; i < arr.length - 1; i++) {
            // If any element is strictly greater than the next one, it's not sorted
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        // If no adjacent elements violated the condition, it is sorted
        return true;
    }
}

public class ArrayIsSort {

    public static void main(String[] args) {
        int[] arr1 = {3, 5, 6, 9, 11};
        int[] arr2 = {5, 4, 6, 7, 8};

        SortArray obj = new SortArray();

        System.out.println("--- Naive Approach ---");
        System.out.println("Is arr1 sorted: " + obj.checkArrayNaive(arr1));
        System.out.println("Is arr2 sorted: " + obj.checkArrayNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Is arr1 sorted: " + obj.checkArrayOptimal(arr1));
        System.out.println("Is arr2 sorted: " + obj.checkArrayOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), where N is the length of the array. The nested loops compare 
 *      each element with every element after it.
 *    - Space Complexity: O(1), as no extra space is required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N), because we traverse the array exactly once, checking adjacent elements.
 *    - Space Complexity: O(1), as no extra space is used.
 */