import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Majority Element II - Elements occurring more than [n/3]

// Given an array arr[] consisting of n integers, find all the array elements which occurs more than floor(n/3) times.
// Note: The returned array of majority elements should be sorted.

// Examples:

// Input: arr[] = [2, 2, 3, 1, 3, 2, 1, 1]
// Output: [1, 2]
// Explanation: The frequency of 1 and 2 is 3, which is more than floor n/3 (8/3 = 2).

// Input: arr[] = [-5, 3, -5]
// Output: [-5]
// Explanation: The frequency of -5 is 2, which is more than floor n/3 (3/3 = 1).

// Input: arr[] = [3, 2, 2, 4, 1, 4]
// Output: [ ]
// Explanation: There is no majority element.

class MajorityElements {

    /*
     * [Naive Approach] Using Nested Loops - O(n^2) Time and O(1) Space
     * The idea is to iterate over all elements and count the frequency of the
     * element in the array. If the frequency of the element is greater than
     * floor(n/3), add it to the result.
     * To avoid adding duplicate elements into the result, we can check if the
     * element is already present in the result. We can stop the iteration if we
     * have already found two majority elements.
     */

    static ArrayList<Integer> findMajority(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Count the frequency of arr[i]
            int cnt = 0;
            for (int j = i; j < n; j++) {
                if (arr[j] == arr[i])
                    cnt += 1;
            }

            // Check if arr[i] is a majority element
            if (cnt > (n / 3)) {
                // Add arr[i] only if it is not already present
                if (res.size() == 0 || arr[i] != res.get(0)) {
                    res.add(arr[i]);
                }
            }

            // If we have found two majority elements,
            // we can stop our search
            if (res.size() == 2) {
                if (res.get(0) > res.get(1))
                    java.util.Collections.swap(res, 0, 1);
                break;
            }
        }

        return res;
    }

    /*
     * [Better Approach] Using Hash Map or Dictionary - O(n) Time and O(n) Space
     * The idea is to use a hash map or dictionary to count the frequency of each
     * element in the array.
     * After counting, iterate over the hash map and if the frequency of any element
     * is greater than (n/3), push it into the result. Finally, the majority
     * elements are returned after sorting.
     */

    static ArrayList<Integer> searchMajority(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();

        // find frequency of each number
        for (int ele : arr)
            freq.put(ele, freq.getOrDefault(ele, 0) + 1);

        // Iterate over each key-value
        // pair in the hash map
        for (Map.Entry<Integer, Integer> it : freq.entrySet()) {
            int ele = it.getKey();
            int cnt = it.getValue();

            // Add the element to the result if its
            // frequency is greater than n / 3
            if (cnt > n / 3)
                res.add(ele);
        }

        // Sort result if there are two elements
        // and they are out of order
        if (res.size() == 2 && res.get(0) > res.get(1)) {
            int temp = res.get(0);
            res.set(0, res.get(1));
            res.set(1, temp);
        }

        return res;
    }

    /// for one candidate, Majority Element for n/2 greater
    static ArrayList<Integer> BoyerMooreVoting(int[] arr){
        int candidate = arr[0];
        int count =1;
        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();

        for(int i=1; i<arr.length; i++){
            if(arr[i] == candidate){
                count++;
            } else{
                count--;
                if(count == 0){
                    candidate = arr[i];
                    count = 1;
                }
            }

            if(count > n/2){
                res.add(candidate);
                break;
            }
        }

        return res;
    }

    /// for two candidate, Majority Elements for n/3 greater

    static ArrayList<Integer> majorityElements(int[] arr){
        int cand1 = 0;
        int cand2 = 1;
        int count1 = 0;
        int count2 = 0;
        int n = arr.length;


        for(int num : arr){
            if(num == cand1){
                count1++;
            } else if(num == cand2){
                count2++;
            } else if(count1 == 0){
                cand1 = num;
                count1 = 1;
            } else if(count2 == 0){
                cand2 = num;
                count2 = 1;
            } else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for(int num : arr){
            if(num == cand1){
                count1++;
            } else if(num == cand2){
                count2++;
            }
        }

        ArrayList<Integer> res = new ArrayList<Integer>();
        if(count1 > n/3){
            res.add(cand1);
        }
        if(count2 > n/3){
            res.add(cand2);
        }

         // If we have found two majority elements,
            // we can stop our search
            if (res.size() == 2) {
                if (res.get(0) > res.get(1))
                    java.util.Collections.swap(res, 0, 1);
            }

        return res;    
    }

    public static void main(String[] args) {
        int[] arr = { 2, 2, 3, 1, 3, 2, 1, 1 };

        // Naive Approach
        System.out.println("Naive Approach");
        ArrayList<Integer> res = findMajority(arr);
        for (int ele : res)
            System.out.print(ele + " ");

        System.out.println();

        // HashMap Approach
        System.out.println("HashMap Approach");
        ArrayList<Integer> ans = searchMajority(arr);
        for (int ele : ans)
            System.out.print(ele + " ");

        System.out.println();

        // Boyer-Moore voting
        System.out.println("Boyer-Moore voting Approach");
        ArrayList<Integer> ans1 = BoyerMooreVoting(arr);  
        for (int ele : ans1) 
         System.out.print(ele + " "); 
         System.out.println();
        ArrayList<Integer> ans2 = majorityElements(arr);  
        for (int ele : ans2) 
         System.out.print(ele + " ");
    }
}