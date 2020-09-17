package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int imax = 0;
        int max = 0;
        for (int i = lo; i <= hi; i++) {
            if (max <= nums[i]) {
                max = nums[i];
                imax = i;
            }
        }

        TreeNode root = new TreeNode(max);

        root.right = constructMaximumBinaryTree(nums, imax + 1, hi);
        root.left = constructMaximumBinaryTree(nums, lo, imax - 1);

        return root;
    }

}
