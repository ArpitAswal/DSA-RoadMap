// Reverse an Array using Recursion

// Given an array of integers arr, reverse the array in-place using a recursive function.
// Reversing an array means exchanging elements from both ends moving towards the center.

// Examples:

// Input: arr[] = [2, 4, 7, 9, 11, 12, 43, 123]
// Output: [123, 43, 12, 11, 9, 7, 4, 2]
// Explanation: The first element 2 swaps with the last element 123, 4 with 43, and so on.

// Input: arr[] = [1]
// Output: [1]
// Explanation: A single element array remains unchanged when reversed.

// Input: arr[] = []
// Output: []
// Explanation: An empty array remains empty.

import java.util.Arrays;

class ReverseArray {

    /*
     * [Alternative Approach] Iterative Two-Pointer - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize two pointers: `left = 0` and `right = arr.length - 1`.
     *   2. While `left < right`:
     *        a. Swap elements at left and right:
     *           `int temp = arr[left]; arr[left] = arr[right]; arr[right] = temp;`
     *        b. Move pointers towards center: `left++`, `right--`.
     *
     * Time Complexity  : O(n) - performs n / 2 swaps.
     * Space Complexity : O(1) - performs swaps in-place with no extra memory.
     */
    static void reverseArrayIterative(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // No reversal needed
        }

        int left = 0;
        int right = arr.length - 1;

        // Loop until pointers meet in the middle
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /*
     * [Optimal/Interview Approach] Recursive Two-Pointer Swap - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. If `arr` is null, or length <= 1, return.
     *   2. Base Case: if `left >= right`, all pairs have been swapped -> return.
     *   3. Swap elements at `arr[left]` and `arr[right]`.
     *   4. Recursive Case: Call `reverseArrayRecursive(arr, left + 1, right - 1)` to swap remaining inner pairs.
     *
     * Why this is interview-preferred:
     *   - Elegant reduction of problem size by 2 in each recursive step.
     *   - Reverses in-place directly on the original array.
     *
     * Time Complexity  : O(n) - performs n / 2 recursive calls (representing n / 2 swaps).
     * Space Complexity : O(n) - call stack space of depth n / 2 (equivalent to O(n) space).
     */
    static void reverseArrayRecursive(int[] arr, int left, int right) {
        // Base Case: stop when pointers cross or meet in the middle
        if (left >= right) {
            return;
        }

        // Swap the elements at left and right pointers
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        // Recursive call with adjusted boundary pointers
        reverseArrayRecursive(arr, left + 1, right - 1);
    }

    public static void main (String[] args) {
        int[] arr1 = {2, 4, 7, 9, 11, 12, 43, 123};
        System.out.println("Original array : " + Arrays.toString(arr1));

        // Test Iterative Version
        int[] iterativeTest = arr1.clone();
        reverseArrayIterative(iterativeTest);
        System.out.println("Reversed (Iterative): " + Arrays.toString(iterativeTest));

        // Test Recursive Version (Interview-Preferred)
        int[] recursiveTest = arr1.clone();
        reverseArrayRecursive(recursiveTest, 0, recursiveTest.length - 1);
        System.out.println("Reversed (Recursive): " + Arrays.toString(recursiveTest));
    }
}