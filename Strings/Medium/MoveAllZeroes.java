// Move all Zeros to End of Array

// Given an array of integers arr[], move all the zeros to the end of the array while maintaining the relative order of all non-zero elements.

// Examples: 

// Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
// Output: [1, 2, 4, 3, 5, 0, 0, 0]
// Explanation: There are three 0s that are moved to the end.

// Input: arr[] = [10, 20, 30]
// Output: [10, 20, 30]
// Explanation: No change in array as there are no 0s.

class MoveAllZeroes {

    /*
     * [Naive Approach] Using Temporary Array - O(n) Time and O(n) Space
     * The idea is to use a temporary array of the same size, copy all non-zero
     * elements into it, fill the remaining positions with zeros, and then copy the
     * temporary array back to the original array.
     */
    static void pushZerosFirstApproach(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];

        // to keep track of the index in temp[]
        int j = 0;

        // Copy non-zero elements to temp[]
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0)
                temp[j++] = arr[i];
        }

        // Fill remaining positions in temp[] with zeros
        while (j < n)
            temp[j++] = 0;

        // Copy all the elements from temp[] to arr[]
        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }

    /*
     * [Better Approach] Two Traversals-O(n) Time and O(1) space
     * The idea is to move all the zeros to the end of the array while maintaining
     * the relative order of non-zero elements using two traversals.
     * Traverse the array once to move all non-zero elements to the front while
     * maintaining order, then traverse the remaining positions and fill them with
     * zeros.
     */

    static void pushZerosSecondApproach(int[] arr) {

        int count = 0;

        // If the element is non-zero, replace the element
        // at index 'count' with this element and increment
        // count
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0)
                arr[count++] = arr[i];
        }

        // Now all non-zero elements have been shifted to
        // the front. Make all elements 0 from count to end.
        while (count < arr.length)
            arr[count++] = 0;
    }

    /*
     * [Expected Approach] One Traversal-O(n) Time and O(1) space
     * The idea is similar to the previous approach where we took a pointer, say
     * count to track where the next non-zero element should be placed.
     * However, on encountering a non-zero element, instead of directly placing the
     * non-zero element at arr[count], we will swap the non-zero element with
     * arr[count].
     * This will ensure that if there is any zero present at arr[count], it is
     * pushed towards the end of array and is not overwritten.
     */

    static void pushZerosThirdApproach(int[] arr) {

        // Pointer to track the position
        // for next non-zero element
        int count = 0;

        for (int i = 0; i < arr.length; i++) {

            // If the current element is non-zero
            if (arr[i] != 0) {

                // Swap the current element with
                // the 0 at index 'count'
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;

                // Move 'count' pointer to
                // the next position
                count++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 0, 4, 3, 0, 5, 0 };

        // Naive Approach
        pushZerosFirstApproach(arr);

        System.out.println("First Approach: ");

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();

        // Two Traversals Approach
        pushZerosSecondApproach(arr);

        System.out.println("Second Approach: ");

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();

        // One Traversals Approach
        pushZerosThirdApproach(arr);

        System.out.println("Third Approach: ");

        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();

    }
}