package Medium;

import java.util.HashMap;

public class TwoSumArray {

    public static void main(String args[]) {
        // Find two sum in unsorted array
        int arr[] = { 3, 6, 2, 7, 5, 1 };
        int tar = 13;

        twoSumInsort(arr, tar);

    }

    private static void twoSumInsort(int[] arr, int tar) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int comp = tar - arr[i];
            if (freq.containsKey(comp)) {
                System.out.println("The sum of two in insort array are : " + arr[i] + " and " + arr[freq.get(i)]);

            } else {
                freq.put(arr[i], i);
            }
        }
    }

}