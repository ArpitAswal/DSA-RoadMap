// Valid Parentheses Check

// Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
// determine if the input string is valid.
// An input string is valid if:
// 1. Open brackets must be closed by the same type of brackets.
// 2. Open brackets must be closed in the correct order.
// 3. Every close bracket has a corresponding open bracket of the same type.

// Examples:

// Input: s = "{[()]}"
// Output: true
// Explanation: Every bracket opens and closes in matching pairs in proper nested order.

// Input: s = "{}}{"
// Output: false
// Explanation: The third bracket '}' has no matching open '{' bracket in stack.

// Input: s = "([)]"
// Output: false
// Explanation: ']' closes before ')' is closed, violating order.

import java.util.Stack;

class ValidParenthesis {

    /*
     * [Naive Approach] String Replacement Elimination - O(n^2) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Continuously search for adjacent matching pairs "()", "{}", or "[]" in string.
     *   2. Replace matching pairs with empty string "".
     *   3. Repeat until no more matching pairs exist in string.
     *   4. If final string is empty "", string is valid; otherwise invalid.
     *
     * Drawback:
     *   - String concatenation and replace operations create new string objects in O(n) per pass, leading to O(n^2) time.
     *
     * Time Complexity  : O(n^2) - up to n/2 passes, each taking O(n) string manipulation time.
     * Space Complexity : O(n)   - stores intermediate modified string objects.
     */
    static boolean isValidNaive(String s) {
        // Continue replacing valid pairs as long as any of "()", "{}", or "[]" exists
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }

        // If string becomes completely empty, all parentheses matched correctly
        return s.length() == 0;
    }

    /*
     * [Optimal / Interview Approach] Using Stack - O(n) Time and O(n) Space
     *
     * Logic / Steps:
     *   1. Create a Stack of Characters to track unmatched open brackets.
     *   2. Traverse string character by character:
     *        a. If character is an open bracket ('(', '{', '['): push onto stack.
     *        b. If character is a closing bracket (')', '}', ']'):
     *             - If stack is empty: mismatch! Return false immediately.
     *             - Pop top character from stack.
     *             - Check if popped open bracket matches current closing bracket.
     *             - If mismatched (e.g. '(' with '}'): return false immediately.
     *   3. After traversal completes, if stack is empty: return true; else return false (unclosed brackets remaining).
     *
     * Why this is interview-preferred:
     *   - Processes string in a single linear pass O(n).
     *   - Stack naturally enforces LIFO (Last-In-First-Out) nested parenthesis matching order.
     *
     * Time Complexity  : O(n) - single pass over string of length n.
     * Space Complexity : O(n) - stack stores up to n characters in worst case.
     */
    static boolean isValidStack(String s) {
        Stack<Character> stack = new Stack<>(); // Stack to keep track of opening brackets

        // Loop through each character of the input string
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); // Read character at index i

            // If current character is an opening bracket, push it onto stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // If closing bracket encountered but stack is empty -> no matching open bracket
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop(); // Retrieve and remove top opening bracket from stack

                // Verify matching bracket pairs
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false; // Mismatch found -> invalid string
                }
            }
        }

        // String is valid only if all opening brackets were matched and popped
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str1 = "{[()]}";
        String str2 = "{}}{";

        System.out.println("String 1: \"" + str1 + "\"");
        System.out.println("1. Naive Approach Result : " + isValidNaive(str1));
        System.out.println("2. Stack Approach Result : " + isValidStack(str1));

        System.out.println("\nString 2: \"" + str2 + "\"");
        System.out.println("1. Naive Approach Result : " + isValidNaive(str2));
        System.out.println("2. Stack Approach Result : " + isValidStack(str2));

        System.out.println("\n--- Additional Tests ---");
        String[] tests = { "()", "()[]{}", "(]", "([)]", "{[]}" };
        for (String t : tests) {
            System.out.println("String: \"" + t + "\" -> Valid: " + isValidStack(t));
        }
    }
}