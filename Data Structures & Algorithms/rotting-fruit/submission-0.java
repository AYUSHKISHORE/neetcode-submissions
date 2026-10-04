class Solution {

    /*
        Note - both approaches uses 1 basic concept of storing the bad orange in queue...then from bad orange perfoming the next steps

            in approachOneBFSDistanceBased
                -> we are checking the farthest distance from bad orange just like we did in previos question (walls and gates)

            in approachTwoBFSSizeBased
                -> we are checking the taking the level/size based and for each size increasing time by 1
            
        Time
            - approach1 = O(n*m)
            - approach1 = O(n*m)
        Space
            - approach1 = O(n*m)
            - approach2 = O(n*m)



    */


    public int orangesRotting(int[][] grid) {

        //return approachOneBFSDistanceBased(grid);
        return approachTwoBFSSizeBased(grid);
        
    }

    public int approachOneBFSDistanceBased(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int ones=0;
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(grid[r][c]==2){
                    queue.offer(new int[]{r,c});
                }
                if(grid[r][c]==1){
                    ones++;
                }
            }
        }

        int[][] directions = {
            {-1,0},//up
            {1,0},//down
            {0,-1},//left
            {0,1}//down
        };

        int max = 0;
        while(!queue.isEmpty()){
            int[]current = queue.poll();
            int r = current[0];
            int c = current[1];

            for(int[] direction : directions){
                int nr = r + direction[0];
                int nc = c + direction[1];

                if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                    continue;
                }

                if(grid[nr][nc]!=1){
                    continue;
                }
                ones--;

                grid[nr][nc]=grid[r][c]+grid[nr][nc];
                if(max<grid[nr][nc]){
                    max = grid[nr][nc];
                }

                queue.offer(new int[]{nr,nc});

            }
        }

        if(ones>0){
            return -1;
        }

        return max-2>0?max-2:0;
    }

    public int approachTwoBFSSizeBased(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int nosOfOnes=0;
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(grid[r][c]==2){
                    queue.offer(new int[]{r,c});
                }
                if(grid[r][c]==1){
                    nosOfOnes++;
                }
            }
        }

        int[][] directions ={
            {-1,0},
            {1,0},
            {0,1},
            {0,-1}
        };


        int time = 0;
        
        if(nosOfOnes==0){
            return 0;
        }

        while(!queue.isEmpty()){
            
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] current = queue.poll();
                
                int r = current[0];
                int c = current[1];

                for(int[] direction : directions){
                    int nr = r + direction[0];
                    int nc = c + direction[1];


                    if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                        continue;
                    }

                    if(grid[nr][nc]!=1){
                        continue;
                    }
                    nosOfOnes--;

                    grid[nr][nc]=grid[r][c]+1;
                    queue.add(new int[]{nr,nc});
                } 
            }
            time++;
        }

        if(nosOfOnes>0){
            return -1;
        }

        return time-1;
    }
}