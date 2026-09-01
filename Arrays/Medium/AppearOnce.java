/*
 * Problem Statement: Find the Number that Appears Once
 * 
 * Given a non-empty array of integers where every element appears twice except for one.
 * Find that single one.
 * 
 * Example 1:
 * Input: arr[] = {2, 2, 1}
 * Output: 1
 * Explanation: 1 appears only once, while 2 appears twice.
 * 
 * Example 2:
 * Input: arr[] = {4, 1, 2, 1, 2}
 * Output: 4
 * Explanation: 4 appears only once, while 1 and 2 appear twice.
 */

import java.util.HashMap;
import java.util.Map;

class OneElement {

    /*
     * [Naive Approach] Linear Search
     * For each element, search the entire array to count its occurrences.
     * If the count is 1, return that element.
     */
    int appearNaive(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            // Count occurrences of arr[i]
            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            // If it appears exactly once, return it
            if (count == 1) {
                return arr[i];
            }
        }
        return -1;
    }

    /*
     * [Better Approach] HashMap
     * Store the frequency of each element in a hash map. Then iterate through the 
     * map to find the element with a frequency of 1.
     */
    int appearHash(int[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Populate map with frequencies
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // Find the element with frequency 1
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    /*
     * [Optimal Approach] Bit Manipulation (XOR)
     * XOR of two same numbers is always 0 (e.g., 2 ^ 2 = 0).
     * XOR of a number with 0 is the number itself (e.g., 4 ^ 0 = 4).
     * By XORing all the numbers in the array, the duplicates will cancel each other out,
     * leaving only the number that appears once.
     */
    int appearOptimal(int[] arr) {
        int xor = 0;
        // XOR all elements in the array
        for (int i = 0; i < arr.length; i++) {
            xor = xor ^ arr[i];
        }
        return xor;
    }
}

public class AppearOnce {
  
    public static void main(String[] args) {
        int[] arr = {4, 1, 2, 1, 2};
        OneElement obj = new OneElement();
        
        System.out.println("--- Naive Approach ---");
        System.out.println("The element that appears once is: " + obj.appearNaive(arr));
        
        System.out.println("\n--- Better (HashMap) Approach ---");
        System.out.println("The element that appears once is: " + obj.appearHash(arr));
        
        System.out.println("\n--- Optimal (XOR) Approach ---");
        System.out.println("The element that appears once is: " + obj.appearOptimal(arr));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), as we use nested loops to count occurrences for each element.
 *    - Space Complexity: O(1), no extra space used.
 * 
 * 2. Better Approach (HashMap):
 *    - Time Complexity: O(N), for iterating through the array to build the map and iterating 
 *      through the map. Note that HashMap operations take O(1) on average.
 *    - Space Complexity: O(N/2 + 1) ~ O(N), as we store the unique elements in the map.
 * 
 * 3. Optimal Approach (XOR):
 *    - Time Complexity: O(N), as we iterate through the array exactly once.
 *    - Space Complexity: O(1), we only use a single variable 'xor'.
 */