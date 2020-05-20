package com.dzavorin.solutions.tree;

import java.util.LinkedList;

public class ValidSequenceFromRootToLeavesBinaryTree {

    public boolean isValidSequence(TreeNode root, int[] arr) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        for (Integer i : arr) {
            LinkedList<TreeNode> l = new LinkedList<>();
            boolean found = false;
            while (!list.isEmpty()) {
                TreeNode node = list.poll();
                if (node.val == i) {
                    found = true;
                    if (node.left != null) l.add(node.left);
                    if (node.right != null) l.add(node.right);
                }
            }
            if (!found) {
                return false;
            }
            list = l;
        }
        return list.isEmpty();
    }

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
}
