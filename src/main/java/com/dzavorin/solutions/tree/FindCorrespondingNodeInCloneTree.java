package com.dzavorin.solutions.tree;

import static com.dzavorin.solutions.tree.BinaryTreeMaximumPathSum.*;

public class FindCorrespondingNodeInCloneTree {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;

        if (target == original) return cloned;
        TreeNode lnode = getTargetCopy(original.left, cloned.left, target);
        if (lnode != null) return lnode;
        TreeNode rnode = getTargetCopy(original.right, cloned.right, target);
        if (rnode != null) return rnode;
        return null;
    }

    ///


    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null || target == null) {
            return null;
        }

        if (original.val == target.val && cloned.val == target.val) {
            return cloned;
        }

        TreeNode l = getTargetCopy(original.left, cloned.left, target);
        if (l != null) return l;

        TreeNode r = getTargetCopy(original.right, cloned.right, target);
        if (r != null) return r;

        return null;

    }
}
