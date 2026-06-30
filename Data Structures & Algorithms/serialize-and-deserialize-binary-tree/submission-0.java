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
    Approach
        Serialize
                //1,2,N,N,3,N,N
                Use StringBuilder append the val and , 
                if we get null then append N
                Time - O(n)
                Space - O(n)

        DeSerialize
                // define global index counter (if you pass in function counter will not get update after node.left)

                node.left -> should point to 2 pointer
                node.right -> should point to 3 pointer
                but if we pass in function both node.left , node.right we pass value 2 which causes wrong pointing

                Time - O(n)
                Space - O(n)

        Both logic has preoder traversal

*/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        serializeDFS(root, sb);
        return sb.toString();
        
    }

    public void serializeDFS(TreeNode node, StringBuilder sb){

        if(node == null){
            sb.append("N").append(",");
            return;
        }

        sb.append(node.val).append(",");

        serializeDFS(node.left,sb);
        serializeDFS(node.right,sb);
    }

    int index = 0;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str = data.split(",");

        return deserializeDFS(str);
    }

    //1,2,N,N,3,N,N
    public TreeNode deserializeDFS(String[] str){

        String val = str[index];
        index++;
        if(val.equals("N")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));

        node.left=deserializeDFS(str);
        node.right=deserializeDFS(str);

        return node;


    }


}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));