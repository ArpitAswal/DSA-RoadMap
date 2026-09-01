/*
 * Problem Statement: Union of Two Sorted Arrays
 * 
 * Given two sorted arrays, arr1 and arr2, return a new array representing their union.
 * The union of two arrays can be defined as the common and distinct elements in the 
 * two arrays. The output array should be in sorted order and contain only distinct elements.
 * 
 * Example 1:
 * Input: 
 *   arr1[] = {1, 2, 3, 4, 5}
 *   arr2[] = {2, 3, 4, 4, 5}
 * Output: {1, 2, 3, 4, 5}
 * 
 * Example 2:
 * Input:
 *   arr1[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
 *   arr2[] = {2, 3, 4, 4, 5, 11, 12}
 * Output: {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}
 */

class Union {

    /*
     * [Naive Approach] Iterative Search
     * Create a result array of size n + m. For each element in arr1, check if it 
     * is already in the result array. If not, add it. Do the same for arr2.
     * Finally, copy the distinct elements to an appropriately sized array.
     */
    int[] findUnionNaive(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;
        
        // The maximum possible size of the union is n + m
        int[] temp = new int[n + m];
        int k = 0;

        // Add elements from first array
        for (int i = 0; i < n; i++) {
            boolean exists = false;
            // Check if arr1[i] is already in temp
            for (int j = 0; j < k; j++) {
                if (temp[j] == arr1[i]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                temp[k++] = arr1[i];
            }
        }

        // Add elements from second array
        for (int i = 0; i < m; i++) {
            boolean exists = false;
            // Check if arr2[i] is already in temp
            for (int j = 0; j < k; j++) {
                if (temp[j] == arr2[i]) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                temp[k++] = arr2[i];
            }
        }

        // We must sort the naive result because elements from arr2 might be smaller
        // However, avoiding built-in Arrays.sort as per requirements. We will use a simple bubble sort.
        for (int i = 0; i < k - 1; i++) {
            for (int j = 0; j < k - 1 - i; j++) {
                if (temp[j] > temp[j + 1]) {
                    int t = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = t;
                }
            }
        }

        // Copy exactly k elements into the final result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    /*
     * [Optimal Approach] Two Pointers
     * Since the arrays are already sorted, we can use two pointers to traverse both 
     * arrays simultaneously. Compare elements, add the smaller one, and advance the 
     * respective pointer. Handle duplicates by checking the last added element.
     */
    int[] findUnionOptimal(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        // The maximum possible size of the union is n + m
        int[] temp = new int[n + m];
        
        int i = 0; // Pointer for arr1
        int j = 0; // Pointer for arr2
        int k = 0; // Pointer for temp array

        // Traverse both arrays
        while (i < n && j < m) {
            // If arr1 has the smaller element
            if (arr1[i] <= arr2[j]) {
                // Add if it's the first element or different from the last added
                if (k == 0 || temp[k - 1] != arr1[i]) {
                    temp[k++] = arr1[i];
                }
                i++;
            } 
            // If arr2 has the smaller element
            else {
                if (k == 0 || temp[k - 1] != arr2[j]) {
                    temp[k++] = arr2[j];
                }
                j++;
            }
        }

        // If elements are left in arr1
        while (i < n) {
            if (k == 0 || temp[k - 1] != arr1[i]) {
                temp[k++] = arr1[i];
            }
            i++;
        }

        // If elements are left in arr2
        while (j < m) {
            if (k == 0 || temp[k - 1] != arr2[j]) {
                temp[k++] = arr2[j];
            }
            j++;
        }

        // Extract the exact sized array
        int[] result = new int[k];
        for (int idx = 0; idx < k; idx++) {
            result[idx] = temp[idx];
        }
        return result;
    }
}

public class UnionOfTwoArrays {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = {2, 3, 4, 4, 5, 11, 12};
        
        Union obj = new Union();
        
        System.out.println("--- Naive Approach ---");
        int[] naiveResult = obj.findUnionNaive(arr1, arr2);
        System.out.print("Union: ");
        printArray(naiveResult);

        System.out.println("\n--- Optimal Approach ---");
        int[] optimalResult = obj.findUnionOptimal(arr1, arr2);
        System.out.print("Union: ");
        printArray(optimalResult);
    }
    
    // Helper function to print array without using Arrays.toString
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
 *    - Time Complexity: O((N+M)^2) for checking duplicates, plus O(K^2) for sorting 
 *      where K <= N+M. Overall O((N+M)^2).
 *    - Space Complexity: O(N+M) for the temporary array to store union elements.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N + M), where N and M are the lengths of the two arrays. 
 *      The two-pointer technique processes each element at most once.
 *    - Space Complexity: O(N + M) to store the result in the worst case (if all 
 *      elements are distinct).
 */