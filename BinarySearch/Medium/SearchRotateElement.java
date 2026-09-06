/*
 * Problem Statement 1: Search in Rotated Sorted Array (Distinct Elements)
 * 
 * Given the array nums after the possible rotation and an integer target, return the 
 * index of target if it is in nums, or -1 if it is not in nums.
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 * Output: 4
 * Explanation: 0 is present in the array at index 4.
 * 
 * Problem Statement 2: Search in Rotated Sorted Array II (With Duplicates)
 * 
 * There is an integer array nums sorted in non-decreasing order (not necessarily with 
 * distinct values). Given the array nums after the rotation and an integer target, 
 * return true if target is in nums, or false if it is not in nums.
 * 
 * Example 2:
 * Input: nums = [2, 5, 6, 0, 0, 1, 2], target = 0
 * Output: true
 */

class SearchRotateElement {

    /*
     * [Naive Approach] Linear Search (Works for both distinct and duplicate elements)
     * We can just traverse the entire array and return the index or boolean value if 
     * the target is found.
     */
    private static int searchDistinctNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static boolean searchDuplicateNaive(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }

    /*
     * [Optimal Approach 1] Binary Search for Distinct Elements
     * In a rotated sorted array, one half of the array will always be sorted. 
     * We first identify which half is sorted, and then check if the target lies 
     * within that sorted half. If it does, we search in that half, else we search 
     * in the other half.
     */
    private static int searchDistinctOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            } 
            
            // Check if left half is sorted
            else if (arr[low] <= arr[mid]) {
                // Check if target lies within the sorted left half
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1; // Search right half
                }
            } 
            // Otherwise, the right half must be sorted
            else {
                // Check if target lies within the sorted right half
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1; // Search left half
                }
            }
        }
        return -1;
    }

    /*
     * [Optimal Approach 2] Binary Search for Elements with Duplicates
     * Similar to the distinct elements logic, but with an edge case. If arr[low], 
     * arr[mid], and arr[high] are all equal, we cannot determine which half is sorted.
     * In this case, we simply shrink the search space by incrementing low and decrementing high.
     */
    private static boolean searchDuplicateOptimal(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return true;
            }

            // EDGE CASE: Cannot identify which half is sorted. Shrink the search space.
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
            } 
            // Check if left half is sorted
            else if (arr[low] <= arr[mid]) {
                if (target >= arr[low] && target < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } 
            // Right half is sorted
            else {
                if (target > arr[mid] && target <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arrDistinct = {4, 5, 6, 7, 0, 1, 2};
        int targetDistinct = 0;

        int[] arrDuplicate = {1, 0, 1, 1, 1};
        int targetDuplicate = 0;

        System.out.println("--- Naive Approach ---");
        System.out.println("Index of " + targetDistinct + " in distinct array: " + searchDistinctNaive(arrDistinct, targetDistinct));
        System.out.println("Is " + targetDuplicate + " in duplicate array? " + searchDuplicateNaive(arrDuplicate, targetDuplicate));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Index of " + targetDistinct + " in distinct array: " + searchDistinctOptimal(arrDistinct, targetDistinct));
        System.out.println("Is " + targetDuplicate + " in duplicate array? " + searchDuplicateOptimal(arrDuplicate, targetDuplicate));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N) for both distinct and duplicate array search.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach (Distinct Array):
 *    - Time Complexity: O(log N). At each step, the search space is divided by half.
 *    - Space Complexity: O(1).
 * 
 * 3. Optimal Approach (Duplicate Array):
 *    - Time Complexity: O(log N) in the average case. In the worst case (when all elements 
 *      are the same but not the target), the time complexity degrades to O(N) because we 
 *      reduce the search space by 1 at each step.
 *    - Space Complexity: O(1).
 */