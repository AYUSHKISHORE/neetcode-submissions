class Solution {

    /*
        time complexity = O(N)
        space complexity = O(1)

        Logic
            -> we need to find max water to stored between 2 container
            -> two pointer approach
            -> i = 0 , j = last
            -> if(height[i]<height[j]) i++ else j--;
            -> compare the existing heights of container and whichever container is smaller move from that container hoping bigger container might increase some water capactiy

    */


    public int maxArea(int[] heights) {
        int i = 0;
        int j = heights.length -1;
        int max = Integer.MIN_VALUE;


        while(i<j){
            int water = Math.min(heights[i],heights[j]) * (j-i);
            max = water>max?water:max;
            if(heights[i]<heights[j]){
                i++;
                continue;
            }else{
                j--;
                continue;
            }

        }
        return max;
    }
}

