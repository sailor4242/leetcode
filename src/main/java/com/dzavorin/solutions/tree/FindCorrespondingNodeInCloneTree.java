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
}
