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
    Time - O(n)
    Space - O(n)
    basic level order traversal

*/

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> arr = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i = 0;i<size;i++){
                TreeNode curr = queue.poll();
                if(i==size-1){
                    arr.add(curr.val);
                }

                if(curr.left!=null){
                    queue.offer(curr.left);
                }

                if(curr.right!=null){
                    queue.offer(curr.right);
                }
            }
        }
        return arr;

    }
}