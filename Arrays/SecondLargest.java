/// Given an array of positive integers arr[], return the second largest element from the array. If the second largest element doesn't exist then return -1.

///Note: The second largest element should not be equal to the largest element.

/* Examples:

Input: arr[] = [12, 35, 1, 10, 34, 1]
Output: 34
Explanation: The largest element of the array is 35 and the second largest element is 34. */

import java.util.Arrays;

class SecondLargest {

    /*
     * [Naive Approach] Using Sorting
     * 
     * The idea is to sort the array in non-decreasing order. Now, we know that the
     * largest element will be at index n - 1. So, starting from index (n - 2),
     * traverse the remaining array in reverse order. As soon as we encounter an
     * element which is not equal to the largest element, return it as the second
     * largest element in the array. If all the elements are equal to the largest
     * element, return -1.
     */

    int getSecondLargest1(int[] arr) {
        int n = arr.length;

        // Sort the array in non-decreasing order
        Arrays.sort(arr);

        // start from second last element as last element is the largest
        for (int i = n - 2; i >= 0; i--) {

            // return the first element which is not equal to the
            // largest element
            if (arr[i] != arr[n - 1]) {
                return arr[i];
            }
        }

        // If no second largest element was found, return -1
        return -1;
    }

    /*
     * [Better Approach] Two Pass Search
     * 
     * The approach is to traverse the array twice. In the first traversal, find the
     * maximum element. In the second traversal, find the maximum element ignoring
     * the one we found in the first traversal.
     */

    int getSecondLargest2(int[] arr) {
        int n = arr.length;

        int largest = -1, secondLargest = -1;

        // Finding the largest element
        for (int i = 0; i < n; i++) {
            if (arr[i] > largest)
                largest = arr[i];
        }

        // Finding the second largest element
        for (int i = 0; i < n; i++) {

            // Update second largest if the current element is greater
            // than second largest and not equal to the largest
            if (arr[i] > secondLargest && arr[i] != largest) {
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    /*
     * [Expected Approach] One Pass Search
     * 
     * The idea is to keep track of the largest and second largest element while
     * traversing the array. Initialize largest and secondLargest with -1. Now, for
     * any index i,
     * 
     * . If arr[i] > largest, update secondLargest with largest and largest with
     * arr[i].
     * . Else If arr[i] < largest and arr[i] > secondLargest, update secondLargest
     * with arr[i].
     * 
     */

    int getSecondLargest3(int[] arr) {
        if (arr.length < 2) {
            return -1;
        }

        int lar = arr[0];
        int sec = -1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > lar) {
                sec = lar;
                lar = arr[i];
            } else if (arr[i] >= sec && arr[i] < lar) {
                sec = arr[i];
            }
        }

        return sec;
    }

    public static void main(String[] args) {
        int[] arr = { 12, 35, 1, 10, 35, 1 };
        SecondLargest sl = new SecondLargest();
        System.out.println("The second largest number is: " + sl.getSecondLargest1(arr));
        System.out.println("The second largest number is: " + sl.getSecondLargest2(arr));
        System.out.println("The second largest number is: " + sl.getSecondLargest3(arr));

    }

    /*
     * Naive Approach
     * 
     * Time Complexity: O(n*log(n)), as sorting the array takes O(n*log(n)) time and
     * traversing the array can take O(n) time in the worst case, so total time
     * complexity = (n*log(n) + n) = O(n*log(n)).
     * Auxiliary space: O(1), as no extra space is required.
     * 
     * Two Pass Search
     * 
     * Time Complexity: O(2*n) = O(n), as we are traversing the array two times.
     * Auxiliary space: O(1), as no extra space is required.
     * 
     * 
     * One Pass Search
     * 
     * Time Complexity: O(n), as we are traversing the array only once.
     * Auxiliary space: O(1)
     */

}