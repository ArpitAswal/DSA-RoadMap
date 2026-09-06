/*
 * Problem Statement: Set Matrix Zeroes
 * 
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row 
 * and column to 0's. You must do it in place.
 * 
 * Example 1:
 * Input: matrix = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
 * Output: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
 * Explanation: Since matrix[1][1] = 0. Therefore the 2nd column and 2nd row will be set to 0.
 * 
 * Example 2:
 * Input: matrix = [[0, 1, 2, 0], [3, 4, 5, 2], [1, 3, 1, 5]]
 * Output: [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
 * Explanation: matrix[0][0] and matrix[0][3] are 0, so the 1st and 4th columns, 
 * along with the 1st row, are all set to 0.
 */

class SetMatrix {

    /*
     * [Better Approach]
     * Use two auxiliary arrays to keep track of the rows and columns that need to be zeroed.
     * We iterate through the matrix, and if matrix[i][j] == 0, we mark row[i] and col[j] as 1.
     * Then we iterate again and set matrix[i][j] = 0 if row[i] == 1 or col[j] == 1.
     */
    public void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] rowMarker = new int[m];
        int[] colMarker = new int[n];

        // First pass: mark the rows and columns that contain a 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowMarker[i] = 1;
                    colMarker[j] = 1;
                }
            }
        }

        // Second pass: set the elements to 0 based on the markers
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rowMarker[i] == 1 || colMarker[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
     * [Optimal Approach]
     * Use the first row and first column of the matrix itself as the marker arrays.
     * We need two additional variables (or just flags) to track if the first row 
     * and first column originally contained any zeroes, to avoid overlapping conflicts.
     */
    public void setZeroesOptimal(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Flag to track if first row should be zeroed
        boolean firstRowZero = false;
        // Flag to track if first column should be zeroed
        boolean firstColZero = false;

        // Check if first row has any zero
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // Check if first column has any zero
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // Use first row and column as markers
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark the row
                    matrix[0][j] = 0; // Mark the column
                }
            }
        }

        // Set cells to zero based on markers (excluding first row and column)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Finally, zero the first row if needed
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // Finally, zero the first column if needed
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

public class MatrixZero {
    public static void main(String[] args) {
        SetMatrix obj = new SetMatrix();
        
        int[][] matrix1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] matrix2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };

        System.out.println("--- Better Approach ---");
        obj.setZeroesBetter(matrix1);
        printMatrix(matrix1);

        System.out.println("\n--- Optimal Approach ---");
        obj.setZeroesOptimal(matrix2);
        printMatrix(matrix2);
    }
    
    // Helper function to print a 2D matrix
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
 * 1. Better Approach:
 *    - Time Complexity: O(2 * M * N) = O(M * N). We traverse the entire matrix twice.
 *    - Space Complexity: O(M + N), as we use two extra arrays to mark the rows and columns.
 * 
 * 2. Optimal Approach:
 *    - Time Complexity: O(2 * M * N) = O(M * N). We traverse the matrix twice. 
 *    - Space Complexity: O(1). We use the first row and first column of the given 
 *      matrix itself to store the markers, avoiding any auxiliary space.
 */
