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
    Note 
        We can form binary tree if we have following possibilites
            1) if we have preorder and inorder data
            2) if we have preorder and exact null data
        
        We can form BST if we have below info
            1) preorder data

        inorder = [leftSubTree, root, rightSubTree]
        preroder = [root, leftSubTree, rightSubTree]

        once we get root index 
        we just need to set 
            left = build(startIndex, rootIndex - 1)
            right = build(rootIndex + 1, endIndex)
        
        
    Logic 
        -> We form a map using inorder elements and its index
        -> then using preorder 1st node is root of whole , 2nd node is root of left subtree
            -> once we get the root node
            -> we form the treenode
            -> and then we index of it inorder
            -> using inorder
                 left = build(startIndex, rootIndex - 1)
                 right = build(rootIndex + 1, endIndex)


    Time = O(n)
    Space = O(n)

*/


class Solution {
    
    int preIndex = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        for(int i = 0;i<inorder.length;i++){
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length-1);
    }

    public TreeNode build(int[] preorder, int startIndex, int endIndex){

        if(startIndex>endIndex){
            return null;
        }

        int rootVal = preorder[preIndex++];
        TreeNode root = new TreeNode(rootVal);

        int nextRootIndex = inorderMap.get(rootVal);

        root.left = build(preorder, startIndex, nextRootIndex-1);
        root.right = build(preorder, nextRootIndex+1 , endIndex);

        return root;

    }
}