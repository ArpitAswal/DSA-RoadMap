/*
 * Problem Statement: Find the Second Smallest Element in an Array
 * 
 * Given an array of integers, find the second smallest distinct element in the array.
 * If there is no such element (e.g., all elements are the same or array has fewer than 
 * 2 elements), return -1.
 * 
 * Example 1:
 * Input: [1, 2, 4, 7, 7, 5]  
 * Output: 2  
 * Explanation: The distinct elements are 1, 2, 4, 5, 7. The second smallest element is 2.
 * 
 * Example 2:
 * Input: [1, 1, 1]
 * Output: -1
 * Explanation: There is no distinct second smallest element.
 */

class Smallest {

    /*
     * [Naive Approach] Two Passes
     * 
     * Pass 1: Find the absolute smallest element.
     * Pass 2: Find the smallest element that is strictly greater than the first one.
     */
    int findSecondSmallestNaive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        // Pass 1: Find the smallest element
        int smallest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }

        // Pass 2: Find the smallest element strictly greater than 'smallest'
        // Using Integer.MAX_VALUE as a sentinel value
        int secondSmallest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > smallest && arr[i] < secondSmallest) {
                secondSmallest = arr[i];
            }
        }

        // If secondSmallest is still MAX_VALUE, it means no distinct second smallest was found
        if (secondSmallest == Integer.MAX_VALUE) {
            return -1;
        }
        return secondSmallest;
    }

    /*
     * [Optimal Approach] Single Pass
     * 
     * Traverse the array once, keeping track of both the smallest and second smallest
     * elements encountered so far.
     */
    int findSecondSmallestOptimal(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < smallest) {
                // Current element is strictly smaller than 'smallest'
                // Demote 'smallest' to 'secondSmallest'
                secondSmallest = smallest;
                smallest = arr[i];
            } else if (arr[i] < secondSmallest && arr[i] != smallest) {
                // Current element is between 'smallest' and 'secondSmallest'
                secondSmallest = arr[i];
            }
        }

        if (secondSmallest == Integer.MAX_VALUE) {
            return -1;
        }
        return secondSmallest;
    }
}

public class SecondSmallest {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 7, 7, 5};
        int[] arr2 = {1, 1, 1};

        Smallest obj = new Smallest();

        System.out.println("--- Naive Approach ---");
        System.out.println("Second smallest in arr1: " + obj.findSecondSmallestNaive(arr1));
        System.out.println("Second smallest in arr2: " + obj.findSecondSmallestNaive(arr2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Second smallest in arr1: " + obj.findSecondSmallestOptimal(arr1));
        System.out.println("Second smallest in arr2: " + obj.findSecondSmallestOptimal(arr2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N) because we make exactly two passes through the array 
 *      (O(N) + O(N) = O(N)).
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N) because we make a single pass through the array.
 *    - Space Complexity: O(1), no extra space required.
 */