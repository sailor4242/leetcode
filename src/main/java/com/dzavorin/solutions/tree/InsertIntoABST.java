package com.dzavorin.solutions.tree;

public class InsertIntoABST {

    public KthSmallestInBST.TreeNode insertIntoBST(KthSmallestInBST.TreeNode root, int val) {
        if (root == null) {
            return new KthSmallestInBST.TreeNode(val);
        }
        KthSmallestInBST.TreeNode node = root;
        while (node != null) {
            if (val < node.val) {
                if (node.left == null) {
                    node.left = new KthSmallestInBST.TreeNode(val);
                    break;
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new KthSmallestInBST.TreeNode(val);
                    break;
                }
                node = node.right;
            }
        }

        return root;
    }

}
