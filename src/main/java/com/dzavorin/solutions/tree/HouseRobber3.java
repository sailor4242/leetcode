package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class HouseRobber3 {

    public int rob(TreeNode root) {
        return Math.max(rob(root, true), rob(root, false));
    }

    public int rob(TreeNode node, boolean skip) {
        if (node == null) return 0;

        if (!skip) {
            return node.val +
                    rob(node.left, true) + rob(node.right, true);
        } else {
            return Math.max(rob(node.left, false), rob(node.left, true))
                    + Math.max(rob(node.right, false), rob(node.right, true));
        }
    }

    public int rob2(TreeNode root) {
        int[] rootValue = getNodeVal(root);
        return Math.max(rootValue[0], rootValue[1]);
    }

    /**
     * Returns 2 values for each node
     * int[0] - node value - value if you pick it
     * int[1] = skip value - value if you skip this node
     */
    private int[] getNodeVal(TreeNode root) {

        if (root == null) return new int[2];

        int[] leftNode = getNodeVal(root.left);
        int[] rightNode = getNodeVal(root.right);

        int nodeValue = root.val + leftNode[1] + rightNode[1];

        // you can choose the child or its sub child for left and right
        int nodeSkipValue = Math.max(leftNode[0], leftNode[1]) + Math.max(rightNode[0], rightNode[1]);

        return new int[] {nodeValue, nodeSkipValue};
    }

}
