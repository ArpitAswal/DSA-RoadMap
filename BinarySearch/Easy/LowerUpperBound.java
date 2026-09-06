/*
 * Problem Statement: Lower and Upper Bound in a Sorted Array
 * 
 * Given a sorted array of integers and a target value x:
 * 
 * Lower Bound: Finds the first/smallest index i where arr[i] >= target.
 * Upper Bound: Finds the first/smallest index i where arr[i] > target (strictly greater).
 * If no such index is found, return the length of the array (N).
 * 
 * Example 1 (Lower Bound):
 * Input Format: N = 4, arr[] = {1, 2, 2, 3}, x = 2
 * Output: 1
 * Explanation: Index 1 is the smallest index such that arr[1] >= 2.
 * 
 * Example 2 (Upper Bound):
 * Input Format: N = 4, arr[] = {1, 2, 2, 3}, x = 2
 * Output: 3
 * Explanation: Index 3 is the smallest index such that arr[3] > 2.
 */

class LowerUpperBound {

    /*
     * [Naive Approach] Linear Search for Lower Bound
     * Traverse the array linearly from start to end and return the first index 
     * where the element is greater than or equal to the target.
     */
    private static int lowerBoundNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return arr.length; // If no element is >= target
    }

    /*
     * [Naive Approach] Linear Search for Upper Bound
     * Traverse the array linearly from start to end and return the first index 
     * where the element is strictly greater than the target.
     */
    private static int upperBoundNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > target) {
                return i;
            }
        }
        return arr.length; // If no element is > target
    }

    /*
     * [Optimal Approach] Binary Search for Lower Bound
     * Use Binary Search to find the first element that is >= target in O(log N) time.
     */
    private static int lowerBoundOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int result = arr.length; // Default to array length if not found

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                result = mid;       // Potential answer, but look for a smaller index
                high = mid - 1;
            } else {
                low = mid + 1;      // Current element is less than target
            }
        }
        return result;
    }

    /*
     * [Optimal Approach] Binary Search for Upper Bound
     * Use Binary Search to find the first element that is > target in O(log N) time.
     */
    private static int upperBoundOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int result = arr.length; // Default to array length if not found

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > target) {
                result = mid;       // Potential answer, but look for a smaller index
                high = mid - 1;
            } else {
                low = mid + 1;      // Current element is <= target
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 4, 6, 8, 10, 12};
        int target1 = 8;

        int[] arr2 = {1, 2, 2, 3};
        int target2 = 2;

        System.out.println("--- Naive Approach ---");
        System.out.println("Lower Bound of " + target1 + " in arr1: " + lowerBoundNaive(arr1, target1));
        System.out.println("Upper Bound of " + target1 + " in arr1: " + upperBoundNaive(arr1, target1));
        System.out.println("Lower Bound of " + target2 + " in arr2: " + lowerBoundNaive(arr2, target2));
        System.out.println("Upper Bound of " + target2 + " in arr2: " + upperBoundNaive(arr2, target2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Lower Bound of " + target1 + " in arr1: " + lowerBoundOptimal(arr1, target1));
        System.out.println("Upper Bound of " + target1 + " in arr1: " + upperBoundOptimal(arr1, target1));
        System.out.println("Lower Bound of " + target2 + " in arr2: " + lowerBoundOptimal(arr2, target2));
        System.out.println("Upper Bound of " + target2 + " in arr2: " + upperBoundOptimal(arr2, target2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N) for both lower and upper bounds, as we scan the array.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N) for both lower and upper bounds due to binary search.
 *    - Space Complexity: O(1), as it's an iterative binary search.
 */