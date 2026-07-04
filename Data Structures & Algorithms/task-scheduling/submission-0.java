class Solution {


    /*
        Approach1 Using MaxHeap
            High level logic
                1. Pick the task with highest remaining count from max heap.
                2. Execute it.
                3. If still remaining, put it in cooldown queue.
                4. When cooldown completes, push it back to heap.
                5. If heap is empty but queue has tasks, CPU idles.

            Take the freq of each task
            Insert it in maxHeap (priorityQueue)
            create a new queue (i.e CoolDownQueue<int[]>)  which takes remaining task and time to start next task
            time = 0

            loop till maxHeap or CoolDownQueue is not empty
                increase time
                take max(i.e first) taskCount from maxHeap  
                decreas the count
                if count>0 insert in coolDownQueue(count, time + n) --> where time+n mean after time +n it can start

                if(coolDownQueue is not empty and peek()[1]==time)
                    add in maxHeap

            return time


            Time Complexity - O(NLogK)     (N - task) (k is frequest inserted/polled from priorityQueue)
            Space Complexity - O(K)

        
        Approach_Two_Greedy
            -> In this greedy Approach although heap could also be called greedy
            I/P A A A B B B C C D (n-2)
            A-3, B-3, C-2, D-1
            We first find maxFreq of element (A-3)
            now we find gaps min req
            A _ _ A _ _ A

            seeing here we need 2 gaps (which mean maxFreq - 1)
            gaps = maxFreq - 1
            gaps = A _ _ A _ _
            idleSlots here = _ _ _ _ (which means gaps * n) 

            now adjust the element, but make sure it doesn't have freq more than gaps (here B - 3 ) as we can place 3 Bs in 2 gaps 
            idleSlots -= Math.min(gaps, freq)
            Post B
            A B _ A B _ 
            Post C
            A B C A B C A
            Now we see idleSlot is -1 (post D)
            A B C A B C A D
            return totalTask + min(idleSlots, 0)
            now since idleSlot -ve mean we can place rest element at last
            A B C A B C A D B


            Time - O(k + 26log26) { 26Log26 is constant = O(1)} = O(k)
            Space = O(26) = O(1)

        Approach_Three_Mathematical
            In the mathematical approach, we first count the frequency of every task and find maxFreq, which is the highest frequency among all tasks. The task with the highest frequency decides the minimum required structure because the same task must have at least n intervals between two executions. If a task appears maxFreq times, then there will be maxFreq - 1 gaps between its occurrences. Each gap forms a block of size n + 1, where 1 position is for the task itself and n positions are for cooldown. So the base frame becomes (maxFreq - 1) * (n + 1). After this, we add maxFreqCount, which is the number of tasks having the same maximum frequency, because all of them will appear in the last block. Therefore, the formula becomes Math.max(tasks.length, (maxFreq - 1) * (n + 1) + maxFreqCount). We take the maximum with tasks.length because if there are enough other tasks to fill the idle spaces, then no extra idle time is needed and the answer is simply the total number of tasks. The time complexity is O(tasks.length) and the space complexity is O(1) because we only use a fixed frequency array of size 26.


            Time:  O(tasks.length)
            Space: O(1)




    */
    public int leastInterval(char[] tasks, int n) {
        
        return Approach_One_MaxHeap(tasks,n);
        //return Approach_Two_Greedy(tasks, n);
        //return Approach_Three_Mathematical(tasks, n);

    }

    public int Approach_One_MaxHeap(char[] tasks, int n){
        int freq[] = new int[26];
        for(char c : tasks){
            freq[c-'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
     // OR
     // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->Integer.compare(b,a));

        for(int f : freq){
            if(f>0){ //as no point of keeping 0 freq task
                 maxHeap.offer(f);
            }
        }

        Queue<int[]> coolDownTaskQueue = new LinkedList<>();
        int time = 0;
        while(!maxHeap.isEmpty() || !coolDownTaskQueue.isEmpty()){
            time++;

            if(!maxHeap.isEmpty()){
                int count = maxHeap.poll();
                count--;
                if(count>0){
                    coolDownTaskQueue.offer(new int[]{count, time+n});
                }
            }

            if(!coolDownTaskQueue.isEmpty() && coolDownTaskQueue.peek()[1]==time){

                maxHeap.offer(coolDownTaskQueue.poll()[0]);
            }

        } 

        return time;

    }

    // A - 3
    // B - 2
    // C - 2
    // n - 2
    // A _ _ A _ _ A
    // A _ _   A _ _   A
    // A B _ A B _ A
    // A B C A B C A
    // maxFreq = 3
    // gap = maxFreq - 1 => 3-1 = 2
    // idleSlot = gaps * n = 2 * 2 = 4
    // now fill idleslot with left element and subtract the idle slot

    //imp when idleSlot is -ve mean (left element can be placed) at any place
    //Take suppose  A-3, B- 2, C-2, D-1
    // A B C A B C A D

    // or even this can be satisfied
    // A-3, B-2, C-2, D-2
    
    // A _ _ A _ _ A
    // A B C A B D A C B


    public int Approach_Two_Greedy(char[] tasks, int n){
        int[] freq = new int[26];
        for(char task : tasks){
            freq[task-'A']++;
        }

        Arrays.sort(freq);

        int maxFreq = freq[25];

        // A-3, B-2, C-2
        //gaps = {A _ _} {A _ _} A --> here 2 gaps are there
        int gaps = maxFreq-1;
        // idlegaps _ _ _ _  (here 4)
        int idleSlots  = gaps * n;

        for(int i=24;i>=0;i--){
            idleSlots -= Math.min(freq[i], gaps); // because if freq is > gap then max we can handle is gap
        }
        // we need  to idleSlots or 0 max (as -ve idleSlots means task can be adjusted at last)
        return tasks.length + Math.max(0, idleSlots);

    }

    public int Approach_Three_Mathematical(char[] tasks, int n){
        int[] freq = new int[26];
        for(char task : tasks){
            freq[task-'A']++;
        }

        Arrays.sort(freq);
        int maxFreq = freq[25];
        int maxFreqCount = 0;
        
        for(int f : freq){
            if(maxFreq==f){
                maxFreqCount++;
            }
        }
        int frameLength = ((maxFreq - 1)*(n+1))+maxFreqCount;
        int ans = Math.max(tasks.length , frameLength);

        return ans;
    }
}