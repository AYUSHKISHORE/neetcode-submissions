/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/*

        Note - Don't falls for input mentioned in example...you won't get the input like this

        Instead you'll get single node using that single node you need to build.


        Logic create a map<Node, Node>

        for every node add that in map
        post add find neighbors create map object eventually and put in neighbor of parent node

        Time 
            approach1DFS - O(V+E) .. for every node we visit its neighbors
            approach1BFS - O(V+E) .. for every node we visit its neighbors

        Space 
            approach1DFS - O(V) -> recursive stack
            approach1BFS - O(V) -> queue


*/


class Solution {
    public Node cloneGraph(Node node) {
       //return approach1DFS(node);

       return approach2BFS(node);
    }

    Map<Node, Node> map = new HashMap<>();
    public Node approach1DFS(Node node){
        
        if(node == null){
            return null;
        }

        if(map.containsKey(node)){
            return map.get(node);
        }

        map.put(node, new Node(node.val));


        for(Node neighbor : node.neighbors){
            map.get(node).neighbors.add(approach1DFS(neighbor));
        }

        return map.get(node); 
    }

    public Node approach2BFS(Node node){
        
        if(node == null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));

        while(!queue.isEmpty()){

            Node curr = queue.poll();

            for(Node neighbor : curr.neighbors){

                if(!map.containsKey(neighbor)){
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor); 
                }

                map.get(curr).neighbors.add(map.get(neighbor));

            }
        }

        return map.get(node);
    }
}