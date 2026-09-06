/*
 * Problem Statement: Leaders in an Array
 * 
 * Given an array, print all the elements which are leaders. A leader is an element 
 * that is strictly greater than all the elements to its right side.
 * Note: The rightmost element is always a leader.
 * 
 * Example:
 * Input: arr = [10, 22, 12, 3, 0, 6]  
 * Output: 22 12 6  
 * Explanation:
 * 6 is a leader because there are no elements after it.  
 * 12 is strictly greater than all elements to its right (3, 0, 6).
 * 22 is strictly greater than all elements to its right (12, 3, 0, 6).
 * 10 is not a leader because 22 is to its right.
 */

class LeadersArray {

    /*
     * [Naive Approach]
     * For every element, check if it is strictly greater than all the elements to its right.
     * Use two nested loops to verify this condition.
     */
    static int[] findLeadersNaive(int[] arr) {
        int n = arr.length;
        
        // At most, all elements can be leaders, so max size is n
        int[] temp = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            boolean isLeader = true;
            for (int j = i + 1; j < n; j++) {
                // If any element to the right is greater or equal, it's not a leader
                if (arr[j] >= arr[i]) {
                    isLeader = false;
                    break;
                }
            }
            if (isLeader) {
                temp[count++] = arr[i];
            }
        }

        // Create the exact sized result array
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    /*
     * [Optimal Approach]
     * Scan the array from right to left. The rightmost element is always a leader.
     * Keep track of the maximum element seen so far from the right. If the current 
     * element is strictly greater than the maximum seen so far, it's a leader.
     */
    static int[] findLeadersOptimal(int[] arr) {
        int n = arr.length;
        
        // At most, all elements can be leaders, so max size is n
        int[] temp = new int[n];
        int count = 0;

        // The rightmost element is always a leader
        int maxFromRight = arr[n - 1];
        temp[count++] = maxFromRight;

        // Traverse from second last element to the left
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                maxFromRight = arr[i];
                temp[count++] = maxFromRight;
            }
        }

        // The elements in temp are stored from right to left, we need to reverse them
        // to maintain the original relative order (left to right).
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            // Reversing manually
            result[i] = temp[count - 1 - i];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 12, 3, 0, 6};

        System.out.println("--- Naive Approach ---");
        int[] naiveLeaders = findLeadersNaive(arr);
        System.out.print("The leaders are: ");
        printArray(naiveLeaders);

        System.out.println("\n--- Optimal Approach ---");
        int[] optimalLeaders = findLeadersOptimal(arr);
        System.out.print("The leaders are: ");
        printArray(optimalLeaders);
    }
    
    // Helper function to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), as we check every element against all elements to its right.
 *    - Space Complexity: O(N), for storing the result array in the worst case.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N), as we traverse the array exactly once from right to left, 
 *      plus an O(N) traversal to reverse the found leaders. Total Time = O(N).
 *    - Space Complexity: O(N), for storing the temporary list of leaders and the result 
 *      array in the worst case (where the array is sorted in descending order).
 */