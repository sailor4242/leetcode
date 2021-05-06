package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.SumOfNodesWithEvenValuedGrandParent.*;

public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }
        int r = root.val;
        if ((low != null && r <= low) || (high != null && high <= r)) {
            return false;
        }

        return isValidBSTHelper(root.left, low, r) && isValidBSTHelper(root.right, r, high);
    }

}
