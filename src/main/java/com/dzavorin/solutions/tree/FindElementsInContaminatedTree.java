package com.dzavorin.solutions.tree;

public class FindElementsInContaminatedTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    TreeNode root;

    public FindElementsInContaminatedTree(TreeNode root) {
        this.root = root;
        if (root == null) {
            return;
        }
        root.val = 0;
        contamine(root);
    }

    public void contamine(TreeNode node) {
        if (node.left != null) {
            node.left.val = 2 * node.val + 1;
            contamine(node.left);
        }
        if (node.right != null) {
            node.right.val = 2 * node.val + 2;
            contamine(node.right);
        }
    }

    public boolean find(int target) {
        return find(root, target);
    }

    public boolean find(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }

        boolean res = find(node.left, target);
        if (!res) {
            res = find(node.right, target);
        }
        return res;
    }
}
