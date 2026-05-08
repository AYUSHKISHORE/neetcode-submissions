class Solution {
    /*
        #Approach 1
            Time Complexity - O(2n) =~ O(n)
            Space Complexity - O(2n) =~ O(n)
    
            * we have 2 arrays left & right of size n
            * left array contains difference between max_left_at_index_i and height_at_index_i ; it traverse from 1->n-1
            * right array contains difference between max_right_at_index_i and height_at_index_i; it traverse from n-2->0
    
            * while traversing right array
            * compute the sum by choosing the smaller difference from left,right array
    
        #Approach-2
            time complexity - O(n)
            space compelxity - O(1)
    
            this a two pointer approach
            where we loop till i<=j
                if leftMax<rightMax
                    then compute the maxLeftHeight and then sum
                else
                    then compute the maxRightHeight and then sum
    
    
    */

    public int trap(int[] heights) {

        //Array based approach
        int approach_one_result = approachOne(heights);
        //return approach_one_result;

        //two pointer based approach
        int approach_two_result = approachTwo(heights);
        return approach_two_result;

    }

    public static int approachOne(int[] heights) {
        int max = heights[0];
        int water = 0;
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < heights.length; i++) {
            max = Math.max(heights[i], max);
            if (heights[i] < max) {
                left[i] = max - heights[i];
            }
        }
        int sum = 0;
        max = heights[heights.length - 1];
        for (int j = heights.length - 2; j >= 0; j--) {
            max = Math.max(heights[j], max);
            if (heights[j] < max) {
                right[j] = max - heights[j];
                sum += Math.min(right[j], left[j]);
            }
        }

        return sum;
    }

    public static int approachTwo(int[] heights) {
        int n = heights.length;
        int j = n - 1;
        int i = 0;
        int leftMax = heights[i];
        int rightMax = heights[j];
        int sum = 0;
        while (i <= j) {
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, heights[i]);
                sum += leftMax - heights[i];
                i++;
            } else {
                rightMax = Math.max(rightMax, heights[j]);
                sum += rightMax - heights[j];
                j--;
            }
        }
        return sum;
    }

}
