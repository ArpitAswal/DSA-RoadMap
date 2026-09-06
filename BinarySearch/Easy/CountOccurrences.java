/*
 * Problem Statement: Count occurrences of a number in a sorted array with duplicates
 * 
 * Given a sorted array of integers containing duplicates, find the number of 
 * occurrences of a target element X.
 * 
 * Example 1:
 * Input: N = 7,  X = 3 , array[] = {2, 2 , 3 , 3 , 3 , 3 , 4}
 * Output: 4
 * Explanation: 3 is occurring 4 times in the given array so it is our answer.
 * 
 * Example 2:
 * Input: N = 7, X = 9, array[] = {1, 1, 2, 2, 2, 2, 3}
 * Output: 0
 * Explanation: 9 is not present in the array.
 */

class CountOccurrences {

    /*
     * [Naive Approach] Linear Search
     * Since we just need to count occurrences, we can iterate over the entire array 
     * and increment a counter whenever we find the target element.
     */
    private static int countNaive(int[] arr, int target) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                count++;
            }
        }
        return count;
    }

    /*
     * [Optimal Approach] Binary Search for First and Last Occurrences
     * We can find the exact boundaries of the target element using Binary Search.
     * 1. Find the first occurrence (lower bound).
     * 2. Find the last occurrence (upper bound).
     * The total count will simply be (last_occurrence - first_occurrence + 1).
     */
    private static int countOptimal(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        
        // If the element is not found, return 0
        if (first == -1) {
            return 0;
        }
        
        int last = findLastOccurrence(arr, target);
        
        return last - first + 1;
    }

    // Helper method to find the first occurrence using Binary Search
    private static int findFirstOccurrence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int first = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                first = mid;      // Potentially our first occurrence
                high = mid - 1;   // Look on the left side for an earlier occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    // Helper method to find the last occurrence using Binary Search
    private static int findLastOccurrence(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int last = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                last = mid;       // Potentially our last occurrence
                low = mid + 1;    // Look on the right side for a later occurrence
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 2, 3, 3, 3, 3, 4};
        int target1 = 3;
        
        int[] arr2 = {1, 1, 2, 2, 2, 2, 3};
        int target2 = 9;

        System.out.println("--- Naive Approach ---");
        System.out.println("Occurrences of " + target1 + ": " + countNaive(arr1, target1));
        System.out.println("Occurrences of " + target2 + ": " + countNaive(arr2, target2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Occurrences of " + target1 + ": " + countOptimal(arr1, target1));
        System.out.println("Occurrences of " + target2 + ": " + countOptimal(arr2, target2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we potentially scan the whole array.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). Finding the first occurrence takes O(log N) and 
 *      finding the last occurrence takes O(log N). Total time is strictly O(log N).
 *    - Space Complexity: O(1), as we only use a few integer variables.
 */