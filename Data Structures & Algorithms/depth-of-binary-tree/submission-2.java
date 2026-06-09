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

    approachRecursion       
           Time Complexity  =  O(n) or O(logn) for balanced tree  
           Space Complexity =  O(h) (height of tree) due to recursion stack
    approachLevelOrder
            Time Complexity =  O(n)
            Space Complexity = O(w) (max width in level order)

    approachRecursion
        In the recursive approach, if the current node is null, we return 0. Otherwise, we calculate the maximum depth of the left subtree and right subtree recursively, take the maximum of both, and add 1 for the current node. So the formula is:

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));


    approachLevelOrder
        In the level order approach, we use a queue to process the tree level by level. For every level, we first take the current queue size, which tells us how many nodes are present at that level. We process all those nodes, add their children to the queue, and increase len by 1. When the queue becomes empty, len contains the total number of levels, which is the maximum depth of the tree.


*/


class Solution {
    public int maxDepth(TreeNode root) {
        //return approachRecursion(root);
        return approachLevelOrder(root);
    }

    public int approachRecursion(TreeNode root){
        if(root == null){
            return 0;
        }

        return 1 + Math.max(approachRecursion(root.left),approachRecursion(root.right));
    }

    public int approachLevelOrder(TreeNode root){
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int len = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            len++;
            for(int i = 0; i<size; i++){
                TreeNode curr = queue.remove();
                if(curr.left !=null){
                    queue.add(curr.left);
                }
                if(curr.right != null){
                    queue.add(curr.right);
                }
            }
        }
        return len;
    }
}