class Solution {

    /*
        Approach 1 
            Time Complexity - O(logN + M)
            Space Complexity - O(1)

            In this approach we will flatten the matrix as it is in increasing order
            left = 0
            right = mat.length * mat[0].length - 1

            mid = (left) + (right - left) / 2;
            row = mid / mat[0].length;
            col = mid % mat[0].length;

            based on row and col find the mid by moving left and right

            eg
            1 2 3
            4 5 6
            7 8 9

            left = 0
            right = 8 = mat.length * mat[0].length - 1 = 3*3 -1

            mid = left + (right - left) / 2 => 0 + (8 - 0)/2 => 4
            element at 4th index = 5

            row = mid / 3 = 4/3 = 1
            col = mid % 3 = 4%3 = 1
            element 5 is at row = 1 and col = 1

        Approach 2
            Time Complexity - O(logN) + O(logM)
            Space Complexity - O(1)

            In this approach we first identify the correct row using binary search rowWise

            major check
             else if (matrix[mid][m - 1] < target) {
                row_wise_left = mid + 1;
            } else if (matrix[mid][0] > target) { // In this case we check for first element is big the move right back else we check everything with last element
                row_wise_right = mid - 1;
            }

    */


    public boolean searchMatrix(int[][] matrix, int target) {

        boolean result_approach1 = approach1(matrix, target);
        boolean result_approach2 = approach1(matrix, target);

        return result_approach1;

    }

    public boolean approach1(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;
        int left = 0;
        int right = n * m - 1;

        while (left <= right) {
            int mid = (int) left + (right - left) / 2;
            int row = mid / m;
            int col = mid % m;

            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                left = mid + 1;
            } else if (matrix[row][col] > target) {
                right = mid - 1;
            }
        }
        return false;
    }

    public boolean approach2(int[][] matrix, int target) {

        int n = matrix.length;
        int m = matrix[0].length;
        int row_wise_left = 0;
        int row_wise_right = matrix.length - 1;

        while (row_wise_left <= row_wise_right) {

            int mid = (int) row_wise_left + (row_wise_right - row_wise_left) / 2;
            if (matrix[mid][m - 1] == target) {
                return true;
            } else if (matrix[mid][0] <= target && target <= matrix[mid][m - 1]) {
                int col_wise_left = 0;
                int col_wise_right = m - 1;

                while (col_wise_left <= col_wise_right) {
                    int mid_col = (int) col_wise_left + (col_wise_right - col_wise_left) / 2;
                    if (matrix[mid][mid_col] == target) {
                        return true;
                    } else if (matrix[mid][mid_col] < target) {
                        col_wise_left = mid_col + 1;
                    } else if (matrix[mid][mid_col] > target) {
                        col_wise_right = mid_col - 1;
                    }
                }
                return false;
            } else if (matrix[mid][m - 1] < target) {
                row_wise_left = mid + 1;
            } else if (matrix[mid][0] > target) {
                row_wise_right = mid - 1;
            }

        }

        return false;

    }

}
