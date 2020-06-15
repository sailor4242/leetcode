package com.dzavorin.solutions.tree;

public class RangeSumOfBst {

    public int rangeSumBST(BinaryTreeMaximumPathSum.TreeNode root, int L, int R) {
        int res = 0;

        if (root == null) {
            return res;
        }

        res += rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);

        if (root.val >= L && root.val <= R) {
            res += root.val;
        }

        return res;
    }

}
