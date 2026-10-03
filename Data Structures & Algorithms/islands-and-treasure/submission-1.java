class Solution {

    /*
        Logic 
            * We'll have a BFS solution...as we need nearer value.
            * We'll store all the destination points in queue
                * poll the value from queue and update the path 

        All `0` cells are added to the queue first, so BFS starts from all treasures together.

        The nearest treasure reaches a cell first and sets its distance. Once the distance is set,  that cell is not `INF` anymore, so a farther treasure cannot overwrite it.


        Time 
            - O(n*m)
        Space 
            -O(n*m) = queue

    */
    public void islandsAndTreasure(int[][] grid) {
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                if(grid[r][c]==0){
                    queue.offer(new int[]{r,c});
                }
            }
        }


        int[][] directions = {
            {1,0},//down
            {-1,0},//up
            {0,1},//right
            {0,-1}//left
        };

        while(!queue.isEmpty()){
            int[]current = queue.poll();
            int r = current[0];
            int c = current[1];

            for(int[] direction :directions){
                int newRow = r + direction[0];
                int newCol = c + direction[1];

                if(newRow<0 || newRow>=rows || newCol<0 || newCol>=cols){
                    continue;
                }
                

                //Apart from way all other value to rejected (like - alreadyprocessed, 0 , -1)
                if(grid[newRow][newCol]!=Integer.MAX_VALUE){
                    continue;
                }

                grid[newRow][newCol] = grid[r][c] +1;
                queue.offer(new int[]{newRow,newCol});
            }
        }
    }
}
