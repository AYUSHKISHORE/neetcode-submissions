class KthLargest {

    /*
        Logic 
            -> Normal PriorityQueue based question
            Q) In Java Does PriorityQueue store values in ascending order?
            A) No, but PriorityQueue peek/poll method guranteed it will return the smallest value


            PriorityQueue does not store everything sorted.
            It only keeps the highest priority element at the top.
            By Default in Java
            priority element = smallest element
            That is why it behaves like a min heap.

            How to make it(PriorityQueue) a maxHeap?
                PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

                maxHeap.offer(5);
                maxHeap.offer(1);
                maxHeap.offer(3);
                maxHeap.offer(2);

                System.out.println(maxHeap.poll()); // 5
                System.out.println(maxHeap.poll()); // 3
                System.out.println(maxHeap.poll()); // 2
                System.out.println(maxHeap.poll()); // 1



        Time - offer() = O(log n)
                poll()  = O(log n)
                peek()  = O(1)
        Space - O(n)


    */

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.minHeap = new PriorityQueue<>();
        this.k=k;

        for(int n : nums){
            add(n);
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        if(minHeap.size()>k){
            minHeap.poll();
        }

        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */