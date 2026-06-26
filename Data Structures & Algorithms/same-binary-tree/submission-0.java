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
    approach1Recursive
        Time - O(n)/ O(logn) if tree is balance
        Space - O(h) h = height of tree stored in recursive stack
    approach2Recursive
        Time - O(n)/ O(logn) if tree is balance
        Space - O(h) h = height of tree stored in recursive stack
    approach3Recursive
        Time - O(n)/ O(logn) if tree is balance
        Space - O(h) h = height of tree stored in recursive stack
    approach4Iterative
        Time - O(n)
        Space - O(n)/O(w) if w=widht of tree is too much


    Summary of all approach is not as we have multiple check so look code
*/
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
           return approach1Recursive(p,q); // Preorder traversal way
        // return approach2Recursive(p,q); // Postorder traversal way
        // return approach3Recursive(p,q); // Inorder traversal way
        // return approach4Iterative(p,q); // Level Order 
        
    }

    //Preorder approach
    public boolean approach1Recursive(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }

        if(p == null || q == null){
            return false;
        }

        return (p.val == q.val) && approach1Recursive(p.left, q.left) && approach1Recursive(p.right, q.right);
    }

    //Postorder approach
    public boolean approach2Recursive(TreeNode p, TreeNode q){

        if(p==null && q== null){
            return true;
        }

        if(p==null || q==null){
            return false;
        }

        return approach2Recursive(p.left, q.left) && approach2Recursive(p.right, q.right) && (p.val ==q.val);

    }

    public boolean approach3Recursive(TreeNode p, TreeNode q){

        if(p==null && q== null){
            return true;
        }

        if(p==null || q== null){
            return false;
        }

        return approach3Recursive(p.left, q.left) && (p.val == q.val) && approach3Recursive(p.right, q.right);

    }

    //Level Order approch
    public boolean approach4Iterative(TreeNode p, TreeNode q){

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p); // safer alternative of add as add throws exception when failed to add
        queue.offer(q);

        while(!queue.isEmpty()){
            TreeNode currP = queue.poll(); // safer alternative of remove
            TreeNode currQ = queue.poll(); 

            if(currP == null && currQ == null){
                continue; // this is useful if we are both tree like [1, null, 2]
            }
            if(currP == null || currQ == null){
                return false;
            }
            if(currP.val != currQ.val){
                return false;
            }

            queue.offer(currP.left);
            queue.offer(currQ.left);
            queue.offer(currP.right);
            queue.offer(currQ.right);
        }

        return true;

    }


}