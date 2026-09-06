/*
 * Problem Statement: Rotate Matrix by 90 Degrees
 * 
 * Given an N * N 2D integer matrix, rotate the matrix by 90 degrees clockwise.
 * 
 * Example 1:
 * Input : matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
 * Output : [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
 * Explanation: The matrix is rotated 90 degrees clockwise in-place.
 * 
 * Example 2:
 * Input : matrix = [[5, 1, 9, 11], [2, 4, 8, 10], [13, 3, 6, 7], [15, 14, 12, 16]]
 * Output : [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]
 */

class RotateMatrix {

    /*
     * [Naive Approach]
     * Create a new dummy matrix of the same size. Then, traverse the original matrix
     * and place the elements in the dummy matrix such that dummy[j][n - 1 - i] = matrix[i][j].
     * Finally, copy the elements from the dummy matrix back to the original matrix.
     */
    public static void rotate90Naive(int[][] matrix) {
        int n = matrix.length;
        int[][] dummy = new int[n][n];
        
        // Copy elements to the dummy matrix in rotated positions
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dummy[j][n - 1 - i] = matrix[i][j];
            }
        }
        
        // Copy back to original matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = dummy[i][j];
            }
        }
    }

    /*
     * [Optimal Approach]
     * We can do this in place using two steps:
     * 1. Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
     * 2. Reverse each row of the transposed matrix.
     */
    public static void rotate90Optimal(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose
        transpose(matrix, n);

        // Step 2: Reverse each row
        reverse(matrix, n);
    }

    // Helper method to transpose the matrix
    private static void transpose(int[][] arr, int n) {
        for (int i = 0; i < n; i++) {
            // Note: j starts from i to only traverse the upper triangle and avoid double swapping
            for (int j = i; j < n; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    // Helper method to reverse each row
    private static void reverse(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;

            // Swap elements from both ends moving toward the center
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        int[][] matrix2 = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16} }; 
        
        System.out.println("--- Naive Approach ---");
        System.out.println("Original matrix1:");
        printMatrix(matrix1);
        rotate90Naive(matrix1);
        System.out.println("Rotated matrix1:");
        printMatrix(matrix1);

        System.out.println("\n--- Optimal Approach ---");
        System.out.println("Original matrix2:");
        printMatrix(matrix2);
        rotate90Optimal(matrix2);
        System.out.println("Rotated matrix2:");
        printMatrix(matrix2);
    }

    // Helper method to print a 2D matrix
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j < matrix[0].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
}

/*
 * Complexity Analysis:
 * 
 * 1. Naive Approach:
 *    - Time Complexity: O(N^2), as we traverse the matrix of size N x N twice.
 *    - Space Complexity: O(N^2), as we use an extra N x N dummy matrix to store 
 *      the rotated elements.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(N^2). Transposing takes O(N^2 / 2) time, and reversing 
 *      each row takes O(N^2 / 2) time. Total time is strictly O(N^2).
 *    - Space Complexity: O(1), as the rotation is done in place.
 */