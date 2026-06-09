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
    Time Complexity - O(n)
    Space Complexity - O(1)
    Approach
        - To reverse a linked list, we use three pointers: prev, curr, and nextNode. Initially, prev is null and curr points to head. For each node, we first save the next node in nextNode because we are going to break the original link. Then we reverse the current node’s link by doing curr.next = prev. After that, we move prev to curr and curr to nextNode. This process continues until curr becomes null. At the end, prev points to the new head of the reversed linked list, so we return prev.
        

        Save -> Reverse -> Move
*/
class Solution {
    public ListNode reverseList(ListNode head) {

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;   // 1. save next
            curr.next = prev;   // 2. reverse link
            prev = curr;        // 3. move prev
            curr = next;        // 4. move curr
        }

        return prev;

    }
}