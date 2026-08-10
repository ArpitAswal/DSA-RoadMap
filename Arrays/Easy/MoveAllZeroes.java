// Move all Zeros to End of Array

// Given an array of integers arr[], move all the zeros to the end of the array while
// maintaining the relative order of all non-zero elements. The modification should be in-place.

// Examples:

// Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
// Output: [1, 2, 4, 3, 5, 0, 0, 0]
// Explanation: There are three zeros (0s) in the array. They are moved to the end while 
// elements [1, 2, 4, 3, 5] keep their original relative order.

// Input: arr[] = [10, 20, 30]
// Output: [10, 20, 30]
// Explanation: No zeros present, so the array remains unchanged.

// Input: arr[] = [0, 0, 1]
// Output: [1, 0, 0]
// Explanation: Two leading zeros are pushed to the end, non-zero element 1 moves to index 0.

import java.util.Arrays;

class MoveAllZeroes {

    /*
     * [Naive Approach] Using Temporary Array - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Create a temporary array `temp` of the same length n.
     *   2. Traverse the original array `arr` with index pointer `i`.
     *   3. If arr[i] != 0, copy arr[i] into temp at index `j` and increment `j`.
     *   4. Fill remaining positions in `temp` from index `j` to n - 1 with 0s.
     *   5. Copy all elements from `temp` back to original array `arr`.
     *
     * Drawback: Uses extra O(n) auxiliary space for the temporary array.
     *
     * Time Complexity  : O(n) - two traversals of size n (one to filter non-zeros, one to copy back).
     * Space Complexity : O(n) - extra space allocated for temporary array temp[n].
     */
    static void pushZerosFirstApproach(int[] arr) {
        int n = arr.length;      // Get array length
        int[] temp = new int[n]; // Create temporary array of size n

        int j = 0; // Pointer to track insertion index in temp array

        // Step 1: Copy all non-zero elements into temp
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                temp[j] = arr[i]; // Store non-zero element at current temp index j
                j++;              // Increment temp pointer
            }
        }

        // Step 2: Fill remaining positions in temp from index j to n - 1 with zeros
        while (j < n) {
            temp[j] = 0; // Assign zero
            j++;         // Increment pointer
        }

        // Step 3: Copy all elements from temp back to original array arr
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i]; // Overwrite original array
        }
    }

    /*
     * [Better Approach] Two Traversals (In-Place) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain a index pointer 'count' starting at 0 (tracks position for next non-zero).
     *   2. First Pass: Traverse array `arr`. If arr[i] != 0, assign arr[count] = arr[i] and increment `count`.
     *   3. Second Pass: Loop from index `count` up to n - 1 and set arr[count] = 0.
     *
     * Time Complexity  : O(n) - two passes: first pass moves non-zeros, second pass fills remaining with 0.
     * Space Complexity : O(1) - in-place modification; uses only one integer count variable.
     */
    static void pushZerosSecondApproach(int[] arr) {
        int count = 0; // Pointer for next non-zero element index position

        // First Pass: Shift all non-zero elements to the front
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[count] = arr[i]; // Place non-zero element at index count
                count++;             // Move count pointer forward
            }
        }

        // Second Pass: Fill all remaining positions from count to array length with 0
        while (count < arr.length) {
            arr[count] = 0; // Assign zero to trailing positions
            count++;        // Increment count pointer
        }
    }

    /*
     * [Optimal / Interview Approach] One Traversal In-Place Swapping - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain a pointer 'count' starting at index 0 (tracks next position for non-zero element).
     *   2. Single Pass: Traverse array `arr` with index `i` from 0 to n - 1:
     *        - When a non-zero element arr[i] != 0 is encountered:
     *        - Swap arr[i] with arr[count].
     *        - Increment `count`.
     *   3. Swapping ensures non-zeros move front while zeros automatically shift towards the back in 1 pass.
     *
     * Why this is interview-preferred:
     *   - Modifies array completely in-place in a SINGLE traversal O(n).
     *   - Minimizes total memory writes compared to two-pass approach.
     *
     * Time Complexity  : O(n) - single pass over array of size n.
     * Space Complexity : O(1) - constant auxiliary space used.
     */
    static void pushZerosThirdApproach(int[] arr) {
        int count = 0; // Pointer tracking position for next non-zero element

        // Single pass over array
        for (int i = 0; i < arr.length; i++) {
            // If element is non-zero
            if (arr[i] != 0) {
                // Swap arr[i] with arr[count]
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;

                // Move count pointer to next position
                count++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 0, 4, 3, 0, 5, 0 };
        System.out.println("Original Array: " + Arrays.toString(arr1));

        // 1. Naive Approach - Temporary Array
        int[] test1 = arr1.clone();
        pushZerosFirstApproach(test1);
        System.out.println("First Approach (Temp Array)    : " + Arrays.toString(test1));

        // 2. Two Traversals Approach
        int[] test2 = arr1.clone();
        pushZerosSecondApproach(test2);
        System.out.println("Second Approach (Two Traversals): " + Arrays.toString(test2));

        // 3. One Traversal Swapping Approach (Interview-Preferred)
        int[] test3 = arr1.clone();
        pushZerosThirdApproach(test3);
        System.out.println("Third Approach (One Traversal)  : " + Arrays.toString(test3));
    }
}