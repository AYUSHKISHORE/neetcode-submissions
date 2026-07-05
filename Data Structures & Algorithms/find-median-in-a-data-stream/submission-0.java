class MedianFinder {

    /*
        Approach
            Logic we  store element in 2 heap 
                1 2 3 4 5 6 7
                1st maxHeap which store left or smaller part of array (small)
                2nd minHeap which store right or bigger part of array (large)

                small = [3,2,1] (maxHeap)
                large = [4,5,6,7] (minHeap)

                add
                    first check if small is empty or element is smaller than small.peek then add in small
                    else if check if element is bigger than large.peek() then add in large


                    Note important
                        if there is case if any of heapsize (Say Heap1) become > otherHeapSize(say Heap2) + 1
                            then rebalanceit (i.e - Heap2.offer(Heap1.poll()))

                    Eg
                    small = [3,2,1] (maxHeap)
                    large = [4,5,6,7] (minHeap)

                    Let say another element 8 comes
                    small = [3,2,1] (maxHeap)
                    large = [4,5,6,7,8] (minHeap)

                    now large>small + 1
                        large.poll() = 4
                        and small.offer(4)

                FindMedian
                    If both heap size is same 
                        peek both heap element -> take sum/2
                    
                    else return the heap.peek() which bigger in size

            Time
                addNum()      -> O(log n)
                findMedian()  -> O(1)
            Space             -> O(n)

    */

    PriorityQueue<Integer> small; //maxHeap
    PriorityQueue<Integer> large; //minHeap
    public MedianFinder() {
        small = new PriorityQueue<Integer>(Collections.reverseOrder());
        large = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        
        if(small.isEmpty() || small.peek()>num){
            small.offer(num);
        }else{
            large.offer(num);
        }

        if(small.size()>large.size()+1){
            large.offer(small.poll());
        }else if(large.size()>small.size()+1){
            small.offer(large.poll());
        }
        
    }
    
    public double findMedian() {
        if(small.size()==large.size()){
            return (double) (large.peek() + small.peek())/2.0;
        }  

        if(small.size()>large.size()){
            return (double)small.peek();
        } 
        return (double)large.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */