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
    Time  - (n)
    Space - O(n)

    Approach
     * add root in queue
     * loop till queue is not empty
        * Get the size of queue
            * loop on the size
                poll from queue
                add value to list
                check left child
                check right child


 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> arr = new ArrayList<>();        
        if(root == null){
            return arr;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            
            int size = queue.size();
            
            List<Integer> lst = new LinkedList<>();
            for(int i = 0; i< size; i++){
                TreeNode curr = queue.poll();
                lst.add(curr.val);
                
                if(curr.left!=null){
                    queue.offer(curr.left);
                }
                if(curr.right!=null){
                    queue.offer(curr.right);
                }   
            }
            arr.add(lst);

        }

        return arr;


    }
}