package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        if (root == null) return 0;

        return findTiltH(root)[0];
    }

    public int[] findTiltH(TreeNode root) {
        if (root == null) return new int[] {0, 0};

        if (root.left == null && root.right == null) {
            return new int []{0, root.val};
        }

        int[] left = findTiltH(root.left);
        int[] right = findTiltH(root.right);

        int tilt = Math.abs(left[1] - right[1]);

        // 0 index - current tilt,
        // 1 index - current sum
        return new int[] {tilt + left[0] + right[0], root.val + left[1] + right[1]};
    }

}
