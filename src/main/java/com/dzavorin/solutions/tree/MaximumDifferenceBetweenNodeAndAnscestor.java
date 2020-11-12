package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class MaximumDifferenceBetweenNodeAndAnscestor {

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;

        int nextAbsL = maxAncestorDiff(root.left, root.val, root.val);
        int nextAbsR = maxAncestorDiff(root.right, root.val, root.val);

        return Math.max(nextAbsL, nextAbsR);
    }

    private int maxAncestorDiff(TreeNode root, int min, int max) {
        if (root == null) return 0;

        int minAbs = Math.abs(min - root.val);
        int maxAbs = Math.abs(max - root.val);

        int nextAbsL = maxAncestorDiff(root.left, Math.min(min, root.val), Math.max(max, root.val));
        int nextAbsR = maxAncestorDiff(root.right, Math.min(min, root.val), Math.max(max, root.val));

        return Math.max(Math.max(nextAbsL, nextAbsR), Math.max(minAbs, maxAbs));

    }

    ////////

    public int maxAncestorDiff2(TreeNode root) {
        if (root == null) return 0;

        return maxAncestorDiff2(root, root.val, root.val);
    }

    private int maxAncestorDiff2(TreeNode root, int min, int max) {
        if (root == null) return max - min;

        int nextAbsL = maxAncestorDiff2(root.left, Math.min(min, root.val), Math.max(max, root.val));
        int nextAbsR = maxAncestorDiff2(root.right, Math.min(min, root.val), Math.max(max, root.val));

        return Math.max(nextAbsL, nextAbsR);

    }

}
