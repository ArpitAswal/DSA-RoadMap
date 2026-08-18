// Find All Divisors of a Number

// Given a positive integer n, find and return all its positive divisors in ascending order.
// A divisor is an integer that divides another integer evenly (without leaving a remainder).

// Examples:

// Input: n = 36
// Output: [1, 2, 3, 4, 6, 9, 12, 18, 36]
// Explanation: All these numbers divide 36 without any remainder.

// Input: n = 7
// Output: [1, 7]
// Explanation: 7 is a prime number, so its only divisors are 1 and 7.

// Input: n = 1
// Output: [1]
// Explanation: 1 is only divisible by itself.

// Input: n <= 0
// Output: []
// Explanation: Divisors are searched only for positive integers.

import java.util.ArrayList;
import java.util.List;

class DivisorNumber {

    /*
     * [Naive Approach] Linear Search from 1 to N - O(n) Time and O(d) Space
     *
     * Logic / Steps:
     *   1. If n <= 0, return an empty list.
     *   2. Initialize an empty list to store divisors.
     *   3. Loop from i = 1 to n:
     *        - If n % i == 0, add i to the list.
     *   4. Return the list.
     *
     * Drawback: For large n (e.g., 10^9), a loop of size 10^9 is too slow and causes TLE.
     *
     * Time Complexity  : O(n) - loops n times to check divisibility of all numbers.
     * Space Complexity : O(d) - where d is the number of divisors (to store the result list).
     */
    static List<Integer> getDivisorsNaive(int n) {
        List<Integer> res = new ArrayList<>();
        if (n <= 0) {
            return res; // Handle non-positive integer edge cases
        }

        // Check every number from 1 to n
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                res.add(i); // i is a divisor
            }
        }
        return res;
    }

    /*
     * [Optimal/Interview Approach] Loop up to Sqrt(n) with List Merging - O(sqrt(n)) Time and O(d) Space
     *
     * Logic / Steps:
     *   1. If n <= 0, return an empty list.
     *   2. Initialize two lists:
     *        - `lowDivisors`: to store divisors <= sqrt(n).
     *        - `highDivisors`: to store divisors > sqrt(n).
     *   3. Loop `i` from 1 to sqrt(n) (using `i * i <= n` to avoid built-in Math.sqrt).
     *   4. If `n % i == 0`:
     *        - Add `i` to `lowDivisors` (since we loop upwards, this will be in ascending order).
     *        - If `i != n / i`, add `n / i` to `highDivisors` (since `i` increases, `n / i` decreases, so this list is descending).
     *   5. Combine the lists:
     *        - Add all elements of `lowDivisors` to final result.
     *        - Add elements of `highDivisors` in reverse order (from end to start) to final result to maintain ascending order.
     *   6. Return the combined result.
     *
     * Why this is interview-preferred:
     *   - Reduces time complexity from O(n) to O(sqrt(n)) dramatically.
     *   - Obtains a fully sorted list in ascending order without using any built-in sorting functions (like Collections.sort).
     *
     * Time Complexity  : O(sqrt(n)) - loop runs up to sqrt(n) times.
     * Space Complexity : O(d)       - where d is the number of divisors (to store result). Auxiliary space is O(d).
     */
    static List<Integer> getDivisorsOptimal(int n) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0) {
            return result; // Edge Case
        }

        List<Integer> lowDivisors = new ArrayList<>();
        List<Integer> highDivisors = new ArrayList<>();

        // Loop up to sqrt(n) using mathematical condition i * i <= n
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                lowDivisors.add(i); // i is a divisor in the smaller half (increasing order)

                // If divisors are distinct, add the paired divisor n / i (decreasing order)
                if (i != n / i) {
                    highDivisors.add(n / i);
                }
            }
        }

        // Merge lowDivisors (already sorted ascending) into final result
        for (int i = 0; i < lowDivisors.size(); i++) {
            result.add(lowDivisors.get(i));
        }

        // Merge highDivisors (sorted descending) in reverse order to keep ascending order
        for (int i = highDivisors.size() - 1; i >= 0; i--) {
            result.add(highDivisors.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        int n1 = 36;
        int n2 = 7;
        int n3 = 1;
        int n4 = -5;

        System.out.println("Testing DivisorNumber:");
        System.out.println("Divisors of " + n1 + " (Naive)  : " + getDivisorsNaive(n1));
        System.out.println("Divisors of " + n1 + " (Optimal): " + getDivisorsOptimal(n1));

        System.out.println("Divisors of " + n2 + " (Optimal): " + getDivisorsOptimal(n2));
        System.out.println("Divisors of " + n3 + " (Optimal): " + getDivisorsOptimal(n3));
        System.out.println("Divisors of " + n4 + " (Optimal): " + getDivisorsOptimal(n4));
    }
}