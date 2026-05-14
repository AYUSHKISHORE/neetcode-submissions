class Solution {
    /*
        Time Complexity - O(N)
        Space Complexity - O(N)
        Approach
            -> In this question we need to take diff of days of next max temp.
            -> We create a stack of array to store temperature and index
            -> we loop across temperatures
                while stack is not empty and current Temp > stack 1st element{
                    pop and get details (diff)
                }
                push every element and temp in stack
    */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();
        for(int i=0;i<n;i++){
            int t = temperatures[i];
            while(!stack.isEmpty() && stack.peek()[0]<t ){
                int[]popDetails = stack.pop();
                int diff = i-popDetails[1];
                res[popDetails[1]]=diff;
            }
            stack.push(new int[]{t,i});
        }
        return res;

    }
}