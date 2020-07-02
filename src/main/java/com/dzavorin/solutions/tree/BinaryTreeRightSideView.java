package com.dzavorin.solutions.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {

    List<Integer> list = new ArrayList<>();
    int j = 0;

    public List<Integer> rightSideView(KthSmallestInBST.TreeNode root) {
        rightSideView(root, 1);
        return list;
    }

    public void rightSideView(KthSmallestInBST.TreeNode root, int i) {

        if (root == null) {
            return;
        }
        if (i > j) {
            list.add(root.val);
            j++;
        }
        rightSideView(root.right, i + 1);
        rightSideView(root.left, i + 1);

    }

}
