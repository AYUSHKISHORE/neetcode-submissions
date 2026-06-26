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
n = nodes in root
m = nodes in subRoot
h = height of root
k = height of subRoot
    approach1Recursive
        Time - O(n*m) 
        Space - O(h+k)

        In approach one recursive move root tree till it founds a value which matches with subtree, once that value is found the just check if they are same tree or not

    approach2Iterative
        Time - O(n*m)
        Space - O(w+k)

        In approach two BFS move using queue root tree till it founds a value which matches with subtree, once that value is found the just check if they are same tree or not

NOTE - for sameTree logic we have recursion (preorder), but sameTree code can be written in iterative also


Where:
w = maximum width of root queue

Worst-case space for iterative approach can become:
O(n + k) because BFS queue can store many nodes at one level.

For balanced trees:
Recursive space: O(log n + log m)
Iterative space: O(n + log m) worst-case due to queue width


 */
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //return approach1Recursive(root, subRoot);
        return approach2Iterative(root, subRoot);
    }

    public boolean approach1Recursive(TreeNode r, TreeNode s){
        if(s==null){
            return true;
        }

        if(r==null){
            return false;
        }

        if(r.val == s.val && sameTree(r,s)){
            return true;
        }

        return approach1Recursive(r.left,s) || approach1Recursive(r.right,s);
    }

    public boolean approach2Iterative(TreeNode r, TreeNode s){
        if(s==null){
            return true;
        }
        if(r==null){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(r);

        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();

            if(curr.val == s.val && sameTree(curr,s)){
                return true;
            }
            if(curr.left!=null) queue.offer(curr.left);
            if(curr.right!=null) queue.offer(curr.right);

        }
        return false;
    }

    public boolean sameTree(TreeNode r, TreeNode s){
        if(r==null && s == null){
            return true;
        }

        if(r==null || s==null){
            return false;
        }

        return (r.val == s.val) && sameTree(r.left,s.left) && sameTree(r.right, s.right);
    }
}