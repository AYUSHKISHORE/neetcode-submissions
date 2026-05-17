class Solution {
    /*
        Time Complexity - O(N) + O(NlogN) + O(N) = O(NlogN)
        Space Complexity - O(N)

        Approach
            Basic approach
            -> We will find the time = distance / speed 
            -> if time is same they reach as same fleet


            Before that we will create sort the position and speed in descending order of position, assuming if nearer to target person reach faster ++ if particular is behind certain but reaches fastly than before then they are of same fleet

            -> cars[n][2] -> n rows to cars position and speed -> so 2 colums
            -> sort the position
            -> create stack
            -> loop across the sorted cars
            -> compute time = distance/speed
                -> check if stack isEmpty or stack.peek()<time
                -> push in stack
            
            -> return stack size.




    */

    public int carFleet(int target, int[] position, int[] speed) {
        
        int[][] cars = new int[speed.length][2];

        for(int i=0;i<speed.length;i++){
            cars[i][0]=position[i];
            cars[i][1]=speed[i];
        }

        //Now we sort basis of position
        Arrays.sort(cars,(a,b)->b[0]-a[0]);

        Stack<Double> stack = new Stack<>();

        for(int i=0;i<cars.length;i++){
            double time = (double)(target - cars[i][0])/(double)cars[i][1];
            if(stack.isEmpty() || stack.peek()<time){
                stack.push(time);
            }
        }

        return stack.size();
        
    }
}