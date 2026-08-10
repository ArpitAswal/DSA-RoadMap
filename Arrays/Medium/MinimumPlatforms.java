// Minimum Platforms Required for Railway Station

// Given arrival arr[] and departure dep[] times of all trains that reach a railway station,
// find the minimum number of platforms required for the railway station so that no train waits.

// Note: Time is given in 24-hour format (e.g. 900 means 09:00, 1130 means 11:30).
// If a train arrives at the same time another departs (arr[i] == dep[j]), the departure occurs first.

// Examples:

// Input: arr[] = [900, 940, 950, 1100, 1500, 1800], dep[] = [910, 1200, 1120, 1130, 1900, 2000]
// Output: 3
// Explanation:
//   - Train 1 arrives at 900, departs at 910. (Platform 1)
//   - Train 2 arrives at 940, departs at 1200. (Platform 1)
//   - Train 3 arrives at 950, departs at 1120. (Platform 2)
//   - Train 4 arrives at 1100, departs at 1130. (Platform 3)
//   At 1100, 3 trains (Train 2, Train 3, Train 4) are present simultaneously on platforms.
//   Minimum 3 platforms are required.

import java.util.Arrays;

class MinimumPlatforms {

    /*
     * [Naive Approach] Nested Loop Overlap Count - O(n^2) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. For each train 'i', count how many other trains 'j' overlap with train i's arrival and departure interval.
     *   2. Train j overlaps with train i if:
     *        (arr[j] >= arr[i] and arr[j] <= dep[i]) OR (arr[i] >= arr[j] and arr[i] <= dep[j]).
     *   3. Keep track of the maximum count of overlapping trains found across all trains.
     *
     * Time Complexity  : O(n^2) - nested loop comparing every train pair.
     * Space Complexity : O(1)   - constant auxiliary space.
     */
    static int minPlatformsNaive(int[] arr, int[] dep) {
        int n = arr.length;      // Number of trains
        int maxPlatforms = 1;   // Maximum platforms required

        // Outer loop inspects each train i
        for (int i = 0; i < n; i++) {
            int overlappingTrains = 1; // Train i itself requires 1 platform

            // Inner loop checks overlap with every other train j
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // Check if train j overlaps with time interval of train i
                    if (arr[i] >= arr[j] && dep[j] >= arr[i]) {
                        overlappingTrains++;
                    }
                }
            }

            // Update max platforms required if current train experiences higher overlap
            if (overlappingTrains > maxPlatforms) {
                maxPlatforms = overlappingTrains;
            }
        }

        return maxPlatforms;
    }

    /*
     * [Optimal / Interview Approach] Sorting + Two Pointers - O(n log n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Sort arrival array `arr[]` and departure array `dep[]` independently in ascending order.
     *   2. Maintain two pointers: `i = 1` for arrivals and `j = 0` for departures.
     *   3. Track `neededPlatforms = 1` and `maxPlatforms = 1`.
     *   4. Traverse both arrays:
     *        - If arr[i] <= dep[j]: next event is a train ARRIVAL!
     *          Increment neededPlatforms++, increment i++.
     *        - If arr[i] > dep[j]: next event is a train DEPARTURE!
     *          Decrement neededPlatforms--, increment j++.
     *        - Update maxPlatforms = max(maxPlatforms, neededPlatforms).
     *   5. Return maxPlatforms.
     *
     * Why this is interview-preferred:
     *   - Transforms interval overlap problem into a simple sweep-line timeline traversal using two pointers.
     *   - Efficient O(n log n) time dominated only by sorting step.
     *
     * Time Complexity  : O(n log n) - sorting arr and dep arrays of length n + O(n) sweep line.
     * Space Complexity : O(1)       - in-place two-pointer traversal.
     */
    static int minPlatformsOptimised(int[] arr, int[] dep) {
        int n = arr.length; // Number of trains

        // Clone and sort arrival and departure arrays independently
        int[] sortedArr = arr.clone();
        int[] sortedDep = dep.clone();

        Arrays.sort(sortedArr); // Sort arrival times ascending
        Arrays.sort(sortedDep); // Sort departure times ascending

        int neededPlatforms = 1; // Platforms needed at current time
        int maxPlatforms = 1;    // Maximum platforms needed overall

        int i = 1; // Pointer for arrival array (start at index 1 since first train already arrived)
        int j = 0; // Pointer for departure array

        // Process all events in chronological order
        while (i < n && j < n) {
            // Next event is arrival (train arrives before or at the same time another departs)
            if (sortedArr[i] <= sortedDep[j]) {
                neededPlatforms++; // Additional platform required for arriving train
                i++;               // Move to next arrival
            } else {
                // Next event is departure (train departs)
                neededPlatforms--; // Platform freed up as train departs
                j++;               // Move to next departure
            }

            // Update peak platform requirement seen so far
            if (neededPlatforms > maxPlatforms) {
                maxPlatforms = neededPlatforms;
            }
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };

        System.out.println("Arrival Times  : " + Arrays.toString(arr));
        System.out.println("Departure Times: " + Arrays.toString(dep) + "\n");

        System.out.println("1. Naive Approach Result     : " + minPlatformsNaive(arr, dep));
        System.out.println("2. Sorting + Two-Pointer Result: " + minPlatformsOptimised(arr, dep));
    }
}