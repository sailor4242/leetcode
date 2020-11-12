package com.dzavorin.solutions.tree;

public class BinaryTreeMaximumPathSum {

    int s = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return s;
    }

    int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int val = root.val;
        int l = Math.max(maxPath(root.left), 0);
        int r = Math.max(maxPath(root.right), 0);

        s = Math.max(s, val + l + r);
        return root.val + Math.max(l, r);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x; this.left = left; this.right = right;
        }
    }
}
