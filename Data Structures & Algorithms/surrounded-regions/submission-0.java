class Solution {

    /*
        Logic - We perform the DFS from the boundary O if found...and mark  all the O in way as S 

        post DFS we will again to nested loops and convert all S (mean O which reached from boundary O) to O and all other O to X

        Time 
            - O(n*m)

        Space
            - O(n*m)

        Note - this can be done by any traversal methodology

    */

    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int boundaryZeros = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if(r==0 || r==rows-1 || c== 0 || c==cols-1){
                    if (board[r][c] == 'O') {
                         dfs(board, r, c);
                    }
                }
                
            }
        }
        for(int r = 0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(board[r][c]=='O'){
                    board[r][c]='X';
                }
                if(board[r][c]=='S'){
                    board[r][c]='O';
                }
            }
        }
    }

    public void dfs(char[][] board, int r, int c){
        int rows = board.length;
        int cols = board[0].length;

        Stack<int[]> stack = new Stack<>();

        int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };
            
        board[r][c]='S';
        stack.push(new int[]{r,c});

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            for(int[] direction : directions){
                int nr = current[0]+direction[0];
                int nc = current[1]+direction[1];

                if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                    continue;
                }
                
                /*
                if(board[nr][nc]!='O' && board[nr][nc]!='S'){
                    continue;
                }
                //this above condition will cause TLE [0,0] -> [S,0] -> [S,S]
                */
                if(board[nr][nc]!='O'){
                    continue;
                }

                board[nr][nc]='S';

                stack.push(new int[]{nr,nc});
            }
        }
    }
}