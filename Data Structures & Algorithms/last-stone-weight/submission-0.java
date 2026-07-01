class Solution {

    /*
        Logic - MaxHeap question
            take 2 element and add the diff (if diff>0)

            return the last element if present 

        
        Important I/Ps
            I/P = [1]
            O/p = 1

            I/P = [2,2]
            O/P = 0

        Maxheap = PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        minHeap = PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        Time - O(nLogn)
        Space - O(n)

    */

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone : stones){
            maxHeap.offer(stone);
        }
       while(maxHeap.size()>1){
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();

            if(stone1==stone2){
                continue;
            }
            int newStone = Math.abs(stone1-stone2);
            maxHeap.offer(newStone);

        }

        return maxHeap.size()==1?maxHeap.peek():0;
    }
}