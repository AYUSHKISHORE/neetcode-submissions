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
    Logic for question
    for any path we can't single node coming twice
    Input: root = [15,-10,20,null,null,15,5,-5]

    so here tree 
             15
        -10        20
                15      5
              -5

ideal path is 15 -> 20 -> 15 (not 15 -> 20 -> 15 -> 5 because in this path 20 is coming twice) 

    Approach
        It is a kind of diameter of tree based approach
        We go ahead with postorder because before choosing root we need to decide which subtree has higher sum left or right

        first we check left to see whether left  path is >0 or not ... if not then we take 0
        same way we do right

        then based on left and right we calculate we consider this node only based on this what will be pathSum

        compare with maxSum

        and return notThePathSum instead chose either left + root or right + root as we need no overlapping element (here 20)

        Time - O(n)
        Space - O(n)


        Diameter logic
        For diameter, at every node:
        diameter = Math.max(diameter, leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);

        Meaning:
        left side height + right side height
        Max Path Sum logic
        For max path sum, at every node:
        maxSum = Math.max(maxSum, leftGain + root.val + rightGain);
        return root.val + Math.max(leftGain, rightGain);

*/


class Solution {


    int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfsPostOrder(root);
        return maxSum;
    }

    public int dfsPostOrder(TreeNode root){

        if(root == null){
            return 0;
        }

        int left = Math.max(0, dfsPostOrder(root.left));
        int right = Math.max(0, dfsPostOrder(root.right));

        int currentPath = left + root.val + right;
        maxSum = Math.max(currentPath, maxSum);

        return root.val + Math.max(left,right);

    }
}
