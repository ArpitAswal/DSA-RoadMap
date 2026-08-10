/// Reverse an array arr[]. Reversing an array means rearranging the elements such that the first element becomes the last, the second element becomes second last and so on.

/// Examples:

/// Input: arr[] = [1, 4, 3, 2, 6, 5]  
/// Output:  [5, 6, 2, 3, 4, 1]
/// Explanation: The first element 1 moves to last position, the second element 4 moves to second-last and so on.

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseArray {

    public static void main(String[] args) {
        int[] original = { 1, 2, 3, 4, 5 };

        // 1. Using a Temporary Array
        int[] arr1 = original.clone();
        reverseExtraSpace(arr1);
        System.out.println("Extra Space:  " + Arrays.toString(arr1));

        // 2. Iterative Two-Pointer (In-place)
        int[] arr2 = original.clone();
        reverseIterative(arr2);
        System.out.println("Iterative:   " + Arrays.toString(arr2));

        // 3. Recursive Approach
        int[] arr3 = original.clone();
        reverseRecursive(arr3, 0, arr3.length - 1);
        System.out.println("Recursive:   " + Arrays.toString(arr3));

        // 4. Using Library Methods
        int[] arr4 = original.clone();
        reverseLibrary(arr4);
        System.out.println("Library:     " + Arrays.toString(arr4));

        // 5. Using Swapping half items
        int[] arr5 = original.clone();
        swapHalfArray(arr5);
        System.out.println("Half Swap:   " + Arrays.toString(arr5));
    }

    /**
     * Approach 1: Using a Temporary Array
     * Logic: Create a new array and fill it with elements from the end of the
     * original.
     * Time Complexity: O(n) - Single pass to fill the new array.
     * Space Complexity: O(n) - Requires an extra array of size n.
     */
    public static void reverseExtraSpace(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[n - 1 - i];
        }
        System.arraycopy(temp, 0, arr, 0, n);
    }

    /**
     * Approach 2: Iterative Two-Pointer (In-place)
     * Logic: Maintain 'start' and 'end' pointers and swap elements until they meet.
     * Time Complexity: O(n) - Process half the array (n/2 swaps).
     * Space Complexity: O(1) - No extra space used.
     */
    public static void reverseIterative(int[] arr) {
        int start = 0, end = arr.length - 1;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Approach 3: Recursive Approach
     * Logic: Swap the first and last elements, then call the function for the
     * remaining sub-array.
     * Time Complexity: O(n) - n/2 recursive calls.
     * Space Complexity: O(n) - Implicit stack space for recursion.
     */
    public static void reverseRecursive(int[] arr, int start, int end) {
        if (start >= end)
            return;
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        reverseRecursive(arr, start + 1, end - 1);
    }

    /**
     * Approach 4: Using Library Methods (Collections.reverse)
     * Logic: Convert primitive array to a List and use built-in utility.
     * Time Complexity: O(n) - Conversion and reversal both take linear time.
     * Space Complexity: O(n) - Boxed integers and List creation require extra
     * memory.
     */
    public static void reverseLibrary(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.reverse(list);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
    }

    /*
     * Approach 5: By Swapping Elements - O(n) Time and O(1) Space
     * Logic:The idea is to iterate over the first half of the array and swap each
     * element with its corresponding element from the end. So, while iterating over
     * the first half, any element at index i is swapped with the element at index
     * (n - i - 1).
     * Time Complexity: O(n), the loop runs through half of the array, so it's
     * linear with respect to the array size.
     * Auxiliary Space: O(1), no extra space is required, therefore we are reversing
     * the array in-place.
     */

    public static void swapHalfArray(int[] arr) {
        int n = arr.length;

        // Iterate over the first half
        // and for every index i, swap
        // arr[i] with arr[n - i - 1]
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }
}