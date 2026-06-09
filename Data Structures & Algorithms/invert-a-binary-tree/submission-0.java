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
                                Time Complexity | Space Complexity 
        Approach1-> Iterative       O(n)        | O(n) queue
        Approach2-> Recursive       O(n)        | O(n) recursion stack
    
        Approach1 -> Iterative DFS
            -> Normal Level Order Traversal and changing the pointer

        Approach2 -> Recursive
            -> Normal Recursion and changing pointer


 */
class Solution {
    public TreeNode invertTree(TreeNode root) {

      //return approachIterative(root); 
      return approachRecursive(root);

    }

    public TreeNode approachIterative(TreeNode root){
        if(root == null){
        return null;
      }
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);

      while(!queue.isEmpty()){
        TreeNode curr = queue.remove();
        if(curr.right != null){
            queue.add(curr.right);
        }
        if(curr.left != null){
            queue.add(curr.left);
        }
        TreeNode currRight = curr.right;
        TreeNode currLeft = curr.left;
        curr.right = currLeft;
        curr.left = currRight;
      } 
      return root;
    }

    public TreeNode approachRecursive(TreeNode root){
        if(root == null){
            return null;
        }

        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        root.left = tempRight;
        root.right = tempLeft;

        approachRecursive(root.left);
        approachRecursive(root.right);

        return root;
    }
}