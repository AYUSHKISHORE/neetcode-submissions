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
    approach1BFSUsingLong
        * In this approach we need to check a node within a boundary value decide min and max
        inside queue add root with Long.MIN_VALUE and Long.MAX_VALUE
        if moving left decide between (minValue , val)
        if moving right decide between (val, maxValue)

        define a class i.e Pair with node, minValue, maxValue with min being MIN_VALUE and max being MAX_VALUE

        if(val<=minValue || val>=maxValue) then this mean value is not within the required boundary so return false

        we use long because if value is min or max integer value which is -2147483647 or 2147483647 (i.e -2^31 or 2^31) then if fails to handle above if condition

        Use BFS and carry (min, max) range with every node using long boundaries, so Integer.MIN_VALUE and Integer.MAX_VALUE are handled safely.

        Time = O(n)
        Space = O(n)


    
    approach2BFSUsingInt
        Logic is same as approach1 only having 
        inside queue add root with null values
        if(minValue!=null && val<=minValue) return false
        same way for max

        Carry a valid range for every node: root starts with (-∞, +∞), left child gets (min, root.val), right child gets (root.val, max), and we use long so even Integer.MIN_VALUE / Integer.MAX_VALUE are handled safely.

        Use BFS and carry nullable (min, max) boundaries, where null means no lower/upper limit.

        Time = O(n)
        Space = O(n)


    approach3Recursive
        Use recursion to validate every node inside its allowed (min, max) range, updating the range while going left or right.

        Time = O(n)
        Space = O(n)




 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        //return approach1BFSUsingLong(root);
        //return approach2BFSUsingInt(root);
        return approach3Recursive(root, Long.MIN_VALUE, Long.MAX_VALUE);

    }

    //approach1 
    class Pair{
        TreeNode node;
        long minValue;
        long maxValue;
        Pair(TreeNode node, long minValue, long maxValue){
            this.node = node;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public boolean approach1BFSUsingLong(TreeNode root){

        if(root == null){
            return true;
        }

        Queue<Pair> queue = new LinkedList<>();

        //[2147483647]
        //using long because if value is 2147483647 which is max value of int  hence causing the boundary condition to fail hence using long to increase the range
        queue.offer(new Pair(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while(!queue.isEmpty()){

            Pair p = queue.poll();
            long minValue = p.minValue;
            long maxValue = p.maxValue;
            TreeNode curr = p.node;
            long val = curr.val;

            //[2147483647]
            if(val<=minValue || val>=maxValue){
                return false;
            }

            if(curr.left!=null){
                queue.offer(new Pair(curr.left, minValue, curr.val));
            }

            if(curr.right!=null){
                queue.offer(new Pair(curr.right, curr.val, maxValue));
            }

        }

        return true;
       
    }


    //approach2

    class Pair2{
        TreeNode node;
        Integer minValue;
        Integer maxValue;

        Pair2(TreeNode node, Integer minValue, Integer maxValue){
            this.node = node;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }
    
    public boolean approach2BFSUsingInt(TreeNode root){
        if(root == null){
            return true;
        }

        Queue<Pair2> queue = new LinkedList<>();
        queue.offer(new Pair2(root, null, null));

        while(!queue.isEmpty()){
            Pair2 p = queue.poll();
            Integer minValue = p.minValue;
            Integer maxValue = p.maxValue;
            TreeNode curr = p.node;
            int val = curr.val;

            if(minValue!=null && val<=minValue){
                return false;
            }

            if(maxValue!=null && val>=maxValue){
                return false;
            }

            if(curr.left!=null){
                queue.offer(new Pair2(curr.left, minValue, val));
            }

            if(curr.right!=null){
                queue.offer(new Pair2(curr.right, val, maxValue));
            }
        }

        return true;
    }


    //approach3
    public boolean approach3Recursive(TreeNode root, long minValue, long maxValue){
        
        if(root == null){
            return true;
        }

        if(root.val<=minValue || root.val>=maxValue){
            return false;
        }

        return approach3Recursive(root.left,minValue,root.val) && approach3Recursive(root.right,root.val,maxValue);

    }


}