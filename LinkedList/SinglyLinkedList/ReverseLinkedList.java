// Reverse a Singly Linked List

// Given the head of a singly linked list, reverse the list, and return the reversed list's head.

// Examples:

// Input:  1 -> 2 -> 3 -> 4 -> 5 -> null
// Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
// Explanation: The pointers of all nodes are reversed in direction, making 5 the new head.

// Input:  1 -> 2 -> null
// Output: 2 -> 1 -> null
// Explanation: Node 2 becomes head pointing to 1.

import java.util.Stack;

class ReverseLinkedList {

    /*
     * [Naive Approach] Using Stack - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Push values of all nodes onto a Stack.
     *   2. Traverse linked list again from head, popping top value from Stack and overwriting node.data.
     *
     * Drawback: Modifies node values instead of reversing actual pointer links, and requires O(n) extra space.
     *
     * Time Complexity  : O(n) - two passes: one to push onto stack, one to pop and overwrite values.
     * Space Complexity : O(n) - stack stores n node values.
     */
    static DataNode reverseListStack(DataNode head) {
        if (head == null) return null;

        Stack<Integer> stack = new Stack<>();
        DataNode curr = head;

        // Pass 1: Push node data values onto stack
        while (curr != null) {
            stack.push(curr.data);
            curr = curr.next;
        }

        curr = head;
        // Pass 2: Pop values from stack and re-assign to list nodes
        while (curr != null) {
            curr.data = stack.pop();
            curr = curr.next;
        }

        return head;
    }

    /*
     * [Optimal / Interview Approach] Iterative In-Place 3-Pointer - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain three pointers: `prev = null`, `curr = head`, `next = null`.
     *   2. Traverse linked list while `curr != null`:
     *        a. Store next node: `next = curr.next`.
     *        b. Reverse pointer link: `curr.next = prev`.
     *        c. Advance `prev` pointer: `prev = curr`.
     *        d. Advance `curr` pointer: `curr = next`.
     *   3. After loop completes, `prev` points to the new head of the reversed list. Return `prev`.
     *
     * Why this is interview-preferred:
     *   - Reverses pointer links in-place in a single traversal O(n).
     *   - Operates in strictly O(1) auxiliary memory.
     *
     * Time Complexity  : O(n) - single pass through n nodes of linked list.
     * Space Complexity : O(1) - constant memory using three pointer variables.
     */
    static DataNode reverseListIterative(DataNode head) {
        DataNode prev = null; // Previous node pointer (initially null for original head)
        DataNode curr = head; // Current node pointer being processed
        DataNode next = null; // Temporary pointer to store next node reference

        // Traverse through linked list
        while (curr != null) {
            next = curr.next;   // 1. Store next node reference
            curr.next = prev;   // 2. Reverse link direction (point current node to prev)
            prev = curr;        // 3. Move prev pointer forward to current node
            curr = next;        // 4. Move curr pointer forward to next node
        }

        return prev; // prev becomes the new head of reversed list
    }

    /*
     * [Alternative Approach] Recursive Link Reversal - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Base case: If head is null or head.next is null, return head.
     *   2. Recursively reverse sub-list from head.next onwards: `newHead = reverseListRecursive(head.next)`.
     *   3. Reverse link for current head: `head.next.next = head`.
     *   4. Disconnect current head's original next link: `head.next = null`.
     *   5. Return `newHead`.
     *
     * Time Complexity  : O(n) - visits each node once in recursion.
     * Space Complexity : O(n) - implicit call stack space of depth n.
     */
    static DataNode reverseListRecursive(DataNode head) {
        // Base Case: empty list or last node reached
        if (head == null || head.next == null) {
            return head;
        }

        // Recursive call to reverse sub-list
        DataNode newHead = reverseListRecursive(head.next);

        // Reverse connection between head and next node
        head.next.next = head;
        head.next = null; // Break original forward link

        return newHead; // Return new head of reversed list
    }

    // Helper method to print linked list nodes
    static void printList(DataNode node) {
        while (node != null) {
            System.out.print(node.data + (node.next != null ? " -> " : ""));
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DataNode head = new DataNode(1);
        head.next = new DataNode(2);
        head.next.next = new DataNode(3);
        head.next.next.next = new DataNode(4);
        head.next.next.next.next = new DataNode(5);

        System.out.println("Original Linked List: ");
        printList(head);

        // Test Iterative Approach (Interview-Preferred)
        head = reverseListIterative(head);
        System.out.println("\nReversed Linked List (Iterative): ");
        printList(head);

        // Reverse back using Recursive Approach
        head = reverseListRecursive(head);
        System.out.println("\nReversed Back (Recursive): ");
        printList(head);
    }
}