/*
        Time Complexity = O(1)
        Space Complexity = O(N) (n=capacity)
        
        LRU we need double linked list as fetch time is faster 
            as it helps to remove and insert in O(1) time

        we need
            map =  which store the key and value
            capacity =  to maintain the list size
            left <----------> right
            left has LRU
            right has MRU 
        
        Once we "get" an element push it to MRU
        we "put" an element at MRU
            if capacity<map.size () -> remove from lru 
        

        //get 
            -> will first check if key exist or not
            key exist -> get the Node as value
            -> remove the node
            -> insert at right (MRU)

        //put
            -> check if exist
                -> exist -> then remove
            then add at right (MRU)
                    -
            -> check the count of map
                -> if it is more than size remove the left.next (as left is dummy function)


        based on the above requirement we need to make to more function
        //insert -> which insert node at right
        //remove -> which remove the node

        //Note we assign dummy value at first 
                this.left = new Node(0,0);
                this.right = new Node(0,0);
        
        So that we don't have to check edge cases if node is null or not

    */

class LRUCache {
    
    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Node left;
    private Node right;

    private Map<Integer, Node> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.left = new Node(0,0);
        this.right = new Node(0,0);

        left.next = right;
        right.prev = left;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node curr = map.get(key);
        remove(curr);
        insert(curr);

        return curr.value;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)){
            Node oldNode = map.get(key);
            remove(oldNode);
        }

        Node newNode = new Node(key, value);
        insert(newNode);
        map.put(key, newNode);

        if(capacity<map.size()){
            Node lru = left.next;
            remove(lru);
            map.remove(lru.key);
        }

    }

    public void insert(Node newNode){
        Node nextNode = right;
        Node prevNode = right.prev;

        prevNode.next = newNode;
        newNode.next = right;
        newNode.prev = prevNode;
        right.prev = newNode; 
    }


    public void remove(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */