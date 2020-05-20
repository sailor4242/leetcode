package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestInBST {

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

    public int kthSmallest(TreeNode root, int k) {

        List<Integer> res = new ArrayList();
        inOrder(root, res, k);
        return res.get(res.size() - 1);
    }

    public void inOrder(TreeNode node, List<Integer> res, int k) {
        if (node == null) {
            return;
        }

        inOrder(node.left, res, k);
        if (res.size() < k) {
            res.add(node.val);
        } else {
            return;
        }
        inOrder(node.right, res, k);
    }
}
