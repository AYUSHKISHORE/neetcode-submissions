public class Solution {
    /*
        Time Complexity - O(N)
        Space Complexity - O(N-K+1)
        Approach - 
            In this question for every window of size k we need max element
            for that we need to make sure 2 things
                -> highest element should be in the range of the window
                -> if new elements comes in, then update the max element

            -> In order to solve this we will have deque which stores the index of max element of that window at the top followed by next highest element index

        Psuedo
            -> make the array of size = n-k+1
            -> make a Deque
            ->loop across the element
                -> first check if first element's index in deque is within window
                    (i.e) deque.pollFirst() <= right - k
                -> replace the last index of queue till current element is greater than the last index element of queue
                    (i.e) while(!deque.isEmpty() && nums[deque]<=nums[right])
                
                -> add index in deque
                -> if after 1st window keep on adding the elment
                    (i.e) right >= k-1
                    store the first index element in ans array.

        

    */

    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;
        int ans[] = new int[n-k+1];
        Deque<Integer> deque = new LinkedList<>();
        int count = 0;
        for(int right = 0;right<nums.length;right++){

            //We are checking deque is not empty and deque first element index is within window or not (if not remove)
            while(!deque.isEmpty() && deque.peekFirst()<=right-k){
                deque.pollFirst();
            }

            // we are checking if deque element at last is less than current value of array the remove it 
            while(!deque.isEmpty() && nums[deque.peekLast()]<=nums[right]){
                deque.pollLast();
            }
            
           
            deque.offerLast(right);
            //start storing every value once 1st window gets complete
            if(right>=k-1){
                ans[count++]=nums[deque.peekFirst()];
            }
        }

        return ans;
    }
}