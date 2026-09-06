/*
 * Problem Statement: Spiral Matrix
 * 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * Example:
 * Input: Matrix[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } }
 * Output: 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
 * Explanation: The elements are traversed in a clockwise spiral starting from the 
 * top-left corner.
 */

class SpiralOrder {

    /*
     * [Optimal Approach]
     * Use four pointers (top, bottom, left, right) to represent the boundaries of the 
     * unvisited portion of the matrix. We traverse the top row (left to right), the 
     * right column (top to bottom), the bottom row (right to left), and the left column 
     * (bottom to top), shrinking the boundaries iteratively until all elements are visited.
     */
    private static int[] getSpiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] spiral = new int[m * n];
        int index = 0;

        // Initialize boundary pointers
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        // Loop until boundaries overlap
        while (top <= bottom && left <= right) {

            // Traverse from left to right along the top boundary
            for (int i = left; i <= right; i++) {
                spiral[index++] = matrix[top][i];
            }
            top++; // Shrink top boundary

            // Traverse from top to bottom along the right boundary
            for (int i = top; i <= bottom; i++) {
                spiral[index++] = matrix[i][right];
            }
            right--; // Shrink right boundary

            // Make sure we are on a different row before traversing right to left
            if (top <= bottom) {
                // Traverse from right to left along the bottom boundary
                for (int i = right; i >= left; i--) {
                    spiral[index++] = matrix[bottom][i];
                }
                bottom--; // Shrink bottom boundary
            }

            // Make sure we are on a different column before traversing bottom to top
            if (left <= right) {
                // Traverse from bottom to top along the left boundary
                for (int i = bottom; i >= top; i--) {
                    spiral[index++] = matrix[i][left];
                }
                left++; // Shrink left boundary
            }
        }

        return spiral;
    }

    public static void main(String[] args) {
        int[][] matrix = { 
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 16 } 
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        System.out.println("\n--- Optimal Approach (Spiral Order) ---");
        int[] spiralResult = getSpiralOrder(matrix);
        System.out.print("The array in spiral order is: ");
        printArray(spiralResult);
    }

    // Helper method to print a 1D array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
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
 * - Time Complexity: O(m * n), where m is the number of rows and n is the number of 
 *   columns. Every element in the matrix is visited exactly once.
 * - Space Complexity: O(m * n), to store the final result array. No extra auxiliary 
 *   space is required for the traversal itself.
 */