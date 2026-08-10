// Find the Middle Node of a Singly Linked List

// Given the head of a singly linked list, return the middle node of the linked list.
// If there are two middle nodes (i.e. even number of nodes), return the second middle node.

// Examples:

// Input:  1 -> 2 -> 3 -> 4 -> 5 -> null
// Output: Node with value 3
// Explanation: The middle node of the list is 3.

// Input:  1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
// Output: Node with value 4
// Explanation: List has 6 nodes. The middle nodes are 3 and 4, so we return the second middle node 4.

class FindMiddleNode {

    /*
     * [Naive Approach] Length Count (Two Passes) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Pass 1: Traverse entire linked list to count total number of nodes `length`.
     *   2. Calculate middle index: `mid = length / 2`.
     *   3. Pass 2: Traverse list from head for `mid` steps to reach the middle node.
     *   4. Return the middle node.
     *
     * Time Complexity  : O(n) - two passes: first pass counts n nodes, second pass advances n/2 nodes.
     * Space Complexity : O(1) - constant auxiliary memory.
     */
    static DataNode findMiddleNaive(DataNode head) {
        if (head == null) return null;

        int length = 0;
        DataNode curr = head;

        // Pass 1: Count total nodes in list
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        int mid = length / 2; // Index of middle node (0-based)
        curr = head;

        // Pass 2: Advance to middle node
        for (int i = 0; i < mid; i++) {
            curr = curr.next;
        }

        return curr;
    }

    /*
     * [Optimal / Interview Approach] Slow and Fast Pointers (Tortoise and Hare) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Maintain two pointers starting at head: `slow = head` and `fast = head`.
     *   2. Loop while `fast != null` and `fast.next != null`:
     *        - Advance `slow` by 1 step: `slow = slow.next`.
     *        - Advance `fast` by 2 steps: `fast = fast.next.next`.
     *   3. When `fast` reaches the end of the list (or null), `slow` will be pointing exactly at the middle node!
     *   4. Return `slow`.
     *
     * Why this is interview-preferred:
     *   - Finds the middle node in a SINGLE pass O(n) instead of two passes.
     *   - Uses O(1) constant auxiliary space.
     *
     * Time Complexity  : O(n) - single pass traversing n nodes with fast pointer.
     * Space Complexity : O(1) - constant auxiliary memory.
     */
    static DataNode findMiddleSlowFast(DataNode head) {
        if (head == null) return null;

        DataNode slow = head; // Moves 1 node at a time
        DataNode fast = head; // Moves 2 nodes at a time

        // Traverse until fast pointer reaches end of list
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Move slow pointer 1 step forward
            fast = fast.next.next; // Move fast pointer 2 steps forward
        }

        return slow; // Slow pointer now points to middle node
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
        // Test Case 1: Odd length list (1 -> 2 -> 3 -> 4 -> 5)
        DataNode head1 = new DataNode(1);
        head1.next = new DataNode(2);
        head1.next.next = new DataNode(3);
        head1.next.next.next = new DataNode(4);
        head1.next.next.next.next = new DataNode(5);

        System.out.println("Test Case 1 (Odd length list):");
        printList(head1);
        System.out.println("Middle Node (Naive)     : " + findMiddleNaive(head1).data);
        System.out.println("Middle Node (Slow-Fast) : " + findMiddleSlowFast(head1).data);

        System.out.println();

        // Test Case 2: Even length list (1 -> 2 -> 3 -> 4 -> 5 -> 6)
        DataNode head2 = new DataNode(1);
        head2.next = new DataNode(2);
        head2.next.next = new DataNode(3);
        head2.next.next.next = new DataNode(4);
        head2.next.next.next.next = new DataNode(5);
        head2.next.next.next.next.next = new DataNode(6);

        System.out.println("Test Case 2 (Even length list):");
        printList(head2);
        System.out.println("Middle Node (Naive)     : " + findMiddleNaive(head2).data);
        System.out.println("Middle Node (Slow-Fast) : " + findMiddleSlowFast(head2).data);
    }
}