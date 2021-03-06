package com.dzavorin.solutions.tree;

public class BinaryTreePruning {

    public BinaryTreeMaximumPathSum.TreeNode pruneTree(BinaryTreeMaximumPathSum.TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }

}
