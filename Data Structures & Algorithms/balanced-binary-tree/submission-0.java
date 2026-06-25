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
        Approach1 (recursion DFS)
            Time = O(n)/ O(logn) in case of balance tree
            Space = O(n) recursive stack

            Recursive DFS calculates height bottom-up and returns -1 as soon as any subtree is unbalanced.
            Best approach because it is clean, one-pass, and uses only recursion stack space.
        
        Approach2 (Iterative BFS)
            Time = O(n)
            Space = O(n)
            
            Note - in this approach we have store the height at each node
            BFS first stores all nodes, then processes them in reverse order to calculate heights and check balance.
            Correct iterative approach, but uses extra O(n) space for list and map.

 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        return !(approach1(root) == -1);
        //return approach2(root);
        
        
    }

    public int approach1(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = approach1(root.left);
        if(left == -1){
            return -1;
        }
        int right = approach1(root.right);
        if(right == -1){
            return -1;
        }

        if(Math.abs(left - right)>1){
            return -1;
        }

        return 1+Math.max(left, right);
    }

    public boolean approach2(TreeNode root){
        if(root == null){
            return true;
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
        
        for(int i = arr.size()-1; i>=0; i--){
            TreeNode curr = arr.get(i);

            int left = curr.left == null ? 0 : heightMap.get(curr.left);
            int right = curr.right == null ? 0 : heightMap.get(curr.right);

            int diff = Math.abs(left-right);
            if(diff>1){
                return false;
            }
            heightMap.put(curr, 1 + Math.max(left,right));
        }

        return true;
    }
}