/*
 * Problem Statement: Floor and Ceil in a Sorted Array
 * 
 * Given a sorted array arr[] of size N and an integer X, find the floor and ceiling of X in arr[0..N-1].
 * - The floor of X is the largest element in the array which is smaller than or equal to X.
 * - The ceiling of X is the smallest element in the array greater than or equal to X.
 * If floor or ceil doesn't exist, output -1.
 * 
 * Example 1:
 * Input Format: n = 6, arr[] ={3, 4, 4, 7, 8, 10}, x = 5
 * Output: Floor: 4, Ceil: 7
 * Explanation: The floor of 5 in the array is 4, and the ceiling of 5 in the array is 7.
 * 
 * Example 2:
 * Input Format: n = 6, arr[] ={3, 4, 4, 7, 8, 10}, x = 8
 * Output: Floor: 8, Ceil: 8
 * Explanation: The floor of 8 in the array is 8, and the ceiling of 8 in the array is 8.
 */

class FloorCeilSortArray {

    /*
     * [Naive Approach] Linear Search
     * Since the array is sorted, we can iterate from left to right.
     * To find the floor: The last element we encounter that is <= X is the floor.
     * To find the ceil: The first element we encounter that is >= X is the ceil.
     */
    public static int[] getFloorAndCeilNaive(int[] arr, int x) {
        int floor = -1;
        int ceil = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= x) {
                floor = arr[i];
            }
            if (arr[i] >= x && ceil == -1) {
                ceil = arr[i];
            }
        }
        return new int[]{floor, ceil};
    }

    /*
     * [Optimal Approach] Binary Search
     * We can find the floor and ceil independently using Binary Search in O(log N) time.
     */
    public static int[] getFloorAndCeilOptimal(int[] arr, int x) {
        int f = findFloor(arr, x);
        int c = findCeil(arr, x);
        return new int[]{f, c};
    }

    // Helper Function to find floor using Binary Search
    private static int findFloor(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow
            if (arr[mid] <= x) {
                ans = arr[mid];     // Potential floor found
                low = mid + 1;      // Look for a larger valid floor on the right
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Helper Function to find ceiling using Binary Search
    private static int findCeil(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Prevents overflow
            if (arr[mid] >= x) {
                ans = arr[mid];     // Potential ceil found
                high = mid - 1;     // Look for a smaller valid ceil on the left
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 4, 4, 7, 8, 10};
        int x1 = 5;
        
        int[] arr2 = {3, 4, 4, 7, 8, 10};
        int x2 = 8;
        
        System.out.println("--- Naive Approach ---");
        int[] res1Naive = getFloorAndCeilNaive(arr1, x1);
        int[] res2Naive = getFloorAndCeilNaive(arr2, x2);
        System.out.println("Floor and Ceil of " + x1 + ": [" + res1Naive[0] + ", " + res1Naive[1] + "]");
        System.out.println("Floor and Ceil of " + x2 + ": [" + res2Naive[0] + ", " + res2Naive[1] + "]");

        System.out.println("\n--- Optimal Approach ---");
        int[] res1Optimal = getFloorAndCeilOptimal(arr1, x1);
        int[] res2Optimal = getFloorAndCeilOptimal(arr2, x2);
        System.out.println("Floor and Ceil of " + x1 + ": [" + res1Optimal[0] + ", " + res1Optimal[1] + "]");
        System.out.println("Floor and Ceil of " + x2 + ": [" + res2Optimal[0] + ", " + res2Optimal[1] + "]");
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N), as we traverse the entire array once.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log N). We perform two independent binary searches, 
 *      each taking O(log N) time.
 *    - Space Complexity: O(1), as no extra auxiliary space is used.
 */