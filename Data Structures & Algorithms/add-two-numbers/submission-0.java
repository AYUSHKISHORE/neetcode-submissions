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
        Time Complexity = O(n + m)
        Space Complexity = O(n + m)

        Approach
            Use a dummy node to build the result linked list easily. Since the digits are stored in reverse order, we can add both numbers from the head directly, just like normal digit-by-digit addition. Traverse both lists together, take l1.val and l2.val if they exist, add them with the current carry, then create a new node with sum % 10. Update the carry using sum / 10. Continue the loop while either list still has nodes or carry is not zero. Finally, return dummy.next because dummy is only a fake starting node.


*/

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        int carry = 0;
        while(l1!=null || l2!=null || carry!=0){

            int val1 = 0;
            int val2 = 0;

            if(l1!=null){
                val1=l1.val;
                l1 = l1.next;
            }

            if(l2!=null){
                val2=l2.val;
                l2 = l2.next;
            }

            int sum = val1 + val2 + carry;
            int digit = sum%10;
            carry = sum/10;

            curr.next = new ListNode(digit);
            curr = curr.next;
        }

        return dummy.next;

    }
}