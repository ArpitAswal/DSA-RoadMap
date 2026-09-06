/*
 * Problem Statement: Square Root of a Number
 * 
 * Given a non-negative integer x, return the square root of x rounded down to the 
 * nearest integer. The returned integer should be non-negative as well.
 * You must not use any built-in exponent function or operator (like Math.sqrt).
 * 
 * Example 1:
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2.
 * 
 * Example 2:
 * Input: x = 28
 * Output: 5
 * Explanation: The square root of 28 is 5.2915..., and since we round it down to 
 * the nearest integer, 5 is returned.
 */

class SqrtNumber {

    /*
     * [Naive Approach] Linear Search
     * We can simply iterate from i = 1 upwards. The moment i * i is strictly greater 
     * than x, we know the floor of the square root is i - 1.
     * We need to be careful of integer overflow when calculating i * i, so we use long.
     */
    public static long sqrtNaive(long x) {
        if (x < 2) {
            return x;
        }

        long ans = 1;
        for (long i = 1; i <= x; i++) {
            if (i * i <= x) {
                ans = i;
            } else {
                break; // We found an i where i*i > x
            }
        }
        return ans;
    }

    /*
     * [Optimal Approach] Binary Search
     * We can find the square root in O(log x) time using binary search. The search 
     * space is from 1 to x / 2. We check if mid * mid <= x to determine the boundaries.
     */
    public static long sqrtOptimal(long x) {
        // Handle small numbers directly
        if (x < 2) {
            return x;
        }

        // Initialize binary search range
        long left = 1;
        long right = x / 2;
        long ans = 0;

        // Perform binary search
        while (left <= right) {
            // Find middle point (safely avoiding overflow)
            long mid = left + (right - left) / 2;

            // Check if mid * mid is less than or equal to x
            // Using (mid <= x / mid) is safer against overflow than (mid * mid <= x), 
            // though we are using long so it's generally safe.
            if (mid * mid <= x) {
                // Store mid as potential answer
                ans = mid;
                // Move to right half to find a larger possible integer
                left = mid + 1;
            } else {
                // mid * mid is strictly greater than x, so search left half
                right = mid - 1;
            }
        }

        return ans;
    }

    public static void main (String[] args) {
        long x1 = 4;
        long x2 = 28;

        System.out.println("--- Naive Approach ---");
        System.out.println("The floor value of the square root of " + x1 + " is: " + sqrtNaive(x1));
        System.out.println("The floor value of the square root of " + x2 + " is: " + sqrtNaive(x2));

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("The floor value of the square root of " + x1 + " is: " + sqrtOptimal(x1));
        System.out.println("The floor value of the square root of " + x2 + " is: " + sqrtOptimal(x2));
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(sqrt(x)), as the loop runs approximately sqrt(x) times.
 *    - Space Complexity: O(1), no extra space required.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(log x), since we divide the search space by 2 at each step.
 *    - Space Complexity: O(1), as this is an iterative binary search using a few pointers.
 */