package com.dzavorin.solutions.tree;

public class SearchInABST {

    public KthSmallestInBST.TreeNode searchBST(KthSmallestInBST.TreeNode root, int val) {
        while(root != null) {
            if (root.val == val)
                return root;
            else if (val < root.val)
                root = root.left;
            else
                root = root.right;
        }

        return null;
    }

}
