// Merge Two Sorted Linked Lists

// You are given the heads of two sorted linked lists list1 and list2.
// Merge the two lists into one sorted linked list. The list should be made by splicing together the nodes of the first two lists.
// Return the head of the merged linked list.

// Examples:

// Input:  list1 = 1 -> 3 -> 5 -> null, list2 = 2 -> 4 -> 6 -> null
// Output: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
// Explanation: Comparing node values in order splices list1 and list2 into a single sorted list.

// Input:  list1 = null, list2 = 0 -> null
// Output: 0 -> null
// Explanation: Since list1 is empty, merged list is list2 itself.

import java.util.ArrayList;
import java.util.Collections;

class MergeTwoList {

    /*
     * [Naive Approach] Store Values in List, Sort, and Rebuild - O((n + m) log(n + m)) Time and O(n + m) Space
     *
     * Logic / Steps:
     *   1. Extract values of all nodes from list1 and list2 into an ArrayList.
     *   2. Sort the ArrayList using Collections.sort().
     *   3. Rebuild a new linked list from sorted values.
     *
     * Drawback: Allocates extra memory for array list and new nodes, ignoring existing node links.
     *
     * Time Complexity  : O((n + m) log(n + m)) - sorting (n + m) elements.
     * Space Complexity : O(n + m)               - extra list storing values.
     */
    static DataNode mergeListNaive(DataNode list1, DataNode list2) {
        ArrayList<Integer> values = new ArrayList<>();

        // Traverse list1 and collect values
        DataNode curr1 = list1;
        while (curr1 != null) {
            values.add(curr1.data);
            curr1 = curr1.next;
        }

        // Traverse list2 and collect values
        DataNode curr2 = list2;
        while (curr2 != null) {
            values.add(curr2.data);
            curr2 = curr2.next;
        }

        // Sort all collected values
        Collections.sort(values);

        // Rebuild new sorted linked list
        DataNode dummy = new DataNode(0);
        DataNode temp = dummy;
        for (int val : values) {
            temp.next = new DataNode(val);
            temp = temp.next;
        }

        return dummy.next;
    }

    /*
     * [Optimal / Interview Approach] Dummy Node Iterative In-Place Merge - O(n + m) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Create a sentinel/dummy node `dummy = new DataNode(0)` and pointer `tail = dummy`.
     *   2. While both `list1 != null` and `list2 != null`:
     *        - Compare node values: `list1.data` vs `list2.data`.
     *        - Attach smaller node to `tail.next`:
     *            if (list1.data <= list2.data) { tail.next = list1; list1 = list1.next; }
     *            else { tail.next = list2; list2 = list2.next; }
     *        - Advance `tail = tail.next`.
     *   3. Append remaining non-null list to `tail.next`:
     *        if (list1 != null) tail.next = list1;
     *        if (list2 != null) tail.next = list2;
     *   4. Return `dummy.next` (head of merged sorted list).
     *
     * Why this is interview-preferred:
     *   - Re-uses existing node pointers in-place without allocating new memory.
     *   - Merges lists in a single linear pass O(n + m) time and O(1) auxiliary space.
     *
     * Time Complexity  : O(n + m) - single pass comparing nodes of list1 and list2.
     * Space Complexity : O(1)     - constant extra space using dummy node reference.
     */
    static DataNode mergeListIterative(DataNode list1, DataNode list2) {
        // Base cases: if one list is empty, return the other
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        DataNode dummy = new DataNode(0); // Dummy node to simplify head attachment
        DataNode tail = dummy;            // Tail pointer tracks end of merged list

        // Traverse both lists and attach smaller node to merged list
        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                tail.next = list1;  // Attach node from list1
                list1 = list1.next; // Advance list1 pointer
            } else {
                tail.next = list2;  // Attach node from list2
                list2 = list2.next; // Advance list2 pointer
            }
            tail = tail.next; // Advance tail pointer
        }

        // Attach remaining nodes of list1 or list2
        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }

        return dummy.next; // Head of merged list
    }

    /*
     * [Alternative Approach] Recursive Merge - O(n + m) Time and O(n + m) Space
     *
     * Logic / Steps:
     *   1. Base cases: if list1 is null, return list2; if list2 is null, return list1.
     *   2. Compare list1.data and list2.data:
     *        - If list1.data <= list2.data: list1.next = mergeListRecursive(list1.next, list2); return list1.
     *        - Else: list2.next = mergeListRecursive(list1, list2.next); return list2.
     *
     * Time Complexity  : O(n + m) - makes (n + m) recursive calls.
     * Space Complexity : O(n + m) - call stack memory.
     */
    static DataNode mergeListRecursive(DataNode list1, DataNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.data <= list2.data) {
            list1.next = mergeListRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeListRecursive(list1, list2.next);
            return list2;
        }
    }

    // Helper method to print linked list
    static void printList(DataNode node) {
        while (node != null) {
            System.out.print(node.data + (node.next != null ? " -> " : ""));
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DataNode list1 = new DataNode(1);
        list1.next = new DataNode(3);
        list1.next.next = new DataNode(5);

        DataNode list2 = new DataNode(2);
        list2.next = new DataNode(4);
        list2.next.next = new DataNode(6);

        System.out.println("List 1: ");
        printList(list1);
        System.out.println("List 2: ");
        printList(list2);

        DataNode mergedHead = mergeListIterative(list1, list2);
        System.out.println("\nMerged Sorted List (Iterative): ");
        printList(mergedHead);
    }
}