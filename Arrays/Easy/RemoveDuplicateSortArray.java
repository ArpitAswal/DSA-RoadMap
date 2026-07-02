import java.util.Arrays;

// Remove Duplicates from a Sorted Array

// Given a sorted array arr[], remove the duplicate elements in-place such that
// each element appears only once. Return the number of unique elements and print
// the modified array up to that length.

// Note: The array is already sorted, so duplicates are always adjacent.

// Examples:

// Input: arr[] = [1, 1, 2, 2, 3, 4, 4, 5]
// Output: [1, 2, 3, 4, 5], unique count = 5
// Explanation: Duplicates removed in-place; first 5 elements are unique.

// Input: arr[] = [1, 1, 1, 1]
// Output: [1], unique count = 1
// Explanation: All elements are same; only one unique element.

// Input: arr[] = [1, 2, 3, 4, 5]
// Output: [1, 2, 3, 4, 5], unique count = 5
// Explanation: No duplicates; array remains unchanged.

class RemoveDuplicateSortArray {

    /*
     * [Naive Approach] Using a HashSet - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Insert all elements into a LinkedHashSet (preserves insertion order).
     *   2. The set automatically removes duplicates.
     *   3. Copy the set elements back into the array.
     *   4. Return the count of unique elements.
     *
     * Drawback: Uses O(n) extra space for the set, which is not in-place.
     *
     * Time Complexity  : O(n) - one pass to fill the set, one pass to copy back.
     * Space Complexity : O(n) - extra space for the HashSet.
     */
    static int removeDuplicatesNaive(int[] arr) {
        // LinkedHashSet preserves insertion order and removes duplicates automatically
        java.util.LinkedHashSet<Integer> set = new java.util.LinkedHashSet<>();

        // Add every element; duplicates are silently ignored by the set
        for (int num : arr) {
            set.add(num);
        }

        // Copy unique elements back into the original array
        int idx = 0;
        for (int num : set) {
            arr[idx++] = num;
        }

        // Return count of unique elements
        return set.size();
    }

    /*
     * [Optimised Approach] Two-Pointer Technique - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Use a slow pointer 'j' starting at index 0 (tracks the last unique position).
     *   2. Use a fast pointer 'i' starting at index 1 to scan through the array.
     *   3. Since the array is sorted, duplicates are always adjacent.
     *   4. Whenever arr[i] != arr[i-1], we have found a new unique element:
     *        - Increment j and place arr[i] at arr[j].
     *   5. After the loop, the first (j+1) elements are the unique elements.
     *
     * Why this is interview-preferred:
     *   - In-place → O(1) extra space.
     *   - Single pass → O(n) time.
     *   - Exploits the sorted property elegantly.
     *
     * Time Complexity  : O(n) - single traversal of the array.
     * Space Complexity : O(1) - no extra data structures; modified in-place.
     */
    static int removeDuplicatesOptimised(int[] arr) {
        // 'j' is the index of the last written unique element
        int j = 0;

        // Start from index 1 since arr[0] is always unique
        for (int i = 1; i < arr.length; i++) {
            // If current element differs from the previous one, it's unique
            if (arr[i] != arr[i - 1]) {
                j++;                // Move slow pointer forward
                arr[j] = arr[i];   // Overwrite with the new unique element
            }
            // If arr[i] == arr[i-1], it's a duplicate → skip it
        }

        // Number of unique elements is j + 1 (0-indexed)
        return j + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5};

        // Naive Approach - O(n) time, O(n) space using HashSet
        System.out.println("Naive Approach (HashSet):");
        int[] arrCopy = arr.clone(); // clone to keep original for optimised test
        int countNaive = removeDuplicatesNaive(arrCopy);
        System.out.println("Unique count: " + countNaive);
        System.out.println("Array: " + Arrays.toString(Arrays.copyOf(arrCopy, countNaive)));

        System.out.println();

        // Optimised Approach - O(n) time, O(1) space using Two Pointers
        System.out.println("Optimised Approach (Two Pointers):");
        int countOpt = removeDuplicatesOptimised(arr);
        System.out.println("Unique count: " + countOpt);
        System.out.println("Array: " + Arrays.toString(Arrays.copyOf(arr, countOpt)));
    }
}