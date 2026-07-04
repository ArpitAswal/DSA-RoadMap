package Medium;

import java.util.HashMap;

public class TwoSumArray {

    public static void main(String args[]) {
        // Find two sum in unsorted array
        int arr[] = { 3, 6, 2, 7, 5, 1 };
        int tar = 13;

        twoSumInsort(arr, tar);

        // Find two sum in sorted array
        int array[] = { 1, 2, 3, 4, 5, 6, 7 };
        int target = 10;

        twoSumSort(array, target);
    }

    private static void twoSumInsort(int[] arr, int tar) {
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int comp = tar - arr[i];
            if (freq.containsKey(comp)) {
                System.out.println("The sum of two in insort array are : " + arr[i] + " and " + arr[freq.get(i)]);
                break;
            } else {
                freq.put(arr[i], i);
            }
        }
    }

    private static void twoSumSort(int[] arr, int k) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            if (arr[start] + arr[end] == k) {
                System.out.println("The sum of two in sort array are : " + arr[start] + " and " + arr[end]);
                break;
            } else if (arr[start] + arr[end] < k) {
                start++;
            } else {
                end--;
            }
        }
    }

}