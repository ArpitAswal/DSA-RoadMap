// Maximum Product Subarray

// Given an integer array arr[], find a contiguous non-empty subarray within an array
// that has the largest product, and return the product.

// Examples:

// Input: arr[] = [-2, 6, -3, -10, 0, 2]
// Output: 180
// Explanation: Subarray [6, -3, -10] has the maximum product = 6 * (-3) * (-10) = 180.

// Input: arr[] = [2, 3, -2, 4]
// Output: 6
// Explanation: Subarray [2, 3] has maximum product = 6.

// Input: arr[] = [-2, 0, -1]
// Output: 0
// Explanation: The result cannot be 2, because [-2, -1] is not a contiguous subarray. Max product is 0.

class MaximumProductSubArray {

    /*
     * [Naive Approach] Brute Force - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize maxProduct with arr[0].
     *   2. Outer loop 'i' marks starting index of subarray from 0 to n - 1.
     *   3. Reset currentProduct = 1.
     *   4. Inner loop 'j' marks ending index from i to n - 1:
     *        - Multiply currentProduct by arr[j].
     *        - Update maxProduct if currentProduct > maxProduct.
     *   5. Return maxProduct.
     *
     * Time Complexity  : O(n^2) - two nested loops traversing array elements.
     * Space Complexity : O(1)   - uses primitive variables only.
     */
    static int findMaxProductNaive(int[] arr) {
        int n = arr.length;          // Length of array
        int maxProduct = arr[0];    // Initialize max product with first element

        // Outer loop: start of subarray
        for (int i = 0; i < n; i++) {
            int currentProduct = 1; // Reset product for subarray starting at i

            // Inner loop: end of subarray
            for (int j = i; j < n; j++) {
                currentProduct *= arr[j]; // Multiply by next element

                // Update max product if current subarray product is greater
                if (currentProduct > maxProduct) {
                    maxProduct = currentProduct;
                }
            }
        }

        return maxProduct;
    }

    /*
     * [Better Approach] Two-Pass Prefix/Suffix Product - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If array contains an even number of negative numbers and no zeros, total product is maximum.
     *   2. If array contains an odd number of negative numbers, maximum product comes from either prefix or suffix product before/after one negative element.
     *   3. Pass 1: Compute product from left to right (resetting to 1 when hitting 0).
     *   4. Pass 2: Compute product from right to left (resetting to 1 when hitting 0).
     *   5. Maximum of all left and right products is the answer.
     *
     * Time Complexity  : O(n) - single loop computing left and right products simultaneously.
     * Space Complexity : O(1) - auxiliary space is constant.
     */
    static int findMaxProductTwoPass(int[] arr) {
        int leftProduct = 1;  // Prefix product from left to right
        int rightProduct = 1; // Suffix product from right to left
        int maxSoFar = Integer.MIN_VALUE; // Global max product

        int n = arr.length; // Length of array

        // Single loop computes prefix and suffix products
        for (int i = 0; i < n; i++) {
            // Reset prefix product to 1 if zero encountered previously
            if (leftProduct == 0) {
                leftProduct = 1;
            }
            // Reset suffix product to 1 if zero encountered previously
            if (rightProduct == 0) {
                rightProduct = 1;
            }

            leftProduct *= arr[i];         // Accumulate product from left
            rightProduct *= arr[n - 1 - i]; // Accumulate product from right

            // Update max product seen so far
            if (leftProduct > maxSoFar) {
                maxSoFar = leftProduct;
            }
            if (rightProduct > maxSoFar) {
                maxSoFar = rightProduct;
            }
        }

        return maxSoFar;
    }

    /*
     * [Optimal / Interview Approach] Modified Kadane's Algorithm (Min/Max Product Tracking) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Multiplying by a negative number swaps maximum product and minimum product.
     *   2. Maintain `currMax` (max product ending at current index) and `currMin` (min product ending at current index).
     *   3. For each element arr[i]:
     *        - If arr[i] < 0: swap(currMax, currMin) (because multiplying by negative turns min to max and max to min).
     *        - Update currMax = max(arr[i], currMax * arr[i]).
     *        - Update currMin = min(arr[i], currMin * arr[i]).
     *        - Update global maxSoFar = max(maxSoFar, currMax).
     *
     * Why this is interview-preferred:
     *   - Dynamic programming approach that tracks both max and min products in a single pass O(n).
     *   - Elegant and robust across zeros, positive, and negative numbers.
     *
     * Time Complexity  : O(n) - single pass over array of size n.
     * Space Complexity : O(1) - constant auxiliary variables.
     */
    static int findMaxProductKadane(int[] arr) {
        int currMax = arr[0]; // Stores max product ending at current position
        int currMin = arr[0]; // Stores min product ending at current position
        int maxSoFar = arr[0]; // Overall max product

        // Iterate through array starting from index 1
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i]; // Current element

            // If current number is negative, swap max and min values
            if (num < 0) {
                int temp = currMax;
                currMax = currMin;
                currMin = temp;
            }

            // Decide whether to multiply current element or start fresh from num
            if (num > currMax * num) {
                currMax = num;
            } else {
                currMax = currMax * num;
            }

            if (num < currMin * num) {
                currMin = num;
            } else {
                currMin = currMin * num;
            }

            // Update overall global maximum product
            if (currMax > maxSoFar) {
                maxSoFar = currMax;
            }
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr1 = { -2, 6, -3, -10, 0, 2 };

        System.out.println("Input Array: ");
        for (int val : arr1) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        System.out.println("1. Naive Approach Result    : " + findMaxProductNaive(arr1));
        System.out.println("2. Two-Pass Approach Result  : " + findMaxProductTwoPass(arr1));
        System.out.println("3. Kadane Approach Result    : " + findMaxProductKadane(arr1));

        System.out.println("\n--- Additional Tests ---");
        int[][] testCases = {
            { 2, 3, -2, 4 },
            { -2, 0, -1 },
            { -2, -3, 0, -2, -40 }
        };

        for (int[] test : testCases) {
            System.out.println("Array: " + java.util.Arrays.toString(test) + 
                               " -> Max Product: " + findMaxProductKadane(test));
        }
    }
}