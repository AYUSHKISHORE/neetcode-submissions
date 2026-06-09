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
        Time Complexity = O(N)
        Space Complexity = O(1)

        Approach
        -> create a resp LL and ans which copy of resp
        -> curr end with head of LL
        -> with size = 0
        -> loop infinite
            -> loop till size<k and update the end to next element
            -> if size ==k
                -> now reverse (curr till end)
                    -> assign it ans.next
            -> else
                -> assign ans.next = currHead


        Your code reverses the linked list in groups of size k. First, it checks whether the current group has exactly k nodes by moving the end pointer. If a full group exists, it reverses nodes from currHead up to end using the reverse() helper. The reversed group is connected to the answer list through ans.next. Since the old currHead becomes the tail after reversal, we can directly move ans = currHead. Then currHead = end starts the next group. If fewer than k nodes are left, they are attached as-is.


 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode resp = new ListNode(0);
        ListNode ans = resp;
        ListNode currHead = head;
        ListNode end = head;
        int size = 0;
        while(true){
            

            while(size < k ){
                if(end == null){
                    break;
                }
                end = end.next;
                size++;
            }
            if(size == k){
                ans.next = reverse(currHead, end);
            }else{
                ans.next = currHead;
                break;
            }
            while(ans.next != null){
                ans = ans.next;
            }
            currHead = end;
            size = 0;
        }
        return resp.next;

    }

    public ListNode reverse(ListNode head, ListNode end){
        ListNode curr = head;
        ListNode prev = null;

        while(curr!=end){
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
       
        return prev;
    }
}