/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /*
    apporach1RecursiveBST
        * This approach is applicable only for BST
        Time - O(n)
        Space - O(n)/O(h) (h=height)

    approach2IterativeBST
        * This approach is applicable only for BST
        Time - O(n)
        Space - O(1)
    approach3IterativeBinaryTree
        * This approach is applicable for all binary Tree
        * In this approach we do level order travesal and for every node we keep the parent in map 
        * once parent mapping is done
        * store the ancestor of p and q in set -> inorder to get the common first ancestor
        * loop till p!=null 
            at every loop get the parent and store it in p and in ancestor
        * loop till q doesn't exist in ancestor (set)
        * return q

        Time - O(n)
        Space - O(n) 

    /* Note these cases are valid only for BST approaches (here apporach1RecursiveBST and approach2IterativeBST)
        Case1 p<root and q<root means both common ancestor lie in the left
        Case2 p>root and q>root means both common ancestor lie in the right
        Case3 
            p<root and q>root means [1,2,3] p= 2 and q = 3 and root = 1
            p>root and q<root means [1,2,3] p= 3 and q = 2 and root = 1
            p==root==q

    */





class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        return apporach1RecursiveBST(root, p, q);
        //return approach2IterativeBST(root, p, q);
        //return approach3IterativeBinaryTree(root,p,q);
    }
    
    public TreeNode apporach1RecursiveBST(TreeNode root, TreeNode p, TreeNode q){

        if(p.val<root.val && q.val<root.val){
            return apporach1RecursiveBST(root.left, p, q);
        }else if(p.val>root.val && q.val>root.val){
            return apporach1RecursiveBST(root.right,p, q);
        }
        return root;
    }

    public TreeNode approach2IterativeBST(TreeNode root, TreeNode p, TreeNode q){

        TreeNode curr = root;

        while(curr!=null){
            if(p.val<curr.val && q.val<curr.val){
                curr = curr.left;
                continue;
            }
            if(p.val>curr.val && q.val>curr.val){
                curr = curr.right;
                continue;
            }
            return curr;
        }
        return root;
    }

    public TreeNode approach3IterativeBinaryTree(TreeNode root, TreeNode p, TreeNode q){

        if(root == null || root == p || root == q){
            return root;
        }

        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        parent.put(root, null);
        
        while(!queue.isEmpty() && (!parent.containsKey(p) || !parent.containsKey(q))){
            TreeNode curr = queue.poll();
            
            if(curr.left!=null){
                queue.offer(curr.left);
                parent.put(curr.left,curr);
            }

            if(curr.right!=null){
                queue.offer(curr.right);
                parent.put(curr.right,curr);
            }
        }

        Set<TreeNode> ancestor = new HashSet<>();
        while(p!=null){
            ancestor.add(p);
            p = parent.get(p);
        }

        while(!ancestor.contains(q)){
            q = parent.get(q);
        }

        return q;
    }
}