// Greatest Common Divisor (GCD) of Two Numbers

// Given two integers a and b, find their Greatest Common Divisor (GCD), also known as Highest Common Factor (HCF).
// The GCD of two integers is the largest positive integer that divides both numbers without leaving a remainder.

// Examples:

// Input: a = 48, b = 18
// Output: 6
// Explanation: The factors of 48 are 1, 2, 3, 4, 6, 8, 12, 16, 24, 48.
//              The factors of 18 are 1, 2, 3, 6, 9, 18.
//              The largest common factor is 6.

// Input: a = 0, b = 5
// Output: 5
// Explanation: The GCD of 0 and any number x is |x|, because any non-zero integer divides 0.

// Input: a = -4, b = 6
// Output: 2
// Explanation: The GCD is always a positive integer, so HCF(-4, 6) is HCF(4, 6) = 2.

class GCDNumber {

    /*
     * Custom absolute value function to avoid using built-in Math.abs().
     */
    private static int abs(int x) {
        // Special case to prevent negation overflow for Integer.MIN_VALUE
        if (x == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE; // Approximates boundary safely for GCD purposes
        }
        return x < 0 ? -x : x;
    }

    /*
     * Custom min function to avoid using built-in Math.min().
     */
    private static int min(int x, int y) {
        return x < y ? x : y;
    }

    /*
     * [Naive Approach] Linear Search - O(min(a, b)) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Convert a and b to their absolute values.
     *   2. If either number is 0, return the other absolute value.
     *   3. Loop from i = 1 to min(a, b).
     *   4. If both a % i == 0 and b % i == 0, update gcd = i.
     *   5. Return gcd.
     *
     * Time Complexity  : O(min(a, b)) - loops from 1 to the smaller input.
     * Space Complexity : O(1)         - constant space.
     */
    static int gcdNaive(int a, int b) {
        a = abs(a);
        b = abs(b);

        if (a == 0) return b;
        if (b == 0) return a;

        int gcd = 1;
        int limit = min(a, b);
        for (int i = 1; i <= limit; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i; // Found a common divisor
            }
        }
        return gcd;
    }

    /*
     * [Better Approach] Euclidean Algorithm by Subtraction - O(a + b) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Convert a and b to their absolute values.
     *   2. If either number is 0, return the other.
     *   3. While a is not equal to b:
     *        - If a > b, update a = a - b.
     *        - Else, update b = b - a.
     *   4. Return a (or b, since they are equal).
     *
     * Drawback: If one number is very large and the other is 1 (e.g. a=10^9, b=1), subtraction takes 10^9 steps.
     *
     * Time Complexity  : O(a + b) - in the worst case (linear subtraction steps).
     * Space Complexity : O(1)     - constant space.
     */
    static int gcdSubtraction(int a, int b) {
        a = abs(a);
        b = abs(b);

        if (a == 0) return b;
        if (b == 0) return a;

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    /*
     * [Optimal/Interview Approach] Euclidean Algorithm (Modulo/Iterative) - O(log(min(a, b))) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Convert a and b to absolute values.
     *   2. While b is not equal to 0:
     *        a. Store b in a temporary variable: `temp = b`.
     *        b. Update b to remainder of a divided by b: `b = a % b`.
     *        c. Update a to the temp value: `a = temp`.
     *   3. Return a.
     *
     * Why this is interview-preferred:
     *   - Logarithmic time complexity (extremely fast even for numbers with hundreds of digits).
     *   - Iterative implementation prevents stack overflow (unlike recursive implementation).
     *   - Uses only primitive modulo arithmetic.
     *
     * Time Complexity  : O(log(min(a, b))) - number of steps is bounded logarithmically.
     * Space Complexity : O(1)               - constant auxiliary space.
     */
    static int gcdOptimal(int a, int b) {
        a = abs(a);
        b = abs(b);

        // Standard Euclidean modulo loop
        while (b != 0) {
            int temp = b;
            b = a % b; // Get remainder
            a = temp;  // Advance divisor
        }
        return a; // a contains the GCD
    }

    public static void main(String args[]) {
        int a1 = 48, b1 = 18;
        int a2 = 0, b2 = 5;
        int a3 = -4, b3 = 6;
        int a4 = 17, b4 = 23; // Coprime numbers

        System.out.println("Testing GCDNumber:");
        System.out.println("GCD(" + a1 + ", " + b1 + ") -> Naive: " + gcdNaive(a1, b1) + " | Sub: " + gcdSubtraction(a1, b1) + " | Opt: " + gcdOptimal(a1, b1));
        System.out.println("GCD(" + a2 + ", " + b2 + ") -> Naive: " + gcdNaive(a2, b2) + " | Sub: " + gcdSubtraction(a2, b2) + " | Opt: " + gcdOptimal(a2, b2));
        System.out.println("GCD(" + a3 + ", " + b3 + ") -> Naive: " + gcdNaive(a3, b3) + " | Sub: " + gcdSubtraction(a3, b3) + " | Opt: " + gcdOptimal(a3, b3));
        System.out.println("GCD(" + a4 + ", " + b4 + ") -> Naive: " + gcdNaive(a4, b4) + " | Sub: " + gcdSubtraction(a4, b4) + " | Opt: " + gcdOptimal(a4, b4));
    }
}