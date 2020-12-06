package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class IncreasingOrderSearchTree {

    public TreeNode increasingBST(TreeNode root) {
        List<Integer> vals = new ArrayList();
        inorder(root, vals);
        TreeNode ans = new TreeNode(0), cur = ans;
        for (int v : vals) {
            cur.right = new TreeNode(v);
            cur = cur.right;
        }
        return ans.right;
    }

    public void inorder(TreeNode node, List<Integer> vals) {
        if (node == null) return;
        inorder(node.left, vals);
        vals.add(node.val);
        inorder(node.right, vals);
    }

    ///// rec

    TreeNode cur;

    public TreeNode increasingBST2(TreeNode root) {
        TreeNode ans = new TreeNode(0); // dummy node
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        node.left = null;
        cur.right = node;
        cur = cur.right;

        inorder(node.right);
    }

}
