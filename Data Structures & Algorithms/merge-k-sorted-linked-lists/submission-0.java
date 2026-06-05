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
    Time Complexity = O(N * K) = where N is the size of the individual list and K is nos of list
    Space Complexity = O(1) - as we are using the constant dummy node and just changing the pointer location

    Basic divide and conquer approach


 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        if(lists.length==0 ){
            return null;
        }

        ListNode finalList = lists[0];
        int size = lists.length;
        int i = 1;
        while(i<size){
            ListNode secondaryList = lists[i];

            finalList = merge(finalList, secondaryList);
            i++;
        }

        return finalList;

    }

    public ListNode merge(ListNode one , ListNode two){
        
        ListNode merged = new ListNode();
        ListNode dummy = merged;
        while(one!=null && two !=null){
            if(one.val <= two.val){
               dummy.next = one;
               one = one.next;
            }else{
               dummy.next = two;
               two = two.next;
            }
            dummy = dummy.next;
        }
        while(one!=null){
            dummy.next = one;
            one = one.next;
            dummy = dummy.next;
        }

        while(two!=null){
            dummy.next = two;
            two = two.next;
            dummy = dummy.next;
        }

        return merged.next;

    }
}