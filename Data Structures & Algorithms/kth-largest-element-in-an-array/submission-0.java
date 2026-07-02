class Solution {
    public int findKthLargest(int[] nums, int k) {
        /*
            Approach
                * MinHeap
                    add the elements in min Heap and poll when size>k

                return the peek() or 0

                Time - O(nlogk)
                Space - O(k)

        */

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->Integer.compare(a,b));

        for(int num : nums){
            minHeap.offer(num);
            if(minHeap.size()>k){
                minHeap.poll();
            }
        }

        return minHeap.size()==0?0:minHeap.peek();
    }
}