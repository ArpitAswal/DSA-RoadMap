// Prime Number Verification

// Given a number n, check if it is a prime number.
// A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.

// Examples:

// Input: n = 2
// Output: true
// Explanation: 2 is the smallest prime number and the only even prime.

// Input: n = 3
// Output: true
// Explanation: 3 has only two factors: 1 and 3.

// Input: n = 15
// Output: false
// Explanation: 15 is divisible by 1, 3, 5, and 15, so it is a composite number.

// Input: n = 1
// Output: false
// Explanation: 1 is not a prime number by definition.

class PrimeNumber {

    /*
     * [Naive Approach] Trial Division up to N-1 - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n <= 1, return false.
     *   2. Loop through all integers i from 2 to n - 1.
     *   3. If n % i == 0, then i is a factor, so n is not prime -> return false.
     *   4. If loop completes, return true.
     *
     * Time Complexity  : O(n) - in the worst case (when n is prime), we check all numbers up to n.
     * Space Complexity : O(1) - no extra space used.
     */
    static boolean isPrimeNaive(int n) {
        // Numbers less than or equal to 1 are not prime
        if (n <= 1) {
            return false;
        }

        // Check divisibility for all numbers from 2 up to n - 1
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false; // Found a divisor, so it's composite
            }
        }
        return true; // No divisors found, it's prime
    }

    /*
     * [Expected Approach] Trial Division up to Square Root of N - O(sqrt(n)) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. If n <= 1, return false.
     *   2. Loop through integers starting from 2 up to sqrt(n) (using i * i <= n to avoid Math.sqrt built-in).
     *   3. If n is divisible by any i, return false.
     *   4. Otherwise, return true.
     *
     * Why we only check up to sqrt(n):
     *   - If a number n has a factor larger than sqrt(n), it must also have a matching factor smaller than sqrt(n)
     *     because a * b = n. If both a and b were greater than sqrt(n), their product would be greater than n.
     *
     * Time Complexity  : O(sqrt(n)) - the loop runs at most sqrt(n) times.
     * Space Complexity : O(1)       - constant auxiliary space.
     */
    static boolean isPrimeSqrt(int n) {
        // Base case: numbers <= 1 are not prime
        if (n <= 1) {
            return false;
        }

        // Loop up to square root of n using mathematical multiplication i * i <= n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // Divisor found
            }
        }
        return true; // Prime
    }

    /*
     * [Optimal/Interview Approach] Trial Division with 6k +/- 1 Optimization - O(sqrt(n)) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Handle base cases: if n <= 1 return false. If n == 2 or n == 3, return true.
     *   2. If n is divisible by 2 or 3, return false (handles all even numbers and multiples of 3 immediately).
     *   3. Loop from i = 5 to sqrt(n) (i * i <= n), incrementing by 6 in each step (i += 6):
     *        a. Check if n is divisible by i (form 6k - 1) or i + 2 (form 6k + 1).
     *        b. If divisible by either, return false.
     *   4. Return true.
     *
     * Why 6k +/- 1 works:
     *   - All integers can be expressed as 6k, 6k+1, 6k+2, 6k+3, 6k+4, or 6k+5.
     *   - 6k, 6k+2, 6k+4 are divisible by 2, and 6k+3 is divisible by 3.
     *   - Thus, any prime number greater than 3 must be of the form 6k + 1 or 6k + 5 (which is 6(k+1) - 1).
     *   - Checking only these numbers reduces the search space by about 66%.
     *
     * Time Complexity  : O(sqrt(n)) - with 3x fewer iterations than the basic sqrt approach.
     * Space Complexity : O(1)       - constant auxiliary space.
     */
    static boolean isPrimeOptimal(int n) {
        // Handle initial small cases
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }

        // Exclude multiples of 2 and 3
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        // Check divisors of the form 6k +/- 1 starting from 5 up to sqrt(n)
        for (int i = 5; i * i <= n; i += 6) {
            // i represents 6k - 1, i + 2 represents 6k + 1
            if (n % i == 0 || n % (i + 2) == 0) {
                return false; // Found a divisor
            }
        }

        return true; // Prime
    }

    public static void main(String[] args) {
        int[] testNumbers = { 1, 2, 3, 4, 15, 17, 97, 100 };

        System.out.println("Testing PrimeNumber:");
        for (int num : testNumbers) {
            System.out.println("Number: " + num + 
                               " -> Naive: " + isPrimeNaive(num) + 
                               " | Sqrt: " + isPrimeSqrt(num) + 
                               " | Optimal: " + isPrimeOptimal(num));
        }
    }
}