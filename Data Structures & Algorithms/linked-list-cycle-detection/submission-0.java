/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
    Time Complexity = O(n)
    Space Complexity = O(1)

    Approach
        This approach is floyd cycle detection approach, also know slow and fast pointer approach

        here we create a slow pointer which moves 1 step at a time.
        fast pointer which moves 2 steps at a time.

        if(fast == slow)--> then cycle exists , else no       



*/


public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}