package com.dzavorin.solutions.tree;

import java.util.LinkedList;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class LowestCommonAncestor {

    ////// simple dfs

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else {
            return left == null ? right : left;
        }
    }

    ////// with lists

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> first = new LinkedList<>();
        lowestCommonAncestor(root, p, first);

        LinkedList<TreeNode> second = new LinkedList<>();
        lowestCommonAncestor(root, q, second);

        TreeNode lca = null;
        while (!first.isEmpty() && !second.isEmpty()) {
            if (first.getLast() == second.removeLast()) {
                lca = first.removeLast();
            } else {
                break;
            }
        }

        return lca;
    }

    public boolean lowestCommonAncestor(TreeNode node,
                                        TreeNode p,
                                        LinkedList<TreeNode> list) {

        if (node == null) {
            return false;
        }

        if (node.val == p.val) {
            list.add(node);
            return true;
        }

        if (lowestCommonAncestor(node.left, p, list) || lowestCommonAncestor(node.right, p, list)) {
            list.add(node);
            return true;
        } else {
            return false;
        }

    }

}
