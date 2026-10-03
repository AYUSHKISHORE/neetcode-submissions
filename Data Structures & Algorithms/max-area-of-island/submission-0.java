class Solution {
    /*
        Logic
            Loop across the grid , whenever 1 is found traverse (DFS/BFS) keep counting the attached 1s

            Note - (Imp) once a cell is visited make it 0 so that it won't get recalculated.

            Time 
                DFS ITERATIVE - O(n*m)
                DFS RECURSIVE - O(n*m)
            Space
                DFS ITERATIVE - O(n*m) stack<int[]>
                DFS RECURSIVE - O(n*m) recursion stack


    */


    public int maxAreaOfIsland(int[][] grid) {
        int maxArea=0;
        for(int r = 0; r<grid.length;r++){
            for(int c =0;c<grid[0].length;c++){
                if(grid[r][c]==1){
                    //int area = dfsRecursive(grid,r,c,0);
                    int area = dfsIterative(grid,r,c,0);
                    maxArea = Math.max(area,maxArea);

                }
            }
        }
        return maxArea;
    }

    public int dfsRecursive(int[][]grid, int r, int c, int area){

        int rows = grid.length;
        int cols = grid[0].length;

        if(r<0 || r>=rows || c<0 || c>=cols){
            return 0;
        }

        if(grid[r][c]==0){
            return 0;
        }

        area=1;
        grid[r][c]=0;

        area += dfsRecursive(grid,r+1,c,area);
        area += dfsRecursive(grid,r-1,c,area);
        area += dfsRecursive(grid,r,c+1,area);
        area += dfsRecursive(grid,r,c-1,area);
        
        return area;

    }

    public int dfsIterative(int[][] grid,int r, int c, int area){
        int rows = grid.length;
        int cols = grid[0].length;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{r,c});
        area++;
        grid[r][c]=0;

        int[][] directions = {
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
        };

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];

            for(int[] direction : directions){
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if(newRow<0 || newRow>=rows || newCol<0 || newCol>=cols){
                    continue;
                }

                if(grid[newRow][newCol]==0){
                    continue;
                }
                area++;
                grid[newRow][newCol]=0;

                stack.push(new int[]{newRow,newCol});
            }

        }
        return area;

    }
}