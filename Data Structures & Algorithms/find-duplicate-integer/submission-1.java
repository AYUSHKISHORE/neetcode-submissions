class Solution {
    /*
            Time Complexity - O(n)
            Space Complexity - O(1)

            Approach


                In this algorithm we use floyd cycle detection algorithm (having slow and fast pointer)
                Floyd Cycle Detection Algorithm is used when
                    Detect whether a cycle exists
                    Find the meeting point inside the cycle
                    Find the cycle entry/start point
                    Use O(1) extra space

                Floyd Cycle Detection Requirements
                    For a linked list, Floyd Cycle Detection works when each node has exactly one next pointer:    

                For an array, Floyd works only when the array can behave like a linked list:
                nextIndex = nums[currentIndex]
                    Requirements:

                    1. nums[i] must always be a valid index.
                    2. Every index should point to exactly one next index.
                    3. We should be able to safely do:
                    slow = nums[slow]
                    fast = nums[nums[fast]]
                    4. A cycle should be guaranteed or possible.

                    Since the array has n + 1 numbers and values are only from 1 to n, one number must repeat, which creates a cycle.

            Logic
            Treat the array like a linked list where nums[i] points to the next index. Since there are n + 1 numbers in range [1, n], one number repeats and creates a cycle; the cycle entry is the duplicate.

            Use slow-fast pointers to first meet inside the cycle, then reset slow = nums[0] and move both one step. Their next meeting point is the duplicate number.


    */
    public int findDuplicate(int[] nums) {
        
        int slow = nums[0];
        int fast = nums[0];


        // first we get the meeting point of slow and fast pointer
        // slow will move 1 step at a time
        // fast will move 2 step at a time
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);

        //above loop stop as soon as slow and fast meet
        //now we set slow = head and keeping fast at the meeting position
        // In this problem:  cycle entry = duplicate number
        
        slow=nums[0];
        //distance from slow to duplicate/cycle first element is same as distance from meeting point inside cycle to duplicate/cycle first element
        // distance from slow to duplicate(cycle 1st element) == distance from fast to duplicate(cycle 1st element)
       
        while(slow!=fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;


    }
}