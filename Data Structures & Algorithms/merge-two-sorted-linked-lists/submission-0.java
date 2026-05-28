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
        Time Complexity - O(n+m)
        Space Complexity - O(1) because we just using pointer (apart from creating 1 node dummy we are just using pointer)
            It would look like new list if we write like this.... curr.next = new ListNode(list1.val);

        Approach 
            Basic merge sort type logic

    */


class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while(list1!=null && list2!=null){

            if(list1.val<=list2.val){
                curr.next = list1;
                list1 = list1.next;
            }else{
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if(list1!=null){
            curr.next = list1;
        }

        if(list2!=null){
            curr.next=list2;
        }
        return dummy.next;
    }
}