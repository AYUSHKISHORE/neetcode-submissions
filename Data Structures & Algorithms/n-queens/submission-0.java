class Solution {


    /*
        Time
            Traversal in single board = N! 
                explanation 
                    1st put of queen we can traverse N-1 row
                    2nd put of queen we can traverse N-2 row
                    ... upto 1
            
            Total Col traversal could be N

            Total Traversal on board = O(N * N!)

            Total Solution = Sn
            Form Response = O(N^2)

            Total Time = O(N*N!) + O(Sn + N^2) (why + instead O(N*N!) * O(Sn + N^2) because we are calling formresponse at the last of computational traversal)

        Space
            Auxiliary Space (Auxiliary space is the extra temporary memory an algorithm uses while running, excluding the memory used to store the final output.)
            
                chess: O(n^2)
                Three sets: O(n)
                Recursion stack: O(n)
                Temporary path: O(n^2) characters for a completed board

                Total Space = O(n^2) + O(n) + O(n) + O(n^2) = O(n^2)

            Computation Space (here result)
                result = Sn

            Total Space = O(Sn) + O(n^2) = O(n^2)
            



    */


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] chess = new char[n][n];

        //Note in Set store position of that index
        Set<Integer> rightDiagonal = new HashSet<>();
        Set<Integer> leftDiagonal = new HashSet<>();
        Set<Integer> sameCol = new HashSet<>();

        for(int i=0; i<n; i++){
            Arrays.fill(chess[i],'.');
            
        }
        backtrack(chess, result, rightDiagonal, leftDiagonal, sameCol, n, 0);

        return result;
    }

    public void backtrack(char [][] chess, List<List<String>> result, Set<Integer> rightDiagonal, Set<Integer> leftDiagonal, Set<Integer> sameCol, int n, int row){

        if(row == n){
            formResponse(chess, n, result);
            return;
        }


        for(int col=0 ; col<n ; col++){
            
            //Note in Set store position of that index
            int leftDiagonalIndex = row - col;
            int rightDiagonalIndex = row + col;
            
            if(!rightDiagonal.contains(rightDiagonalIndex) && !leftDiagonal.contains(leftDiagonalIndex) && !sameCol.contains(col)){

                chess[row][col]='Q';
                rightDiagonal.add(rightDiagonalIndex);
                leftDiagonal.add(leftDiagonalIndex);
                sameCol.add(col);


                backtrack(chess,result,rightDiagonal,leftDiagonal,sameCol,n,row+1);
                
                chess[row][col]='.';
                rightDiagonal.remove(rightDiagonalIndex);
                leftDiagonal.remove(leftDiagonalIndex);
                sameCol.remove(col);
            }
        }
    }

    public void formResponse(char [][]chess,int n, List<List<String>> result){

        List<String> path = new ArrayList<>();
        for(int i = 0; i<n; i++){
             path.add(new String(chess[i]));
        }
        result.add(new ArrayList<>(path));


        /*
            if we use chess[][] String instead of char then we should write

            List<String> path = new ArrayList<>();
            for(int i = 0; i<n; i++){
                path.add(String.join("", chess[i]));
            }
            result.add(new ArrayList<>(path));


            explanation
            String.join(separator, elements)
            [".","Q",".","."] -> ".Q.."

            
        */
    }
}