/*
 * Problem Statement: Find A Peak Element
 * 
 * A peak element is an element that is strictly greater than its neighbors.
 * Given an integer array nums, find a peak element, and return its index. If the array 
 * contains multiple peaks, return the index to any of the peaks.
 * You may imagine that nums[-1] = -∞ and nums[n] = -∞.
 * You must write an algorithm that runs in O(log n) time.
 * 
 * Example 1:
 * Input: arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 5, 1}
 * Output: 7
 * Explanation: There is a peak element, 8, that is at index 7.
 * 
 * Example 2:
 * Input: arr[] = {1, 2, 1, 3, 5, 6, 4}
 * Output: 1 or 5
 * Explanation: Your function can return either index 1 where the peak element is 2, 
 * or index 5 where the peak element is 6.
 */

class PeakElement {

    /*
     * [Naive Approach] Linear Search
     * We can traverse the array and check each element if it's strictly greater 
     * than its neighbors. The first element we find that satisfies this condition 
     * is returned.
     */
    private static int findPeakNaive(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            // Check if current element is greater than its left neighbor 
            // (or if it is the first element) and greater than its right neighbor 
            // (or if it is the last element).
            if ((i == 0 || arr[i] > arr[i - 1]) && 
                (i == n - 1 || arr[i] > arr[i + 1])) {
                return i;
            }
        }
        return -1;
    }

    /*
     * [Optimal Approach] Binary Search
     * We can find a peak in O(log N) using Binary Search. 
     * If the mid element is greater than its right neighbor, the peak must lie on 
     * the left side (including mid). Otherwise, the peak must lie on the right side.
     */
    private static int findPeakOptimal(int[] arr) {
        int n = arr.length;

        // Edge case: single element
        if (n == 1) return 0;

        // Edge case: check first and last element to avoid out of bounds in loop
        if (arr[0] >= arr[1]) return 0;
        if (arr[n - 1] >= arr[n - 2]) return n - 1;

        // Start search space excluding 0 and n-1
        int low = 1;
        int high = n - 2;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
                return mid; // Peak found
            } else if (arr[mid] > arr[mid + 1]) {
                // If mid is greater than right, we are on a decreasing slope, 
                // so a peak must exist on the left.
                high = mid;
            } else {
                // If mid is less than or equal to right, we are on an increasing slope, 
                // so a peak must exist on the right.
                low = mid + 1;
            }
        }

        // low and high will converge to a peak
        return low; 
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 5, 1};
        int[] arr2 = {1, 2, 1, 3, 5, 6, 4};

        System.out.println("--- Naive Approach ---");
        int peak1Naive = findPeakNaive(arr1);
        System.out.println("Peak in arr1: " + arr1[peak1Naive] + " at index " + peak1Naive);
        
        int peak2Naive = findPeakNaive(arr2);
        System.out.println("Peak in arr2: " + arr2[peak2Naive] + " at index " + peak2Naive);

        System.out.println("\n--- Optimal Approach ---");
        int peak1Optimal = findPeakOptimal(arr1);
        System.out.println("Peak in arr1: " + arr1[peak1Optimal] + " at index " + peak1Optimal);
        
        int peak2Optimal = findPeakOptimal(arr2);
        System.out.println("Peak in arr2: " + arr2[peak2Optimal] + " at index " + peak2Optimal);
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
 *    - Space Complexity: O(1), as this is an iterative binary search using a few pointers.
 */