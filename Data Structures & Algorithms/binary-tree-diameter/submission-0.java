/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


 /*
    Approach1 (recursion - DFS appraoch)
        Time    - O(n) / O(logn) when tree is balanced
        Space   - O(h) recursion stack

        Use DFS to go till leaf nodes first.
        For every node, get height of left subtree and right subtree.
        The diameter passing through that node is:
            left height + right height
        Update global diameter with the maximum value.
        Return the height of current node to its parent.
        This solves height and diameter together in one traversal.

    Approach2 (iterative - BFS level order approach)
        Time    - O(n)
        Space   - O(n) 
        This approach is useful when we need height for every node

        First do level order traversal and store all nodes in an array/list.
        Then traverse that list from back to front, so child nodes are processed before parent nodes.
        For every node, get left and right child heights from a map.
        Update diameter using:
            left height + right height
        Then store current node height in the map.
        This is an iterative way to simulate bottom-up processing.

 */
class Solution {

    //we will define a global variable for recursion approach
    int diameter = 0; // note diameter is distance between the 2 nodes (i.e edge)
    public int diameterOfBinaryTree(TreeNode root) {
        //approach1 using recursion
        //get_height_approach1(root);
        //return diameter;

        //BFS based approach (level order traversal) store and get the height and diameter
        return get_height_approach2(root);
    }

    // in this approach we are calculating the the height of root node 
    // while doing this we calculate the all subsequent node height
    // we are using out logic to get max diameter
    // note this approach doesn't store height at every node (instead just return the root node height)
    public int get_height_approach1(TreeNode node){
        if(node == null){
            return 0;
        }

        int left = get_height_approach1(node.left);
        int right = get_height_approach1(node.right);
        
        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left,right);
    }

    //In this approach first we get the levelOrder Traverse data [1,2,3,4]
    // then we loop in reverse order the calulate the height and store it in map
    public int get_height_approach2(TreeNode root){

        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<TreeNode> arr = new ArrayList<>();

        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.remove();
            arr.add(curr);
            if(curr.left!=null){
                queue.add(curr.left);
            }
            if(curr.right!=null){
                queue.add(curr.right);
            }
        }


        Map<TreeNode, Integer> heightMap = new HashMap<>();
        int diameter = 0;

        for(int i = arr.size()-1; i>=0; i--){
            
            TreeNode curr = arr.get(i);

            int left = curr.left == null ? 0 : heightMap.get(curr.left);
            int right = curr.right == null ? 0 : heightMap.get(curr.right);
            diameter = Math.max(diameter, left + right);

            heightMap.put(curr, 1 + Math.max(left,right));


        }

        return diameter;
    }

}