package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class MinimumDeapthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int res = 1;
        if (root.left == null) {
            res += minDepth(root.right);
        } else if (root.right == null) {
            res += minDepth(root.left);
        } else {
            res += Math.min(minDepth(root.left), minDepth(root.right));
        }
        return res;
    }

}
