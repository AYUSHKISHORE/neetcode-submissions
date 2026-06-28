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
        approach1BFS
            * Basic level order traversal and keeping every element in sortedSet and then converting sorted set in arraylist and return k-1 element;

            SortedSet<Integer> s = new TreeSet<>();
            Time - O(nlogn) as insertion in sorted set taken (logn) and inserting n nodes takes O(nlogn)
            Space - O(n)

        * approach2Inorder
            * Basic inorder travesal kth node == kth traversal in inorder
            * Time = O(n)
            * Space = O(n) recursive stack

 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {

            //return approach1BFS(root, k);
            
            //approach2
            approach2Inorder(root, k);
            return ans;
        
    }

    public int approach1BFS(TreeNode root, int k){
        if(root == null){
            return -1;
        }
        
        SortedSet<Integer> set = new TreeSet<>();
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            set.add(node.val);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        List<Integer> list = new ArrayList<>(set);

        return list.get(k-1);
    }

    int count = 0;
    int ans = -1;
    public void approach2Inorder(TreeNode node, int k){
        if(node == null){
            return;
        }

        approach2Inorder(node.left,k);
        count++;
        if(count == k){
            ans = node.val;
            return;
        }
        approach2Inorder(node.right,k);
    }
}