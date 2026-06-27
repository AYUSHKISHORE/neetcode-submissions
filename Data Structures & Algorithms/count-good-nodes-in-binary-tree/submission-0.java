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
    Question
        For a node x to be good, no ancestor from root to x should have value greater than x.val.
        
        So x.val must be >= max value seen so far on that path.
    approach1DFS
        * Time = O(n)
        * Space = O(n) Recursion stack

        recursive traversal
            * Update the maxSoFar if node.val>=maxSoFar and increase the count
            * move left and update count
            * move right and update count


    approach2BFS
        * Time = O(n)
        * Space = O(n)

        * Create a class Pair{TreeNode, MaxSoFar}
        * BFS/LevelOrderTraversal 
            -> Update the maxSoFar if node.val>=maxSoFar and increase the count

*/

class Solution {
    public int goodNodes(TreeNode root) {

        return approach1DFS(root, root.val);

        //return approach2BFS(root);
        
    }

    public int approach1DFS(TreeNode node, int maxSoFar){

        if(node == null){
            return 0;
        }

        int count = 0;
        if(node.val>=maxSoFar){
            count = 1;
            maxSoFar = Math.max(node.val, maxSoFar);
        }

      
        count+=approach1DFS(node.left, maxSoFar);
        count+=approach1DFS(node.right, maxSoFar);
        return count;

    }


    //approach 2 BFS

    class Pair{
        TreeNode node;
        int maxSoFar;

        Pair(TreeNode node, int maxSoFar){
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }

    public int approach2BFS(TreeNode root){

        if(root == null){
            return 0;
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, root.val));
        int count = 0;
        while(!queue.isEmpty()){

            Pair p = queue.poll();
            TreeNode curr = p.node;
            int maxSoFar = p.maxSoFar;

            if(curr.val>=maxSoFar){
                count++;
                maxSoFar = curr.val;
            }

            if(curr.left!=null){
                queue.offer(new Pair(curr.left,maxSoFar));
            }
            if(curr.right!=null){
                queue.offer(new Pair(curr.right,maxSoFar));
            }
        }

        return count;

    }
}