// Find All Pairs with Given Sum in an Array

// Given an array of integers arr[] and an integer sum, find and print all pairs of elements
// in the array whose sum is equal to the given sum.

// Examples:

// Input: arr[] = [1, 5, 7, -1, 5], sum = 6
// Output: (5, 1), (-1, 7), (5, 1)
// Explanation:
//   - arr[1] (5) + arr[0] (1) = 6
//   - arr[3] (-1) + arr[2] (7) = 6
//   - arr[4] (5) + arr[0] (1) = 6

// Input: arr[] = [2, 3, 4, -1, 6], sum = 5
// Output: (3, 2), (6, -1)
// Explanation:
//   - 3 + 2 = 5
//   - 6 + (-1) = 5

import java.util.Arrays;
import java.util.HashSet;

class FindAllSumPairs {

    /*
     * [Naive Approach] Brute Force using Nested Loops - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     * 1. Outer loop 'i' traverses elements from index 0 to n - 1.
     * 2. Inner loop 'j' traverses elements from index i + 1 to n - 1.
     * 3. Check if arr[i] + arr[j] == sum.
     * 4. If equal, print pair (arr[i], arr[j]).
     *
     * Drawback:
     * - Compares every element with every other element, leading to O(n^2) time.
     *
     * Time Complexity : O(n^2) - due to double nested loop over n elements.
     * Space Complexity : O(1) - no extra memory structures allocated.
     */
    static void findAllPairsNaive(int[] arr, int sum) {
        int n = arr.length; // Number of elements in array

        System.out.println("Naive Approach Pairs:");
        int pairCount = 0; // Count of pairs found

        // Outer loop picks first element
        for (int i = 0; i < n; i++) {
            // Inner loop picks second element after index i
            for (int j = i + 1; j < n; j++) {
                // Check if sum of two elements equals target sum
                if (arr[i] + arr[j] == sum) {
                    System.out.print("(Pair found: " + arr[i] + ", " + arr[j] + ") ");
                    pairCount++;
                }
            }
        }

        if (pairCount == 0) {
            System.out.print("No pairs found.");
        }
        System.out.println();
    }

    /*
     * [Better Approach] Sorting + Two-Pointer Technique - O(n log n) Time and O(1)
     * Space
     *
     * Logic / Steps:
     * 1. Clone and sort the array in ascending order using Arrays.sort().
     * 2. Place left pointer at index 0 and right pointer at index n - 1.
     * 3. Compute current sum: currSum = sortedArr[left] + sortedArr[right].
     * 4. If currSum == sum:
     * - Print pair (sortedArr[left], sortedArr[right]).
     * - Move both pointers: left++ and right--.
     * 5. If currSum < sum: increment left pointer to increase total sum.
     * 6. If currSum > sum: decrement right pointer to decrease total sum.
     *
     * Time Complexity : O(n log n) - dominated by sorting step O(n log n) + O(n)
     * two-pointer scan.
     * Space Complexity : O(1) - ignoring copy array / auxiliary space.
     */
    static void findAllPairsTwoPointer(int[] arr, int sum) {
        // Clone array to avoid modifying original input
        int[] sortedArr = arr.clone();

        // Sort array in ascending order
        Arrays.sort(sortedArr);

        int left = 0; // Left pointer at beginning
        int right = sortedArr.length - 1; // Right pointer at end

        System.out.println("Two-Pointer Approach Pairs:");
        int pairCount = 0;

        // Traverse until left and right pointers cross
        while (left < right) {
            int currentSum = sortedArr[left] + sortedArr[right]; // Calculate pair sum

            if (currentSum == sum) {
                // Found pair matching target sum
                System.out.print("(Pair found: " + sortedArr[left] + ", " + sortedArr[right] + ") ");
                pairCount++;
                left++; // Move left pointer rightward
                right--; // Move right pointer leftward
            } else if (currentSum < sum) {
                // Sum too small -> increment left to get larger value
                left++;
            } else {
                // Sum too large -> decrement right to get smaller value
                right--;
            }
        }

        if (pairCount == 0) {
            System.out.print("No pairs found.");
        }
        System.out.println();
    }

    /*
     * [Optimal / Interview Approach] Single-Pass HashSet - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     * 1. Create a HashSet of Integers to store visited elements.
     * 2. Iterate through each element arr[i] of array:
     * a. Calculate complement: comp = sum - arr[i].
     * b. Check if 'comp' is present in HashSet.
     * c. If present: print pair (arr[i], comp).
     * d. Add current element arr[i] to HashSet.
     * 3. HashSet lookup and insertion are O(1) on average.
     *
     * Why this is interview-preferred:
     * - Solves the problem in a single pass O(n) without needing to sort the array.
     * - Works on unsorted data seamlessly.
     *
     * Time Complexity : O(n) - single traversal over the array.
     * Space Complexity : O(n) - HashSet stores up to n unique elements.
     */
    static void findAllPairsHashSet(int[] arr, int sum) {
        // HashSet to store already seen numbers
        HashSet<Integer> set = new HashSet<>();

        System.out.println("HashSet Approach Pairs:");
        int pairCount = 0;

        // Iterate through all array elements
        for (int i = 0; i < arr.length; i++) {
            int comp = sum - arr[i]; // Calculate required complement

            // If complement exists in set, we have found a pair
            if (set.contains(comp)) {
                System.out.print("(Pair found: " + arr[i] + ", " + comp + ") ");
                pairCount++;
            }

            // Insert current element into set for future element comparisons
            set.add(arr[i]);
        }

        if (pairCount == 0) {
            System.out.print("No pairs found.");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 1, 5, 7, -1, 5 };
        int sum = 6;

        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Target Sum: " + sum + "\n");

        // 1. Naive Approach - O(n^2) Time, O(1) Space
        findAllPairsNaive(arr, sum);

        System.out.println();

        // 2. Two-Pointer Approach - O(n log n) Time, O(1) Space
        findAllPairsTwoPointer(arr, sum);

        System.out.println();

        // 3. HashSet Approach - O(n) Time, O(n) Space (Interview-Preferred)
        findAllPairsHashSet(arr, sum);
    }
}