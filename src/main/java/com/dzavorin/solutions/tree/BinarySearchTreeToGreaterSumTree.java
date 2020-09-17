package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class BinarySearchTreeToGreaterSumTree {

    public TreeNode bstToGst(TreeNode root) {

        bstToGst(root, 0);
        return root;

    }

    public int bstToGst(TreeNode root, int s) {
        if (root == null) return 0;

        int sum = root.val;

        sum += bstToGst(root.right, s);

        root.val = s + sum;

        sum += bstToGst(root.left, s + sum);

        return sum;
    }

}
