package com.dzavorin.solutions.tree;

public class DiameterOfBinaryTree {

    private int i;

    public int diameterOfBinaryTree(TreeNode root)  {
        if (root == null) {
            return 0;
        }
        max(root);
        return i - 1;
    }

    private int max(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = max(node.left);
        int r = max(node.right);
        i = Math.max(i, l + r + 1);
        return Math.max(l, r) + 1;
    }

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

}
