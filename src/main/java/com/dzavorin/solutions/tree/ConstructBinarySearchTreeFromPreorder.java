package com.dzavorin.solutions.tree;

import com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.TreeNode;

public class ConstructBinarySearchTreeFromPreorder {
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            add(root, preorder[i]);
        }
        return root;
    }

    public void add(TreeNode cur, int x) {
        if (x < cur.val ) {
            if (cur.left == null) {
                cur.left = new TreeNode(x);
            } else {
                add(cur.left, x);
            }
        } else {
            if (cur.right == null) {
                cur.right = new TreeNode(x);
            } else {
                add(cur.right, x);
            }
        }
    }

}
