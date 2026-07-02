// Find the Second Largest Element in an Array

// Given an array arr[] of n integers, find the second largest distinct element.
// If no second largest element exists (e.g., all elements are the same), return -1.

// Examples:

// Input: arr[] = [10, 5, 20, 8, 20]
// Output: 10
// Explanation: Largest is 20. Second largest distinct element is 10.

// Input: arr[] = [5, 5, 5]
// Output: -1
// Explanation: All elements are the same; no distinct second largest exists.

// Input: arr[] = [1, 2]
// Output: 1
// Explanation: Largest is 2; second largest is 1.

class SecondLargestElement {

    /*
     * [Naive Approach] Using Sorting - O(n log n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Sort the array in ascending order.
     *   2. Start from the second-to-last element and walk backwards.
     *   3. The first element that is strictly less than arr[n-1] (the largest)
     *      is the second largest.
     *   4. If no such element exists, return -1.
     *
     * Drawback: Sorting costs O(n log n) and modifies the original array.
     *
     * Time Complexity  : O(n log n) - dominated by the sorting step.
     * Space Complexity : O(1)       - no extra space (in-place sort).
     */
    static int findSecondLargestNaive(int[] arr) {
        int n = arr.length;

        // Sort array so largest is at arr[n-1]
        java.util.Arrays.sort(arr);

        // Walk backwards from second-to-last to find the first distinct element
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] != arr[n - 1]) {
                return arr[i]; // First element smaller than the largest
            }
        }

        // No distinct second largest found
        return -1;
    }

    /*
     * [Optimised Approach] Single Linear Scan - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize 'largest' as arr[0] and 'secondLargest' as Integer.MIN_VALUE
     *      (using MIN_VALUE correctly handles all negative arrays).
     *   2. Traverse the array from index 1:
     *        a. If arr[i] > largest  → secondLargest = largest, largest = arr[i].
     *        b. Else if arr[i] < largest AND arr[i] > secondLargest
     *           → update secondLargest = arr[i].
     *        (The extra arr[i] != largest guard ensures we only track DISTINCT elements.)
     *   3. If secondLargest is still Integer.MIN_VALUE, no distinct second largest exists.
     *
     * Why this is interview-preferred:
     *   - One pass, no sorting → O(n) time.
     *   - Only two extra variables → O(1) space.
     *   - Original array is not modified.
     *
     * Time Complexity  : O(n) - single traversal of the array.
     * Space Complexity : O(1) - only two extra integer variables.
     */
    static int findSecondLargestOptimised(int[] arr) {
        // Start with the first element as the largest
        int largest = arr[0];

        // Use MIN_VALUE as sentinel: means "not yet found"
        int secondLargest = Integer.MIN_VALUE;

        // Traverse from index 1
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                // Current element beats the largest → demote largest to second
                secondLargest = largest;
                largest = arr[i];
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                // Current element is between secondLargest and largest → update second
                secondLargest = arr[i];
            }
        }

        // If secondLargest was never updated, no distinct second element exists
        return (secondLargest == Integer.MIN_VALUE) ? -1 : secondLargest;
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 20, 8, 20};

        // Naive Approach - O(n log n) using sorting
        System.out.println("Naive Approach (Sorting):");
        System.out.println("Second Largest: " + findSecondLargestNaive(arr.clone()));

        System.out.println();

        // Optimised Approach - O(n) using single linear scan
        System.out.println("Optimised Approach (Single Pass):");
        System.out.println("Second Largest: " + findSecondLargestOptimised(arr));
    }
}