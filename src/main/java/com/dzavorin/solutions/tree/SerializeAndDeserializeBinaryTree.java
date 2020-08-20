package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.TreeNode;

public class SerializeAndDeserializeBinaryTree {

    public String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        serializeH(root, sb);
        return sb.toString();
    }

    public void serializeH(TreeNode node, StringBuilder sb) {
        if (sb.length() != 0) sb.append(",");
        if (node == null) {
            sb.append("#");
            return;
        }

        sb.append(node.val);
        serializeH(node.left, sb);
        serializeH(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        if (vals.length == 0 || vals[0].equals("#")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        int[] a = new int[]{1};
        root.left = deserializeH(vals, a);
        root.right = deserializeH(vals, a);

        return root;
    }

    public TreeNode deserializeH(String[] vals, int[] a) {

        if (vals[a[0]].equals("#")) {
            a[0]++;
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(vals[a[0]]));
            a[0]++;
            if (a[0] < vals.length) node.left = deserializeH(vals, a);
            if (a[0] < vals.length) node.right = deserializeH(vals, a);

            return node;
        }

    }
}
