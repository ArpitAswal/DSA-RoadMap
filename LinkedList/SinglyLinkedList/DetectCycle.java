// Detect Cycle in a Singly Linked List

// Given head, the head of a linked list, determine if the linked list has a cycle in it.
// There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.

// Examples:

// Input: Linked List: 1 -> 2 -> 3 -> 4 -> (connects back to 2)
// Output: true
// Explanation: Following next pointers from node 4 leads back to node 2, forming an infinite loop cycle.

// Input: Linked List: 1 -> 2 -> 3 -> null
// Output: false
// Explanation: The list terminates with null at node 3, so no cycle exists.

import java.util.HashSet;

class DataNode {
    int data;       // Value stored in the node
    DataNode next;  // Reference pointer to the next node

    // Constructor to initialize node with data
    DataNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class DetectCycle {

    /*
     * [Naive / HashSet Approach] Node Reference Hashing - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Maintain a HashSet of DataNode references to store visited nodes.
     *   2. Traverse linked list using a pointer `curr = head`:
     *        - If `curr` is already present in HashSet: cycle detected! Return true.
     *        - Else add `curr` to HashSet and move `curr = curr.next`.
     *   3. If traversal reaches `null`, list ends without a cycle -> Return false.
     *
     * Time Complexity  : O(n) - visits each node in list once.
     * Space Complexity : O(n) - HashSet stores up to n node references.
     */
    static boolean hasCycleHashSet(DataNode head) {
        HashSet<DataNode> visitedNodes = new HashSet<>(); // Set to store visited node references
        DataNode curr = head;                             // Traversal pointer starting at head

        // Traverse linked list until null end is reached
        while (curr != null) {
            // If current node reference was already seen, a cycle is present
            if (visitedNodes.contains(curr)) {
                return true;
            }

            visitedNodes.add(curr); // Add node reference to set
            curr = curr.next;       // Advance pointer to next node
        }

        return false; // Reached end of list without cycle
    }

    /*
     * [Optimal / Interview Approach] Floyd's Cycle Detection Algorithm (Slow & Fast Pointers) - O(n) Time and O(1) Space
     *
     * Logic / Steps:
     *   1. Initialize two pointers at head: `slow = head` and `fast = head`.
     *   2. Move `slow` by 1 step (`slow = slow.next`) and `fast` by 2 steps (`fast = fast.next.next`).
     *   3. If a cycle exists, `fast` pointer will eventually enter cycle and catch up to `slow` pointer (`slow == fast`).
     *   4. If `fast == null` or `fast.next == null`, list has no cycle and terminates.
     *
     * Why this is interview-preferred:
     *   - Detects cycle in linear time O(n) using strictly O(1) constant memory (no hash set required).
     *
     * Time Complexity  : O(n) - fast pointer traverses at most 2n nodes before meeting slow or hitting null.
     * Space Complexity : O(1) - constant space using two pointer references.
     */
    static boolean hasCycleFloyd(DataNode head) {
        // Base case: empty list or single node pointing to null cannot have cycle
        if (head == null || head.next == null) {
            return false;
        }

        DataNode slow = head; // Slow pointer moves 1 step at a time
        DataNode fast = head; // Fast pointer moves 2 steps at a time

        // Loop until fast pointer reaches end of list
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Advance slow by 1 step
            fast = fast.next.next;   // Advance fast by 2 steps

            // Pointers meet at same node -> cycle detected!
            if (slow == fast) {
                return true;
            }
        }

        // Fast pointer reached null -> no cycle
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Linked List with Cycle: 1 -> 2 -> 3 -> 4 -> 2
        DataNode head = new DataNode(1);
        head.next = new DataNode(2);
        head.next.next = new DataNode(3);
        head.next.next.next = new DataNode(4);
        head.next.next.next.next = head.next; // Create cycle: 4 -> 2

        System.out.println("Test Case 1 (List with Cycle):");
        System.out.println("1. HashSet Method Result : " + hasCycleHashSet(head));
        System.out.println("2. Floyd Method Result   : " + hasCycleFloyd(head));

        System.out.println();

        // Test Case 2: Linked List without Cycle: 10 -> 20 -> 30 -> null
        DataNode head2 = new DataNode(10);
        head2.next = new DataNode(20);
        head2.next.next = new DataNode(30);

        System.out.println("Test Case 2 (List without Cycle):");
        System.out.println("1. HashSet Method Result : " + hasCycleHashSet(head2));
        System.out.println("2. Floyd Method Result   : " + hasCycleFloyd(head2));
    }
}