class Solution {

    /*
        We need find all position (i.e index) from which water can flow to both ocean
        (randomIndex_i)(randomIndex_j) -> from these index water should flow to pacific && atlantic
        
        Note - Logic is same for all 4 approach (3 DFS, 1 BFS)
            From the boundary elements move to the other elements (only if height[currElement(i.e Boundaryelement)]<= height[movingElement]) and also keep the track of index which are visited...
                This mean all visited index can move the water
            
            keep track for pacific and atlantic ... (i.e visited in different boolean array)


        Time
            approachOneRecursiveDFS1 - O(n*m)
            approachTwoRecursiveDFS2 - O(n*m)
            approachThreeIterativeStackDFS - O(n*m)
            approachFourIterativeBFS - O(n*m)

        Space
            approachOneRecursiveDFS1 - O(n*m) {Recursive stack}
            approachTwoRecursiveDFS2 - O(n*m) {Recursive stack}
            approachThreeIterativeStackDFS - O(n*m) {stack}
            approachFourIterativeBFS - O(n*m) {queue}
    */

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        //return approachOneRecursiveDFS1(heights);
        //return approachTwoRecursiveDFS2(heights);
        //return approachThreeIterativeStackDFS(heights);
        return approachFourIterativeBFS(heights);



    }

    //
    //
    // approachOne start
    public List<List<Integer>> approachOneRecursiveDFS1(int[][] heights){
        
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for(int r=0;r<rows;r++){
            //pacific 
            dfsRecursiveWayOne(heights,pacific,r,0);

            //atlantic
            dfsRecursiveWayOne(heights,atlantic,r,cols-1);
        }

        for(int c=0;c<cols;c++){
            //pacific
            dfsRecursiveWayOne(heights,pacific,0,c);
            
            //atlanntic
            dfsRecursiveWayOne(heights,atlantic,rows-1,c);
        }

        List<List<Integer>> result = new ArrayList<>();

        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(pacific[r][c] && atlantic[r][c]){
                    result.add(Arrays.asList(r,c));
                }
            }
        }

        return result;
    }

    public void dfsRecursiveWayOne(int[][] heights, boolean[][]visited, int r, int c){
        int rows = heights.length;
        int cols = heights[0].length;

        if(r<0 || r>=rows || c<0 || c>=cols){
            return;
        }
        if(visited[r][c]){
            return;
        }
        visited[r][c]=true;

        int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };

        for(int[] direction : directions){
            int nr = r + direction[0];
            int nc = c + direction[1];

            if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                continue;
            }

            if(visited[nr][nc]){
                continue;
            }
            
            if(heights[r][c]>heights[nr][nc]){
                continue;
            }

            //visited[nr][nc]=true;
            //important recursively moving again
            dfsRecursiveWayOne(heights,visited,nr,nc);
        }

    }

    //
    //
    //approachOne end


    //approachTwo start
    public List<List<Integer>> approachTwoRecursiveDFS2(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for(int r=0;r<rows;r++){

            //pacific
            dfsRecursiveWayTwo(heights,pacific,r,0, heights[r][0]);

            //atlantic
            dfsRecursiveWayTwo(heights,atlantic,r,cols-1,heights[r][cols-1]);
        }

        for(int c=0;c<cols;c++){

            //pacific
            dfsRecursiveWayTwo(heights,pacific,0,c,heights[0][c]);

            //atlantic
            dfsRecursiveWayTwo(heights,atlantic,rows-1,c,heights[rows-1][c]);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(pacific[r][c] && atlantic[r][c]){
                    result.add(Arrays.asList(r,c));
                }
            }
        }
        return result;
    }

    public void dfsRecursiveWayTwo(int[][]heights, boolean[][]visited, int r, int c, int prvsHeight){
        int rows = heights.length;
        int cols = heights[0].length;



        if(r<0 || r>=rows || c<0 || c>=cols){
            return;
        }

        if(heights[r][c]<prvsHeight){
            return;
        }

        if(visited[r][c]){
            return;
        }

        visited[r][c]=true;

        dfsRecursiveWayTwo(heights,visited,r-1,c,heights[r][c]);
        dfsRecursiveWayTwo(heights,visited,r+1,c,heights[r][c]);
        dfsRecursiveWayTwo(heights,visited,r,c-1,heights[r][c]);
        dfsRecursiveWayTwo(heights,visited,r,c+1,heights[r][c]);

    }

    //approachTwo end

    //approachThree start
    public List<List<Integer>> approachThreeIterativeStackDFS(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for(int r=0;r<rows;r++){
            //pacific
            dfsIterativeStack(heights,pacific,r,0);

            //atlantic
            dfsIterativeStack(heights,atlantic,r,cols-1);
        }

        for(int c=0;c<cols;c++){
            //pacific
            dfsIterativeStack(heights,pacific,0,c);

            //atlantic
            dfsIterativeStack(heights,atlantic,rows-1,c);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(pacific[r][c] && atlantic[r][c]){
                    result.add(Arrays.asList(r,c));
                }
            }
        }

        return result;

    }

    public void dfsIterativeStack(int[][] heights, boolean[][]visited, int r, int c){

        int rows = heights.length;
        int cols = heights[0].length;

        Stack<int[]> stack = new Stack<>();
        
        if(visited[r][c]){
            return;
        }
        visited[r][c]=true;

        stack.push(new int[]{r,c});

        int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };

        while(!stack.isEmpty()){
            int[] current = stack.pop();
            
            for(int[] direction : directions){
                int nr = current[0] + direction[0];
                int nc = current[1] + direction[1];

                if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                    continue;
                }

                if(visited[nr][nc]){
                    continue;
                }

                if(heights[nr][nc]<heights[current[0]][current[1]]){
                    continue;
                }
                visited[nr][nc]=true;
                stack.push(new int[]{nr,nc});

            }
        }

    }
    //approachThree end

    //approachFour start
    public List<List<Integer>> approachFourIterativeBFS(int[][] heights){
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for(int r=0;r<rows;r++){
            //pacific
            bfsIterativeQueue(heights,pacific,r,0);

            //atlantic
            bfsIterativeQueue(heights,atlantic,r,cols-1);
        }

        for(int c=0;c<cols;c++){
            //pacific
            bfsIterativeQueue(heights,pacific,0,c);

            //atlantic
            bfsIterativeQueue(heights,atlantic,rows-1,c);
        }

        List<List<Integer>> result = new ArrayList<>();
        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(pacific[r][c] && atlantic[r][c]){
                    result.add(Arrays.asList(r,c));
                }
            }
        }

        return result;

    }

    public void bfsIterativeQueue(int[][] heights, boolean[][] visited, int r, int c){
        int rows = heights.length;
        int cols = heights[0].length;

        Queue<int[]> queue = new LinkedList<>();
        
        if(visited[r][c]){
            return;
        }
        visited[r][c]=true;

        queue.offer(new int[]{r,c});

        int[][] directions = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
        };

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            
            for(int[] direction : directions){
                int nr = current[0] + direction[0];
                int nc = current[1] + direction[1];

                if(nr<0 || nr>=rows || nc<0 || nc>=cols){
                    continue;
                }

                if(visited[nr][nc]){
                    continue;
                }

                if(heights[nr][nc]<heights[current[0]][current[1]]){
                    continue;
                }
                visited[nr][nc]=true;
                queue.offer(new int[]{nr,nc});

            }
        }
    }



}