// Rotate an Array by d - Counterclockwise or Left

// Given an array of integers arr[] of size n, the task is to rotate the array elements to the left by d positions.

// Examples:

// Input: arr[] = {1, 2, 3, 4, 5, 6}, d = 2
// Output: {3, 4, 5, 6, 1, 2}
// Explanation: After first left rotation, arr[] becomes {2, 3, 4, 5, 6, 1} and after the second rotation, arr[] becomes {3, 4, 5, 6, 1, 2}

// Input: arr[] = {1, 2, 3}, d = 4
// Output: {2, 3, 1}
// Explanation: The array is rotated as follows:

// After first left rotation, arr[] = {2, 3, 1}
// After second left rotation, arr[] = {3, 1, 2}
// After third left rotation, arr[] = {1, 2, 3}
// After fourth left rotation, arr[] = {2, 3, 1}

class RotateAnArray {

    /*
     * [Naive Approach] Rotate one by one - O(n * d) Time and O(1) Space
     * In each iteration, shift the elements by one position to the left in a
     * circular fashion (the first element becomes the last). Perform this operation
     * d times to rotate the elements to the left by d positions.
     */

    // Function to left rotate array by d positions
    static void rotateArr1(int[] arr, int d) {
        int n = arr.length;

        // Repeat the rotation d times
        for (int i = 0; i < d; i++) {

            // Left rotate the array by one position
            int first = arr[0];
            for (int j = 0; j < n - 1; j++) {
                arr[j] = arr[j + 1];
            }
            arr[n - 1] = first;
        }
    }

    /*
     * [Better Approach] Using Temporary Array - O(n) Time and O(n) Space
     * 
     * This problem can be solved using the below idea:
     * 
     * The idea is to use a temporary array of size n, where n is the length of the
     * original array. If we left rotate the array by d positions, the last n - d
     * elements will be at the front and the first d elements will be at the end.
     * Copy the last (n - d) elements of original array into the first n - d
     * positions of temporary array.
     * Then copy the first d elements of the original array to the end of temporary
     * array.
     * Finally, copy all the elements of temporary array back into the original
     * array.
     * 
     */

    // Function to rotate array
    static void rotateArr2(int[] arr, int d) {
        int n = arr.length;

        // Handle case when d > n
        d %= n;

        // Storing rotated version of array
        int[] temp = new int[n];

        // Copy last n - d elements to the front of temp
        for (int i = 0; i < n - d; i++)
            temp[i] = arr[d + i];

        // Copy the first d elements to the back of temp
        for (int i = 0; i < d; i++)
            temp[n - d + i] = arr[i];

        // Copying the elements of temp in arr
        // to get the final rotated array
        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }

    /*
     * Using Reversal Algorithm - O(n) Time and O(1) Space
     * 
     * The idea is based on the observation that if we left rotate the array by d
     * positions, the last (n - d) elements will be at the front and the first d
     * elements will be at the end.
     * 
     * Reverse the subarray containing the first d elements of the array.
     * Reverse the subarray containing the last (n - d) elements of the array.
     * Finally, reverse all the elements of the array.
     * 
     */

    // Function to rotate an array by d elements to the left
    static void rotateArr3(int[] arr, int d) {
        int n = arr.length;

        // Handle the case where d > size of array
        d %= n;

        // Reverse the first d elements
        reverse(arr, 0, d - 1);

        // Reverse the remaining n-d elements
        reverse(arr, d, n - 1);

        // Reverse the entire array
        reverse(arr, 0, n - 1);
    }

    // Function to reverse a portion of the array
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        int d = 2;

        // Native Approach
        rotateArr1(arr, d);

        System.out.println("Native Approach");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        rotateArr2(arr, d);

        System.out.println("Temp Array Approach");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

        rotateArr3(arr, d);

        System.out.println("Reversal Algorithm Approach");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

/*
 * Naive Approach:
 * Time Complexity: O(n*d), the outer loop runs d times, and within each
 * iteration, the inner loop shifts all n elements of the array by one position,
 * resulting in a total of n*d operations.
 * Auxiliary Space: O(1)
 * 
 * Temp Array Approach:
 * Time Complexity: O(n), as we are visiting each element only twice.
 * Auxiliary Space: O(n), as we are using an additional temporary array.
 * 
 * Reversal Algorithm Approach:
 * Time complexity: O(n), as we are visiting each element exactly twice.
 * Auxiliary Space: O(1)
 */