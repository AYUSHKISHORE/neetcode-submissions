/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/*
        Time Complexity = O(n)
        Space Complexity = O(1)

        Approach
            ->
            Use a dummy node before head so we can easily remove any node, including the first node. Set dummy.next = head, then keep two pointers: left at dummy and right at head. First move right n steps ahead to create a gap of n nodes. Then move both left and right together until right becomes null. At this point, left is just before the node that needs to be removed, so we delete it using left.next = left.next.next. Finally, return dummy.next because the head may change if the first node was removed. 
            
            Note - just make dummy node before head...which dummy.next = head..
                because suppose we need remove [1] only value linkedlist

*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy =  new ListNode();
        dummy.next = head;
        ListNode right = head;
        ListNode left = dummy;

        while(n>0){
            right = right.next;
            n--;
        }

        while(right!=null){
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;

        return dummy.next;
    }
}