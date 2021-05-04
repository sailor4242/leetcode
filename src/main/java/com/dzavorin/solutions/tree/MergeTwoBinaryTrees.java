package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.TreeNode;

public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        TreeNode next = new TreeNode((t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val));

        TreeNode t1l = t1 == null ? null : t1.left;
        TreeNode t2l = t2 == null ? null : t2.left;
        TreeNode t1r = t1 == null ? null : t1.right;
        TreeNode t2r = t2 == null ? null : t2.right;

        next.left = mergeTrees(t1l , t2l);

        next.right = mergeTrees(t1r, t2r);

        return next;
    }

}
