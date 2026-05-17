class Solution {

    /*
        time complexity - O(N)
        space complexity - O(N)

        Approach -
        A monotonic increasing stack is a stack that stores elements in increasing order, where each new element is greater than or equal to the element below it.
        
        This solution uses a monotonic increasing stack where each stack element stores a pair of [startIndex, height]. As we iterate through the histogram, we keep pushing bars while heights are increasing. Whenever we encounter a smaller height, it means the taller bars in the stack cannot extend further to the right, so we pop them and calculate their maximum possible rectangle area using height * width, where width is determined by the current index and the popped bar’s starting index. While popping, we update the startIndex so the current smaller height can extend back to the earliest valid position. After the traversal, some bars may still remain in the stack, meaning they can extend till the end of the array, so we process them separately. Since each bar is pushed and popped at most once, the overall time complexity is O(n) with O(n) auxiliary stack space.

    */

    //Important input = [2,5,6,1]
    //O/P = 10
    public int largestRectangleArea(int[] heights) {
        
        int maxArea = 0;
        Stack<int[]> stack = new Stack<>();

        for(int i=0;i<heights.length;i++){

            int start = i;
            while(!stack.isEmpty() && stack.peek()[1]>heights[i]){
                int[] pop = stack.pop();
                int index = pop[0];
                int popHeight = pop[1];
                maxArea = Math.max(maxArea, popHeight * (i - index));
                start = index;
            }
            stack.push(new int[]{start,heights[i]});
        }

        for(int[] pair : stack){
            int index = pair[0];
            int popHeight = pair[1];
            maxArea = Math.max(maxArea, popHeight * (heights.length - index));
        }

        return maxArea;
    }
}