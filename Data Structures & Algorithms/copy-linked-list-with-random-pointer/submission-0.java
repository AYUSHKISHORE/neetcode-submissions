/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/*

    Approach1
        Using 2 pass hashmap based solution
        Time Complexity = O(n)
        Space Complexity = O(n)

        Approach we create a hashmap to store the nodes
        map<node,node>

        in 1st pass we get create the new node with respective values of curr
        in 2nd pass we get the value from curr_key and assign the next and random to copiedNode

        Important
        Node copiedNode = map.get(curr);
        copiedNode.next = map.get(curr.next); // NOT copiedNode.next = curr.next;
        copiedNode.random = map.get(curr.random);// NOT copiedNode.random = curr.random;

    Approach2
        Time Complexity - O(N)
        Space Complexity - O(1)
        
        In the space-optimized solution, we first create a copy of every node and insert it just after the original node, so the list becomes A -> A' -> B -> B' -> C -> C'. Now, for any original node curr, its copied node is curr.next. This helps us assign the random pointer easily: if curr.random points to some original node, then curr.random.next is its copied node, so we set copy.random = curr.random.next. After assigning all random pointers, we separate the mixed list into the original list and the copied list. The time complexity is O(n) because we traverse the list three times, and the space complexity is O(1) because we do not use extra data structures like a HashMap.

*/

class Solution {
    public Node copyRandomList(Node head) {


        Node approach1Result = Approach1(head);
        //return approach1Result;

        Node approach2Result = Approach2(head);
        return approach2Result;
    }

    public Node Approach1(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;

        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            //Important
            //Node copiedNode = map.get(curr);
            //copiedNode.next = map.get(curr.next); // NOT copiedNode.next = curr.next;
            //copiedNode.random = map.get(curr.random);// NOT copiedNode.random = curr.random;
            
            Node copiedNode = map.get(curr);
            copiedNode.next = map.get(curr.next);
            copiedNode.random = map.get(curr.random);
            curr = curr.next;
        }

        return map.get(head);
    }

    public Node Approach2(Node head) {
        
        if(head == null){
            return null;
        }

        Node curr = head;
        
        // Step 1: Create copied nodes and insert them after original nodes
        while(curr != null){
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = copy.next;
        }

        // Step 2: Assign the random pointer to copied nodes

        curr = head;
        while(curr!=null){
            Node copy = curr.next;

            if(curr.random != null){
                copy.random = curr.random.next; 
                //why not curr.random 
                // because it will point to curr pointer not copied pointer
            }else{
                copy.random = null;
            }

            curr = copy.next;
        }

        curr = head;
        Node copiedHead = curr.next;

        while(curr!=null){
            
            Node copy = curr.next;
            curr.next = copy.next;
            
            if(copy.next!=null){
                copy.next = copy.next.next;
            }else{
                copy.next = null;
            }

            curr=curr.next;
        }

        return copiedHead;


    }
}