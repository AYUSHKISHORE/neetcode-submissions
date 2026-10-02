class Solution {

    /*
        Logic 
            Loop across the grid if '1' is found then traverse DFS/BFS till it find 1 ...and once 1 is find convert is 0 ...to reach again

        approachOneDfsRecursive
            Time - O(n*m)
            Space - O(n*m) stack memory

        approachTwoDfsIterative
            Time - O(n*m)
            Space - O(n*m) stack<int[]>

        approachThreeIterativeBFS
            Time - O(n*m)
            Space - O(n*m) queue<int[]>    



    */
    public int numIslands(char[][] grid) {
        
         int islands = 0;

        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[0].length; c++){
                if(grid[r][c]=='1'){
                    islands++;
                    //approachOneDfsRecursive(grid,r,c); // Recursive DFS
                    //approachTwoDfsIterative(grid,r,c); // Iterative DFS
                    approachThreeIterativeBFS(grid,r,c); //Iterative BFS
                }
            }
        }

        return islands;
    }

    public void approachOneDfsRecursive(char[][] grid, int r , int c){

        int n = grid.length;
        int m = grid[0].length;

        if(r>=n || c>=m || r<0 || c<0){
            return;
        }

        if(grid[r][c]=='0'){
            return;
        }

        grid[r][c]='0';


        approachOneDfsRecursive(grid,r+1,c);
        approachOneDfsRecursive(grid,r-1,c);
        approachOneDfsRecursive(grid,r,c+1);
        approachOneDfsRecursive(grid,r,c-1);

        return;
    }

    public void approachTwoDfsIterative(char[][]grid, int r, int c){
        int n = grid.length;
        int m = grid[0].length;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r,c});

        grid[r][c]='0';
        int[][] directions = {
            {-1,0},//up
            {1,0}, //down
            {0,-1},//left
            {0,1}//right
        };

        while(!stack.isEmpty()){

            int[]current = stack.pop();
            int row = current[0];
            int col = current[1];

            for(int[] direction : directions){
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if(newRow<0 || newRow>=n || newCol<0 || newCol>=m){
                    continue;
                }

                if(grid[newRow][newCol]=='0'){
                    continue;
                }

                grid[newRow][newCol]='0';
                stack.push(new int[]{newRow,newCol});
            }

        }
    }

    public void approachThreeIterativeBFS(char[][]grid, int r, int c){
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r,c});

        grid[r][c]='0';
        int[][] directions = {
            {-1,0},//up
            {1,0},//down
            {0,-1},//left
            {0,1}//right
        };

        while(!queue.isEmpty()){
            int[]current = queue.poll();
            int row = current[0];
            int col = current[1];

            for(int[] direction: directions){
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if(newRow<0 || newRow>=rows || newCol<0 || newCol>=cols){
                    continue;
                }

                if(grid[newRow][newCol]=='0'){
                    continue;
                }

                grid[newRow][newCol] = '0';

                queue.offer(new int[]{newRow,newCol});
            }
        }
    }
}