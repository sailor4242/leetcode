package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class RecoverBinarySearchTree {

    public void recoverTree(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        inorder(root, nodes);

        List<Integer> vals = new ArrayList<>();
        for (TreeNode node : nodes) {
            vals.add(node.val);
        }
        vals.sort(null);

        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).val = vals.get(i);
        }
    }

    private void inorder (TreeNode node, List<TreeNode> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node);
        inorder(node.right, res);
    }


    /////////////////


    TreeNode prev = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree2(TreeNode root) {

        inorder(root);

        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        if (prev != null && prev.val > node.val) {
            if (first == null) {
                first = prev;
            }
            second = node;
        }

        prev = node;
        inorder(node.right);
    }
}
