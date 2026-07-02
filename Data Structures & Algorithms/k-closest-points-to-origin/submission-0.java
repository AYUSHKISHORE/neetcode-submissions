class Solution {
    /*

        Wrong Approach
            First we tried using minHeap and storing element in map 
            but this gets wrong incase of input [[2,-2], [-2,2]]
            Wrong =  Map<Double, []int> map = new HashMap<>();
            Right =  Map<Double, int[]> map = new HashMap<>();

            in java 
            x^2 is XOR operation

        Right Approach
            * Use max heap and insert the points
                * whenever maxHeap.size()>k remove the farthest element

            * add the left maxHeap inside the array and return

            For maxheap
            
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(distance(b), distance(a)));

            Here -> (a,b) -> Integer.compare(distance(b), distance(a))

            a = [3, 3]   // distance = 18
            b = [5, -1]  // distance = 26

            Integer.compare(distance(b), distance(a))
            Integer.compare(26, 18) 
            Output is +1
            Positive means Java will put b before a


            Time - O(nLogk) (why logK because we are only inserting k elements)
            Space - O(k) as we are entering k elements only

        

    */


    public int[][] kClosest(int[][] points, int k) {
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(distance(b), distance(a)));

        for(int[] point : points){
            maxHeap.offer(point);

            if(maxHeap.size()>k){
                maxHeap.poll();
            }
        }

        int resp[][] = new int[k][2];
        int j=0;
        while(!maxHeap.isEmpty()){
            resp[j++]=maxHeap.poll();
        }

        return resp;

    }


    public int distance(int[] points){
        int x = points[0];
        int y = points[1];
        int distance =  x*x  + y*y;
        return distance;
    }
}