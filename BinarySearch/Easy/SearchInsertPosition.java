/*
 * Problem Statement: Search Insert Position
 * 
 * Given a sorted array of distinct integers and a target value, return the index if the 
 * target is found. If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [1, 2, 4, 7], target = 6
 * Output: 3
 * Explanation: 6 is not found in the array. It should be inserted between 4 and 7, 
 * which is at index 3.
 * 
 * Example 2:
 * Input: nums = [1, 2, 4, 7], target = 2
 * Output: 1
 * Explanation: 2 is found at index 1.
 */

class SearchInsertPosition {

    /*
     * [Naive Approach] Linear Search
     * Traverse the array linearly. The insert position is the index of the first 
     * element that is greater than or equal to the target.
     */
    private static int findPositionNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
                return i;
            }
        }
        return arr.length; // If target is strictly greater than all elements
    }

    /*
     * [Optimal Approach] Binary Search
     * This problem is equivalent to finding the Lower Bound. We can use Binary Search 
     * to find the first index where the element is greater than or equal to the target 
     * in O(log N) time.
     */
    private static int findPositionOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int pos = arr.length; // Default to length if target is greater than all elements

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (arr[mid] >= target) {
                pos = mid;         // Potential insert position
                high = mid - 1;    // Try to find a smaller index on the left
            } else {
                low = mid + 1;     // Current element is strictly smaller, search on the right
            } 
        }
        return pos;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 4, 7};
        int target1 = 6;
        
        int[] arr2 = {1, 2, 4, 7};
        int target2 = 2;
        
        System.out.println("--- Naive Approach ---");
        System.out.println("Insert Position of " + target1 + " in arr1: " + findPositionNaive(arr1, target1)); 
        System.out.println("Insert Position of " + target2 + " in arr2: " + findPositionNaive(arr2, target2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Insert Position of " + target1 + " in arr1: " + findPositionOptimal(arr1, target1)); 
        System.out.println("Insert Position of " + target2 + " in arr2: " + findPositionOptimal(arr2, target2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we traverse the entire array in the worst case.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). Using binary search, the search space is divided 
 *      by 2 at each step.
 *    - Space Complexity: O(1), as no extra space is used in the iterative binary search.
 */